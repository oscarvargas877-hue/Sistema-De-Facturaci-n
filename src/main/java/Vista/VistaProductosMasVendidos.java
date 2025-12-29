/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;
import Modelo.PaginadorTabla;
import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Usuario
 */
public class VistaProductosMasVendidos extends javax.swing.JFrame {
    private Controlador.ControladorProductosMasVendidos controladorProductos;
    private ChartPanel panelGrafico;
    // === PAGINACIÓN DE PRODUCTOS MÁS VENDIDOS ===
    private PaginadorTabla<Modelo.ProductoMasVendidoModelo> paginadorProductos;

    /**
     * Creates new form VistaProductosMasVendidos
     */
    public VistaProductosMasVendidos() {
        initComponents();//NO BORRAR
        
        // PANTALLA COMPLETA
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Fondo profesional
        getContentPane().setBackground(new Color(70, 130, 180));

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridBagLayout());
        panelCentral.setOpaque(false);

        GridBagConstraints normalGbc = new GridBagConstraints();
        normalGbc.gridwidth = GridBagConstraints.REMAINDER;
        normalGbc.anchor = GridBagConstraints.CENTER;
        normalGbc.insets = new Insets(20, 0, 20, 0);
        normalGbc.fill = GridBagConstraints.HORIZONTAL;
        normalGbc.weightx = 1.0;
        normalGbc.weighty = 0;

        GridBagConstraints tableGbc = new GridBagConstraints();
        tableGbc.gridwidth = GridBagConstraints.REMAINDER;
        tableGbc.anchor = GridBagConstraints.CENTER;
        tableGbc.insets = new Insets(10, 50, 20, 50);
        tableGbc.fill = GridBagConstraints.BOTH;
        tableGbc.weightx = 1.0;
        tableGbc.weighty = 0.4; // Tabla alta pero controlada

        GridBagConstraints chartGbc = new GridBagConstraints();
        chartGbc.gridwidth = GridBagConstraints.REMAINDER;
        chartGbc.anchor = GridBagConstraints.CENTER;
        chartGbc.insets = new Insets(10, 50, 30, 50);
        chartGbc.fill = GridBagConstraints.BOTH;
        chartGbc.weightx = 1.0;
        chartGbc.weighty = 0.6; // Gráfico ocupa más espacio (más grande)

        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.gridwidth = GridBagConstraints.REMAINDER;
        buttonGbc.anchor = GridBagConstraints.CENTER;
        buttonGbc.insets = new Insets(10, 0, 50, 0);
        buttonGbc.fill = GridBagConstraints.HORIZONTAL;
        buttonGbc.weightx = 1.0;
        buttonGbc.weighty = 0;

        // Título
        panelCentral.add(jLabel1, normalGbc);

        // Tabla
        panelCentral.add(ScrollTablaProductos, tableGbc);

        // Gráfico de barras (ocupa más espacio)
        panelCentral.add(ScrollDiagramaDeBarras, chartGbc);

        //  PANEL DE BOTONES: con GridBagLayout para que se vean completos
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
        jLabel1.setFont(new Font("Arial Black", Font.BOLD, 48));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setHorizontalAlignment(JLabel.CENTER);

