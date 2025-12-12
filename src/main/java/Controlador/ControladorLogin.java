/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.UsuarioModelo;
import Vista.VistaLogin;
import Vista.VistaMenuAdmin;
import Vista.VistaMenuCajero;
import Vista.VistaPrimerAdmin;

public class ControladorLogin {

    private VistaLogin vistaLogin;

    public ControladorLogin(VistaLogin vista) {
        this.vistaLogin = vista;
    }

    // Método llamado al hacer clic en "Iniciar Sesión"
    public void validarCredenciales(String nombreUsuario, String contrasenia) {
        UsuarioModelo usuario = UsuarioModelo.validarLogin(nombreUsuario, contrasenia);

        if (usuario != null) {
            vistaLogin.dispose();

            if ("administrador".equals(usuario.getRol())) {
                VistaMenuAdmin menuAdmin = new VistaMenuAdmin();
                ControladorAdmin controladorAdmin = new ControladorAdmin(menuAdmin);
                menuAdmin.establecerControlador(controladorAdmin);
                menuAdmin.setVisible(true);
            } else if ("cajero".equals(usuario.getRol())) {
                // ← PASAR EL ID DEL USUARIO LOGUEADO
                VistaMenuCajero menuCajero = new VistaMenuCajero();
                ControladorCajero controladorCajero = new ControladorCajero(menuCajero, usuario.getIdUsuario());
                menuCajero.establecerControlador(controladorCajero);
                menuCajero.setVisible(true);
            }
        } else {
            vistaLogin.mostrarMensajeError("Usuario o contraseña incorrectos.");
        }
    }

    // Método para verificar y configurar el inicio
    public static void verificarYConfigurarInicio() {
        if (!UsuarioModelo.existeAdministrador()) {
            VistaPrimerAdmin vistaPrimerAdmin = new VistaPrimerAdmin();
            ControladorPrimerAdmin controlador = new ControladorPrimerAdmin(vistaPrimerAdmin);
            vistaPrimerAdmin.establecerControlador(controlador);
            vistaPrimerAdmin.setVisible(true);
        } else {
            VistaLogin vistaLogin = new VistaLogin();
            ControladorLogin controlador = new ControladorLogin(vistaLogin);
            vistaLogin.establecerControlador(controlador);
            vistaLogin.setVisible(true);
        }
    }

    public void cerrarAplicacion() {
        System.exit(0);
    }
}