package Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistorialClinico {
    private final String numeroHistoria;
    private final LocalDate fechaCreacion;
    private final Mascota mascota;
    private final List<ConsultaMedica> consultas = new ArrayList<>();

    HistorialClinico(String numeroHistoria, Mascota mascota) {
        this.numeroHistoria = numeroHistoria;
        this.mascota = mascota;
        fechaCreacion = LocalDate.now();
    }

    public String getNumeroHistoria() {
        return numeroHistoria;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public ConsultaMedica crearConsulta(String idConsulta, LocalDateTime fecha, Veterinario veterinario) {
        ConsultaMedica consulta = new ConsultaMedica(idConsulta, fecha, veterinario, this);
        agregarConsulta(consulta);
        return consulta;
    }

    private void agregarConsulta(ConsultaMedica consulta) {
        consultas.add(consulta);
    }

    public List<ConsultaMedica> obtenerConsultas() {
        return List.copyOf(consultas);
    }

    public ConsultaMedica buscarConsulta(String id) {
        for (ConsultaMedica consulta : consultas) {
            if (consulta.getIdConsulta().equals(id)) {
                return consulta;
            }
        }
        return null;
    }

    public String generarResumen() {
        StringBuilder texto = new StringBuilder("Historia: " + numeroHistoria
                + "\nFecha de creación: " + fechaCreacion + "\nMascota: " + mascota.getNombre());
        for (ConsultaMedica consulta : consultas) {
            texto.append("\n\nConsulta: ").append(consulta.getIdConsulta())
                    .append("\nFecha: ").append(consulta.getFecha())
                    .append("\nVeterinario: ").append(consulta.getVeterinario().getNombre())
                    .append("\nDiagnóstico: ").append(consulta.getDiagnostico())
                    .append("\nTratamientos: ").append(consulta.getTratamientos())
                    .append("\nNotas: ").append(consulta.getNotasMedicas());
            for (ItemAplicado item : consulta.getItems()) {
                texto.append("\n").append(item instanceof Vacuna ? "Vacuna: " : "Medicamento: ")
                        .append(item.getNombre()).append(" | Cantidad: ").append(item.getCantidad())
                        .append(" | Valor: ").append(item.calcularTotal());
                if (item instanceof Vacuna) {
                    Vacuna vacuna = (Vacuna) item;
                    texto.append(" | Lote: ").append(vacuna.getLote())
                            .append(" | Próxima dosis: ").append(vacuna.getProximaDosis() == null
                                    ? "No programada" : vacuna.getProximaDosis());
                } else if (item instanceof Medicamento) {
                    Medicamento medicamento = (Medicamento) item;
                    texto.append(" | Dosis: ").append(medicamento.getDosis())
                            .append(" | Vía: ").append(medicamento.getViaAdministracion());
                }
            }
        }
        return texto.toString();
    }
}
