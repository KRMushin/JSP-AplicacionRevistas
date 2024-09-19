/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRegistro;
import com.mycompany.proyecto_unoipc2.backend.Modelos.FotoUsuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioLecturaEscritura;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ServicioFotoUsuario {
    private RepositorioLecturaEscritura<FotoUsuario, Long> repositorioFotosUsuario;
    private static final long MAX_SIZE = 100 * 1024; // 500 KB en bytes

    public ServicioFotoUsuario(RepositorioLecturaEscritura<FotoUsuario, Long> repositorioFotosUsuario) {
        this.repositorioFotosUsuario = repositorioFotosUsuario;
    }

    public InputStream obtenerFoto(HttpServletRequest req) throws IOException, ServletException, DatosInvalidosRegistro {
        Part part = req.getPart("foto");

        if (part != null && part.getSize() > 0) {
            if (part.getSize() > MAX_SIZE) {
                throw new DatosInvalidosRegistro("El archivo de la foto es demasiado grande. El tamaño máximo permitido es de 500 KB.");
            }
            return part.getInputStream();
        } else {
            return null;
        }
    }
    

    public FotoUsuario manejarFoto(HttpServletRequest req, InputStream fotoInputStream) throws SQLException, DatosInvalidosRegistro {
        if (req.getParameter("idFoto").trim().length() > 0) {
            try {
                Long id = Long.valueOf(req.getParameter("idFoto"));
                return new FotoUsuario(id, req.getParameter("nombreUsuario"), fotoInputStream);
            } catch (NumberFormatException e) {
                throw new DatosInvalidosRegistro("El ID de la foto no es válido.");
            }
        } else {
            FotoUsuario foto = new FotoUsuario();
            foto.setFoto(fotoInputStream);
            foto.setNombreUsuario(req.getParameter("nombreUsuario"));
            return foto;
        }
    }

    public FotoUsuario obtenerFotoExistente(Long idFoto) throws SQLException {
        return repositorioFotosUsuario.obtenerPorId(idFoto);
    }

    public FotoUsuario actualizarFoto(FotoUsuario foto) throws SQLException {
        return repositorioFotosUsuario.actualizar(foto);
    }

    public FotoUsuario guardarFoto(FotoUsuario foto) throws SQLException {
        return repositorioFotosUsuario.guardar(foto);
    }
}


