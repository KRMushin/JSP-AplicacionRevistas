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
 * @param <ID_USUARIO>
 * @param <P>
 * @param <ID_MODELO>
 */
public interface RepositorioCRUD<T , ID, P>{
    
    List<T> listar(ID identificador, P parametro) throws SQLException;
    
    T guardar(T modelo) throws SQLException;
    
    T actualizar(T modelo) throws SQLException;
    
    T obtenerPorId(Long identificador) throws SQLException;
    
    void eliminar(Long identificador) throws SQLException;
    
    
    
    
}
