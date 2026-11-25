package Controlador;

import Modelo.*;
import Vista.VentanaInsumos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.event.ListSelectionListener;

public class ControladorInsumos implements ActionListener, ListSelectionListener {
    private final Clinica clinica;
    private final VentanaInsumos vista;
    private boolean refrescando;
    private final DateTimeFormatter hora = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    private final DateTimeFormatter dia = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public ControladorInsumos(Clinica clinica, VentanaInsumos vista) {
        this.clinica = clinica;
        this.vista = vista;
        vista.alAplicar(this);
        vista.alListar(this);
        vista.alNuevo(this);
        vista.alVolver(this);
        vista.alCambiarConsulta(this);
        vista.alCambiarTipo(this);
        vista.alSeleccionar(this);
        vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent evento) {
                actualizar();
            }
        });
        actualizar();
        nuevo();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (refrescando) {
            return;
        }
        refrescando = true;
        try {
            switch (evento.getActionCommand()) {
                case "aplicar":
                    aplicar();
                    break;
                case "consulta":
                    verInsumos();
                    break;
                case "tipo":
                    ajustarTipo();
                    break;
                case "listar":
                    actualizar();
                    break;
                case "nuevo":
                    nuevo();
                    break;
                case "volver":
                    vista.setVisible(false);
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException | IllegalStateException error) {
            vista.mostrarError(error.getMessage());
        } finally {
            refrescando = false;
        }
    }

    private String obligatorio(String campo, String nombre) {
        String texto = campo.trim();
        if (texto.isBlank()) {
            error("Complete " + nombre + ".");
        }
        return texto;
    }

    private void exigir(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    private void aplicar() {
        ConsultaMedica consulta = consulta(obligatorio(vista.getConsulta(), "la consulta"));
        exigir(consulta.estaAbierta(), "La consulta ya tiene cobro; no admite insumos nuevos.");
        String codigo = obligatorio(vista.getCodigo(), "el código del insumo");
        exigir(consulta.buscarItem(codigo) == null, "Ya existe ese código en la consulta. Use otro código.");
        String nombre = obligatorio(vista.getNombre(), "el nombre del insumo");
        int cantidad = entero(vista.getCantidad(), "La cantidad", 1);
        double unitario = importe(vista.getValor(), "El valor unitario", true);
        double acumulado = clinica.getTarifaBase() * 1.25 + clinica.getCostoSedacion()
                + consulta.calcularValorVacunas() + consulta.calcularValorMedicamentos() + cantidad * unitario;
        exigir(Double.isFinite(acumulado), "El importe acumulado excede el límite numérico.");
        ItemAplicado item;
        if (vista.getTipo().equals("Vacuna")) {
            String lote = obligatorio(vista.getLote(), "el lote");
            LocalDate proxima = proximaDosis();
            if (proxima != null && proxima.isBefore(consulta.getFecha().toLocalDate())) {
                error("La próxima dosis no puede ser anterior a la aplicación.");
            }
            item = new Vacuna(codigo, nombre, cantidad, unitario, lote, proxima);
        } else {
            String dosis = obligatorio(vista.getDosis(), "la dosis");
            String via = obligatorio(vista.getVia(), "la vía de administración");
            item = new Medicamento(codigo, nombre, cantidad, unitario, dosis, via);
        }
        consulta.agregarItem(item);
        actualizar();
        verInsumos();
        vista.mostrarInformacion("Insumo aplicado y añadido al historial y al cobro de esta consulta.");
    }

    private void verInsumos() {
        if (vista.getConsulta().isBlank()) {
            vista.habilitarBotonAplicar(false);
            vista.mostrarRegistros(List.of());
            vista.mostrarResultado("Seleccione una consulta. Solo se pueden modificar consultas abiertas.");
            return;
        }
        ConsultaMedica consulta = consulta(vista.getConsulta());
        List<String[]> filas = new ArrayList<>();
        for (ItemAplicado item : consulta.getItems()) {
            filas.add(new String[]{item.getCodigo(),
                item instanceof Vacuna ? "Vacuna" : "Medicamento", item.getNombre(),
                String.valueOf(item.getCantidad()), moneda(item.calcularTotal())});
        }
        vista.mostrarRegistros(filas);
        vista.mostrarResultado(datosConsulta(consulta) + "\n\nVacunas: "
                + moneda(consulta.calcularValorVacunas()) + "\nMedicamentos: " + moneda(consulta.calcularValorMedicamentos()));
        vista.habilitarBotonAplicar(consulta.estaAbierta());
    }

    private String datosConsulta(ConsultaMedica consulta) {
        return "Consulta: " + consulta.getIdConsulta() + "\nPaciente: " + consulta.getHistorial().getMascota().getNombre()
                + "\nFecha: " + consulta.getFecha().format(hora) + "\nVeterinario: " + consulta.getVeterinario().getNombre()
                + "\nCita vinculada: " + (consulta.getCita() == null ? "Sin cita" : consulta.getCita().getIdCita())
                + "\nDiagnóstico: " + (consulta.getDiagnostico().isBlank() ? "PENDIENTE" : consulta.getDiagnostico())
                + "\nTratamientos: " + consulta.getTratamientos() + "\nNotas: " + consulta.getNotasMedicas()
                + "\nEstado: " + (consulta.estaAbierta() ? "Abierta" : "Con cobro emitido");
    }

    private ConsultaMedica encontrarConsulta(String id) {
        for (Mascota m : clinica.getMascotas()) {
            ConsultaMedica q = m.obtenerHistorial().buscarConsulta(id);
            if (q != null) {
                return q;
            }
        }
        return null;
    }

    private ConsultaMedica consulta(String id) {
        ConsultaMedica q = encontrarConsulta(id);
        exigir(q != null, "Seleccione una consulta registrada.");
        return q;
    }

    private int entero(String campo, String nombre, int minimo) {
        try {
            int numero = Integer.parseInt(obligatorio(campo, nombre));
            if (numero < minimo) {
                error(nombre + " debe ser al menos " + minimo + ".");
            }
            return numero;
        } catch (NumberFormatException error) {
            error(nombre + " debe ser un entero válido, sin separadores.");
            return 0;
        }
    }

    private double numero(String campo, String nombre, boolean permiteCero) {
        String texto = obligatorio(campo, nombre);
        if (!texto.matches("[0-9]+([.,][0-9]+)?")) {
            error(
                nombre + ": use números sin separadores de miles; el decimal puede ser punto o coma.");
        }
        double numero = Double.parseDouble(texto.replace(',', '.'));
        if (!Double.isFinite(numero) || numero < 0 || (!permiteCero && numero == 0)) {
            error(nombre + (permiteCero ? " debe ser mayor o igual que cero." : " debe ser mayor que cero."));
        }
        return numero;
    }

    private double dinero(double cantidad) {
        return BigDecimal.valueOf(cantidad).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private double importe(String campo, String nombre, boolean permiteCero) {
        double cantidad = numero(campo, nombre, permiteCero);
        if (BigDecimal.valueOf(cantidad).stripTrailingZeros().scale() > 2) {
            error(nombre + " admite como máximo dos cifras decimales.");
        }
        return dinero(cantidad);
    }

    private LocalDate proximaDosis() {
        if (vista.getProxima().isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(vista.getProxima(), dia);
        }
        catch (DateTimeParseException error) {
            error("Próxima dosis inválida. Use dd/MM/aaaa o deje el campo vacío.");
            return null;
        }
    }

    private String moneda(double importe) {
        return String.format(Locale.forLanguageTag("es-CO"), "$%,.2f", importe);
    }

    private void error(String mensaje) {
        throw new IllegalArgumentException(mensaje);
    }

    public void actualizar() {
        boolean anterior = refrescando;
        refrescando = true;
        try {
            ArrayList<String> idsConsulta = new ArrayList<>();
            ArrayList<String> nombresConsulta = new ArrayList<>();
            for (ConsultaMedica registro : clinica.getConsultas()) {
                idsConsulta.add(registro.getIdConsulta());
                nombresConsulta.add(registro.getIdConsulta() + " · " + registro.getHistorial().getMascota().getNombre() + (registro.estaAbierta() ? " · Abierta" : " · Con cobro"));
            }
            vista.cargarConsulta(idsConsulta, nombresConsulta);
            verInsumos();
        } finally {
            refrescando = anterior;
        }
    }

    private void nuevo() {
        vista.setCodigo("");
        vista.setNombre("");
        vista.setValor("");
        vista.setLote("");
        vista.setProxima("");
        vista.setDosis("");
        vista.setVia("");
        vista.setCantidad("1");
        verInsumos();
        ajustarTipo();
        vista.mostrarResultado("Seleccione una consulta abierta. Valores sin puntos de miles, hasta dos decimales. Próxima dosis opcional.");
        vista.mostrarInformacion("Formulario nuevo. Los registros guardados se conservan.");
    }

    private void ajustarTipo() {
        boolean vacuna = vista.getTipo().equals("Vacuna");
        vista.habilitarLote(vacuna);
        vista.habilitarProxima(vacuna);
        vista.habilitarDosis(!vacuna);
        vista.habilitarVia(!vacuna);
    }

    @Override
    public void valueChanged(javax.swing.event.ListSelectionEvent evento) {
        if (refrescando || vista.estaActualizando() || evento.getValueIsAdjusting()) {
            return;
        }
        String id = vista.getIdSeleccionado();
        if (id.isEmpty()) {
            return;
        }
        refrescando = true;
        try {
            vista.mostrarInformacion("Los insumos aplicados no se editan; use Nuevo para añadir otro.");
        } catch (IllegalArgumentException | IllegalStateException error) {
            vista.mostrarError(error.getMessage());
        } finally {
            refrescando = false;
        }
    }
}
