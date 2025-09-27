package com.agropatriaapp.agropatriaapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "productos")
public class Productos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int idProducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "condicion")
    private int condicion;

    @Column(name = "clasificacion")
    private String clasificacion;

    @Column(name = "region")
    private String region;

    @Column(name = "categoria")
    private int categoria;

    @Column(name = "moneda")
    private String moneda;

    private Integer cantidadPublicaciones;

    public Productos(){

    }
    public Productos(int idProducto, String nombre, String descripcion, String imagen, Integer precio, 
    int condicion, String clasificacion, String region, int categoria, String moneda){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.condicion = condicion;
        this.clasificacion = clasificacion;
        this.region = region;
        this.categoria = categoria;
        this.moneda = moneda;
    }

    public int getIdProducto(){
        return idProducto;
    }
    public void setIdProducto(int idProducto){
        this.idProducto = idProducto;
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
    public String getImagen(){
        return imagen;
    }
    public void setImagen(String imagen){
        this.imagen = imagen;
    }
    public Integer getPrecio(){
        return precio;
    }
    public void setPrecio(Integer precio){
        this.precio = precio;
    }
    public int getCondicion(){
        return condicion;
    }
    public void setCondicion(int condicion){
        this.condicion = condicion;
    }
    public String getClasificacion(){
        return clasificacion;
    }
    public void setClasificacion(String clasificacion){
        this.clasificacion = clasificacion;
    }
    public String getRegion(){
        return region;
    }
    public void setRegion(String region){
        this.region = region;
    }
    public int getCategoria(){
        return categoria;
    }
    public void setCategoria(int categoria){
        this.categoria = categoria;
    }
    public String getMoneda(){
        return moneda;
    }
    public void setMoneda(String moneda){
        this.moneda = moneda;
    }

    public Integer getCantidadPublicaciones(){
        return cantidadPublicaciones;
    }
    
    public void setCantidadPublicaciones(Integer cantidadPublicaciones){
        this.cantidadPublicaciones = cantidadPublicaciones;
    }


















}
