/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

import BDD.ConexionBDD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

// Clase que representa a un usuario y contiene toda la lógica de negocio relacionada
public class UsuarioModelo extends Persona {

    // Atributos del usuario
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia; // Almacenará el hash
    private String rol;
    private boolean activo; // true = activo, false = inactivo

    // Constructor vacío (necesario para instanciar sin datos)
    public UsuarioModelo() {
        super();// Llama al constructor vacío de Persona
    }

    // Constructor para crear un nuevo usuario (usado al registrar)
    public UsuarioModelo(String nombreUsuario, String contrasenia, String rol) {
     super(); // Inicializa los campos de Persona (por ahora en null/0)
     this.nombreUsuario = nombreUsuario;
     this.contrasenia = BCrypt.hashpw(contrasenia, BCrypt.gensalt());
     this.rol = rol;
     this.activo = true;
    }

    // Constructor para crear un usuario con estado activo  (usado en gestión)
    public UsuarioModelo(String nombreUsuario, String contrasenia, String rol, boolean activo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = BCrypt.hashpw(contrasenia, BCrypt.gensalt(12));
        this.rol = rol;
        this.activo = activo;
    }

    // Verifica si ya existe al menos un administrador en la base de datos
    public static boolean existeAdministrador() {
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();
        if (conexion == null) {
            return false;
        }

        try {
            CallableStatement llamada = conexion.prepareCall("{CALL sp_existe_admin()}");
            ResultSet resultado = llamada.executeQuery();
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

    // Valida las credenciales de login usando BCrypt
    public static UsuarioModelo validarLogin(String nombreUsuario, String contraseniaIngresada) {
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();
        if (conexion == null) {
            return null;
        }

        try {
            CallableStatement llamada = conexion.prepareCall("{CALL sp_validar_login(?, ?, ?, ?)}");
            llamada.setString(1, nombreUsuario);
            llamada.registerOutParameter(2, java.sql.Types.INTEGER);
            llamada.registerOutParameter(3, java.sql.Types.VARCHAR);
            llamada.registerOutParameter(4, java.sql.Types.VARCHAR);

            llamada.execute();

            int id = llamada.getInt(2);
            String rol = llamada.getString(3);
            String hashAlmacenado = llamada.getString(4);

            if (hashAlmacenado == null) {
                return null; // Usuario no encontrado
            }

            // Verificar contraseña con BCrypt
            if (BCrypt.checkpw(contraseniaIngresada, hashAlmacenado)) {
                UsuarioModelo usuario = new UsuarioModelo();
                usuario.setIdUsuario(id);
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

    // Guarda este usuario en la base de datos (usado al registrar)
        public void guardar() throws SQLException, IllegalArgumentException {
        // Validación de cédula ANTES de cualquier operación con BD
        if (this.getCedula() == null || this.getCedula().trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula es obligatoria.");
        }

        if (!validarCedulaEcuatoriana(this.getCedula())) {
            throw new IllegalArgumentException("La cédula ecuatoriana no es válida.");
        }

        // Validaciones adicionales
        if (this.nombreUsuario == null || this.nombreUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio.");
        }
        if (this.contrasenia == null || this.contrasenia.isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }

        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();
        
        if (conexion == null) {
            throw new SQLException("No se pudo conectar a la base de datos.");
        }

        try {
            CallableStatement llamada = conexion.prepareCall(
                "{CALL sp_registrar_usuario(?, ?, ?, ?, ?, ?, ?)}"
            );
            
            llamada.setString(1, this.nombreUsuario.trim());
            llamada.setString(2, this.contrasenia);
            llamada.setString(3, this.rol);
            llamada.setString(4, this.getCedula().trim());
            llamada.setString(5, this.getDireccion() != null ? this.getDireccion().trim() : "");
            llamada.setInt(6, this.getEdad());
            llamada.setString(7, this.getGenero());

            llamada.executeUpdate();

            // Obtener ID generado
            try (ResultSet rs = llamada.getGeneratedKeys()) {
                if (rs.next()) {
                    this.idUsuario = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        }
    }

    // Obtiene todos los usuarios (para gestión de usuarios)
    public static List<UsuarioModelo> obtenerTodosLosUsuarios() {
    List<UsuarioModelo> listaUsuarios = new ArrayList<>();
    ConexionBDD conexionBDD = new ConexionBDD();
    Connection conexion = conexionBDD.conectar();
    if (conexion == null) {
        return listaUsuarios;
    }
    try {
        CallableStatement sentencia = conexion.prepareCall("{CALL sp_obtener_usuarios()}");
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            UsuarioModelo usuario = new UsuarioModelo();
            usuario.setIdUsuario(resultado.getInt("idUsuario"));
            usuario.setNombreUsuario(resultado.getString("nombreUsuario"));
            usuario.setCedula(resultado.getString("cedula"));
            usuario.setDireccion(resultado.getString("direccion"));
            usuario.setEdad(resultado.getInt("edad"));
            usuario.setGenero(resultado.getString("genero"));
            usuario.setRol(resultado.getString("rol"));
            usuario.setActivo("Activo".equals(resultado.getString("estado")));
            listaUsuarios.add(usuario);
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
    return listaUsuarios;
}
    
  // Método para actualizar todos los datos del usuario en la base de datos
   
    public void actualizar() throws SQLException {
      ConexionBDD conexionBDD = new ConexionBDD();
      Connection conexion = conexionBDD.conectar();

      if (conexion == null) {
          throw new SQLException("No se pudo conectar a la base de datos.");
      }

      CallableStatement sentencia = null;
      try {
          sentencia = conexion.prepareCall("{CALL sp_actualizar_usuario(?, ?, ?, ?, ?, ?, ?)}");
          sentencia.setInt(1, this.idUsuario);
          sentencia.setString(2, this.nombreUsuario);
          sentencia.setString(3, this.rol);
          sentencia.setString(4, this.getCedula());
          sentencia.setString(5, this.getDireccion());
          sentencia.setInt(6, this.getEdad());
          sentencia.setString(7, this.getGenero());

          sentencia.executeUpdate();

      } finally {
          // Cerrar la conexión de forma segura
          if (sentencia != null) {
              try {
                  sentencia.close();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
          }
          if (conexion != null && !conexion.isClosed()) {
              conexion.close();
          }
      }
  }

    // Cambia el estado de activo/inactivo de un usuario
    public void cambiarEstado(boolean nuevoEstado) {
        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();
        if (conexion == null) return;

        try {
            CallableStatement sentencia = conexion.prepareCall("{CALL sp_cambiar_estado_usuario(?, ?)}");
            sentencia.setInt(1, this.idUsuario);
            sentencia.setInt(2, nuevoEstado ? 1 : 0);
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
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
    
        
     // Valida una cédula ecuatoriana según el algoritmo oficial del Registro Civil
    
    public static boolean validarCedulaEcuatoriana(String cedula) {
        cedula = cedula.trim();

        // Longitud y solo números
        if (cedula.length() != 10 || !cedula.matches("\\d{10}")) {
            return false;
        }

        // Código de provincia (01-24)
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }

        // Tercer dígito (0-5 normales, 6 para sociedades)
        int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
        if (tercerDigito > 6) {
            return false;
        }

        // Algoritmo módulo 10
        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            int valor = digito * coeficientes[i];
            suma += (valor > 9) ? valor - 9 : valor;
        }

        int digitoVerificador = Integer.parseInt(cedula.substring(9));
        int resultado = (suma % 10 == 0) ? 0 : 10 - (suma % 10);

        return digitoVerificador == resultado;
    }
    
       
     // Busca un usuario por cédula usando Stored Procedure
  
    
   
    public static List<UsuarioModelo> buscarPorCedula(String cedula) {
        List<UsuarioModelo> lista = new ArrayList<>();

        ConexionBDD conexionBDD = new ConexionBDD();
        Connection conexion = conexionBDD.conectar();

        if (conexion == null) {
            return lista; // Lista vacía si no hay conexión
        }

        try {
            CallableStatement cstmt = conexion.prepareCall("{CALL sp_buscar_usuario_por_cedula(?)}");
            cstmt.setString(1, cedula);

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                UsuarioModelo usuario = new UsuarioModelo();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setCedula(rs.getString("cedula"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setGenero(rs.getString("genero"));
                usuario.setRol(rs.getString("rol"));
                usuario.setActivo(rs.getInt("activo") == 1);

                lista.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return lista;
    }
}

