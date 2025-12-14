/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;

/**
 *
 * @author Usuario
 */
public class VistaVerFactura extends javax.swing.JFrame {
 // Atributos privados para almacenar los datos
    private String cliente;
    private java.util.List<Modelo.DetalleFacturaModelo> detalle;
    private double total;
    // Atributo para guardar la referencia a la vista de facturación
    private VistaFacturacion vistaFacturacion;
    /**
     * Creates new form VistaVerFactura
     */
    public VistaVerFactura(String cliente, java.util.List<Modelo.DetalleFacturaModelo> detalle, double total) {
        initComponents();
         this.cliente = cliente;
        this.detalle = detalle;
        this.total = total;
        

    // CONFIGURACIÓN GENERAL 
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    getContentPane().setBackground(new Color(30, 30, 40));

    //  REMOVER LAYOUT ANTIGUO 
    getContentPane().removeAll();
    getContentPane().setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 40, 10, 40);

    // TÍTULO 
    lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 70));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setHorizontalAlignment(JLabel.CENTER);
    gbc.gridx = 0; gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    getContentPane().add(lblTitulo, gbc);

    //  CLIENTE Y FECHA 
    JPanel panelInfo = new JPanel();
    panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.X_AXIS));
    panelInfo.setOpaque(false);

    lblCliente.setFont(new Font("Arial Black", Font.BOLD, 36));
    lblCliente.setForeground(Color.WHITE);
    lblCliente.setAlignmentX(Component.LEFT_ALIGNMENT);
    panelInfo.add(Box.createHorizontalGlue());
    panelInfo.add(lblCliente);

    panelInfo.add(Box.createHorizontalStrut(50));

    lblFechaHora.setFont(new Font("Arial Black", Font.BOLD, 36));
    lblFechaHora.setForeground(Color.WHITE);
    lblFechaHora.setAlignmentX(Component.RIGHT_ALIGNMENT);
    panelInfo.add(lblFechaHora);
    panelInfo.add(Box.createHorizontalGlue());

    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    getContentPane().add(panelInfo, gbc);

    //  DETALLE VENTA 
    lblDetalleVenta.setFont(new Font("Arial Black", Font.BOLD, 45));
    lblDetalleVenta.setForeground(Color.WHITE);
    lblDetalleVenta.setHorizontalAlignment(JLabel.CENTER);
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    getContentPane().add(lblDetalleVenta, gbc);

    // TABLA 
    tablaDetalle.setFont(new Font("Arial", Font.PLAIN, 32));
    tablaDetalle.setRowHeight(70);
    tablaDetalle.setBackground(Color.WHITE);
    tablaDetalle.setForeground(Color.BLACK);
    tablaDetalle.setGridColor(Color.LIGHT_GRAY);
    tablaDetalle.setShowGrid(true);
    tablaDetalle.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 34));
    tablaDetalle.getTableHeader().setBackground(new Color(0, 102, 102));
    tablaDetalle.getTableHeader().setForeground(Color.WHITE);
    tablaDetalle.getTableHeader().setReorderingAllowed(false);

    ScrollDetalleFactura.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 102), 4));
    ScrollDetalleFactura.setViewportView(tablaDetalle);
    ScrollDetalleFactura.setMinimumSize(new Dimension(1200, 250)); // ← Más baja

    gbc.gridy = 3;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 0.6;
    getContentPane().add(ScrollDetalleFactura, gbc);

    
    // TOTAL A LA IZQUIERDA 
    JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
    panelTotal.setOpaque(false);

    lblTotal.setFont(new Font("Arial Black", Font.BOLD, 50));
    lblTotal.setForeground(Color.WHITE);
    panelTotal.add(lblTotal);

    txtTotal.setFont(new Font("Arial Black", Font.BOLD, 50));
    txtTotal.setForeground(Color.WHITE);
    txtTotal.setBackground(new Color(30, 30, 40));
    txtTotal.setHorizontalAlignment(JTextField.CENTER);
    txtTotal.setEditable(false);
    txtTotal.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
    txtTotal.setPreferredSize(new Dimension(200, 60)); // ← Un poco más pequeño
    panelTotal.add(txtTotal);

    gbc.gridy = 4;
    gbc.weighty = 0.05;
    gbc.fill = GridBagConstraints.HORIZONTAL; // ← Ocupa todo el ancho
    gbc.anchor = GridBagConstraints.WEST;     // ← Pegado a la izquierda
    getContentPane().add(panelTotal, gbc);

   //  BOTÓN ACEPTAR 
    btnAceptar.setFont(new Font("Arial Black", Font.BOLD, 36));
    btnAceptar.setBackground(new Color(46, 204, 113));
    btnAceptar.setForeground(Color.WHITE);
    btnAceptar.setFocusPainted(false);
    btnAceptar.setPreferredSize(new Dimension(280, 70)); // Ancho fijo de 280px

    gbc.gridy = 5;
    gbc.weighty = 0.02;
    gbc.fill = GridBagConstraints.NONE; // ⭐️ ESTO ES CLAVE: evita que se estire
    gbc.anchor = GridBagConstraints.WEST; // Alineado a la izquierda
    gbc.insets = new Insets(5, 40, 30, 40);
    getContentPane().add(btnAceptar, gbc);
   
    //  CARGAR DATOS 
    cargarDatos();

    //  FORZAR ACTUALIZACIÓN 
    revalidate();
    repaint();

    }

         // Método para cargar los datos en la vista
    private void cargarDatos() {
        // Mostrar cliente
        lblCliente.setText("Cliente: " + cliente);

        // Mostrar fecha y hora actual
        java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        lblFechaHora.setText("Fecha y Hora: " + ahora.format(formatter));

        // Actualizar la tabla de detalle
        actualizarTablaDetalle();

        // Actualizar el total
        txtTotal.setText(String.format("%.2f", total));
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

        // ️ Forzar que la tabla se redimensione y se vea completa
        tablaDetalle.setPreferredScrollableViewportSize(new Dimension(800, 600));
        tablaDetalle.setFillsViewportHeight(true);

        // Refrescar el scrollpane
        ScrollDetalleFactura.revalidate();
        ScrollDetalleFactura.repaint();
    }
    
    // Método para establecer la vista de facturación
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

        lblTitulo = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblFechaHora = new javax.swing.JLabel();
        lblDetalleVenta = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        ScrollDetalleFactura = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Factura Generada");

        lblCliente.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(0, 102, 102));
        lblCliente.setText("Cliente: ");

        lblFechaHora.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblFechaHora.setForeground(new java.awt.Color(0, 102, 102));
        lblFechaHora.setText("Fecha y hora: ");

        lblDetalleVenta.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblDetalleVenta.setForeground(new java.awt.Color(0, 102, 102));
        lblDetalleVenta.setText("Detalle Venta");

        lblTotal.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 102, 102));
        lblTotal.setText("Total:");

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

        txtTotal.setEditable(false);

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
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ScrollDetalleFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDetalleVenta))
                        .addGap(0, 280, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(btnAceptar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFechaHora)
                .addGap(18, 18, 18)
                .addComponent(lblDetalleVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollDetalleFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btnAceptar)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
    // Cerrar esta ventana y regresar al menú del cajero
    if (vistaFacturacion != null) {
    vistaFacturacion.setVisible(true); // Mostrar la ventana de facturación
    }
    this.dispose(); // Cerrar esta ventana
        
    }//GEN-LAST:event_btnAceptarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollDetalleFactura;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDetalleVenta;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
