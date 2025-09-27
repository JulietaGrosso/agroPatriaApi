package com.agropatriaapp.agropatriaapi.dto;

import lombok.Getter;
import lombok.Setter;

public class VendedorDto {
    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String razon;

    @Getter
    @Setter
    private long cuitVendedor;

    @Getter
    @Setter
    private String localidadVendedor;

    @Getter
    @Setter
    private String provinciaVendedor;

    @Getter
    @Setter
    private String logo;

    @Getter
    @Setter
    private int planesIdPlan;


    public VendedorDto(String nombre, String razon, long cuit_vendedor, String localidadVendedor,
     String provinciaVendedor, String logo, int planes_id_plan){
        this.nombre = nombre;
        this.razon = razon;
        this.cuitVendedor = cuit_vendedor;
        this.localidadVendedor = localidadVendedor;
        this.provinciaVendedor = provinciaVendedor;
        this.logo = logo;
        this.planesIdPlan = planes_id_plan;
    }



}
