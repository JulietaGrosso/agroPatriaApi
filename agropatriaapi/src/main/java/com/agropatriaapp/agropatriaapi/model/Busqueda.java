package com.agropatriaapp.agropatriaapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="busqueda")
public class Busqueda {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_busqueda")
    private int idBusqueda;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public Busqueda(){

    }

    public Busqueda(int idBusqueda, String nombre, String descripcion){
        this.idBusqueda = idBusqueda;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdBusqueda(){
        return idBusqueda;
    }
    public void setIdBusqueda(int idBusqueda){
        this.idBusqueda = idBusqueda;
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

}
