package com.agropatriaapp.agropatriaapi.dto;

import com.agropatriaapp.agropatriaapi.model.Cuentas;

import lombok.Getter;
import lombok.Setter;

public class CuentasDto {
    @Getter
    @Setter
    private String correo;
    
    @Getter
    @Setter
    private String contrasena;


   public CuentasDto(String correo, String contrasena){
      this.correo = correo;
      this.contrasena = contrasena;
  }



}
