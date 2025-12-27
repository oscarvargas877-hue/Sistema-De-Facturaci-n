/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;
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
import javax.swing.JTextField;


/**
 *
 * @author Usuario
 */
public class VistaGestionStock extends javax.swing.JFrame {
     // Atributo para guardar la referencia al controlador
    private Controlador.ControladorGestionStock controladorGestionStock;
    /**
     * Creates new form VistaGestionStock
     */
    public VistaGestionStock() {
        initComponents();
   // PANTALLA COMPLETA
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    // Fondo profesional
    getContentPane().setBackground(new Color(70, 130, 180));

    // Panel central con GridBagLayout
    JPanel panelCentral = new JPanel(new GridBagLayout());
    panelCentral.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(20, 0, 10, 0);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    gbc.weighty = 0;

    // TÍTULO 
    lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 48));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setHorizontalAlignment(JLabel.CENTER);
    panelCentral.add(lblTitulo, gbc);

    // Lista de Productos"
    lblListaProductos.setFont(new Font("Arial Black", Font.BOLD, 28));
    lblListaProductos.setForeground(Color.WHITE);
    lblListaProductos.setHorizontalAlignment(JLabel.CENTER);
    panelCentral.add(lblListaProductos, gbc);

    //  TABLA 
    TablaProductos.setFont(new Font("Arial", Font.PLAIN, 20));
    TablaProductos.setRowHeight(50);
    TablaProductos.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 24));
    TablaProductos.getTableHeader().setForeground(Color.WHITE);
    TablaProductos.getTableHeader().setBackground(new Color(0, 102, 102));
    jScrollPane1.setPreferredSize(new Dimension(1000, 250)); // Altura controlada
    gbc.insets = new Insets(10, 50, 20, 50);
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 0.6; // La tabla ocupa parte del espacio vertical
    panelCentral.add(jScrollPane1, gbc);

    // PANEL FORMULARIO Etiquetas a la izquierda, campos con espacio 
    JPanel panelFormulario = new JPanel(new GridBagLayout());
    panelFormulario.setOpaque(false);

    GridBagConstraints gbcLabel = new GridBagConstraints();
    gbcLabel.anchor = GridBagConstraints.WEST;
    gbcLabel.insets = new Insets(10, 10, 10, 20); // Más espacio a la derecha de la etiqueta
    gbcLabel.weightx = 0.0; // ← NO ocupa espacio extra
    gbcLabel.fill = GridBagConstraints.NONE;

    GridBagConstraints gbcField = new GridBagConstraints();
    gbcField.gridwidth = GridBagConstraints.REMAINDER;
    gbcField.anchor = GridBagConstraints.WEST; //  campo a la izquierda del espacio disponible
    gbcField.insets = new Insets(10, 0, 10, 10);
    gbcField.weightx = 1.0; //  el campo se expande horizontalmente
    gbcField.fill = GridBagConstraints.HORIZONTAL;

    // Etiqueta y campo Código
    lblCodigo.setFont(new Font("Arial Black", Font.BOLD, 28));
    lblCodigo.setForeground(Color.WHITE);
    txtCodigo.setFont(new Font("Arial", Font.PLAIN, 28));
    txtCodigo.setPreferredSize(new Dimension(100, 50)); // ancho fijo
    txtCodigo.setHorizontalAlignment(JTextField.CENTER);
    txtCodigo.setBackground(Color.WHITE);
    txtCodigo.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 80), 2)); // borde suave

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

    // Agregar panel formulario al panel central
    gbc.insets = new Insets(10, 100, 10, 100); // margen lateral generoso
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weighty = 0;
    panelCentral.add(panelFormulario, gbc);

    // BOTÓN Reabastecer tamaño normal
    btnReabastecer.setFont(new Font("Arial Black", Font.BOLD, 28));
    btnReabastecer.setPreferredSize(new Dimension(400, 60)); // Ancho normal
    btnReabastecer.setBackground(new Color(46, 204, 113)); // Verde
    btnReabastecer.setForeground(Color.WHITE);
    gbc.insets = new Insets(10, 50, 10, 50);
    gbc.fill = GridBagConstraints.NONE; //  NO SE EXPANDE
    panelCentral.add(btnReabastecer, gbc);

    // BOTÓN Atrás tamaño normal
    lblAtras.setFont(new Font("Arial Black", Font.BOLD, 28));
    lblAtras.setPreferredSize(new Dimension(400, 60)); // Ancho normal
    lblAtras.setBackground(new Color(155, 89, 182)); // Morado
    lblAtras.setForeground(Color.WHITE);
    panelCentral.add(lblAtras, gbc);

    //  MENSAJE DE ERROR/ALERTA  DEBAJO DEL BOTÓN ATRÁS
    lblMensajeDeError.setFont(new Font("Arial Black", Font.BOLD, 26));
    lblMensajeDeError.setForeground(Color.RED);
    lblMensajeDeError.setHorizontalAlignment(JLabel.CENTER);
    gbc.insets = new Insets(10, 50, 20, 50);
    panelCentral.add(lblMensajeDeError, gbc);

    // Aplicar panel central
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panelCentral, BorderLayout.CENTER);

    revalidate();
    repaint();
   
    }
        public void establecerControlador(Controlador.ControladorGestionStock controlador) {
        this.controladorGestionStock = controlador;
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
        lblAtras = new javax.swing.JButton();

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

        lblAtras.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblAtras.setForeground(new java.awt.Color(0, 102, 102));
        lblAtras.setText("Atras");
        lblAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblAtrasActionPerformed(evt);
            }
        });

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnReabastecer)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(lblCantidadSumar)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCantidaSumar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblAtras)
                                    .addComponent(lblMensajeDeError, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(120, 327, Short.MAX_VALUE))
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
                .addComponent(lblAtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMensajeDeError, javax.swing.GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
         lblMensajeDeError.setVisible(false);
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCantidaSumarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidaSumarActionPerformed
        // TODO add your handling code here:
         lblMensajeDeError.setVisible(false);
    }//GEN-LAST:event_txtCantidaSumarActionPerformed

    private void btnReabastecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReabastecerActionPerformed
        // TODO add your handling code here:
        // Obtener los valores de los campos
        String codigoProducto = txtCodigo.getText();
        String cantidadTexto = txtCantidaSumar.getText();

        // Validar que los campos no estén vacíos
        if (codigoProducto.trim().isEmpty() || cantidadTexto.trim().isEmpty()) {
            lblMensajeDeError.setText("Por favor ingrese código y cantidad.");
            lblMensajeDeError.setVisible(true);
            return;
        }

        // Validar que la cantidad sea un número entero positivo
        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad <= 0) {
                lblMensajeDeError.setText("La cantidad debe ser mayor que 0.");
                lblMensajeDeError.setVisible(true);
                return;
            }
        } catch (NumberFormatException e) {
            lblMensajeDeError.setText("La cantidad debe ser un número válido.");
            lblMensajeDeError.setVisible(true);
            return;
        }

        // Llamar al controlador para reabastecer el stock
        if (controladorGestionStock != null) {
            controladorGestionStock.reabastecerStock(codigoProducto, cantidad);
        }
    }//GEN-LAST:event_btnReabastecerActionPerformed

    private void lblAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblAtrasActionPerformed
        // TODO add your handling code here:
        // Llamar al controlador para volver al menú del administrador
        if (controladorGestionStock != null) {
            controladorGestionStock.volverAlMenu();
        }
    }//GEN-LAST:event_lblAtrasActionPerformed
     
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
      
    
    // Método público para cargar los productos en la tabla y mostrar alerta si hay stock bajo
    public void cargarProductos(java.util.List<Modelo.ProductoModelo> listaProductos) {
       // Crear el modelo de la tabla
       javax.swing.table.DefaultTableModel modeloTabla = new javax.swing.table.DefaultTableModel(
           new Object[]{"Código", "Nombre", "Precio", "Stock"}, 0
       );

       // Bandera para saber si hay stock bajo
       boolean hayStockBajo = false;

       // Llenar la tabla con los productos
       for (Modelo.ProductoModelo producto : listaProductos) {
           modeloTabla.addRow(new Object[]{
               producto.getCodigo(),
               producto.getNombre(),
               producto.getPrecio(),
               producto.getCantidadStock()
           });
           // Verificar si el stock es menor a 3
           if (producto.getCantidadStock() < 3) {
               hayStockBajo = true;
           }
       }
       
       //PARA QUE LAS FILAS NO SEAN EDITABLES
       modeloTabla.isCellEditable(0, 0); 
       TablaProductos.setDefaultEditor(Object.class, null); 
     

       // Asignar el modelo a la tabla
       TablaProductos.setModel(modeloTabla);

       //AQUÍ VA EL COLOR ROJO 
       TablaProductos.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
           @Override
           public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {

               java.awt.Component celda = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

               // Obtener el stock de la fila actual (columna 3 = Stock)
               int stock = (int) table.getValueAt(row, 3);

               // Si el stock es menor a 3  ROJO Y NEGRITA
               if (stock < 3) {
                   celda.setForeground(java.awt.Color.RED);
                   celda.setFont(celda.getFont().deriveFont(java.awt.Font.BOLD));
               } else {
                   // Color normal
                   celda.setForeground(isSelected ? java.awt.Color.WHITE : java.awt.Color.BLACK);
                   celda.setFont(celda.getFont().deriveFont(java.awt.Font.PLAIN));
               }

               // Fondo cuando seleccionas la fila
               if (isSelected) {
                   celda.setBackground(new java.awt.Color(0, 120, 215));
               } else {
                   celda.setBackground(java.awt.Color.WHITE);
               }

               return celda;
           }
       });
       // Mostrar o ocultar el mensaje de alerta 
       if (hayStockBajo) {
           lblMensajeDeError.setText("¡Alerta! Stock bajo (<3) en algunos productos.");
           lblMensajeDeError.setForeground(java.awt.Color.RED);
           lblMensajeDeError.setVisible(true);
       } else {
           lblMensajeDeError.setVisible(false);
       }
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
    private javax.swing.JButton btnReabastecer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lblAtras;
    private javax.swing.JLabel lblCantidadSumar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblListaProductos;
    private javax.swing.JLabel lblMensajeDeError;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCantidaSumar;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
