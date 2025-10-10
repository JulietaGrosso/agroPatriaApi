package com.agropatriaapp.agropatriaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agropatriaapp.agropatriaapi.dto.ProductoFiltroDto;
import com.agropatriaapp.agropatriaapi.model.Productos;
import com.agropatriaapp.agropatriaapi.services.ProductosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("productos")
public class ProductosController {

    @Autowired
    private ProductosService productosService;


    @GetMapping("producto")
    public ResponseEntity<?> getProductos(
      @RequestParam(required = false) Integer categoria,
      @RequestParam(required = false) Integer condicion
    ){
        List<Productos>productos = productosService.getProductos(categoria, condicion);
        return ResponseEntity.ok(productos); 
    }

    @GetMapping("buscar")
    public ResponseEntity<?> buscarProductos(
      @RequestParam(required = false) Integer categoria,
      @RequestParam(required = false) String nombre
    ) {
      ProductoFiltroDto filtros =
          ProductoFiltroDto
          .builder()
          .categoria(categoria)
          .nombre(nombre)
          .build();
        List<Productos>productos = productosService.buscarProductos(filtros);
        return ResponseEntity.ok(productos); 

    }


}
