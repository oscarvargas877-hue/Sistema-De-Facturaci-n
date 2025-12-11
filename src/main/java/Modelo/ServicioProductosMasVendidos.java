/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Modelo/ServicioProductosMasVendidos.java
package Modelo;

import BDD.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicioProductosMasVendidos {

    // Obtiene la lista de productos más vendidos usando el SP
    public static List<ProductoMasVendidoModelo> obtenerProductosMasVendidos() {
        List<ProductoMasVendidoModelo> lista = new ArrayList<>();
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();

        if (conexion == null) {
            return lista;
        }

        try {
            CallableStatement stmt = conexion.prepareCall("{CALL sp_obtener_productos_mas_vendidos()}");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductoMasVendidoModelo producto = new ProductoMasVendidoModelo(
                    rs.getString("producto"),
                    rs.getInt("cantidad_total_vendida")
                );
                lista.add(producto);
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
        return lista;
    }
}