/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

import BDD.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

public class FacturaModelo {
    private Integer idCliente;
    private int idFactura;
    private String fechaHora;
    private int idCajero;
    private String cliente;
    private double total;

    public FacturaModelo() {}

    public FacturaModelo(int idCajero, Integer idCliente, String nombreClienteSiNoRegistrado, double total) {
      this.idCajero = idCajero;
      this.idCliente = idCliente;
      this.cliente = nombreClienteSiNoRegistrado; // Mantén por compatibilidad o para clientes sin cédula
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

            // 1. Crear factura con el SP ACTUALIZADO (ahora recibe idCliente)
            stmtFactura = conexion.prepareCall("{CALL sp_crear_factura(?,?,?,?,?)}");  // 4 IN + 1 OUT

            stmtFactura.setInt(1, this.idCajero);

            // NUEVO: Manejar idCliente (puede ser null)
            if (this.idCliente != null) {
                stmtFactura.setInt(2, this.idCliente);
            } else {
                stmtFactura.setNull(2, java.sql.Types.INTEGER);
            }

            // Nombre del cliente (puede ser "Consumidor Final" o lo que escriba el cajero)
            stmtFactura.setString(3, this.cliente != null && !this.cliente.trim().isEmpty() ? this.cliente : "Consumidor Final");

            stmtFactura.setDouble(4, this.total);
            stmtFactura.registerOutParameter(5, java.sql.Types.INTEGER);  // OUT ahora es el parámetro 5

            stmtFactura.executeUpdate();

            int idFactura = stmtFactura.getInt(5);  // Cambiado de 4 a 5
            this.idFactura = idFactura;

            // Preparar SP de detalle y stock (sin cambios aquí)
            stmtDetalle = conexion.prepareCall("{CALL sp_crear_detalle_factura(?,?,?,?,?,?)}");
            stmtStock = conexion.prepareCall("{CALL sp_actualizar_stock(?,?)}");

            for (DetalleFacturaModelo d : detalles) {
                // Insertar detalle
                stmtDetalle.setInt(1, idFactura);
                stmtDetalle.setInt(2, d.getIdProducto());
                stmtDetalle.setInt(3, d.getCantidad());
                stmtDetalle.setDouble(4, d.getPrecioUnitario());
                stmtDetalle.setDouble(5, d.getDescuentoAplicado());
                stmtDetalle.setDouble(6, d.getSubtotal());
                stmtDetalle.executeUpdate();

                // Restar stock
                stmtStock.setInt(1, d.getIdProducto());
                stmtStock.setInt(2, d.getCantidad());
                stmtStock.executeUpdate();
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
            } catch (Exception e) { 
                e.printStackTrace(); 
            }
        }
    }

    // Getters y Setters
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public Integer getIdCliente() {return idCliente;}
    public void setIdCliente(Integer idCliente) {this.idCliente = idCliente;}

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public int getIdCajero() { return idCajero; }
    public void setIdCajero(int idCajero) { this.idCajero = idCajero; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}