package com.agropatriaapp.agropatriaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agropatriaapp.agropatriaapi.dto.SegurosDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.Seguros;
import com.agropatriaapp.agropatriaapi.services.SeguroService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("seguros")
public class SeguroController {

    @Autowired
    private SeguroService seguroService;

    @GetMapping
    public ResponseEntity<?>getSeguros(){
        List<Seguros> seguros = seguroService.getSeguros();
        return ResponseEntity.ok(seguros);
    }
    
    @PostMapping
    public ResponseEntity<?>postSeguros(@RequestBody SegurosDto segurosDto){
        return ResponseEntity.ok(seguroService.postSeguros(segurosDto));
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?>putSeguros(@PathVariable("id")int id, @RequestBody SegurosDto segurosDto) throws NotFoundEntityException{
       try{
         return ResponseEntity.ok(seguroService.putSeguros(id, segurosDto));
        

       } catch(NotFoundEntityException e){
        e.printStackTrace();
        return ResponseEntity.status(404).body(new Response(false, "Seguro no encontrado"));
       }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?>deleteSeguros(@PathVariable("id") int idSeguros){
        return ResponseEntity.ok(seguroService.deleteSeguros(idSeguros));
    }




}
