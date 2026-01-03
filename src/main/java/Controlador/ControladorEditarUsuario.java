/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
   
   
       public void intentarGuardarCambios(int idUsuario, String nuevoNombre, String nuevaCedula,
                                       String nuevaDireccion, String edadStr,
                                       String nuevoGenero, String nuevoRol) {

        // ================== VALIDACIÓN DE NOMBRE DE USUARIO ==================
        if (nuevoNombre.isEmpty()) {
            vistaEditar.mostrarMensajeError("El nombre de usuario es obligatorio.");
            return;
        }
        if (nuevoNombre.length() < 4) {
            vistaEditar.mostrarMensajeError("El nombre de usuario debe tener al menos 4 caracteres.");
            return;
        }
        if (nuevoNombre.contains(" ")) {
            vistaEditar.mostrarMensajeError("El nombre de usuario no puede contener espacios.");
            return;
        }

        // ================== VALIDACIÓN DE CÉDULA ==================
        if (nuevaCedula.isEmpty()) {
            vistaEditar.mostrarMensajeError("La cédula es obligatoria.");
            return;
        }
        if (nuevaCedula.length() != 10) {
            vistaEditar.mostrarMensajeError("La cédula debe tener exactamente 10 dígitos.");
            return;
        }
        if (!nuevaCedula.matches("\\d{10}")) {
            vistaEditar.mostrarMensajeError("La cédula solo debe contener números.");
            return;
        }
        if (!Modelo.UsuarioModelo.validarCedulaEcuatoriana(nuevaCedula)) {
            vistaEditar.mostrarMensajeError("La cédula ingresada no es válida según el algoritmo ecuatoriano.");
            return;
        }

        // ================== VALIDACIÓN DE DIRECCIÓN ==================
        if (nuevaDireccion.isEmpty()) {
            vistaEditar.mostrarMensajeError("La dirección es obligatoria.");
            return;
        }
        if (nuevaDireccion.length() < 5) {
            vistaEditar.mostrarMensajeError("La dirección debe tener al menos 5 caracteres.");
            return;
        }

        // ================== VALIDACIÓN DE EDAD ==================
        if (edadStr.isEmpty()) {
            vistaEditar.mostrarMensajeError("La edad es obligatoria.");
            return;
        }
        int nuevaEdad;
        try {
            nuevaEdad = Integer.parseInt(edadStr);
            if (nuevaEdad < 18 || nuevaEdad > 60) {
                vistaEditar.mostrarMensajeError("La edad debe estar entre 18 y 60 años.");
                return;
            }
        } catch (NumberFormatException ex) {
            vistaEditar.mostrarMensajeError("La edad debe ser un número válido.");
            return;
        }

        // ================== VALIDACIÓN DE GÉNERO ==================
        if (nuevoGenero == null) {
            vistaEditar.mostrarMensajeError("Por favor seleccione un género.");
            return;
        }

        // ================== VALIDACIÓN DE ROL ==================
        if (nuevoRol == null) {
            vistaEditar.mostrarMensajeError("Por favor seleccione un rol.");
            return;
        }

        // ================== SI TODAS LAS VALIDACIONES PASAN → ACTUALIZAR MODELO ==================
        UsuarioModelo usuarioModificado = new UsuarioModelo();
        usuarioModificado.setIdUsuario(idUsuario);
        usuarioModificado.setNombreUsuario(nuevoNombre);
        usuarioModificado.setCedula(nuevaCedula);
        usuarioModificado.setDireccion(nuevaDireccion);
        usuarioModificado.setEdad(nuevaEdad);
        usuarioModificado.setGenero(nuevoGenero);
        usuarioModificado.setRol(nuevoRol);

        // Llamar al método existente que ya tienes para guardar
        guardarCambios(usuarioModificado);
    }
}