package com.agropatriaapp.agropatriaapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name="PlanesCategoria")
public class PlanesCategoria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "planes_id_plan")
    private int planesIdPlan;
    

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="categorias_id")
    private Categorias categorias;


    public PlanesCategoria(){

    }

    public PlanesCategoria(int planesIdPlan){
        this.planesIdPlan = planesIdPlan;
    }
    


     public int getPlanesIdPlan() {
        return planesIdPlan;
    }
    public void setPlanesIdPlan(int planesIdPlan) {
        this.planesIdPlan = planesIdPlan;
    }
    

    public Categorias getCategorias(){
        return categorias;
    }
    public void setCategorias(Categorias categorias){
        this.categorias = categorias;
    }


}