        tablaProductos.setFont(new Font("Arial", Font.PLAIN, 22));
        tablaProductos.setRowHeight(50);
        tablaProductos.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 26));
        tablaProductos.getTableHeader().setForeground(Color.WHITE);
        tablaProductos.getTableHeader().setBackground(new Color(0, 102, 102));

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
// AGREGA ESTO ANTES DE revalidate(); EN EL CONSTRUCTOR

    // ================== AGREGAR BOTONES DE PAGINACIÓN AL PANEL ==================
    JPanel panelPaginacion = new JPanel(new GridBagLayout());
    panelPaginacion.setOpaque(false);

    GridBagConstraints gbcPag = new GridBagConstraints();
    gbcPag.insets = new Insets(10, 20, 10, 20);
    gbcPag.fill = GridBagConstraints.HORIZONTAL;
    gbcPag.weightx = 1.0;
    gbcPag.anchor = GridBagConstraints.CENTER;

    // Botón Anterior
    Font fontPag = new Font("Arial Black", Font.BOLD, 26);
    btnAnterior.setFont(fontPag);
    btnAnterior.setBackground(Color.magenta);  
    btnAnterior.setForeground(Color.BLACK);
    btnAnterior.setPreferredSize(new Dimension(200, 70));
    btnAnterior.setFocusPainted(false);
    panelPaginacion.add(btnAnterior, gbcPag);

    // Etiqueta de página
    lblPagina.setFont(new Font("Arial Black", Font.BOLD, 28));
    lblPagina.setForeground(Color.WHITE);
    lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
    gbcPag.weightx = 0.5;
    panelPaginacion.add(lblPagina, gbcPag);

    // Botón Siguiente
    btnSiguiente.setFont(fontPag);
    btnSiguiente.setBackground(Color.MAGENTA);  
    btnSiguiente.setForeground(Color.BLACK);
    btnSiguiente.setPreferredSize(new Dimension(200, 70));
    btnSiguiente.setFocusPainted(false);
    gbcPag.weightx = 1.0;
    panelPaginacion.add(btnSiguiente, gbcPag);
    // Agregar panel de paginación al panel central
    GridBagConstraints paginacionGbc = new GridBagConstraints();
    paginacionGbc.gridwidth = GridBagConstraints.REMAINDER;
    paginacionGbc.anchor = GridBagConstraints.CENTER;
    paginacionGbc.insets = new Insets(10, 0, 20, 0);
    paginacionGbc.fill = GridBagConstraints.HORIZONTAL;
    paginacionGbc.weightx = 1.0;
    paginacionGbc.weighty = 0;

    panelCentral.add(panelPaginacion, paginacionGbc);

    // Inicializar el paginador
    paginadorProductos = new PaginadorTabla<>(tablaProductos, lblPagina, btnAnterior, btnSiguiente);
        
    // AGREGA ESTO ANTES DE revalidate(); PARA QUE LA TABLA SE VEA

    // ================== AUMENTAR ALTURA DE LA TABLA ==================
    tableGbc.weighty = 0.5; // Aumentar de 0.4 a 0.5 para que la tabla ocupe más espacio
    tablaProductos.setRowHeight(60); // Aumentar altura de filas
    ScrollTablaProductos.setPreferredSize(new Dimension(900, 300)); // Tabla más grande

    // ================== REDUCIR GRÁFICO ==================
    chartGbc.weighty = 0.4; // Reducir de 0.6 a 0.4
    ScrollDiagramaDeBarras.setPreferredSize(new Dimension(900, 250)); // Gráfico más pequeño
        

        revalidate();
        repaint();
    }
    // Método para inyectar el controlador desde fuera
    public void establecerControlador(Controlador.ControladorProductosMasVendidos controlador) {
        this.controladorProductos = controlador;
    }
    
   
    // MÉTODO NUEVO MUESTRA TABLA GRÁFICO DE BARRAS
        public void mostrarProductosMasVendidos(java.util.List<Modelo.ProductoMasVendidoModelo> listaProductos) {
        // El paginador carga la tabla con solo 5 por página
        paginadorProductos.cargarDatos(listaProductos);

        // Actualizar gráfico con TODOS los datos (no solo la página actual)
        actualizarGraficoBarras(listaProductos);

        // Tabla no editable
        tablaProductos.setDefaultEditor(Object.class, null);
    }
        

     // MÉTODO CREA Y MUESTRA EL GRÁFICO DE BARRAS
    private void actualizarGraficoBarras(java.util.List<Modelo.ProductoMasVendidoModelo> listaProductos) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Modelo.ProductoMasVendidoModelo p : listaProductos) {
            dataset.addValue(p.getCantidadTotalVendida(), "Cantidad Vendida", p.getNombreProducto());
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Productos Más Vendidos",
            "Producto",
            "Cantidad Vendida",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        // Estilo profesional
        chart.setBackgroundPaint(new Color(40, 40, 40));
        chart.getTitle().setPaint(Color.WHITE);
        chart.getCategoryPlot().setBackgroundPaint(new Color(60, 60, 60));
        chart.getCategoryPlot().getDomainAxis().setLabelPaint(Color.WHITE);
        chart.getCategoryPlot().getRangeAxis().setLabelPaint(Color.WHITE);
        chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.CYAN);
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(0, 170, 255));

        // ZOOM QUE SÍ FUNCIONA EN JFreeChart 1.5.3
        ChartPanel chartPanel = new ChartPanel(chart);

        // ESTAS SON LAS LÍNEAS CORRECTAS 
        chartPanel.setMouseWheelEnabled(true);           // RUEDA DEL MOUSE = ZOOM
        chartPanel.setRangeZoomable(true);               // Permite zoom vertical
        chartPanel.setDomainZoomable(true);              // Permite zoom horizontal
        chartPanel.setZoomAroundAnchor(true);            // Zoom centrado en el cursor
        chartPanel.setFillZoomRectangle(true);           // Rectángulo de zoom visible
        chartPanel.setZoomFillPaint(new Color(100, 100, 255, 50)); // Color del rectángulo

        // Poner el gráfico en el scroll
        ScrollDiagramaDeBarras.setViewportView(chartPanel);
    }
    // Método para mostrar mensajes de error (si ocurre algún problema)
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

        jLabel1 = new javax.swing.JLabel();
        ScrollTablaProductos = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        btnRecargar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        ScrollDiagramaDeBarras = new javax.swing.JScrollPane();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Productos Más Vendidos");

        ScrollTablaProductos.setForeground(new java.awt.Color(0, 102, 102));

        tablaProductos.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        tablaProductos.setForeground(new java.awt.Color(0, 102, 102));
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Producto", "Cantidad total vendida"
            }
        ));
        jScrollPane2.setViewportView(tablaProductos);

        ScrollTablaProductos.setViewportView(jScrollPane2);

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(ScrollTablaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(ScrollDiagramaDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAnterior)
                                .addGap(41, 41, 41)
                                .addComponent(lblPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSiguiente))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRecargar)
                                .addGap(147, 147, 147)
                                .addComponent(btnAtras)))))
                .addContainerGap(372, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(66, 66, 66)
                .addComponent(ScrollTablaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(ScrollDiagramaDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPagina)
                    .addComponent(btnAnterior)
                    .addComponent(btnSiguiente))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnRecargar))
                .addGap(100, 100, 100))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarActionPerformed
        // TODO add your handling code here:
        // Llamar al controlador para recargar los datos desde la BD
        if (controladorProductos != null) {
          controladorProductos.cargarProductosMasVendidos();
        }
    }//GEN-LAST:event_btnRecargarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
         // Llamar al controlador para volver al menú del administrador
        if (controladorProductos != null) {
        controladorProductos.volverAlMenu();
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
     if (paginadorProductos != null) {
        paginadorProductos.irPaginaAnterior();
       
    }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
    if (paginadorProductos != null) {
        paginadorProductos.irPaginaSiguiente();
    
    }
    }//GEN-LAST:event_btnSiguienteActionPerformed

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
            java.util.logging.Logger.getLogger(VistaProductosMasVendidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaProductosMasVendidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaProductosMasVendidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaProductosMasVendidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaProductosMasVendidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollDiagramaDeBarras;
    private javax.swing.JScrollPane ScrollTablaProductos;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
