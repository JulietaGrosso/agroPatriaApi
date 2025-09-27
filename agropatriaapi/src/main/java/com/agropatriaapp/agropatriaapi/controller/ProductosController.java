package com.agropatriaapp.agropatriaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> getProductos(){
        List<Productos>productos = productosService.getProductos();
        return ResponseEntity.ok(productos); 

    }



}
