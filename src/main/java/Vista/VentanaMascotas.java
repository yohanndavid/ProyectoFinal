package Vista;

public class VentanaMascotas extends javax.swing.JFrame {

    public VentanaMascotas() {
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

    public String getPropietario() {
        int posicion = cmbPropietario.getSelectedIndex();
        if (posicion < 1 || posicion > idsPropietario.size()) {
            return "";
        }
        return idsPropietario.get(posicion - 1);
    }

    public void setPropietario(String id) {
        int posicion = idsPropietario.indexOf(id);
        cmbPropietario.setSelectedIndex(posicion + 1);
    }

    public void cargarPropietario(java.util.List<String> ids, java.util.List<String> nombres) {
        String anterior = getPropietario();
        idsPropietario.clear();
        idsPropietario.addAll(ids);
        cmbPropietario.removeAllItems();
        cmbPropietario.addItem("Seleccione...");
        for (String nombre : nombres) {
            cmbPropietario.addItem(nombre);
        }
        setPropietario(anterior);
    }

    public void habilitarPropietario(boolean habilitado) {
        cmbPropietario.setEnabled(habilitado);
    }

    public void alCambiarPropietario(java.awt.event.ActionListener listener) {
        cmbPropietario.setActionCommand("propietario");
        cmbPropietario.addActionListener(listener);
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

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public void setNombre(String valor) {
        txtNombre.setText(valor);
    }

    public void habilitarNombre(boolean habilitado) {
        txtNombre.setEditable(habilitado);
    }

    public String getEspecie() {
        return txtEspecie.getText().trim();
    }

    public void setEspecie(String valor) {
        txtEspecie.setText(valor);
    }

    public void habilitarEspecie(boolean habilitado) {
        txtEspecie.setEditable(habilitado);
    }

    public String getEdad() {
        return txtEdad.getText().trim();
    }

    public void setEdad(String valor) {
        txtEdad.setText(valor);
    }

    public void habilitarEdad(boolean habilitado) {
        txtEdad.setEditable(habilitado);
    }

    public String getPeso() {
        return txtPeso.getText().trim();
    }

    public void setPeso(String valor) {
        txtPeso.setText(valor);
    }

    public void habilitarPeso(boolean habilitado) {
        txtPeso.setEditable(habilitado);
    }

    public String getRaza() {
        return txtRaza.getText().trim();
    }

    public void setRaza(String valor) {
        txtRaza.setText(valor);
    }

    public void habilitarRaza(boolean habilitado) {
        txtRaza.setEditable(habilitado);
    }

    public String getEsquema() {
        return txtEsquema.getText().trim();
    }

    public void setEsquema(String valor) {
        txtEsquema.setText(valor);
    }

    public void habilitarEsquema(boolean habilitado) {
        txtEsquema.setEditable(habilitado);
    }

    public String getPermiso() {
        return txtPermiso.getText().trim();
    }

    public void setPermiso(String valor) {
        txtPermiso.setText(valor);
    }

    public void habilitarPermiso(boolean habilitado) {
        txtPermiso.setEditable(habilitado);
    }

    public String getSedacion() {
        return cmbSedacion.getSelectedItem().toString();
    }

    public void setSedacion(String valor) {
        cmbSedacion.setSelectedItem(valor);
    }

    public void habilitarSedacion(boolean habilitado) {
        cmbSedacion.setEnabled(habilitado);
    }

    public void alCambiarSedacion(java.awt.event.ActionListener listener) {
        cmbSedacion.setActionCommand("sedacion");
        cmbSedacion.addActionListener(listener);
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

    public void alPeso(java.awt.event.ActionListener listener) {
        btnPeso.addActionListener(listener);
    }

    public void habilitarBotonPeso(boolean habilitado) {
        btnPeso.setEnabled(habilitado);
    }

    public void alVacunas(java.awt.event.ActionListener listener) {
        btnVacunas.addActionListener(listener);
    }

    public void habilitarBotonVacunas(boolean habilitado) {
        btnVacunas.setEnabled(habilitado);
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
        lblTitulo.setText("Mascotas");
        lblAyuda = new javax.swing.JLabel();
        lblAyuda.setText("Seleccione propietario. Especie libre; edad en años (admite 0), peso en kg. Exóticas: permiso obligatorio.");
        panelDatos = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        lblId.setText("Identificador automático");
        txtId = new javax.swing.JTextField();
        txtId.setText("");
        txtId.setEditable(false);
        lblPropietario = new javax.swing.JLabel();
        lblPropietario.setText("Propietario");
        cmbPropietario = new javax.swing.JComboBox<>();
        cmbPropietario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione..."}));
        lblTipo = new javax.swing.JLabel();
        lblTipo.setText("Tipo de mascota");
        cmbTipo = new javax.swing.JComboBox<>();
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Convencional", "Exótica"}));
        lblNombre = new javax.swing.JLabel();
        lblNombre.setText("Nombre");
        txtNombre = new javax.swing.JTextField();
        txtNombre.setText("");
        lblEspecie = new javax.swing.JLabel();
        lblEspecie.setText("Especie");
        txtEspecie = new javax.swing.JTextField();
        txtEspecie.setText("");
        lblEdad = new javax.swing.JLabel();
        lblEdad.setText("Edad (años)");
        txtEdad = new javax.swing.JTextField();
        txtEdad.setText("");
        lblPeso = new javax.swing.JLabel();
        lblPeso.setText("Peso (kg)");
        txtPeso = new javax.swing.JTextField();
        txtPeso.setText("");
        lblRaza = new javax.swing.JLabel();
        lblRaza.setText("Raza (opcional)");
        txtRaza = new javax.swing.JTextField();
        txtRaza.setText("");
        lblEsquema = new javax.swing.JLabel();
        lblEsquema.setText("Esquema de vacunas (opcional)");
        txtEsquema = new javax.swing.JTextField();
        txtEsquema.setText("");
        lblPermiso = new javax.swing.JLabel();
        lblPermiso.setText("Permiso ICA / CITES");
        txtPermiso = new javax.swing.JTextField();
        txtPermiso.setText("");
        lblSedacion = new javax.swing.JLabel();
        lblSedacion.setText("Requiere sedación");
        cmbSedacion = new javax.swing.JComboBox<>();
        cmbSedacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"No", "Sí"}));
        panelAcciones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnRegistrar.setText("Registrar");
        btnRegistrar.setActionCommand("registrar");
        btnBuscar = new javax.swing.JButton();
        btnBuscar.setText("Ver seleccionado");
        btnBuscar.setActionCommand("buscar");
        btnPeso = new javax.swing.JButton();
        btnPeso.setText("Actualizar peso");
        btnPeso.setActionCommand("peso");
        btnVacunas = new javax.swing.JButton();
        btnVacunas.setText("Actualizar vacunas");
        btnVacunas.setActionCommand("vacunas");
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
            new Object[][]{}, new String[]{"ID", "Nombre", "Especie", "Propietario", "Peso kg"}) {
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
        setTitle("Mascotas");
        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblEsquema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSedacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(16)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtId, 0, 400, Short.MAX_VALUE)
                .addComponent(cmbPropietario, 0, 400, Short.MAX_VALUE)
                .addComponent(cmbTipo, 0, 400, Short.MAX_VALUE)
                .addComponent(txtNombre, 0, 400, Short.MAX_VALUE)
                .addComponent(txtEspecie, 0, 400, Short.MAX_VALUE)
                .addComponent(txtEdad, 0, 400, Short.MAX_VALUE)
                .addComponent(txtPeso, 0, 400, Short.MAX_VALUE)
                .addComponent(txtRaza, 0, 400, Short.MAX_VALUE)
                .addComponent(txtEsquema, 0, 400, Short.MAX_VALUE)
                .addComponent(txtPermiso, 0, 400, Short.MAX_VALUE)
                .addComponent(cmbSedacion, 0, 400, Short.MAX_VALUE)));
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createSequentialGroup()
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblEsquema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtEsquema, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(5)
            .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblSedacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbSedacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)));
        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addComponent(btnRegistrar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnBuscar, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnPeso, 0, 170, Short.MAX_VALUE)
                .addGap(8)
                .addComponent(btnVacunas, 0, 170, Short.MAX_VALUE))
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
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnVacunas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JLabel lblPropietario;
    private javax.swing.JComboBox<String> cmbPropietario;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JTextField txtEspecie;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JLabel lblRaza;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JLabel lblEsquema;
    private javax.swing.JTextField txtEsquema;
    private javax.swing.JLabel lblPermiso;
    private javax.swing.JTextField txtPermiso;
    private javax.swing.JLabel lblSedacion;
    private javax.swing.JComboBox<String> cmbSedacion;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnPeso;
    private javax.swing.JButton btnVacunas;
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
    private final java.util.ArrayList<String> idsPropietario = new java.util.ArrayList<>();
    private boolean actualizando;
}
