/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

import BDD.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

public class FacturaModelo {
    private Integer idCliente;
    private int idFactura;
    private String fechaHora;
    private int idCajero;
    private String cliente;
    private double subtotal;
    private double iva;
    private double totalConIva;

    public FacturaModelo() {}

    public FacturaModelo(int idCajero, Integer idCliente, String nombreClienteSiNoRegistrado, double subtotal) {
        this.idCajero = idCajero;
        this.idCliente = idCliente;
        this.cliente = nombreClienteSiNoRegistrado;
        this.subtotal = subtotal;
    }

    public boolean guardarFacturaYDetalles(List<DetalleFacturaModelo> detalles) {
    Connection conexion = null;
    CallableStatement stmtFactura = null;
    CallableStatement stmtDetalle = null;
    CallableStatement stmtStock = null;

    try {
        ConexionBDD conexionBDD = new ConexionBDD();
        conexion = conexionBDD.conectar();
        if (conexion == null) {
            return false;
        }
        conexion.setAutoCommit(false);

        // Calcular IVA y total con IVA
        double ivaCalculado = Math.round(this.subtotal * 0.15 * 100.0) / 100.0;
        double totalConIvaCalculado = this.subtotal + ivaCalculado;

        // Llamar al SP sp_crear_factura con los 3 parámetros de dinero (subtotal, iva, total_con_iva)
        stmtFactura = conexion.prepareCall("{CALL sp_crear_factura(?,?,?,?,?,?,?)}");
        stmtFactura.setInt(1, this.idCajero);
        if (this.idCliente != null) {
            stmtFactura.setInt(2, this.idCliente);
        } else {
            stmtFactura.setNull(2, Types.INTEGER);
        }
        stmtFactura.setString(3, this.cliente != null && !this.cliente.trim().isEmpty() ? this.cliente : "Consumidor Final");
        stmtFactura.setDouble(4, this.subtotal);          // p_subtotal
        stmtFactura.setDouble(5, ivaCalculado);           // p_iva (calculado: 15%)
        stmtFactura.setDouble(6, totalConIvaCalculado);   // p_total_con_iva
        stmtFactura.registerOutParameter(7, Types.INTEGER); // OUT p_idFactura
        stmtFactura.executeUpdate();

        int idFacturaGenerado = stmtFactura.getInt(7);
        this.idFactura = idFacturaGenerado;

        // Insertar cada detalle
        stmtDetalle = conexion.prepareCall("{CALL sp_crear_detalle_factura(?,?,?,?,?,?)}");
        for (DetalleFacturaModelo detalle : detalles) {
            stmtDetalle.setInt(1, idFacturaGenerado);
            stmtDetalle.setInt(2, detalle.getIdProducto());
            stmtDetalle.setInt(3, detalle.getCantidad());
            stmtDetalle.setDouble(4, detalle.getPrecioUnitario());
            stmtDetalle.setDouble(5, detalle.getDescuentoAplicado());
            stmtDetalle.setDouble(6, detalle.getSubtotal());
            stmtDetalle.executeUpdate();

            // Actualizar stock
            stmtStock = conexion.prepareCall("{CALL sp_actualizar_stock(?,?)}");
            stmtStock.setInt(1, detalle.getIdProducto());
            stmtStock.setInt(2, detalle.getCantidad());
            stmtStock.executeUpdate();
            stmtStock.close();
        }

        conexion.commit();
        return true;

    } catch (Exception e) {
        if (conexion != null) {
            try {
                conexion.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace(); // Muestra el error real en consola
        return false;
    } finally {
        try {
            if (stmtFactura != null) stmtFactura.close();
            if (stmtDetalle != null) stmtDetalle.close();
            if (stmtStock != null) stmtStock.close();
            if (conexion != null) {
                conexion.setAutoCommit(true);
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    // Getters y Setters
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public int getIdCajero() { return idCajero; }
    public void setIdCajero(int idCajero) { this.idCajero = idCajero; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }

    public double getTotalConIva() { return totalConIva; }
    public void setTotalConIva(double totalConIva) { this.totalConIva = totalConIva; }
}