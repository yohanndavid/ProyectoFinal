package Modelo;

import java.time.LocalDate;

public class Vacuna extends ItemAplicado {
    private final String lote;
    private final LocalDate proximaDosis;

    public Vacuna(String codigo, String nombre, int cantidad, double valorUnitario,
            String lote, LocalDate proximaDosis) {
        super(codigo, nombre, cantidad, valorUnitario);
        this.lote = lote;
        this.proximaDosis = proximaDosis;
    }

    public String getLote() {
        return lote;
    }

    public LocalDate getProximaDosis() {
        return proximaDosis;
    }
}
