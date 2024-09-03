/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Repositorios;

/**
 *
 * @author kevin-mushin
 */
public interface RepositorioLecturaEscritura<T> {
    
    
        T guardar(T t);
    
        T actualizar(T t);
    
        T porNombreUsuario(String nombreUsuario);
    
    
}
