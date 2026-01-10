/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Modelo.UsuarioModelo;
import Vista.VistaEditarUsuario;
import Vista.VistaGestionUsuarios;
import Vista.VistaMenuAdmin;
import java.util.List;
import javax.swing.JOptionPane;

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
        Modelo.UsuarioModelo usuario = Modelo.UsuarioModelo.obtenerPorId(idUsuario);  // ← Nueva llamada al modelo
        if (usuario == null) {
            JOptionPane.showMessageDialog(vistaGestion, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
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
      Modelo.UsuarioModelo usuario = Modelo.UsuarioModelo.obtenerPorId(idUsuario);  // ← Cambiado a obtenerPorId (eficiente)
      if (usuario == null) {
          JOptionPane.showMessageDialog(vistaGestion, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
          return;
      }
      if (usuario.isActivo()) {
          JOptionPane.showMessageDialog(vistaGestion, "El usuario ya está activo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
          return;
      }
      usuario.setActivo(true);
      usuario.cambiarEstado(true);
      cargarUsuarios();
      JOptionPane.showMessageDialog(vistaGestion, "Usuario activado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para inactivar un usuario
    public void inactivarUsuario(int idUsuario) {
      Modelo.UsuarioModelo usuario = Modelo.UsuarioModelo.obtenerPorId(idUsuario);  // ← Cambiado a obtenerPorId (eficiente)
      if (usuario == null) {
          JOptionPane.showMessageDialog(vistaGestion, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
          return;
      }
      if (!usuario.isActivo()) {
          JOptionPane.showMessageDialog(vistaGestion, "El usuario ya está inactivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
          return;
      }
      usuario.setActivo(false);
      usuario.cambiarEstado(false);
      cargarUsuarios();
      JOptionPane.showMessageDialog(vistaGestion, "Usuario inactivado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para volver al menú del administrador
    public void volverAlMenu() {
        vistaGestion.dispose();
        vistaMenuAdmin.setVisible(true);
    }

    // Método para recargar la lista de usuarios (usado desde VistaEditarUsuario)
    public void recargarLista() {
        cargarUsuarios();
    }
    
    
    // Getter para acceder a vistaMenuAdmin desde otros controladores
    public Vista.VistaMenuAdmin getVistaMenuAdmin() {
        return this.vistaMenuAdmin;
}
    //buscar a usuarios por la cedula 
    public void buscarUsuariosPorCedula(String cedulaBuscada) {
        cedulaBuscada = cedulaBuscada.trim();

        // Si está vacío → mostrar todos los usuarios
        if (cedulaBuscada.isEmpty()) {
            cargarUsuarios(); // Esto llama a obtenerTodosLosUsuarios()
            return;
        }

        // Validación de formato (10 dígitos numéricos)
        if (cedulaBuscada.length() != 10 || !cedulaBuscada.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(vistaGestion,
                "La cédula debe contener exactamente 10 dígitos numéricos.",
                "Formato inválido",
                JOptionPane.ERROR_MESSAGE);

            // ← CLAVE: restablecer a todos los usuarios para limpiar resultados anteriores
            cargarUsuarios();
            return;
        }

        // Validación algoritmo ecuatoriano
        if (!UsuarioModelo.validarCedulaEcuatoriana(cedulaBuscada)) {
            JOptionPane.showMessageDialog(vistaGestion,
                "La cédula ingresada no es válida según el algoritmo ecuatoriano.",
                "Cédula inválida",
                JOptionPane.ERROR_MESSAGE);

            // ← CLAVE: restablecer a todos los usuarios
            cargarUsuarios();
            return;
        }

        // Búsqueda en BD
        List<UsuarioModelo> listaFiltrada = UsuarioModelo.buscarPorCedula(cedulaBuscada);

        // Actualizar tabla
        vistaGestion.cargarUsuarios(listaFiltrada);

        // ← Mensajes controlados desde el CONTROLADOR (no desde la vista)
        if (listaFiltrada.isEmpty()) {
            JOptionPane.showMessageDialog(vistaGestion,
                "No se encontró ningún usuario con la cédula: " + cedulaBuscada,
                "Sin resultados",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vistaGestion,
                "Usuario encontrado",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}