package Vista;

public class VentanaMenu extends javax.swing.JFrame {

    public VentanaMenu() {
        initComponents();
    }

    public String getClinica() {
        return txtClinica.getText().trim();
    }

    public void setClinica(String valor) {
        txtClinica.setText(valor);
    }

    public void habilitarClinica(boolean habilitado) {
        txtClinica.setEditable(habilitado);
    }

    public String getTarifa() {
        return txtTarifa.getText().trim();
    }

    public void setTarifa(String valor) {
        txtTarifa.setText(valor);
    }

    public void habilitarTarifa(boolean habilitado) {
        txtTarifa.setEditable(habilitado);
    }

    public String getSedacion() {
        return txtSedacion.getText().trim();
    }

    public void setSedacion(String valor) {
        txtSedacion.setText(valor);
    }

    public void habilitarSedacion(boolean habilitado) {
        txtSedacion.setEditable(habilitado);
    }

    public void alIniciar(java.awt.event.ActionListener listener) {
        btnIniciar.addActionListener(listener);
    }

    public void habilitarBotonIniciar(boolean habilitado) {
        btnIniciar.setEnabled(habilitado);
    }

    public void alPropietarios(java.awt.event.ActionListener listener) {
        btnPropietarios.addActionListener(listener);
    }

    public void habilitarBotonPropietarios(boolean habilitado) {
        btnPropietarios.setEnabled(habilitado);
    }

    public void alPersonal(java.awt.event.ActionListener listener) {
        btnPersonal.addActionListener(listener);
    }

    public void habilitarBotonPersonal(boolean habilitado) {
        btnPersonal.setEnabled(habilitado);
    }

    public void alMascotas(java.awt.event.ActionListener listener) {
        btnMascotas.addActionListener(listener);
    }

    public void habilitarBotonMascotas(boolean habilitado) {
        btnMascotas.setEnabled(habilitado);
    }

    public void alCitas(java.awt.event.ActionListener listener) {
        btnCitas.addActionListener(listener);
    }

    public void habilitarBotonCitas(boolean habilitado) {
        btnCitas.setEnabled(habilitado);
    }

    public void alConsultas(java.awt.event.ActionListener listener) {
        btnConsultas.addActionListener(listener);
    }

    public void habilitarBotonConsultas(boolean habilitado) {
        btnConsultas.setEnabled(habilitado);
    }

    public void alInsumos(java.awt.event.ActionListener listener) {
        btnInsumos.addActionListener(listener);
    }

    public void habilitarBotonInsumos(boolean habilitado) {
        btnInsumos.setEnabled(habilitado);
    }

    public void alHistorial(java.awt.event.ActionListener listener) {
        btnHistorial.addActionListener(listener);
    }

    public void habilitarBotonHistorial(boolean habilitado) {
        btnHistorial.setEnabled(habilitado);
    }

    public void alCobros(java.awt.event.ActionListener listener) {
        btnCobros.addActionListener(listener);
    }

    public void habilitarBotonCobros(boolean habilitado) {
        btnCobros.setEnabled(habilitado);
    }

    public void alSalir(java.awt.event.ActionListener listener) {
        btnSalir.addActionListener(listener);
    }

    public void habilitarBotonSalir(boolean habilitado) {
        btnSalir.setEnabled(habilitado);
    }

    public void mostrarResultado(String texto) {
        txtResultado.setText(texto);
        txtResultado.setCaretPosition(0);
    }

    public void mostrarInformacion(String mensaje) {
        lblEstado.setText(mensaje);
        lblEstado.setToolTipText(mensaje);
    }

