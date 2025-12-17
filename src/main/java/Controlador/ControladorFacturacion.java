/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Controlador/ControladorFacturacion.java
// Controlador/ControladorFacturacion.java
package Controlador;

import Modelo.DetalleFacturaModelo;
import Modelo.FacturaModelo;
import Modelo.ProductoModelo;
import Vista.VistaFacturacion;
import Vista.VistaMenuCajero;
import Vista.VistaVerFactura;
import java.util.ArrayList;
import java.util.List;

// Controlador para la facturación (HU005)
public class ControladorFacturacion {

    private VistaFacturacion vistaFacturacion;
    private VistaMenuCajero vistaMenuCajero;
    private List<DetalleFacturaModelo> listaDetalles;
    private int idCajero;

    public ControladorFacturacion(VistaFacturacion vista, VistaMenuCajero menuCajero, int idCajero) {
        this.vistaFacturacion = vista;
        this.vistaMenuCajero = menuCajero;
        this.idCajero = idCajero;
        this.listaDetalles = new ArrayList<>();
    }

    public void cargarProductos() {
        List<ProductoModelo> productos = ProductoModelo.obtenerTodosLosProductos();
        vistaFacturacion.cargarProductos(productos);
    }

    public void agregarProducto(String nombreProducto, int cantidad) {
        ProductoModelo producto = ProductoModelo.obtenerProductoPorNombre(nombreProducto);
        if (producto == null) {
            vistaFacturacion.mostrarMensajeError("Producto no encontrado.");
            return;
        }

        // Validar stock usando ID (seguro y preciso)
        if (!ProductoModelo.verificarStockSuficiente(producto.getIdProducto(), cantidad)) {
            int stockDisponible = ProductoModelo.obtenerStockDisponible(producto.getIdProducto());
            vistaFacturacion.mostrarMensajeError(
                "Stock insuficiente. Quedan " + stockDisponible + " unidades."
            );
            return;
        }

        // Crear detalle
        DetalleFacturaModelo detalle = new DetalleFacturaModelo(
            producto.getIdProducto(),
            producto.getNombre(),
            cantidad,
            producto.getPrecio()
        );
        listaDetalles.add(detalle);

        // Actualizar vista
        double total = calcularTotalActual();
        vistaFacturacion.actualizarDetalleYTotal(listaDetalles, total);
    }

    public void finalizarVenta(String cliente) {
        if (listaDetalles.isEmpty()) {
            vistaFacturacion.mostrarMensajeError("No hay productos en la factura.");
            return;
        }
        
        vistaFacturacion.mostrarMensajeEspera("Espere un momento...");

        double total = calcularTotalActual();

        // Ejecutar en segundo plano para evitar congelamiento
        new Thread(() -> {
            FacturaModelo factura = new FacturaModelo(idCajero, cliente, total);
            boolean exito = factura.guardarFacturaYDetalles(listaDetalles);

            java.awt.EventQueue.invokeLater(() -> {
                if (exito) {
                    // Mostrar factura generada
                    VistaVerFactura vistaVerFactura = new VistaVerFactura(cliente, listaDetalles, total);
                    vistaVerFactura.establecerVistaFacturacion(vistaFacturacion);
                    vistaVerFactura.setVisible(true);
                    vistaFacturacion.dispose();
                } else {
                    vistaFacturacion.mostrarMensajeError(
                        "Error al guardar la factura. Verifique el stock e intente de nuevo."
                    );
                }
            });
        }).start();
    }

    // Método para volver al menú de facturación
    public void volverAlMenu() {
        vistaFacturacion.dispose();
        vistaMenuCajero.setVisible(true);
    }
    
         public void limpiarVenta() {
        // Limpiar la lista de detalles en el modelo
        listaDetalles.clear();

        // Actualizar la vista usando el método público
        java.awt.EventQueue.invokeLater(() -> {
            vistaFacturacion.limpiarCamposParaNuevaVenta();
        });
    }

    private double calcularTotalActual() {
        double total = 0.0;
        for (DetalleFacturaModelo detalle : listaDetalles) {
            total += detalle.getSubtotal();
        }
        return total;
    }
    
}