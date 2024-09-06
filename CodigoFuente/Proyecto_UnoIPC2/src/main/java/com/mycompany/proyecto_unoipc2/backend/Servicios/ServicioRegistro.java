/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.Modelos.CarteraDigital;
import com.mycompany.proyecto_unoipc2.backend.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioCRUD;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioLecturaEscritura;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import com.mycompany.proyecto_unoipc2.backend.Utileria.Rol;
import com.mycompany.proyecto_unoipc2.backend.CreadoresModelo.CreadorUsuario;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRegistro;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.TransaccionFallidaException;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioCarteraDigital;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioPreferencia;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioUsuario;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioRegistro {
    private Connection conn;
    private RepositorioLecturaEscritura<Usuario, String> usuarioRepositorio;
    private RepositorioLecturaEscritura<CarteraDigital, String> carteraRepositorio;
    private RepositorioCRUD<PreferenciaUsuario, String, String> preferenciasRepositorio;
    private CreadorUsuario creadorUsuario;

    public ServicioRegistro() throws SQLException {
        this.conn = ConexionBaseDatos.getInstance();
        this.usuarioRepositorio = new RepositorioUsuario(conn);
        this.carteraRepositorio = new RepositorioCarteraDigital(conn);
        this.preferenciasRepositorio = new RepositorioPreferencia(conn);
        this.creadorUsuario = new CreadorUsuario();
    }
    
    public Usuario RegistrarUsuario(HttpServletRequest req) throws DatosInvalidosRegistro, TransaccionFallidaException {
    
         Usuario nuevoUsuario = creadorUsuario.crearUsuario(req);
         
         if (obtenerUsuario(nuevoUsuario.getNombreUsuario()) != null) {
             throw new DatosInvalidosRegistro(" Porfavor Ingrese otro nombre de usuario este ya esta en nuestro sistema");
        }
         transaccionRegistrarUsuario(nuevoUsuario);
         
    
        return nuevoUsuario;
    }
    private void transaccionRegistrarUsuario(Usuario nuevoUsuario) throws TransaccionFallidaException {
            try {
                    conn.setAutoCommit(false);
                    Usuario usuarioRegistro = usuarioRepositorio.guardar(nuevoUsuario);

                    if (usuarioRegistro == null) {
                                throw new TransaccionFallidaException("El nuevo usuario no pudo ser creado.");

                }
                    if (nuevoUsuario.getRol() != Rol.EDITOR) {
                        carteraRepositorio.guardar(new CarteraDigital(usuarioRegistro.getNombreUsuario(),0.0));
                   }

                    if (nuevoUsuario.getPreferencias() != null && !nuevoUsuario.getPreferencias().isEmpty()) {
                         guardarPreferencias(nuevoUsuario.getPreferencias(),usuarioRegistro.getNombreUsuario());
                    }

                    conn.commit();

            } catch (SQLException e) {
                    try {
                            conn.rollback();
                    } catch (SQLException rollbackEx) {
                            e.addSuppressed(rollbackEx); 
                    }
            throw new TransaccionFallidaException(" Fallo la transaccion de registro de usuario ");
            }
            finally {
                    try {
                        conn.setAutoCommit(true);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                }
            }
        
    }
    
    private void guardarPreferencias(List<PreferenciaUsuario> preferencias, String nombreUsuario) throws SQLException {
            for (int i = 0; i < preferencias.size(); i++) {
                    PreferenciaUsuario pref = preferencias.get(i);
                    preferenciasRepositorio.guardar(new PreferenciaUsuario(pref.getPreferencia(),nombreUsuario,pref.getTipoPreferencia()));          
        }
    }   
    private Usuario obtenerUsuario(String nombreUsuario) {
            try {
                return usuarioRepositorio.obtenerPorId(nombreUsuario);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
}

}
