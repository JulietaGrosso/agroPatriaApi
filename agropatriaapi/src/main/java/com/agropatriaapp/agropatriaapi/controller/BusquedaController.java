package com.agropatriaapp.agropatriaapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.agropatriaapp.agropatriaapi.dto.BusquedaDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Busqueda;
import com.agropatriaapp.agropatriaapi.model.Cuentas;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.services.BusquedaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("busqueda")
public class BusquedaController {

    @Autowired
    private BusquedaService busquedaService;

    @GetMapping("busca")
     public ResponseEntity<?> getBusquedas(){
        List<Busqueda>busquedas = busquedaService.getBusquedas();
        return ResponseEntity.ok(busquedas);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBusqueda(@PathVariable("id") int id){
        return ResponseEntity.ok(busquedaService.deleteBusqueda(id));
    }

    @PostMapping()
    public ResponseEntity<?> postBusqueda(@RequestBody BusquedaDto busquedaDto){
        return ResponseEntity.ok(busquedaService.postBusqueda(busquedaDto));
    }

    @PutMapping("{id}")
    public  ResponseEntity<?> putBusqueda(@PathVariable("id") int id, @RequestBody BusquedaDto busquedaDto){
        try {
            return ResponseEntity.ok(busquedaService.putBusqueda(id, busquedaDto));
        } catch (NotFoundEntityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.status(404).body(new Response(false,"La busqueda no se encontró"));
        }
    }
    

}
