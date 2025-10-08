package com.agropatriaapp.agropatriaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="cuentas")
public class Cuentas {
    @Id
    private int id;
    
    @Column(name = "email")
    private String correo;
    
    @JsonIgnore
    @Column(name = "password")
    private String contrasena;

    public Cuentas(){

    }
    
    public Cuentas(int id, String correo, String contrasena){
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
    }


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getContrasena(){
        return contrasena;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }






}
