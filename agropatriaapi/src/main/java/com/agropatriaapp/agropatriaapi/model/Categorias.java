package com.agropatriaapp.agropatriaapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity(name="categorias")
public class Categorias {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    @Column(name = "id")
     private int id;
    
     @Column(name = "nombre")
     private String nombre;

     @Column(name = "descripcion")
     private String descripcion;

     @Column(name = "url")
     private String url;


    public Categorias(){
        
    }


    public Categorias(int id, String nombre, String descripcion, String url){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }





}
