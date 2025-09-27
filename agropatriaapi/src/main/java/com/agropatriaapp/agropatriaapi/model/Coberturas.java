package com.agropatriaapp.agropatriaapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="coberturas")
public class Coberturas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCobertura;
    
    @Column(name = "nombre_cobertura")
    private String nombreCobertura;

    @Column(name = "seguros_id_seguros")
    private int seguroId;



    public Coberturas(){

    }
    public Coberturas(int idCobertura, String nombreCobertura, int seguroId){
        this.idCobertura = idCobertura;
        this.nombreCobertura = nombreCobertura;
        this.seguroId = seguroId;
    }

    public int getIdCobertura(){
        return idCobertura;
    }
    public void setIdCobertura(int idCobertura){
        this.idCobertura = idCobertura;
    }
    public String getNombreCobertura(){
        return nombreCobertura;
    }
    public void setNombreCobertura(String nombreCobertura){
        this.nombreCobertura = nombreCobertura;
    }

    public int getSeguroId(){
        return seguroId;
    }
    public void setSeguroId(int seguroId){
        this.seguroId = seguroId;
    }


    
}
