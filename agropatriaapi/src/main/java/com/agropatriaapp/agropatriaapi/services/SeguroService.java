package com.agropatriaapp.agropatriaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.CoberturasDto;
import com.agropatriaapp.agropatriaapi.dto.SegurosDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Coberturas;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.Seguros;
import com.agropatriaapp.agropatriaapi.repositorios.CoberturasRepositorio;
import com.agropatriaapp.agropatriaapi.repositorios.SeguroRepositorio;

@Service
public class SeguroService {

    @Autowired
    private SeguroRepositorio seguroRepositorio;
    @Autowired
    private CoberturasService coberturasService;

    public List<Seguros> getSeguros(){
        return seguroRepositorio.findAll();
    }

    public Response deleteSeguros(int idSeguros){
        seguroRepositorio.deleteById(idSeguros);
        return new Response(true, "Eliminado correctamente");
    }

    public Response postSeguros(SegurosDto segurosDto){
        Seguros seguros = new Seguros();
        seguros.setNombreSeguro(segurosDto.getNombreSeguro());
        Seguros segurosGuardado = seguroRepositorio.save(seguros);
        coberturasService.guardarCoberturas(segurosGuardado.getIdSeguros(), segurosDto.getListaCoberturas());
        //hacer el metodo deguardar que recibe el id de se seguro y una lista de coberturas
        return new Response(true, "Seguro agregado correctamente");
    }

    

    public Response putSeguros(int idSeguros, SegurosDto segurosDto) throws NotFoundEntityException{
        Optional<Seguros> segurosOp = seguroRepositorio.findById(idSeguros);
        if(segurosOp.isPresent()){
            Seguros seguros = segurosOp.get();
            seguros.setNombreSeguro(segurosDto.getNombreSeguro());
            seguroRepositorio.save(seguros);
            coberturasService.borrarCoberturas(idSeguros);
            coberturasService.guardarCoberturas(idSeguros, segurosDto.getListaCoberturas());
            return new Response(true, "Se actualizó correctamente");
        }
        throw new NotFoundEntityException("No se encontró el seguro");
    }






}
