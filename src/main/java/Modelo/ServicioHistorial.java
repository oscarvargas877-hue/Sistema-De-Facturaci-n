/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Modelo/ServicioHistorial.java
package Modelo;

import BDD.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicioHistorial {

    // Método que llama al Stored Procedure sp_obtener_historial_ventas
    public static List<HistorialVentaModelo> obtenerHistorialVentas() {
        List<HistorialVentaModelo> historial = new ArrayList<>();
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();

        if (conexion == null) {
            return historial;
        }

        try {
            // Llamar al Stored Procedure
            CallableStatement stmt = conexion.prepareCall("{CALL sp_obtener_historial_ventas()}");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HistorialVentaModelo venta = new HistorialVentaModelo(
                    rs.getInt("idFactura"),
                    rs.getString("fechaHora"),
                    rs.getString("cajero"),
                    rs.getString("cliente"),
                    rs.getDouble("total")
                );
                historial.add(venta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return historial;
    }
    
    
        // Método para obtener el detalle de una factura específica usando el SP
        public static List<DetalleFacturaModelo> obtenerDetalleFactura(int idFactura) {
        List<DetalleFacturaModelo> detalle = new ArrayList<>();
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();

        if (conexion == null) {
            return detalle; // Lista vacía si no hay conexión
        }

        try {
            CallableStatement stmt = conexion.prepareCall("{CALL sp_obtener_detalle_factura(?)}");
            stmt.setInt(1, idFactura);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DetalleFacturaModelo item = new DetalleFacturaModelo();
                item.setNombreProducto(rs.getString("producto"));
                item.setCantidad(rs.getInt("cantidad"));
                item.setPrecioUnitario(rs.getDouble("precio"));
                item.setDescuentoAplicado(rs.getDouble("descuento_porcentaje") / 100); // Convertir 10% → 0.10
                item.setSubtotal(rs.getDouble("subtotal"));
                detalle.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return detalle;
    }
}