    public void mostrarError(String mensaje) {
        mostrarInformacion("Revise: " + mensaje);
        javax.swing.JOptionPane.showMessageDialog(this, mensaje,
                "Revise los datos", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    public boolean confirmar(String mensaje) {
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, mensaje,
                "Confirmar operación", javax.swing.JOptionPane.YES_NO_OPTION);
        return respuesta == javax.swing.JOptionPane.YES_OPTION;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblTitulo = new javax.swing.JLabel();
        lblTitulo.setText("Clínica veterinaria");
        lblAyuda = new javax.swing.JLabel();
        lblAyuda.setText("Datos en memoria: permanecen mientras el programa esté abierto. Inicie la jornada para habilitar los módulos.");
        panelDatos = new javax.swing.JPanel();
        lblClinica = new javax.swing.JLabel();
        lblClinica.setText("Nombre de la clínica");
        txtClinica = new javax.swing.JTextField();
        txtClinica.setText("Clínica Veterinaria");
        lblTarifa = new javax.swing.JLabel();
        lblTarifa.setText("Tarifa base por consulta");
        txtTarifa = new javax.swing.JTextField();
        txtTarifa.setText("100000");
        lblSedacion = new javax.swing.JLabel();
        lblSedacion.setText("Costo adicional de sedación");
        txtSedacion = new javax.swing.JTextField();
        txtSedacion.setText("30000");
        panelAcciones = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        btnIniciar.setText("Iniciar jornada");
        btnIniciar.setActionCommand("iniciar");
        btnPropietarios = new javax.swing.JButton();
        btnPropietarios.setText("Propietarios");
        btnPropietarios.setActionCommand("Propietarios");
        btnPersonal = new javax.swing.JButton();
        btnPersonal.setText("Personal");
        btnPersonal.setActionCommand("Personal");
        btnMascotas = new javax.swing.JButton();
        btnMascotas.setText("Mascotas");
        btnMascotas.setActionCommand("Mascotas");
        btnCitas = new javax.swing.JButton();
        btnCitas.setText("Agenda de citas");
        btnCitas.setActionCommand("Citas");
        btnConsultas = new javax.swing.JButton();
        btnConsultas.setText("Atención médica");
        btnConsultas.setActionCommand("Consultas");
        btnInsumos = new javax.swing.JButton();
        btnInsumos.setText("Vacunas / medicamentos");
        btnInsumos.setActionCommand("Insumos");
        btnHistorial = new javax.swing.JButton();
        btnHistorial.setText("Historial clínico");
        btnHistorial.setActionCommand("Historial");
        btnCobros = new javax.swing.JButton();
        btnCobros.setText("Caja y comprobantes");
        btnCobros.setActionCommand("Cobros");
        btnSalir = new javax.swing.JButton();
        btnSalir.setText("Salir");
        btnSalir.setActionCommand("salir");
        scrollResultado = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        scrollResultado.setViewportView(txtResultado);
        lblEstado = new javax.swing.JLabel();
        lblEstado.setText("Listo.");
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clínica veterinaria");
        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSedacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(16)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtClinica, 0, 400, Short.MAX_VALUE)
                .addComponent(txtTarifa, 0, 400, Short.MAX_VALUE)
                .addComponent(txtSedacion, 0, 400, Short.MAX_VALUE)));
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblSedacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSedacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)));
        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnIniciar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnPropietarios, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnPersonal, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnMascotas, 0, 170, Short.MAX_VALUE))
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnCitas, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnConsultas, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnInsumos, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnHistorial, 0, 170, Short.MAX_VALUE))
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnCobros, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnSalir, 0, 170, Short.MAX_VALUE)));
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createSequentialGroup()
            .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnPropietarios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnCobros, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGap(12)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblTitulo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAyuda, 0, 960, Short.MAX_VALUE)
                .addComponent(panelDatos, 0, 960, Short.MAX_VALUE)
                .addComponent(panelAcciones, 0, 960, Short.MAX_VALUE)
                .addComponent(scrollResultado, 0, 960, Short.MAX_VALUE)
                .addComponent(lblEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(12));
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGap(12)
            .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(8)
            .addComponent(lblAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(8)
            .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(8)
            .addComponent(panelAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(8)
            .addComponent(scrollResultado, 0, 230, Short.MAX_VALUE)
            .addGap(8)
            .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(12));
        pack();
    }//GEN-END:initComponents
    // </editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblAyuda;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JLabel lblClinica;
    private javax.swing.JTextField txtClinica;
    private javax.swing.JLabel lblTarifa;
    private javax.swing.JTextField txtTarifa;
    private javax.swing.JLabel lblSedacion;
    private javax.swing.JTextField txtSedacion;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnPropietarios;
    private javax.swing.JButton btnPersonal;
    private javax.swing.JButton btnMascotas;
    private javax.swing.JButton btnCitas;
    private javax.swing.JButton btnConsultas;
    private javax.swing.JButton btnInsumos;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnCobros;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane scrollResultado;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JLabel lblEstado;
    // End of variables declaration//GEN-END:variables

}
