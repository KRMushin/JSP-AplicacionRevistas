/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.LoginInvalido;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.TransaccionFallidaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Utileria.AutenticadorPassword;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ServicioAutenticadorUsuario {
    
    private AutenticadorPassword autenticadorPassword;
    private ServicioUsuario servicioUsuario;
    
    public ServicioAutenticadorUsuario() throws SQLException  {
        this.autenticadorPassword = new AutenticadorPassword();
        this.servicioUsuario = new ServicioUsuario();
    }

    public Usuario autenticarUsuario(HttpServletRequest request) throws LoginInvalido, SQLException, TransaccionFallidaException {
        
        Usuario usuario = servicioUsuario.obtenerUsuario(request.getParameter("nombreUsuario"));

        if (usuario == null) {
            throw new LoginInvalido(" Nombre de usuario o contraseña incorrecta");
        }
        if (!autenticadorPassword.contraseñaCorrecta( request.getParameter("password"),usuario.getPassword())) {
            throw new LoginInvalido(" Nombre de usuario o contraseña incorrecta ");
        }
        if (usuario.getRol() == null) {
            throw new LoginInvalido(" Revise sus datos porfavor ");
        }
                
        return servicioUsuario.obtenerLlaveUsuario(usuario.getNombreUsuario());
    }
    
}
