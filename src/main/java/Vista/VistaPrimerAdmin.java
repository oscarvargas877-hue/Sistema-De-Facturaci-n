/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


/**
 *
 * @author Usuario
 */
public class VistaPrimerAdmin extends javax.swing.JFrame {
    // Atributo para guardar la referencia al controlador
    private Controlador.ControladorPrimerAdmin controladorPrimerAdmin;

    /**
     * Creates new form VistaPrimerAdmin
     */
    public VistaPrimerAdmin() {
        initComponents();
        
// Configuración básica
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(new Color(70, 130, 180));

        // ================== TÍTULO GRANDE ARRIBA ==================
        lblTitulo.setText("CREAR PRIMER ADMINISTRADOR");
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 60));
        lblTitulo.setForeground(new Color(0, 102, 102));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);

        // ================== BOTÓN VERDE GRANDE ABAJO ==================
        btnCrearPrimerAdministrador.setText("Crear Primer Administrador");
        btnCrearPrimerAdministrador.setFont(new Font("Arial Black", Font.BOLD, 40));
        btnCrearPrimerAdministrador.setBackground(new Color(46, 204, 113));
        btnCrearPrimerAdministrador.setForeground(Color.WHITE);
        btnCrearPrimerAdministrador.setFocusPainted(false);
        btnCrearPrimerAdministrador.setPreferredSize(new Dimension(800, 100)); // Más ancho

        // ================== MENSAJE DE ERROR Y ÉXITO ==================
        lblMensajeError.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblMensajeError.setForeground(Color.WHITE);
        lblMensajeError.setHorizontalAlignment(JLabel.CENTER);
        lblMensajeError.setVisible(false);

        // ================== ESTILO DE ETIQUETAS Y CAMPOS ==================
        Font fontEtiqueta = new Font("Arial Black", Font.BOLD, 36);
        Font fontCampo = new Font("Arial", Font.PLAIN, 32);

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

        // Campos más anchos y altos
        Dimension dimensionCampoAncho = new Dimension(600, 65);
        Dimension dimensionEdad = new Dimension(200, 65);

        txtNombreUsuario.setFont(fontCampo);
        txtNombreUsuario.setPreferredSize(dimensionCampoAncho);
        jPContrasenia.setFont(fontCampo);
        jPContrasenia.setPreferredSize(dimensionCampoAncho);
        txtCedula.setFont(fontCampo);
        txtCedula.setPreferredSize(dimensionCampoAncho);
        txtDireccion.setFont(fontCampo);
        txtDireccion.setPreferredSize(dimensionCampoAncho);
        txtEdad.setFont(fontCampo);
        txtEdad.setPreferredSize(dimensionEdad);

        // Género más grande y bonito
        RadioMasculino.setFont(new Font("Arial Black", Font.BOLD, 34));
        RadioMasculino.setForeground(Color.WHITE);
        RadioMasculino.setOpaque(false);
        RadioMasculino.setSelected(true);

        RadioFemenino.setFont(new Font("Arial Black", Font.BOLD, 34));
        RadioFemenino.setForeground(Color.WHITE);
        RadioFemenino.setOpaque(false);

        // ================== ACERCAR CÉDULA MÁS ARRIBA ==================
        // Esto se logra ajustando el layout de NetBeans (no podemos cambiarlo directamente),
        // pero podemos "empujar" visualmente los componentes con insets
        // Lo mejor es confiar en el layout actual y solo ajustar tamaños

        revalidate();
        repaint();
    }
       
             public void establecerControlador(Controlador.ControladorPrimerAdmin controlador) {
        this.controladorPrimerAdmin = controlador;

        // Conectamos el botón al controlador
        btnCrearPrimerAdministrador.addActionListener(e -> {
            String nombreUsuario = txtNombreUsuario.getText().trim();
            String contrasenia = new String(jPContrasenia.getPassword());
            String cedula = txtCedula.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String edadStr = txtEdad.getText().trim();
            String genero = RadioMasculino.isSelected() ? "Masculino" : "Femenino";

            // Validaciones
            if (nombreUsuario.isEmpty() || contrasenia.isEmpty() || cedula.isEmpty() ||
                direccion.isEmpty() || edadStr.isEmpty()) {
                mostrarMensajeError("Todos los campos son obligatorios.", Color.RED);
                return;
            }

            if (nombreUsuario.contains(" ")) {
                mostrarMensajeError("El nombre de usuario no puede contener espacios.", Color.RED);
                return;
            }

            int edad;
            try {
                edad = Integer.parseInt(edadStr);
                if (edad < 18 || edad > 120) {
                    mostrarMensajeError("Edad no válida (18-120 años).", Color.RED);
                    return;
                }
            } catch (NumberFormatException ex) {
                mostrarMensajeError("La edad debe ser un número válido.", Color.RED);
                return;
            }

            // Si todo está bien, llamamos al controlador
            try {
                controlador.crearPrimerAdministrador(nombreUsuario, contrasenia, cedula, direccion, edad, genero);

                // Mensaje de éxito en verde
                mostrarMensajeExito("¡Primer Administrador creado exitosamente!");

                // Pausa de 2 segundos para que el usuario vea el mensaje
                Timer timer = new Timer(2000, ev -> {
                    // Cerrar esta ventana y abrir el Login (el controlador ya lo hace, pero por seguridad)
                    // Si ya lo hace el controlador, puedes quitar esto
                });
                timer.setRepeats(false);
                timer.start();

            } catch (Exception ex) {
                mostrarMensajeError("Error al crear el administrador: " + ex.getMessage(), Color.RED);
            }
        });
    }

    // Método auxiliar para errores (rojo)
    private void mostrarMensajeError(String mensaje, Color color) {
        lblMensajeError.setText(mensaje);
        lblMensajeError.setForeground(color);
        lblMensajeError.setVisible(true);
        revalidate();
        repaint();
    }
    // Sobrecarga solo mensaje 
    public void mostrarMensajeError(String mensaje) {
        mostrarMensajeError(mensaje, Color.RED);
    }
    // Método auxiliar para éxito (verde)
    public void mostrarMensajeExito(String mensaje) {
        lblMensajeError.setText(mensaje);
        lblMensajeError.setForeground(new Color(46, 100, 113)); 
        lblMensajeError.setVisible(true);
        revalidate();
        repaint();
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
        txtNombreUsuario = new javax.swing.JTextField();
        jPContrasenia = new javax.swing.JPasswordField();
        lblMensajeError = new javax.swing.JLabel();
        btnCrearPrimerAdministrador = new javax.swing.JButton();
        txtDireccion = new javax.swing.JTextField();
        lblEdad = new javax.swing.JLabel();
        RadioFemenino = new javax.swing.JRadioButton();
        txtCedula = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        lblGenero = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        RadioMasculino = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Crear Primer Administrador ");

        lblNombreUsuario.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(0, 102, 102));
        lblNombreUsuario.setText("Nombre De Usuario:");

        lblContrasenia.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblContrasenia.setForeground(new java.awt.Color(0, 102, 102));
        lblContrasenia.setText("Contraseña:");

        txtNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreUsuarioActionPerformed(evt);
            }
        });

        jPContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPContraseniaActionPerformed(evt);
            }
        });

        btnCrearPrimerAdministrador.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnCrearPrimerAdministrador.setForeground(new java.awt.Color(0, 102, 102));
        btnCrearPrimerAdministrador.setText("Crear Primer Administrador");
        btnCrearPrimerAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPrimerAdministradorActionPerformed(evt);
            }
        });

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        lblEdad.setText("Edad:");

        RadioFemenino.setText("Femenino");

        lblCedula.setText("Cédula:");

        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        lblGenero.setText("Género:");

        lblDireccion.setText("Dirección: ");

        RadioMasculino.setText("Masculino");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(lblTitulo)
                .addContainerGap(208, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCrearPrimerAdministrador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMensajeError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreUsuario)
                            .addComponent(lblContrasenia)
                            .addComponent(lblCedula)
                            .addComponent(lblDireccion)
                            .addComponent(lblEdad)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblGenero)
                                .addGap(43, 43, 43)
                                .addComponent(RadioMasculino)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEdad)
                                    .addComponent(txtDireccion)
                                    .addComponent(txtCedula)
                                    .addComponent(jPContrasenia)
                                    .addComponent(txtNombreUsuario)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(RadioFemenino)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreUsuario)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasenia)
                    .addComponent(jPContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
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
                    .addComponent(RadioFemenino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrearPrimerAdministrador)
                    .addComponent(lblMensajeError, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
// Acción del botón "Crear Primer Administrador"
    private void btnCrearPrimerAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPrimerAdministradorActionPerformed
        // TODO add your handling code here:
    String nombreUsuario = txtNombreUsuario.getText();
    String contrasenia = new String(jPContrasenia.getPassword());

    // Validar que los campos no estén vacíos
    if (nombreUsuario.trim().isEmpty() || contrasenia.trim().isEmpty()) {
        lblMensajeError.setText("Por favor complete todos los campos.");
        lblMensajeError.setVisible(true);
        return;
    }

    // Validar que el nombre de usuario no contenga espacios
    if (nombreUsuario.contains(" ")) {
        lblMensajeError.setText("El nombre de usuario no puede contener espacios.");
        lblMensajeError.setVisible(true);
        return;
    }

   
    }//GEN-LAST:event_btnCrearPrimerAdministradorActionPerformed
// Acción al presionar Enter en el campo de contraseña (opcional pero útil)
    private void jPContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPContraseniaActionPerformed
        // TODO add your handling code here:
         btnCrearPrimerAdministrador.doClick();
    }//GEN-LAST:event_jPContraseniaActionPerformed
// Acción al presionar Enter en el campo de usuario
    private void txtNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreUsuarioActionPerformed
        // TODO add your handling code here:
         jPContrasenia.requestFocus();
    }//GEN-LAST:event_txtNombreUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(VistaPrimerAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrimerAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrimerAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrimerAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrimerAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioFemenino;
    private javax.swing.JRadioButton RadioMasculino;
    private javax.swing.JButton btnCrearPrimerAdministrador;
    private javax.swing.JPasswordField jPContrasenia;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblMensajeError;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
