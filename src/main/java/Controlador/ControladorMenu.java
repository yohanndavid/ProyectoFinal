package Controlador;

import Modelo.Clinica;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ControladorMenu implements ActionListener {
    private final VentanaMenu vista;
    private Clinica clinica;
    private VentanaPropietarios ventanaPropietarios;
    private ControladorPropietarios controladorPropietarios;
    private VentanaPersonal ventanaPersonal;
    private ControladorPersonal controladorPersonal;
    private VentanaMascotas ventanaMascotas;
    private ControladorMascotas controladorMascotas;
    private VentanaCitas ventanaCitas;
    private ControladorCitas controladorCitas;
    private VentanaConsultas ventanaConsultas;
    private ControladorConsultas controladorConsultas;
    private VentanaInsumos ventanaInsumos;
    private ControladorInsumos controladorInsumos;
    private VentanaHistorial ventanaHistorial;
    private ControladorHistorial controladorHistorial;
    private VentanaCobros ventanaCobros;
    private ControladorCobros controladorCobros;

    public ControladorMenu(VentanaMenu vista) {
        this.vista = vista;
        vista.alIniciar(this);
        vista.alPropietarios(this);
        vista.alPersonal(this);
        vista.alMascotas(this);
        vista.alCitas(this);
        vista.alConsultas(this);
        vista.alInsumos(this);
        vista.alHistorial(this);
        vista.alCobros(this);
        vista.alSalir(this);
        habilitarModulos(false);
        vista.mostrarResultado("ORDEN DE TRABAJO\n\n1. Iniciar jornada y registrar propietarios y personal."
                + "\n2. Registrar mascotas.\n3. Agendar cita o atender directamente."
                + "\n4. Registrar atención, diagnóstico, vacunas y medicamentos."
                + "\n5. Revisar cobro, emitirlo, recibir pago y ver el comprobante."
                + "\n\nCerrar una ventana secundaria no borra sus registros."
                + "\nLos datos se pierden al cerrar el programa completo.");
        vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evento) {
                salir();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        try {
            String accion = evento.getActionCommand();
            if (accion.equals("iniciar")) {
                iniciarJornada();
                return;
            }
            if (accion.equals("salir")) {
                salir();
                return;
            }
            if (clinica == null) {
                throw new IllegalArgumentException("Primero inicie la jornada.");
            }
            switch (accion) {
                case "Propietarios":
                    abrirPropietarios();
                    break;
                case "Personal":
                    abrirPersonal();
                    break;
                case "Mascotas":
                    abrirMascotas();
                    break;
                case "Citas":
                    abrirCitas();
                    break;
                case "Consultas":
                    abrirConsultas();
                    break;
                case "Insumos":
                    abrirInsumos();
                    break;
                case "Historial":
                    abrirHistorial();
                    break;
                case "Cobros":
                    abrirCobros();
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException | IllegalStateException error) {
            vista.mostrarError(error.getMessage());
        }
    }

    private void iniciarJornada() {
        if (clinica != null) {
            throw new IllegalStateException("La jornada ya está iniciada.");
        }
        String nombre = vista.getClinica();
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("Escriba el nombre de la clínica.");
        }
        double tarifa = leerImporte(vista.getTarifa(), "La tarifa base", false);
        double sedacion = leerImporte(vista.getSedacion(), "El costo de sedación", true);
        if (!Double.isFinite(tarifa * 1.25 + sedacion)) {
            throw new IllegalArgumentException("Las tarifas exceden el límite numérico.");
        }
        clinica = new Clinica(nombre, tarifa, sedacion);
        vista.habilitarClinica(false);
        vista.habilitarTarifa(false);
        vista.habilitarSedacion(false);
        vista.habilitarBotonIniciar(false);
        habilitarModulos(true);
        vista.mostrarInformacion("Jornada iniciada. Registre primero propietarios y personal.");
    }

    private double leerImporte(String texto, String nombre, boolean permiteCero) {
        if (!texto.matches("[0-9]+([.,][0-9]+)?")) {
            throw new IllegalArgumentException(nombre + ": use números sin separadores de miles.");
        }
        double valor = Double.parseDouble(texto.replace(',', '.'));
        if (!Double.isFinite(valor) || (!permiteCero && valor == 0)) {
            throw new IllegalArgumentException(nombre + " debe ser un importe válido.");
        }
        if (BigDecimal.valueOf(valor).stripTrailingZeros().scale() > 2) {
            throw new IllegalArgumentException(nombre + " admite hasta dos cifras decimales.");
        }
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private void habilitarModulos(boolean habilitado) {
        vista.habilitarBotonPropietarios(habilitado);
        vista.habilitarBotonPersonal(habilitado);
        vista.habilitarBotonMascotas(habilitado);
        vista.habilitarBotonCitas(habilitado);
        vista.habilitarBotonConsultas(habilitado);
        vista.habilitarBotonInsumos(habilitado);
        vista.habilitarBotonHistorial(habilitado);
        vista.habilitarBotonCobros(habilitado);
    }

    private void abrirPropietarios() {
        if (ventanaPropietarios == null) {
            ventanaPropietarios = new VentanaPropietarios();
            controladorPropietarios = new ControladorPropietarios(clinica, ventanaPropietarios);
        }
        controladorPropietarios.actualizar();
        ventanaPropietarios.setLocationRelativeTo(vista);
        ventanaPropietarios.setVisible(true);
        ventanaPropietarios.toFront();
    }

    private void abrirPersonal() {
        if (ventanaPersonal == null) {
            ventanaPersonal = new VentanaPersonal();
            controladorPersonal = new ControladorPersonal(clinica, ventanaPersonal);
        }
        controladorPersonal.actualizar();
        ventanaPersonal.setLocationRelativeTo(vista);
        ventanaPersonal.setVisible(true);
        ventanaPersonal.toFront();
    }

    private void abrirMascotas() {
        if (ventanaMascotas == null) {
            ventanaMascotas = new VentanaMascotas();
            controladorMascotas = new ControladorMascotas(clinica, ventanaMascotas);
        }
        controladorMascotas.actualizar();
        ventanaMascotas.setLocationRelativeTo(vista);
        ventanaMascotas.setVisible(true);
        ventanaMascotas.toFront();
    }

    private void abrirCitas() {
        if (ventanaCitas == null) {
            ventanaCitas = new VentanaCitas();
            controladorCitas = new ControladorCitas(clinica, ventanaCitas);
        }
        controladorCitas.actualizar();
        ventanaCitas.setLocationRelativeTo(vista);
        ventanaCitas.setVisible(true);
        ventanaCitas.toFront();
    }

    private void abrirConsultas() {
        if (ventanaConsultas == null) {
            ventanaConsultas = new VentanaConsultas();
            controladorConsultas = new ControladorConsultas(clinica, ventanaConsultas);
        }
        controladorConsultas.actualizar();
        ventanaConsultas.setLocationRelativeTo(vista);
        ventanaConsultas.setVisible(true);
        ventanaConsultas.toFront();
    }

    private void abrirInsumos() {
        if (ventanaInsumos == null) {
            ventanaInsumos = new VentanaInsumos();
            controladorInsumos = new ControladorInsumos(clinica, ventanaInsumos);
        }
        controladorInsumos.actualizar();
        ventanaInsumos.setLocationRelativeTo(vista);
        ventanaInsumos.setVisible(true);
        ventanaInsumos.toFront();
    }

    private void abrirHistorial() {
        if (ventanaHistorial == null) {
            ventanaHistorial = new VentanaHistorial();
            controladorHistorial = new ControladorHistorial(clinica, ventanaHistorial);
        }
        controladorHistorial.actualizar();
        ventanaHistorial.setLocationRelativeTo(vista);
        ventanaHistorial.setVisible(true);
        ventanaHistorial.toFront();
    }

    private void abrirCobros() {
        if (ventanaCobros == null) {
            ventanaCobros = new VentanaCobros();
            controladorCobros = new ControladorCobros(clinica, ventanaCobros);
        }
        controladorCobros.actualizar();
        ventanaCobros.setLocationRelativeTo(vista);
        ventanaCobros.setVisible(true);
        ventanaCobros.toFront();
    }

    private void salir() {
        if (!vista.confirmar("¿Cerrar el programa? Los datos de la jornada se perderán.")) {
            return;
        }
        if (ventanaPropietarios != null) {
            ventanaPropietarios.dispose();
        }
        if (ventanaPersonal != null) {
            ventanaPersonal.dispose();
        }
        if (ventanaMascotas != null) {
            ventanaMascotas.dispose();
        }
        if (ventanaCitas != null) {
            ventanaCitas.dispose();
        }
        if (ventanaConsultas != null) {
            ventanaConsultas.dispose();
        }
        if (ventanaInsumos != null) {
            ventanaInsumos.dispose();
        }
        if (ventanaHistorial != null) {
            ventanaHistorial.dispose();
        }
        if (ventanaCobros != null) {
            ventanaCobros.dispose();
        }
        vista.dispose();
    }
}
