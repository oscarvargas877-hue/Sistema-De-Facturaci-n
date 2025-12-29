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
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
/**
 *
 * @author Usuario
 */
public class VistaGestionUsuarios extends javax.swing.JFrame {
    // Atributo para guardar la referencia al controlador
    private Controlador.ControladorGestionUsuarios controladorGestion;
    // === PAGINACIÓN DE USUARIOS ===
    private PaginadorTabla<Modelo.UsuarioModelo> paginadorUsuarios;
    /**
     * Creates new form VistaGestionUsuarios
     */
    public VistaGestionUsuarios() {
        initComponents(); // no borrar
    // PANTALLA COMPLETA
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    // Fondo profesional
    getContentPane().setBackground(new Color(70, 130, 180));

    // Panel central
    JPanel panelCentral = new JPanel();
    panelCentral.setLayout(new GridBagLayout());
    panelCentral.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 1.0;

    // TÍTULO 
    gbc.insets = new Insets(20, 0, 10, 0);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weighty = 0;
    panelCentral.add(lblTitulo, gbc);

    // Lista de usuarios
    gbc.insets = new Insets(10, 0, 20, 0);
    panelCentral.add(lblListaUsuarios, gbc);

    //  TABLA altura reducida y controlada 
    gbc.insets = new Insets(0, 50, 20, 50); // margen lateral y debajo
    gbc.fill = GridBagConstraints.HORIZONTAL; // solo horizontal
    gbc.weighty = 0; //  NO SE EXPANDE VERTICALMENTE
    ScrollTablaUsuarios.setPreferredSize(new Dimension(1200, 250)); // ALTURA FIJA DE 250
    ScrollTablaUsuarios.setMaximumSize(new Dimension(1200, 300)); // EVITA QUE SE HAGA MÁS GRANDE
    panelCentral.add(ScrollTablaUsuarios, gbc);

    //  PANEL DE BOTONES
    JPanel panelBotones = new JPanel();
    panelBotones.setOpaque(false);
    panelBotones.add(btnEditar);
    panelBotones.add(btnActivar);
    panelBotones.add(btnInactivar);
    panelBotones.add(btnAtras);

    gbc.insets = new Insets(0, 0, 30, 0); // espacio inferior
    gbc.fill = GridBagConstraints.NONE;
    gbc.weighty = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    panelCentral.add(panelBotones, gbc);

    // Aplicar panel central
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panelCentral, BorderLayout.CENTER);

