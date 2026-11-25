package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Propietario extends Persona {
    private final String correo;
    private final String direccion;
    private final List<Mascota> mascotas = new ArrayList<>();

    public Propietario(String documento, String nombre, String telefono, String correo, String direccion) {
        super(documento, nombre, telefono);
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Mascota> getMascotas() {
        return List.copyOf(mascotas);
    }

    public MascotaConvencional crearMascotaConvencional(String idMascota, String nombre,
            String especie, int edad, double peso, String raza, String esquemaVacunas) {
        MascotaConvencional mascota = new MascotaConvencional(idMascota, nombre, especie,
                edad, peso, raza, esquemaVacunas, this);
        mascotas.add(mascota);
        return mascota;
    }

    public MascotaExotica crearMascotaExotica(String idMascota, String nombre,
            String especie, int edad, double peso, String permisoAmbiental, boolean requiereSedacion) {
        MascotaExotica mascota = new MascotaExotica(idMascota, nombre, especie,
                edad, peso, permisoAmbiental, requiereSedacion, this);
        mascotas.add(mascota);
        return mascota;
    }

    public int obtenerCantidadMascotas() {
        return mascotas.size();
    }

    public Mascota buscarMascota(String id) {
        for (Mascota mascota : mascotas) {
            if (mascota.getIdMascota().equals(id)) {
                return mascota;
            }
        }
        return null;
    }

    public boolean tieneBeneficio() {
        return obtenerCantidadMascotas() > 2;
    }

    @Override
    public String obtenerDatos() {
        return super.obtenerDatos() + "\nCorreo: " + correo + "\nDirección: " + direccion;
    }
}
