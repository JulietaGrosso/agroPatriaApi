package com.agropatriaapp.agropatriaapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agropatriaapp.agropatriaapi.dto.PublicacionDto;
import com.agropatriaapp.agropatriaapi.dto.PublicacionFiltroDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Publicacion;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.services.PublicacionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("publicacion")
public class PublicacionController {

    @Autowired 
    PublicacionService publicacionService;

    @GetMapping("{id}")
    public ResponseEntity<?> getPublicacion(@PathVariable("id") int id) throws NotFoundEntityException{
        return ResponseEntity.ok(publicacionService.getPublicacion(id));
    }

    @GetMapping("publicaciones")
    public ResponseEntity<?> getPublicaciones(
      @RequestParam(required = false) Integer vendedor,
      @RequestParam(required = false) Integer producto,
      @RequestParam(required = false) Integer condicion,
      @RequestParam(required = false) Integer vendido
    ) {
      PublicacionFiltroDto filtros =
          PublicacionFiltroDto
          .builder()
          .producto(producto)
          .condicion(condicion)
          .vendedor(vendedor)
          .vendido(vendido)
          .build();

      List<Publicacion> publicacion = publicacionService.getPublicaciones(filtros);
      return ResponseEntity.ok(publicacion);
    }
    
    @PostMapping
    public ResponseEntity<?> postPublicaciones(@RequestBody PublicacionDto publicacionDto){
        return ResponseEntity.ok(publicacionService.postPublicacion(publicacionDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePublicacion(@PathVariable("id") int id){
        return ResponseEntity.ok(publicacionService.deletePublicacion(id));
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> putPublicacion(@PathVariable("id") int id, @RequestBody PublicacionDto publicacionDto){
      try{
          return ResponseEntity.ok(publicacionService.putPublicacion(id, publicacionDto));
      }catch(NotFoundEntityException e){
        e.printStackTrace();
            return ResponseEntity.status(404).body(new Response(false,"La Puclicación no se encontró"));
      }
    }
    
}
