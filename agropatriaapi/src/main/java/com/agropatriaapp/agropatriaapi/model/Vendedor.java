package com.agropatriaapp.agropatriaapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="vendedor")
public class Vendedor {
    @Id
    private int idCuentas;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "razon")
    private String razon;

    @Column(name = "cuit_vendedor")
    private long cuitVendedor;

    @Column(name = "localidad_vendedor")
    private String localidadVendedor;

    @Column(name = "provincia_vendedor")
    private String provinciaVendedor;

    @Column(name = "logo_vendedor")
    private String logo;

    @Column(name = "planes_id_plan")
    private int planesIdPlan;

    public Vendedor(){
        
    }

   public Vendedor(int idCuentas,String nombre, String razon, long cuitVendedor,
    String localidadVendedor, String provinciaVendedor, String logo, int planesIdPlan){
        this.idCuentas = idCuentas;
        this.nombre = nombre;
        this.razon = razon;
        this.cuitVendedor = cuitVendedor;
        this.localidadVendedor = localidadVendedor;
        this.provinciaVendedor = provinciaVendedor;
        this.logo = logo;
   }

   public int getIdCuentas() {
        return idCuentas;
    }
    public void setIdCuentas(int idCuentas) {
        this.idCuentas = idCuentas;
    }
   public String getNombre(){
    return nombre;
   }
   public void setNombre(String nombre){
    this.nombre = nombre;
   }
   public String getRazon(){
    return razon;
   }
   public void setRazon(String razon){
    this.razon = razon;
   }
   public long getCuitVendedor(){
    return cuitVendedor;
   }
   public void setCuitVendedor(long cuitVendedor){
    this.cuitVendedor=cuitVendedor;
   }
   public String getLocalidadVendedor(){
    return localidadVendedor;
   }
   public void setLocalidadVendedor(String localidadVendedor){
    this.localidadVendedor = localidadVendedor;
   }
   public String getProvinciaVendedor(){
    return provinciaVendedor;
   }
   public void setProvinciaVendedor(String provinciaVendedor){
    this.provinciaVendedor = provinciaVendedor;
   }
   public String getLogo(){
    return logo;
   }
   public void setLogo(String logo){
    this.logo = logo;
   }
    public int getPlanesIdPlan() {
        return planesIdPlan;
    }
    public void setPlanesIdPlan(int planesIdPlan) {
        this.planesIdPlan = planesIdPlan;
    }






}
