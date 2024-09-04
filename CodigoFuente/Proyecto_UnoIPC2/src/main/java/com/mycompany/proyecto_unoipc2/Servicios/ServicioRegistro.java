/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Servicios;

import com.mycompany.proyecto_unoipc2.ImplementacionesRepositorios.RepositorioCarteraDigital;
import com.mycompany.proyecto_unoipc2.ImplementacionesRepositorios.RepositorioPreferencia;
import com.mycompany.proyecto_unoipc2.ImplementacionesRepositorios.RepositorioUsuario;
import com.mycompany.proyecto_unoipc2.Modelos.CarteraDigital;
import com.mycompany.proyecto_unoipc2.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.Utileria.ConexionBaseDatos;
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
    private RepositorioUsuario usuarioRepositorio;
    private RepositorioCarteraDigital carteraRepositorio;
    private RepositorioPreferencia preferenciasRepositorio;
    private Validaciones validaciones;

    public ServicioRegistro() throws SQLException {
        
        this.conn = ConexionBaseDatos.getInstance();
        this.usuarioRepositorio = new RepositorioUsuario(conn);
        this.carteraRepositorio = new RepositorioCarteraDigital(conn);
        this.preferenciasRepositorio = new RepositorioPreferencia(conn);
        this.validaciones = new Validaciones();
    }
    
    public Map<String, String> validarYRegistrarUsuario(String nombreUsuario, String password, String nombrePila, Rol rol, 
                                                                                            byte[] foto, List<PreferenciaUsuario> preferencias){
        
         Map<String, String> errores = new HashMap<>();
         // validar que los datos sean acorrectos por seguridad
         if (!validaciones.esRegistroValido(nombreUsuario,password,nombrePila,rol.toString(),preferencias)) {
             errores.put("registro", "HA INGRESADO VALORES INVALIDOS PARA EL REGISTRO :) ");
             return errores;
          }
         
         Usuario usuario = obtenerUsuario(nombreUsuario);
         if (usuario != null) {
            errores.put("usuario existente", "Elija otro nombre de usuario porfavor este ya esta en uso");
            return errores;
        }
         
         Usuario nuevoUsuario = new Usuario(rol,nombreUsuario,password,nombrePila);
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
                    Usuario usuarioRegistro = usuarioRepositorio.guardar(nuevoUsuario);
                    if (nuevoUsuario.getRol() != Rol.EDITOR) {
                        carteraRepositorio.guardar(new CarteraDigital(0.0,usuarioRegistro.getId()));
                   }
                    if (!preferencias.isEmpty()) {

                         guardarPreferencias(preferencias,usuarioRegistro.getId());
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
            // al ocurrir un error se reestablece el autocomit 
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
                return usuarioRepositorio.obtenerPorNombreUsuario(nombreUsuario);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
}


    private void guardarPreferencias(List<PreferenciaUsuario> preferencias, Long id) throws SQLException {
            for (int i = 0; i < preferencias.size(); i++) {
            PreferenciaUsuario pref = preferencias.get(i);
             preferenciasRepositorio.guardar(new PreferenciaUsuario(pref.getPreferencia(),id,pref.getTipoPreferencia()));          
        }
    }   
}
