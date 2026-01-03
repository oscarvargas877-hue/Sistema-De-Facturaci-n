/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClienteModelo;
import Modelo.DetalleFacturaModelo;
import Modelo.FacturaModelo;
import Modelo.ProductoModelo;
import static Modelo.UsuarioModelo.validarCedulaEcuatoriana;
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

        // Enfocar cédula al abrir
        java.awt.EventQueue.invokeLater(() -> {
            vistaFacturacion.enfocarCampoCedula();
        });
    }

    // ================== CARGAR PRODUCTOS EN COMBO ==================
    public void cargarProductos() {
        List<ProductoModelo> productos = ProductoModelo.obtenerTodosLosProductos();
        vistaFacturacion.cargarProductos(productos);
    }

    // ================== INTENTAR AGREGAR PRODUCTO (con todas las validaciones) ==================
    public void intentarAgregarProducto(String nombreProducto, String cantidadStr) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            vistaFacturacion.mostrarMensajeError("Por favor seleccione un producto.");
            return;
        }

        if (cantidadStr.isEmpty()) {
            vistaFacturacion.mostrarMensajeError("Por favor ingrese una cantidad.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                vistaFacturacion.mostrarMensajeError("La cantidad debe ser mayor que 0.");
                return;
            }
        } catch (NumberFormatException e) {
            vistaFacturacion.mostrarMensajeError("La cantidad debe ser un número válido.");
            return;
        }

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

        // Crear detalle (el descuento se calcula automáticamente en el constructor de DetalleFacturaModelo)
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

               // Limpiar cantidad y enfocar para próximo producto (rapidez)
        java.awt.EventQueue.invokeLater(() -> {
            vistaFacturacion.limpiarYEnfocarCantidad();
        });
    }

    // ================== BUSCAR CLIENTE POR CÉDULA ==================
    
    public void buscarClientePorCedula() {
        String cedula = vistaFacturacion.obtenerCedulaIngresada().trim();  // Obtiene de la vista

        if (cedula.isEmpty()) {
            vistaFacturacion.mostrarMensajeError("Por favor ingrese la cédula del cliente.");
            vistaFacturacion.enfocarCampoCedula();
            return;
        }

        if (cedula.length() != 10 || !cedula.matches("\\d{10}")) {
            vistaFacturacion.mostrarMensajeError("La cédula debe tener exactamente 10 dígitos numéricos.");
            vistaFacturacion.enfocarCampoCedula();
            return;
        }

        if (!validarCedulaEcuatoriana(cedula)) {
            vistaFacturacion.mostrarMensajeError("La cédula ingresada no es válida según el algoritmo ecuatoriano.");
            vistaFacturacion.enfocarCampoCedula();
            return;
        }

        ClienteModelo cliente = ClienteModelo.buscarPorCedula(cedula);
        if (cliente != null) {
            vistaFacturacion.mostrarDatosCliente(cliente.getNombresApellidos(), cliente.getDireccion());
            vistaFacturacion.mostrarMensajeConColor("Cliente encontrado: " + cliente.getNombresApellidos(), java.awt.Color.GREEN);
            vistaFacturacion.habilitarEdicionCliente(false);
        } else {
            vistaFacturacion.limpiarDatosCliente();
            vistaFacturacion.mostrarMensajeConColor("Cliente no registrado. Ingrese nombres/apellidos y dirección.", java.awt.Color.ORANGE);
            vistaFacturacion.habilitarEdicionCliente(true);
            vistaFacturacion.enfocarNombresApellidos();
        }
    }

    // ================== FINALIZAR VENTA ==================
    public void finalizarVenta() {
        if (listaDetalles.isEmpty()) {
            vistaFacturacion.mostrarMensajeError("No hay productos en la factura. Agregue al menos uno.");
            return;
        }

        String cedula = vistaFacturacion.obtenerCedulaIngresada().trim();
        String nombresApellidos = vistaFacturacion.obtenerNombresApellidosIngresado().trim();
        String direccion = vistaFacturacion.obtenerDireccionClienteIngresada().trim();

        if (cedula.isEmpty()) {
            vistaFacturacion.mostrarMensajeError("La cédula del cliente es obligatoria.");
            return;
        }
        if (cedula.length() != 10 || !cedula.matches("\\d{10}")) {
            vistaFacturacion.mostrarMensajeError("La cédula debe tener exactamente 10 dígitos numéricos.");
            return;
        }
        if (!validarCedulaEcuatoriana(cedula)) {
            vistaFacturacion.mostrarMensajeError("La cédula ingresada no es válida según el algoritmo ecuatoriano.");
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

        double subtotal = calcularTotalActual();
        double iva = Math.round(subtotal * 0.15 * 100.0) / 100.0; // IVA 15% Ecuador 2026?
        double totalConIva = subtotal + iva;

        new Thread(() -> {
            try {
                Integer idCliente = null;

                ClienteModelo cliente = ClienteModelo.buscarPorCedula(cedula);
                if (cliente != null) {
                    idCliente = cliente.getIdCliente();
                } else {
                    ClienteModelo nuevo = new ClienteModelo();
                    nuevo.setCedula(cedula);
                    nuevo.setNombresApellidos(nombresApellidos);
                    nuevo.setDireccion(direccion);
                    nuevo.setTelefono(null);
                    nuevo.setGenero(null);

                    if (!nuevo.guardar()) {
                        java.awt.EventQueue.invokeLater(() -> {
                            vistaFacturacion.ocultarMensajeEspera();
                            vistaFacturacion.mostrarMensajeError("Error al crear el cliente nuevo.");
                        });
                        return;
                    }

                    ClienteModelo creado = ClienteModelo.buscarPorCedula(cedula);
                    if (creado != null) {
                        idCliente = creado.getIdCliente();
                    }
                }

                FacturaModelo factura = new FacturaModelo(idCajero, idCliente, nombresApellidos, subtotal);
                boolean exito = factura.guardarFacturaYDetalles(listaDetalles);

                java.awt.EventQueue.invokeLater(() -> {
                    vistaFacturacion.ocultarMensajeEspera();
                    if (exito) {
                        // Crear la vista de factura impresa
                        VistaVerFactura vistaVer = new VistaVerFactura(
                            factura.getIdFactura(),
                            nombresApellidos,
                            cedula,
                            direccion,
                            listaDetalles,
                            subtotal,
                            iva,
                            totalConIva
                        );

                        // Inyectar el nuevo controlador (pasa la vista de facturación actual)
                        ControladorVerFactura controladorVer = new ControladorVerFactura(vistaVer, vistaFacturacion);
                        vistaVer.establecerControlador(controladorVer);

                        // Mostrar factura impresa y ocultar facturación temporalmente
                        vistaVer.setVisible(true);
                        vistaFacturacion.setVisible(false);  // Oculta mientras se ve la factura impresa

                        // NO llamamos a limpiarVenta() aquí → lo hace el controlador al aceptar
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

    // ================== VOLVER AL MENÚ ==================
    public void volverAlMenu() {
        vistaFacturacion.dispose();
        vistaMenuCajero.setVisible(true);
    }

    // ================== LIMPIAR VENTA ==================
       public void limpiarVenta() {
        listaDetalles.clear();
        java.awt.EventQueue.invokeLater(() -> {
            vistaFacturacion.limpiarCamposParaNuevaVenta();  // Ya limpia txtTotal a "0.00"
            vistaFacturacion.limpiarDatosCliente();
            vistaFacturacion.enfocarCampoCedula();

            // Actualizar tabla y total con lista vacía
            vistaFacturacion.actualizarDetalleYTotal(new ArrayList<>(), 0.0);
        });
    }

    // ================== CALCULAR TOTAL ==================
    private double calcularTotalActual() {
        double total = 0.0;
        for (DetalleFacturaModelo detalle : listaDetalles) {
            total += detalle.getSubtotal();
        }
        return total;
    }
}