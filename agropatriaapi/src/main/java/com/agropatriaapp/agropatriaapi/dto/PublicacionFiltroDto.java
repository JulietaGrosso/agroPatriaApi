package com.agropatriaapp.agropatriaapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class PublicacionFiltroDto {
  @Getter
  @Setter
  Integer vendedor;

  @Getter
  @Setter
  Integer producto;

  @Getter
  @Setter
  Integer condicion;

  @Getter
  @Setter
  Integer categoria;

  @Getter
  @Setter
  Integer vendido;

  @Setter
  @Getter
  Integer page;

  @Getter
  @Setter
  Integer elementsPerPage;
}
