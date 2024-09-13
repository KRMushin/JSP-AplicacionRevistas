/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.CreadoresModelo.CreadorRevista;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRevista;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones.RepositorioRevistas;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioArchivosPDF;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioCRUD;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RevistaRelacionRepositorio;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ServicioEditores {
    
    
    private RepositorioCRUD<Revista, String> repositorioRevistas;
    private RepositorioArchivosPDF repositorioPDF;
    private RevistaRelacionRepositorio relacionesRevista;
    private CreadorRevista creadorRevista;
    private Connection conn;

    public ServicioEditores() throws SQLException {
        
        this.conn = ConexionBaseDatos.getInstance();
        this.repositorioPDF = new RepositorioArchivosPDF(conn);
        this.repositorioRevistas = new RepositorioRevistas(conn);
        this.relacionesRevista = new RevistaRelacionRepositorio(conn);
        this.creadorRevista = new CreadorRevista();
    }
    public Revista guardarRevista(HttpServletRequest req, String operacion) throws DatosInvalidosRevista, IOException, ServletException, SQLException{

        Revista revistaOperacion = creadorRevista.crearRevista(req, operacion);
        InputStream inputStream = creadorRevista.obtenerPDFStream(req);
        
        if (operacion.equalsIgnoreCase("publicacion")) {
            
            String[] etiquetas = req.getParameterValues("idEtiqueta");
            Long idCategoria = Long.valueOf(req.getParameter("idCategoria"));
            revistaOperacion.setIdCategoria(idCategoria);
            
            if (etiquetas == null || etiquetas.length <= 0 ||  idCategoria <= 0) {
            throw new DatosInvalidosRevista("La publicación debe tener una categoría válida y al menos una etiqueta.");
            }

            return publicarRevista(revistaOperacion, inputStream, idCategoria, etiquetas);
        }
        return actualizarRevista(revistaOperacion);
    }

    private Revista publicarRevista(Revista revistaOperacion, InputStream archivoPdf, Long idcategoria,String[] etiquetas) throws DatosInvalidosRevista, SQLException{
        try {

              conn.setAutoCommit(false);
               
              if (!revistaOperacion.esValida() || archivoPdf == null ) {
                  throw new DatosInvalidosRevista(" Los datos de la revista o el pdf son invalidos");
                  
            }
              
              Long idPdf = repositorioPDF.guardarArchivoPDF(archivoPdf);
              revistaOperacion.setIdArchivoRevisa(idPdf);
              
              Revista revista =repositorioRevistas.guardar(revistaOperacion);
              relacionesRevista.agregarCategoriaRevista(revista.getIdRevista(), idcategoria);
              relacionesRevista.insertarCuadroLikes(revista);
              
              for (String etiquetaCadena : etiquetas) {
                  Long idEtiqueta;
                  try {
                      idEtiqueta = Long.valueOf(etiquetaCadena);
                  } catch (NumberFormatException e) {
                      throw new DatosInvalidosRevista("ID de etiqueta inválido: " + etiquetaCadena);
                  }
                  relacionesRevista.agregarEtiquetaRevista(revista.getIdRevista(), idEtiqueta);
             }
              conn.commit();
              return revista;
              
        }  catch (DatosInvalidosRevista | SQLException e) {
                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                      throw new DatosInvalidosRevista("Error en bloque try: "  +e );
        } finally {
                if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close(); 
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Revista actualizarRevista(Revista revistaOperacion) throws DatosInvalidosRevista, SQLException{
        if (!revistaOperacion.esValida() || revistaOperacion.getIdRevista() <= 0) {
               throw new DatosInvalidosRevista("ID de etiqueta inválido ");
        }
        Revista rev = repositorioRevistas.actualizar(revistaOperacion);
        if (rev == null) {
               throw new SQLException(" No se actualizo la revista correctamente");
        }
        return rev;
    }
}
