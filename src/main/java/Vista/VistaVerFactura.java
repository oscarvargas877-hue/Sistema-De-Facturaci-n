/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.DetalleFacturaModelo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;  // ← Este es el correcto
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */


public class VistaVerFactura extends javax.swing.JFrame {
    private int numeroFactura;
    private String nombresApellidos;
    private String cedula;
    private String direccion;
    private List<DetalleFacturaModelo> detalle;
    private double totalSinIVA;
    private VistaFacturacion vistaFacturacion;
    /**
     * Creates new form VistaVerFactura
     */
    public VistaVerFactura(int numeroFactura, String nombresApellidos, String cedula, String direccion,
                           List<DetalleFacturaModelo> detalle, double totalSinIVA) {
        initComponents();
       this.numeroFactura = numeroFactura;
        this.nombresApellidos = nombresApellidos;
        this.cedula = cedula;
        this.direccion = direccion.isEmpty() ? "No registrada" : direccion;
        this.detalle = detalle;
        this.totalSinIVA = totalSinIVA;

        getContentPane().setBackground(Color.WHITE);

        cargarFacturaCompleta();  // ← Este método sí lo mantienes

        // Fondo blanco como factura real
        getContentPane().setBackground(Color.WHITE);

        // Cargar todos los datos dinámicos
        cargarFacturaCompleta();


    }
          private void cargarFacturaCompleta() {
        // === ENCABEZADO ===
        lblNombreNegocio.setFont(new Font("Arial Black", Font.BOLD, 36));
        lblNombreNegocio.setHorizontalAlignment(JLabel.CENTER);
        lblNombreNegocio.setText("SUPERMERCADOS TUTI S.A.");

        lblRuc.setFont(new Font("Arial", Font.BOLD, 22));
        lblRuc.setHorizontalAlignment(JLabel.CENTER);
        lblRuc.setText("RUC: 1793000000001");

        lblDireccionNegocio.setFont(new Font("Arial", Font.PLAIN, 20));
        lblDireccionNegocio.setHorizontalAlignment(JLabel.CENTER);
        lblDireccionNegocio.setText("Dirección matriz: Av. Principal y Calle Secundaria, Eugenio Espejo - Calderón");

        // Número de factura + fecha y hora actuales
        String numFacturaStr = String.format("%03d-%03d-%09d", 1, 1, numeroFactura);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String fecha = dateFormat.format(new Date());
        String hora = timeFormat.format(new Date());

        lblNumeroFechaHora.setFont(new Font("Arial", Font.BOLD, 20));
        lblNumeroFechaHora.setHorizontalAlignment(JLabel.CENTER);
        lblNumeroFechaHora.setText("Factura N.º: " + numFacturaStr + "     Fecha: " + fecha + "     Hora: " + hora);

        // === DATOS DEL CLIENTE ===
        lblNombreCliente.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombreCliente.setText("Nombres y apellidos: " + nombresApellidos);

        lblCedula.setFont(new Font("Arial", Font.BOLD, 18));
        lblCedula.setText("Cédula: " + cedula);

        lblDireccion.setFont(new Font("Arial", Font.BOLD, 18));
        lblDireccion.setText("Dirección: " + this.direccion);

        // === TABLA DETALLE ===
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"Producto", "Cantidad", "Precio", "Descuento", "Subtotal"}, 0
        );

        double subtotal = 0;
        for (DetalleFacturaModelo item : detalle) {
            String descuentoStr = String.format("%.0f%%", item.getDescuentoAplicado() * 100);
            modelo.addRow(new Object[]{
                item.getNombreProducto(),
                item.getCantidad(),
                String.format("%.2f", item.getPrecioUnitario()),
                descuentoStr,
                String.format("%.2f", item.getSubtotal())
            });
            subtotal += item.getSubtotal();
        }

        tablaDetalle.setModel(modelo);
        tablaDetalle.setRowHeight(40);
        tablaDetalle.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 20));
        tablaDetalle.getTableHeader().setBackground(new Color(0, 102, 102));
        tablaDetalle.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tablaDetalle.getColumnCount(); i++) {
            tablaDetalle.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // === TOTALES CON IVA 15% ===
        double iva = subtotal * 0.15;
        double totalFinal = subtotal + iva;

        lblIva.setFont(new Font("Arial Black", Font.BOLD, 24));
        lblIva.setText("IVA 15%: $" + String.format("%.2f", iva));

        lblTotal.setFont(new Font("Arial Black", Font.BOLD, 30));
        lblTotal.setForeground(new Color(0, 130, 0));
        lblTotal.setText("Total: $" + String.format("%.2f", totalFinal));

        // === GRACIAS ===
        lblGracias.setFont(new Font("Arial", Font.ITALIC, 28));
        lblGracias.setForeground(new Color(0, 102, 102));
        lblGracias.setHorizontalAlignment(JLabel.CENTER);
        lblGracias.setText("Gracias por su compra");

        // === BOTÓN ACEPTAR ===
        btnAceptar.setFont(new Font("Arial Black", Font.BOLD, 24));
        btnAceptar.setBackground(new Color(46, 204, 113));
        btnAceptar.setForeground(Color.WHITE);
    }
          
           // Método para actualizar la tabla de detalle

    private void actualizarTablaDetalle() {
        // Crear el modelo de la tabla
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
            new Object[]{"Producto", "Cantidad", "Precio", "Descuento", "Subtotal"}, 0
        );

        // Llenar la tabla con los detalles
        if (detalle != null && !detalle.isEmpty()) {
            for (Modelo.DetalleFacturaModelo item : detalle) {
                String descuentoStr = String.format("%.0f%%", item.getDescuentoAplicado() * 100);
                modelo.addRow(new Object[]{
                    item.getNombreProducto(),
                    item.getCantidad(),
                    String.format("%.2f", item.getPrecioUnitario()),
                    descuentoStr,
                    String.format("%.2f", item.getSubtotal())
                });
            }
        } else {
            modelo.addRow(new Object[]{"-", "-", "-", "-", "-"});
        }
        

        // Asignar el modelo a la tabla
        tablaDetalle.setModel(modelo);
        //PARA QUE LAS FILAS NO SEAN EDITABLES
        tablaDetalle.setDefaultEditor(Object.class, null);

        // ️ Forzar que la tabla se redimensione y se vea completa
        tablaDetalle.setPreferredScrollableViewportSize(new Dimension(800, 600));
        tablaDetalle.setFillsViewportHeight(true);

        // Refrescar el scrollpane
        ScrollDetalleFactura.revalidate();
        ScrollDetalleFactura.repaint();
    }
        public void establecerVistaFacturacion(VistaFacturacion vista) {
        this.vistaFacturacion = vista;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollDetalleFactura = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        PanelEncabezado = new javax.swing.JPanel();
        lblNombreNegocio = new javax.swing.JLabel();
        lblRuc = new javax.swing.JLabel();
        lblDireccionNegocio = new javax.swing.JLabel();
        lblNumeroFechaHora = new javax.swing.JLabel();
        PanelCliente = new javax.swing.JPanel();
        lblNombreCliente = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        PanelTotales = new javax.swing.JPanel();
        lblIva = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblGracias = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaDetalle.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        tablaDetalle.setForeground(new java.awt.Color(0, 102, 102));
        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio", "Descuento", "Subtotal"
            }
        ));
        ScrollDetalleFactura.setViewportView(tablaDetalle);

        lblNombreNegocio.setText("Supermercados Tuti");

        lblRuc.setText("Ruc: 1793000000001");

        lblDireccionNegocio.setText("Dirección Matriz Av Principal y Calle Secundaria, Espejo- Guzman");

        lblNumeroFechaHora.setText("Factura N°                                                                  Fecha:                                                                Hora:");

        javax.swing.GroupLayout PanelEncabezadoLayout = new javax.swing.GroupLayout(PanelEncabezado);
        PanelEncabezado.setLayout(PanelEncabezadoLayout);
        PanelEncabezadoLayout.setHorizontalGroup(
            PanelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEncabezadoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(PanelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumeroFechaHora, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                    .addGroup(PanelEncabezadoLayout.createSequentialGroup()
                        .addGroup(PanelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDireccionNegocio)
                            .addComponent(lblRuc))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(PanelEncabezadoLayout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(lblNombreNegocio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelEncabezadoLayout.setVerticalGroup(
            PanelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreNegocio)
                .addGap(24, 24, 24)
                .addComponent(lblRuc)
                .addGap(12, 12, 12)
                .addComponent(lblDireccionNegocio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumeroFechaHora)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        lblNombreCliente.setText("Nombres y apellidos:              ");

        lblCedula.setText("Cédula: ");

        lblDireccion.setText("Dirección:");

        javax.swing.GroupLayout PanelClienteLayout = new javax.swing.GroupLayout(PanelCliente);
        PanelCliente.setLayout(PanelClienteLayout);
        PanelClienteLayout.setHorizontalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelClienteLayout.createSequentialGroup()
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelClienteLayout.createSequentialGroup()
                        .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PanelClienteLayout.setVerticalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblNombreCliente)
                .addGap(18, 18, 18)
                .addComponent(lblCedula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDireccion)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        lblIva.setText("IVA 15%:                     ");

        lblTotal.setText("Total: ");

        javax.swing.GroupLayout PanelTotalesLayout = new javax.swing.GroupLayout(PanelTotales);
        PanelTotales.setLayout(PanelTotalesLayout);
        PanelTotalesLayout.setHorizontalGroup(
            PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTotalesLayout.createSequentialGroup()
                .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTotalesLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblIva))
                    .addGroup(PanelTotalesLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblTotal)))
                .addContainerGap(369, Short.MAX_VALUE))
        );
        PanelTotalesLayout.setVerticalGroup(
            PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTotalesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblIva)
                .addGap(18, 18, 18)
                .addComponent(lblTotal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblGracias.setText("Gracias por su compra");

        btnAceptar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(0, 102, 102));
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(lblGracias)
                .addGap(70, 70, 70)
                .addComponent(btnAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PanelCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(PanelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(152, 152, 152))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ScrollDetalleFactura)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(PanelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollDetalleFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGracias)
                    .addComponent(btnAceptar))
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
    // Cerrar esta ventana y regresar al menú del cajero
        if (vistaFacturacion != null) {
            vistaFacturacion.setVisible(true);
           }
           this.dispose();

    }//GEN-LAST:event_btnAceptarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCliente;
    private javax.swing.JPanel PanelEncabezado;
    private javax.swing.JPanel PanelTotales;
    private javax.swing.JScrollPane ScrollDetalleFactura;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDireccionNegocio;
    private javax.swing.JLabel lblGracias;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNombreNegocio;
    private javax.swing.JLabel lblNumeroFechaHora;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaDetalle;
    // End of variables declaration//GEN-END:variables
}
