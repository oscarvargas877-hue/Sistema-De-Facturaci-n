/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

import BDD.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Clase que representa un producto y contiene su lógica de negocio
public class ProductoModelo {

    // Atributos del producto
    private int idProducto;
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidadStock;

    // Constructor vacío
    public ProductoModelo() {}

    // Constructor con parámetros
    public ProductoModelo(String codigo, String nombre, double precio, int cantidadStock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }

    // Método para verificar si hay suficiente stock
    public static boolean verificarStockSuficiente(String codigoProducto, int cantidadRequerida) {
    ProductoModelo producto = obtenerProductoPorCodigo(codigoProducto);
    return producto != null && producto.getCantidadStock() >= cantidadRequerida;
    }

    // Método para obtener la cantidad disponible de un producto
    public static int obtenerStockDisponible(String codigoProducto) {
        ProductoModelo producto = obtenerProductoPorCodigo(codigoProducto);
        return producto != null ? producto.getCantidadStock() : 0;
    }

    // Método para aplicar descuento si cantidad >= 5
    public static double calcularSubtotalConDescuento(double precioUnitario, int cantidad) {
        if (cantidad >= 5) {
            return precioUnitario * cantidad * 0.9; // 10% de descuento
        }
        return precioUnitario * cantidad;
    }

    // Método para obtener el porcentaje de descuento (0.0 o 0.1)
    public static double obtenerDescuentoAplicado(int cantidad) {
        return cantidad >= 5 ? 0.10 : 0.00;
    }

    // Método para obtener un producto por su código (usando el SP sp_obtener_productos)
    public static ProductoModelo obtenerProductoPorCodigo(String codigo) {
        List<ProductoModelo> productos = obtenerTodosLosProductos();
        for (ProductoModelo p : productos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    // Método para obtener todos los productos (usa el SP sp_obtener_productos)
    public static List<ProductoModelo> obtenerTodosLosProductos() {
        List<ProductoModelo> listaProductos = new ArrayList<>();
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();

        if (conexion == null) {
            return listaProductos;
        }

        try {
            CallableStatement sentencia = conexion.prepareCall("{CALL sp_obtener_productos()}");
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                ProductoModelo producto = new ProductoModelo();
                producto.setIdProducto(resultado.getInt("idProducto"));
                producto.setCodigo(resultado.getString("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCantidadStock(resultado.getInt("cantidad_stock"));
                listaProductos.add(producto);
            }
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException excepcion) {
                excepcion.printStackTrace();
            }
        }
        return listaProductos;
    }

    // Método para actualizar el stock (usa el SP sp_actualizar_stock)
    public static void actualizarStock(int idProducto, int cantidad) {
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();

        if (conexion == null) {
            return;
        }

        try {
            CallableStatement sentencia = conexion.prepareCall("{CALL sp_actualizar_stock(?, ?)}");
            sentencia.setInt(1, idProducto);
            sentencia.setInt(2, cantidad); // Puede ser positivo (reabastecer) o negativo (vender)
            sentencia.execute();
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException excepcion) {
                excepcion.printStackTrace();
            }
        }
    }

        // Método para obtener un producto por su nombre (para facturación)
    public static ProductoModelo obtenerProductoPorNombre(String nombre) {
        List<ProductoModelo> productos = obtenerTodosLosProductos();
        for (ProductoModelo p : productos) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }
    
    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }
}