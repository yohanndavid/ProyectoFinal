package Modelo;

public final class MascotaConvencional extends Mascota {
    private final String raza;
    private String esquemaVacunas;

    MascotaConvencional(String idMascota, String nombre, String especie, int edad,
            double peso, String raza, String esquemaVacunas, Propietario propietario) {
        super(idMascota, nombre, especie, edad, peso, propietario);
        this.raza = raza;
        this.esquemaVacunas = esquemaVacunas;
    }

    public String getRaza() {
        return raza;
    }

    public String getEsquemaVacunas() {
        return esquemaVacunas;
    }

    public void actualizarEsquemaVacunas(String esquema) {
        esquemaVacunas = esquema;
    }

    @Override
    public double calcularRecargo(double tarifaBase, ConsultaMedica consulta) {
        return 0;
    }
}
