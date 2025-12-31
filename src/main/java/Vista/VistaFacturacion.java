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
import javax.swing.JScrollPane;


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
 
    // ================== CONFIGURACIÓN GENERAL ==================
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    getContentPane().setBackground(new Color(70, 130, 180));
    
    // ================== CREAR PANEL PRINCIPAL CON LAYOUT ==================
    JPanel panelPrincipal = new JPanel(new GridBagLayout());
    panelPrincipal.setBackground(new Color(70, 130, 180));
    panelPrincipal.setOpaque(true);
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    
    // ================== TÍTULO ==================
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 3;
    lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 60));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setHorizontalAlignment(JLabel.CENTER);
    panelPrincipal.add(lblTitulo, gbc);
    
    // ================== CÉDULA ==================
    gbc.gridwidth = 1;
    gbc.gridy = 1;
    gbc.gridx = 0;
    lblCedula.setFont(new Font("Arial Black", Font.BOLD, 32));
    lblCedula.setForeground(Color.WHITE);
    panelPrincipal.add(lblCedula, gbc);
    
    gbc.gridx = 1;
    txtCedula.setFont(new Font("Arial", Font.PLAIN, 28));
    txtCedula.setPreferredSize(new Dimension(350, 50));
    panelPrincipal.add(txtCedula, gbc);
    
    gbc.gridx = 2;
    btnBuscarCedula.setFont(new Font("Arial Black", Font.BOLD, 24));
    btnBuscarCedula.setBackground(new Color(46, 204, 113));
    btnBuscarCedula.setForeground(Color.WHITE);
    btnBuscarCedula.setPreferredSize(new Dimension(200, 50));
    btnBuscarCedula.setFocusPainted(false);
    panelPrincipal.add(btnBuscarCedula, gbc);
    
    // ================== NOMBRES ==================
    gbc.gridx = 0;
    gbc.gridy = 2;
    lblNombresCompletos.setFont(new Font("Arial Black", Font.BOLD, 32));
    lblNombresCompletos.setForeground(Color.WHITE);
    panelPrincipal.add(lblNombresCompletos, gbc);
    
    gbc.gridx = 1;
    gbc.gridwidth = 2;
    txtNombresApellidos.setFont(new Font("Arial", Font.PLAIN, 28));
    txtNombresApellidos.setEditable(false);
    txtNombresApellidos.setPreferredSize(new Dimension(400, 50));
    panelPrincipal.add(txtNombresApellidos, gbc);
    
    // ================== DIRECCIÓN ==================
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    lblDireccion.setFont(new Font("Arial Black", Font.BOLD, 32));
    lblDireccion.setForeground(Color.WHITE);
    panelPrincipal.add(lblDireccion, gbc);
    
    gbc.gridx = 1;
    gbc.gridwidth = 2;
    txtDireccion.setFont(new Font("Arial", Font.PLAIN, 28));
    txtDireccion.setEditable(false);
    txtDireccion.setPreferredSize(new Dimension(400, 50));
    panelPrincipal.add(txtDireccion, gbc);
    
    // ================== PRODUCTOS ==================
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    lblProductos.setFont(new Font("Arial Black", Font.BOLD, 32));
    lblProductos.setForeground(Color.WHITE);
    panelPrincipal.add(lblProductos, gbc);
    
    gbc.gridx = 1;
    gbc.gridwidth = 2;
    ComboProductos.setFont(new Font("Arial", Font.PLAIN, 28));
    ComboProductos.setPreferredSize(new Dimension(400, 50));
    panelPrincipal.add(ComboProductos, gbc);
    
    // ================== CANTIDAD ==================
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    lblCantidad.setFont(new Font("Arial Black", Font.BOLD, 32));
    lblCantidad.setForeground(Color.WHITE);
    panelPrincipal.add(lblCantidad, gbc);
    
    gbc.gridx = 1;
    gbc.gridwidth = 2;
    txtCantidad.setFont(new Font("Arial", Font.PLAIN, 28));
    txtCantidad.setPreferredSize(new Dimension(400, 50));
    panelPrincipal.add(txtCantidad, gbc);
    
    // ================== BOTÓN AGREGAR ==================
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 3;
    btnAgregarProducto.setFont(new Font("Arial Black", Font.BOLD, 28));
    btnAgregarProducto.setBackground(new Color(0, 102, 102));
    btnAgregarProducto.setForeground(Color.WHITE);
    btnAgregarProducto.setPreferredSize(new Dimension(600, 60));
    btnAgregarProducto.setFocusPainted(false);
    panelPrincipal.add(btnAgregarProducto, gbc);
    
    // ================== DETALLE VENTA ==================
    gbc.gridx = 0;
    gbc.gridy = 7;
    lblDetalleVenta.setFont(new Font("Arial Black", Font.BOLD, 32));
    lblDetalleVenta.setForeground(Color.WHITE);
    panelPrincipal.add(lblDetalleVenta, gbc);
    
    gbc.gridy = 8;
    ScrollDetalleVenta.setPreferredSize(new Dimension(300, 90));
    panelPrincipal.add(ScrollDetalleVenta, gbc);
    
    // ================== TOTAL ==================
    gbc.gridy = 9;
    gbc.gridwidth = 1;
    lblTotal.setFont(new Font("Arial Black", Font.BOLD, 32));
    lblTotal.setForeground(Color.WHITE);
    panelPrincipal.add(lblTotal, gbc);
    
    gbc.gridx = 1;
    gbc.gridwidth = 2;
    txtTotal.setFont(new Font("Arial", Font.PLAIN, 28));
    txtTotal.setEditable(false);
    txtTotal.setPreferredSize(new Dimension(400, 50));
    panelPrincipal.add(txtTotal, gbc);
    
    // ================== BOTONES FINALES ==================
    gbc.gridx = 0;
    gbc.gridy = 10;
    gbc.gridwidth = 3;
    
    JPanel panelBotones = new JPanel();
    panelBotones.setBackground(new Color(70, 130, 180));
    
    Font fontBoton = new Font("Arial Black", Font.BOLD, 28);
    
    btnFinalizarVenta.setFont(fontBoton);
    btnFinalizarVenta.setBackground(new Color(46, 204, 113));
    btnFinalizarVenta.setForeground(Color.WHITE);
    btnFinalizarVenta.setPreferredSize(new Dimension(400, 60));
    btnFinalizarVenta.setFocusPainted(false);
    panelBotones.add(btnFinalizarVenta);
    
    btnBorrar.setFont(fontBoton);
    btnBorrar.setBackground(new Color(231, 76, 60));
    btnBorrar.setForeground(Color.WHITE);
    btnBorrar.setPreferredSize(new Dimension(200, 60));
    btnBorrar.setFocusPainted(false);
    panelBotones.add(btnBorrar);
    
    btnAtras.setFont(fontBoton);
    btnAtras.setBackground(new Color(52, 152, 219));
    btnAtras.setForeground(Color.WHITE);
    btnAtras.setPreferredSize(new Dimension(200, 60));
    btnAtras.setFocusPainted(false);
    panelBotones.add(btnAtras);
    
    panelPrincipal.add(panelBotones, gbc);
    
    // ================== CENTRAR PANEL EN LA PANTALLA ==================
    JPanel panelCentral = new JPanel(new GridBagLayout());
    panelCentral.setBackground(new Color(70, 130, 180));
    GridBagConstraints gbcCentral = new GridBagConstraints();
    gbcCentral.anchor = GridBagConstraints.NORTH;
    gbcCentral.weighty = 0.1;
    panelCentral.add(panelPrincipal, gbcCentral);
    
    JScrollPane scrollPane = new JScrollPane(panelCentral);
    scrollPane.setBackground(new Color(70, 130, 180));
    scrollPane.getViewport().setBackground(new Color(70, 130, 180));
    scrollPane.setBorder(null);
    setContentPane(scrollPane);
    
    // ================== ACCIONES ==================
    btnBuscarCedula.addActionListener(e -> {
        if (controladorFacturacion != null) {
            controladorFacturacion.buscarClientePorCedula();
        }
    });

    txtCedula.addActionListener(e -> {
        if (controladorFacturacion != null) {
            controladorFacturacion.buscarClientePorCedula();
        }
    });
    
    java.awt.EventQueue.invokeLater(() -> txtCedula.requestFocusInWindow());
     
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
        lblNombresCompletos = new javax.swing.JLabel();
        btnAgregarProducto = new javax.swing.JButton();
        lblDetalleVenta = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnFinalizarVenta = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        lblProductos = new javax.swing.JLabel();
        ComboProductos = new javax.swing.JComboBox<>();
        txtNombresApellidos = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        ScrollDetalleVenta = new javax.swing.JScrollPane();
        TablaDetalleVenta = new javax.swing.JTable();
        lblCedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        btnBuscarCedula = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Facturación");

        lblNombresCompletos.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblNombresCompletos.setForeground(new java.awt.Color(0, 102, 102));
        lblNombresCompletos.setText("Nombres Apellidos");

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
        lblDetalleVenta.setText("Detalle Venta:");

        lblTotal.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 102, 102));
        lblTotal.setText("Total:");

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

        txtNombresApellidos.setEditable(false);
        txtNombresApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresApellidosActionPerformed(evt);
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

        lblCedula.setText("Cédula");

        lblDireccion.setText("Dirección");

        txtDireccion.setEditable(false);

        btnBuscarCedula.setText("Buscar");

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblProductos)
                                    .addComponent(lblNombresCompletos)
                                    .addComponent(lblCantidad)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCedula)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBuscarCedula))
                                    .addComponent(lblDireccion))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombresApellidos)
                                    .addComponent(ComboProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCantidad)
                                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(txtCedula)
                                    .addComponent(txtDireccion)))
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
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCedula)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarCedula)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblTitulo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombresApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombresCompletos))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDireccion)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblProductos)
                    .addComponent(ComboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(359, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombresApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresApellidosActionPerformed

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
       if (controladorFacturacion != null) {
        controladorFacturacion.finalizarVenta();
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
        txtCedula.setText("");
        txtNombresApellidos.setText("");
        txtDireccion.setText("");
        txtCantidad.setText("");
        txtTotal.setText("0.00");

        // Habilitar edición (por si estaba bloqueada)
        habilitarEdicionCliente(true);

        // Limpiar la tabla
        javax.swing.table.DefaultTableModel modeloVacio = new javax.swing.table.DefaultTableModel(
            new Object[]{"Producto", "Cantidad", "Precio", "Descuento", "Subtotal"}, 0
        );
        TablaDetalleVenta.setModel(modeloVacio);

        // Seleccionar primer producto
        if (ComboProductos.getItemCount() > 0) {
            ComboProductos.setSelectedIndex(0);
        }

        // Enfocar cédula para nueva venta rápida
        enfocarCampoCedula();
    }
    
        // ==================== MÉTODOS PARA MANEJO DE CLIENTE ====================

        // Enfocar el campo cédula al abrir la ventana
        public void enfocarCampoCedula() {
            txtCedula.requestFocusInWindow();
        }

        // Enfocar el campo nombres/apellidos (cuando el cliente no existe)
        public void enfocarNombresApellidos() {
            txtNombresApellidos.requestFocusInWindow();
        }

        // Obtener cédula ingresada
        public String obtenerCedulaIngresada() {
            return txtCedula.getText().trim();
        }

        // Obtener nombres/apellidos ingresados
        public String obtenerNombresApellidosIngresado() {
            return txtNombresApellidos.getText().trim();
        }

        // Obtener dirección ingresada
        public String obtenerDireccionClienteIngresada() {
            return txtDireccion.getText().trim();
        }

        // Mostrar datos del cliente encontrado
        public void mostrarDatosCliente(String nombresApellidos, String direccion) {
            txtNombresApellidos.setText(nombresApellidos);
            txtDireccion.setText(direccion != null ? direccion : "");
        }

        // Limpiar solo los campos de cliente (nombre y dirección)
        public void limpiarDatosCliente() {
            txtNombresApellidos.setText("");
            txtDireccion.setText("");
            // NO limpiar txtCedula porque el cajero ya la escribió
        }

        // Habilitar o deshabilitar edición de nombre y dirección
        public void habilitarEdicionCliente(boolean habilitar) {
            txtNombresApellidos.setEditable(habilitar);
            txtDireccion.setEditable(habilitar);

            if (habilitar) {
                // Fondo amarillo claro cuando es editable
                txtNombresApellidos.setBackground(new java.awt.Color(255, 255, 200));
                txtDireccion.setBackground(new java.awt.Color(255, 255, 200));
            } else {
                // Fondo verde claro cuando está cargado automáticamente
                txtNombresApellidos.setBackground(new java.awt.Color(200, 255, 200));
                txtDireccion.setBackground(new java.awt.Color(200, 255, 200));
            }
        }

        // Mostrar mensaje con color (verde = éxito, naranja = advertencia)
        public void mostrarMensajeConColor(String mensaje, java.awt.Color color) {
            javax.swing.JOptionPane pane = new javax.swing.JOptionPane(mensaje, javax.swing.JOptionPane.INFORMATION_MESSAGE);
            javax.swing.JDialog dialog = pane.createDialog(this, "Información");
            dialog.getContentPane().setBackground(color);
            // Cambiar color del texto
            for (java.awt.Component comp : dialog.getContentPane().getComponents()) {
                if (comp instanceof javax.swing.JPanel) {
                    for (java.awt.Component inner : ((javax.swing.JPanel) comp).getComponents()) {
                        inner.setForeground(java.awt.Color.WHITE);
                    }
                }
            }
            dialog.setVisible(true);
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
    private javax.swing.JButton btnBuscarCedula;
    private javax.swing.JButton btnFinalizarVenta;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblDetalleVenta;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblNombresCompletos;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombresApellidos;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
