package com.agropatriaapp.agropatriaapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.CoberturasDto;
import com.agropatriaapp.agropatriaapi.model.Coberturas;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.Seguros;
import com.agropatriaapp.agropatriaapi.repositorios.CoberturasRepositorio;
import com.agropatriaapp.agropatriaapi.repositorios.SeguroRepositorio;

@Service
public class CoberturasService {

    @Autowired
    CoberturasRepositorio coberturasRepositorio;

    

    public void guardarCoberturas(int idSeguros, List<String>listaCoberturas){
       for(String cobertura : listaCoberturas){
        Coberturas coberturas = new Coberturas();
        coberturas.setNombreCobertura(cobertura);
        coberturas.setSeguroId(idSeguros);
         coberturasRepositorio.save(coberturas);
       }
    }

    public void borrarCoberturas(int idSeguros){
      List<Coberturas> coberturasSeguro = coberturasRepositorio.findAllBySeguroId(idSeguros);
      for(Coberturas cobertura : coberturasSeguro){
        coberturasRepositorio.delete(cobertura);
       }

    }
}
