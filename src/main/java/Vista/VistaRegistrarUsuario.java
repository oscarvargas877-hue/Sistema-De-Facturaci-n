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
public class VistaRegistrarUsuario extends javax.swing.JFrame {
     private Controlador.ControladorRegistrarUsuario controladorRegistrar;
    /**
     * Creates new form VistaRegistrarUsuario
     */
    public VistaRegistrarUsuario() {
        initComponents();//NO BORRAR
    // Crear un grupo de botones para que solo se pueda seleccionar uno
    javax.swing.ButtonGroup grupoRol = new javax.swing.ButtonGroup();
    grupoRol.add(RadioAdministrador);
    grupoRol.add(RadioCajero);
    
 // PANTALLA COMPLETA
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    // Fondo profesional
    getContentPane().setBackground(new Color(52, 73, 94));

    // PANEL CENTRAL PARA CENTRAR TODO
    JPanel panelCentral = new JPanel();
    panelCentral.setLayout(new GridBagLayout());
    panelCentral.setOpaque(false);

    GridBagConstraints labelGbc = new GridBagConstraints();
    labelGbc.anchor = GridBagConstraints.EAST; // Etiquetas a la derecha
    labelGbc.insets = new Insets(20, 0, 20, 20);

    GridBagConstraints fieldGbc = new GridBagConstraints();
    fieldGbc.gridwidth = GridBagConstraints.REMAINDER;
    fieldGbc.fill = GridBagConstraints.HORIZONTAL;
    fieldGbc.weightx = 1.0;
    fieldGbc.insets = new Insets(20, 0, 20, 100); // Margen derecho

    // Título
    panelCentral.add(lblTitulo, fieldGbc);

    // Nombre de usuario
    panelCentral.add(lblNombreUsuario, labelGbc);
    panelCentral.add(txtNombreUsuario, fieldGbc);

    // Contraseña
    panelCentral.add(lblContrasenia, labelGbc);
    panelCentral.add(jPContrasenia, fieldGbc);

    // Rol
    panelCentral.add(lblRol, labelGbc);

    JPanel panelRadio = new JPanel();
    panelRadio.setOpaque(false);
    panelRadio.add(RadioAdministrador);
    panelRadio.add(RadioCajero);
    fieldGbc.gridwidth = GridBagConstraints.REMAINDER;
    panelCentral.add(panelRadio, fieldGbc);

    // Botones más arriba 
    JPanel panelBotones = new JPanel();
    panelBotones.setOpaque(false);
    panelRadio.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 0));
    panelBotones.add(btnRegistrar);
    panelBotones.add(btnCancelar);
    panelCentral.add(panelBotones, fieldGbc);

    // Mensaje de error
    panelCentral.add(lblMensajeError, fieldGbc);

    // Aplicar panel central
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panelCentral, BorderLayout.CENTER);

    // ESTILOS FINALES
    lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 48));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setHorizontalAlignment(JLabel.CENTER);

    lblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 28));
    lblNombreUsuario.setForeground(Color.WHITE);

    lblContrasenia.setFont(new Font("Arial", Font.BOLD, 28));
    lblContrasenia.setForeground(Color.WHITE);

    lblRol.setFont(new Font("Arial", Font.BOLD, 28));
    lblRol.setForeground(Color.WHITE);

    txtNombreUsuario.setFont(new Font("Arial", Font.PLAIN, 28));
    txtNombreUsuario.setPreferredSize(new Dimension(600, 60)); // Ancho normal
    txtNombreUsuario.setHorizontalAlignment(JTextField.CENTER);

    jPContrasenia.setFont(new Font("Arial", Font.PLAIN, 28));
    jPContrasenia.setPreferredSize(new Dimension(600, 60)); // Ancho normal

    RadioAdministrador.setFont(new Font("Arial", Font.BOLD, 28));
    RadioAdministrador.setForeground(Color.WHITE);
    RadioAdministrador.setOpaque(false);

    RadioCajero.setFont(new Font("Arial", Font.BOLD, 28));
    RadioCajero.setForeground(Color.WHITE);
    RadioCajero.setOpaque(false);

    btnRegistrar.setFont(new Font("Arial Black", Font.BOLD, 28));
    btnRegistrar.setBackground(new Color(46, 204, 113));
    btnRegistrar.setForeground(Color.WHITE);
    btnRegistrar.setPreferredSize(new Dimension(350, 80));

    btnCancelar.setFont(new Font("Arial Black", Font.BOLD, 28));
    btnCancelar.setBackground(new Color(231, 76, 60));
    btnCancelar.setForeground(Color.WHITE);
    btnCancelar.setPreferredSize(new Dimension(350, 80));

    lblMensajeError.setFont(new Font("Arial", Font.BOLD, 26));
    lblMensajeError.setForeground(Color.YELLOW);
    lblMensajeError.setHorizontalAlignment(JLabel.CENTER);

    revalidate();
    repaint();
    
    }
        public void establecerControlador(Controlador.ControladorRegistrarUsuario controlador) {
        this.controladorRegistrar = controlador;
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
        lblNombreUsuario = new javax.swing.JLabel();
        lblContrasenia = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        lblMensajeError = new javax.swing.JLabel();
        jPContrasenia = new javax.swing.JPasswordField();
        txtNombreUsuario = new javax.swing.JTextField();
        RadioAdministrador = new javax.swing.JRadioButton();
        RadioCajero = new javax.swing.JRadioButton();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Registrar Nuevo Usuario");

        lblNombreUsuario.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(0, 102, 102));
        lblNombreUsuario.setText("Nombre de usuario");

        lblContrasenia.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblContrasenia.setForeground(new java.awt.Color(0, 102, 102));
        lblContrasenia.setText("Contraseña");

        lblRol.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblRol.setForeground(new java.awt.Color(0, 102, 102));
        lblRol.setText("Rol");

        jPContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPContraseniaActionPerformed(evt);
            }
        });

        txtNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreUsuarioActionPerformed(evt);
            }
        });

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

        btnRegistrar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(0, 102, 102));
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnRegistrar)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNombreUsuario)
                                        .addComponent(lblContrasenia)
                                        .addComponent(lblRol)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(RadioAdministrador)
                                        .addGap(18, 18, 18)
                                        .addComponent(RadioCajero))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(143, 143, 143)
                                        .addComponent(btnCancelar)))
                                .addGap(61, 61, 61))
                            .addComponent(lblMensajeError, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lblTitulo)))
                .addContainerGap(390, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreUsuario)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasenia)
                    .addComponent(jPContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRol)
                    .addComponent(RadioAdministrador)
                    .addComponent(RadioCajero))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnRegistrar))
                .addGap(57, 57, 57)
                .addComponent(lblMensajeError, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreUsuarioActionPerformed

    private void jPContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPContraseniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPContraseniaActionPerformed

    private void RadioAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioAdministradorActionPerformed
        // TODO add your handling code here:
          lblMensajeError.setVisible(false);
    }//GEN-LAST:event_RadioAdministradorActionPerformed

    private void RadioCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioCajeroActionPerformed
        // TODO add your handling code here:
          lblMensajeError.setVisible(false);
    }//GEN-LAST:event_RadioCajeroActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        String nombreUsuario = txtNombreUsuario.getText();
        String contrasenia = new String(jPContrasenia.getPassword());
        String rol = "";
        
          // Validar campos vacíos
        if (nombreUsuario.trim().isEmpty() || contrasenia.trim().isEmpty()) {
            lblMensajeError.setText("Por favor complete todos los campos.");
            lblMensajeError.setVisible(true);
            return;
        }

        // Determinar el rol seleccionado
        if (RadioAdministrador.isSelected()) {
            rol = "administrador";
        } else if (RadioCajero.isSelected()) {
            rol = "cajero";
        } else {
            lblMensajeError.setText("Por favor seleccione un rol");
            lblMensajeError.setVisible(true);
            return;
        }

      

        // Llamar al controlador
        if (controladorRegistrar != null) {
            controladorRegistrar.registrarUsuario(nombreUsuario, contrasenia, rol);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if (controladorRegistrar != null) {
            controladorRegistrar.cancelar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

        // Método público para que el controlador muestre mensajes
    public void mostrarMensaje(String mensaje, boolean exito) {
        lblMensajeError.setText(mensaje);
        lblMensajeError.setForeground(exito ? java.awt.Color.GREEN : java.awt.Color.RED);
        lblMensajeError.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaRegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaRegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaRegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaRegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaRegistrarUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioAdministrador;
    private javax.swing.JRadioButton RadioCajero;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JPasswordField jPContrasenia;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblMensajeError;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
