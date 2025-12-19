/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Controlador/ControladorHistorialVentas.java
package Controlador;

import Modelo.DetalleFacturaModelo;
import Modelo.HistorialVentaModelo;
import Modelo.ServicioHistorial;
import Vista.VistaHistorialVentas;
import Vista.VistaMenuAdmin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

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
    
        // Método para mostrar el detalle de una factura en una ventana emergente
    public void mostrarDetalleFactura(int idFactura) {
        List<DetalleFacturaModelo> detalle = ServicioHistorial.obtenerDetalleFactura(idFactura);

        // Crear ventana emergente
        JDialog dialog = new JDialog(vistaHistorial, "Detalle de Factura #" + idFactura, true);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(vistaHistorial);
        dialog.setLayout(new BorderLayout());

        // Modelo de tabla para el detalle
        DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Producto", "Cantidad", "Precio", "Descuento", "Subtotal"}, 0
        );

        double totalFactura = 0;
        for (DetalleFacturaModelo item : detalle) {
            String descuentoStr = String.format("%.0f%%", item.getDescuentoAplicado() * 100);
            modelo.addRow(new Object[]{
                item.getNombreProducto(),
                item.getCantidad(),
                String.format("%.2f", item.getPrecioUnitario()),
                descuentoStr,
                String.format("%.2f", item.getSubtotal())
            });
            totalFactura += item.getSubtotal();
        }

        JTable tablaDetalle = new JTable(modelo);
        tablaDetalle.setRowHeight(35);
        tablaDetalle.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 16));
        tablaDetalle.getTableHeader().setBackground(new Color(0, 102, 102));
        tablaDetalle.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tablaDetalle);

        // Label con el total
        JLabel lblTotal = new JLabel("TOTAL: " + String.format("%.2f", totalFactura));
        lblTotal.setFont(new Font("Arial Black", Font.BOLD, 30));
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setBackground(new Color(30, 30, 40));
        lblTotal.setOpaque(true);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(lblTotal, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.getContentPane().setBackground(new Color(30, 30, 40));
        dialog.setVisible(true);
    }
}