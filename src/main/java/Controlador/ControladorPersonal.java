package Controlador;

import Modelo.*;
import Vista.VentanaPersonal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;

public class ControladorPersonal implements ActionListener, ListSelectionListener {
    private final Clinica clinica;
    private final VentanaPersonal vista;
    private boolean refrescando;

    public ControladorPersonal(Clinica clinica, VentanaPersonal vista) {
        this.clinica = clinica;
        this.vista = vista;
        vista.alRegistrar(this);
        vista.alBuscar(this);
        vista.alListar(this);
        vista.alNuevo(this);
        vista.alVolver(this);
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
                case "registrar":
                    registrarPersonal();
                    break;
                case "buscar":
                    verPersonal();
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

    private void registrarPersonal() {
        String documento = obligatorio(vista.getDocumento(), "el documento del colaborador");
        String nombre = obligatorio(vista.getNombre(), "el nombre");
        String telefono = obligatorio(vista.getTelefono(), "el teléfono");
        if (vista.getTipo().equals("Veterinario")) {
            String especialidad = obligatorio(vista.getEspecialidad(), "la especialidad");
            String tarjeta = obligatorio(vista.getTarjeta(), "la tarjeta profesional");
            exigir(clinica.buscarVeterinario(documento) == null, "Ya existe ese veterinario.");
            for (Veterinario registrado : clinica.getVeterinarios()) {
                exigir(!registrado.getTarjetaProfesional().equalsIgnoreCase(tarjeta),
                        "La tarjeta profesional ya está registrada.");
            }
            clinica.registrarVeterinario(new Veterinario(documento, nombre, telefono, especialidad, tarjeta));
        } else {
            exigir(clinica.buscarRecepcionista(documento) == null, "Ya existe esa recepcionista.");
            clinica.registrarRecepcionista(new Recepcionista(documento, nombre, telefono));
        }
        actualizar();
        verPersonal();
        vista.mostrarInformacion("Colaborador registrado.");
    }

    private void verPersonal() {
        String documento = obligatorio(vista.getDocumento(), "el documento");
        Persona persona;
        if (vista.getTipo().equals("Veterinario")) {
            Veterinario veterinario = veterinario(documento);
            vista.setEspecialidad(veterinario.getEspecialidad());
            vista.setTarjeta(veterinario.getTarjetaProfesional());
            persona = veterinario;
        } else {
            persona = recepcionista(documento);
        }
        vista.setNombre(persona.getNombre());
        vista.setTelefono(persona.getTelefono());
        vista.mostrarResultado(persona.obtenerDatos());
        vista.habilitarDocumento(false);
        vista.habilitarNombre(false);
        vista.habilitarTelefono(false);
        vista.habilitarEspecialidad(false);
        vista.habilitarTarjeta(false);
        vista.habilitarTipo(false);
        vista.habilitarBotonRegistrar(false);
    }

    private Veterinario veterinario(String documento) {
        Veterinario v = clinica.buscarVeterinario(documento);
        exigir(v != null, "No se encontró el veterinario. Regístrelo en Personal.");
        return v;
    }

    private Recepcionista recepcionista(String documento) {
        Recepcionista r = clinica.buscarRecepcionista(documento);
        exigir(r != null, "No se encontró la recepcionista. Regístrela en Personal.");
        return r;
    }

    private void error(String mensaje) {
        throw new IllegalArgumentException(mensaje);
    }

    public void actualizar() {
        boolean anterior = refrescando;
        refrescando = true;
        try {
            cargarPersonal();
            if ((vista.getTipo().equals("Veterinario") && clinica.buscarVeterinario(vista.getDocumento()) != null)
                    || (vista.getTipo().equals("Recepcionista") && clinica.buscarRecepcionista(vista.getDocumento()) != null)) {
                verPersonal();
            }
        } finally {
            refrescando = anterior;
        }
    }

    private void cargarPersonal() {
        ArrayList<String[]> filas = new ArrayList<>();
        for (Recepcionista recepcionista : clinica.getRecepcionistas()) {
            filas.add(new String[]{"R:" + recepcionista.getDocumento(), recepcionista.getNombre(),
                    "Recepcionista", recepcionista.getTelefono()});
        }
        for (Veterinario veterinario : clinica.getVeterinarios()) {
            filas.add(new String[]{"V:" + veterinario.getDocumento(), veterinario.getNombre(),
                    "Veterinario", veterinario.getTelefono()});
        }
        vista.mostrarRegistros(filas);
    }

    private void nuevo() {
        vista.habilitarTipo(true);
        vista.habilitarDocumento(true);
        vista.habilitarNombre(true);
        vista.habilitarTelefono(true);
        vista.habilitarEspecialidad(true);
        vista.habilitarTarjeta(true);
        vista.setDocumento("");
        vista.setNombre("");
        vista.setTelefono("");
        vista.setEspecialidad("");
        vista.setTarjeta("");
        vista.habilitarBotonRegistrar(true);
        ajustarTipo();
        vista.mostrarResultado("Registre recepcionistas y veterinarios. La especialidad y tarjeta corresponden al veterinario.");
        vista.mostrarInformacion("Formulario nuevo. Los registros guardados se conservan.");
    }

    private void ajustarTipo() {
        boolean veterinario = vista.getTipo().equals("Veterinario");
        vista.habilitarEspecialidad(veterinario);
        vista.habilitarTarjeta(veterinario);
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
            vista.setTipo(id.startsWith("V:") ? "Veterinario" : "Recepcionista");
            vista.setDocumento(id.substring(2));
            verPersonal();
        } catch (IllegalArgumentException | IllegalStateException error) {
            vista.mostrarError(error.getMessage());
        } finally {
            refrescando = false;
        }
    }
}
