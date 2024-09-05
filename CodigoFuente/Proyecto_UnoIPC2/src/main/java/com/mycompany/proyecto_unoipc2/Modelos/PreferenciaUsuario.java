/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Modelos;

import com.mycompany.proyecto_unoipc2.Utileria.TipoPreferencia;

/**
 *
 * @author kevin-mushin
 */
public class PreferenciaUsuario {
    
        private  Long idPreferencia; 
        private final  String nombreUsuario;
        private final  String preferencia;
        private final TipoPreferencia tipoPreferencia;

            public String getPreferencia() {
                return preferencia;
            }
            public TipoPreferencia getTipoPreferencia() {
                return tipoPreferencia;
            }

            public String getNombreUsuario() {
                return nombreUsuario;
            }

            public Long getIdPreferencia() {
                return idPreferencia;
            }

            public void setIdPreferencia(Long idPreferencia) {
                this.idPreferencia = idPreferencia;
            }
            
            // COSNTRUCTOR DE UNA PREFERENCIA DEL USUARIO
    public PreferenciaUsuario(String preferencia, String nombreUsuario, TipoPreferencia tipoPreferencia) {
        this.preferencia = preferencia;
        this.nombreUsuario = nombreUsuario;
        this.tipoPreferencia = tipoPreferencia;
    }
        
        
        
}

