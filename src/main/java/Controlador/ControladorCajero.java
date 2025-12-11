/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Controlador/ControladorCajero.java
package Controlador;

import Vista.VistaFacturacion;
import Vista.VistaLogin;
import Vista.VistaMenuCajero;

// Clase que controla las acciones del menú del cajero
public class ControladorCajero {

    // Referencia al menú del cajero
    private VistaMenuCajero vistaMenuCajero;

    // Constructor que recibe la vista
    public ControladorCajero(VistaMenuCajero vista) {
        this.vistaMenuCajero = vista;
    }

    // Abre la ventana de facturación (HU005)
    public void abrirVentanaFacturacion() {
        int idCajero = 1; // ← ¡Este valor debe venir del login o de una sesión activa!
        vistaMenuCajero.dispose();
        VistaFacturacion vista = new VistaFacturacion();
        ControladorFacturacion controlador = new ControladorFacturacion(vista, vistaMenuCajero, idCajero);
        vista.establecerControlador(controlador);
        vista.setVisible(true);
        controlador.cargarProductos();
    }

    // Cierra la sesión del cajero y regresa al login (HU010, CA10)
    public void cerrarSesion() {
        vistaMenuCajero.dispose(); // Cierra la ventana del menú
        VistaLogin vistaLogin = new VistaLogin();
        ControladorLogin controladorLogin = new ControladorLogin(vistaLogin);
        vistaLogin.establecerControlador(controladorLogin);
        vistaLogin.setVisible(true);
    }
}