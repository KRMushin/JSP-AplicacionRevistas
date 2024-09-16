/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.CreadoresModelo;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRevista;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author kevin-mushin
 */
public class CreadorRevista {
    
//    public static final Double TASA_MANTENIMIENTOKB = ;
    
    
    public Revista crearRevista(HttpServletRequest req) throws DatosInvalidosRevista, IOException, ServletException{

        Revista revista = extraerYValidarRevista(req);

         Part pdf = req.getPart("revistaPDF");
         Long tamaño = pdf.getSize();

        revista.setCostoMantenimiento(sugerirCostoMantenimiento(tamaño));
        revista.setFechaCreacion(validarFechaCreacion(req));
        revista.setEstadoRevista("ACTIVA");
        return revista;
    
    }
    private Revista extraerYValidarRevista(HttpServletRequest req) throws DatosInvalidosRevista{
        Revista revista = new Revista();
        try {
            revista.setDescripcion(req.getParameter("descripcion"));
            revista.setEstadoRevista(req.getParameter("estadoRevista"));
            revista.setNombreAutor(req.getParameter("nombreAutor"));
            revista.setTituloRevista(req.getParameter("tituloRevista"));
            
            String revistaComentableCadena = req.getParameter("revistaComentable");
            boolean revistaComentable = (revistaComentableCadena != null && revistaComentableCadena.equals("true"));
            revista.setRevistaComentable(revistaComentable);
            
            String revistaLikeableCadena = req.getParameter("revistaLikeable");
            boolean revistaLikeable = (revistaLikeableCadena != null && revistaLikeableCadena.equals("true"));
            revista.setRevistaLikeable(revistaLikeable);
            
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new DatosInvalidosRevista("Los datos proporcionados son invalidos, porfavor revise los datos nuevamente");
        }
        return revista;


    }

    // 5 unidades por cada 
    private Double sugerirCostoMantenimiento(Long tamaño) {
                return tamaño /(1024.0 * 1024.0)*10;
    }


    /* ESTE METODO ES USADO PARA CUANDO EL ADMINISTRADOR DECIDE CAMBIAR EL PRECIO DE LOS COSTOS*/
    public void validarCostos(Revista revista, HttpServletRequest req) throws DatosInvalidosRevista {
        try {
              revista.setCostoMantenimiento(Double.valueOf(req.getParameter("nuevoCostoMantenimiento")));
        } catch (NumberFormatException e) {
            throw new DatosInvalidosRevista(" Los valores para los nuevos costos son invalidos porfavor de revisar");
        }

    }

    private LocalDate validarFechaCreacion(HttpServletRequest req) throws DatosInvalidosRevista {
            
        try {
              String fecha = req.getParameter("fechaPublicacion");
              if (fecha == null || fecha.trim().isEmpty()) {
              throw new DatosInvalidosRevista("La fecha de publicación no puede estar vacía.");
              }
              DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
              LocalDate fechaCreacion = LocalDate.parse(fecha, formatear);
              return fechaCreacion;
        } catch (DateTimeParseException e) {
            throw new DatosInvalidosRevista(" La fecha proporcionada es invalida");
        }
        
    }
    
    public InputStream obtenerPDFStream(HttpServletRequest req) throws IOException, ServletException{
        Part archivoPDF = req.getPart("revistaPDF");
        InputStream inputStream = archivoPDF.getInputStream();
        return inputStream;
    }
}