    // ESTILOS VISUALES 
    lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 48));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setHorizontalAlignment(JLabel.CENTER);

    lblListaUsuarios.setFont(new Font("Arial Black", Font.BOLD, 28));
    lblListaUsuarios.setForeground(Color.WHITE);
    lblListaUsuarios.setHorizontalAlignment(JLabel.CENTER);

    tablaUsuarios.setFont(new Font("Arial", Font.PLAIN, 20));
    tablaUsuarios.setRowHeight(50);
    tablaUsuarios.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 24));
    tablaUsuarios.getTableHeader().setForeground(Color.WHITE);
    tablaUsuarios.getTableHeader().setBackground(new Color(0, 102, 102));

    // Botones grandes y coloridos
    Font fontBotones = new Font("Arial Black", Font.BOLD, 24);
    Dimension tamañoBoton = new Dimension(300, 70);

    btnEditar.setFont(fontBotones);
    btnEditar.setPreferredSize(tamañoBoton);
    btnEditar.setBackground(new Color(52, 152, 219));
    btnEditar.setForeground(Color.WHITE);

    btnActivar.setFont(fontBotones);
    btnActivar.setPreferredSize(tamañoBoton);
    btnActivar.setBackground(new Color(46, 204, 113));
    btnActivar.setForeground(Color.WHITE);

    btnInactivar.setFont(fontBotones);
    btnInactivar.setPreferredSize(tamañoBoton);
    btnInactivar.setBackground(new Color(231, 76, 60));
    btnInactivar.setForeground(Color.WHITE);

    btnAtras.setFont(fontBotones);
    btnAtras.setPreferredSize(tamañoBoton);
    btnAtras.setBackground(new Color(155, 89, 182));
    btnAtras.setForeground(Color.WHITE);
    
        // ==================== BARRA DE PAGINACIÓN INFERIOR - GESTIÓN DE USUARIOS ====================

    // Estilizar los botones y label de paginación que ya tienes en el .form
    btnAnteriorGestion.setFont(new Font("Arial Black", Font.BOLD, 22));
    btnAnteriorGestion.setForeground(new Color(0, 102, 102));
    btnAnteriorGestion.setBackground(Color.WHITE);
    btnAnteriorGestion.setPreferredSize(new Dimension(220, 70));
    btnAnteriorGestion.setFocusPainted(false);

    btnSiguienteGestion.setFont(new Font("Arial Black", Font.BOLD, 22));
    btnSiguienteGestion.setForeground(new Color(0, 102, 102));
    btnSiguienteGestion.setBackground(Color.WHITE);
    btnSiguienteGestion.setPreferredSize(new Dimension(220, 70));
    btnSiguienteGestion.setFocusPainted(false);

    lblPagina.setFont(new Font("Arial Black", Font.BOLD, 24));
    lblPagina.setForeground(Color.WHITE);
    lblPagina.setHorizontalAlignment(SwingConstants.CENTER);

    // Crear panel dedicado solo para la paginación (para mejor control)
    JPanel panelPaginacion = new JPanel(new BorderLayout(60, 20));
    panelPaginacion.setBackground(new Color(70, 130, 180));
    panelPaginacion.setBorder(BorderFactory.createEmptyBorder(20, 80, 40, 80));
    panelPaginacion.add(btnAnteriorGestion, BorderLayout.WEST);
    panelPaginacion.add(lblPagina, BorderLayout.CENTER);
    panelPaginacion.add(btnSiguienteGestion, BorderLayout.EAST);

    // Agregar la barra de paginación al fondo de la ventana
    getContentPane().add(panelPaginacion, BorderLayout.SOUTH);

    // Inicializar el paginador con los componentes existentes
    paginadorUsuarios = new PaginadorTabla<>(tablaUsuarios, lblPagina, btnAnteriorGestion, btnSiguienteGestion);

    revalidate();
    repaint();
    }
    // Método para inyectar el controlador desde fuera
    public void establecerControlador(Controlador.ControladorGestionUsuarios controlador) {
        this.controladorGestion = controlador;
    }
    

  // Método público para cargar usuarios con paginación  colores en estado
   public void cargarUsuarios(java.util.List<Modelo.UsuarioModelo> listaUsuarios) {
    // Usar paginador para mostrar solo 5 por página
    paginadorUsuarios.cargarDatos(listaUsuarios);

    // Renderer personalizado para estado y colores
    tablaUsuarios.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Color del texto: SIEMPRE NEGRO excepto si está seleccionado
            if (isSelected) {
                setForeground(Color.WHITE);
            } else {
                setForeground(Color.BLACK);
            }

            // La columna "Estado" es la 3 (índice 3) - colorear SOLO esa celda
            if (column == 3 && value != null) {
                String estado = value.toString();
                
                if ("Activo".equals(estado)) {
                    setForeground(isSelected ? Color.WHITE : new Color(0, 153, 0)); // Verde
                    setFont(getFont().deriveFont(Font.BOLD));
                } else if ("Inactivo".equals(estado)) {
                    setForeground(isSelected ? Color.WHITE : Color.RED); // Rojo
                    setFont(getFont().deriveFont(Font.BOLD));
                }
            } else {
                setFont(getFont().deriveFont(Font.PLAIN));
            }

            // Fondo de fila seleccionada
            setBackground(isSelected ? new Color(0, 120, 215) : Color.WHITE);

            return this;
        }
    });

    // Tabla no editable
    tablaUsuarios.setDefaultEditor(Object.class, null);
}
     // Método para obtener el ID del usuario seleccionado
    private int obtenerIdUsuarioSeleccionado() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            return (int) tablaUsuarios.getValueAt(filaSeleccionada, 0);
        }
        return -1; // No hay selección
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
        lblListaUsuarios = new javax.swing.JLabel();
        ScrollTablaUsuarios = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        btnActivar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnInactivar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();
        btnAnteriorGestion = new javax.swing.JButton();
        btnSiguienteGestion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Gestión De Usuarios");

        lblListaUsuarios.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblListaUsuarios.setForeground(new java.awt.Color(0, 102, 102));
        lblListaUsuarios.setText("Lista de usuarios ");

        tablaUsuarios.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        tablaUsuarios.setForeground(new java.awt.Color(0, 102, 102));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Usuario", "Rol", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tablaUsuarios);

        ScrollTablaUsuarios.setViewportView(jScrollPane1);

        btnActivar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnActivar.setForeground(new java.awt.Color(0, 102, 102));
        btnActivar.setText("Activar Usuarios");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(0, 102, 102));
        btnEditar.setText("Editar Usuario");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnInactivar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnInactivar.setForeground(new java.awt.Color(0, 102, 102));
        btnInactivar.setText("Inactivar Usuarios");
        btnInactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInactivarActionPerformed(evt);
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

        lblPagina.setText("Pagina");

        btnAnteriorGestion.setText("Anterior");
        btnAnteriorGestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorGestionActionPerformed(evt);
            }
        });

        btnSiguienteGestion.setText("Siguiente");
        btnSiguienteGestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteGestionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblListaUsuarios)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnEditar)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(238, 238, 238)
                                            .addComponent(btnActivar))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(105, 105, 105)
                                            .addComponent(btnAnteriorGestion)
                                            .addGap(67, 67, 67)
                                            .addComponent(lblPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSiguienteGestion)))
                                    .addGap(81, 81, 81)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAtras)
                                        .addComponent(btnInactivar))
                                    .addGap(45, 45, 45))
                                .addComponent(ScrollTablaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTitulo)
                .addGap(38, 38, 38)
                .addComponent(lblListaUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ScrollTablaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnActivar)
                    .addComponent(btnInactivar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(lblPagina)
                    .addComponent(btnAnteriorGestion)
                    .addComponent(btnSiguienteGestion))
                .addGap(87, 87, 87))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
         int idUsuario = obtenerIdUsuarioSeleccionado();
        if (idUsuario == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor seleccione un usuario para editar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (controladorGestion != null) {
            controladorGestion.editarUsuario(idUsuario);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        // TODO add your handling code here:
        int idUsuario = obtenerIdUsuarioSeleccionado();
        if (idUsuario == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor seleccione un usuario para activar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (controladorGestion != null) {
            controladorGestion.activarUsuario(idUsuario);
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void btnInactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInactivarActionPerformed
        // TODO add your handling code here:
          int idUsuario = obtenerIdUsuarioSeleccionado();
        if (idUsuario == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor seleccione un usuario para inactivar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (controladorGestion != null) {
            controladorGestion.inactivarUsuario(idUsuario);
        }
    }//GEN-LAST:event_btnInactivarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
          if (controladorGestion != null) {
            controladorGestion.volverAlMenu();
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAnteriorGestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorGestionActionPerformed
        // TODO add your handling code here:
         if (paginadorUsuarios != null) {
        paginadorUsuarios.irPaginaAnterior();
    }
    }//GEN-LAST:event_btnAnteriorGestionActionPerformed

    private void btnSiguienteGestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteGestionActionPerformed
        // TODO add your handling code here:
         if (paginadorUsuarios != null) {
        paginadorUsuarios.irPaginaSiguiente();
    }
    }//GEN-LAST:event_btnSiguienteGestionActionPerformed

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
            java.util.logging.Logger.getLogger(VistaGestionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGestionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGestionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGestionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGestionUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollTablaUsuarios;
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnAnteriorGestion;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInactivar;
    private javax.swing.JButton btnSiguienteGestion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblListaUsuarios;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
