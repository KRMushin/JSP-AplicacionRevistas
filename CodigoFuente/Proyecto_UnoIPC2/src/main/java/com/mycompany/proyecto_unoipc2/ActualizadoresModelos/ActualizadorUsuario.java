/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.ActualizadoresModelos;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRegistro;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.TransaccionFallidaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.FotoUsuario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones.RepositorioFotosUsuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones.RepositorioPreferencia;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones.RepositorioUsuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioCRUD;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioLecturaEscritura;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioFotoUsuario;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import com.mycompany.proyecto_unoipc2.backend.Utileria.TipoPreferencia;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ActualizadorUsuario {
        private RepositorioLecturaEscritura<FotoUsuario,Long> repositorioFotosUsuario;
        private RepositorioCRUD<PreferenciaUsuario,String> repositorioPreferenciasUsuario;
        private RepositorioLecturaEscritura<Usuario, String> repositorioUsuario;
        private ServicioFotoUsuario servicioFotoUsuario;
        private Connection conn;

        public ActualizadorUsuario(Connection conn) throws SQLException {
            this.conn = conn;
        this.repositorioUsuario = new RepositorioUsuario(conn);
        this.repositorioPreferenciasUsuario = new RepositorioPreferencia(conn);
        this.repositorioFotosUsuario = new RepositorioFotosUsuario(conn);
        this.servicioFotoUsuario = new ServicioFotoUsuario(repositorioFotosUsuario);
    }
    
     public Usuario extraerDatosYvalidar(HttpServletRequest req) throws IOException, ServletException, SQLException, TransaccionFallidaException, DatosInvalidosRegistro {
            
                    Usuario usuario = new Usuario();
                    usuario.setNombreUsuario(req.getParameter("nombreUsuario"));
                    usuario.setNombre(req.getParameter("nombre"));
                    usuario.setDescripcion(req.getParameter("descripcion"));
                    usuario.setPreferencias(obtenerPreferenciasNuevas(req));

                    InputStream fotoInputStream = servicioFotoUsuario.obtenerFoto(req);

                    if (fotoInputStream != null) {
                        usuario.setFoto(servicioFotoUsuario.manejarFoto(req, fotoInputStream));
                    } else if (req.getParameter("idFoto").trim().length() > 0) {
                        usuario.setFoto(servicioFotoUsuario.obtenerFotoExistente(Long.valueOf(req.getParameter("idFoto"))));
                    }

                    return usuario;
        }
     public Usuario  transaccionActualizarUsuario(Usuario usuario) throws SQLException , TransaccionFallidaException{
        
                  try {
                            conn.setAutoCommit(false);
                                 if (usuario.getFoto() != null && usuario.getFoto().getIdFoto() != null) {
                                    usuario.setFoto(servicioFotoUsuario.actualizarFoto(usuario.getFoto()));

                                 }else if (usuario.getFoto() != null && usuario.getFoto().getIdFoto() == null) {
                                    FotoUsuario fotoNueva = repositorioFotosUsuario.guardar(usuario.getFoto());
                                    usuario.getFoto().setIdFoto(fotoNueva.getIdFoto());
                                }

                                repositorioUsuario.actualizar(usuario);
                                if (usuario.getPreferencias() != null) {
                                        List<PreferenciaUsuario> preferencias = usuario.getPreferencias();
                                        repositorioPreferenciasUsuario.eliminar(usuario.getNombreUsuario());

                                        for (int i = 0; i < preferencias.size(); i++) {
                                            repositorioPreferenciasUsuario.guardar(preferencias.get(i));
                                        }
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
                            }
                    }
        }
     private List<PreferenciaUsuario> obtenerPreferenciasNuevas(HttpServletRequest req) {
        
                List<PreferenciaUsuario> prefRetorno = new ArrayList<>();
                String nombreUsuario = req.getParameter("nombreUsuario");
                String[] temasPref = req.getParameterValues("preferenciasTemas");
                String[] temasHob = req.getParameterValues("preferenciasHobbies");
                String[] temasGus = req.getParameterValues("preferenciasGustos");

                if (temasPref != null && temasPref.length > 0) {
                    for (String tema : temasPref) {
                        prefRetorno.add(new PreferenciaUsuario(tema,nombreUsuario,TipoPreferencia.TEMA_PREFERENCIA));
                    }
                }

                if (temasHob != null && temasHob.length > 0) {
                    for (String tema : temasHob) {
                        prefRetorno.add(new PreferenciaUsuario(tema,nombreUsuario,TipoPreferencia.HOBBIE));
                    }
                }

                if (temasGus != null && temasGus.length > 0) {
                    for (String tema : temasGus) {
                        prefRetorno.add(new PreferenciaUsuario(tema,nombreUsuario,TipoPreferencia.GUSTO));
                    }
                }
                return prefRetorno;
            }
}
