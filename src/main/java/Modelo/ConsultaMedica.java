package Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaMedica {
    private final String idConsulta;
    private final LocalDateTime fecha;
    private final Veterinario veterinario;
    private final HistorialClinico historial;
    private String diagnostico = "";
    private String tratamientos = "";
    private String notasMedicas = "";
    private final List<ItemAplicado> items = new ArrayList<>();
    private Cita cita;
    private Cobro cobro;

    ConsultaMedica(String idConsulta, LocalDateTime fecha, Veterinario veterinario,
            HistorialClinico historial) {
        this.idConsulta = idConsulta;
        this.fecha = fecha;
        this.veterinario = veterinario;
        this.historial = historial;
    }

    public String getIdConsulta() {
        return idConsulta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public HistorialClinico getHistorial() {
        return historial;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamientos() {
        return tratamientos;
    }

    public String getNotasMedicas() {
        return notasMedicas;
    }

    public List<ItemAplicado> getItems() {
        return List.copyOf(items);
    }

    public Cita getCita() {
        return cita;
    }

    public Cobro getCobro() {
        return cobro;
    }

    public boolean estaAbierta() {
        return cobro == null;
    }

    private void verificarAbierta() {
        if (!estaAbierta()) {
            throw new IllegalStateException("La consulta ya tiene un cobro; no admite cambios.");
        }
    }

    public void registrarDiagnostico(String diagnostico) {
        verificarAbierta();
        this.diagnostico = diagnostico;
    }

    public void registrarTratamientos(String tratamientos) {
        verificarAbierta();
        this.tratamientos = tratamientos;
    }

    public void registrarNotas(String notas) {
        verificarAbierta();
        notasMedicas = notas;
    }

    public void agregarItem(ItemAplicado item) {
        verificarAbierta();
        if (item.getConsulta() != null) {
            throw new IllegalStateException("El insumo ya fue aplicado.");
        }
        if (buscarItem(item.getCodigo()) != null) {
            throw new IllegalStateException("Código de insumo repetido.");
        }
        items.add(item);
        item.asignarConsulta(this);
    }

    public ItemAplicado buscarItem(String codigo) {
        for (ItemAplicado item : items) {
            if (item.getCodigo().equals(codigo)) {
                return item;
            }
        }
        return null;
    }

    public double calcularValorVacunas() {
        double total = 0;
        for (ItemAplicado item : items) {
            if (item instanceof Vacuna) {
                total += item.calcularTotal();
            }
        }
        return total;
    }

    public double calcularValorMedicamentos() {
        double total = 0;
        for (ItemAplicado item : items) {
            if (item instanceof Medicamento) {
                total += item.calcularTotal();
            }
        }
        return total;
    }

    void asignarCita(Cita cita) {
        this.cita = cita;
    }

    public Cobro previsualizarCobro(double tarifaBase, double costoSedacion, Recepcionista recepcionista) {
        verificarAbierta();
        return new Cobro("VISTA PREVIA", this, tarifaBase, costoSedacion, recepcionista);
    }

    public Cobro generarCobro(String numeroCobro, double tarifaBase,
            double costoSedacion, Recepcionista recepcionista) {
        verificarAbierta();
        if (diagnostico.isBlank()) {
            throw new IllegalStateException("La consulta necesita un diagnóstico.");
        }
        cobro = new Cobro(numeroCobro, this, tarifaBase, costoSedacion, recepcionista);
        return cobro;
    }
}
