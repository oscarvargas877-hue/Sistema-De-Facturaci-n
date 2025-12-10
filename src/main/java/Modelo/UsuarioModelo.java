/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Modelo/UsuarioModelo.java
// Modelo/UsuarioModelo.java
package Modelo;

import BDD.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

// Clase que representa a un usuario y contiene la lógica de negocio relacionada con usuarios
public class UsuarioModelo {

    // Atributos del usuario
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia; // Se almacena como hash
    private String rol;

    // Constructor vacío
    public UsuarioModelo() {}

    // Constructor para crear un nuevo usuario (usado al registrar)
    public UsuarioModelo(String nombreUsuario, String contrasenia, String rol) {
        this.nombreUsuario = nombreUsuario;
        // Hasheamos la contraseña en el momento de crear el objeto
        this.contrasenia = BCrypt.hashpw(contrasenia, BCrypt.gensalt(12));
        this.rol = rol;
    }

    // Verifica si ya existe al menos un administrador en la base de datos
    // Usa el stored procedure sp_existe_admin
    public static boolean existeAdministrador() {
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();
        if (conexion == null) {
            return false; // No hay conexión, asumimos que no se puede verificar
        }

        try {
            // Preparamos la llamada al stored procedure
            CallableStatement sentencia = conexion.prepareCall("{CALL sp_existe_admin()}");
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return resultado.getInt("existe") > 0;
            }
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException excepcion) {
                excepcion.printStackTrace();
            }
        }
        return false;
    }

    // Valida las credenciales de un usuario al hacer login
    // Usa el stored procedure sp_validar_login
    public static UsuarioModelo validarLogin(String nombreUsuario, String contraseniaIngresada) {
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();
        if (conexion == null) {
            return null;
        }

        try {
            CallableStatement sentencia = conexion.prepareCall("{CALL sp_validar_login(?, ?, ?, ?)}");
            
            // Parámetro de entrada: nombre de usuario
            sentencia.setString(1, nombreUsuario);
            // Parámetros de salida
            sentencia.registerOutParameter(2, java.sql.Types.INTEGER);
            sentencia.registerOutParameter(3, java.sql.Types.VARCHAR);
            sentencia.registerOutParameter(4, java.sql.Types.VARCHAR);

            sentencia.execute();

            int idUsuario = sentencia.getInt(2);
            String rol = sentencia.getString(3);
            String hashAlmacenado = sentencia.getString(4);

            // Si no se encontró el usuario
            if (hashAlmacenado == null) {
                return null;
            }

            // Verificar contraseña con BCrypt
            if (BCrypt.checkpw(contraseniaIngresada, hashAlmacenado)) {
                UsuarioModelo usuario = new UsuarioModelo();
                usuario.setIdUsuario(idUsuario);
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setRol(rol);
                return usuario;
            }

        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException excepcion) {
                excepcion.printStackTrace();
            }
        }
        return null;
    }

    // Guarda este usuario en la base de datos
    // Usa el stored procedure sp_registrar_usuario
    public void guardar() {
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();
        if (conexion == null) {
            return;
        }

        try {
            CallableStatement sentencia = conexion.prepareCall("{CALL sp_registrar_usuario(?, ?, ?)}");
            sentencia.setString(1, this.nombreUsuario);
            sentencia.setString(2, this.contrasenia); // Ya está hasheada
            sentencia.setString(3, this.rol);
            sentencia.execute();
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException excepcion) {
                excepcion.printStackTrace();
            }
        }
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}