
package com.agropatriaapp.agropatriaapi.dto;

import lombok.Getter;
import lombok.Setter;

public class CoberturasDto {

    @Getter
    @Setter
    private String nombreCobertura;

  

    public CoberturasDto(String nombreCobertura){
        this.nombreCobertura = nombreCobertura;
    }

}
