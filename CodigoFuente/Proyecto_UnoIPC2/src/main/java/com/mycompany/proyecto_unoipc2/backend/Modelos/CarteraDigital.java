/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Modelos;

/**
 *
 * @author kevin-mushin
 */
public class CarteraDigital {
    
    private String nombreRepresentante;
    private Double saldoDisponible;

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    /* constructor para carteras digitales */
    public CarteraDigital(String nombreRepresentante, Double saldoDisponible) {
        this.nombreRepresentante = nombreRepresentante;
        this.saldoDisponible = saldoDisponible;

    }
    
    public void debitarSaldo(){}
    
    
    
}
