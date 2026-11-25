package Modelo;

public abstract class Persona {
    private final String documento;
    private final String nombre;
    private final String telefono;

    protected Persona(String documento, String nombre, String telefono) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String obtenerDatos() {
        return "Documento: " + documento + "\nNombre: " + nombre + "\nTeléfono: " + telefono;
    }
}
