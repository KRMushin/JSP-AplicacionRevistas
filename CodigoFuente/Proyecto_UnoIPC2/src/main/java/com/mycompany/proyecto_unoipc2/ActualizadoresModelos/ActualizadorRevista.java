/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.ActualizadoresModelos;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRevista;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author kevin-mushin
 */
public class ActualizadorRevista {

    public Revista validarYExtraerEstadosNuevos(HttpServletRequest req) throws DatosInvalidosRevista {
            Revista revista = new Revista();
            
            revista.setIdRevista(obtenerIdRevista(req));
            revista.setRevistaComentable(validarEstadoComentarios(req));
            revista.setRevistaLikeable(validarEstadoLikes(req));
            revista.setAceptaSuscripciones(validarEstadoSuscripciones(req));
        return revista;
    }

    private boolean validarEstadoComentarios(HttpServletRequest req) {
            String revistaComentableCadena = req.getParameter("revistaComentable");
           return(revistaComentableCadena != null && revistaComentableCadena.equals("true"));
    }

    private boolean validarEstadoLikes(HttpServletRequest req) {
            String revistaLikeableCadena = req.getParameter("revistaLikeable");
            return (revistaLikeableCadena != null && revistaLikeableCadena.equals("true"));
    }
    private boolean validarEstadoSuscripciones(HttpServletRequest req) {
            String revistaSuscripcionesCadena = req.getParameter("revistaAceptaSuscripciones");
            return (revistaSuscripcionesCadena != null && revistaSuscripcionesCadena.equals("true"));
    }

    private Long obtenerIdRevista(HttpServletRequest req) throws DatosInvalidosRevista{
        
        try {
               Long id = Long.valueOf(req.getParameter("idRevista"));
               return id;
        } catch (NumberFormatException e) {
            throw new DatosInvalidosRevista("error en la obtencion del id de la revista a actualziar");
        }
    }
    


    

    
}