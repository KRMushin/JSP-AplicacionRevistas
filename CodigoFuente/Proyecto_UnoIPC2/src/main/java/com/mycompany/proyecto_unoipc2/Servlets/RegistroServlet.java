/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Servlets;

import com.mycompany.proyecto_unoipc2.Servicios.ServicioRegistro;
import com.mycompany.proyecto_unoipc2.Utileria.Rol;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 *
 * @author kevin-mushin
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})

public class RegistroServlet extends HttpServlet {
    
    ServicioRegistro servicioRegistro = new ServicioRegistro();
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req
     * @param resp
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //establecer texto html
        resp.setContentType("text/html");

        String nombreUsuario = req.getParameter("nombreUsuario");
        String password = req.getParameter("password");
        String nombrePila = req.getParameter("nombrePila");
        String r = req.getParameter("rolEscogido");
        Rol rolUsuario = Rol.valueOf(r);
        
        
        Map<String , String > errores = servicioRegistro.validarYRegistrarUsuario(nombreUsuario, password, nombrePila, rolUsuario, null, null);
        
        if (!errores.isEmpty()) {
    // Iterar sobre las entradas del mapa de errores
    for (Map.Entry<String, String> entry : errores.entrySet()) {
        String clave = entry.getKey(); // Clave del error
        String mensajeError = entry.getValue(); // Mensaje de error
        System.out.println("Error [" + clave + "]: " + mensajeError); // Imprimir el error
    }
}

    }
    

}
/*

    // Establecer el tipo de contenido de la respuesta como HTML
    resp.setContentType("text/html");
    
    // Obtener un objeto PrintWriter para escribir la respuesta
    PrintWriter out = resp.getWriter();
    
    // Obtener los parámetros del formulario
    String nombreUsuario = req.getParameter("nombreUsuario");
    String password = req.getParameter("password");
    String nombrePila = req.getParameter("nombrePila");
    String r = req.getParameter("rolEscogido");
    
    // Convertir el parámetro 'rolEscogido' al tipo Rol (si es válido)
    Rol rolUsuario = null;
    try {
        rolUsuario = Rol.valueOf(r);
    } catch (IllegalArgumentException e) {
        // Manejar error si el valor del rol es inválido
        out.println("<h3>Rol escogido no es válido: " + r + "</h3>");
        return;
    }
    
    // Imprimir los valores en la respuesta HTML
    out.println("<html><body>");
    out.println("<h2>Valores recibidos:</h2>");
    out.println("<p>Nombre de Usuario: " + nombreUsuario + "</p>");
    out.println("<p>Password: " + password + "</p>");
    out.println("<p>Nombre de Pila: " + nombrePila + "</p>");
    out.println("<p>Rol Escogido: " + rolUsuario + "</p>");
    out.println("</body></html>");
    
    // Cerrar el PrintWriter
    out.close();
        
        
        
        
        
*/