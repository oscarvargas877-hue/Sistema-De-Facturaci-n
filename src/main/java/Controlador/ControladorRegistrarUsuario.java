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
}