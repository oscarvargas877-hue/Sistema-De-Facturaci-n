/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controlador;

import Vista.VistaFacturacion;
import Vista.VistaLogin;
import Vista.VistaMenuCajero;

public class ControladorCajero {

    private VistaMenuCajero vistaMenuCajero;
    private int idCajero; // ← ID del cajero logueado

    // Constructor: recibe el ID del usuario logueado
    public ControladorCajero(VistaMenuCajero vista, int idCajero) {
        this.vistaMenuCajero = vista;
        this.idCajero = idCajero;
    }

    // Abrir ventana de facturación con el ID correcto
    public void abrirVentanaFacturacion() {
        vistaMenuCajero.dispose();
        VistaFacturacion vista = new VistaFacturacion();
        ControladorFacturacion controlador = new ControladorFacturacion(vista, vistaMenuCajero, idCajero);
        vista.establecerControlador(controlador);
        vista.setVisible(true);
        controlador.cargarProductos();
    }

    // Cerrar sesión y volver al login
    public void cerrarSesion() {
        vistaMenuCajero.dispose();
        VistaLogin vistaLogin = new VistaLogin();
        ControladorLogin controladorLogin = new ControladorLogin(vistaLogin);
        vistaLogin.establecerControlador(controladorLogin);
        vistaLogin.setVisible(true);
    }
}