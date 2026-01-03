/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;
import Modelo.PaginadorTabla;
import Modelo.ProductoModelo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 *
 * @author Usuario
 */
public class VistaGestionStock extends javax.swing.JFrame {
     // Atributo para guardar la referencia al controlador
    private Controlador.ControladorGestionStock controladorGestionStock;
    private PaginadorTabla<ProductoModelo> paginadorStock;
    /**
     * Creates new form VistaGestionStock
     */
    public VistaGestionStock() {
        initComponents();
  // ==================== REPOSICIONAR BOTONES DE PAGINACIÓN ====================
    // PANTALLA COMPLETA
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    // Fondo profesional
    getContentPane().setBackground(new Color(70, 130, 180));

    // Limpiar el layout actual
    getContentPane().removeAll();

    // Panel central con GridBagLayout
    JPanel panelCentral = new JPanel(new GridBagLayout());
    panelCentral.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(15, 0, 5, 0);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    gbc.weighty = 0;

    // TÍTULO 
    lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 48));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setHorizontalAlignment(JLabel.CENTER);
    panelCentral.add(lblTitulo, gbc);

    // Lista de Productos
    lblListaProductos.setFont(new Font("Arial Black", Font.BOLD, 28));
    lblListaProductos.setForeground(Color.WHITE);
    lblListaProductos.setHorizontalAlignment(JLabel.CENTER);
    panelCentral.add(lblListaProductos, gbc);

    // TABLA - AUMENTADA DE TAMAÑO
    TablaProductos.setFont(new Font("Arial", Font.PLAIN, 20));
    TablaProductos.setRowHeight(50);
    TablaProductos.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 24));
    TablaProductos.getTableHeader().setForeground(Color.WHITE);
    TablaProductos.getTableHeader().setBackground(new Color(0, 102, 102));
    jScrollPane1.setPreferredSize(new Dimension(1200, 350)); // Aumentado de 250 a 350
    gbc.insets = new Insets(10, 30, 10, 30);
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 0.5;
    panelCentral.add(jScrollPane1, gbc);

    // PANEL FORMULARIO
    JPanel panelFormulario = new JPanel(new GridBagLayout());
    panelFormulario.setOpaque(false);

    GridBagConstraints gbcLabel = new GridBagConstraints();
    gbcLabel.anchor = GridBagConstraints.WEST;
    gbcLabel.insets = new Insets(10, 10, 10, 20);
    gbcLabel.weightx = 0.0;
    gbcLabel.fill = GridBagConstraints.NONE;

    GridBagConstraints gbcField = new GridBagConstraints();
    gbcField.gridwidth = GridBagConstraints.REMAINDER;
    gbcField.anchor = GridBagConstraints.WEST;
    gbcField.insets = new Insets(10, 0, 10, 10);
    gbcField.weightx = 1.0;
    gbcField.fill = GridBagConstraints.HORIZONTAL;

    // Etiqueta y campo Código
    lblCodigo.setFont(new Font("Arial Black", Font.BOLD, 28));
    lblCodigo.setForeground(Color.WHITE);
    txtCodigo.setFont(new Font("Arial", Font.PLAIN, 28));
    txtCodigo.setPreferredSize(new Dimension(100, 50));
    txtCodigo.setHorizontalAlignment(JTextField.CENTER);
    txtCodigo.setBackground(Color.WHITE);
    txtCodigo.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 80), 2));

    panelFormulario.add(lblCodigo, gbcLabel);
    panelFormulario.add(txtCodigo, gbcField);

    // Etiqueta y campo Cantidad
    lblCantidadSumar.setFont(new Font("Arial Black", Font.BOLD, 28));
    lblCantidadSumar.setForeground(Color.WHITE);
    txtCantidaSumar.setFont(new Font("Arial", Font.PLAIN, 28));
    txtCantidaSumar.setPreferredSize(new Dimension(100, 50));
    txtCantidaSumar.setHorizontalAlignment(JTextField.CENTER);
    txtCantidaSumar.setBackground(Color.WHITE);
    txtCantidaSumar.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 80), 2));

    panelFormulario.add(lblCantidadSumar, gbcLabel);
    panelFormulario.add(txtCantidaSumar, gbcField);

    gbc.insets = new Insets(5, 100, 5, 100);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weighty = 0;
    panelCentral.add(panelFormulario, gbc);

    // BOTÓN Reabastecer
    btnReabastecer.setFont(new Font("Arial Black", Font.BOLD, 28));
    btnReabastecer.setPreferredSize(new Dimension(400, 60));
    btnReabastecer.setBackground(new Color(46, 204, 113));
    btnReabastecer.setForeground(Color.WHITE);
    gbc.insets = new Insets(5, 50, 5, 50);
    gbc.fill = GridBagConstraints.NONE;
    panelCentral.add(btnReabastecer, gbc);

    // BOTÓN Atrás
    btnAtras.setFont(new Font("Arial Black", Font.BOLD, 28));
    btnAtras.setPreferredSize(new Dimension(400, 60));
    btnAtras.setBackground(new Color(155, 89, 182));
    btnAtras.setForeground(Color.WHITE);
    panelCentral.add(btnAtras, gbc);

    // MENSAJE DE ERROR
    lblMensajeDeError.setFont(new Font("Arial Black", Font.BOLD, 26));
    lblMensajeDeError.setForeground(Color.WHITE);
    lblMensajeDeError.setHorizontalAlignment(JLabel.CENTER);
    gbc.insets = new Insets(5, 50, 5, 50);
    panelCentral.add(lblMensajeDeError, gbc);

    // PANEL PAGINACIÓN - CON MÁS ESPACIO
    JPanel panelPaginacion = new JPanel();
    panelPaginacion.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 15));
    panelPaginacion.setOpaque(false);
    
    btnAnterior.setFont(new Font("Arial Black", Font.BOLD, 20));
    btnAnterior.setForeground(new Color(0, 102, 102));
    btnAnterior.setBackground(Color.WHITE);
    btnAnterior.setPreferredSize(new Dimension(140, 60));
    btnAnterior.setFocusPainted(false);
    
    btnSiguiente.setFont(new Font("Arial Black", Font.BOLD, 20));
    btnSiguiente.setForeground(new Color(0, 102, 102));
    btnSiguiente.setBackground(Color.WHITE);
    btnSiguiente.setPreferredSize(new Dimension(180, 60));
    btnSiguiente.setFocusPainted(false);
    
    lblPagina.setFont(new Font("Arial Black", Font.BOLD, 24));
    lblPagina.setForeground(Color.WHITE);
    lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
    lblPagina.setPreferredSize(new Dimension(200, 60)); // Aumentado de 150 a 200
    
    panelPaginacion.add(btnAnterior);
    panelPaginacion.add(lblPagina);
    panelPaginacion.add(btnSiguiente);
    
    gbc.insets = new Insets(10, 50, 20, 50);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weighty = 0;
    panelCentral.add(panelPaginacion, gbc);

    // Aplicar layout
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panelCentral, BorderLayout.CENTER);

    // Crear e inicializar el paginador
    paginadorStock = new PaginadorTabla<>(TablaProductos, lblPagina, btnAnterior, btnSiguiente);


    revalidate();
    repaint();
    
    // === NUEVO: Al seleccionar una fila en la tabla, poner el código automáticamente en txtCodigo ===
    TablaProductos.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {  // Evita que se dispare dos veces
            int filaSeleccionada = TablaProductos.getSelectedRow();
            if (filaSeleccionada != -1) {  // Hay una fila seleccionada
                Object valorCodigo = TablaProductos.getValueAt(filaSeleccionada, 0);  // Columna 0 = Código
                if (valorCodigo != null) {
                    txtCodigo.setText(valorCodigo.toString().trim());
                    // Opcional: enfocar el campo cantidad para escribir rápido
                    txtCantidaSumar.requestFocusInWindow();
                    txtCantidaSumar.selectAll();  // Selecciona el texto para sobrescribir fácil
                }
            }
        }
    });
    
 
    // === NAVEGACIÓN CON ENTER ===
    txtCodigo.addActionListener(e -> {
        txtCantidaSumar.requestFocusInWindow();
        txtCantidaSumar.selectAll();
    });

    // IMPORTANTE: Reemplaza COMPLETAMENTE este listener
    // Enter en cantidad → intentar reabastecer
    txtCantidaSumar.addActionListener(e -> intentarReabastecer());

    // Paginación: PaginadorTabla ya agrega listeners a los botones → no necesitas nada más
}

    // ================== MÉTODO PRIVADO UNIFICADO (vista solo recoge datos) ==================
    private void intentarReabastecer() {
    String codigo = txtCodigo.getText().trim();
    String cantidadTexto = txtCantidaSumar.getText().trim();

    // Validación mínima en vista (solo campos vacíos)
    if (codigo.isEmpty() || cantidadTexto.isEmpty()) {
        mostrarMensajeConColor("Por favor ingrese código y cantidad.", Color.WHITE);
        return;
    }

    int cantidad;
    try {
        cantidad = Integer.parseInt(cantidadTexto);
    } catch (NumberFormatException ex) {
        mostrarMensajeConColor("La cantidad debe ser un número válido.", Color.WHITE);
        return;
    }

    // Delegar TODO lo demás al controlador
    if (controladorGestionStock != null) {
        controladorGestionStock.reabastecerStock(codigo, cantidad);
    }

    // Limpiar cantidad para el siguiente producto
    txtCantidaSumar.setText("");
    txtCantidaSumar.requestFocusInWindow();
 }
    
    
    //METODO ESTABLECER CONTROLADOR 
    public void establecerControlador(Controlador.ControladorGestionStock controlador) {
    this.controladorGestionStock = controlador;

    // === LISTENERS UNIFICADOS ===
    // Reabastecer (clic en botón)
    btnReabastecer.addActionListener(e -> intentarReabastecer());

    // Atrás
    btnAtras.addActionListener(e -> controladorGestionStock.volverAlMenu());
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
        lblListaProductos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        lblCodigo = new javax.swing.JLabel();
        lblCantidadSumar = new javax.swing.JLabel();
        lblMensajeDeError = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtCantidaSumar = new javax.swing.JTextField();
        btnReabastecer = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Gestión De Stock");

        lblListaProductos.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblListaProductos.setForeground(new java.awt.Color(0, 102, 102));
        lblListaProductos.setText("Lista de Productos");

        TablaProductos.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        TablaProductos.setForeground(new java.awt.Color(0, 102, 102));
        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Precio", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaProductos);

        lblCodigo.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(0, 102, 102));
        lblCodigo.setText("Código");

        lblCantidadSumar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblCantidadSumar.setForeground(new java.awt.Color(0, 102, 102));
        lblCantidadSumar.setText("Ingrese la cantidad");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        txtCantidaSumar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidaSumarActionPerformed(evt);
            }
        });

        btnReabastecer.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnReabastecer.setForeground(new java.awt.Color(0, 102, 102));
        btnReabastecer.setText("Reabastecer Stock");
        btnReabastecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReabastecerActionPerformed(evt);
            }
        });

        btnAtras.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(0, 102, 102));
        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        lblPagina.setText("Pagina");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodigo)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(46, 46, 46)
                                            .addComponent(lblCantidadSumar)
                                            .addGap(18, 18, Short.MAX_VALUE)
                                            .addComponent(txtCantidaSumar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblMensajeDeError, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnReabastecer))
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAtras)
                                        .addGap(49, 49, 49)
                                        .addComponent(btnAnterior)
                                        .addGap(86, 86, 86)
                                        .addComponent(lblPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(70, 70, 70)
                                        .addComponent(btnSiguiente)))))
                        .addGap(326, 326, 326))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblListaProductos)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(lblTitulo)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTitulo)
                .addGap(52, 52, 52)
                .addComponent(lblListaProductos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidaSumar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidadSumar))
                .addGap(18, 18, 18)
                .addComponent(btnReabastecer)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(lblPagina)
                    .addComponent(btnAnterior)
                    .addComponent(btnSiguiente))
                .addGap(19, 19, 19)
                .addComponent(lblMensajeDeError, javax.swing.GroupLayout.DEFAULT_SIZE, 4, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCantidaSumarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidaSumarActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCantidaSumarActionPerformed

    private void btnReabastecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReabastecerActionPerformed
        // TODO add your handling code here:
   
    
    }//GEN-LAST:event_btnReabastecerActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        // Llamar al controlador para volver al menú del administrador
      
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnteriorActionPerformed
     
        // Método público para mostrar mensajes de error o éxito
      public void mostrarMensajeError(String mensaje) {
          lblMensajeDeError.setText(mensaje);
          lblMensajeDeError.setVisible(true);
        }
          // Método público para mostrar mensajes con color específico
        public void mostrarMensajeConColor(String mensaje, java.awt.Color color) {
            lblMensajeDeError.setText(mensaje);
            lblMensajeDeError.setForeground(color);
            lblMensajeDeError.setVisible(true);
        }
      
    
  // Método público para cargar los productos con paginación + alerta stock bajo + color rojo
    public void cargarProductos(java.util.List<Modelo.ProductoModelo> listaProductos) {

        // === USAR EL PAGINADOR PARA MANEJAR TODO: PAGINACIÓN + LLENADO DE TABLA ===
        paginadorStock.cargarDatos(listaProductos);

        // === DETECTAR SI HAY STOCK BAJO EN TODA LA LISTA (no solo en la página actual) ===
        boolean hayStockBajo = false;
        for (Modelo.ProductoModelo producto : listaProductos) {
            if (producto.getCantidadStock() < 3) {
                hayStockBajo = true;
                break; // Optimización: salir temprano si ya encontramos uno
            }
        }

        // === MOSTRAR ALERTA GENERAL DE STOCK BAJO ===
        if (hayStockBajo) {
            mostrarMensajeConColor("¡Alerta! Stock bajo (<3) en algunos productos.", Color.WHITE);
        } else {
            lblMensajeDeError.setVisible(false); // Ocultar si no hay alerta
        }

        // === RENDERER PERSONALIZADO: RESALTAR STOCK BAJO EN ROJO Y NEGRITA ===
        // Este renderer se aplica a la tabla cada vez que se carga una nueva página
        TablaProductos.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                java.awt.Component celda = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Obtener el valor de la columna Stock (índice 3)
                Object valorStock = table.getValueAt(row, 3);
                int stock = 0;
                if (valorStock != null) {
                    try {
                        stock = Integer.parseInt(valorStock.toString());
                    } catch (NumberFormatException e) {
                        stock = 0;
                    }
                }

                // Aplicar estilo según stock
                if (stock < 3) {
                    celda.setForeground(Color.RED);
                    celda.setFont(celda.getFont().deriveFont(Font.BOLD));
                } else {
                    celda.setForeground(isSelected ? Color.WHITE : Color.BLACK);
                    celda.setFont(celda.getFont().deriveFont(Font.PLAIN));
                }

                // Fondo de selección bonito
                celda.setBackground(isSelected ? new Color(0, 120, 215) : Color.WHITE);

                return celda;
            }
        });

        // === HACER LA TABLA NO EDITABLE (buena práctica) ===
        TablaProductos.setDefaultEditor(Object.class, null);
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
            java.util.logging.Logger.getLogger(VistaGestionStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGestionStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGestionStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGestionStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGestionStock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProductos;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnReabastecer;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidadSumar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblListaProductos;
    private javax.swing.JLabel lblMensajeDeError;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCantidaSumar;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
