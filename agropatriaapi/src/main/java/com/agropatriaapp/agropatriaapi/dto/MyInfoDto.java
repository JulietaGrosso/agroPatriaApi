package com.agropatriaapp.agropatriaapi.dto;

import java.util.Map;

import com.agropatriaapp.agropatriaapi.model.Cuentas;
import com.agropatriaapp.agropatriaapi.model.Pagos;
import com.agropatriaapp.agropatriaapi.model.Planes;
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

  @Setter
  @Getter
  Planes plan;

  @Setter
  @Getter
  Map<String, Object> UserData;

  public MyInfoDto(){
    
  }
}
