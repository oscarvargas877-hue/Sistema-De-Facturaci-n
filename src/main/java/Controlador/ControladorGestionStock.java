/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Controlador/ControladorGestionStock.java
package Controlador;

import Modelo.ProductoModelo;
import Vista.VistaGestionStock;
import Vista.VistaMenuAdmin;
import java.awt.Color;

// Controlador para la gestión de stock (HU004)
public class ControladorGestionStock {

    // Referencias a las vistas
    private VistaGestionStock vistaGestionStock;
    private VistaMenuAdmin vistaMenuAdmin;

    // Constructor
    public ControladorGestionStock(VistaGestionStock vista, VistaMenuAdmin menuAdmin) {
        this.vistaGestionStock = vista;
        this.vistaMenuAdmin = menuAdmin;
    }

    // Método para cargar y mostrar los productos al abrir la ventana
    public void cargarProductos() {
        java.util.List<Modelo.ProductoModelo> listaProductos = ProductoModelo.obtenerTodosLosProductos();
        vistaGestionStock.cargarProductos(listaProductos);
    }

    // Método para reabastecer el stock de un producto
    public void reabastecerStock(String codigoProducto, int cantidad) {
        if (cantidad <= 0) {
            vistaGestionStock.mostrarMensajeConColor("La cantidad debe ser mayor que 0.", Color.WHITE);
            return;
        }

        Modelo.ProductoModelo producto = ProductoModelo.obtenerProductoPorCodigo(codigoProducto);
        if (producto == null) {
            vistaGestionStock.mostrarMensajeConColor("Producto no encontrado. Verifique el código.", Color.WHITE);
            return;
        }

        ProductoModelo.reabastecerStock(producto.getIdProducto(), cantidad);
        cargarProductos();
        vistaGestionStock.mostrarMensajeConColor("Stock actualizado correctamente", Color.WHITE);
    }

    // Método para volver al menú del administrador
    public void volverAlMenu() {
        vistaGestionStock.dispose();
        vistaMenuAdmin.setVisible(true);
    }
}