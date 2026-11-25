package Controlador;

import Modelo.*;
import Vista.VentanaMascotas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.event.ListSelectionListener;

public class ControladorMascotas implements ActionListener, ListSelectionListener {
    private final Clinica clinica;
    private final VentanaMascotas vista;
    private boolean refrescando;

    public ControladorMascotas(Clinica clinica, VentanaMascotas vista) {
        this.clinica = clinica;
        this.vista = vista;
        vista.alRegistrar(this);
        vista.alBuscar(this);
        vista.alPeso(this);
        vista.alVacunas(this);
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
                    registrarMascota();
                    break;
                case "buscar":
                    verMascota();
                    break;
                case "peso":
                    actualizarPeso();
                    break;
                case "vacunas":
                    actualizarVacunas();
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

    private void registrarMascota() {
        Propietario propietario = propietario(obligatorio(vista.getPropietario(), "el propietario"));
        String id = obligatorio(vista.getId(), "el identificador");
        exigir(encontrarMascota(id) == null, "La mascota ya está registrada; pulse Nuevo para registrar otra.");
        String nombre = obligatorio(vista.getNombre(), "el nombre de la mascota");
        String especie = obligatorio(vista.getEspecie(), "la especie");
        int edad = entero(vista.getEdad(), "La edad", 0);
        double peso = numero(vista.getPeso(), "El peso en kg", false);
        if (vista.getTipo().equals("Convencional")) {
            String raza = vista.getRaza().isBlank() ? "No informada" : vista.getRaza();
            String esquema = vista.getEsquema().isBlank() ? "Sin información registrada" : vista.getEsquema();
            propietario.crearMascotaConvencional(id, nombre, especie, edad, peso, raza, esquema);
        } else {
            String permiso = obligatorio(vista.getPermiso(), "el permiso ICA/CITES");
            propietario.crearMascotaExotica(id, nombre, especie, edad, peso, permiso, vista.getSedacion().equals("Sí"));
        }
        actualizar();
        verMascota();
        vista.mostrarInformacion("Mascota " + nombre + " registrada con su propietario e historial.");
    }

    private void verMascota() {
        Mascota mascota = mascota(vista.getId());
        vista.setPropietario(mascota.getPropietario().getDocumento());
        vista.setTipo(mascota instanceof MascotaConvencional ? "Convencional" : "Exótica");
        vista.setNombre(mascota.getNombre());
        vista.setEspecie(mascota.getEspecie());
        vista.setEdad(String.valueOf(mascota.getEdad()));
        vista.setPeso(String.valueOf(mascota.getPeso()));
        if (mascota instanceof MascotaConvencional) {
            MascotaConvencional convencional = (MascotaConvencional) mascota;
            vista.setRaza(convencional.getRaza());
            vista.setEsquema(convencional.getEsquemaVacunas());
        } else if (mascota instanceof MascotaExotica) {
            MascotaExotica exotica = (MascotaExotica) mascota;
            vista.setPermiso(exotica.getPermisoAmbiental());
            vista.setSedacion(exotica.isRequiereSedacion() ? "Sí" : "No");
        }
        vista.habilitarPropietario(false);
        vista.habilitarTipo(false);
        vista.habilitarNombre(false);
        vista.habilitarEspecie(false);
        vista.habilitarEdad(false);
        vista.habilitarRaza(false);
        vista.habilitarPermiso(false);
        vista.habilitarSedacion(false);
        vista.habilitarPeso(true);
        vista.habilitarEsquema(mascota instanceof MascotaConvencional);
        vista.habilitarBotonRegistrar(false);
        vista.habilitarBotonPeso(true);
        vista.habilitarBotonVacunas(mascota instanceof MascotaConvencional);
        vista.mostrarResultado(datosMascota(mascota)
                + "\n\nPuede actualizar el peso y, en convencionales, el esquema de vacunas.");
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

    private void actualizarPeso() {
        Mascota mascota = mascota(vista.getId());
        mascota.actualizarPeso(numero(vista.getPeso(), "El peso", false));
        actualizar();
        verMascota();
        vista.mostrarInformacion("Peso actualizado.");
    }

    private void actualizarVacunas() {
        Mascota mascota = mascota(vista.getId());
        exigir(mascota instanceof MascotaConvencional, "El esquema corresponde a una mascota convencional.");
        ((MascotaConvencional) mascota).actualizarEsquemaVacunas(obligatorio(vista.getEsquema(), "el esquema"));
        actualizar();
        verMascota();
        vista.mostrarInformacion("Esquema de vacunas actualizado.");
    }

    private Propietario propietario(String documento) {
        Propietario p = clinica.buscarPropietario(documento);
        exigir(p != null, "No se encontró el propietario. Regístrelo primero.");
        return p;
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
            ArrayList<String> idsPropietario = new ArrayList<>();
            ArrayList<String> nombresPropietario = new ArrayList<>();
            for (Propietario registro : clinica.getPropietarios()) {
                idsPropietario.add(registro.getDocumento());
                nombresPropietario.add(registro.getNombre() + " (" + registro.getDocumento() + ")");
            }
            vista.cargarPropietario(idsPropietario, nombresPropietario);
            cargarMascotas();
            if (encontrarMascota(vista.getId()) != null) {
                verMascota();
            } else {
                vista.habilitarBotonRegistrar(!clinica.getPropietarios().isEmpty());
            }
        } finally {
            refrescando = anterior;
        }
    }

    private void cargarMascotas() {
        ArrayList<String[]> filas = new ArrayList<>();
        for (Mascota mascota : clinica.getMascotas()) {
            filas.add(new String[]{mascota.getIdMascota(), mascota.getNombre(), mascota.getEspecie(),
                    mascota.getPropietario().getNombre(), String.valueOf(mascota.getPeso())});
        }
        vista.mostrarRegistros(filas);
    }

    private void nuevo() {
        vista.habilitarPropietario(true);
        vista.habilitarTipo(true);
        vista.habilitarNombre(true);
        vista.habilitarEspecie(true);
        vista.habilitarEdad(true);
        vista.habilitarPeso(true);
        vista.habilitarRaza(true);
        vista.habilitarEsquema(true);
        vista.habilitarPermiso(true);
        vista.habilitarSedacion(true);
        vista.setNombre("");
        vista.setEspecie("");
        vista.setEdad("");
        vista.setPeso("");
        vista.setRaza("");
        vista.setEsquema("");
        vista.setPermiso("");
        vista.setId(id("M", clinica.getMascotas().size()));
        vista.setSedacion("No");
        vista.habilitarBotonRegistrar(!clinica.getPropietarios().isEmpty());
        vista.habilitarBotonPeso(false);
        vista.habilitarBotonVacunas(false);
        ajustarTipo();
        vista.mostrarResultado("Seleccione propietario. Especie libre; edad en años (admite 0), peso en kg. Exóticas: permiso obligatorio.");
        vista.mostrarInformacion("Formulario nuevo. Los registros guardados se conservan.");
    }

    private void ajustarTipo() {
        boolean convencional = vista.getTipo().equals("Convencional");
        vista.habilitarRaza(convencional);
        vista.habilitarEsquema(convencional);
        vista.habilitarPermiso(!convencional);
        vista.habilitarSedacion(!convencional);
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
            verMascota();
        } catch (IllegalArgumentException | IllegalStateException error) {
            vista.mostrarError(error.getMessage());
        } finally {
            refrescando = false;
        }
    }
}
