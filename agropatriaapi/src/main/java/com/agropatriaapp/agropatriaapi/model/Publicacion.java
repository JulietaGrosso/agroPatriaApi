package com.agropatriaapp.agropatriaapi.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name="publicacion")
public class Publicacion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "productos_id")
    private int productosId;
    @Column(name = "vendedor_cuentas_id")
    private int vendedorCuentasId;
    @Column(name = "vendido")
    private boolean vendido;
     @Column(name = "nombrePublicacion")
    private String nombrePublicacion;

    @Column(name = "descripcionPublic")
    private String descripcionPublic;

    @Column(name = "condicion")
    private String condicion;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "modelo")
    @Getter
    @Setter
    private String modelo;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "categoria")
    @Setter
    @Getter
    private Integer categoria;

    @Column(name = "moneda")
    @Getter
    @Setter
    private String moneda;

    @Column(name = "financiacion")
    @Getter
    @Setter
    private String financiacion;

    @Column(name = "imagenes")
    @Lob
    private List<String> imagenes;

    @Column(name = "fecha_publicacion", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Getter
    private LocalDateTime fechaPublicacion;

    @ManyToOne()
    @JoinColumn(name = "vendedor_cuentas_id", referencedColumnName = "idCuentas", insertable = false, updatable = false)
    @Getter
    @Setter
    private Vendedor vendedor;

    public Publicacion(){
        this.fechaPublicacion = LocalDateTime.now();

    }
    public Publicacion(int id, int productosId, int vendedorCuentasId, boolean vendido){
        this.id = id;
        this.productosId = productosId;
        this.vendedorCuentasId = vendedorCuentasId;
        this.vendido = vendido;
        this.fechaPublicacion = LocalDateTime.now();
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public boolean getVendido(){
        return vendido;
    }
    public void setVendido(boolean vendido){
        this.vendido = vendido;
    }
     public int getProductosId() {
        return productosId;
    }
    public void setProductosId(int productosId) {
        this.productosId = productosId;
    }
    public int getVendedorCuentasId() {
        return vendedorCuentasId;
    }
    public void setVendedorCuentasId(int vendedorCuentasId) {
        this.vendedorCuentasId = vendedorCuentasId;
    }

    


    public String getNombrePublicacion() {
        return this.nombrePublicacion;
    }

    public void setNombrePublicacion(String nombrePublicacion) {
        this.nombrePublicacion = nombrePublicacion;
    }

    public String getDescripcionPublic() {
        return this.descripcionPublic;
    }

    public void setDescripcionPublic(String descripcionPublic) {
        this.descripcionPublic = descripcionPublic;
    }

    public String getCondicion() {
        return this.condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getPrecio() {
        return this.precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public List<String> getImagenes() {
        return this.imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

}
