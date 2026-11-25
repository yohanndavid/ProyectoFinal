package Controlador;

import Modelo.*;
import Vista.VentanaHistorial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ControladorHistorial implements ActionListener {
    private final Clinica clinica;
    private final VentanaHistorial vista;
    private boolean refrescando;

    public ControladorHistorial(Clinica clinica, VentanaHistorial vista) {
        this.clinica = clinica;
        this.vista = vista;
        vista.alConsultar(this);
        vista.alListar(this);
        vista.alVolver(this);
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
                case "consultar":
                    verHistorial();
                    break;
                case "listar":
                    actualizar();
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

    private void verHistorial() {
        Mascota mascota = mascota(obligatorio(vista.getMascota(), "la mascota"));
        vista.mostrarResultado(datosMascota(mascota) + "\n\n" + mascota.obtenerHistorial().generarResumen());
    }

    private String datosMascota(Mascota mascota) {
        String texto = "Paciente: " + mascota.getNombre() + " (" + mascota.getIdMascota() + ")"
                + "\nEspecie: " + mascota.getEspecie() + "\nEdad: " + mascota.getEdad()
                + "\nPeso: " + mascota.getPeso() + " kg"
                + "\nPropietario: " + mascota.getPropietario().getNombre();
        if (mascota instanceof MascotaConvencional) {
            MascotaConvencional convencional = (MascotaConvencional) mascota;
            return texto + "\nRaza: " + convencional.getRaza() + "\nVacunas: " + convencional.getEsquemaVacunas();
        }
        MascotaExotica exotica = (MascotaExotica) mascota;
        return texto + "\nPermiso: " + exotica.getPermisoAmbiental()
                + "\nRequiere sedación: " + (exotica.isRequiereSedacion() ? "Sí" : "No");
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
        } finally {
            refrescando = anterior;
        }
    }

    private void nuevo() {
        vista.mostrarResultado("");
    }
}
