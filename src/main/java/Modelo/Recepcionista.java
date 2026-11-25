package Modelo;

public class Recepcionista extends Colaborador {
    public Recepcionista(String documento, String nombre, String telefono) {
        super(documento, nombre, telefono, "Recepcionista");
    }

    public Cita agendarCita(String id, java.time.LocalDateTime fecha, String motivo, Mascota mascota) {
        return new Cita(id, fecha, motivo, mascota, this);
    }

    public Cobro registrarCobro(String numero, ConsultaMedica consulta,
            double tarifaBase, double costoSedacion) {
        return consulta.generarCobro(numero, tarifaBase, costoSedacion, this);
    }
}
