/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


 //Clase base Persona - solo para herencia en UsuarioModelo

public abstract class Persona {

    private String cedula;
    private String direccion;
    private int edad;
    private String genero;

    // Constructor vacío
    public Persona() {}

    // Constructor con parámetros
    public Persona(String cedula, String direccion, int edad, String genero) {
        this.cedula = cedula;
        this.direccion = direccion;
        this.edad = edad;
        this.genero = genero;
    }

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}