/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BDD.ConexionBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteModelo {
    
    private int idCliente;
    private String cedula;
    private String nombresApellidos;  // ← CAMBIO AQUÍ
    private String direccion;
    private String telefono;
    private String genero;
// Constructor vacío - NECESARIO para instanciar y luego usar setters
public ClienteModelo() {}
    // Constructor
    public ClienteModelo(String cedula, String nombresApellidos, String direccion, String telefono, String genero) {
        this.cedula = cedula;
        this.nombresApellidos = nombresApellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.genero = genero;
    }

    // Método buscarPorCedula (actualizado)
    public static ClienteModelo buscarPorCedula(String cedula) {
        ClienteModelo cliente = null;
        String sql = "SELECT * FROM cliente WHERE cedula = ?";
        
        try (Connection con = new ConexionBDD().conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, cedula);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                cliente = new ClienteModelo();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombresApellidos(rs.getString("nombresApellidos"));  // ← CAMBIO
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setGenero(rs.getString("genero"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    // Método guardar (actualizado)
    public boolean guardar() {
        String sql = "INSERT INTO cliente (cedula, nombresApellidos, direccion, telefono, genero) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = new ConexionBDD().conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, this.cedula);
            pst.setString(2, this.nombresApellidos);  // ← CAMBIO
            pst.setString(3, this.direccion);
            pst.setString(4, this.telefono);
            pst.setString(5, this.genero);
            
            return pst.executeUpdate() > 0;
            
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Cliente ya existe con esa cédula");
            }
            e.printStackTrace();
            return false;
        }
    }

    // Getters y Setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombresApellidos() { return nombresApellidos; }  // ← CAMBIO
    public void setNombresApellidos(String nombresApellidos) { this.nombresApellidos = nombresApellidos; }  // ← CAMBIO

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}