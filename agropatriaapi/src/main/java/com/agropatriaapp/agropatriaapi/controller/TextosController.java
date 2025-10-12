package com.agropatriaapp.agropatriaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.agropatriaapp.agropatriaapi.dto.TextosDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.services.TextosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("textos")
public class TextosController {

  @Autowired
  private TextosService textosService;


  @GetMapping("{id}")
  public ResponseEntity<?>getTexto(@PathVariable("id")int id){
     try{
        return ResponseEntity.ok(textosService.getTexto(id));
        }catch(NotFoundEntityException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(new Response(false, "El texto no se encontró"));
        }
    }
 

  @PutMapping("{id}")
    public ResponseEntity<?> putTextos(@PathVariable("id")int id, @RequestBody TextosDto textosDto){
        try{
        return ResponseEntity.ok(textosService.putTextos(id, textosDto));
        }catch(NotFoundEntityException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(new Response(false, "El texto no se encontró"));
        }

    }

  }




