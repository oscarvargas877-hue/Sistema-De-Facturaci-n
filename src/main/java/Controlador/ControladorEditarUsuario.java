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
    private ControladorGestionUsuarios controladorGestion;
    private Vista.VistaMenuAdmin vistaMenuAdmin; // NUEVO: Referencia al menú
    
    public ControladorEditarUsuario(VistaEditarUsuario vista, ControladorGestionUsuarios controladorGestion) {
        this.vistaEditar = vista;
        this.controladorGestion = controladorGestion;
        this.vistaMenuAdmin = controladorGestion.getVistaMenuAdmin(); // NUEVO: Obtener referencia
    }
    
    public void guardarCambios(UsuarioModelo usuarioModificado) {
        try {
            usuarioModificado.actualizar();
            
            javax.swing.JOptionPane.showMessageDialog(vistaEditar, 
                "Usuario actualizado correctamente.", 
                "Éxito", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            vistaEditar.dispose();
            irAGestionUsuarios();
            
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
    
    public void cancelarEdicion() {
        vistaEditar.dispose();
        irAGestionUsuarios();
    }
    
   private void irAGestionUsuarios() {
    // Crear NUEVA vista
    VistaGestionUsuarios vistaGestion = new VistaGestionUsuarios();
    
    // Crear NUEVO controlador
    ControladorGestionUsuarios nuevoControlador = 
        new ControladorGestionUsuarios(vistaGestion, vistaMenuAdmin);
    
    // Establecer controlador en la vista
    vistaGestion.establecerControlador(nuevoControlador);
    
    // NO HACERLA VISIBLE AÚN - esperar a que cargue los datos primero
    
    // Cargar datos ANTES de hacerla visible
    javax.swing.SwingUtilities.invokeLater(() -> {
        try {
            // Cargar usuarios PRIMERO
            nuevoControlador.cargarUsuarios();
            System.out.println("DEBUG: Datos cargados, ahora mostrando vista");
            
            // AHORA hacerla visible (después de cargar)
            vistaGestion.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            vistaGestion.setVisible(true); // Mostrar aunque falle
        }
    });
  }
}