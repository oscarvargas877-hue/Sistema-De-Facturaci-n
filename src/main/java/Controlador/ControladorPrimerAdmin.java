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
}