package Modelo;

public abstract class Colaborador extends Persona {
    private final String rol;

    protected Colaborador(String documento, String nombre, String telefono, String rol) {
        super(documento, nombre, telefono);
        this.rol = rol;
    }

    public String obtenerRol() {
        return rol;
    }

    @Override
    public String obtenerDatos() {
        return super.obtenerDatos() + "\nRol: " + obtenerRol();
    }
}
