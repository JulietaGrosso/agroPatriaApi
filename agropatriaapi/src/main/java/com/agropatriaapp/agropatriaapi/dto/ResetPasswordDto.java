package com.agropatriaapp.agropatriaapi.dto;

import lombok.Getter;
import lombok.Setter;

public class ResetPasswordDto {
    @Getter
    @Setter
    private int id;
    
    @Getter
    @Setter
    private String contrasena;


   public ResetPasswordDto(){
  }



}
