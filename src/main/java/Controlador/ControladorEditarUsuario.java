/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Controlador/ControladorEditarUsuario.java
// Controlador/ControladorEditarUsuario.java
package Controlador;

import Modelo.UsuarioModelo;
import Vista.VistaEditarUsuario;
import Vista.VistaGestionUsuarios;

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
            // Actualizar el usuario en la base de datos
            usuarioModificado.actualizar();

            // Mostrar mensaje de éxito
            javax.swing.JOptionPane.showMessageDialog(vistaEditar, "Usuario actualizado correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            // Volver a la vista de gestión y recargar la lista
            vistaEditar.dispose();
            controladorGestion.volverAlMenu();
            controladorGestion.cargarUsuarios();

        } catch (Exception e) {
            // Manejar error de nombre duplicado (viene de la BD por UNIQUE)
            if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
                javax.swing.JOptionPane.showMessageDialog(vistaEditar, "El nombre de usuario ya existe. Elija otro.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(vistaEditar, "Error al actualizar el usuario. Intente de nuevo.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    // Método para cancelar la edición y volver a la vista de gestión
    public void cancelarEdicion() {
        vistaEditar.dispose();
        controladorGestion.volverAlMenu();
    }
}