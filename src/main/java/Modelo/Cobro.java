package Modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Locale;

public class Cobro {
    private final String numeroCobro;
    private final LocalDate fecha;
    private final double tarifaBase;
    private final double costoSedacion;
    private final double recargo;
    private final double valorVacunas;
    private final double valorMedicamentos;
    private final double subtotal;
    private final double descuento;
    private final double total;
    private String metodoPago = "PENDIENTE";
    private boolean pagado;
    private final ConsultaMedica consulta;
    private final Recepcionista recepcionista;
    private final String detalle;

    Cobro(String numeroCobro, ConsultaMedica consulta, double tarifaBase,
            double costoSedacion, Recepcionista recepcionista) {
        this.numeroCobro = numeroCobro;
        this.consulta = consulta;
        this.recepcionista = recepcionista;
        Mascota mascota = consulta.getHistorial().getMascota();
        Propietario propietario = mascota.getPropietario();
        fecha = consulta.getFecha().toLocalDate();
        this.tarifaBase = dinero(tarifaBase);
        recargo = dinero(mascota.calcularRecargo(this.tarifaBase, consulta));
        double valorSedacion = 0;
        if (mascota instanceof MascotaExotica) {
            MascotaExotica exotica = (MascotaExotica) mascota;
            if (exotica.isRequiereSedacion()) {
                valorSedacion = dinero(costoSedacion);
            }
        }
        this.costoSedacion = valorSedacion;
        valorVacunas = dinero(consulta.calcularValorVacunas());
        valorMedicamentos = dinero(consulta.calcularValorMedicamentos());
        subtotal = dinero(this.tarifaBase + recargo + this.costoSedacion + valorVacunas + valorMedicamentos);
        descuento = propietario.tieneBeneficio() ? dinero(subtotal * 0.10) : 0;
        total = dinero(subtotal - descuento);
        detalle = crearDetalle(propietario, mascota);
    }

    private String crearDetalle(Propietario propietario, Mascota mascota) {
        StringBuilder texto = new StringBuilder("Consulta: " + consulta.getIdConsulta()
                + "\nMascota: " + mascota.getNombre() + " (" + mascota.getIdMascota() + ")"
                + "\nPropietario: " + propietario.getNombre()
                + "\nDocumento: " + propietario.getDocumento()
                + "\nVeterinario: " + consulta.getVeterinario().getNombre());
        for (ItemAplicado item : consulta.getItems()) {
            texto.append("\nÍtem: ").append(item.getNombre())
                    .append(" | Cantidad: ").append(item.getCantidad())
                    .append(" | Unitario: ").append(moneda(item.getValorUnitario()))
                    .append(" | Total: ").append(moneda(item.calcularTotal()));
        }
        return texto.toString();
    }

    private static double dinero(double valor) {
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static String moneda(double valor) {
        return String.format(Locale.forLanguageTag("es-CO"), "$%,.2f", valor);
    }

    public String getNumeroCobro() {
        return numeroCobro;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public double getCostoSedacion() {
        return costoSedacion;
    }

    public double getRecargo() {
        return recargo;
    }

    public double getValorVacunas() {
        return valorVacunas;
    }

    public double getValorMedicamentos() {
        return valorMedicamentos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotal() {
        return total;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public boolean isPagado() {
        return pagado;
    }

    public ConsultaMedica getConsulta() {
        return consulta;
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public double calcularSubtotal() {
        return subtotal;
    }

    public double calcularDescuento() {
        return descuento;
    }

    public double calcularTotal() {
        return total;
    }

    public void registrarPago(String metodoPago) {
        if (pagado) {
            throw new IllegalStateException("Este cobro ya está pagado.");
        }
        this.metodoPago = metodoPago;
        pagado = true;
    }

    public String emitirComprobante() {
        return "COMPROBANTE DE COBRO " + numeroCobro + "\nFecha: " + fecha + "\n" + detalle
                + "\nTarifa base: " + moneda(tarifaBase)
                + "\nRecargo 25%: " + moneda(recargo)
                + "\nSedación: " + moneda(costoSedacion)
                + "\nVacunas: " + moneda(valorVacunas)
                + "\nMedicamentos: " + moneda(valorMedicamentos)
                + "\nSubtotal: " + moneda(subtotal)
                + "\nDescuento: " + moneda(descuento)
                + "\nTotal: " + moneda(total)
                + "\nMétodo de pago: " + metodoPago
                + "\nEstado: " + (pagado ? "PAGADO" : "PENDIENTE");
    }
}
