package com.agropatriaapp.agropatriaapi.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name="planes")
public class Planes {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPlan;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private int precio;

    @Column(name = "duracion_dias")
    private int duracionDias;

    @Column(name = "cantidad_public")
    private int cantidadPublic;

    @Getter
    @Setter
    @OneToMany(mappedBy = "planesIdPlan", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<PlanesCategoria> planesCategorias = new HashSet<>();

    @Getter
    @Setter
    @Column(name = "activo")
    private boolean activo = false;


    public Planes(){

    }

    public Planes(int idPlan, String titulo, String descripcion, int precio, int duracionDias, int cantidadPublic){
        this.idPlan = idPlan;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionDias = duracionDias;
        this.cantidadPublic = cantidadPublic;
    }

    public int getIdPlan() {
        return idPlan;
    }
    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
     public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getDuracionDias() {
        return duracionDias;
    }
    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }
    public int getCantidadPublic() {
        return cantidadPublic;
    }
    public void setCantidadPublic(int cantidadPublic) {
        this.cantidadPublic = cantidadPublic;
    }


}




