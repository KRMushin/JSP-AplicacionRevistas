/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.ActualizadoresModelos.ActualizadorUsuario;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRegistro;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.TransaccionFallidaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.CarteraDigital;
import com.mycompany.proyecto_unoipc2.backend.Modelos.FotoUsuario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioCRUD;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones.RepositorioFotosUsuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioLecturaEscritura;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones.RepositorioPreferencia;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones.RepositorioUsuario;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import com.mycompany.proyecto_unoipc2.backend.Utileria.TipoPreferencia;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioUsuario {
        
    private RepositorioLecturaEscritura<FotoUsuario,Long> repositorioFotosUsuario;
    private RepositorioCRUD<PreferenciaUsuario,String> repositorioPreferenciasUsuario;
    private RepositorioUsuario repositorioUsuario;
    private ActualizadorUsuario actualizadorUsuario;
    private Connection conn;

    public ServicioUsuario() throws SQLException {
        this.conn = ConexionBaseDatos.getInstance();
        this.actualizadorUsuario = new ActualizadorUsuario(conn);
        this.repositorioUsuario = new RepositorioUsuario(conn);
        this.repositorioPreferenciasUsuario = new RepositorioPreferencia(conn);
        this.repositorioFotosUsuario = new RepositorioFotosUsuario(conn);
        
    }
    /*      METODOS QUE SE CARAGAN EL PERFIL DEL USUARIO */
    public Usuario obtenerUsuario(String nombreUsuario) throws TransaccionFallidaException {
       try {
              conn.setAutoCommit(false);
                   Usuario usuario = repositorioUsuario.obtenerPorId(nombreUsuario);
                    if (usuario == null) {
                        throw new TransaccionFallidaException("Usuario no encontrado: " + nombreUsuario);
                    }

                    /*  cuando el usuario */
                    if (usuario.getFoto() != null && usuario.getFoto().getIdFoto() != null) {
                        FotoUsuario foto = repositorioFotosUsuario.obtenerPorId(usuario.getFoto().getIdFoto());
                        usuario.setFoto(foto);
                    }

                    List<PreferenciaUsuario> preferencias = repositorioPreferenciasUsuario.listar(nombreUsuario);
                    if (!preferencias.isEmpty()) {
                        usuario.setPreferencias(preferencias);
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

    /*          METODOS PARA ACTUALIZAR AL USUARIO*/
    
    public Usuario actualizarUsuario(HttpServletRequest req) throws DatosInvalidosRegistro, SQLException, IOException, ServletException, TransaccionFallidaException{
            
            Usuario usuario = actualizadorUsuario.extraerDatosYvalidar(req);
            return actualizadorUsuario.transaccionActualizarUsuario(usuario);
        
    }
    public Usuario obtenerLlaveUsuario(String nombreUsuario) throws SQLException{
    return repositorioUsuario.obtenerLlaveUsuario(nombreUsuario);
    }
    public FotoUsuario procesarFotoUsuario(Long idFoto, OutputStream outPut) throws SQLException, IOException {

        FotoUsuario fotoUsuario = repositorioFotosUsuario.obtenerPorId(idFoto);
        InputStream inputFoto= fotoUsuario.getFoto();

        byte[] buffer = new byte[1024];
        int bytes;

        while ((bytes = inputFoto.read(buffer)) != -1) {
            outPut.write(buffer, 0, bytes);
        }
        return fotoUsuario;
    }

    public void mostrarFotoPerfil(HttpServletRequest req, HttpServletResponse response) throws SQLException, IOException {
                String idStrFoto = req.getParameter("idFoto");
                Long idFoto = Long.valueOf(idStrFoto);
                
                response.setContentType("image/jpeg");
                
                OutputStream outPut = response.getOutputStream();
                FotoUsuario foto = procesarFotoUsuario(idFoto, outPut);

    }




}
//    private Usuario extraerDatosYvalidar(HttpServletRequest req) throws IOException, ServletException, SQLException, TransaccionFallidaException, DatosInvalidosRegistro {
//        Usuario usuario = new Usuario();
//        usuario.setNombreUsuario(req.getParameter("nombreUsuario"));
//        usuario.setNombre(req.getParameter("nombre"));
//        usuario.setDescripcion(req.getParameter("descripcion"));
//        usuario.setPreferencias(obtenerPreferenciasNuevas(req));
//        
//        /*  significa que la foto que se va ingresar es para actualizar y no es nueva*/
//        InputStream fotoInputStream = obtenerFoto(req);
//
//       if (fotoInputStream != null) {
//        // Caso 1: Existe un idFoto y también una nueva foto proporcionada
//        if (req.getParameter("idFoto").trim().length() > 0) {
//            try {
//                Long id = Long.valueOf(req.getParameter("idFoto"));
//                usuario.setFoto(new FotoUsuario(id, req.getParameter("nombreUsuario"), fotoInputStream));
//            } catch (NumberFormatException e) {
//                throw new DatosInvalidosRegistro("El ID de la foto no es válido.");
//            }
//        } 
//        // Caso 2: No existe idFoto pero se proporciona una nueva foto
//        else {
//            FotoUsuario foto = new FotoUsuario();
//            foto.setFoto(fotoInputStream);
//            foto.setNombreUsuario(req.getParameter("nombreUsuario"));
//            usuario.setFoto(foto);
//        }
//        } else if (fotoInputStream == null && req.getParameter("idFoto").trim().length() > 0) {
//            usuario.setFoto(repositorioFotosUsuario.obtenerPorId(Long.valueOf(req.getParameter("idFoto"))));
//        }
//
//        
//        return usuario;
//
//    }
//   
//    private Usuario  transaccionActualizarUsuario(Usuario usuario) throws SQLException , TransaccionFallidaException{
//        
//        try {
//            conn.setAutoCommit(false);
//            
//             if (usuario.getFoto() != null && usuario.getFoto().getIdFoto() != null) {
//               usuario.setFoto(repositorioFotosUsuario.actualizar(usuario.getFoto()));
//            
//             }else if (usuario.getFoto() != null && usuario.getFoto().getIdFoto() == null) {
//                FotoUsuario fotoNueva = repositorioFotosUsuario.guardar(usuario.getFoto());
//                usuario.getFoto().setIdFoto(fotoNueva.getIdFoto());
//            }
//        
//            repositorioUsuario.actualizar(usuario);
//            if (usuario.getPreferencias() != null) {
//                List<PreferenciaUsuario> preferencias = usuario.getPreferencias();
//                repositorioPreferenciasUsuario.eliminar(usuario.getNombreUsuario());
//            
//            for (int i = 0; i < preferencias.size(); i++) {
//                repositorioPreferenciasUsuario.guardar(preferencias.get(i));
//            }
//        }
//            conn.commit();
//            return usuario;
//            
//        } catch (SQLException e) {
//                    try {
//                            conn.rollback();
//                    } catch (SQLException rollbackEx) {
//                            e.addSuppressed(rollbackEx); 
//                    }
//            throw new TransaccionFallidaException(" Fallo la transaccion de registro de usuario ");
//            }
//            finally {
//                    try {
//                        conn.setAutoCommit(true);
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                }
//            }
//    }
//
//    private List<PreferenciaUsuario> obtenerPreferenciasNuevas(HttpServletRequest req) {
//        
//        List<PreferenciaUsuario> prefRetorno = new ArrayList<>();
//        String nombreUsuario = req.getParameter("nombreUsuario");
//        String[] temasPref = req.getParameterValues("preferenciasTemas");
//        String[] temasHob = req.getParameterValues("preferenciasHobbies");
//        String[] temasGus = req.getParameterValues("preferenciasGustos");
//        
//        if (temasPref != null && temasPref.length > 0) {
//            for (int i = 0; i < temasPref.length; i++) {
//                String tema = temasPref[i];
//                prefRetorno.add(new PreferenciaUsuario(tema,nombreUsuario,TipoPreferencia.TEMA_PREFERENCIA));
//            }
//        }
//        
//        if (temasHob != null && temasHob.length > 0) {
//            for (int i = 0; i < temasHob.length; i++) {
//                String tema = temasHob[i];
//                prefRetorno.add(new PreferenciaUsuario(tema,nombreUsuario,TipoPreferencia.HOBBIE));
//            }
//        }
//        
//        if (temasGus != null && temasGus.length > 0) {
//            for (int i = 0; i < temasGus.length; i++) {
//                String tema = temasGus[i];
//                prefRetorno.add(new PreferenciaUsuario(tema,nombreUsuario,TipoPreferencia.GUSTO));
//            }
//        }
//        
//        return prefRetorno;
//        
//        
//    }
//
//    private InputStream obtenerFoto(HttpServletRequest req) throws IOException, ServletException, DatosInvalidosRegistro {
//            Part part = req.getPart("foto");
//
//        final long MAX_SIZE = 100 * 1024; // 500 KB en bytes
//
//        if (part != null && part.getSize() > 0) {
//        if (part.getSize() > MAX_SIZE) {
//            throw new DatosInvalidosRegistro("El archivo de la foto es demasiado grande. El tamaño máximo permitido es de 500 KB.");
//        }
//        return part.getInputStream();
//    } else {
//        return null;
//    }
//}