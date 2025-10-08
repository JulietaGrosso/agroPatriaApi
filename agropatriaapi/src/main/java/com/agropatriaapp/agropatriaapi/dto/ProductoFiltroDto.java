package com.agropatriaapp.agropatriaapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ProductoFiltroDto {
  @Getter
  @Setter
  String nombre;

  @Getter
  @Setter
  Integer categoria;
}
