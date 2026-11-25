package Vista;

public class VentanaConsultas extends javax.swing.JFrame {

    public VentanaConsultas() {
        initComponents();
        conectarFiltro();
    }

    public String getId() {
        return txtId.getText().trim();
    }

    public void setId(String valor) {
        txtId.setText(valor);
    }

    public void habilitarId(boolean habilitado) {
        txtId.setEditable(habilitado);
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

    public String getVeterinario() {
        int posicion = cmbVeterinario.getSelectedIndex();
        if (posicion < 1 || posicion > idsVeterinario.size()) {
            return "";
        }
        return idsVeterinario.get(posicion - 1);
    }

    public void setVeterinario(String id) {
        int posicion = idsVeterinario.indexOf(id);
        cmbVeterinario.setSelectedIndex(posicion + 1);
    }

    public void cargarVeterinario(java.util.List<String> ids, java.util.List<String> nombres) {
        String anterior = getVeterinario();
        idsVeterinario.clear();
        idsVeterinario.addAll(ids);
        cmbVeterinario.removeAllItems();
        cmbVeterinario.addItem("Seleccione...");
        for (String nombre : nombres) {
            cmbVeterinario.addItem(nombre);
        }
        setVeterinario(anterior);
    }

    public void habilitarVeterinario(boolean habilitado) {
        cmbVeterinario.setEnabled(habilitado);
    }

    public void alCambiarVeterinario(java.awt.event.ActionListener listener) {
        cmbVeterinario.setActionCommand("veterinario");
        cmbVeterinario.addActionListener(listener);
    }

    public String getCita() {
        int posicion = cmbCita.getSelectedIndex();
        if (posicion < 1 || posicion > idsCita.size()) {
            return "";
        }
        return idsCita.get(posicion - 1);
    }

    public void setCita(String id) {
        int posicion = idsCita.indexOf(id);
        cmbCita.setSelectedIndex(posicion + 1);
    }

    public void cargarCita(java.util.List<String> ids, java.util.List<String> nombres) {
        String anterior = getCita();
        idsCita.clear();
        idsCita.addAll(ids);
        cmbCita.removeAllItems();
        cmbCita.addItem("Sin cita / atención directa");
        for (String nombre : nombres) {
            cmbCita.addItem(nombre);
        }
        setCita(anterior);
    }

    public void habilitarCita(boolean habilitado) {
        cmbCita.setEnabled(habilitado);
    }

    public void alCambiarCita(java.awt.event.ActionListener listener) {
        cmbCita.setActionCommand("cita");
        cmbCita.addActionListener(listener);
    }

    public String getFecha() {
        return txtFecha.getText().trim();
    }

    public void setFecha(String valor) {
        txtFecha.setText(valor);
    }

    public void habilitarFecha(boolean habilitado) {
        txtFecha.setEditable(habilitado);
    }

    public String getDiagnostico() {
        return txtDiagnostico.getText().trim();
    }

    public void setDiagnostico(String valor) {
        txtDiagnostico.setText(valor);
    }

    public void habilitarDiagnostico(boolean habilitado) {
        txtDiagnostico.setEditable(habilitado);
    }

    public String getTratamientos() {
        return txtTratamientos.getText().trim();
    }

    public void setTratamientos(String valor) {
        txtTratamientos.setText(valor);
    }

    public void habilitarTratamientos(boolean habilitado) {
        txtTratamientos.setEditable(habilitado);
    }

    public String getNotas() {
        return txtNotas.getText().trim();
    }

    public void setNotas(String valor) {
        txtNotas.setText(valor);
    }

    public void habilitarNotas(boolean habilitado) {
        txtNotas.setEditable(habilitado);
    }

    public void alRegistrar(java.awt.event.ActionListener listener) {
        btnRegistrar.addActionListener(listener);
    }

    public void habilitarBotonRegistrar(boolean habilitado) {
        btnRegistrar.setEnabled(habilitado);
    }

    public void alGuardar(java.awt.event.ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void habilitarBotonGuardar(boolean habilitado) {
        btnGuardar.setEnabled(habilitado);
    }

    public void alBuscar(java.awt.event.ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }

    public void habilitarBotonBuscar(boolean habilitado) {
        btnBuscar.setEnabled(habilitado);
    }

    public void alAhora(java.awt.event.ActionListener listener) {
        btnAhora.addActionListener(listener);
    }

    public void habilitarBotonAhora(boolean habilitado) {
        btnAhora.setEnabled(habilitado);
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
        lblTitulo.setText("Atención médica");
        lblAyuda = new javax.swing.JLabel();
        lblAyuda.setText("La cita es opcional. Puede completar el diagnóstico después; debe hacerlo antes de emitir el cobro.");
        panelDatos = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        lblId.setText("Identificador automático");
        txtId = new javax.swing.JTextField();
        txtId.setText("");
        txtId.setEditable(false);
        lblMascota = new javax.swing.JLabel();
        lblMascota.setText("Mascota");
        cmbMascota = new javax.swing.JComboBox<>();
        cmbMascota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione..."}));
        lblVeterinario = new javax.swing.JLabel();
        lblVeterinario.setText("Veterinario");
        cmbVeterinario = new javax.swing.JComboBox<>();
        cmbVeterinario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione..."}));
        lblCita = new javax.swing.JLabel();
        lblCita.setText("Cita (opcional)");
        cmbCita = new javax.swing.JComboBox<>();
        cmbCita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione..."}));
        lblFecha = new javax.swing.JLabel();
        lblFecha.setText("Fecha: dd/MM/aaaa HH:mm");
        txtFecha = new javax.swing.JTextField();
        txtFecha.setText("");
        lblDiagnostico = new javax.swing.JLabel();
        lblDiagnostico.setText("Diagnóstico");
        txtDiagnostico = new javax.swing.JTextField();
        txtDiagnostico.setText("");
        lblTratamientos = new javax.swing.JLabel();
        lblTratamientos.setText("Tratamientos");
        txtTratamientos = new javax.swing.JTextField();
        txtTratamientos.setText("");
        lblNotas = new javax.swing.JLabel();
        lblNotas.setText("Notas médicas");
        txtNotas = new javax.swing.JTextField();
        txtNotas.setText("");
        panelAcciones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnRegistrar.setText("Iniciar atención");
        btnRegistrar.setActionCommand("registrar");
        btnGuardar = new javax.swing.JButton();
        btnGuardar.setText("Guardar datos clínicos");
        btnGuardar.setActionCommand("guardar");
        btnBuscar = new javax.swing.JButton();
        btnBuscar.setText("Ver seleccionado");
        btnBuscar.setActionCommand("buscar");
        btnAhora = new javax.swing.JButton();
        btnAhora.setText("Fecha actual");
        btnAhora.setActionCommand("ahora");
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
            new Object[][]{}, new String[]{"ID", "Mascota", "Veterinario", "Fecha", "Estado"}) {
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
        setTitle("Atención médica");
        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTratamientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(16)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtId, 0, 400, Short.MAX_VALUE)
                .addComponent(cmbMascota, 0, 400, Short.MAX_VALUE)
                .addComponent(cmbVeterinario, 0, 400, Short.MAX_VALUE)
                .addComponent(cmbCita, 0, 400, Short.MAX_VALUE)
                .addComponent(txtFecha, 0, 400, Short.MAX_VALUE)
                .addComponent(txtDiagnostico, 0, 400, Short.MAX_VALUE)
                .addComponent(txtTratamientos, 0, 400, Short.MAX_VALUE)
                .addComponent(txtNotas, 0, 400, Short.MAX_VALUE)));
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbCita, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTratamientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtTratamientos, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblNotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)));
        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnRegistrar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnGuardar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnBuscar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnAhora, 0, 170, Short.MAX_VALUE))
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnListar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnNuevo, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnVolver, 0, 170, Short.MAX_VALUE)));
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createSequentialGroup()
            .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnAhora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel lblMascota;
    private javax.swing.JComboBox<String> cmbMascota;
    private javax.swing.JLabel lblVeterinario;
    private javax.swing.JComboBox<String> cmbVeterinario;
    private javax.swing.JLabel lblCita;
    private javax.swing.JComboBox<String> cmbCita;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JLabel lblDiagnostico;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JLabel lblTratamientos;
    private javax.swing.JTextField txtTratamientos;
    private javax.swing.JLabel lblNotas;
    private javax.swing.JTextField txtNotas;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnAhora;
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
    private final java.util.ArrayList<String> idsCita = new java.util.ArrayList<>();
    private final java.util.ArrayList<String> idsVeterinario = new java.util.ArrayList<>();
    private final java.util.ArrayList<String> idsMascota = new java.util.ArrayList<>();
    private boolean actualizando;
}
