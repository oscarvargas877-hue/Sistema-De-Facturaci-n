/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Controlador/ControladorLogin.java
// Controlador/ControladorLogin.java
package Controlador;

import Modelo.UsuarioModelo;
import Vista.VistaLogin;
import Vista.VistaMenuAdmin;
import Vista.VistaMenuCajero;
import Vista.VistaPrimerAdmin;

// Clase que controla el flujo de inicio de sesión
public class ControladorLogin {

    // Referencia a la vista de login
    private VistaLogin vistaLogin;

    // Constructor que recibe la vista
    public ControladorLogin(VistaLogin vista) {
        this.vistaLogin = vista;
    }

    // Método llamado cuando el usuario hace clic en "Iniciar Sesión"
    public void validarCredenciales(String nombreUsuario, String contrasenia) {
        // Validamos las credenciales usando el modelo
        UsuarioModelo usuario = UsuarioModelo.validarLogin(nombreUsuario, contrasenia);

        if (usuario != null) {
            // Credenciales correctas: cerramos la vista de login
            vistaLogin.dispose();

            // Redirigimos según el rol
            if ("administrador".equals(usuario.getRol())) {
                VistaMenuAdmin menuAdmin = new VistaMenuAdmin();
                Controlador.ControladorAdmin controladorAdmin = new Controlador.ControladorAdmin(menuAdmin);
                menuAdmin.establecerControlador(controladorAdmin);
                menuAdmin.setVisible(true);
            } else if ("cajero".equals(usuario.getRol())) {
                VistaMenuCajero menuCajero = new VistaMenuCajero();
                Controlador.ControladorCajero controladorCajero = new Controlador.ControladorCajero(menuCajero);
                menuCajero.establecerControlador(controladorCajero);
                menuCajero.setVisible(true);
            }
        } else {
            // Credenciales incorrectas: mostramos error en la vista
            vistaLogin.mostrarMensajeError("Usuario o contraseña incorrectos.");
        }
    }

    // Método para verificar si existe un administrador al iniciar la app
    public static void verificarYConfigurarInicio() {
        if (!UsuarioModelo.existeAdministrador()) {
            // No hay admin → mostrar formulario para crear el primero
            VistaPrimerAdmin vistaPrimerAdmin = new VistaPrimerAdmin();
            vistaPrimerAdmin.setVisible(true);
        } else {
            // Ya existe admin → mostrar login
            VistaLogin vistaLogin = new VistaLogin();
            ControladorLogin controlador = new ControladorLogin(vistaLogin);
            vistaLogin.establecerControlador(controlador);
            vistaLogin.setVisible(true);
        }
    }

    // Método llamado al hacer clic en "Cerrar"
    public void cerrarAplicacion() {
        System.exit(0);
    }
}