package Vista;

public class VentanaPersonal extends javax.swing.JFrame {

    public VentanaPersonal() {
        initComponents();
        conectarFiltro();
    }

    public String getTipo() {
        return cmbTipo.getSelectedItem().toString();
    }

    public void setTipo(String valor) {
        cmbTipo.setSelectedItem(valor);
    }

    public void habilitarTipo(boolean habilitado) {
        cmbTipo.setEnabled(habilitado);
    }

    public void alCambiarTipo(java.awt.event.ActionListener listener) {
        cmbTipo.setActionCommand("tipo");
        cmbTipo.addActionListener(listener);
    }

    public String getDocumento() {
        return txtDocumento.getText().trim();
    }

    public void setDocumento(String valor) {
        txtDocumento.setText(valor);
    }

    public void habilitarDocumento(boolean habilitado) {
        txtDocumento.setEditable(habilitado);
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public void setNombre(String valor) {
        txtNombre.setText(valor);
    }

    public void habilitarNombre(boolean habilitado) {
        txtNombre.setEditable(habilitado);
    }

    public String getTelefono() {
        return txtTelefono.getText().trim();
    }

    public void setTelefono(String valor) {
        txtTelefono.setText(valor);
    }

    public void habilitarTelefono(boolean habilitado) {
        txtTelefono.setEditable(habilitado);
    }

    public String getEspecialidad() {
        return txtEspecialidad.getText().trim();
    }

    public void setEspecialidad(String valor) {
        txtEspecialidad.setText(valor);
    }

    public void habilitarEspecialidad(boolean habilitado) {
        txtEspecialidad.setEditable(habilitado);
    }

    public String getTarjeta() {
        return txtTarjeta.getText().trim();
    }

    public void setTarjeta(String valor) {
        txtTarjeta.setText(valor);
    }

    public void habilitarTarjeta(boolean habilitado) {
        txtTarjeta.setEditable(habilitado);
    }

    public void alRegistrar(java.awt.event.ActionListener listener) {
        btnRegistrar.addActionListener(listener);
    }

    public void habilitarBotonRegistrar(boolean habilitado) {
        btnRegistrar.setEnabled(habilitado);
    }

    public void alBuscar(java.awt.event.ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }

    public void habilitarBotonBuscar(boolean habilitado) {
        btnBuscar.setEnabled(habilitado);
    }

    public void alListar(java.awt.event.ActionListener listener) {
        btnListar.addActionListener(listener);
    }

    public void habilitarBotonListar(boolean habilitado) {
        btnListar.setEnabled(habilitado);
    }

    public void alNuevo(java.awt.event.ActionListener listener) {
        btnNuevo.addActionListener(listener);
    }

    public void habilitarBotonNuevo(boolean habilitado) {
        btnNuevo.setEnabled(habilitado);
    }

    public void alVolver(java.awt.event.ActionListener listener) {
        btnVolver.addActionListener(listener);
    }

    public void habilitarBotonVolver(boolean habilitado) {
        btnVolver.setEnabled(habilitado);
    }

    public void alSeleccionar(javax.swing.event.ListSelectionListener listener) {
        tablaRegistros.getSelectionModel().addListSelectionListener(listener);
    }

    public String getIdSeleccionado() {
        int fila = tablaRegistros.getSelectedRow();
        if (fila == -1) {
            return "";
        }
        return tablaRegistros.getValueAt(fila, 0).toString();
    }

    public boolean estaActualizando() {
        return actualizando;
    }

    public void mostrarRegistros(java.util.List<String[]> filas) {
        actualizando = true;
        try {
            javax.swing.table.DefaultTableModel modelo =
                    (javax.swing.table.DefaultTableModel) tablaRegistros.getModel();
            modelo.setRowCount(0);
            for (String[] fila : filas) {
                modelo.addRow(fila);
            }
        } finally {
            actualizando = false;
        }
    }

    private void filtrarListado() {
        javax.swing.table.TableRowSorter<?> ordenador =
                (javax.swing.table.TableRowSorter<?>) tablaRegistros.getRowSorter();
        String texto = txtFiltro.getText().trim();
        if (texto.isEmpty()) {
            ordenador.setRowFilter(null);
        } else {
            ordenador.setRowFilter(javax.swing.RowFilter.regexFilter(
                    "(?iu)" + java.util.regex.Pattern.quote(texto)));
        }
    }

    private void conectarFiltro() {
        txtFiltro.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent evento) {
                filtrarListado();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent evento) {
                filtrarListado();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent evento) {
                filtrarListado();
            }
        });
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
        lblTitulo.setText("Personal");
        lblAyuda = new javax.swing.JLabel();
        lblAyuda.setText("Registre recepcionistas y veterinarios. La especialidad y tarjeta corresponden al veterinario.");
        panelDatos = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        lblTipo.setText("Tipo de colaborador");
        cmbTipo = new javax.swing.JComboBox<>();
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Recepcionista", "Veterinario"}));
        lblDocumento = new javax.swing.JLabel();
        lblDocumento.setText("Documento");
        txtDocumento = new javax.swing.JTextField();
        txtDocumento.setText("");
        lblNombre = new javax.swing.JLabel();
        lblNombre.setText("Nombre");
        txtNombre = new javax.swing.JTextField();
        txtNombre.setText("");
        lblTelefono = new javax.swing.JLabel();
        lblTelefono.setText("Teléfono");
        txtTelefono = new javax.swing.JTextField();
        txtTelefono.setText("");
        lblEspecialidad = new javax.swing.JLabel();
        lblEspecialidad.setText("Especialidad");
        txtEspecialidad = new javax.swing.JTextField();
        txtEspecialidad.setText("");
        lblTarjeta = new javax.swing.JLabel();
        lblTarjeta.setText("Tarjeta profesional");
        txtTarjeta = new javax.swing.JTextField();
        txtTarjeta.setText("");
        panelAcciones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnRegistrar.setText("Registrar");
        btnRegistrar.setActionCommand("registrar");
        btnBuscar = new javax.swing.JButton();
        btnBuscar.setText("Buscar por documento");
        btnBuscar.setActionCommand("buscar");
        btnListar = new javax.swing.JButton();
        btnListar.setText("Actualizar listado");
        btnListar.setActionCommand("listar");
        btnNuevo = new javax.swing.JButton();
        btnNuevo.setText("Nuevo");
        btnNuevo.setActionCommand("nuevo");
        btnVolver = new javax.swing.JButton();
        btnVolver.setText("Volver al menú");
        btnVolver.setActionCommand("volver");
        panelListado = new javax.swing.JPanel();
        lblFiltro = new javax.swing.JLabel();
        lblFiltro.setText("Buscar en el listado:");
        txtFiltro = new javax.swing.JTextField();
        txtFiltro.setText("");
        scrollListado = new javax.swing.JScrollPane();
        tablaRegistros = new javax.swing.JTable();
        tablaRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{}, new String[]{"Registro", "Nombre", "Rol", "Teléfono"}) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        });
        tablaRegistros.setAutoCreateRowSorter(true);
        tablaRegistros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollListado.setViewportView(tablaRegistros);
        scrollResultado = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        scrollResultado.setViewportView(txtResultado);
        lblEstado = new javax.swing.JLabel();
        lblEstado.setText("Listo.");
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Personal");
        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(16)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cmbTipo, 0, 400, Short.MAX_VALUE)
                .addComponent(txtDocumento, 0, 400, Short.MAX_VALUE)
                .addComponent(txtNombre, 0, 400, Short.MAX_VALUE)
                .addComponent(txtTelefono, 0, 400, Short.MAX_VALUE)
                .addComponent(txtEspecialidad, 0, 400, Short.MAX_VALUE)
                .addComponent(txtTarjeta, 0, 400, Short.MAX_VALUE)));
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)));
        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnRegistrar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnBuscar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnListar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnNuevo, 0, 170, Short.MAX_VALUE))
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnVolver, 0, 170, Short.MAX_VALUE)));
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createSequentialGroup()
            .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)));
        javax.swing.GroupLayout panelListadoLayout = new javax.swing.GroupLayout(panelListado);
        panelListado.setLayout(panelListadoLayout);
        panelListadoLayout.setHorizontalGroup(
            panelListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListadoLayout.createSequentialGroup()
                .addComponent(lblFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8)
                .addComponent(txtFiltro, 0, 400, Short.MAX_VALUE))
            .addComponent(scrollListado, 0, 800, Short.MAX_VALUE));
        panelListadoLayout.setVerticalGroup(
            panelListadoLayout.createSequentialGroup()
            .addGroup(panelListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addComponent(scrollListado, 0, 140, Short.MAX_VALUE));
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
                .addComponent(panelListado, 0, 960, Short.MAX_VALUE)
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
            .addComponent(panelListado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(8)
            .addComponent(scrollResultado, 0, 100, Short.MAX_VALUE)
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
    private javax.swing.JLabel lblTipo;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JLabel lblEspecialidad;
    private javax.swing.JTextField txtEspecialidad;
    private javax.swing.JLabel lblTarjeta;
    private javax.swing.JTextField txtTarjeta;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel panelListado;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JScrollPane scrollListado;
    private javax.swing.JTable tablaRegistros;
    private javax.swing.JScrollPane scrollResultado;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JLabel lblEstado;
    // End of variables declaration//GEN-END:variables
    private boolean actualizando;
}
