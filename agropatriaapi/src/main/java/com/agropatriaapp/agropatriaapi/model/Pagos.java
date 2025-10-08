package com.agropatriaapp.agropatriaapi.model;

import java.sql.Date;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name="pagos")
public class Pagos {
  
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idPago")
    @Setter
    @Getter
    private Integer idPago;

   @Column(name = "uuidPago")
    private String uuidPago;

   @Column(name = "idCuenta")
    private int idCuenta;

   @Column(name = "idPlan")
    private int idPlan;

   @Column(name = "monto")
    private int monto;

   @Column(name = "duracion")
    private int duracion;

   @Column(name = "fechaPago")
   @Nullable
    private Date fechaPago;

   @Column(name = "paymentId")
    private int paymentId;


    public Pagos(){
     }


  public String getUuidPago() {
    return this.uuidPago;
  }

  public void setUuidPago(String uuidPago) {
    this.uuidPago = uuidPago;
  }

  public int getIdCuenta() {
    return this.idCuenta;
  }

  public void setIdCuenta(int idCuenta) {
    this.idCuenta = idCuenta;
  }

  public int getIdPlan() {
    return this.idPlan;
  }

  public void setIdPlan(int idPlan) {
    this.idPlan = idPlan;
  }

  public int getMonto() {
    return this.monto;
  }

  public void setMonto(int monto) {
    this.monto = monto;
  }

  public int getDuracion() {
    return this.duracion;
  }

  public void setDuracion(int duracion) {
    this.duracion = duracion;
  }

  public Date getFechaPago() {
    return this.fechaPago;
  }

  public void setFechaPago(Date fechaPago) {
    this.fechaPago = fechaPago;
  }

  public int getPaymentId() {
    return this.paymentId;
  }

  public void setPaymentId(int paymentId) {
    this.paymentId = paymentId;
  }

}
