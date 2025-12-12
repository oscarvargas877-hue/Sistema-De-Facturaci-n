/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Modelo/FacturaModelo.java
package Modelo;

import BDD.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

public class FacturaModelo {

    private int idFactura;
    private String fechaHora;
    private int idCajero;
    private String cliente;
    private double total;

    public FacturaModelo() {}

    public FacturaModelo(int idCajero, String cliente, double total) {
        this.idCajero = idCajero;
        this.cliente = cliente;
        this.total = total;
    }

  public boolean guardarFacturaYDetalles(List<DetalleFacturaModelo> detalles) {
    Connection conexion = null;
    CallableStatement stmtFactura = null;
    CallableStatement stmtDetalle = null;
    CallableStatement stmtStock = null;

    try {
        conexion = new ConexionBDD().conectar();
        if (conexion == null) return false;

        conexion.setAutoCommit(false);

        // 1. Crear factura con tu SP real
        stmtFactura = conexion.prepareCall("{CALL sp_crear_factura(?,?,?,?)}");
        stmtFactura.setInt(1, this.idCajero);
        stmtFactura.setString(2, this.cliente);
        stmtFactura.setDouble(3, this.total);
        stmtFactura.registerOutParameter(4, java.sql.Types.INTEGER);
        stmtFactura.executeUpdate();

        int idFactura = stmtFactura.getInt(4);
        this.idFactura = idFactura;

        // Preparar SP de detalle y stock (los que sí tienes)
        stmtDetalle = conexion.prepareCall("{CALL sp_crear_detalle_factura(?,?,?,?,?,?)}");
        stmtStock = conexion.prepareCall("{CALL sp_actualizar_stock(?,?)}");

        for (DetalleFacturaModelo d : detalles) {
            // Insertar detalle con tu SP real
            stmtDetalle.setInt(1, idFactura);
            stmtDetalle.setInt(2, d.getIdProducto());
            stmtDetalle.setInt(3, d.getCantidad());
            stmtDetalle.setDouble(4, d.getPrecioUnitario());
            stmtDetalle.setDouble(5, d.getDescuentoAplicado());  // 0.10 o 0.00
            stmtDetalle.setDouble(6, d.getSubtotal());
            stmtDetalle.executeUpdate();

            // RESTAR STOCK (tu SP ya resta y controla insuficiente)
            stmtStock.setInt(1, d.getIdProducto());
            stmtStock.setInt(2, d.getCantidad());
            stmtStock.executeUpdate();
        }

        conexion.commit();
        return true;

    } catch (Exception e) {
        if (conexion != null) {
            try { conexion.rollback(); } catch (Exception ex) {}
        }
        e.printStackTrace();
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
        } catch (Exception e) { e.printStackTrace(); }
    }
}

    // Getters y Setters
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public int getIdCajero() { return idCajero; }
    public void setIdCajero(int idCajero) { this.idCajero = idCajero; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}