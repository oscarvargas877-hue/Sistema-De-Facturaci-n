/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import Controlador.ControladorLogin;

/**
 *
 * @author Usuario
 */
public class SistemaFacturacion {

    public static void main(String[] args) {
        
       // Verifica si existe un administrador
        if (!Modelo.UsuarioModelo.existeAdministrador()) {
            // No existe → mostrar formulario para crear el primero
            Vista.VistaPrimerAdmin vista = new Vista.VistaPrimerAdmin();
            Controlador.ControladorPrimerAdmin controlador = new Controlador.ControladorPrimerAdmin(vista);
            vista.establecerControlador(controlador);
            vista.setVisible(true);
        } else {
            // Ya existe  mostrar login
            ControladorLogin.verificarYConfigurarInicio();
        }
    }

    
}
