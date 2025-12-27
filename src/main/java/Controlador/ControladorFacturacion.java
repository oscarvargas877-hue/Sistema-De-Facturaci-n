/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controlador;

import Modelo.ClienteModelo;
import Modelo.DetalleFacturaModelo;
import Modelo.FacturaModelo;
import Modelo.ProductoModelo;
import Vista.VistaFacturacion;
import Vista.VistaMenuCajero;
import Vista.VistaVerFactura;
import java.util.ArrayList;
import java.util.List;

public class ControladorFacturacion {

    private Integer idClienteActual = null; // ID del cliente encontrado o creado
    private VistaFacturacion vistaFacturacion;
    private VistaMenuCajero vistaMenuCajero;
    private List<DetalleFacturaModelo> listaDetalles;
    private int idCajero;

    public ControladorFacturacion(VistaFacturacion vista, VistaMenuCajero menuCajero, int idCajero) {
        this.vistaFacturacion = vista;
        this.vistaMenuCajero = menuCajero;
        this.idCajero = idCajero;
        this.listaDetalles = new ArrayList<>();

        // Al abrir la ventana, enfocar el campo cédula para rapidez
        java.awt.EventQueue.invokeLater(() -> {
            vistaFacturacion.enfocarCampoCedula();
        });
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

        if (!ProductoModelo.verificarStockSuficiente(producto.getIdProducto(), cantidad)) {
            int stockDisponible = ProductoModelo.obtenerStockDisponible(producto.getIdProducto());
            vistaFacturacion.mostrarMensajeError("Stock insuficiente. Quedan " + stockDisponible + " unidades.");
            return;
        }

        DetalleFacturaModelo detalle = new DetalleFacturaModelo(
            producto.getIdProducto(),
            producto.getNombre(),
            cantidad,
            producto.getPrecio()
        );
        listaDetalles.add(detalle);

        double total = calcularTotalActual();
        vistaFacturacion.actualizarDetalleYTotal(listaDetalles, total);
    }

    // NUEVO MÉTODO: Buscar cliente al presionar Enter en el campo cédula
    public void buscarClientePorCedula() {
        String cedula = vistaFacturacion.obtenerCedulaIngresada().trim();

        if (cedula.isEmpty()) {
            vistaFacturacion.mostrarMensajeError("Por favor ingrese la cédula del cliente.");
            return;
        }

        if (!cedula.matches("\\d{10}|\\d{13}")) {
            vistaFacturacion.mostrarMensajeError("Cédula inválida. Debe tener 10 o 13 dígitos.");
            return;
        }

        ClienteModelo cliente = ClienteModelo.buscarPorCedula(cedula);

        if (cliente != null) {
            // Cliente encontrado
            this.idClienteActual = cliente.getIdCliente();
            vistaFacturacion.mostrarDatosCliente(cliente.getNombresApellidos(), cliente.getDireccion() != null ? cliente.getDireccion() : "");
            vistaFacturacion.mostrarMensajeConColor("Cliente encontrado: " + cliente.getNombresApellidos(), java.awt.Color.GREEN);
            vistaFacturacion.habilitarEdicionCliente(false); // Bloquea edición
        } else {
            // Cliente NO encontrado
            this.idClienteActual = null;
            vistaFacturacion.limpiarDatosCliente(); // Limpia nombres y dirección
            vistaFacturacion.mostrarMensajeConColor("Cliente no registrado. Ingrese nombres/apellidos y dirección.", java.awt.Color.ORANGE);
            vistaFacturacion.habilitarEdicionCliente(true); // Permite edición
            vistaFacturacion.enfocarNombresApellidos(); // Enfoca el campo de nombres
        }
    }

    // MÉTODO FINALIZAR VENTA ACTUALIZADO
    public void finalizarVenta(String clienteParametro) { // El parámetro ya no se usa mucho
        if (listaDetalles.isEmpty()) {
            vistaFacturacion.mostrarMensajeError("No hay productos en la factura.");
            return;
        }

        String cedula = vistaFacturacion.obtenerCedulaIngresada().trim();
        String nombresApellidos = vistaFacturacion.obtenerNombresApellidosIngresado().trim();
        String direccion = vistaFacturacion.obtenerDireccionClienteIngresada().trim();

        if (cedula.isEmpty() || nombresApellidos.isEmpty()) {
            vistaFacturacion.mostrarMensajeError("Cédula y nombres/apellidos son obligatorios.");
            return;
        }

        vistaFacturacion.mostrarMensajeEspera("Procesando venta...");

        double total = calcularTotalActual();

        new Thread(() -> {
            Integer idClienteFinal = idClienteActual;

            // Si el cliente no existía → crearlo ahora
            if (idClienteFinal == null) {
                ClienteModelo nuevoCliente = new ClienteModelo();
                nuevoCliente.setCedula(cedula);
                nuevoCliente.setNombresApellidos(nombresApellidos);
                nuevoCliente.setDireccion(direccion.isEmpty() ? null : direccion);
                nuevoCliente.setTelefono(null);
                nuevoCliente.setGenero(null);

                if (nuevoCliente.guardar()) {
                    // Obtener el ID del cliente recién creado
                    ClienteModelo creado = ClienteModelo.buscarPorCedula(cedula);
                    if (creado != null) {
                        idClienteFinal = creado.getIdCliente();
                    }
                } else {
                    java.awt.EventQueue.invokeLater(() -> {
                        vistaFacturacion.mostrarMensajeError("Error al registrar el nuevo cliente.");
                    });
                    return;
                }
            }

            // Crear y guardar la factura
            FacturaModelo factura = new FacturaModelo(idCajero, idClienteFinal, nombresApellidos, total);
            boolean exito = factura.guardarFacturaYDetalles(listaDetalles);

            java.awt.EventQueue.invokeLater(() -> {
                if (exito) {
                   VistaVerFactura vistaVerFactura = new VistaVerFactura(
        factura.getIdFactura(),                                    
                    nombresApellidos,                                           
                    cedula,                                                     
                    direccion,                                                  
                    listaDetalles,                                           
                    total                                                      
                );
                vistaVerFactura.establecerVistaFacturacion(vistaFacturacion);
                vistaVerFactura.setVisible(true);
                vistaFacturacion.dispose();
                } else {
                    vistaFacturacion.mostrarMensajeError("Error al guardar la factura.");
                }
            });
        }).start();
    }

    public void volverAlMenu() {
        vistaFacturacion.dispose();
        vistaMenuCajero.setVisible(true);
    }

    public void limpiarVenta() {
        listaDetalles.clear();
        idClienteActual = null;
        java.awt.EventQueue.invokeLater(() -> {
            vistaFacturacion.limpiarCamposParaNuevaVenta();
            vistaFacturacion.limpiarDatosCliente();
            vistaFacturacion.enfocarCampoCedula();
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