/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Utileria;

import com.mycompany.proyecto_unoipc2.Modelos.PreferenciaUsuario;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class Validaciones {
    UtileriaValidaciones utileria = new UtileriaValidaciones();
    
    public boolean esRegistroValido(String nombreUsuario, String password, String nombrePila, String rol, List<PreferenciaUsuario> preferencias) {
        
        if (!utileria.noContieneEspacios(nombreUsuario)) {
            return false;
        }
        if (!utileria.noContieneEspacios(password)) {
            return false;
        }
        if (!utileria.esFormatoNombre(nombrePila)) {
            return false;
        }
        if (!preferencias.isEmpty()) {
            for (int i = 0; i < preferencias.size(); i++) {
                  if (utileria.preferenciaFormatoInvalido(preferencias.get(i))) {
                    return false;
                }       
            }
        }
        return true;
        
        
    }
    
}
