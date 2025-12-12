/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

// Clase que representa un ítem dentro de una factura
public class DetalleFacturaModelo {

    private int idDetalle;
    private int idFactura;
    private int idProducto;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double descuentoAplicado; // 0.00 a 1.00 (ej: 0.10 = 10%)
    private double subtotal;

    public DetalleFacturaModelo() {}

    // Constructor para crear un detalle desde la lógica de facturación
    public DetalleFacturaModelo(int idProducto, String nombreProducto, int cantidad, double precioUnitario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;

        // Aplicar descuento si la cantidad es >= 5
        if (cantidad >= 5) {
            this.descuentoAplicado = 0.10; // 10%
        } else {
            this.descuentoAplicado = 0.00;
        }

        //  Calcular subtotal: (precio * cantidad) * (1 - descuento)
        this.subtotal = (precioUnitario * cantidad) * (1 - this.descuentoAplicado);
    }

    // Getters y Setters
    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }

    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public double getDescuentoAplicado() { return descuentoAplicado; }
    public void setDescuentoAplicado(double descuentoAplicado) { this.descuentoAplicado = descuentoAplicado; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}