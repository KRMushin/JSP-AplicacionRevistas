/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.FiltrosInvalidosBusqueda;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioFiltrosRevista;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RevistaRelacionRepositorio;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioNavegacionRevistas {
    
    private Connection conn;
    private RepositorioFiltrosRevista repositorioFiltrado;

    public ServicioNavegacionRevistas() throws SQLException {
        this.conn = ConexionBaseDatos.getInstance();
        this.repositorioFiltrado = new RepositorioFiltrosRevista(conn);
    }

    public List<Revista> obtenerRevistasPorFiltros(HttpServletRequest request) throws FiltrosInvalidosBusqueda, SQLException {

        List<Revista> revistasEncontradas = new ArrayList<>();
        String[] idEtiquetas = request.getParameterValues("etiquetasEscogidas");
        String idCategoria = request.getParameter("idCategoriaEscogida");
        String nombreUsuario = request.getParameter("nombreUsuario");
        
        if ((idEtiquetas == null || idEtiquetas.length <= 0) && (idCategoria == null || idCategoria.isEmpty())) {
             revistasEncontradas = obtenerTodasLasRevistas(nombreUsuario);

        } else if ((idEtiquetas != null && idEtiquetas.length > 0) && (idCategoria == null || idCategoria.isEmpty())) {
            revistasEncontradas = obtenerRevistasPorEtiquetas(idEtiquetas,nombreUsuario);

        } else if ((idEtiquetas == null || idEtiquetas.length == 0) && (idCategoria != null && !idCategoria.isEmpty())) {
            revistasEncontradas = obtenerRevistasPorCategoria(idCategoria,nombreUsuario);

        } else if ((idEtiquetas != null && idEtiquetas.length > 0) && (idCategoria != null && !idCategoria.isEmpty())) {
         revistasEncontradas = obtenerPorEtiquetaYCategoria(idCategoria,idEtiquetas,nombreUsuario);

        }
        return revistasEncontradas;

    }

    private List<Revista> obtenerTodasLasRevistas(String nombreUsuario) throws SQLException {
        return repositorioFiltrado.obtenerRevistasActivas(nombreUsuario);

    }

    private List<Revista> obtenerRevistasPorEtiquetas(String[] idEtiquetas,String nombreUsuario) throws FiltrosInvalidosBusqueda, SQLException {
        List<Long> identificadores = obtenerIdentificadorees(idEtiquetas);
        return repositorioFiltrado.obtenerRevistasPorEtiquetas(identificadores,nombreUsuario);

    }

    private List<Revista> obtenerRevistasPorCategoria(String idCategoria, String nombreUsuario) throws SQLException {
        return repositorioFiltrado.obtenerRevistasPorCategoria(Long.valueOf(idCategoria),nombreUsuario);

    }

    private List<Revista> obtenerPorEtiquetaYCategoria(String idCategoria, String[] idEtiquetas, String nombreUsuario) throws FiltrosInvalidosBusqueda, SQLException {
        return repositorioFiltrado.obtenerRevistasPorCategoriaEtiquetas(obtenerLong(idCategoria), idEtiquetas, nombreUsuario);
    }
    

    private List<Long> obtenerIdentificadorees(String[] idEtiquetas) throws FiltrosInvalidosBusqueda {
        List<Long> ident = new ArrayList<>();
        for (int i = 0; i < idEtiquetas.length; i++) {
            String idEtiqueta = idEtiquetas[i];
            if (!idEtiqueta.isEmpty()) {
                ident.add(obtenerLong(idEtiqueta));
            }
        }
        return ident;

    }
    
    private Long obtenerLong(String id) throws FiltrosInvalidosBusqueda{
        try {
              return Long.valueOf(id);
        } catch (NumberFormatException e) {
            throw new FiltrosInvalidosBusqueda("un id tiene formato invalido");
        }
        
    }

}
