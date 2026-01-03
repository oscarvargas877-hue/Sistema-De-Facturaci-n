/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.UsuarioModelo;
import Vista.VistaLogin;
import Vista.VistaPrimerAdmin;
import java.sql.SQLException;
import javax.swing.Timer;

// Clase que controla el flujo de creación del primer administrador
public class ControladorPrimerAdmin {

    // Referencia a la vista de creación del primer admin
    private VistaPrimerAdmin vistaPrimerAdmin;

    // Constructor que recibe la vista
    public ControladorPrimerAdmin(VistaPrimerAdmin vista) {
        this.vistaPrimerAdmin = vista;
    }

    // Método actualizado: recibe todos los datos personales
    public void crearPrimerAdministrador(String nombreUsuario, String contrasenia,
                                         String cedula, String direccion, int edad, String genero) {

        UsuarioModelo nuevoAdmin = new UsuarioModelo(nombreUsuario, contrasenia, "administrador");
        nuevoAdmin.setCedula(cedula);
        nuevoAdmin.setDireccion(direccion);
        nuevoAdmin.setEdad(edad);
        nuevoAdmin.setGenero(genero);
        nuevoAdmin.setActivo(true);

        try {
            nuevoAdmin.guardar(); // Aquí se valida la cédula y lanza excepción si inválida

            // ================== ÉXITO: solo si llega aquí ==================
            vistaPrimerAdmin.mostrarMensajeExito("¡Primer Administrador creado exitosamente!");

            Timer timer = new Timer(2000, e -> {
                vistaPrimerAdmin.dispose();
                VistaLogin vistaLogin = new VistaLogin();
                ControladorLogin controladorLogin = new ControladorLogin(vistaLogin);
                vistaLogin.establecerControlador(controladorLogin);
                vistaLogin.setVisible(true);
            });
            timer.setRepeats(false);
            timer.start();

        } catch (IllegalArgumentException ex) {
            // Errores de validación (cédula inválida, campos vacíos, etc.)
            vistaPrimerAdmin.mostrarMensajeError(ex.getMessage());

        } catch (SQLException ex) {
            // Errores de base de datos (duplicados, conexión, etc.)
            String mensajeError = "Error al guardar en la base de datos.";

            if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("duplicate entry")) {
                if (ex.getMessage().toLowerCase().contains("nombreusuario")) {
                    mensajeError = "El nombre de usuario ya existe.";
                } else if (ex.getMessage().toLowerCase().contains("cedula")) {
                    mensajeError = "La cédula ya está registrada.";
                } else {
                    mensajeError = "El nombre de usuario o la cédula ya existe.";
                }
            }

            vistaPrimerAdmin.mostrarMensajeError(mensajeError);
            ex.printStackTrace();
        } catch (Exception ex) {
            vistaPrimerAdmin.mostrarMensajeError("Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
        public void intentarCrearPrimerAdministrador(String nombreUsuario, String contrasenia,
                                                String cedula, String direccion, String edadStr,
                                                String genero) {

        // ================== VALIDACIÓN DE NOMBRE DE USUARIO ==================
        if (nombreUsuario.isEmpty()) {
            vistaPrimerAdmin.mostrarMensajeError("El nombre de usuario es obligatorio.");
            return;
        }
        if (nombreUsuario.length() < 4) {
            vistaPrimerAdmin.mostrarMensajeError("El nombre de usuario debe tener al menos 4 caracteres.");
            return;
        }
        if (nombreUsuario.contains(" ")) {
            vistaPrimerAdmin.mostrarMensajeError("El nombre de usuario no puede contener espacios.");
            return;
        }

        // ================== VALIDACIÓN DE CONTRASEÑA ==================
        if (contrasenia.isEmpty()) {
            vistaPrimerAdmin.mostrarMensajeError("La contraseña es obligatoria.");
            return;
        }
        if (contrasenia.length() < 4) {
            vistaPrimerAdmin.mostrarMensajeError("La contraseña debe tener al menos 4 caracteres.");
            return;
        }

        // ================== VALIDACIÓN DE CÉDULA ==================
        if (cedula.isEmpty()) {
            vistaPrimerAdmin.mostrarMensajeError("La cédula es obligatoria.");
            return;
        }
        if (cedula.length() != 10) {
            vistaPrimerAdmin.mostrarMensajeError("La cédula debe tener exactamente 10 dígitos.");
            return;
        }
        if (!cedula.matches("\\d{10}")) {
            vistaPrimerAdmin.mostrarMensajeError("La cédula solo debe contener números.");
            return;
        }
        if (!Modelo.UsuarioModelo.validarCedulaEcuatoriana(cedula)) {
            vistaPrimerAdmin.mostrarMensajeError("La cédula ingresada no es válida verifique.");
            return;
        }

        // ================== VALIDACIÓN DE DIRECCIÓN ==================
        if (direccion.isEmpty()) {
            vistaPrimerAdmin.mostrarMensajeError("La dirección es obligatoria.");
            return;
        }
        if (direccion.length() < 5) {
            vistaPrimerAdmin.mostrarMensajeError("La dirección debe tener al menos 5 caracteres.");
            return;
        }

        // ================== VALIDACIÓN DE EDAD ==================
        if (edadStr.isEmpty()) {
            vistaPrimerAdmin.mostrarMensajeError("La edad es obligatoria.");
            return;
        }
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
            if (edad < 18 || edad > 60) {
                vistaPrimerAdmin.mostrarMensajeError("La edad debe estar entre 18 y 60 años.");
                return;
            }
        } catch (NumberFormatException ex) {
            vistaPrimerAdmin.mostrarMensajeError("La edad debe ser un número válido.");
            return;
        }

        // ================== VALIDACIÓN DE GÉNERO ==================
        if (genero == null) {
            vistaPrimerAdmin.mostrarMensajeError("Por favor seleccione un género.");
            return;
        }

        // ================== SI TODO PASA → CREAR EL PRIMER ADMIN ==================
        // Llamar al método 
        crearPrimerAdministrador(nombreUsuario, contrasenia, cedula, direccion, edad, genero);

    }
}