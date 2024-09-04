/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Repositorios;

import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public interface RepositorioLecturaEscritura<T> {
    
    
        T guardar(T modelo) throws SQLException;
    
        T actualizar(T modelo) throws SQLException;
    
        T obtenerPorId(Long id) throws SQLException;
    
    
}
