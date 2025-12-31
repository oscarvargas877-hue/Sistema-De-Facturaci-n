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
    if (cedula.length() != 10 || !cedula.matches("\\d{10}")) {
        vistaFacturacion.mostrarMensajeError("La cédula debe tener exactamente 10 dígitos numéricos.");
        return;
    }

    ClienteModelo cliente = ClienteModelo.buscarPorCedula(cedula);

    if (cliente != null) {
        // Cliente encontrado → mostrar datos
        vistaFacturacion.mostrarDatosCliente(
            cliente.getNombresApellidos(),
            cliente.getDireccion() != null ? cliente.getDireccion() : ""
        );
        vistaFacturacion.mostrarMensajeConColor(
            "Cliente encontrado: " + cliente.getNombresApellidos(),
            java.awt.Color.GREEN
        );
        vistaFacturacion.habilitarEdicionCliente(false); // Bloquear edición

    } else {
        // Cliente NO encontrado → permitir crear nuevo
        vistaFacturacion.limpiarDatosCliente();
        vistaFacturacion.mostrarMensajeConColor(
            "Cliente no registrado. Ingrese nombres/apellidos y dirección.",
            java.awt.Color.ORANGE
        );
        vistaFacturacion.habilitarEdicionCliente(true); // Permitir edición
        vistaFacturacion.enfocarNombresApellidos();
    }
}
    
    // MÉTODO FINALIZAR VENTA - CON VALIDACIONES COMPLETAS Y SIN BUGS
    public void finalizarVenta() {
    if (listaDetalles.isEmpty()) {
        vistaFacturacion.mostrarMensajeError("No hay productos en la factura. Agregue al menos uno.");
        return;
    }

    String cedula = vistaFacturacion.obtenerCedulaIngresada().trim();
    String nombresApellidos = vistaFacturacion.obtenerNombresApellidosIngresado().trim();
    String direccion = vistaFacturacion.obtenerDireccionClienteIngresada().trim();

    // ================== VALIDACIONES OBLIGATORIAS ==================
    if (cedula.isEmpty()) {
        vistaFacturacion.mostrarMensajeError("La cédula del cliente es obligatoria.");
        return;
    }
    if (cedula.length() != 10 || !cedula.matches("\\d{10}")) {
        vistaFacturacion.mostrarMensajeError("La cédula debe tener exactamente 10 dígitos numéricos.");
        return;
    }

    if (nombresApellidos.isEmpty()) {
        vistaFacturacion.mostrarMensajeError("Los nombres y apellidos del cliente son obligatorios.");
        return;
    }

    if (direccion.isEmpty()) {
        vistaFacturacion.mostrarMensajeError("La dirección del cliente es obligatoria.");
        return;
    }

    vistaFacturacion.mostrarMensajeEspera("Procesando venta...");

    double total = calcularTotalActual();

    new Thread(() -> {
        try {
            Integer idCliente = null;

            // ================== SIEMPRE BUSCAR CLIENTE POR CÉDULA ==================
            ClienteModelo cliente = ClienteModelo.buscarPorCedula(cedula);

            if (cliente != null) {
                // Cliente ya existe → usar su ID
                idCliente = cliente.getIdCliente();
            } else {
                // Cliente no existe → crear nuevo
                ClienteModelo nuevo = new ClienteModelo();
                nuevo.setCedula(cedula);
                nuevo.setNombresApellidos(nombresApellidos);
                nuevo.setDireccion(direccion);
                nuevo.setTelefono(null);
                nuevo.setGenero(null);

                if (!nuevo.guardar()) {
                    java.awt.EventQueue.invokeLater(() -> {
                        vistaFacturacion.ocultarMensajeEspera();
                        vistaFacturacion.mostrarMensajeError("Error al crear el cliente nuevo. Puede que la cédula ya exista.");
                    });
                    return;
                }

                // Buscar de nuevo para obtener el ID
                ClienteModelo creado = ClienteModelo.buscarPorCedula(cedula);
                if (creado != null) {
                    idCliente = creado.getIdCliente();
                } else {
                    java.awt.EventQueue.invokeLater(() -> {
                        vistaFacturacion.ocultarMensajeEspera();
                        vistaFacturacion.mostrarMensajeError("Cliente creado pero no se pudo obtener su ID.");
                    });
                    return;
                }
            }

            // ================== GUARDAR FACTURA ==================
            FacturaModelo factura = new FacturaModelo(idCajero, idCliente, nombresApellidos, total);
            boolean exito = factura.guardarFacturaYDetalles(listaDetalles);

            java.awt.EventQueue.invokeLater(() -> {
                vistaFacturacion.ocultarMensajeEspera();
                if (exito) {
                    VistaVerFactura vistaVer = new VistaVerFactura(
                        factura.getIdFactura(),
                        nombresApellidos,
                        cedula,
                        direccion,
                        listaDetalles,
                        total
                    );
                    vistaVer.establecerVistaFacturacion(vistaFacturacion);
                    vistaVer.setVisible(true);
                    vistaFacturacion.dispose();
                } else {
                    vistaFacturacion.mostrarMensajeError("Error al guardar la factura.");
                }
            });

        } catch (Exception e) {
            java.awt.EventQueue.invokeLater(() -> {
                vistaFacturacion.ocultarMensajeEspera();
                vistaFacturacion.mostrarMensajeError("Error inesperado: " + e.getMessage());
                e.printStackTrace();
            });
        }
    }).start();
}

    public void volverAlMenu() {
        vistaFacturacion.dispose();
        vistaMenuCajero.setVisible(true);
    }

    public void limpiarVenta() {
        listaDetalles.clear();
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