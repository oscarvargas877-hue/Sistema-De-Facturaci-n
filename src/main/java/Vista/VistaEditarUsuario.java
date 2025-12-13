/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        cargarDatos();
        // Crear un grupo de botones para que solo se pueda seleccionar uno
        javax.swing.ButtonGroup grupoRol = new javax.swing.ButtonGroup();
        grupoRol.add(RadioAdministrador);
        grupoRol.add(RadioCajero);
        

        // PANTALLA COMPLETA
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Fondo profesional
        getContentPane().setBackground(new Color(52, 73, 94));

        // Panel central para centrar todo
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setOpaque(false);

        //  CONSTRAINTS 

        // Título
        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridwidth = GridBagConstraints.REMAINDER;
        gbcTitulo.anchor = GridBagConstraints.CENTER;
        gbcTitulo.insets = new Insets(30, 0, 40, 0);
        gbcTitulo.fill = GridBagConstraints.HORIZONTAL;
        gbcTitulo.weightx = 1.0;
        gbcTitulo.weighty = 0;

        // Etiquetas izquierda
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.anchor = GridBagConstraints.EAST;
        gbcLabel.insets = new Insets(15, 50, 15, 20);
        gbcLabel.weightx = 0.0;
        gbcLabel.fill = GridBagConstraints.NONE;

        // Campos de texto derecha, ancho controlado
        GridBagConstraints gbcField = new GridBagConstraints();
        gbcField.gridwidth = GridBagConstraints.REMAINDER;
        gbcField.anchor = GridBagConstraints.WEST;
        gbcField.insets = new Insets(15, 0, 15, 100);
        gbcField.weightx = 0.0;
        gbcField.fill = GridBagConstraints.NONE;

        // Panel de rol centrado
        GridBagConstraints gbcRol = new GridBagConstraints();
        gbcRol.gridwidth = GridBagConstraints.REMAINDER;
        gbcRol.anchor = GridBagConstraints.CENTER;
        gbcRol.insets = new Insets(20, 0, 20, 0);
        gbcRol.fill = GridBagConstraints.NONE;
        gbcRol.weightx = 1.0;
        gbcRol.weighty = 0;

        // Estado centrado
        GridBagConstraints gbcEstado = new GridBagConstraints();
        gbcEstado.gridwidth = GridBagConstraints.REMAINDER;
        gbcEstado.anchor = GridBagConstraints.CENTER;
        gbcEstado.insets = new Insets(20, 0, 30, 0);
        gbcEstado.fill = GridBagConstraints.NONE;
        gbcEstado.weightx = 1.0;
        gbcEstado.weighty = 0;

        // Botones centrados, completos
        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridwidth = GridBagConstraints.REMAINDER;
        gbcBotones.anchor = GridBagConstraints.CENTER;
        gbcBotones.insets = new Insets(20, 0, 50, 0);
        gbcBotones.fill = GridBagConstraints.NONE;
        gbcBotones.weightx = 1.0;
        gbcBotones.weighty = 0;

        //  TÍTULO 
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 48));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        panelCentral.add(lblTitulo, gbcTitulo);

        //  ID USUARIO 
        lblIdUsuario.setFont(new Font("Arial Black", Font.BOLD, 28));
        lblIdUsuario.setForeground(Color.WHITE);
        txtIdUsuario.setFont(new Font("Arial", Font.PLAIN, 28));
        txtIdUsuario.setPreferredSize(new Dimension(400, 60)); // ← Ancho controlado (era 600)
        txtIdUsuario.setHorizontalAlignment(JTextField.CENTER);
        txtIdUsuario.setEditable(false); // opcional: ID no editable
        txtIdUsuario.setBackground(Color.WHITE);
        txtIdUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(70, 70, 80), 2));

        panelCentral.add(lblIdUsuario, gbcLabel);
        panelCentral.add(txtIdUsuario, gbcField);

        //  NOMBRE 
        lblNombre.setFont(new Font("Arial Black", Font.BOLD, 28));
        lblNombre.setForeground(Color.WHITE);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 28));
        txtNombre.setPreferredSize(new Dimension(400, 60)); // ← Ancho controlado
        txtNombre.setHorizontalAlignment(JTextField.CENTER);
        txtNombre.setBackground(Color.WHITE);
        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(70, 70, 80), 2));

        panelCentral.add(lblNombre, gbcLabel);
        panelCentral.add(txtNombre, gbcField);

        //  ROL 
        lblRol.setFont(new Font("Arial Black", Font.BOLD, 28));
        lblRol.setForeground(Color.WHITE);
        RadioAdministrador.setFont(new Font("Arial Black", Font.BOLD, 28));
        RadioAdministrador.setForeground(Color.WHITE);
        RadioAdministrador.setOpaque(false);
        RadioCajero.setFont(new Font("Arial Black", Font.BOLD, 28));
        RadioCajero.setForeground(Color.WHITE);
        RadioCajero.setOpaque(false);

        JPanel panelRol = new JPanel();
        panelRol.setOpaque(false);
        panelRol.add(lblRol);
        panelRol.add(RadioAdministrador);
        panelRol.add(RadioCajero);
        panelCentral.add(panelRol, gbcRol);

        //  ESTADO 
        lblEstado.setFont(new Font("Arial Black", Font.BOLD, 28));
        lblEstado.setForeground(Color.CYAN);
        lblEstado.setHorizontalAlignment(JLabel.CENTER);
        panelCentral.add(lblEstado, gbcEstado);

        //  BOTONES 
        Font fontBotones = new Font("Arial Black", Font.BOLD, 28);
        Dimension tamañoBoton = new Dimension(400, 80);

        btnGuardar.setFont(fontBotones);
        btnGuardar.setPreferredSize(tamañoBoton);
        btnGuardar.setBackground(new Color(46, 204, 113)); // Verde
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);

        btnCancelar.setFont(fontBotones);
        btnCancelar.setPreferredSize(tamañoBoton);
        btnCancelar.setBackground(new Color(231, 76, 60)); // Rojo
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);

        // Panel de botones con GridBagLayout para evitar que se corten
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setOpaque(false);

        GridBagConstraints gbcBtn = new GridBagConstraints();
        gbcBtn.insets = new Insets(10, 30, 10, 30);
        gbcBtn.fill = GridBagConstraints.NONE;
        gbcBtn.anchor = GridBagConstraints.CENTER;

        panelBotones.add(btnGuardar, gbcBtn);
        panelBotones.add(btnCancelar, gbcBtn);

        panelCentral.add(panelBotones, gbcBotones);

        // Aplicar panel central
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelCentral, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
     // Método para inyectar el controlador desde fuera
    public void establecerControlador(Controlador.ControladorEditarUsuario controlador) {
        this.controladorEditar = controlador;
    }
    
    
    // Método para cargar los datos del usuario en la interfaz
    private void cargarDatos() {
        // Mostrar el ID (solo lectura)
        txtIdUsuario.setText(String.valueOf(usuarioActual.getIdUsuario()));
        txtIdUsuario.setEditable(false);

        // Mostrar el nombre de usuario
        txtNombre.setText(usuarioActual.getNombreUsuario());

        // Seleccionar el rol
        if ("administrador".equals(usuarioActual.getRol())) {
            RadioAdministrador.setSelected(true);
        } else {
            RadioCajero.setSelected(true);
        }

        // Mostrar el estado
        String estado = usuarioActual.isActivo() ? "Activo" : "Inactivo";
        lblEstado.setText("Estado: " + estado);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblIdUsuario = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        lblRol = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        RadioAdministrador = new javax.swing.JRadioButton();
        RadioCajero = new javax.swing.JRadioButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("EDITAR USUARIO");

        lblIdUsuario.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblIdUsuario.setForeground(new java.awt.Color(0, 102, 102));
        lblIdUsuario.setText("ID Usuario");

        txtIdUsuario.setEditable(false);
        txtIdUsuario.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        txtIdUsuario.setForeground(new java.awt.Color(0, 102, 102));
        txtIdUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdUsuarioActionPerformed(evt);
            }
        });

        lblRol.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblRol.setForeground(new java.awt.Color(0, 102, 102));
        lblRol.setText("Rol");

        lblEstado.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(0, 102, 102));
        lblEstado.setText("Estado: ");

        RadioAdministrador.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        RadioAdministrador.setForeground(new java.awt.Color(0, 102, 102));
        RadioAdministrador.setText("Administrador");
        RadioAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioAdministradorActionPerformed(evt);
            }
        });

        RadioCajero.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        RadioCajero.setForeground(new java.awt.Color(0, 102, 102));
        RadioCajero.setText("Cajero");
        RadioCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioCajeroActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 102, 102));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 102, 102));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblNombre.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(0, 102, 102));
        lblNombre.setText("Nombre");

        txtNombre.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 102, 102));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addComponent(lblTitulo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblRol)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnGuardar)
                                            .addComponent(RadioAdministrador)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblIdUsuario))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RadioCajero)
                            .addComponent(btnCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblIdUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRol)
                    .addComponent(RadioAdministrador)
                    .addComponent(RadioCajero))
                .addGap(26, 26, 26)
                .addComponent(lblEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RadioAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioAdministradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioAdministradorActionPerformed

    private void RadioCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioCajeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioCajeroActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    String nuevoNombre = txtNombre.getText().trim();
    String nuevoRol = "";

    // Validar que el nombre no esté vacío
    if (nuevoNombre.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre de usuario.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar que se haya seleccionado un rol
    if (RadioAdministrador.isSelected()) {
        nuevoRol = "administrador";
    } else if (RadioCajero.isSelected()) {
        nuevoRol = "cajero";
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor seleccione un rol.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Actualizar los datos del usuario
    usuarioActual.setNombreUsuario(nuevoNombre);
    usuarioActual.setRol(nuevoRol);

    // Llamar al controlador para guardar los cambios
    if (controladorEditar != null) {
        controladorEditar.guardarCambios(usuarioActual);
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if (controladorEditar != null) {
            controladorEditar.cancelarEdicion();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtIdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdUsuarioActionPerformed
        // TODO add your handling code here:
   

    }//GEN-LAST:event_txtIdUsuarioActionPerformed

 
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioAdministrador;
    private javax.swing.JRadioButton RadioCajero;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblIdUsuario;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
