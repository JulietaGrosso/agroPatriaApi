package com.agropatriaapp.agropatriaapi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.agropatriaapp.agropatriaapi.model.Cuentas;
import com.agropatriaapp.agropatriaapi.services.CuentasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("cuenta")
public class CuentasController {

    @Autowired
    private CuentasService cuentasService;

    


    @GetMapping("cuenta")
    public ResponseEntity<?> getCuentas(){
        List<Cuentas>cuentas = cuentasService.getCuentas();
        return ResponseEntity.ok(cuentas);
    }
}




