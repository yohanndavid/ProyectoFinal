package Modelo;

import java.time.LocalDateTime;

public class Cita {
    private final String idCita;
    private final LocalDateTime fechaHora;
    private final String motivo;
    private String estado = "PROGRAMADA";
    private final Mascota mascota;
    private final Recepcionista recepcionista;
    private ConsultaMedica consulta;

    public Cita(String idCita, LocalDateTime fechaHora, String motivo,
            Mascota mascota, Recepcionista recepcionista) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.mascota = mascota;
        this.recepcionista = recepcionista;
        mascota.agregarCita(this);
    }

    public String getIdCita() {
        return idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getEstado() {
        return estado;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public ConsultaMedica getConsulta() {
        return consulta;
    }

    public void asociarConsulta(ConsultaMedica consulta) {
        if (!estaProgramada() || consulta.getHistorial().getMascota() != mascota
                || consulta.getCita() != null) {
            throw new IllegalStateException("La cita no se puede asociar con esa consulta.");
        }
        this.consulta = consulta;
        consulta.asignarCita(this);
        marcarAtendida();
    }

    private void marcarAtendida() {
        estado = "ATENDIDA";
    }

    public boolean estaProgramada() {
        return estado.equals("PROGRAMADA");
    }

    public void cancelar() {
        if (!estaProgramada()) {
            throw new IllegalStateException("Solo se pueden cancelar citas programadas.");
        }
        estado = "CANCELADA";
    }
}
