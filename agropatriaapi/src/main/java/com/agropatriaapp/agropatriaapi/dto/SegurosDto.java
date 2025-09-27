package com.agropatriaapp.agropatriaapi.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class SegurosDto {


  
  @Getter
  @Setter
  private String nombreSeguro;
  
  @Getter
  @Setter
  private List<String> listaCoberturas; 
  public SegurosDto(String nombreSeguro){
    this.nombreSeguro = nombreSeguro;
  }

  
}
