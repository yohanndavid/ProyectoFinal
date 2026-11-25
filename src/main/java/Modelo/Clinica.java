package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Clinica {
    private final String nombre;
    private final double tarifaBase;
    private final double costoSedacion;
    private final List<Propietario> propietarios = new ArrayList<>();
    private final List<Recepcionista> recepcionistas = new ArrayList<>();
    private final List<Veterinario> veterinarios = new ArrayList<>();

    public Clinica(String nombre, double tarifaBase, double costoSedacion) {
        this.nombre = nombre;
        this.tarifaBase = tarifaBase;
        this.costoSedacion = costoSedacion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public double getCostoSedacion() {
        return costoSedacion;
    }

    public List<Propietario> getPropietarios() {
        return List.copyOf(propietarios);
    }

    public List<Recepcionista> getRecepcionistas() {
        return List.copyOf(recepcionistas);
    }

    public List<Veterinario> getVeterinarios() {
        return List.copyOf(veterinarios);
    }

    public void registrarPropietario(Propietario propietario) {
        propietarios.add(propietario);
    }

    public void registrarRecepcionista(Recepcionista recepcionista) {
        recepcionistas.add(recepcionista);
    }

    public void registrarVeterinario(Veterinario veterinario) {
        veterinarios.add(veterinario);
    }

    public Propietario buscarPropietario(String documento) {
        for (Propietario propietario : propietarios) {
            if (propietario.getDocumento().equals(documento)) {
                return propietario;
            }
        }
        return null;
    }

    public Recepcionista buscarRecepcionista(String documento) {
        for (Recepcionista recepcionista : recepcionistas) {
            if (recepcionista.getDocumento().equals(documento)) {
                return recepcionista;
            }
        }
        return null;
    }

    public Veterinario buscarVeterinario(String documento) {
        for (Veterinario veterinario : veterinarios) {
            if (veterinario.getDocumento().equals(documento)) {
                return veterinario;
            }
        }
        return null;
    }

    public List<Mascota> getMascotas() {
        List<Mascota> resultado = new ArrayList<>();
        for (Propietario propietario : propietarios) {
            resultado.addAll(propietario.getMascotas());
        }
        return List.copyOf(resultado);
    }

    public List<Cita> getCitas() {
        List<Cita> resultado = new ArrayList<>();
        for (Mascota mascota : getMascotas()) {
            resultado.addAll(mascota.getCitas());
        }
        return List.copyOf(resultado);
    }

    public List<ConsultaMedica> getConsultas() {
        List<ConsultaMedica> resultado = new ArrayList<>();
        for (Mascota mascota : getMascotas()) {
            resultado.addAll(mascota.obtenerHistorial().obtenerConsultas());
        }
        return List.copyOf(resultado);
    }

    public List<Cobro> getCobros() {
        List<Cobro> resultado = new ArrayList<>();
        for (ConsultaMedica consulta : getConsultas()) {
            if (consulta.getCobro() != null) {
                resultado.add(consulta.getCobro());
            }
        }
        return List.copyOf(resultado);
    }
}
