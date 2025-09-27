package com.agropatriaapp.agropatriaapi.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="seguros")
public class Seguros {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idSeguros;
    @Column(name = "nombre_seguro")
    private String nombreSeguro;
    
    @OneToMany(mappedBy = "seguroId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coberturas> listaCoberturas = new ArrayList<>();
    

    public Seguros(){

    }

    public Seguros(int idSeguros, String nombreSeguro){
        this.idSeguros = idSeguros;
        this.nombreSeguro = nombreSeguro;
    }

    public int getIdSeguros(){
        return idSeguros;
    }
    public void setIdSeguros(int idSeguros){
        this.idSeguros = idSeguros;
    }
    public String getNombreSeguro(){
        return nombreSeguro;
    }
    public void setNombreSeguro(String nombreSeguro){
        this.nombreSeguro = nombreSeguro;
    }
    
    public List<Coberturas> getListaCoberturas(){
        return listaCoberturas;
    }
    public void setListaCoberturas(List<Coberturas> listaCoberturas){
        this.listaCoberturas = listaCoberturas;
    }




}
