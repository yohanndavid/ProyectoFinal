package Controlador;

import Modelo.*;
import Vista.VentanaPropietarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;

public class ControladorPropietarios implements ActionListener, ListSelectionListener {
    private final Clinica clinica;
    private final VentanaPropietarios vista;
    private boolean refrescando;

    public ControladorPropietarios(Clinica clinica, VentanaPropietarios vista) {
        this.clinica = clinica;
        this.vista = vista;
        vista.alRegistrar(this);
        vista.alBuscar(this);
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
                case "registrar":
                    registrarPropietario();
                    break;
                case "buscar":
                    verPropietario();
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

    private void registrarPropietario() {
        String documento = obligatorio(vista.getDocumento(), "el documento del propietario");
        String nombre = obligatorio(vista.getNombre(), "el nombre del propietario");
        String telefono = obligatorio(vista.getTelefono(), "el teléfono");
        String correo = obligatorio(vista.getCorreo(), "el correo");
        String direccion = obligatorio(vista.getDireccion(), "la dirección");
        if (!correo.matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+")) {
            error("Escriba un correo como nombre@dominio.com.");
        }
        exigir(clinica.buscarPropietario(documento) == null, "Ya existe un propietario con ese documento.");
        clinica.registrarPropietario(new Propietario(documento, nombre, telefono, correo, direccion));
        actualizar();
        verPropietario();
        vista.mostrarInformacion("Propietario registrado. Ya puede registrar sus mascotas.");
    }

    private void verPropietario() {
        Propietario propietario = propietario(obligatorio(vista.getDocumento(), "el documento"));
        vista.setNombre(propietario.getNombre());
        vista.setTelefono(propietario.getTelefono());
        vista.setCorreo(propietario.getCorreo());
        vista.setDireccion(propietario.getDireccion());
        vista.mostrarResultado(propietario.obtenerDatos()
                + "\nMascotas: " + propietario.obtenerCantidadMascotas()
                + "\nBeneficio 10%: " + (propietario.tieneBeneficio() ? "Sí" : "No")
                + "\n\nSeleccione Nuevo para registrar otra persona.");
        vista.habilitarDocumento(false);
        vista.habilitarNombre(false);
        vista.habilitarTelefono(false);
        vista.habilitarCorreo(false);
        vista.habilitarDireccion(false);
        vista.habilitarBotonRegistrar(false);
    }

    private Propietario propietario(String documento) {
        Propietario p = clinica.buscarPropietario(documento);
        exigir(p != null, "No se encontró el propietario. Regístrelo primero.");
        return p;
    }

    private void error(String mensaje) {
        throw new IllegalArgumentException(mensaje);
    }

    public void actualizar() {
        boolean anterior = refrescando;
        refrescando = true;
        try {
            cargarPropietarios();
            if (clinica.buscarPropietario(vista.getDocumento()) != null) {
                verPropietario();
            }
        } finally {
            refrescando = anterior;
        }
    }

    private void cargarPropietarios() {
        ArrayList<String[]> filas = new ArrayList<>();
        for (Propietario propietario : clinica.getPropietarios()) {
            String beneficio = "No";
            if (propietario.tieneBeneficio()) {
                beneficio = "10%";
            }
            filas.add(new String[]{propietario.getDocumento(), propietario.getNombre(), propietario.getTelefono(),
                    String.valueOf(propietario.obtenerCantidadMascotas()), beneficio});
        }
        vista.mostrarRegistros(filas);
    }

    private void nuevo() {
        vista.habilitarDocumento(true);
        vista.habilitarNombre(true);
        vista.habilitarTelefono(true);
        vista.habilitarCorreo(true);
        vista.habilitarDireccion(true);
        vista.setDocumento("");
        vista.setNombre("");
        vista.setTelefono("");
        vista.setCorreo("");
        vista.setDireccion("");
        vista.habilitarBotonRegistrar(true);
        vista.mostrarResultado("Registre al dueño; seleccione una fila para consultar. Nuevo no elimina datos.");
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
            vista.setDocumento(id);
            verPropietario();
        } catch (IllegalArgumentException | IllegalStateException error) {
            vista.mostrarError(error.getMessage());
        } finally {
            refrescando = false;
        }
    }
}
