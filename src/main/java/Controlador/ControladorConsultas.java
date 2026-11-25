package Controlador;

import Modelo.*;
import Vista.VentanaConsultas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.event.ListSelectionListener;

public class ControladorConsultas implements ActionListener, ListSelectionListener {
    private final Clinica clinica;
    private final VentanaConsultas vista;
    private boolean refrescando;
    private final DateTimeFormatter hora = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    public ControladorConsultas(Clinica clinica, VentanaConsultas vista) {
        this.clinica = clinica;
        this.vista = vista;
        vista.alRegistrar(this);
        vista.alGuardar(this);
        vista.alBuscar(this);
        vista.alAhora(this);
        vista.alListar(this);
        vista.alNuevo(this);
        vista.alVolver(this);
        vista.alCambiarCita(this);
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
                case "registrar":
                    atender();
                    break;
                case "guardar":
                    guardarClinica();
                    break;
                case "buscar":
                    verConsulta();
                    break;
                case "cita":
                    seleccionarCita();
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
                case "ahora":
                    vista.setFecha(LocalDateTime.now().format(hora));
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

    private void seleccionarCita() {
        if (vista.getCita().isBlank()) {
            return;
        }
        Cita cita = cita(vista.getCita());
        vista.setMascota(cita.getMascota().getIdMascota());
        vista.mostrarInformacion("Cita de " + cita.getMascota().getNombre() + " seleccionada para la atención.");
    }

    private void atender() {
        String id = obligatorio(vista.getId(), "el identificador de consulta");
        exigir(encontrarConsulta(id) == null, "Esta consulta ya existe; use Guardar datos clínicos.");
        Mascota mascota = mascota(obligatorio(vista.getMascota(), "la mascota"));
        Veterinario veterinario = veterinario(obligatorio(vista.getVeterinario(), "el veterinario"));
        LocalDateTime fecha = fechaHora(vista.getFecha());
        String diagnostico = vista.getDiagnostico();
        String tratamientos = vista.getTratamientos();
        String notas = vista.getNotas();
        Cita cita = vista.getCita().isBlank() ? null : cita(vista.getCita());
        if (cita != null) {
            exigir(cita.estaProgramada() && cita.getMascota() == mascota,
                    "La cita debe estar programada y pertenecer a la mascota seleccionada.");
        }
        ConsultaMedica consulta = veterinario.registrarConsulta(id, fecha, mascota, cita);
        consulta.registrarDiagnostico(diagnostico);
        consulta.registrarTratamientos(tratamientos);
        consulta.registrarNotas(notas);
        actualizar();
        verConsulta();
        vista.mostrarInformacion("Atención creada. Complete el diagnóstico e insumos antes de emitir el cobro.");
    }

    private void guardarClinica() {
        ConsultaMedica consulta = consulta(vista.getId());
        exigir(consulta.estaAbierta(), "La consulta ya tiene cobro; no se pueden cambiar sus datos.");
        consulta.registrarDiagnostico(vista.getDiagnostico());
        consulta.registrarTratamientos(vista.getTratamientos());
        consulta.registrarNotas(vista.getNotas());
        actualizar();
        verConsulta();
        vista.mostrarInformacion("Datos clínicos guardados.");
    }

    private void verConsulta() {
        ConsultaMedica consulta = consulta(vista.getId());
        vista.setCita(consulta.getCita() == null ? "" : consulta.getCita().getIdCita());
        vista.setMascota(consulta.getHistorial().getMascota().getIdMascota());
        vista.setVeterinario(consulta.getVeterinario().getDocumento());
        vista.setFecha(consulta.getFecha().format(hora));
        vista.setDiagnostico(consulta.getDiagnostico());
        vista.setTratamientos(consulta.getTratamientos());
        vista.setNotas(consulta.getNotasMedicas());
        vista.habilitarMascota(false);
        vista.habilitarVeterinario(false);
        vista.habilitarCita(false);
        vista.habilitarFecha(false);
        vista.habilitarBotonAhora(false);
        vista.habilitarDiagnostico(!(!consulta.estaAbierta()));
        vista.habilitarTratamientos(!(!consulta.estaAbierta()));
        vista.habilitarNotas(!(!consulta.estaAbierta()));
        vista.habilitarBotonRegistrar(false);
        vista.habilitarBotonGuardar(consulta.estaAbierta());
        vista.mostrarResultado(datosConsulta(consulta));
    }

    private String datosConsulta(ConsultaMedica consulta) {
        return "Consulta: " + consulta.getIdConsulta() + "\nPaciente: " + consulta.getHistorial().getMascota().getNombre()
                + "\nFecha: " + consulta.getFecha().format(hora) + "\nVeterinario: " + consulta.getVeterinario().getNombre()
                + "\nCita vinculada: " + (consulta.getCita() == null ? "Sin cita" : consulta.getCita().getIdCita())
                + "\nDiagnóstico: " + (consulta.getDiagnostico().isBlank() ? "PENDIENTE" : consulta.getDiagnostico())
                + "\nTratamientos: " + consulta.getTratamientos() + "\nNotas: " + consulta.getNotasMedicas()
                + "\nEstado: " + (consulta.estaAbierta() ? "Abierta" : "Con cobro emitido");
    }

    private Veterinario veterinario(String documento) {
        Veterinario v = clinica.buscarVeterinario(documento);
        exigir(v != null, "No se encontró el veterinario. Regístrelo en Personal.");
        return v;
    }

    private Mascota encontrarMascota(String id) {
        for (Propietario p : clinica.getPropietarios()) {
            Mascota m = p.buscarMascota(id);
            if (m != null) {
                return m;
            }
        }
        return null;
    }

    private Mascota mascota(String id) {
        Mascota m = encontrarMascota(id);
        exigir(m != null, "Seleccione una mascota registrada en el listado.");
        return m;
    }

    private Cita encontrarCita(String id) {
        for (Cita c : clinica.getCitas()) {
            if (c.getIdCita().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private Cita cita(String id) {
        Cita c = encontrarCita(id);
        exigir(c != null, "Seleccione una cita registrada.");
        return c;
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

    private LocalDateTime fechaHora(String campo) {
        try {
            return LocalDateTime.parse(obligatorio(campo, "la fecha y hora"), hora);
        }
        catch (DateTimeParseException error) {
            error("Fecha inválida. Use dd/MM/aaaa HH:mm, por ejemplo 24/11/2026 09:30.");
            return null;
        }
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
            ArrayList<String> idsMascota = new ArrayList<>();
            ArrayList<String> nombresMascota = new ArrayList<>();
            for (Mascota registro : clinica.getMascotas()) {
                idsMascota.add(registro.getIdMascota());
                nombresMascota.add(registro.getNombre() + " (" + registro.getIdMascota() + ")");
            }
            vista.cargarMascota(idsMascota, nombresMascota);
            ArrayList<String> idsVeterinario = new ArrayList<>();
            ArrayList<String> nombresVeterinario = new ArrayList<>();
            for (Veterinario registro : clinica.getVeterinarios()) {
                idsVeterinario.add(registro.getDocumento());
                nombresVeterinario.add(registro.getNombre() + " (" + registro.getDocumento() + ")");
            }
            vista.cargarVeterinario(idsVeterinario, nombresVeterinario);
            ArrayList<String> idsCita = new ArrayList<>();
            ArrayList<String> nombresCita = new ArrayList<>();
            for (Cita registro : clinica.getCitas()) {
                idsCita.add(registro.getIdCita());
                nombresCita.add(registro.getIdCita() + " · " + registro.getMascota().getNombre() + " · " + registro.getFechaHora().format(hora));
            }
            vista.cargarCita(idsCita, nombresCita);
            cargarConsultas();
            if (encontrarConsulta(vista.getId()) != null) {
                verConsulta();
            } else {
                vista.habilitarBotonRegistrar(!clinica.getMascotas().isEmpty() && !clinica.getVeterinarios().isEmpty());
            }
        } finally {
            refrescando = anterior;
        }
    }

    private void cargarConsultas() {
        ArrayList<String[]> filas = new ArrayList<>();
        for (ConsultaMedica consulta : clinica.getConsultas()) {
            String estado = "Con cobro";
            if (consulta.estaAbierta()) {
                estado = "Abierta";
            }
            filas.add(new String[]{consulta.getIdConsulta(), consulta.getHistorial().getMascota().getNombre(),
                    consulta.getVeterinario().getNombre(), consulta.getFecha().format(hora), estado});
        }
        vista.mostrarRegistros(filas);
    }

    private void nuevo() {
        vista.habilitarMascota(true);
        vista.habilitarVeterinario(true);
        vista.habilitarCita(true);
        vista.habilitarFecha(true);
        vista.habilitarBotonAhora(true);
        vista.habilitarDiagnostico(true);
        vista.habilitarTratamientos(true);
        vista.habilitarNotas(true);
        vista.setId(id("Q", clinica.getConsultas().size()));
        vista.setFecha(LocalDateTime.now().format(hora));
        vista.setCita("");
        vista.setDiagnostico("");
        vista.setTratamientos("");
        vista.setNotas("");
        vista.habilitarBotonRegistrar(!clinica.getMascotas().isEmpty() && !clinica.getVeterinarios().isEmpty());
        vista.habilitarBotonGuardar(false);
        vista.mostrarResultado("La cita es opcional. Puede completar el diagnóstico después; debe hacerlo antes de emitir el cobro.");
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
            vista.setId(id);
            verConsulta();
        } catch (IllegalArgumentException | IllegalStateException error) {
            vista.mostrarError(error.getMessage());
        } finally {
            refrescando = false;
        }
    }
}
