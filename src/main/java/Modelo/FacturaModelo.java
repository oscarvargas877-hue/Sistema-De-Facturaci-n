/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Modelo/FacturaModelo.java
package Modelo;

import BDD.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// Clase que representa una factura y contiene la lógica para guardarla
public class FacturaModelo {

    private int idFactura;
    private String fechaHora;
    private int idCajero;
    private String cliente;
    private double total;

    // Constructor vacío
    public FacturaModelo() {}

    // Constructor para crear una factura
    public FacturaModelo(int idCajero, String cliente, double total) {
        this.idCajero = idCajero;
        this.cliente = cliente;
        this.total = total;
        // La fecha y hora se establecen al guardar en la BD (por defecto en MySQL)
    }

    // Método para guardar la factura y sus detalles en la BD
    public boolean guardarFacturaYDetalles(List<DetalleFacturaModelo> listaDetalles) {
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();

        if (conexion == null) {
            return false;
        }

        try {
            // Desactivar el autocommit para manejar la transacción
            conexion.setAutoCommit(false);

            // Paso 1: Crear la factura
            CallableStatement stmtFactura = conexion.prepareCall("{CALL sp_crear_factura(?, ?, ?)}");
            stmtFactura.setInt(1, this.idCajero);
            stmtFactura.setString(2, this.cliente);
            stmtFactura.setDouble(3, this.total);
            stmtFactura.execute();

            // Obtener el ID de la factura creada (asumiendo que el SP devuelve un resultado)
            // Como tu SP devuelve un SELECT, necesitamos ejecutar una consulta adicional
            // O modificar el SP para usar un parámetro OUT (pero por ahora, usamos MAX(id))
            int idFacturaGenerada = obtenerUltimoIdFactura(conexion);

            // Paso 2: Guardar cada detalle de la factura
            for (DetalleFacturaModelo detalle : listaDetalles) {
                // Validar stock antes de insertar
                if (!ProductoModelo.verificarStockSuficiente(detalle.getNombreProducto(), detalle.getCantidad())) {
                    conexion.rollback();
                    return false; // Stock insuficiente
                }

                CallableStatement stmtDetalle = conexion.prepareCall("{CALL sp_crear_detalle_factura(?, ?, ?, ?, ?, ?)}");
                stmtDetalle.setInt(1, idFacturaGenerada);
                stmtDetalle.setInt(2, detalle.getIdProducto());
                stmtDetalle.setInt(3, detalle.getCantidad());
                stmtDetalle.setDouble(4, detalle.getPrecioUnitario());
                stmtDetalle.setDouble(5, detalle.getDescuentoAplicado());
                stmtDetalle.setDouble(6, detalle.getSubtotal());
                stmtDetalle.execute();

                // Actualizar el stock (restar la cantidad vendida)
                ProductoModelo.actualizarStock(detalle.getIdProducto(), -detalle.getCantidad());
            }

            // Confirmar la transacción
            conexion.commit();
            return true;

        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                conexion.setAutoCommit(true);
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método auxiliar para obtener el último ID de factura (alternativa si el SP no devuelve OUT)
    private int obtenerUltimoIdFactura(Connection conexion) throws SQLException {
        java.sql.Statement stmt = conexion.createStatement();
        java.sql.ResultSet rs = stmt.executeQuery("SELECT MAX(idFactura) AS ultimo_id FROM Factura");
        if (rs.next()) {
            return rs.getInt("ultimo_id");
        }
        return 0;
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