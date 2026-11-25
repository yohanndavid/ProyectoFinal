package Controlador;

import Modelo.*;
import Vista.VentanaCobros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.event.ListSelectionListener;

public class ControladorCobros implements ActionListener, ListSelectionListener {
    private final Clinica clinica;
    private final VentanaCobros vista;
    private boolean refrescando;

    public ControladorCobros(Clinica clinica, VentanaCobros vista) {
        this.clinica = clinica;
        this.vista = vista;
        vista.alPrever(this);
        vista.alGenerar(this);
        vista.alPagar(this);
        vista.alConsultar(this);
        vista.alListar(this);
        vista.alNuevo(this);
        vista.alVolver(this);
        vista.alSeleccionar(this);
        vista.alCambiarConsulta(this);
        vista.alCambiarRecepcionista(this);
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
                case "generar":
                    cobrar();
                    break;
                case "prever":
                    prever();
                    break;
                case "pagar":
                    pagar();
                    break;
                case "consultar":
                    verCobro();
                    break;
                case "listar":
                    actualizar();
                    break;
                case "nuevo":
                    nuevo();
                    break;
                case "consulta":
                case "recepcionista":
                    limpiarVistaPrevia();
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

    private void cobrar() {
        String numero = obligatorio(vista.getNumero(), "el número de cobro");
        exigir(encontrarCobro(numero) == null, "Este cobro ya existe. Pulse Nuevo para emitir otro.");
        ConsultaMedica consulta = consulta(obligatorio(vista.getConsulta(), "la consulta"));
        exigir(consulta.estaAbierta(), "La consulta ya tiene un cobro. Selecciónelo en el listado.");
        exigir(!consulta.getDiagnostico().isBlank(), "Falta el diagnóstico: complételo en Atención médica antes de cobrar.");
        Recepcionista recepcionista = recepcionista(obligatorio(vista.getRecepcionista(), "la recepcionista"));
        Cobro previo = consulta.previsualizarCobro(clinica.getTarifaBase(), clinica.getCostoSedacion(), recepcionista);
        if (!vista.confirmar("¿Emitir el cobro de " + consulta.getHistorial().getMascota().getNombre()
                + " por " + moneda(previo.calcularTotal())
                + "? Después no podrá modificar la consulta ni sus insumos. Emitir no registra el pago.")) {
            return;
        }
        recepcionista.registrarCobro(numero, consulta, clinica.getTarifaBase(), clinica.getCostoSedacion());
        actualizar();
        verCobro();
        vista.mostrarInformacion("Cobro emitido. Revise el comprobante y registre el pago cuando lo reciba.");
    }

    private void verCobro() {
        Cobro cobro = cobro(vista.getNumero());
        vista.setConsulta(cobro.getConsulta().getIdConsulta());
        vista.setRecepcionista(cobro.getRecepcionista().getDocumento());
        vista.mostrarResultado(cobro.emitirComprobante());
        vista.mostrarTotales(moneda(cobro.calcularSubtotal()), moneda(cobro.calcularDescuento()),
                moneda(cobro.calcularTotal()), cobro.isPagado() ? "PAGADO" : "PENDIENTE DE PAGO");
        vista.habilitarConsulta(false);
        vista.habilitarRecepcionista(false);
        vista.habilitarBotonGenerar(false);
        vista.habilitarBotonPrever(false);
        vista.habilitarBotonPagar(!cobro.isPagado());
        vista.habilitarMetodo(!cobro.isPagado());
        if (cobro.isPagado()) {
            vista.setMetodo(cobro.getMetodoPago());
        }
    }

    private void prever() {
        ConsultaMedica consulta = consulta(obligatorio(vista.getConsulta(), "la consulta"));
        Recepcionista recepcionista = recepcionista(obligatorio(vista.getRecepcionista(), "la recepcionista"));
        Cobro previo = consulta.previsualizarCobro(clinica.getTarifaBase(), clinica.getCostoSedacion(), recepcionista);
        vista.mostrarTotales(moneda(previo.calcularSubtotal()), moneda(previo.calcularDescuento()),
                moneda(previo.calcularTotal()), "VISTA PREVIA: SIN EMITIR");
        vista.mostrarResultado("VISTA PREVIA — no es un cobro emitido.\nPaciente: "
                + consulta.getHistorial().getMascota().getNombre()
                + "\nTarifa base: " + moneda(previo.getTarifaBase())
                + "\nRecargo: " + moneda(previo.getRecargo())
                + "\nSedación: " + moneda(previo.getCostoSedacion())
                + "\nVacunas: " + moneda(previo.getValorVacunas())
                + "\nMedicamentos: " + moneda(previo.getValorMedicamentos())
                + "\nSubtotal: " + moneda(previo.calcularSubtotal())
                + "\nDescuento: " + moneda(previo.calcularDescuento())
                + "\nTotal: " + moneda(previo.calcularTotal())
                + "\n\nPuede revisar y completar la consulta antes de emitir el cobro.");
    }

    private void pagar() {
        Cobro cobro = cobro(vista.getNumero());
        exigir(!cobro.isPagado(), "El cobro ya está pagado.");
        String metodo = obligatorio(vista.getMetodo(), "el método de pago");
        if (!vista.confirmar("¿Confirma que recibió " + moneda(cobro.calcularTotal()) + " por " + metodo + "?")) {
            return;
        }
        cobro.registrarPago(metodo);
        actualizar();
        verCobro();
        vista.mostrarInformacion("Pago registrado. Puede ver el comprobante en pantalla.");
    }

    private Recepcionista recepcionista(String documento) {
        Recepcionista r = clinica.buscarRecepcionista(documento);
        exigir(r != null, "No se encontró la recepcionista. Regístrela en Personal.");
        return r;
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

    private Cobro encontrarCobro(String id) {
        for (Cobro c : clinica.getCobros()) {
            if (c.getNumeroCobro().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private Cobro cobro(String id) {
        Cobro c = encontrarCobro(id);
        exigir(c != null, "Seleccione un cobro del listado.");
        return c;
    }

    private String moneda(double importe) {
        return String.format(Locale.forLanguageTag("es-CO"), "$%,.2f", importe);
    }

    private String id(String prefijo, int cantidad) {
        return prefijo + String.format(Locale.ROOT, "%03d", cantidad + 1);
    }

    private void error(String mensaje) {
        throw new IllegalArgumentException(mensaje);
    }

    public void actualizar() {
        boolean anterior = refrescando;
        refrescando = true;
        try {
            ArrayList<String> idsRecepcionista = new ArrayList<>();
            ArrayList<String> nombresRecepcionista = new ArrayList<>();
            for (Recepcionista registro : clinica.getRecepcionistas()) {
                idsRecepcionista.add(registro.getDocumento());
                nombresRecepcionista.add(registro.getNombre() + " (" + registro.getDocumento() + ")");
            }
            vista.cargarRecepcionista(idsRecepcionista, nombresRecepcionista);
            ArrayList<String> idsConsulta = new ArrayList<>();
            ArrayList<String> nombresConsulta = new ArrayList<>();
            for (ConsultaMedica registro : clinica.getConsultas()) {
                idsConsulta.add(registro.getIdConsulta());
                nombresConsulta.add(registro.getIdConsulta() + " · " + registro.getHistorial().getMascota().getNombre() + (registro.estaAbierta() ? " · Abierta" : " · Con cobro"));
            }
            vista.cargarConsulta(idsConsulta, nombresConsulta);
            cargarCobros();
            if (encontrarCobro(vista.getNumero()) != null) {
                verCobro();
            } else {
                vista.habilitarBotonGenerar(!clinica.getConsultas().isEmpty() && !clinica.getRecepcionistas().isEmpty());
                vista.habilitarBotonPrever(!clinica.getConsultas().isEmpty() && !clinica.getRecepcionistas().isEmpty());
            }
        } finally {
            refrescando = anterior;
        }
    }

    private void cargarCobros() {
        ArrayList<String[]> filas = new ArrayList<>();
        for (Cobro cobro : clinica.getCobros()) {
            String estado = "PENDIENTE";
            if (cobro.isPagado()) {
                estado = "PAGADO";
            }
            filas.add(new String[]{cobro.getNumeroCobro(), cobro.getConsulta().getIdConsulta(),
                    cobro.getConsulta().getHistorial().getMascota().getNombre(), moneda(cobro.calcularTotal()), estado});
        }
        vista.mostrarRegistros(filas);
    }

    private void limpiarVistaPrevia() {
        if (encontrarCobro(vista.getNumero()) == null) {
            vista.mostrarTotales("—", "—", "—", "SIN COBRO EMITIDO");
            vista.mostrarResultado("Calcule una nueva vista previa para la consulta seleccionada.");
        }
    }

    private void nuevo() {
        vista.habilitarConsulta(true);
        vista.habilitarRecepcionista(true);
        vista.habilitarMetodo(true);
        vista.setNumero(id("B", clinica.getCobros().size()));
        vista.habilitarBotonGenerar(!clinica.getConsultas().isEmpty() && !clinica.getRecepcionistas().isEmpty());
        vista.habilitarBotonPrever(!clinica.getConsultas().isEmpty() && !clinica.getRecepcionistas().isEmpty());
        vista.mostrarTotales("—", "—", "—", "SIN COBRO EMITIDO");
        vista.habilitarBotonPagar(false);
        vista.mostrarResultado("Vista previa no cierra la consulta. Emitir congela los valores; registrar pago confirma el dinero recibido.");
        vista.mostrarInformacion("Formulario nuevo. Los registros guardados se conservan.");
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
            vista.setNumero(id);
            verCobro();
        } catch (IllegalArgumentException | IllegalStateException error) {
            vista.mostrarError(error.getMessage());
        } finally {
            refrescando = false;
        }
    }
}
