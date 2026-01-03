/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controlador;

import Modelo.UsuarioModelo;
import Vista.VistaMenuAdmin;
import Vista.VistaRegistrarUsuario;
import java.sql.SQLException;
import javax.swing.Timer;

// Controlador para el registro de nuevos usuarios (HU003)
public class ControladorRegistrarUsuario {

    // Referencias a las vistas
    private VistaRegistrarUsuario vistaRegistrar;
    private VistaMenuAdmin vistaMenuAdmin;

    // Constructor que recibe ambas vistas
    public ControladorRegistrarUsuario(VistaRegistrarUsuario vista, VistaMenuAdmin menuAdmin) {
        this.vistaRegistrar = vista;
        this.vistaMenuAdmin = menuAdmin;
    }

    // Método para registrar un nuevo usuario
          public void registrarUsuario(String nombreUsuario, String contrasenia, String rol,
                                 String cedula, String direccion, int edad, String genero) {

        // ================== VALIDACIONES BÁSICAS ==================
        if (nombreUsuario.isEmpty() || contrasenia.isEmpty() || cedula.isEmpty() ||
            direccion.isEmpty() || edad <= 0) {
            vistaRegistrar.mostrarMensajeError("Todos los campos son obligatorios.");
            return;
        }

        if (nombreUsuario.contains(" ")) {
            vistaRegistrar.mostrarMensajeError("El nombre de usuario no puede contener espacios.");
            return;
        }

        if (edad < 18 || edad > 120) {
            vistaRegistrar.mostrarMensajeError("Edad no válida (18-120 años).");
            return;
        }

        // ================== VALIDAR SELECCIÓN DE ROL ==================
        if (rol == null || rol.isEmpty() || 
            (!rol.equals("administrador") && !rol.equals("cajero"))) {
            vistaRegistrar.mostrarMensajeError("Por favor seleccione un rol (Administrador o Cajero).");
            return;
        }

        // ================== VALIDAR SELECCIÓN DE GÉNERO ==================
        if (genero == null || genero.isEmpty() || 
            (!genero.equals("Masculino") && !genero.equals("Femenino"))) {
            vistaRegistrar.mostrarMensajeError("Por favor seleccione un género.");
            return;
        }

        // ================== CREAR Y GUARDAR USUARIO ==================
        UsuarioModelo nuevoUsuario = new UsuarioModelo(nombreUsuario, contrasenia, rol);
        nuevoUsuario.setCedula(cedula);
        nuevoUsuario.setDireccion(direccion);
        nuevoUsuario.setEdad(edad);
        nuevoUsuario.setGenero(genero);
        nuevoUsuario.setActivo(true);

        try {
            nuevoUsuario.guardar(); // Aquí valida cédula y lanza excepción si inválida

            vistaRegistrar.mostrarMensajeExito("¡Usuario registrado exitosamente!");

            Timer timer = new Timer(2000, e -> {
                vistaRegistrar.dispose();
                vistaMenuAdmin.setVisible(true);
            });
            timer.setRepeats(false);
            timer.start();

        } catch (IllegalArgumentException ex) {
            vistaRegistrar.mostrarMensajeError(ex.getMessage());

        } catch (SQLException ex) {
            String mensaje = "Error al guardar el usuario.";
            if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("duplicate entry")) {
                mensaje = "El nombre de usuario o la cédula ya existe.";
            }
            vistaRegistrar.mostrarMensajeError(mensaje);

        } catch (Exception ex) {
            vistaRegistrar.mostrarMensajeError("Error inesperado.");
            ex.printStackTrace();
        }
    }

    // Método para cancelar el registro y volver al menú
    public void cancelar() {
        vistaRegistrar.dispose();
        vistaMenuAdmin.setVisible(true);
    }

    // Método para volver al menú (usado por el botón "Atrás")
    public void volverAlMenu() {
        vistaRegistrar.dispose();
        vistaMenuAdmin.setVisible(true);
    }
    
    
    public void intentarRegistrarUsuario(String nombreUsuario, String contrasenia,
                                        String cedula, String direccion, String edadStr,
                                        String genero, String rol) {

        // ================== VALIDACIÓN DE NOMBRE DE USUARIO ==================
        if (nombreUsuario.isEmpty()) {
            vistaRegistrar.mostrarMensajeError("El nombre de usuario es obligatorio.");
            return;
        }
        if (nombreUsuario.length() < 4) {
            vistaRegistrar.mostrarMensajeError("El nombre de usuario debe tener al menos 4 caracteres.");
            return;
        }
        if (nombreUsuario.contains(" ")) {
            vistaRegistrar.mostrarMensajeError("El nombre de usuario no puede contener espacios.");
            return;
        }

        // ================== VALIDACIÓN DE CONTRASEÑA ==================
        if (contrasenia.isEmpty()) {
            vistaRegistrar.mostrarMensajeError("La contraseña es obligatoria.");
            return;
        }
        if (contrasenia.length() < 4) {  
            vistaRegistrar.mostrarMensajeError("La contraseña debe tener al menos 4 caracteres.");
            return;
        }

        // ================== VALIDACIÓN DE CÉDULA ==================
        if (cedula.isEmpty()) {
            vistaRegistrar.mostrarMensajeError("La cédula es obligatoria.");
            return;
        }
        if (cedula.length() != 10) {
            vistaRegistrar.mostrarMensajeError("La cédula debe tener exactamente 10 dígitos.");
            return;
        }
        if (!cedula.matches("\\d{10}")) {
            vistaRegistrar.mostrarMensajeError("La cédula solo debe contener números.");
            return;
        }
        if (!Modelo.UsuarioModelo.validarCedulaEcuatoriana(cedula)) {
            vistaRegistrar.mostrarMensajeError("La cédula ingresada no es válida según el algoritmo ecuatoriano.");
            return;
        }

        // ================== VALIDACIÓN DE DIRECCIÓN ==================
        if (direccion.isEmpty()) {
            vistaRegistrar.mostrarMensajeError("La dirección es obligatoria.");
            return;
        }
        if (direccion.length() < 5) {
            vistaRegistrar.mostrarMensajeError("La dirección debe tener al menos 5 caracteres.");
            return;
        }

        // ================== VALIDACIÓN DE EDAD ==================
        if (edadStr.isEmpty()) {
            vistaRegistrar.mostrarMensajeError("La edad es obligatoria.");
            return;
        }
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
            if (edad < 18 || edad > 60) {
                vistaRegistrar.mostrarMensajeError("La edad debe estar entre 18 y 60 años.");
                return;
            }
        } catch (NumberFormatException ex) {
            vistaRegistrar.mostrarMensajeError("La edad debe ser un número válido.");
            return;
        }

        // ================== VALIDACIÓN DE GÉNERO ==================
        if (genero == null) {
            vistaRegistrar.mostrarMensajeError("Por favor seleccione un género.");
            return;
        }

        // ================== VALIDACIÓN DE ROL ==================
        if (rol == null) {
            vistaRegistrar.mostrarMensajeError("Por favor seleccione un rol.");
            return;
        }

        // ================== SI TODAS LAS VALIDACIONES PASAN → REGISTRAR ==================
        // Llamamos al método existente que ya tienes en este controlador
        registrarUsuario(nombreUsuario, contrasenia, rol, cedula, direccion, edad, genero);
    }
}