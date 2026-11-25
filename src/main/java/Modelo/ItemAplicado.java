package Modelo;

public abstract class ItemAplicado {
    private final String codigo;
    private final String nombre;
    private final int cantidad;
    private final double valorUnitario;
    private ConsultaMedica consulta;

    protected ItemAplicado(String codigo, String nombre, int cantidad, double valorUnitario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public ConsultaMedica getConsulta() {
        return consulta;
    }
    void asignarConsulta(ConsultaMedica consulta) {
        this.consulta = consulta;
    }

    public double calcularTotal() {
        return cantidad * valorUnitario;
    }
}
