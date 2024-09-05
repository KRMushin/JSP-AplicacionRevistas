/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Servicios;

import com.mycompany.proyecto_unoipc2.Repositorios.RepositorioCarteraDigital;
import com.mycompany.proyecto_unoipc2.Repositorios.RepositorioPreferencia;
import com.mycompany.proyecto_unoipc2.Repositorios.RepositorioUsuario;
import com.mycompany.proyecto_unoipc2.Modelos.CarteraDigital;
import com.mycompany.proyecto_unoipc2.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.Repositorios.RepositorioCRUD;
import com.mycompany.proyecto_unoipc2.Repositorios.RepositorioLecturaEscritura;
import com.mycompany.proyecto_unoipc2.Utileria.ConexionBaseDatos;
import com.mycompany.proyecto_unoipc2.Utileria.Encriptador;
import com.mycompany.proyecto_unoipc2.Utileria.Rol;
import com.mycompany.proyecto_unoipc2.Utileria.Validaciones;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kevin-mushin
 */
public class ServicioRegistro {
    
    private Connection conn;
    private RepositorioLecturaEscritura<Usuario, String> usuarioRepositorio;
    private RepositorioLecturaEscritura<CarteraDigital, String> carteraRepositorio;
    private RepositorioCRUD<PreferenciaUsuario, String, String> preferenciasRepositorio;
    private Validaciones validaciones;
    private Encriptador encriptador;

    public ServicioRegistro() {
        
        this.conn = establecerConexion();
        this.usuarioRepositorio = new RepositorioUsuario(conn);
        this.carteraRepositorio = new RepositorioCarteraDigital(conn);
        this.preferenciasRepositorio = new RepositorioPreferencia(conn);
        this.validaciones = new Validaciones();
        this.encriptador = new Encriptador();
    }
    private Connection establecerConexion()  {
        try {
        Connection conn = ConexionBaseDatos.getInstance();
        if (conn != null && !conn.isClosed()) {
            System.out.println("Conexión a la base de datos exitosa.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    } catch (SQLException e) {
        System.err.println("Error al probar la conexión: " + e.getMessage());
        e.printStackTrace();
    }
        return conn;
    }
    
    public Map<String, String> validarYRegistrarUsuario(String nombreUsuario, String password, String nombrePila, Rol rol, 
                                                                                            byte[] foto, List<PreferenciaUsuario> preferencias){
        
         Map<String, String> errores = new HashMap<>();
         // validar que los datos sean acorrectos por seguridad
         if (!validaciones.esRegistroValido(nombreUsuario,nombrePila,rol.toString(),preferencias)) {
             errores.put("registro", "HA INGRESADO VALORES INVALIDOS PARA EL REGISTRO :) ");
             return errores;
          }
         // metodo para evaluar si el usuario ingresado ya existe
         Usuario usuario = obtenerUsuario(nombreUsuario);
         if (usuario == null) {
            errores.put("usuario existente", "Elija otro nombre de usuario porfavor este ya esta en uso");
            return errores;
        }
         
         //encriptar la contraseña
         String contraseñaEncriptada = encriptador.encryptPassword(password);
         
         Usuario nuevoUsuario = new Usuario(nombreUsuario,contraseñaEncriptada,rol,nombrePila);
         
         if (!preferencias.isEmpty()) {
            nuevoUsuario.setPreferencias(preferencias);
        }
         if (foto.length != 0) {
            nuevoUsuario.setFoto(foto);
        }
         String respuestaTransaccion = transaccionRegistrarUsuario(nuevoUsuario, preferencias);
         
         if (respuestaTransaccion != null) {
            errores.put(" Error", respuestaTransaccion);
        }
         
         
         return errores;
    }

    private String transaccionRegistrarUsuario(Usuario nuevoUsuario, List<PreferenciaUsuario> preferencias)  {
        String respuesta = null;

            try {
                    conn.setAutoCommit(false);
                    // guardar el usuario en la DB
                    Usuario usuarioRegistro = usuarioRepositorio.guardar(nuevoUsuario);
                    
                    if (nuevoUsuario.getRol() != Rol.EDITOR) {
                        carteraRepositorio.guardar(new CarteraDigital(usuarioRegistro.getNombreUsuario(),0.0));
                   }
                    if (!preferencias.isEmpty()) {
                         guardarPreferencias(preferencias,usuarioRegistro.getNombreUsuario());
                    }

                conn.commit();

            } catch (SQLException e) {
                    try {
                            conn.rollback();
                    } catch (SQLException rollbackEx) {
                            e.addSuppressed(rollbackEx); 
                    }
              respuesta= " Error en la transaccion de registro usuario ";
            }
            // ocurra o no ocurra error se reestablece el commit
            finally {
                    try {
                        conn.setAutoCommit(true);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                }
            }
        
        return respuesta;
    }
    
    private Usuario obtenerUsuario(String nombreUsuario) {
            try {
                return usuarioRepositorio.obtenerPorId(nombreUsuario);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
}


    private void guardarPreferencias(List<PreferenciaUsuario> preferencias, String nombreUsuario) throws SQLException {
            for (int i = 0; i < preferencias.size(); i++) {
            PreferenciaUsuario pref = preferencias.get(i);
             preferenciasRepositorio.guardar(new PreferenciaUsuario(pref.getPreferencia(),nombreUsuario,pref.getTipoPreferencia()));          
        }
    }   
}
