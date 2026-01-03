/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Usuario
 */

import Vista.VistaFacturacion;
import Vista.VistaVerFactura;

public class ControladorVerFactura {

    private VistaVerFactura vistaVerFactura;
    private VistaFacturacion vistaFacturacion; // Referencia a la ventana anterior

    // Constructor recibe ambas vistas
    public ControladorVerFactura(VistaVerFactura vistaVer, VistaFacturacion vistaFact) {
        this.vistaVerFactura = vistaVer;
        this.vistaFacturacion = vistaFact;
    }

    // Método llamado al presionar "Aceptar"
    public void aceptar() {
        vistaVerFactura.dispose(); // Cerrar factura impresa

        // Volver a facturación y preparar nueva venta
        vistaFacturacion.limpiarCamposParaNuevaVenta();
        vistaFacturacion.limpiarDatosCliente();
        vistaFacturacion.actualizarDetalleYTotal(new java.util.ArrayList<>(), 0.0);
        vistaFacturacion.enfocarCampoCedula();
        vistaFacturacion.setVisible(true);
    }
    
}