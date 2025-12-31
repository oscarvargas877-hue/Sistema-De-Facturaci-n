/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Controlador/ControladorEditarUsuario.java
// Controlador/ControladorEditarUsuario.java
package Controlador;

import Modelo.UsuarioModelo;
import Vista.VistaEditarUsuario;


// Controlador para la edición de usuarios (HU003)
public class ControladorEditarUsuario {

    private VistaEditarUsuario vistaEditar;
    private ControladorGestionUsuarios controladorGestion; // Para volver y recargar

    // Constructor
    public ControladorEditarUsuario(VistaEditarUsuario vista, ControladorGestionUsuarios controladorGestion) {
        this.vistaEditar = vista;
        this.controladorGestion = controladorGestion;
    }

    // Método para guardar los cambios del usuario
    public void guardarCambios(UsuarioModelo usuarioModificado) {
     try {
         usuarioModificado.actualizar();

         // Solo llega aquí si NO hubo excepción
         javax.swing.JOptionPane.showMessageDialog(vistaEditar, 
             "Usuario actualizado correctamente.", 
             "Éxito", 
             javax.swing.JOptionPane.INFORMATION_MESSAGE);

         vistaEditar.dispose();
         controladorGestion.volverAlMenu();
         controladorGestion.cargarUsuarios();

     } catch (java.sql.SQLException e) {
         String mensaje = "Error al actualizar el usuario.";
         if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
             if (e.getMessage().contains("nombreUsuario")) {
                 mensaje = "El nombre de usuario ya existe.";
             } else if (e.getMessage().contains("cedula")) {
                 mensaje = "La cédula ya está registrada en otro usuario.";
             } else {
                 mensaje = "Dato duplicado (nombre de usuario o cédula).";
             }
         }
         javax.swing.JOptionPane.showMessageDialog(vistaEditar, mensaje, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
         e.printStackTrace();

     } catch (Exception e) {
         javax.swing.JOptionPane.showMessageDialog(vistaEditar, 
             "Error inesperado al guardar.", 
             "Error", 
             javax.swing.JOptionPane.ERROR_MESSAGE);
         e.printStackTrace();
     }
 }

    // Método para cancelar la edición y volver a la vista de gestión
    public void cancelarEdicion() {
        vistaEditar.dispose();
        controladorGestion.volverAlMenu();
    }
}