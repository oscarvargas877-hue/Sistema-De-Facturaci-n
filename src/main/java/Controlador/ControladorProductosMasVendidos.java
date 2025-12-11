/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Controlador/ControladorProductosMasVendidos.java
package Controlador;

import Modelo.ProductoMasVendidoModelo;
import Modelo.ServicioProductosMasVendidos;
import Vista.VistaMenuAdmin;
import Vista.VistaProductosMasVendidos;

public class ControladorProductosMasVendidos {

    private VistaProductosMasVendidos vista;
    private VistaMenuAdmin menuAdmin;

    public ControladorProductosMasVendidos(VistaProductosMasVendidos vista, VistaMenuAdmin menuAdmin) {
        this.vista = vista;
        this.menuAdmin = menuAdmin;
    }

    // Carga los productos más vendidos en la vista
    public void cargarProductosMasVendidos() {
        java.util.List<ProductoMasVendidoModelo> lista = ServicioProductosMasVendidos.obtenerProductosMasVendidos();
        vista.mostrarProductosMasVendidos(lista);
    }

    // Regresa al menú del administrador
    public void volverAlMenu() {
        vista.dispose();
        menuAdmin.setVisible(true);
    }
}