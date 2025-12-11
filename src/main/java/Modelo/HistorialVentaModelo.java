/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;


public class HistorialVentaModelo {

    private int idFactura;
    private String fechaHora;
    private String cajero;
    private String cliente;
    private double total;

    // Constructor vacío
    public HistorialVentaModelo() {}

    // Constructor con parámetros
    public HistorialVentaModelo(int idFactura, String fechaHora, String cajero, String cliente, double total) {
        this.idFactura = idFactura;
        this.fechaHora = fechaHora;
        this.cajero = cajero;
        this.cliente = cliente;
        this.total = total;
    }

    // Getters y Setters
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public String getCajero() { return cajero; }
    public void setCajero(String cajero) { this.cajero = cajero; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}