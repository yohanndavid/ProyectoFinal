package Modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Mascota {
    private final String idMascota;
    private final String nombre;
    private final String especie;
    private final int edad;
    private double peso;
    private final Propietario propietario;
    private final HistorialClinico historial;
    private final List<Cita> citas = new ArrayList<>();

    Mascota(String idMascota, String nombre, String especie, int edad,
            double peso, Propietario propietario) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.peso = peso;
        this.propietario = propietario;
        this.historial = crearHistorialClinico();
    }

    public String getIdMascota() {
        return idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public List<Cita> getCitas() {
        return List.copyOf(citas);
    }

    void agregarCita(Cita cita) {
        citas.add(cita);
    }

    public void actualizarPeso(double peso) {
        this.peso = peso;
    }

    private HistorialClinico crearHistorialClinico() {
        return new HistorialClinico("HC-" + idMascota, this);
    }

    public HistorialClinico obtenerHistorial() {
        return historial;
    }

    public abstract double calcularRecargo(double tarifaBase, ConsultaMedica consulta);
}
