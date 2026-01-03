/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import BDD.ConexionBDD;
import Modelo.UsuarioModelo;
import Vista.VistaEditarUsuario;
import Vista.VistaGestionUsuarios;
import Vista.VistaMenuAdmin;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

// Controlador para la gestión de usuarios (HU003)
public class ControladorGestionUsuarios {

    private VistaGestionUsuarios vistaGestion;
    private VistaMenuAdmin vistaMenuAdmin;

    // Constructor
    public ControladorGestionUsuarios(VistaGestionUsuarios vista, VistaMenuAdmin menuAdmin) {
        this.vistaGestion = vista;
        this.vistaMenuAdmin = menuAdmin;
    }

    // Método para cargar y mostrar todos los usuarios al abrir la ventana
    public void cargarUsuarios() {
        java.util.List<Modelo.UsuarioModelo> listaUsuarios = UsuarioModelo.obtenerTodosLosUsuarios();
        vistaGestion.cargarUsuarios(listaUsuarios);
    }

    public void editarUsuario(int idUsuario) {
    // Buscar el usuario completo con TODOS los datos desde la base de datos
    Modelo.UsuarioModelo usuario = obtenerUsuarioCompletoPorId(idUsuario);
    if (usuario == null) {
        javax.swing.JOptionPane.showMessageDialog(vistaGestion, "Usuario no encontrado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    

    // Abrir ventana de edición
    vistaGestion.dispose();
    VistaEditarUsuario vistaEditar = new VistaEditarUsuario(usuario);
    ControladorEditarUsuario controladorEditar = new ControladorEditarUsuario(vistaEditar, this);
    vistaEditar.establecerControlador(controladorEditar);
    vistaEditar.setVisible(true);
   
}
    // Método para activar un usuario
    public void activarUsuario(int idUsuario) {
        Modelo.UsuarioModelo usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            javax.swing.JOptionPane.showMessageDialog(vistaGestion, "Usuario no encontrado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si ya está activo
        if (usuario.isActivo()) {
            javax.swing.JOptionPane.showMessageDialog(vistaGestion, "El usuario ya está activo.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Cambiar el estado a activo
        usuario.setActivo(true);
        usuario.cambiarEstado(true);

        // Recargar la lista
        cargarUsuarios();
        javax.swing.JOptionPane.showMessageDialog(vistaGestion, "Usuario activado correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para inactivar un usuario
    public void inactivarUsuario(int idUsuario) {
        Modelo.UsuarioModelo usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            javax.swing.JOptionPane.showMessageDialog(vistaGestion, "Usuario no encontrado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si ya está inactivo
        if (!usuario.isActivo()) {
            javax.swing.JOptionPane.showMessageDialog(vistaGestion, "El usuario ya está inactivo.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Cambiar el estado a inactivo
        usuario.setActivo(false);
        usuario.cambiarEstado(false);

        // Recargar la lista
        cargarUsuarios();
        javax.swing.JOptionPane.showMessageDialog(vistaGestion, "Usuario inactivado correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para volver al menú del administrador
    public void volverAlMenu() {
        vistaGestion.dispose();
        vistaMenuAdmin.setVisible(true);
    }

    // Método auxiliar para buscar un usuario por ID
    private Modelo.UsuarioModelo buscarUsuarioPorId(int id) {
        java.util.List<Modelo.UsuarioModelo> lista = UsuarioModelo.obtenerTodosLosUsuarios();
        for (Modelo.UsuarioModelo u : lista) {
            if (u.getIdUsuario() == id) {
                return u;
            }
        }
        return null;
    }

    // Método para recargar la lista de usuarios (usado desde VistaEditarUsuario)
    public void recargarLista() {
        cargarUsuarios();
    }
    
    // Método nuevo: obtiene TODOS los datos del usuario desde la BD
    private Modelo.UsuarioModelo obtenerUsuarioCompletoPorId(int idUsuario) {
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();
        if (conexion == null) return null;

        Modelo.UsuarioModelo usuario = null;
        try {
            CallableStatement sentencia = conexion.prepareCall("{CALL sp_obtener_usuario_completo(?)}");
            sentencia.setInt(1, idUsuario);
            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                usuario = new Modelo.UsuarioModelo();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setRol(rs.getString("rol"));
                usuario.setActivo(rs.getInt("activo") == 1);
                usuario.setCedula(rs.getString("cedula"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setGenero(rs.getString("genero"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }
    
    // Getter para acceder a vistaMenuAdmin desde otros controladores
    public Vista.VistaMenuAdmin getVistaMenuAdmin() {
        return this.vistaMenuAdmin;
}
}