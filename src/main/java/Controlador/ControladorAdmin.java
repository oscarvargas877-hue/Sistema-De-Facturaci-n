/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Controlador/ControladorAdmin.java
package Controlador;

import Vista.VistaLogin;
import Vista.VistaMenuAdmin;
import Vista.VistaRegistrarUsuario;
import Vista.VistaGestionStock;
import Vista.VistaHistorialVentas;
import Vista.VistaProductosMasVendidos;

// Clase que controla las acciones del menú del administrador
public class ControladorAdmin {

    // Referencia al menú del administrador
    private VistaMenuAdmin vistaMenuAdmin;

    // Constructor que recibe la vista
    public ControladorAdmin(VistaMenuAdmin vista) {
        this.vistaMenuAdmin = vista;
    }

    // Abre la ventana para registrar un nuevo usuario
    public void abrirVentanaRegistrarUsuario() {
        vistaMenuAdmin.dispose(); // Cierra el menú actual
        VistaRegistrarUsuario vista = new VistaRegistrarUsuario();
        // No necesitas controlador aquí si solo muestra un formulario simple
        vista.setVisible(true);
    }

    // Abre la ventana de gestión de stock
    public void abrirVentanaGestionStock() {
        vistaMenuAdmin.dispose();
        VistaGestionStock vista = new VistaGestionStock();
        vista.setVisible(true);
    }

    // Abre la ventana del historial de ventas
    public void abrirVentanaHistorialVentas() {
        vistaMenuAdmin.dispose();
        VistaHistorialVentas vista = new VistaHistorialVentas();
        vista.setVisible(true);
    }

    // Abre la ventana de productos más vendidos
    public void abrirVentanaProductosMasVendidos() {
        vistaMenuAdmin.dispose();
        VistaProductosMasVendidos vista = new VistaProductosMasVendidos();
        vista.setVisible(true);
    }

    // Cierra la sesión del administrador y regresa al login
    public void cerrarSesion() {
        vistaMenuAdmin.dispose(); // Cierra la ventana del menú
        VistaLogin vistaLogin = new VistaLogin();
        ControladorLogin controladorLogin = new ControladorLogin(vistaLogin);
        vistaLogin.establecerControlador(controladorLogin);
        vistaLogin.setVisible(true);
    }
}