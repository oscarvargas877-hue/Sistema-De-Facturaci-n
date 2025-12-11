/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Controlador/ControladorHistorialVentas.java
package Controlador;

import Modelo.HistorialVentaModelo;
import Modelo.ServicioHistorial;
import Vista.VistaHistorialVentas;
import Vista.VistaMenuAdmin;

public class ControladorHistorialVentas {

    private VistaHistorialVentas vistaHistorial;
    private VistaMenuAdmin vistaMenuAdmin;

    public ControladorHistorialVentas(VistaHistorialVentas vista, VistaMenuAdmin menuAdmin) {
        this.vistaHistorial = vista;
        this.vistaMenuAdmin = menuAdmin;
    }

    // Carga el historial usando el SP a través del Servicio
    public void cargarHistorial() {
        java.util.List<HistorialVentaModelo> historial = ServicioHistorial.obtenerHistorialVentas();
        vistaHistorial.cargarHistorial(historial);
    }

    public void volverAlMenu() {
        vistaHistorial.dispose();
        vistaMenuAdmin.setVisible(true);
    }
}