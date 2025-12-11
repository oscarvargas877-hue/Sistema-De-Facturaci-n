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
}