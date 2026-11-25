package Vista;

public class VentanaInsumos extends javax.swing.JFrame {

    public VentanaInsumos() {
        initComponents();
        conectarFiltro();
    }

    public String getConsulta() {
        int posicion = cmbConsulta.getSelectedIndex();
        if (posicion < 1 || posicion > idsConsulta.size()) {
            return "";
        }
        return idsConsulta.get(posicion - 1);
    }

    public void setConsulta(String id) {
        int posicion = idsConsulta.indexOf(id);
        cmbConsulta.setSelectedIndex(posicion + 1);
    }

    public void cargarConsulta(java.util.List<String> ids, java.util.List<String> nombres) {
        String anterior = getConsulta();
        idsConsulta.clear();
        idsConsulta.addAll(ids);
        cmbConsulta.removeAllItems();
        cmbConsulta.addItem("Seleccione...");
        for (String nombre : nombres) {
            cmbConsulta.addItem(nombre);
        }
        setConsulta(anterior);
    }

    public void habilitarConsulta(boolean habilitado) {
        cmbConsulta.setEnabled(habilitado);
    }

    public void alCambiarConsulta(java.awt.event.ActionListener listener) {
        cmbConsulta.setActionCommand("consulta");
        cmbConsulta.addActionListener(listener);
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

    public String getCodigo() {
        return txtCodigo.getText().trim();
    }

    public void setCodigo(String valor) {
        txtCodigo.setText(valor);
    }

    public void habilitarCodigo(boolean habilitado) {
        txtCodigo.setEditable(habilitado);
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

    public String getCantidad() {
        return txtCantidad.getText().trim();
    }

    public void setCantidad(String valor) {
        txtCantidad.setText(valor);
    }

    public void habilitarCantidad(boolean habilitado) {
        txtCantidad.setEditable(habilitado);
    }

    public String getValor() {
        return txtValor.getText().trim();
    }

    public void setValor(String valor) {
        txtValor.setText(valor);
    }

    public void habilitarValor(boolean habilitado) {
        txtValor.setEditable(habilitado);
    }

    public String getLote() {
        return txtLote.getText().trim();
    }

    public void setLote(String valor) {
        txtLote.setText(valor);
    }

    public void habilitarLote(boolean habilitado) {
        txtLote.setEditable(habilitado);
    }

    public String getProxima() {
        return txtProxima.getText().trim();
    }

    public void setProxima(String valor) {
        txtProxima.setText(valor);
    }

    public void habilitarProxima(boolean habilitado) {
        txtProxima.setEditable(habilitado);
    }

    public String getDosis() {
        return txtDosis.getText().trim();
    }

    public void setDosis(String valor) {
        txtDosis.setText(valor);
    }

    public void habilitarDosis(boolean habilitado) {
        txtDosis.setEditable(habilitado);
    }

    public String getVia() {
        return txtVia.getText().trim();
    }

    public void setVia(String valor) {
        txtVia.setText(valor);
    }

    public void habilitarVia(boolean habilitado) {
        txtVia.setEditable(habilitado);
    }

    public void alAplicar(java.awt.event.ActionListener listener) {
        btnAplicar.addActionListener(listener);
    }

    public void habilitarBotonAplicar(boolean habilitado) {
        btnAplicar.setEnabled(habilitado);
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
        lblTitulo.setText("Vacunas y medicamentos");
        lblAyuda = new javax.swing.JLabel();
        lblAyuda.setText("Seleccione una consulta abierta. Valores sin puntos de miles, hasta dos decimales. Próxima dosis opcional.");
        panelDatos = new javax.swing.JPanel();
        lblConsulta = new javax.swing.JLabel();
        lblConsulta.setText("Consulta");
        cmbConsulta = new javax.swing.JComboBox<>();
        cmbConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione..."}));
        lblTipo = new javax.swing.JLabel();
        lblTipo.setText("Tipo de insumo");
        cmbTipo = new javax.swing.JComboBox<>();
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Vacuna", "Medicamento"}));
        lblCodigo = new javax.swing.JLabel();
        lblCodigo.setText("Código del insumo");
        txtCodigo = new javax.swing.JTextField();
        txtCodigo.setText("");
        lblNombre = new javax.swing.JLabel();
        lblNombre.setText("Nombre");
        txtNombre = new javax.swing.JTextField();
        txtNombre.setText("");
        lblCantidad = new javax.swing.JLabel();
        lblCantidad.setText("Cantidad");
        txtCantidad = new javax.swing.JTextField();
        txtCantidad.setText("1");
        lblValor = new javax.swing.JLabel();
        lblValor.setText("Valor unitario");
        txtValor = new javax.swing.JTextField();
        txtValor.setText("");
        lblLote = new javax.swing.JLabel();
        lblLote.setText("Lote de vacuna");
        txtLote = new javax.swing.JTextField();
        txtLote.setText("");
        lblProxima = new javax.swing.JLabel();
        lblProxima.setText("Próxima dosis: dd/MM/aaaa");
        txtProxima = new javax.swing.JTextField();
        txtProxima.setText("");
        lblDosis = new javax.swing.JLabel();
        lblDosis.setText("Dosis de medicamento");
        txtDosis = new javax.swing.JTextField();
        txtDosis.setText("");
        lblVia = new javax.swing.JLabel();
        lblVia.setText("Vía de administración");
        txtVia = new javax.swing.JTextField();
        txtVia.setText("");
        panelAcciones = new javax.swing.JPanel();
        btnAplicar = new javax.swing.JButton();
        btnAplicar.setText("Aplicar insumo");
        btnAplicar.setActionCommand("aplicar");
        btnListar = new javax.swing.JButton();
        btnListar.setText("Actualizar listado");
        btnListar.setActionCommand("listar");
        btnNuevo = new javax.swing.JButton();
        btnNuevo.setText("Nuevo insumo");
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
            new Object[][]{}, new String[]{"Código", "Tipo", "Nombre", "Cantidad", "Total"}) {
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
        setTitle("Vacunas y medicamentos");
        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblProxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(16)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cmbConsulta, 0, 400, Short.MAX_VALUE)
                .addComponent(cmbTipo, 0, 400, Short.MAX_VALUE)
                .addComponent(txtCodigo, 0, 400, Short.MAX_VALUE)
                .addComponent(txtNombre, 0, 400, Short.MAX_VALUE)
                .addComponent(txtCantidad, 0, 400, Short.MAX_VALUE)
                .addComponent(txtValor, 0, 400, Short.MAX_VALUE)
                .addComponent(txtLote, 0, 400, Short.MAX_VALUE)
                .addComponent(txtProxima, 0, 400, Short.MAX_VALUE)
                .addComponent(txtDosis, 0, 400, Short.MAX_VALUE)
                .addComponent(txtVia, 0, 400, Short.MAX_VALUE)));
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblProxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtProxima, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtVia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)));
        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnAplicar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnListar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnNuevo, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnVolver, 0, 170, Short.MAX_VALUE)));
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createSequentialGroup()
            .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JLabel lblConsulta;
    private javax.swing.JComboBox<String> cmbConsulta;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JLabel lblValor;
    private javax.swing.JTextField txtValor;
    private javax.swing.JLabel lblLote;
    private javax.swing.JTextField txtLote;
    private javax.swing.JLabel lblProxima;
    private javax.swing.JTextField txtProxima;
    private javax.swing.JLabel lblDosis;
    private javax.swing.JTextField txtDosis;
    private javax.swing.JLabel lblVia;
    private javax.swing.JTextField txtVia;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JButton btnAplicar;
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
    private final java.util.ArrayList<String> idsConsulta = new java.util.ArrayList<>();
    private boolean actualizando;
}
