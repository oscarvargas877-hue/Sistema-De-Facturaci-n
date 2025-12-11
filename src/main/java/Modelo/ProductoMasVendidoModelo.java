/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Modelo/ProductoMasVendidoModelo.java
package Modelo;

// Representa una fila del reporte "productos más vendidos"
public class ProductoMasVendidoModelo {

    private String nombreProducto;
    private int cantidadTotalVendida;

    public ProductoMasVendidoModelo() {}

    public ProductoMasVendidoModelo(String nombreProducto, int cantidadTotalVendida) {
        this.nombreProducto = nombreProducto;
        this.cantidadTotalVendida = cantidadTotalVendida;
    }

    // Getters y Setters
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public int getCantidadTotalVendida() { return cantidadTotalVendida; }
    public void setCantidadTotalVendida(int cantidadTotalVendida) { this.cantidadTotalVendida = cantidadTotalVendida; }
}