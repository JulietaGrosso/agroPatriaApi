package com.agropatriaapp.agropatriaapi.dto;

import com.agropatriaapp.agropatriaapi.model.Cuentas;
import com.agropatriaapp.agropatriaapi.model.Pagos;
import com.agropatriaapp.agropatriaapi.model.Vendedor;

import lombok.Getter;
import lombok.Setter;

public class MyInfoDto {

  @Setter
  @Getter
  Cuentas cuenta;

  @Setter
  @Getter
  Vendedor vendedor;

  @Setter
  @Getter
  Pagos UltimoPago;

  public MyInfoDto(){
    
  }
}
