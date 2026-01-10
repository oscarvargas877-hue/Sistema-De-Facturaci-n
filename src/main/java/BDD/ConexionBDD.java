/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDD;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ConexionBDD {
    java.sql.Connection conexion;
  public java.sql.Connection conectar(){
    try {
        // Si ya hay conexión, verifica si está viva
        if (conexion != null && !conexion.isClosed() && conexion.isValid(5)) {  // 5 segundos timeout de prueba
            return conexion;
        }
        // Si no, crea nueva
        Class.forName("com.mysql.cj.jdbc.Driver");  // ← Cambiado a driver moderno (mejor que el viejo)
        conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost/sistema_facturacion?autoReconnect=true&useSSL=false&serverTimezone=UTC",
            "root",
            "1004262877"
        );
        System.out.println("CONECTADO A LA BASE DE DATOS");
        return conexion;
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("ERROR DE CONEXIÓN A LA BASE DE DATOS: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
  }
}