package Controlador;

import Modelo.*;
import Vista.VentanaCitas;
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

public class ControladorCitas implements ActionListener, ListSelectionListener {
    private final Clinica clinica;
    private final VentanaCitas vista;
    private boolean refrescando;
    private final DateTimeFormatter hora = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    public ControladorCitas(Clinica clinica, VentanaCitas vista) {
        this.clinica = clinica;
        this.vista = vista;
        vista.alAgendar(this);
        vista.alBuscar(this);
        vista.alCancelar(this);
        vista.alAhora(this);
        vista.alListar(this);
        vista.alNuevo(this);
        vista.alVolver(this);
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
                case "agendar":
                    agendar();
                    break;
                case "buscar":
                    verCita();
                    break;
                case "cancelar":
                    cancelar();
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

    private void agendar() {
        String id = obligatorio(vista.getId(), "el identificador de cita");
        exigir(encontrarCita(id) == null, "Esta cita ya está registrada.");
        Mascota mascota = mascota(obligatorio(vista.getMascota(), "la mascota"));
        Recepcionista recepcionista = recepcionista(obligatorio(vista.getRecepcionista(), "la recepcionista"));
        LocalDateTime fecha = fechaHora(vista.getFecha());
        String motivo = obligatorio(vista.getMotivo(), "el motivo");
        for (Cita registrada : mascota.getCitas()) {
            if (!registrada.getEstado().equals("CANCELADA") && registrada.getFechaHora().equals(fecha)) {
                throw new IllegalArgumentException("La mascota ya tiene una cita en esa fecha y hora.");
            }
        }
        recepcionista.agendarCita(id, fecha, motivo, mascota);
        actualizar();
        verCita();
        vista.mostrarInformacion("Cita agendada.");
    }

    private void verCita() {
        Cita cita = cita(vista.getId());
        vista.setMascota(cita.getMascota().getIdMascota());
        vista.setRecepcionista(cita.getRecepcionista().getDocumento());
        vista.setFecha(cita.getFechaHora().format(hora));
        vista.setMotivo(cita.getMotivo());
        vista.habilitarMascota(false);
        vista.habilitarRecepcionista(false);
        vista.habilitarFecha(false);
        vista.habilitarBotonAhora(false);
        vista.habilitarMotivo(false);
        vista.habilitarBotonAgendar(false);
        vista.habilitarBotonCancelar(cita.estaProgramada());
        vista.mostrarResultado("Cita: " + cita.getIdCita() + "\nPaciente: "
                + cita.getMascota().getNombre() + "\nFecha: " + cita.getFechaHora().format(hora)
                + "\nMotivo: " + cita.getMotivo() + "\nEstado: " + cita.getEstado());
    }

    private void cancelar() {
        Cita cita = cita(vista.getId());
        exigir(cita.estaProgramada(), "Solo se pueden cancelar citas programadas.");
        if (!vista.confirmar("¿Cancelar la cita de " + cita.getMascota().getNombre() + "?")) {
            return;
        }
        cita.cancelar();
        actualizar();
        verCita();
        vista.mostrarInformacion("Cita cancelada; el registro se conserva.");
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

    private Recepcionista recepcionista(String documento) {
        Recepcionista r = clinica.buscarRecepcionista(documento);
        exigir(r != null, "No se encontró la recepcionista. Regístrela en Personal.");
        return r;
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
            ArrayList<String> idsRecepcionista = new ArrayList<>();
            ArrayList<String> nombresRecepcionista = new ArrayList<>();
            for (Recepcionista registro : clinica.getRecepcionistas()) {
                idsRecepcionista.add(registro.getDocumento());
                nombresRecepcionista.add(registro.getNombre() + " (" + registro.getDocumento() + ")");
            }
            vista.cargarRecepcionista(idsRecepcionista, nombresRecepcionista);
            cargarCitas();
            if (encontrarCita(vista.getId()) != null) {
                verCita();
            } else {
                vista.habilitarBotonAgendar(!clinica.getMascotas().isEmpty() && !clinica.getRecepcionistas().isEmpty());
            }
        } finally {
            refrescando = anterior;
        }
    }

    private void cargarCitas() {
        ArrayList<String[]> filas = new ArrayList<>();
        for (Cita cita : clinica.getCitas()) {
            filas.add(new String[]{cita.getIdCita(), cita.getFechaHora().format(hora),
                    cita.getMascota().getNombre(), cita.getEstado()});
        }
        vista.mostrarRegistros(filas);
    }

    private void nuevo() {
        vista.habilitarMascota(true);
        vista.habilitarRecepcionista(true);
        vista.habilitarFecha(true);
        vista.habilitarBotonAhora(true);
        vista.habilitarMotivo(true);
        vista.setId(id("A", clinica.getCitas().size()));
        vista.setFecha(LocalDateTime.now().format(hora));
        vista.setMotivo("");
        vista.habilitarBotonAgendar(!clinica.getMascotas().isEmpty() && !clinica.getRecepcionistas().isEmpty());
        vista.habilitarBotonCancelar(false);
        vista.mostrarResultado("Primero registre mascota y recepcionista. La fecha puede modificarse antes de agendar.");
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
            verCita();
        } catch (IllegalArgumentException | IllegalStateException error) {
            vista.mostrarError(error.getMessage());
        } finally {
            refrescando = false;
        }
    }
}
