/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.ImplementacionesRepositorios;

import com.mycompany.proyecto_unoipc2.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.Repositorios.RepositorioLecturaEscritura;
import jakarta.jms.Connection;

/**
 *  repositorio que implementa escritura lectura , se conecta con la database
 * @author kevin-mushin
 */
public class RepositorioUsuario implements RepositorioLecturaEscritura<Usuario> {

    private final Connection conn;

    public RepositorioUsuario(Connection conn) {
        this.conn = conn;
    }    
    
    @Override
    public Usuario guardar(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario actualizar(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario porNombreUsuario(String nombreUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
