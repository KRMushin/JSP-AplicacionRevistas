/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.TransaccionFallidaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.CarteraDigital;
import com.mycompany.proyecto_unoipc2.backend.Modelos.FotoUsuario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioCRUD;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioFotosUsuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioLecturaEscritura;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioPreferencia;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioUsuario;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioUsuario {
        
    private RepositorioLecturaEscritura<Usuario, String> repositorioUsuario;
    private RepositorioLecturaEscritura<FotoUsuario,Long> repositorioFotosUsuario;
    private RepositorioCRUD<PreferenciaUsuario,String, String> repositorioPreferenciasUsuario;
    private Connection conn;

    public ServicioUsuario() throws SQLException {
        this.conn = ConexionBaseDatos.getInstance();
        this.repositorioUsuario = new RepositorioUsuario(conn);
        this.repositorioPreferenciasUsuario = new RepositorioPreferencia(conn);
        this.repositorioFotosUsuario = new RepositorioFotosUsuario(conn);
        
    }

    public Usuario obtenerUsuario(String nombreUsuario) throws TransaccionFallidaException {
       try {
              conn.setAutoCommit(false);
                   Usuario usuario = repositorioUsuario.obtenerPorId(nombreUsuario);
                    if (usuario == null) {
                        throw new TransaccionFallidaException("Usuario no encontrado: " + nombreUsuario);
                    }

                    if (usuario.getFoto() != null && usuario.getFoto().getIdFoto() != null) {
                        FotoUsuario foto = repositorioFotosUsuario.obtenerPorId(usuario.getFoto().getIdFoto());
                        usuario.setFoto(foto);
                    }

                    List<PreferenciaUsuario> preferencias = repositorioPreferenciasUsuario.listar(nombreUsuario);
                    if (!preferencias.isEmpty()) {
                        usuario.setPreferencias(preferencias);
                        System.out.println("    NUMERO DE PREFERENCIAS " + usuario.getPreferencias().size() + " |" + preferencias.size());
                    }              
              
              conn.commit();
              return usuario;

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

    public void procesarFotoUsuario(Long idFoto, OutputStream outPut) throws SQLException, IOException {
        
        FotoUsuario fotoUsuario = repositorioFotosUsuario.obtenerPorId(idFoto);
        InputStream inputFoto= fotoUsuario.getFoto();
        
        byte[] buffer = new byte[1024];
        int bytes;
        
        while ((bytes = inputFoto.read(buffer)) != -1) {
            outPut.write(buffer, 0, bytes);
        }
    }
}
