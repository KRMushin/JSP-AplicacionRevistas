/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioArchivosPDF;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
@WebServlet(urlPatterns = {"/ArchivosPdfServlet"})
public class ArchivosPdfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
              String accion = request.getParameter("idRevista");
              String nombreRevista = request.getParameter("nombreRevista");
              
              Long id = Long.valueOf(accion);
              
            /*      cabeceras http para el control del cache */
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("application/pdf");

              String nombreArchivo = nombreRevista + ".pdf";
              response.setHeader("Content-Disposition", "inline; filename=\"" + nombreArchivo + "\"");

              ServicioArchivosPDF service = new ServicioArchivosPDF();
              service.mostrarPdf(response, id);
              
        } catch (IOException | NumberFormatException | SQLException e) {
            
            request.setAttribute("mensaje","No se ejecuto la lectura del pdf" + e.getMessage());
            request.getRequestDispatcher("JSP/VisualizarRevistas.jsp").forward(request, response);
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
