/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;



/**
 *
 * @author Usuario
 */
public class VistaEditarUsuario extends javax.swing.JFrame {
      // Atributo para guardar la referencia al controlador
    private Controlador.ControladorEditarUsuario controladorEditar;
      // Atributo para guardar el usuario actual
    private Modelo.UsuarioModelo usuarioActual;
    
    /**
     * Creates new form VistaEditarUsuario
     */
    public VistaEditarUsuario(Modelo.UsuarioModelo usuario) {
        initComponents();
  this.usuarioActual = usuario;
    
    // ================== CONFIGURACIÓN BÁSICA ==================
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    getContentPane().setBackground(new Color(70, 130, 180));
    
    // ================== CREAR NUEVO PANEL CON LAYOUT CORRECTO ==================
    JPanel panelFormulario = new JPanel(new GridBagLayout());
    panelFormulario.setBackground(new Color(70, 130, 180));
    panelFormulario.setOpaque(true);
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(15, 15, 15, 15);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    
    // ================== TÍTULO ==================
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    lblTitulo.setText("EDITAR USUARIO");
    lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 80));
    lblTitulo.setForeground(new Color(0, 102, 102));
    lblTitulo.setHorizontalAlignment(JLabel.CENTER);
    panelFormulario.add(lblTitulo, gbc);
    
    // ================== ID USUARIO ==================
    gbc.gridwidth = 1;
    gbc.gridy = 1;
    gbc.gridx = 0;
    lblIdUsuario.setFont(new Font("Arial Black", Font.BOLD, 40));
    lblIdUsuario.setForeground(Color.WHITE);
    panelFormulario.add(lblIdUsuario, gbc);
    
    gbc.gridx = 1;
    txtIdUsuario.setFont(new Font("Arial", Font.PLAIN, 36));
    txtIdUsuario.setHorizontalAlignment(JTextField.CENTER);
    txtIdUsuario.setEditable(false);
    txtIdUsuario.setPreferredSize(new Dimension(400, 60));
    panelFormulario.add(txtIdUsuario, gbc);
    
    // ================== NOMBRE ==================
    gbc.gridx = 0;
    gbc.gridy = 2;
    lblNombre.setFont(new Font("Arial Black", Font.BOLD, 40));
    lblNombre.setForeground(Color.WHITE);
    panelFormulario.add(lblNombre, gbc);
    
    gbc.gridx = 1;
    txtNombre.setFont(new Font("Arial", Font.PLAIN, 36));
    txtNombre.setHorizontalAlignment(JTextField.CENTER);
    txtNombre.setPreferredSize(new Dimension(400, 60));
    panelFormulario.add(txtNombre, gbc);
    
    // ================== CÉDULA ==================
    gbc.gridx = 0;
    gbc.gridy = 3;
    lblCedula.setFont(new Font("Arial Black", Font.BOLD, 40));
    lblCedula.setForeground(Color.WHITE);
    panelFormulario.add(lblCedula, gbc);
    
    gbc.gridx = 1;
    txtCedula.setFont(new Font("Arial", Font.PLAIN, 36));
    txtCedula.setHorizontalAlignment(JTextField.CENTER);
    txtCedula.setPreferredSize(new Dimension(400, 60));
    panelFormulario.add(txtCedula, gbc);
    
    // ================== DIRECCIÓN ==================
    gbc.gridx = 0;
    gbc.gridy = 4;
    lblDireccion.setFont(new Font("Arial Black", Font.BOLD, 40));
    lblDireccion.setForeground(Color.WHITE);
    panelFormulario.add(lblDireccion, gbc);
    
    gbc.gridx = 1;
    txtDireccion.setFont(new Font("Arial", Font.PLAIN, 36));
    txtDireccion.setHorizontalAlignment(JTextField.CENTER);
    txtDireccion.setPreferredSize(new Dimension(400, 60));
    panelFormulario.add(txtDireccion, gbc);
    
    // ================== EDAD ==================
    gbc.gridx = 0;
    gbc.gridy = 5;
    lblEdad.setFont(new Font("Arial Black", Font.BOLD, 40));
    lblEdad.setForeground(Color.WHITE);
    panelFormulario.add(lblEdad, gbc);
    
    gbc.gridx = 1;
    txtEdad.setFont(new Font("Arial", Font.PLAIN, 36));
    txtEdad.setHorizontalAlignment(JTextField.CENTER);
    txtEdad.setPreferredSize(new Dimension(400, 60));
    panelFormulario.add(txtEdad, gbc);
    
    // ================== GÉNERO ==================
    gbc.gridx = 0;
    gbc.gridy = 6;
    lblGenero.setFont(new Font("Arial Black", Font.BOLD, 40));
    lblGenero.setForeground(Color.WHITE);
    panelFormulario.add(lblGenero, gbc);
    
    JPanel panelGenero = new JPanel();
    panelGenero.setBackground(new Color(70, 130, 180));
    Font fontRadio = new Font("Arial Black", Font.BOLD, 32);
    
    RadioMasculino.setFont(fontRadio);
    RadioMasculino.setForeground(Color.WHITE);
    RadioMasculino.setOpaque(false);
    RadioMasculino.setContentAreaFilled(false);
    panelGenero.add(RadioMasculino);
    
    RadioFemenino.setFont(fontRadio);
    RadioFemenino.setForeground(Color.WHITE);
    RadioFemenino.setOpaque(false);
    RadioFemenino.setContentAreaFilled(false);
    panelGenero.add(RadioFemenino);
    
    // ================== AGRUPAR RADIO BUTTONS PARA GÉNERO ==================
    ButtonGroup grupoGenero = new ButtonGroup();
    grupoGenero.add(RadioMasculino);
    grupoGenero.add(RadioFemenino);

    // ================== AGRUPAR RADIO BUTTONS PARA ROL ==================
    ButtonGroup grupoRol = new ButtonGroup();
    grupoRol.add(RadioAdministrador);
    grupoRol.add(RadioCajero);
    
    gbc.gridx = 1;
    panelFormulario.add(panelGenero, gbc);
    
    // ================== ROL ==================
    gbc.gridx = 0;
    gbc.gridy = 7;
    lblRol.setFont(new Font("Arial Black", Font.BOLD, 40));
    lblRol.setForeground(Color.WHITE);
    panelFormulario.add(lblRol, gbc);
    
    JPanel panelRol = new JPanel();
    panelRol.setBackground(new Color(70, 130, 180));
    
    RadioAdministrador.setFont(fontRadio);
    RadioAdministrador.setForeground(Color.WHITE);
    RadioAdministrador.setOpaque(false);
    RadioAdministrador.setContentAreaFilled(false);
    panelRol.add(RadioAdministrador);
    
    RadioCajero.setFont(fontRadio);
    RadioCajero.setForeground(Color.WHITE);
    RadioCajero.setOpaque(false);
    RadioCajero.setContentAreaFilled(false);
    panelRol.add(RadioCajero);
    
    gbc.gridx = 1;
    panelFormulario.add(panelRol, gbc);
    
    // ================== BOTONES Y ESTADO ==================
    gbc.gridx = 0;
    gbc.gridy = 8;
    gbc.gridwidth = 2;
    
    JPanel panelBotones = new JPanel();
    panelBotones.setBackground(new Color(70, 130, 180));
    
    Font fontBoton = new Font("Arial Black", Font.BOLD, 28);
    
    btnGuardar.setText("GUARDAR CAMBIOS");
    btnGuardar.setFont(fontBoton);
    btnGuardar.setBackground(new Color(46, 204, 113));
    btnGuardar.setForeground(Color.WHITE);
    btnGuardar.setFocusPainted(false);
    btnGuardar.setBorderPainted(false);
    btnGuardar.setPreferredSize(new Dimension(400, 60));
    panelBotones.add(btnGuardar);
    
    btnCancelar.setText("CANCELAR");
    btnCancelar.setFont(fontBoton);
    btnCancelar.setBackground(new Color(231, 76, 60));
    btnCancelar.setForeground(Color.WHITE);
    btnCancelar.setFocusPainted(false);
    btnCancelar.setBorderPainted(false);
    btnCancelar.setPreferredSize(new Dimension(280, 60));
    panelBotones.add(btnCancelar);
    
    lblEstado.setFont(new Font("Arial Black", Font.BOLD, 36));
    lblEstado.setForeground(Color.CYAN);
    panelBotones.add(lblEstado);
    
    panelFormulario.add(panelBotones, gbc);
    
    // ================== CENTRAR TODO EN LA PANTALLA CON SCROLL ==================
    JPanel panelCentral = new JPanel(new GridBagLayout());
    panelCentral.setBackground(new Color(70, 130, 180));
    GridBagConstraints gbcCentral = new GridBagConstraints();
    gbcCentral.anchor = GridBagConstraints.NORTH;
    gbcCentral.weighty = 0.1;
    panelCentral.add(panelFormulario, gbcCentral);
    
    JScrollPane scrollPane = new JScrollPane(panelCentral);
    scrollPane.setBackground(new Color(70, 130, 180));
    scrollPane.getViewport().setBackground(new Color(70, 130, 180));
    scrollPane.setBorder(null);
    setContentPane(scrollPane);
    
    // ================== CARGAR DATOS ==================
    cargarDatos();
    
    // ================== REFRESCAR ==================
   
    revalidate();
    repaint();
    }
    
    
    
     // Método para inyectar el controlador desde fuera
       public void establecerControlador(Controlador.ControladorEditarUsuario controlador) {
        this.controladorEditar = controlador;
        
        
        btnCancelar.addActionListener(e -> controladorEditar.cancelarEdicion());

       }

    // Mensajes flotantes
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    // Método para cargar los datos del usuario en la interfaz
        private void cargarDatos() {
        txtIdUsuario.setText(String.valueOf(usuarioActual.getIdUsuario()));
        txtNombre.setText(usuarioActual.getNombreUsuario());
        txtCedula.setText(usuarioActual.getCedula() != null ? usuarioActual.getCedula() : "");
        txtDireccion.setText(usuarioActual.getDireccion() != null ? usuarioActual.getDireccion() : "");
        txtEdad.setText(usuarioActual.getEdad() > 0 ? String.valueOf(usuarioActual.getEdad()) : "");

        // Género
        if ("Masculino".equals(usuarioActual.getGenero())) {
            RadioMasculino.setSelected(true);
        } else if ("Femenino".equals(usuarioActual.getGenero())) {
            RadioFemenino.setSelected(true);
        }

        // Rol
        if ("administrador".equals(usuarioActual.getRol())) {
            RadioAdministrador.setSelected(true);
        } else {
            RadioCajero.setSelected(true);
        }

        // Estado
        String estado = usuarioActual.isActivo() ? "Activo" : "Inactivo";
        lblEstado.setText("Estado: " + estado);
        lblEstado.setForeground(usuarioActual.isActivo() ? Color.CYAN : Color.RED);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        RadioMasculino = new javax.swing.JRadioButton();
        btnGuardar = new javax.swing.JButton();
        txtIdUsuario = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        RadioCajero = new javax.swing.JRadioButton();
        lblEstado = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        RadioAdministrador = new javax.swing.JRadioButton();
        RadioFemenino = new javax.swing.JRadioButton();
        lblIdUsuario = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCancelar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 102, 102));
        btnCancelar.setText("Cancelar");

        txtNombre.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 102, 102));

        lblNombre.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(0, 102, 102));
        lblNombre.setText("Nombre");

        RadioMasculino.setText("Masculino");

        btnGuardar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 102, 102));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtIdUsuario.setEditable(false);
        txtIdUsuario.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        txtIdUsuario.setForeground(new java.awt.Color(0, 102, 102));
        txtIdUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdUsuarioActionPerformed(evt);
            }
        });

        lblDireccion.setText("Dirección: ");

        lblEdad.setText("Edad:");

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Editar Usuario");

        RadioCajero.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        RadioCajero.setForeground(new java.awt.Color(0, 102, 102));
        RadioCajero.setText("Cajero");

        lblEstado.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(0, 102, 102));
        lblEstado.setText("Estado: ");

        lblCedula.setText("Cédula:");

        lblRol.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblRol.setForeground(new java.awt.Color(0, 102, 102));
        lblRol.setText("Rol");

        RadioAdministrador.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        RadioAdministrador.setForeground(new java.awt.Color(0, 102, 102));
        RadioAdministrador.setText("Administrador");

        RadioFemenino.setText("Femenino");

        lblIdUsuario.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblIdUsuario.setForeground(new java.awt.Color(0, 102, 102));
        lblIdUsuario.setText("ID Usuario");

        lblGenero.setText("Género");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdUsuario)
                            .addComponent(lblNombre)
                            .addComponent(lblCedula)
                            .addComponent(lblDireccion)
                            .addComponent(lblEdad)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblRol)
                                    .addComponent(lblGenero)
                                    .addComponent(btnGuardar))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RadioAdministrador)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(RadioCajero)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTitulo)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(RadioFemenino))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(btnCancelar))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(RadioMasculino)))
                .addContainerGap(354, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblIdUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCedula)
                        .addGap(9, 9, 9)
                        .addComponent(lblDireccion)
                        .addGap(12, 12, 12)
                        .addComponent(lblEdad)
                        .addGap(24, 24, 24)
                        .addComponent(lblGenero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRol))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lblTitulo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RadioFemenino, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(RadioMasculino))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RadioCajero)
                            .addComponent(RadioAdministrador))))
                .addGap(12, 12, 12)
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdUsuarioActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
   // ================== RECOGER Y LIMPIAR DATOS ==================
    String nuevoNombre = txtNombre.getText().trim();
    String nuevaCedula = txtCedula.getText().trim();
    String nuevaDireccion = txtDireccion.getText().trim();
    String edadStr = txtEdad.getText().trim();

    // ================== VALIDACIÓN DE NOMBRE DE USUARIO ==================
    if (nuevoNombre.isEmpty()) {
        mostrarMensajeError("El nombre de usuario es obligatorio.");
        return;
    }
    if (nuevoNombre.length() < 4) {
        mostrarMensajeError("El nombre de usuario debe tener al menos 4 caracteres.");
        return;
    }
    if (nuevoNombre.contains(" ")) {
        mostrarMensajeError("El nombre de usuario no puede contener espacios.");
        return;
    }

    // ================== VALIDACIÓN DE CÉDULA (LA MÁS IMPORTANTE) ==================
    if (nuevaCedula.isEmpty()) {
        mostrarMensajeError("La cédula es obligatoria.");
        return;
    }
    if (nuevaCedula.length() != 10) {
        mostrarMensajeError("La cédula debe tener exactamente 10 dígitos.");
        return;
    }
    if (!nuevaCedula.matches("\\d{10}")) {
        mostrarMensajeError("La cédula solo debe contener números.");
        return;
    }
    if (!Modelo.UsuarioModelo.validarCedulaEcuatoriana(nuevaCedula)) {
        mostrarMensajeError("La cédula ingresada no es válida según el algoritmo ecuatoriano.");
        return;
    }

    // ================== VALIDACIÓN DE DIRECCIÓN ==================
    if (nuevaDireccion.isEmpty()) {
        mostrarMensajeError("La dirección es obligatoria.");
        return;
    }
    if (nuevaDireccion.length() < 5) {
        mostrarMensajeError("La dirección debe tener al menos 5 caracteres.");
        return;
    }

    // ================== VALIDACIÓN DE EDAD ==================
    if (edadStr.isEmpty()) {
        mostrarMensajeError("La edad es obligatoria.");
        return;
    }
    int nuevaEdad;
    try {
        nuevaEdad = Integer.parseInt(edadStr);
        if (nuevaEdad < 16 || nuevaEdad > 120) {
            mostrarMensajeError("La edad debe estar entre 16 y 120 años.");
            return;
        }
    } catch (NumberFormatException ex) {
        mostrarMensajeError("La edad debe ser un número válido.");
        return;
    }

    // ================== VALIDACIÓN DE GÉNERO ==================
    if (!RadioMasculino.isSelected() && !RadioFemenino.isSelected()) {
        mostrarMensajeError("Por favor seleccione un género.");
        return;
    }
    String nuevoGenero = RadioMasculino.isSelected() ? "Masculino" : "Femenino";

    // ================== VALIDACIÓN DE ROL ==================
    if (!RadioAdministrador.isSelected() && !RadioCajero.isSelected()) {
        mostrarMensajeError("Por favor seleccione un rol.");
        return;
    }
    String nuevoRol = RadioAdministrador.isSelected() ? "administrador" : "cajero";

    // ================== SI TODAS LAS VALIDACIONES PASAN → ACTUALIZAR Y GUARDAR ==================
    usuarioActual.setNombreUsuario(nuevoNombre);
    usuarioActual.setCedula(nuevaCedula);
    usuarioActual.setDireccion(nuevaDireccion);
    usuarioActual.setEdad(nuevaEdad);
    usuarioActual.setGenero(nuevoGenero);
    usuarioActual.setRol(nuevoRol);

    // Solo ahora llamamos al controlador
    controladorEditar.guardarCambios(usuarioActual);
    }//GEN-LAST:event_btnGuardarActionPerformed

 
   // Variable para el grupo de género
   // Grupo lógico para Género (no es un componente visible)
    private ButtonGroup grupoGenero;
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioAdministrador;
    private javax.swing.JRadioButton RadioCajero;
    private javax.swing.JRadioButton RadioFemenino;
    private javax.swing.JRadioButton RadioMasculino;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblIdUsuario;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
