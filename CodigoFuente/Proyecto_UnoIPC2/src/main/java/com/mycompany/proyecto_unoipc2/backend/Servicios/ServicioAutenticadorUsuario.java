/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.LoginInvalido;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioLecturaEscritura;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioUsuario;
import com.mycompany.proyecto_unoipc2.backend.Utileria.AutenticadorPassword;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ServicioAutenticadorUsuario {
    
    private RepositorioLecturaEscritura<Usuario, String> repositorioUsuario;
    private Connection conn;
    private AutenticadorPassword autenticadorPassword;
    
    public ServicioAutenticadorUsuario() throws SQLException {
        this.conn = ConexionBaseDatos.getInstance();
        this.repositorioUsuario = new RepositorioUsuario(conn);
        this.autenticadorPassword = new AutenticadorPassword();
    }

    public Usuario autenticarUsuario(HttpServletRequest request) throws LoginInvalido, SQLException {

        Usuario usuario = repositorioUsuario.obtenerPorId(request.getParameter("nombreUsuario"));
        if (usuario == null) {
            throw new LoginInvalido(" Nombre de usuario o contraseña incorrecta");
        }
        if (!autenticadorPassword.contraseñaCorrecta( request.getParameter("password"),usuario.getPassword())) {
            throw new LoginInvalido(" Nombre de usuario o contraseña incorrecta ");
        }
        if (usuario.getRol() == null) {
            throw new LoginInvalido(" Revise sus datos porfavor ");
        }
        return usuario;
    }
    
    
    
    
    
}
