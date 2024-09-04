/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Modelos;

/**
 *
 * @author kevin-mushin
 */
public class CarteraDigital {
    
    private Long idCartera;
    private Double saldoDisponible;
    private Long idUsuario;

    public Long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(Long idCartera) {
        this.idCartera = idCartera;
    }

    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }    
    
    public CarteraDigital(Long idCartera, Double saldoDisponible, Long idUsuario) {
        this.idCartera = idCartera;
        this.saldoDisponible = saldoDisponible;
        this.idUsuario = idUsuario;
    }
    
    public void debitarSaldo(){}
    
    
    
}
