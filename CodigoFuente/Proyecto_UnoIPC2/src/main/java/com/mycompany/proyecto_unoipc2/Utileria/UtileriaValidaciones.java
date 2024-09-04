/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Utileria;

import com.mycompany.proyecto_unoipc2.Modelos.PreferenciaUsuario;

/**
 *
 * @author kevin-mushin
 */
public class UtileriaValidaciones {
   
    public boolean esFormatoNombre(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        String nombreRegex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";
        return input.matches(nombreRegex);
    }
    
    public boolean noContieneEspacios(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return !input.contains(" ");
    }

    boolean preferenciaFormatoInvalido(PreferenciaUsuario get) {
        return !esFormatoNombre(get.getPreferencia());
    }
}
