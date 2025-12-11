/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Controlador/ControladorFacturacion.java
package Controlador;

import Modelo.DetalleFacturaModelo;
import Modelo.FacturaModelo;
import Modelo.ProductoModelo;
import Vista.VistaFacturacion;
import Vista.VistaMenuCajero;
import java.util.ArrayList;
import java.util.List;

// Controlador para la facturación (HU005)
public class ControladorFacturacion {

    // Referencias a las vistas
    private VistaFacturacion vistaFacturacion;
    private VistaMenuCajero vistaMenuCajero;

    // Atributos para la lógica de la venta
    private List<DetalleFacturaModelo> listaDetalles;
    private int idCajero; // ID del cajero que está haciendo la venta

    // Constructor
    public ControladorFacturacion(VistaFacturacion vista, VistaMenuCajero menuCajero, int idCajero) {
        this.vistaFacturacion = vista;
        this.vistaMenuCajero = menuCajero;
        this.idCajero = idCajero;
        this.listaDetalles = new ArrayList<>();
    }

    // Método para cargar los productos en el combo al iniciar
    public void cargarProductos() {
        java.util.List<Modelo.ProductoModelo> productos = ProductoModelo.obtenerTodosLosProductos();
        vistaFacturacion.cargarProductos(productos);
    }

    // Método para agregar un producto al detalle de la factura
    public void agregarProducto(String nombreProducto, int cantidad) {
        // Obtener el producto por nombre
        Modelo.ProductoModelo producto = ProductoModelo.obtenerProductoPorNombre(nombreProducto);
        if (producto == null) {
            vistaFacturacion.mostrarMensajeError("Producto no encontrado.");
            return;
        }

        // Verificar stock suficiente
        if (!ProductoModelo.verificarStockSuficiente(nombreProducto, cantidad)) {
            int stockDisponible = ProductoModelo.obtenerStockDisponible(nombreProducto);
            vistaFacturacion.mostrarMensajeError(
                "Stock insuficiente. Quedan " + stockDisponible + " unidades."
            );
            return;
        }

        // Crear el detalle del ítem
        DetalleFacturaModelo detalle = new DetalleFacturaModelo(
            producto.getIdProducto(),
            producto.getNombre(),
            cantidad,
            producto.getPrecio()
        );

        // Agregar a la lista de detalles
        listaDetalles.add(detalle);

        // Calcular el total actual
        double total = calcularTotalActual();

        // Actualizar la vista
        vistaFacturacion.actualizarDetalleYTotal(listaDetalles, total);
    }

    // Método para finalizar la venta
    public void finalizarVenta(String cliente) {
        if (listaDetalles.isEmpty()) {
            vistaFacturacion.mostrarMensajeError("No hay productos en la factura.");
            return;
        }

        // Calcular el total final
        double total = calcularTotalActual();

        // Crear la factura
        FacturaModelo factura = new FacturaModelo(idCajero, cliente, total);

        // Guardar la factura y sus detalles en la BD
        boolean exito = factura.guardarFacturaYDetalles(listaDetalles);

        if (exito) {
            // Mostrar la factura generada (HU008)
            vistaFacturacion.dispose();
            Vista.VistaVerFactura vistaVerFactura = new Vista.VistaVerFactura(
                cliente, listaDetalles, total
            );
            vistaVerFactura.setVisible(true);
        } else {
            vistaFacturacion.mostrarMensajeError(
                "Error al guardar la factura. Verifique el stock e intente de nuevo."
            );
        }
    }

    // Método para volver al menú del cajero
    public void volverAlMenu() {
        vistaFacturacion.dispose();
        vistaMenuCajero.setVisible(true);
    }

    // Método privado para calcular el total actual
    private double calcularTotalActual() {
        double total = 0.0;
        for (DetalleFacturaModelo detalle : listaDetalles) {
            total += detalle.getSubtotal();
        }
        return total;
    }
}