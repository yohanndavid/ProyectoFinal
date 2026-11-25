package Vista;

public class VentanaCobros extends javax.swing.JFrame {

    public VentanaCobros() {
        initComponents();
        conectarFiltro();
    }

    public String getNumero() {
        return txtNumero.getText().trim();
    }

    public void setNumero(String valor) {
        txtNumero.setText(valor);
    }

    public void habilitarNumero(boolean habilitado) {
        txtNumero.setEditable(habilitado);
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

    public String getRecepcionista() {
        int posicion = cmbRecepcionista.getSelectedIndex();
        if (posicion < 1 || posicion > idsRecepcionista.size()) {
            return "";
        }
        return idsRecepcionista.get(posicion - 1);
    }

    public void setRecepcionista(String id) {
        int posicion = idsRecepcionista.indexOf(id);
        cmbRecepcionista.setSelectedIndex(posicion + 1);
    }

    public void cargarRecepcionista(java.util.List<String> ids, java.util.List<String> nombres) {
        String anterior = getRecepcionista();
        idsRecepcionista.clear();
        idsRecepcionista.addAll(ids);
        cmbRecepcionista.removeAllItems();
        cmbRecepcionista.addItem("Seleccione...");
        for (String nombre : nombres) {
            cmbRecepcionista.addItem(nombre);
        }
        setRecepcionista(anterior);
    }

    public void habilitarRecepcionista(boolean habilitado) {
        cmbRecepcionista.setEnabled(habilitado);
    }

    public void alCambiarRecepcionista(java.awt.event.ActionListener listener) {
        cmbRecepcionista.setActionCommand("recepcionista");
        cmbRecepcionista.addActionListener(listener);
    }

    public String getMetodo() {
        return cmbMetodo.getSelectedItem().toString();
    }

    public void setMetodo(String valor) {
        cmbMetodo.setSelectedItem(valor);
    }

    public void habilitarMetodo(boolean habilitado) {
        cmbMetodo.setEnabled(habilitado);
    }

    public void alCambiarMetodo(java.awt.event.ActionListener listener) {
        cmbMetodo.setActionCommand("metodo");
        cmbMetodo.addActionListener(listener);
    }

    public void alPrever(java.awt.event.ActionListener listener) {
        btnPrever.addActionListener(listener);
    }

    public void habilitarBotonPrever(boolean habilitado) {
        btnPrever.setEnabled(habilitado);
    }

    public void alGenerar(java.awt.event.ActionListener listener) {
        btnGenerar.addActionListener(listener);
    }

    public void habilitarBotonGenerar(boolean habilitado) {
        btnGenerar.setEnabled(habilitado);
    }

    public void alPagar(java.awt.event.ActionListener listener) {
        btnPagar.addActionListener(listener);
    }

    public void habilitarBotonPagar(boolean habilitado) {
        btnPagar.setEnabled(habilitado);
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

    public void mostrarTotales(String subtotal, String descuento, String total, String estado) {
        lblSubtotal.setText("Subtotal: " + subtotal);
        lblDescuento.setText("Descuento: " + descuento);
        lblTotal.setText("Total: " + total);
        lblPago.setText(estado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblTitulo = new javax.swing.JLabel();
        lblTitulo.setText("Caja y comprobantes");
        lblAyuda = new javax.swing.JLabel();
        lblAyuda.setText("Vista previa no cierra la consulta. Emitir congela los valores; registrar pago confirma el dinero recibido.");
        panelDatos = new javax.swing.JPanel();
        lblNumero = new javax.swing.JLabel();
        lblNumero.setText("Número automático de cobro");
        txtNumero = new javax.swing.JTextField();
        txtNumero.setText("");
        txtNumero.setEditable(false);
        lblConsulta = new javax.swing.JLabel();
        lblConsulta.setText("Consulta");
        cmbConsulta = new javax.swing.JComboBox<>();
        cmbConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione..."}));
        lblRecepcionista = new javax.swing.JLabel();
        lblRecepcionista.setText("Recepcionista");
        cmbRecepcionista = new javax.swing.JComboBox<>();
        cmbRecepcionista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione..."}));
        lblMetodo = new javax.swing.JLabel();
        lblMetodo.setText("Método de pago");
        cmbMetodo = new javax.swing.JComboBox<>();
        cmbMetodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Efectivo", "Tarjeta", "Transferencia"}));
        panelAcciones = new javax.swing.JPanel();
        btnPrever = new javax.swing.JButton();
        btnPrever.setText("Calcular / vista previa");
        btnPrever.setActionCommand("prever");
        btnGenerar = new javax.swing.JButton();
        btnGenerar.setText("Emitir cobro");
        btnGenerar.setActionCommand("generar");
        btnPagar = new javax.swing.JButton();
        btnPagar.setText("Registrar pago");
        btnPagar.setActionCommand("pagar");
        btnConsultar = new javax.swing.JButton();
        btnConsultar.setText("Ver comprobante");
        btnConsultar.setActionCommand("consultar");
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
            new Object[][]{}, new String[]{"Número", "Consulta", "Paciente", "Total", "Estado"}) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        });
        tablaRegistros.setAutoCreateRowSorter(true);
        tablaRegistros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollListado.setViewportView(tablaRegistros);
        panelTotales = new javax.swing.JPanel();
        lblSubtotal = new javax.swing.JLabel();
        lblSubtotal.setText("Subtotal: —");
        lblDescuento = new javax.swing.JLabel();
        lblDescuento.setText("Descuento: —");
        lblTotal = new javax.swing.JLabel();
        lblTotal.setText("Total: —");
        lblPago = new javax.swing.JLabel();
        lblPago.setText("SIN COBRO EMITIDO");
        scrollResultado = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        scrollResultado.setViewportView(txtResultado);
        lblEstado = new javax.swing.JLabel();
        lblEstado.setText("Listo.");
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Caja y comprobantes");
        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblRecepcionista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(16)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtNumero, 0, 400, Short.MAX_VALUE)
                .addComponent(cmbConsulta, 0, 400, Short.MAX_VALUE)
                .addComponent(cmbRecepcionista, 0, 400, Short.MAX_VALUE)
                .addComponent(cmbMetodo, 0, 400, Short.MAX_VALUE)));
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblRecepcionista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbRecepcionista, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)));
        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnPrever, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnGenerar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnPagar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnConsultar, 0, 170, Short.MAX_VALUE))
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnListar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnNuevo, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnVolver, 0, 170, Short.MAX_VALUE)));
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createSequentialGroup()
            .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnPrever, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
        javax.swing.GroupLayout panelTotalesLayout = new javax.swing.GroupLayout(panelTotales);
        panelTotales.setLayout(panelTotalesLayout);
        panelTotalesLayout.setHorizontalGroup(
            panelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalesLayout.createSequentialGroup()
                .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25)
                .addComponent(lblDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
        panelTotalesLayout.setVerticalGroup(
            panelTotalesLayout.createSequentialGroup()
            .addGroup(panelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(4)
            .addComponent(lblPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
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
                .addComponent(panelTotales, 0, 960, Short.MAX_VALUE)
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
            .addComponent(panelTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JLabel lblNumero;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JLabel lblConsulta;
    private javax.swing.JComboBox<String> cmbConsulta;
    private javax.swing.JLabel lblRecepcionista;
    private javax.swing.JComboBox<String> cmbRecepcionista;
    private javax.swing.JLabel lblMetodo;
    private javax.swing.JComboBox<String> cmbMetodo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JButton btnPrever;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel panelListado;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JScrollPane scrollListado;
    private javax.swing.JTable tablaRegistros;
    private javax.swing.JPanel panelTotales;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblPago;
    private javax.swing.JScrollPane scrollResultado;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JLabel lblEstado;
    // End of variables declaration//GEN-END:variables
    private final java.util.ArrayList<String> idsRecepcionista = new java.util.ArrayList<>();
    private final java.util.ArrayList<String> idsConsulta = new java.util.ArrayList<>();
    private boolean actualizando;
}
