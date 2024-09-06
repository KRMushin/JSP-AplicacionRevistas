/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Utileria;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author kevin-mushin
 */
public class Encriptador {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    /*
        devuelve la contraseña encriptada
    */
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
