/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.PaginadorTabla;
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
private PaginadorTabla<Modelo.HistorialVentaModelo> paginadorHistorial;
    /**
     * Creates new form VistaHistorialVentas
     */
    public VistaHistorialVentas() {
        initComponents();
        
        javax.swing.table.DefaultTableModel modeloNuevo = new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null}
        },
        new String [] {
            "ID", "Fecha/Hora", "Cajero", "Cliente", "IVA", "Total"
        }
    );
    tablaHistorial.setModel(modeloNuevo);
    tablaHistorial.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    tablaHistorial.setPreferredScrollableViewportSize(new Dimension(800, 350));
 
  
        
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
        tableGbc.insets = new Insets(10, 50, 20, 50);
        tableGbc.fill = GridBagConstraints.BOTH;
        tableGbc.weightx = 1.0;
        tableGbc.weighty = 1.0; // ← TABLA OCUPA TODO EL ESPACIO VERTICAL DISPONIBLE

        // Título
        panelCentral.add(lblTitulo, normalGbc);

        // Tabla ENORME
        panelCentral.add(ScrollHistorial, tableGbc);

        // ===== PANEL DE PAGINACIÓN =====
        JPanel panelPaginacion = new JPanel(new GridBagLayout());
        panelPaginacion.setOpaque(false);

        GridBagConstraints gbcPag = new GridBagConstraints();
        gbcPag.insets = new Insets(10, 30, 10, 30);
        gbcPag.fill = GridBagConstraints.HORIZONTAL;
        gbcPag.anchor = GridBagConstraints.CENTER;

        // Botón Anterior
        gbcPag.gridx = 0;
        gbcPag.gridy = 0;
        gbcPag.weightx = 0.3;
        btnAnteriorHistorial.setPreferredSize(new Dimension(140, 50));
        btnAnteriorHistorial.setBackground(new Color(255, 255, 255));
        btnAnteriorHistorial.setForeground(Color.DARK_GRAY);
        btnAnteriorHistorial.setFont(new Font("Arial Black", Font.BOLD, 30));
        btnAnteriorHistorial.setFocusPainted(false);
        panelPaginacion.add(btnAnteriorHistorial, gbcPag);

        // Etiqueta de Página
        gbcPag.gridx = 1;
        gbcPag.weightx = 0.4;
        lblPagina.setPreferredSize(new Dimension(200, 50));
        lblPagina.setFont(new Font("Arial Black", Font.BOLD, 30));
        lblPagina.setForeground(Color.WHITE);
        lblPagina.setHorizontalAlignment(JLabel.CENTER);
        panelPaginacion.add(lblPagina, gbcPag);

        // Botón Siguiente
        gbcPag.gridx = 2;
        gbcPag.weightx = 0.3;
        btnSiguienteHistorial.setPreferredSize(new Dimension(140, 50));
        btnSiguienteHistorial.setBackground(new Color(255, 255, 255));
        btnSiguienteHistorial.setForeground(Color.DARK_GRAY);
        btnSiguienteHistorial.setFont(new Font("Arial Black", Font.BOLD, 30));
        btnSiguienteHistorial.setFocusPainted(false);
        panelPaginacion.add(btnSiguienteHistorial, gbcPag);

        // Agregar panel paginación al central
        GridBagConstraints paginacionGbc = new GridBagConstraints();
        paginacionGbc.gridwidth = GridBagConstraints.REMAINDER;
        paginacionGbc.anchor = GridBagConstraints.CENTER;
        paginacionGbc.insets = new Insets(10, 50, 15, 50);
        paginacionGbc.fill = GridBagConstraints.HORIZONTAL;
        paginacionGbc.weightx = 1.0;
        paginacionGbc.weighty = 0;
        panelCentral.add(panelPaginacion, paginacionGbc);

        // ===== PANEL DE BOTONES con GridBagLayout para control total
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
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.gridwidth = GridBagConstraints.REMAINDER;
        buttonGbc.anchor = GridBagConstraints.CENTER;
        buttonGbc.insets = new Insets(10, 0, 50, 0);
        buttonGbc.fill = GridBagConstraints.HORIZONTAL;
        buttonGbc.weightx = 1.0;
        buttonGbc.weighty = 0;
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

        revalidate();
        repaint();
    }
    
    // Método para inyectar el controlador desde fuera
    public void establecerControlador(Controlador.ControladorHistorialVentas controlador) {
        this.controladorHistorial = controlador;
}
  // Método para cargar el historial de ventas en la tabla
    public void cargarHistorial(java.util.List<Modelo.HistorialVentaModelo> listaHistorial) {
        // Inicializar paginador si no existe
        if (paginadorHistorial == null) {
            paginadorHistorial = new PaginadorTabla<>(tablaHistorial, lblPagina, btnAnteriorHistorial, btnSiguienteHistorial);
        }

        // El paginador se encarga de mostrar los datos paginados (7 por página)
        paginadorHistorial.cargarDatos(listaHistorial);

        // PARA QUE LAS FILAS NO SEAN EDITABLES
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
        lblPagina = new javax.swing.JLabel();
        btnAnteriorHistorial = new javax.swing.JButton();
        btnSiguienteHistorial = new javax.swing.JButton();

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

        lblPagina.setText("Pagina");

        btnAnteriorHistorial.setText("Anterior");
        btnAnteriorHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorHistorialActionPerformed(evt);
            }
        });

        btnSiguienteHistorial.setText("Siguiente");
        btnSiguienteHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteHistorialActionPerformed(evt);
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
                        .addGap(0, 207, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(220, 220, 220))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnRecargar)
                                        .addGap(246, 246, 246))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAnteriorHistorial)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSiguienteHistorial)
                                    .addComponent(btnAtras))
                                .addGap(163, 163, 163))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ScrollHistorial)
                        .addContainerGap())))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPagina)
                    .addComponent(btnAnteriorHistorial)
                    .addComponent(btnSiguienteHistorial))
                .addContainerGap(63, Short.MAX_VALUE))
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
    }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAnteriorHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorHistorialActionPerformed
        // TODO add your handling code here:
       if (paginadorHistorial != null) {
            paginadorHistorial.irPaginaAnterior();
        }
    }//GEN-LAST:event_btnAnteriorHistorialActionPerformed

    private void btnSiguienteHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteHistorialActionPerformed
        // TODO add your handling code here:
        if (paginadorHistorial != null) {
            paginadorHistorial.irPaginaSiguiente();
        }
    }//GEN-LAST:event_btnSiguienteHistorialActionPerformed

    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollHistorial;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnAnterior1;
    private javax.swing.JButton btnAnteriorHistorial;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JButton btnSiguienteHistorial;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tablaHistorial;
    // End of variables declaration//GEN-END:variables
}
