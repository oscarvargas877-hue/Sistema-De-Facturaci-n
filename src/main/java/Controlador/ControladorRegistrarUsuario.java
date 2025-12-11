/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controlador;

import Modelo.UsuarioModelo;
import Vista.VistaMenuAdmin;
import Vista.VistaRegistrarUsuario;

// Controlador para el registro de nuevos usuarios (HU003)
public class ControladorRegistrarUsuario {

    // Referencias a las vistas
    private VistaRegistrarUsuario vistaRegistrar;
    private VistaMenuAdmin vistaMenuAdmin;

    // Constructor que recibe ambas vistas
    public ControladorRegistrarUsuario(VistaRegistrarUsuario vista, VistaMenuAdmin menuAdmin) {
        this.vistaRegistrar = vista;
        this.vistaMenuAdmin = menuAdmin;
    }

    // Método para registrar un nuevo usuario
    public void registrarUsuario(String nombreUsuario, String contrasenia, String rol) {
        // Crear el objeto UsuarioModelo (el modelo hashea la contraseña automáticamente)
        UsuarioModelo nuevoUsuario = new UsuarioModelo(nombreUsuario, contrasenia, rol);

        try {
            // Guardar en la base de datos usando el stored procedure
            nuevoUsuario.guardar();

            // Mostrar mensaje de éxito
            vistaRegistrar.mostrarMensaje("Usuario registrado correctamente.", true);

            // Cerrar esta ventana después de 2 segundos y volver al menú
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                vistaRegistrar.dispose();
                vistaMenuAdmin.setVisible(true);
            }).start();

        } catch (Exception excepcion) {
            // Manejar error de nombre duplicado (viene de la BD por la restricción UNIQUE)
            if (excepcion.getMessage() != null && excepcion.getMessage().contains("Duplicate entry")) {
                vistaRegistrar.mostrarMensaje("El nombre de usuario ya existe. Elija otro.", false);
            } else {
                // Otro error (ej: conexión)
                vistaRegistrar.mostrarMensaje("Error al registrar el usuario. Intente de nuevo.", false);
                excepcion.printStackTrace();
            }
        }
    }

    // Método para cancelar el registro y volver al menú
    public void cancelar() {
        vistaRegistrar.dispose();
        vistaMenuAdmin.setVisible(true);
    }

    // Método para volver al menú (usado por el botón "Atrás")
    public void volverAlMenu() {
        vistaRegistrar.dispose();
        vistaMenuAdmin.setVisible(true);
    }
}