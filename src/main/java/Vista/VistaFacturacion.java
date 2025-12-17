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


/**
 *
 * @author Usuario
 */
public class VistaFacturacion extends javax.swing.JFrame {
// Atributo para guardar la referencia al controlador
private Controlador.ControladorFacturacion controladorFacturacion;
// Atributo para el diálogo de espera
private javax.swing.JDialog dialogoEspera;
// Atributo para el diálogo de espera

    /**
     * Creates new form VistaFacturacion
     */
    public VistaFacturacion() {
        initComponents();
 
     
     // CONFIGURACIÓN GENERAL 
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    getContentPane().setBackground(new Color(30, 30, 40));
    getContentPane().removeAll();
    getContentPane().setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 40, 10, 40);

    // TÍTULO CENTRADO Y GRANDE 
    lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 50));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setHorizontalAlignment(JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 3;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    getContentPane().add(lblTitulo, gbc);

    //  CLIENTE 
    lblCliente.setFont(new Font("Arial Black", Font.BOLD, 35));
    lblCliente.setForeground(Color.WHITE);
    gbc.gridy = 1;
    gbc.gridx = 0;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.WEST;
    getContentPane().add(lblCliente, gbc);

    txtCliente.setFont(new Font("Arial", Font.PLAIN, 24));
    txtCliente.setPreferredSize(new Dimension(300, 40));
    gbc.gridx = 1;
    getContentPane().add(txtCliente, gbc);

    //  PRODUCTO 
    lblProductos.setFont(new Font("Arial Black", Font.BOLD, 35));
    lblProductos.setForeground(Color.WHITE);
    gbc.gridy = 2;
    gbc.gridx = 0;
    getContentPane().add(lblProductos, gbc);

    ComboProductos.setFont(new Font("Arial", Font.PLAIN, 24));
    ComboProductos.setPreferredSize(new Dimension(300, 40));
    gbc.gridx = 1;
    getContentPane().add(ComboProductos, gbc);

    // CANTIDAD 
    lblCantidad.setFont(new Font("Arial Black", Font.BOLD, 35));
    lblCantidad.setForeground(Color.WHITE);
    gbc.gridy = 3;
    gbc.gridx = 0;
    getContentPane().add(lblCantidad, gbc);

    txtCantidad.setFont(new Font("Arial", Font.PLAIN, 24));
    txtCantidad.setPreferredSize(new Dimension(120, 40));
    gbc.gridx = 1;
    getContentPane().add(txtCantidad, gbc);

    // BOTÓN AGREGAR 
    btnAgregarProducto.setFont(new Font("Arial Black", Font.BOLD, 24));
    btnAgregarProducto.setBackground(new Color(0, 102, 102));
    btnAgregarProducto.setForeground(Color.WHITE);
    btnAgregarProducto.setFocusPainted(false);
    btnAgregarProducto.setPreferredSize(new Dimension(220, 50));
    gbc.gridx = 2;
    gbc.anchor = GridBagConstraints.WEST;
    getContentPane().add(btnAgregarProducto, gbc);

    //  DETALLE VENTA 
    lblDetalleVenta.setFont(new Font("Arial Black", Font.BOLD, 36));
    lblDetalleVenta.setForeground(Color.WHITE);
    gbc.gridy = 4;
    gbc.gridx = 0;
    gbc.gridwidth = 3;
    gbc.anchor = GridBagConstraints.CENTER;
    getContentPane().add(lblDetalleVenta, gbc);

    // === TABLA ===
    TablaDetalleVenta.setFont(new Font("Arial", Font.PLAIN, 22));
    TablaDetalleVenta.setRowHeight(40);
    TablaDetalleVenta.setBackground(Color.WHITE);
    TablaDetalleVenta.setForeground(Color.BLACK);
    TablaDetalleVenta.setGridColor(Color.LIGHT_GRAY);
    TablaDetalleVenta.setShowGrid(true);
    TablaDetalleVenta.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 24));
    TablaDetalleVenta.getTableHeader().setBackground(new Color(0, 102, 102));
    TablaDetalleVenta.getTableHeader().setForeground(Color.WHITE);
    TablaDetalleVenta.getTableHeader().setReorderingAllowed(false);

    ScrollDetalleVenta.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 102), 4));
    ScrollDetalleVenta.setViewportView(TablaDetalleVenta);
    ScrollDetalleVenta.setMinimumSize(new Dimension(1000, 300));

    gbc.gridy = 5;
    gbc.gridx = 0;
    gbc.gridwidth = 3;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    getContentPane().add(ScrollDetalleVenta, gbc);

   //  TOTAL 
    lblTotal.setFont(new Font("Arial Black", Font.BOLD, 36));
    lblTotal.setForeground(Color.WHITE);
    txtTotal.setFont(new Font("Arial Black", Font.BOLD, 36));
    txtTotal.setForeground(Color.WHITE);
    txtTotal.setBackground(new Color(30, 30, 40));
    txtTotal.setEditable(false);
    txtTotal.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
    txtTotal.setPreferredSize(new Dimension(200, 60)); // ← Más ancho

    JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
    panelTotal.setOpaque(false);
    panelTotal.add(lblTotal);
    panelTotal.add(txtTotal);

    gbc.gridy = 6;
    gbc.gridx = 0;
    gbc.gridwidth = 3;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weighty = 0.1;
    getContentPane().add(panelTotal, gbc);

   //  BOTONES A LA DERECHA 
    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10)); // ← DERECHA
    panelBotones.setOpaque(false);

    btnFinalizarVenta.setFont(new Font("Arial Black", Font.BOLD, 22)); //  Más pequeño
    btnFinalizarVenta.setBackground(new Color(46, 204, 113));
    btnFinalizarVenta.setForeground(Color.WHITE);
    btnFinalizarVenta.setFocusPainted(false);
    btnFinalizarVenta.setPreferredSize(new Dimension(300, 50)); //  Más angosto
    panelBotones.add(btnFinalizarVenta);

    btnBorrar.setFont(new Font("Arial Black", Font.BOLD, 22));
    btnBorrar.setBackground(new Color(231, 76, 60));
    btnBorrar.setForeground(Color.WHITE);
    btnBorrar.setFocusPainted(false);
    btnBorrar.setPreferredSize(new Dimension(200, 50));
    panelBotones.add(btnBorrar);

    btnAtras.setFont(new Font("Arial Black", Font.BOLD, 22));
    btnAtras.setBackground(new Color(52, 73, 94));
    btnAtras.setForeground(Color.WHITE);
    btnAtras.setFocusPainted(false);
    btnAtras.setPreferredSize(new Dimension(200, 50));
    panelBotones.add(btnAtras);
    // ️ Forzar que el panel tenga una altura mínima para que los botones no se corten
    panelBotones.setMinimumSize(new Dimension(800, 80)); // Ancho mínimo 800px, Alto mínimo 80px
    panelBotones.setPreferredSize(new Dimension(800, 80));


    gbc.gridy = 6;
    gbc.gridx = 0;
    gbc.gridwidth = 3;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weighty = 0.1;
    gbc.insets = new Insets(10, 40, 40, 40); // Margen derecho para que no toquen el borde
    getContentPane().add(panelBotones, gbc);


    //  FORZAR ACTUALIZACIÓN 
    revalidate();
    repaint();
   
    }
    
    // Método para inyectar el controlador desde fuera
    public void establecerControlador(Controlador.ControladorFacturacion controlador) {
        this.controladorFacturacion = controlador;
    }

    
    // Método para cargar los productos en el combo
    public void cargarProductos(java.util.List<Modelo.ProductoModelo> productos) {
        ComboProductos.removeAllItems();
        for (Modelo.ProductoModelo producto : productos) {
            ComboProductos.addItem(producto.getNombre());
        }
    }
    
    // Método para actualizar la tabla de detalles y el total
    public void actualizarDetalleYTotal(java.util.List<Modelo.DetalleFacturaModelo> detalle, double total) {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
            new Object[]{"Producto", "Cantidad", "Precio", "Descuento", "Subtotal"}, 0
        );

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

        TablaDetalleVenta.setModel(modelo);
        txtTotal.setText(String.format("%.2f", total));
        //PARA QUE LAS FILAS NO SEAN EDITABLES
        TablaDetalleVenta.setDefaultEditor(Object.class, null);
        txtTotal.setText(String.format("%.2f", total));
    }
      // Método para mostrar mensaje de espera
    public void mostrarMensajeEspera(String mensaje) {
        javax.swing.JOptionPane pane = new javax.swing.JOptionPane(
            mensaje, 
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
        dialogoEspera = pane.createDialog(this, "Espere");
        dialogoEspera.setModal(false);
        dialogoEspera.setVisible(true);
    }
    

    public void ocultarMensajeEspera() {
        if (dialogoEspera != null && dialogoEspera.isVisible()) {
            dialogoEspera.setVisible(false);
            dialogoEspera.dispose();
        }
    }
    
    // Método público para mostrar mensajes de error (usado por el controlador)
    public void mostrarMensajeError(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
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
        btnAgregarProducto = new javax.swing.JButton();
        lblDetalleVenta = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnFinalizarVenta = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        lblProductos = new javax.swing.JLabel();
        ComboProductos = new javax.swing.JComboBox<>();
        txtCliente = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        ScrollDetalleVenta = new javax.swing.JScrollPane();
        TablaDetalleVenta = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Facturación");

        lblCliente.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(0, 102, 102));
        lblCliente.setText("Cliente");

        btnAgregarProducto.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnAgregarProducto.setForeground(new java.awt.Color(0, 102, 102));
        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        lblDetalleVenta.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblDetalleVenta.setForeground(new java.awt.Color(0, 102, 102));
        lblDetalleVenta.setText("Detalle Venta");

        lblTotal.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 102, 102));
        lblTotal.setText("Total");

        txtTotal.setEditable(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        btnFinalizarVenta.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnFinalizarVenta.setForeground(new java.awt.Color(0, 102, 102));
        btnFinalizarVenta.setText("Finalizar Venta");
        btnFinalizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarVentaActionPerformed(evt);
            }
        });

        btnBorrar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(0, 102, 102));
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnAtras.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(0, 102, 102));
        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        lblProductos.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblProductos.setForeground(new java.awt.Color(0, 102, 102));
        lblProductos.setText("Productos");

        ComboProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboProductosActionPerformed(evt);
            }
        });

        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });

        lblCantidad.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(0, 102, 102));
        lblCantidad.setText("Cantidad");

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        TablaDetalleVenta.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        TablaDetalleVenta.setForeground(new java.awt.Color(0, 102, 102));
        TablaDetalleVenta.setModel(new javax.swing.table.DefaultTableModel(
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
        ScrollDetalleVenta.setViewportView(TablaDetalleVenta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProductos)
                                    .addComponent(lblCliente)
                                    .addComponent(lblCantidad))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCliente)
                                    .addComponent(ComboProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCantidad)
                                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDetalleVenta)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(99, 99, 99)
                                    .addComponent(btnFinalizarVenta)
                                    .addGap(36, 36, 36)
                                    .addComponent(btnBorrar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAtras))
                                .addComponent(ScrollDetalleVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(lblTitulo)))
                .addContainerGap(1201, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCliente)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProductos)
                            .addComponent(ComboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidad)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblTitulo)))
                .addGap(25, 25, 25)
                .addComponent(btnAgregarProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDetalleVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollDetalleVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizarVenta)
                    .addComponent(btnBorrar)
                    .addComponent(btnAtras))
                .addContainerGap(349, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed

    private void ComboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboProductosActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        // TODO add your handling code here:
        String nombreProducto = (String) ComboProductos.getSelectedItem();
        String cantidadTexto = txtCantidad.getText();

        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            mostrarMensajeError("Por favor seleccione un producto.");
            return;
        }
        if (cantidadTexto.trim().isEmpty()) {
            mostrarMensajeError("Por favor ingrese una cantidad.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad <= 0) {
                mostrarMensajeError("La cantidad debe ser mayor que 0.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarMensajeError("La cantidad debe ser un número válido.");
            return;
        }

        if (controladorFacturacion != null) {
            controladorFacturacion.agregarProducto(nombreProducto, cantidad);
        }
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void btnFinalizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarVentaActionPerformed
        // TODO add your handling code here:
        String cliente = txtCliente.getText().trim();
        if (cliente.isEmpty()) {
            mostrarMensajeError("Por favor ingrese el nombre del cliente.");
            return;
        }
        if (controladorFacturacion != null) {
            controladorFacturacion.finalizarVenta(cliente);
        }
    }//GEN-LAST:event_btnFinalizarVentaActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
      // Limpiar la venta actual para empezar una nueva
        if (controladorFacturacion != null) {
            controladorFacturacion.limpiarVenta();
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        if (controladorFacturacion != null) {
        controladorFacturacion.volverAlMenu();
    }
    }//GEN-LAST:event_btnAtrasActionPerformed
    
     // Limpia todos los campos para empezar una nueva venta
     
    public void limpiarCamposParaNuevaVenta() {
        txtCliente.setText("");
        txtCantidad.setText("");
        txtTotal.setText("0.00");
        
        // Limpiar la tabla (modelo vacío)
        javax.swing.table.DefaultTableModel modeloVacio = new javax.swing.table.DefaultTableModel(
            new Object[]{"Producto", "Cantidad", "Precio", "Descuento", "Subtotal"}, 0
        );
        TablaDetalleVenta.setModel(modeloVacio);
        
        // Seleccionar el primer producto si hay alguno
        if (ComboProductos.getItemCount() > 0) {
            ComboProductos.setSelectedIndex(0);
        }
        
        // Poner foco en el campo cliente o cantidad para mayor comodidad
        txtCliente.requestFocus();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaFacturacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboProductos;
    private javax.swing.JScrollPane ScrollDetalleVenta;
    private javax.swing.JTable TablaDetalleVenta;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnFinalizarVenta;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDetalleVenta;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
