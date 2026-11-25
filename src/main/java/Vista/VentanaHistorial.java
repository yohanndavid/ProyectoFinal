package Vista;

public class VentanaHistorial extends javax.swing.JFrame {

    public VentanaHistorial() {
        initComponents();
    }

    public String getMascota() {
        int posicion = cmbMascota.getSelectedIndex();
        if (posicion < 1 || posicion > idsMascota.size()) {
            return "";
        }
        return idsMascota.get(posicion - 1);
    }

    public void setMascota(String id) {
        int posicion = idsMascota.indexOf(id);
        cmbMascota.setSelectedIndex(posicion + 1);
    }

    public void cargarMascota(java.util.List<String> ids, java.util.List<String> nombres) {
        String anterior = getMascota();
        idsMascota.clear();
        idsMascota.addAll(ids);
        cmbMascota.removeAllItems();
        cmbMascota.addItem("Seleccione...");
        for (String nombre : nombres) {
            cmbMascota.addItem(nombre);
        }
        setMascota(anterior);
    }

    public void habilitarMascota(boolean habilitado) {
        cmbMascota.setEnabled(habilitado);
    }

    public void alCambiarMascota(java.awt.event.ActionListener listener) {
        cmbMascota.setActionCommand("mascota");
        cmbMascota.addActionListener(listener);
    }

    public void alConsultar(java.awt.event.ActionListener listener) {
        btnConsultar.addActionListener(listener);
    }

    public void habilitarBotonConsultar(boolean habilitado) {
        btnConsultar.setEnabled(habilitado);
    }

    public void alListar(java.awt.event.ActionListener listener) {
        btnListar.addActionListener(listener);
    }

    public void habilitarBotonListar(boolean habilitado) {
        btnListar.setEnabled(habilitado);
    }

    public void alVolver(java.awt.event.ActionListener listener) {
        btnVolver.addActionListener(listener);
    }

    public void habilitarBotonVolver(boolean habilitado) {
        btnVolver.setEnabled(habilitado);
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
        lblTitulo.setText("Historial clínico");
        lblAyuda = new javax.swing.JLabel();
        lblAyuda.setText("Seleccione la mascota para consultar sus atenciones, diagnósticos, tratamientos e insumos.");
        panelDatos = new javax.swing.JPanel();
        lblMascota = new javax.swing.JLabel();
        lblMascota.setText("Mascota");
        cmbMascota = new javax.swing.JComboBox<>();
        cmbMascota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione..."}));
        panelAcciones = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnConsultar.setText("Consultar historial");
        btnConsultar.setActionCommand("consultar");
        btnListar = new javax.swing.JButton();
        btnListar.setText("Actualizar mascotas");
        btnListar.setActionCommand("listar");
        btnVolver = new javax.swing.JButton();
        btnVolver.setText("Volver al menú");
        btnVolver.setActionCommand("volver");
        scrollResultado = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        scrollResultado.setViewportView(txtResultado);
        lblEstado = new javax.swing.JLabel();
        lblEstado.setText("Listo.");
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Historial clínico");
        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(16)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cmbMascota, 0, 400, Short.MAX_VALUE)));
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)));
        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnConsultar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnListar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnVolver, 0, 170, Short.MAX_VALUE)));
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createSequentialGroup()
            .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)));
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
    private javax.swing.JLabel lblMascota;
    private javax.swing.JComboBox<String> cmbMascota;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane scrollResultado;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JLabel lblEstado;
    // End of variables declaration//GEN-END:variables
    private final java.util.ArrayList<String> idsMascota = new java.util.ArrayList<>();
}
