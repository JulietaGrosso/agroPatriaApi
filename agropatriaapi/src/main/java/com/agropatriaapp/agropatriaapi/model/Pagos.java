package com.agropatriaapp.agropatriaapi.model;


@Entity(name="pagos")
public class Pagos {
  
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
    private int fechaPago;

   @Column(name = "paymentId")
    private int paymentId;


    public Pagos(String uuidPago, int idCuenta, int idPlan, int monto, int duracion,
     int fechaPago, int paymentId){
      this.uuidPago = uuidPago;
      this.idCuenta = idCuenta;
      this.idPlan = idCuenta;
      this.monto = monto;
      this.duracion = duracion;
      this.fechaPago = fechaPago;
      this.paymentId = paymentId;
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

  public int getFechaPago() {
    return this.fechaPago;
  }

  public void setFechaPago(int fechaPago) {
    this.fechaPago = fechaPago;
  }

  public int getPaymentId() {
    return this.paymentId;
  }

  public void setPaymentId(int paymentId) {
    this.paymentId = paymentId;
  }

}
