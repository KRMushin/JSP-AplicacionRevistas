/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Utileria;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public enum TipoOpciones {
    
    EDITOR {
        @Override
        public List<OpcionesUsuario> obtenerOpcionesRol() {
            List<OpcionesUsuario> opciones = new ArrayList<>();
            
            opciones.add(new OpcionesUsuario("Ver Perfil", "PerfilUsuarioServlet?action=perfilUsuario&edit=true"));

            opciones.add(new OpcionesUsuario("Publicar revista","CategoriaEtiquetaServlet?accion=obtenerCategorias"));
            opciones.add(new OpcionesUsuario("Mis Revistas","EditorRevistaServlet?accion=obtenerRevistas"));
            /*  REPORTES QUE PODRAN VER LOS EDITORES*/
            opciones.add(new OpcionesUsuario("Comentarios En Revistas","ReporteComentariosServlet"));
            return opciones;

        }
    
    
    },
    SUSCRIPTOR {
        @Override
        public List<OpcionesUsuario> obtenerOpcionesRol() {
            List<OpcionesUsuario> opciones = new ArrayList<>();
            opciones.add(new OpcionesUsuario("Ver Perfil", "PerfilUsuarioServlet?action=perfilUsuario&edit=true"));
            opciones.add(new OpcionesUsuario(" Navegar ","JSP/EditoresSuscriptores/NavegadorRevistasFiltros.jsp"));
            opciones.add(new OpcionesUsuario(" Suscripciones","SuscripcionesServlet"));

            return opciones;
        }
    
    
    },
    COMPRADOR{
        @Override
        public List<OpcionesUsuario> obtenerOpcionesRol() {
            List<OpcionesUsuario> opciones = new ArrayList<>();
            opciones.add(new OpcionesUsuario("Ver Perfil", "PerfilUsuarioServlet?action=perfilUsuario&edit=true"));
            opciones.add(new OpcionesUsuario(" Promocionar Anuncio","JSP/Comprador/ComprarAnuncio.jsp"));
            opciones.add(new OpcionesUsuario(" Cartera Digital","JSP/Comprador/CarteraDigital.jsp"));
            opciones.add(new OpcionesUsuario(" Desactivar Anuncios","ir a perfil.jsp"));
            return opciones;
        }
    
    },
    ADMINISTRADOR{
        @Override
        public List<OpcionesUsuario> obtenerOpcionesRol() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    
    };
    
    public abstract List<OpcionesUsuario> obtenerOpcionesRol();
}
