package com.agropatriaapp.agropatriaapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.BusquedaDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Busqueda;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.repositorios.BusquedaRepositorio;

@Service
public class BusquedaService {

    @Autowired
    private BusquedaRepositorio busquedaRepositorio;

    public List<Busqueda> getBusquedas(){
        return busquedaRepositorio.findAll();
    }

    public Response deleteBusqueda(int id_busqueda){
        busquedaRepositorio.deleteById(id_busqueda);
        return new Response(true, "Eliminado Correctamente");
    }

    public Response postBusqueda(BusquedaDto busquedaDto){
        Busqueda busqueda = new Busqueda();
        busqueda.setNombre(busquedaDto.getNombre());
        busqueda.setDescripcion(busquedaDto.getDescripcion());
        busquedaRepositorio.save(busqueda);
        return new Response(true, "Agregado Correctamente");
    }

    public Response putBusqueda(int id_busqueda, BusquedaDto busquedaDto) throws NotFoundEntityException{
        Optional<Busqueda> busquedaOp = busquedaRepositorio.findById(id_busqueda);
        if(busquedaOp.isPresent()){
            Busqueda busqueda = busquedaOp.get();
            busqueda.setNombre(busquedaDto.getNombre());
            busqueda.setDescripcion(busquedaDto.getDescripcion());
            busquedaRepositorio.save(busqueda);
            return new Response(true, "Se actualizó correctamente");
        }
        throw new NotFoundEntityException("No se encontró la busqueda");
    }
}
