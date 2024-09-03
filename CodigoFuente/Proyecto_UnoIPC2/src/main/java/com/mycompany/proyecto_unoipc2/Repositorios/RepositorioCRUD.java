/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Repositorios;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 * @param <T>
 */
public interface RepositorioCRUD<T>{
    
    List<T> listar() throws SQLException;
    
    T guardar(T t) throws SQLException;
    
    T actualizar(T t) throws SQLException;
    
    T porId(String t) throws SQLException;
    
    
    
    
}
