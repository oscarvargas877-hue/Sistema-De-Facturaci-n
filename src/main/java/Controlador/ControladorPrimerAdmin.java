/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Controlador/ControladorPrimerAdmin.java
package Controlador;

import Modelo.UsuarioModelo;
import Vista.VistaLogin;
import Vista.VistaPrimerAdmin;

// Clase que controla el flujo de creación del primer administrador
public class ControladorPrimerAdmin {

    // Referencia a la vista de creación del primer admin
    private VistaPrimerAdmin vistaPrimerAdmin;

    // Constructor que recibe la vista
    public ControladorPrimerAdmin(VistaPrimerAdmin vista) {
        this.vistaPrimerAdmin = vista;
    }

    // Método llamado cuando el usuario hace clic en "Crear Primer Administrador"
    public void crearPrimerAdministrador(String nombreUsuario, String contrasenia) {
        // Creamos un nuevo objeto UsuarioModelo con rol fijo "administrador"
        UsuarioModelo nuevoAdmin = new UsuarioModelo(nombreUsuario, contrasenia, "administrador");

        try {
            // Guardamos el usuario en la base de datos
            nuevoAdmin.guardar();

            // Si todo sale bien, cerramos la vista actual
            vistaPrimerAdmin.dispose();

            // Mostramos la pantalla de login
            VistaLogin vistaLogin = new VistaLogin();
            ControladorLogin controladorLogin = new ControladorLogin(vistaLogin);
            vistaLogin.establecerControlador(controladorLogin);
            vistaLogin.setVisible(true);

        } catch (Exception excepcion) {
            // Si ocurre un error (ej: nombre de usuario duplicado), lo manejamos
            if (excepcion.getMessage() != null && excepcion.getMessage().contains("Duplicate entry")) {
                vistaPrimerAdmin.mostrarMensajeError("El nombre de usuario ya existe. Elija otro.");
            } else {
                vistaPrimerAdmin.mostrarMensajeError("Error al crear el administrador. Intente de nuevo.");
                excepcion.printStackTrace();
            }
        }
    }
}