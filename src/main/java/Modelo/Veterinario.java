package Modelo;

public class Veterinario extends Colaborador {
    private final String especialidad;
    private final String tarjetaProfesional;

    public Veterinario(String documento, String nombre, String telefono,
            String especialidad, String tarjetaProfesional) {
        super(documento, nombre, telefono, "Veterinario");
        this.especialidad = especialidad;
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public ConsultaMedica registrarConsulta(String id, java.time.LocalDateTime fecha,
            Mascota mascota, Cita cita) {
        if (cita != null && (!cita.estaProgramada() || cita.getMascota() != mascota)) {
            throw new IllegalStateException("La cita no está disponible para esta mascota.");
        }
        ConsultaMedica consulta = mascota.obtenerHistorial().crearConsulta(id, fecha, this);
        if (cita != null) {
            cita.asociarConsulta(consulta);
        }
        return consulta;
    }

    public HistorialClinico consultarHistorial(Mascota mascota) {
        return mascota.obtenerHistorial();
    }

    @Override
    public String obtenerDatos() {
        return super.obtenerDatos() + "\nEspecialidad: " + especialidad
                + "\nTarjeta profesional: " + tarjetaProfesional;
    }
}
