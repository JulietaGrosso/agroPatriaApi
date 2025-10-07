package com.agropatriaapp.agropatriaapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity(name="textos")
public class Textos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "texto")
    @Lob
    private String texto;
    
    public Textos(){
      
    }


  public Textos(int id, String texto){
    this.id = id;
    this.texto = texto;
  }

  public int getId(){
    return id;
  }

  public void setId(int id){
    this.id = id;
  }

  public String getTexto(){
    return texto;
  }
  public void setTexto(String texto){
    this.texto = texto;
  }



}
