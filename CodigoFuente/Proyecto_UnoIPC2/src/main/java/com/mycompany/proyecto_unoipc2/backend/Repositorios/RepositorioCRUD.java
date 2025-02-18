/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Repositorios;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 * @param <T>
 * @param <ID>
 * @param <P>
 */
public interface RepositorioCRUD<T , ID>{
    
    List<T> listar(ID identificador) throws SQLException;
    
    T guardar(T modelo) throws SQLException;
    
    T actualizar(T modelo) throws SQLException;
    
    T obtenerPorId(Long identificador) throws SQLException;
    
    void eliminar(ID identificador) throws SQLException;
    
    
    
    
}
