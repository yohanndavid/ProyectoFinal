package Modelo;

public final class MascotaExotica extends Mascota {
    private final String permisoAmbiental;
    private final boolean requiereSedacion;

    MascotaExotica(String idMascota, String nombre, String especie, int edad,
            double peso, String permisoAmbiental, boolean requiereSedacion, Propietario propietario) {
        super(idMascota, nombre, especie, edad, peso, propietario);
        this.permisoAmbiental = permisoAmbiental;
        this.requiereSedacion = requiereSedacion;
    }

    public String getPermisoAmbiental() {
        return permisoAmbiental;
    }

    public boolean isRequiereSedacion() {
        return requiereSedacion;
    }

    @Override
    public double calcularRecargo(double tarifaBase, ConsultaMedica consulta) {
        return tarifaBase * 0.25;
    }
}
