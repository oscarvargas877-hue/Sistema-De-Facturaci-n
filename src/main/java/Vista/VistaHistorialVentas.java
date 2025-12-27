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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class VistaHistorialVentas extends javax.swing.JFrame {
// Atributo para guardar la referencia al controlador
private Controlador.ControladorHistorialVentas controladorHistorial;

    /**
     * Creates new form VistaHistorialVentas
     */
    public VistaHistorialVentas() {
        initComponents();
        // PANTALLA COMPLETA
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Fondo profesional
        getContentPane().setBackground(new Color(70, 130, 180));

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridBagLayout());
        panelCentral.setOpaque(false);

        // gbc para título
        GridBagConstraints normalGbc = new GridBagConstraints();
        normalGbc.gridwidth = GridBagConstraints.REMAINDER;
        normalGbc.anchor = GridBagConstraints.CENTER;
        normalGbc.insets = new Insets(30, 0, 30, 0);
        normalGbc.fill = GridBagConstraints.HORIZONTAL;
        normalGbc.weightx = 1.0;
        normalGbc.weighty = 0;

        // gbc para la tabla (ENORME Y ALTA)
        GridBagConstraints tableGbc = new GridBagConstraints();
        tableGbc.gridwidth = GridBagConstraints.REMAINDER;
        tableGbc.anchor = GridBagConstraints.CENTER;
        tableGbc.insets = new Insets(10, 50, 30, 50);
        tableGbc.fill = GridBagConstraints.BOTH;
        tableGbc.weightx = 1.0;
        tableGbc.weighty = 1.0; // ← TABLA OCUPA TODO EL ESPACIO VERTICAL DISPONIBLE

        // gbc para botones
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.gridwidth = GridBagConstraints.REMAINDER;
        buttonGbc.anchor = GridBagConstraints.CENTER;
        buttonGbc.insets = new Insets(10, 0, 50, 0);
        buttonGbc.fill = GridBagConstraints.HORIZONTAL;
        buttonGbc.weightx = 1.0;
        buttonGbc.weighty = 0;

        // Título
        panelCentral.add(lblTitulo, normalGbc);

        // Tabla ENORME
        panelCentral.add(ScrollHistorial, tableGbc);

        //  PANEL DE BOTONES con GridBagLayout para control total
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setOpaque(false);

        GridBagConstraints gbcBoton = new GridBagConstraints();
        gbcBoton.insets = new Insets(10, 20, 10, 20); // margen alrededor de cada botón
        gbcBoton.fill = GridBagConstraints.HORIZONTAL;
        gbcBoton.weightx = 1.0; // ← permite que el botón se expanda horizontalmente
        gbcBoton.anchor = GridBagConstraints.CENTER;

        // Botón Recargar
        btnRecargar.setFont(new Font("Arial Black", Font.BOLD, 28));
        btnRecargar.setPreferredSize(new Dimension(400, 80));
        btnRecargar.setBackground(new Color(52, 152, 219)); // Azul
        btnRecargar.setForeground(Color.WHITE);
        panelBotones.add(btnRecargar, gbcBoton);

        // Botón Atrás
        btnAtras.setFont(new Font("Arial Black", Font.BOLD, 28));
        btnAtras.setPreferredSize(new Dimension(400, 80));
        btnAtras.setBackground(new Color(155, 89, 182)); // Morado
        btnAtras.setForeground(Color.WHITE);
        panelBotones.add(btnAtras, gbcBoton);

        // Agregar panel de botones al diseño principal
        panelCentral.add(panelBotones, buttonGbc);
        
        // Aplicar panel central
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelCentral, BorderLayout.CENTER);

        // ESTILOS BRUTALES
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 48));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);

        tablaHistorial.setFont(new Font("Arial", Font.PLAIN, 22));
        tablaHistorial.setRowHeight(50); // Filas altas
        tablaHistorial.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 26));
        tablaHistorial.getTableHeader().setForeground(Color.WHITE);
        tablaHistorial.getTableHeader().setBackground(new Color(0, 102, 102));

        // Botones grandes y coloridos
        Font fontBotones = new Font("Arial Black", Font.BOLD, 28);
        Dimension tamañoBoton = new Dimension(400, 80);

        btnRecargar.setFont(fontBotones);
        btnRecargar.setPreferredSize(tamañoBoton);
        btnRecargar.setBackground(new Color(52, 152, 219)); // Azul
        btnRecargar.setForeground(Color.WHITE);

        btnAtras.setFont(fontBotones);
        btnAtras.setPreferredSize(tamañoBoton);
        btnAtras.setBackground(new Color(155, 89, 182)); // Morado
        btnAtras.setForeground(Color.WHITE);

        revalidate();
        repaint();
    }
    // Método para inyectar el controlador desde fuera
    public void establecerControlador(Controlador.ControladorHistorialVentas controlador) {
        this.controladorHistorial = controlador;
    }
    // Método para cargar el historial de ventas en la tabla
    public void cargarHistorial(java.util.List<Modelo.HistorialVentaModelo> listaHistorial) {
        // Crear el modelo de la tabla
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
            new Object[]{"ID", "Fecha/Hora", "Cajero", "Cliente", "Total"}, 0
        );

        // Llenar la tabla con los datos
        for (Modelo.HistorialVentaModelo venta : listaHistorial) {
            modelo.addRow(new Object[]{
                venta.getIdFactura(),
                venta.getFechaHora(),
                venta.getCajero(),
                venta.getCliente(),
                String.format("%.2f", venta.getTotal())
            });
        }

        // Asignar el modelo a la tabla
        tablaHistorial.setModel(modelo);
        //PARA QUE LAS FILAS NO SEAN EDITABLES
        tablaHistorial.setDefaultEditor(Object.class, null);
               
        // DOBLE CLIC EN LA TABLA PARA VER DETALLE DE FACTURA
     
        tablaHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {  // Detecta doble clic
                    int fila = tablaHistorial.getSelectedRow();
                    if (fila != -1) {  // Si hay una fila seleccionada
                        int idFactura = (Integer) tablaHistorial.getValueAt(fila, 0);  // Columna 0 = ID de factura
                        if (controladorHistorial != null) {
                            controladorHistorial.mostrarDetalleFactura(idFactura);
                        }
                    }
                }
            }
        });
             
    }
    
     
    
    // Método para mostrar mensajes de error
    private void mostrarMensajeError(String mensaje) {
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
        ScrollHistorial = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();
        btnRecargar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Historial De Ventas");

        tablaHistorial.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tablaHistorial.setForeground(new java.awt.Color(0, 102, 102));
        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Feha/Hora", "Cajero", "Cliente", "Total"
            }
        ));
        jScrollPane1.setViewportView(tablaHistorial);

        ScrollHistorial.setViewportView(jScrollPane1);

        btnRecargar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnRecargar.setForeground(new java.awt.Color(0, 102, 102));
        btnRecargar.setText("Recargar");
        btnRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ScrollHistorial)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 258, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnRecargar)
                                .addGap(246, 246, 246)
                                .addComponent(btnAtras)
                                .addGap(170, 170, 170))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(220, 220, 220))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblTitulo)
                .addGap(31, 31, 31)
                .addComponent(ScrollHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnRecargar))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarActionPerformed
        // TODO add your handling code here:
          // Llamar al controlador para recargar los datos
        if (controladorHistorial != null) {
        controladorHistorial.cargarHistorial();
    
    }
    }//GEN-LAST:event_btnRecargarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        // Llamar al controlador para volver al menú del administrador
        if (controladorHistorial != null) {
        controladorHistorial.volverAlMenu();
    }//GEN-LAST:event_btnAtrasActionPerformed

    

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollHistorial;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tablaHistorial;
    // End of variables declaration//GEN-END:variables
}
