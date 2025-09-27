package com.agropatriaapp.agropatriaapi.dto;

import lombok.Getter;
import lombok.Setter;

public class TextosDto {

  @Getter
  @Setter
  private int id;

  @Getter
  @Setter
  private String texto;

  public TextosDto(int id, String texto){
    this.id = id;
    this.texto = texto;
  }

}
