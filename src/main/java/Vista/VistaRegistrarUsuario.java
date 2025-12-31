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
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
// Configuración básica
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(new Color(70, 130, 180));

        // ================== TÍTULO GRANDE ==================
        lblTitulo.setText("REGISTRAR NUEVO USUARIO");
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 60));
        lblTitulo.setForeground(new Color(0, 102, 102));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);

        // ================== ESTILO DE ETIQUETAS ==================
        Font fontEtiqueta = new Font("Arial Black", Font.BOLD, 36);
        lblNombreUsuario.setFont(fontEtiqueta);
        lblNombreUsuario.setForeground(Color.WHITE);
        lblContrasenia.setFont(fontEtiqueta);
        lblContrasenia.setForeground(Color.WHITE);
        lblCedula.setFont(fontEtiqueta);
        lblCedula.setForeground(Color.WHITE);
        lblDireccion.setFont(fontEtiqueta);
        lblDireccion.setForeground(Color.WHITE);
        lblEdad.setFont(fontEtiqueta);
        lblEdad.setForeground(Color.WHITE);
        lblGenero.setFont(fontEtiqueta);
        lblGenero.setForeground(Color.WHITE);
        lblRol.setFont(fontEtiqueta);
        lblRol.setForeground(Color.WHITE);

        // ================== CAMPOS ANCHOS ==================
        Font fontCampo = new Font("Arial", Font.PLAIN, 32);
        Dimension dimensionCampo = new Dimension(650, 70);
        Dimension dimensionEdad = new Dimension(200, 70);

        txtNombreUsuario.setFont(fontCampo);
        txtNombreUsuario.setPreferredSize(dimensionCampo);
        jPContrasenia.setFont(fontCampo);
        jPContrasenia.setPreferredSize(dimensionCampo);
        txtCedula.setFont(fontCampo);
        txtCedula.setPreferredSize(dimensionCampo);
        txtDireccion.setFont(fontCampo);
        txtDireccion.setPreferredSize(dimensionCampo);
        txtEdad.setFont(fontCampo);
        txtEdad.setPreferredSize(dimensionEdad);

        // ================== RADIO BUTTONS GÉNERO (AGRUPADOS) ==================
        ButtonGroup grupoGenero = new ButtonGroup();
        grupoGenero.add(RadioMasculino);
        grupoGenero.add(RadioFemenino);

        RadioMasculino.setFont(new Font("Arial Black", Font.BOLD, 34));
        RadioMasculino.setForeground(Color.WHITE);
        RadioMasculino.setOpaque(false);
        RadioMasculino.setSelected(false); // Deseleccionado por defecto

        RadioFemenino.setFont(new Font("Arial Black", Font.BOLD, 34));
        RadioFemenino.setForeground(Color.WHITE);
        RadioFemenino.setOpaque(false);
        RadioFemenino.setSelected(false); // Deseleccionado por defecto

        // ================== RADIO BUTTONS ROL (AGRUPADOS) ==================
        ButtonGroup grupoRol = new ButtonGroup();
        grupoRol.add(RadioAdministrador);
        grupoRol.add(RadioCajero);

        RadioAdministrador.setFont(new Font("Arial Black", Font.BOLD, 34));
        RadioAdministrador.setForeground(Color.WHITE);
        RadioAdministrador.setOpaque(false);
        RadioAdministrador.setSelected(false); // Deseleccionado por defecto

        RadioCajero.setFont(new Font("Arial Black", Font.BOLD, 34));
        RadioCajero.setForeground(Color.WHITE);
        RadioCajero.setOpaque(false);
        RadioCajero.setSelected(false); // Deseleccionado por defecto

        // ================== BOTONES ==================
        btnRegistrar.setText("Registrar Usuario");
        btnRegistrar.setFont(new Font("Arial Black", Font.BOLD, 40));
        btnRegistrar.setBackground(new Color(46, 204, 113));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setPreferredSize(new Dimension(500, 90));

        btnCancelar.setText("Cancelar");
        btnCancelar.setFont(new Font("Arial Black", Font.BOLD, 40));
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(500, 90));

    
        // Refrescar
        revalidate();
        repaint();
    
    }
         public void establecerControlador(Controlador.ControladorRegistrarUsuario controlador) {
        this.controladorRegistrar = controlador;
         
         
         }
    // Mensaje de error flotante (rojo, icono de error)
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // Mensaje de éxito flotante (verde, icono de información)
    public void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Éxito",
            JOptionPane.INFORMATION_MESSAGE
        );
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
        jPContrasenia = new javax.swing.JPasswordField();
        txtNombreUsuario = new javax.swing.JTextField();
        RadioAdministrador = new javax.swing.JRadioButton();
        RadioCajero = new javax.swing.JRadioButton();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblCedula = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        lblGenero = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        RadioMasculino = new javax.swing.JRadioButton();
        txtDireccion = new javax.swing.JTextField();
        lblEdad = new javax.swing.JLabel();
        RadioFemenino = new javax.swing.JRadioButton();
        txtCedula = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Registrar Nuevo Usuario");

        lblNombreUsuario.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(0, 102, 102));
        lblNombreUsuario.setText("Nombre de usuario:");

        lblContrasenia.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblContrasenia.setForeground(new java.awt.Color(0, 102, 102));
        lblContrasenia.setText("Contraseña:");

        lblRol.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblRol.setForeground(new java.awt.Color(0, 102, 102));
        lblRol.setText("Rol:");

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

        lblCedula.setText("Cédula:");

        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        lblGenero.setText("Género:");

        lblDireccion.setText("Dirección: ");

        RadioMasculino.setText("Masculino");

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        lblEdad.setText("Edad:");

        RadioFemenino.setText("Femenino");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreUsuario)
                                    .addComponent(lblContrasenia))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCedula)
                                    .addComponent(lblDireccion)
                                    .addComponent(lblEdad)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblGenero)
                                        .addGap(18, 18, 18)
                                        .addComponent(RadioMasculino)))
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                                    .addComponent(txtDireccion)
                                    .addComponent(txtCedula)
                                    .addComponent(jPContrasenia)
                                    .addComponent(txtNombreUsuario)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lblTitulo)))
                .addGap(184, 184, 184))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblRol)
                .addGap(34, 34, 34)
                .addComponent(RadioAdministrador)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RadioFemenino)
                    .addComponent(RadioCajero))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnRegistrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCedula)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEdad)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenero)
                    .addComponent(RadioMasculino)
                    .addComponent(RadioFemenino)
                    .addComponent(btnRegistrar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblRol)
                                .addComponent(RadioAdministrador))
                            .addComponent(RadioCajero)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap(148, Short.MAX_VALUE))
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
         
    }//GEN-LAST:event_RadioAdministradorActionPerformed

    private void RadioCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioCajeroActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_RadioCajeroActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
    String nombreUsuario = txtNombreUsuario.getText().trim();
    String contrasenia = new String(jPContrasenia.getPassword());
    String cedula = txtCedula.getText().trim();
    String direccion = txtDireccion.getText().trim();
    String edadStr = txtEdad.getText().trim();

    // === VALIDACIONES BÁSICAS EN LA VISTA (evitan llamar al controlador innecesariamente) ===
    if (nombreUsuario.isEmpty() || contrasenia.isEmpty() || cedula.isEmpty() ||
        direccion.isEmpty() || edadStr.isEmpty()) {
        mostrarMensajeError("Todos los campos son obligatorios.");
        return;
    }

    int edad;
    try {
        edad = Integer.parseInt(edadStr);
        if (edad < 18 || edad > 120) {
            mostrarMensajeError("Edad no válida (18-120 años).");
            return;
        }
    } catch (NumberFormatException ex) {
        mostrarMensajeError("La edad debe ser un número válido.");
        return;
    }

    // === VALIDAR GÉNERO ===
    if (!RadioMasculino.isSelected() && !RadioFemenino.isSelected()) {
        mostrarMensajeError("Por favor seleccione un género.");
        return;
    }
    String genero = RadioMasculino.isSelected() ? "Masculino" : "Femenino";

    // === VALIDAR ROL ===
    if (!RadioAdministrador.isSelected() && !RadioCajero.isSelected()) {
        mostrarMensajeError("Por favor seleccione un rol (Administrador o Cajero).");
        return;
    }
    String rol = RadioAdministrador.isSelected() ? "administrador" : "cajero";

    // === SI TODO ESTÁ BIEN → LLAMAR AL CONTROLADOR ===
    controladorRegistrar.registrarUsuario(nombreUsuario, contrasenia, rol, cedula, direccion, edad, genero);
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if (controladorRegistrar != null) {
            controladorRegistrar.cancelar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed


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
    private javax.swing.JRadioButton RadioFemenino;
    private javax.swing.JRadioButton RadioMasculino;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JPasswordField jPContrasenia;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
