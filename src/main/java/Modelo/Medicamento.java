package Modelo;

public class Medicamento extends ItemAplicado {
    private final String dosis;
    private final String viaAdministracion;

    public Medicamento(String codigo, String nombre, int cantidad, double valorUnitario,
            String dosis, String viaAdministracion) {
        super(codigo, nombre, cantidad, valorUnitario);
        this.dosis = dosis;
        this.viaAdministracion = viaAdministracion;
    }

    public String getDosis() {
        return dosis;
    }

    public String getViaAdministracion() {
        return viaAdministracion;
    }
}
