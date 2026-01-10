/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.DetalleFacturaModelo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;  
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

// importaciones de pdf}
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private double subtotal;
    private double iva;
    private double totalConIva;
    private Controlador.ControladorVerFactura controladorVerFactura;
    private javax.swing.JScrollPane globalScroll;  // Para capturar el scroll completo
    private JPanel wrapper;  // Panel que contiene todo el contenido (para captura completa)

    /**
     * Creates new form VistaVerFactura
     */
    public VistaVerFactura(int numeroFactura, String nombresApellidos, String cedula,
                       String direccion, List<DetalleFacturaModelo> detalle,
                       double subtotal, double iva, double totalConIva) {
        initComponents();
      
  
        this.numeroFactura = numeroFactura;
        this.nombresApellidos = nombresApellidos;
        this.cedula = cedula;
        this.direccion = direccion == null || direccion.trim().isEmpty() ? "No registrada" : direccion;
        this.detalle = detalle;
        this.subtotal = subtotal;
        this.iva = iva;
        this.totalConIva = totalConIva;

    /* ---------- ESTILOS MEJORADOS (solo decoración, nada de lógica cambiada) ---------- */
    lblNombreNegocio.setFont(new Font("Arial Black", Font.BOLD, 48)); // Más grande y destacado
    lblNombreNegocio.setForeground(new Color(0, 100, 100));
    lblNombreNegocio.setHorizontalAlignment(javax.swing.JLabel.CENTER);

    lblRuc.setFont(new Font("Arial", Font.BOLD, 26));
    lblRuc.setForeground(Color.BLACK);
    lblRuc.setHorizontalAlignment(JLabel.CENTER);

    lblDireccionNegocio.setFont(new Font("Arial", Font.PLAIN, 22));
    lblDireccionNegocio.setForeground(Color.DARK_GRAY);
    lblDireccionNegocio.setHorizontalAlignment(JLabel.CENTER);

    lblNumeroFechaHora.setFont(new Font("Arial", Font.BOLD, 26));
    lblNumeroFechaHora.setForeground(Color.BLACK);
    lblNumeroFechaHora.setHorizontalAlignment(JLabel.LEFT);

    lblNombreCliente.setFont(new Font("Arial", Font.BOLD, 24));
    lblNombreCliente.setForeground(Color.BLACK);

    lblCedula.setFont(new Font("Arial", Font.BOLD, 24));
    lblCedula.setForeground(Color.BLACK);

    lblDireccion.setFont(new Font("Arial", Font.BOLD, 24));
    lblDireccion.setForeground(Color.BLACK);

    tablaDetalle.setFont(new Font("Arial", Font.PLAIN, 20)); // Letras más grandes en la tabla
    tablaDetalle.setForeground(new Color(0, 90, 90));
    tablaDetalle.setRowHeight(60); // Filas más altas para mejor lectura
    tablaDetalle.setGridColor(new Color(180, 180, 180)); // Grid suave
    tablaDetalle.setShowGrid(true);

    JTableHeader header = tablaDetalle.getTableHeader();
    header.setFont(new Font("Arial Black", Font.BOLD, 22)); // Header más grande
    header.setBackground(new Color(0, 102, 102));
    header.setForeground(Color.WHITE);
    header.setPreferredSize(new Dimension(header.getWidth(), 70)); // Header más alto

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    for (int i = 0; i < tablaDetalle.getColumnCount(); i++) {
        tablaDetalle.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    // Anchos mejorados para que se vea todo
    tablaDetalle.getColumnModel().getColumn(0).setPreferredWidth(550); // Producto más ancho
    tablaDetalle.getColumnModel().getColumn(1).setPreferredWidth(120);
    tablaDetalle.getColumnModel().getColumn(2).setPreferredWidth(140);
    tablaDetalle.getColumnModel().getColumn(3).setPreferredWidth(140);
    tablaDetalle.getColumnModel().getColumn(4).setPreferredWidth(140);

    // Totales más destacados
    lblIva.setFont(new Font("Arial Black", Font.BOLD, 32));
    lblIva.setForeground(Color.BLACK);

    lblTotal.setFont(new Font("Arial Black", Font.BOLD, 42)); // Total bien grande y verde
    lblTotal.setForeground(new Color(0, 140, 0));

    lblGracias.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 34));
    lblGracias.setForeground(new Color(0, 102, 102));
    lblGracias.setHorizontalAlignment(JLabel.CENTER);

    btnAceptar.setFont(new Font("Arial Black", Font.BOLD, 30));
    btnAceptar.setBackground(new Color(46, 204, 113));
    btnAceptar.setForeground(Color.WHITE);
    btnAceptar.setFocusPainted(false);
    btnAceptar.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40)); // Botón más grande

    // Paneles con bordes elegantes
    PanelEncabezado.setBackground(new Color(240, 250, 250));
    PanelEncabezado.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 102), 3));

    PanelCliente.setBackground(Color.WHITE);
    PanelCliente.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

    PanelTotales.setBackground(new Color(240, 255, 240));
    PanelTotales.setBorder(BorderFactory.createLineBorder(new Color(0, 140, 0), 3));

    PanelDetalle.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 102), 3));

    /* ---------- DATOS FACTURA ---------- */
    String numFacturaStr = String.format("%03d-%03d-%09d", 1, 1, numeroFactura);
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
    String fecha = df.format(new Date());
    String hora = tf.format(new Date());

    lblNombreNegocio.setText("SUPERMERCADOS TUTI");
    lblRuc.setText("RUC: 1793000000001");
    lblDireccionNegocio.setText("Dirección matriz: Av. Principal y Calle Secundaria, Eugenio Espejo - Calderón");
    lblNumeroFechaHora.setText("Factura N.º: " + numFacturaStr + " Fecha: " + fecha + " Hora: " + hora);
    lblNombreCliente.setText("Nombres y apellidos: " + this.nombresApellidos);
    lblCedula.setText("Cédula: " + this.cedula);
    lblDireccion.setText("Dirección: " + this.direccion);

    /* ---------- LLENAR TABLA (solo llenamos, NO recalculamos subtotal) ---------- */
    DefaultTableModel modelo = new DefaultTableModel(
        new String[]{"Producto", "Cantidad", "Precio", "Descuento", "Subtotal"}, 0
    );

    if (detalle != null) {
        for (DetalleFacturaModelo item : detalle) {
            String desc = String.format("%.0f%%", item.getDescuentoAplicado() * 100);
            modelo.addRow(new Object[]{
                item.getNombreProducto(),
                item.getCantidad(),
                String.format("%.2f", item.getPrecioUnitario()),
                desc,
                String.format("%.2f", item.getSubtotal())
            });
        }
    }
    tablaDetalle.setModel(modelo);
    tablaDetalle.setDefaultEditor(Object.class, null);

    /* ---------- ALTURA DINÁMICA: QUE LA TABLA MUESTRE TODAS SUS FILAS ---------- */
    int filas = modelo.getRowCount();
    int altoFila = tablaDetalle.getRowHeight();
    int altoHeader = header.getPreferredSize().height;
    int altoTabla = altoHeader + filas * altoFila + 20; // Margen extra

    tablaDetalle.setPreferredSize(new Dimension(1000, altoTabla));
    tablaDetalle.setMaximumSize(new Dimension(1000, altoTabla));
    tablaDetalle.setMinimumSize(new Dimension(1000, altoTabla));

    /* ---------- SACAMOS LA TABLA DEL SCROLL Y LA METEMOS EN PanelDetalle ---------- */
    jScrollPane2.setViewportView(null);
    PanelDetalle.removeAll();
    PanelDetalle.setLayout(new BorderLayout());
    PanelDetalle.add(header, BorderLayout.NORTH);
    PanelDetalle.add(tablaDetalle, BorderLayout.CENTER);
    PanelDetalle.revalidate();
    PanelDetalle.repaint();


       

    // --- MOSTRAR SUBTOTAL DE ÍTEMS ---
    lblSubtotalItem.setFont(new Font("Arial Black", Font.BOLD, 32));
    lblSubtotalItem.setForeground(Color.BLACK);
    lblSubtotalItem.setText("Subtotal: $" + String.format("%.2f", this.subtotal)); // ← Usa el subtotal que te pasaron

    lblIva.setText("IVA 15%: $" + String.format("%.2f", this.iva));
    lblTotal.setText("Total: $" + String.format("%.2f", this.totalConIva));
        
    lblMensaje.setFont(new Font("Arial", Font.BOLD, 20));
    lblMensaje.setForeground(new Color(0, 0, 0)); // Naranja oscuro para destacar
    lblMensaje.setHorizontalAlignment(JLabel.CENTER);
    lblMensaje.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(new Color(200, 150, 100), 2),
        "Promoción especial",
        javax.swing.border.TitledBorder.LEFT,
        javax.swing.border.TitledBorder.TOP,
        new Font("Arial", Font.BOLD, 16),
        new Color(0, 0, 0)
    ));
    lblMensaje.setText("Si la compra de un producto es mayor a 5 unidades, ¡tendrá el 10% de descuento!");
    
     lblGracias.setText("Gracias por su compra");
    /* ---------- COLORES ---------- */
    PanelEncabezado.setBackground(new Color(240, 250, 250));
    PanelCliente.setBackground(Color.WHITE);
    PanelTotales.setBackground(new Color(240, 255, 240));

    /* ---------- SCROLL GLOBAL DE LA VENTANA ---------- */
    Component originalContent = getContentPane();
    JPanel wrapper = new JPanel(new BorderLayout());
    wrapper.add(originalContent, BorderLayout.CENTER);
    JScrollPane globalScroll = new JScrollPane(wrapper);
    globalScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    globalScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    globalScroll.getVerticalScrollBar().setUnitIncrement(20); // Scroll más suave
    setContentPane(globalScroll);

    /* ---------- MAXIMIZAR ---------- */
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    

   this.wrapper = wrapper;  // ← NUEVA LÍNEA: guarda la referencia al wrapper
   wrapper.add(originalContent, BorderLayout.CENTER);

   globalScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
   globalScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
   globalScroll.getVerticalScrollBar().setUnitIncrement(20); // Scroll más suave
   setContentPane(globalScroll);
   /* ---------- MAXIMIZAR ---------- */
   setExtendedState(JFrame.MAXIMIZED_BOTH);

   // Guardar referencia al scroll global para usarlo al generar PDF
   this.globalScroll = globalScroll;

    revalidate();
    repaint();
    
    }
          
    public void establecerControlador(Controlador.ControladorVerFactura controlador) {
    this.controladorVerFactura = controlador;
}    

 
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        lblSubtotalItem = new javax.swing.JLabel();
        lblGracias = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        PanelDetalle = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        lblMensaje = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                    .addComponent(lblNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelClienteLayout.createSequentialGroup()
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 451, Short.MAX_VALUE)))
                .addContainerGap())
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

        lblSubtotalItem.setText("Subtotal");

        javax.swing.GroupLayout PanelTotalesLayout = new javax.swing.GroupLayout(PanelTotales);
        PanelTotales.setLayout(PanelTotalesLayout);
        PanelTotalesLayout.setHorizontalGroup(
            PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTotalesLayout.createSequentialGroup()
                .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTotalesLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblTotal))
                    .addGroup(PanelTotalesLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtotalItem)
                            .addComponent(lblIva))))
                .addContainerGap(581, Short.MAX_VALUE))
        );
        PanelTotalesLayout.setVerticalGroup(
            PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTotalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubtotalItem)
                .addGap(18, 18, 18)
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
        jScrollPane2.setViewportView(tablaDetalle);

        javax.swing.GroupLayout PanelDetalleLayout = new javax.swing.GroupLayout(PanelDetalle);
        PanelDetalle.setLayout(PanelDetalleLayout);
        PanelDetalleLayout.setHorizontalGroup(
            PanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetalleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelDetalleLayout.setVerticalGroup(
            PanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetalleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblMensaje.setText("Recuerde si la compra de un producto es mayor a 5 tendra el 10% de descuento ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(PanelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(152, 152, 152))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(PanelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblGracias)
                                .addGap(133, 133, 133)
                                .addComponent(btnAceptar)))))
                .addGap(0, 115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(PanelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(PanelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(PanelTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMensaje)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGracias)
                    .addComponent(btnAceptar))
                .addContainerGap(335, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
   // Scroll arriba (por si acaso)
    if (globalScroll != null) {
        globalScroll.getVerticalScrollBar().setValue(0);
    }

    // Captura TODO el contenido (incluye lo que está oculto por scroll)
    java.awt.Dimension tamanoCompleto = wrapper.getPreferredSize();
    BufferedImage image = new BufferedImage(
        tamanoCompleto.width,
        tamanoCompleto.height,
        BufferedImage.TYPE_INT_RGB
    );
    Graphics2D g2d = image.createGraphics();
    
    // Calidad máxima
    g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g2d.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING, java.awt.RenderingHints.VALUE_RENDER_QUALITY);
    
    wrapper.printAll(g2d);  // ← Esto captura TODO completo
    
    g2d.dispose();

    // Diálogo para guardar
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Guardar Factura como PDF");
    chooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf"));
    
    String numFacturaStr = String.format("%03d-%03d-%09d", 1, 1, numeroFactura);
    chooser.setSelectedFile(new java.io.File("Factura_" + numFacturaStr + ".pdf"));
    
    int resultado = chooser.showSaveDialog(this);
    
    if (resultado == JFileChooser.APPROVE_OPTION) {
        java.io.File archivo = chooser.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
            archivo = new java.io.File(archivo.getAbsolutePath() + ".pdf");
        }
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] bytesImagen = baos.toByteArray();

            // PDF horizontal para que quepa bien la tabla
            Document documento = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(archivo));
            documento.open();

            Image imagenPDF = Image.getInstance(bytesImagen);
            imagenPDF.scaleToFit(PageSize.A4.rotate().getWidth() - 40, PageSize.A4.rotate().getHeight() - 40);
            imagenPDF.setAlignment(Image.ALIGN_CENTER);

            documento.add(imagenPDF);
            documento.close();

            JOptionPane.showMessageDialog(this, 
                "¡Factura COMPLETA guardada como PDF!\nUbicación: " + archivo.getAbsolutePath(),
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al generar el PDF: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Volver a facturación
    if (controladorVerFactura != null) {
        controladorVerFactura.aceptar();
    }

    }//GEN-LAST:event_btnAceptarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCliente;
    private javax.swing.JPanel PanelDetalle;
    private javax.swing.JPanel PanelEncabezado;
    private javax.swing.JPanel PanelTotales;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDireccionNegocio;
    private javax.swing.JLabel lblGracias;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNombreNegocio;
    private javax.swing.JLabel lblNumeroFechaHora;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JLabel lblSubtotalItem;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaDetalle;
    // End of variables declaration//GEN-END:variables
}
