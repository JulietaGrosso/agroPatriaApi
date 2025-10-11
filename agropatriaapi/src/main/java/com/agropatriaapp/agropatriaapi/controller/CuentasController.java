package com.agropatriaapp.agropatriaapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.agropatriaapp.agropatriaapi.dto.CuentasDto;
import com.agropatriaapp.agropatriaapi.dto.MyInfoDto;
import com.agropatriaapp.agropatriaapi.dto.ResetPasswordDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Cuentas;
import com.agropatriaapp.agropatriaapi.services.CuentasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("cuenta")
public class CuentasController {

    @Autowired
    private CuentasService cuentasService;

    
    @GetMapping("cuentas")
    public ResponseEntity<?> getCuentas(){
        List<Cuentas>cuentas = cuentasService.getCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @PostMapping("registro")
    public ResponseEntity<?>postCuenta(@RequestBody CuentasDto cuentasDto){
        return ResponseEntity.ok(cuentasService.postRegistro(cuentasDto));
    }
    
    @PostMapping("login")
    public ResponseEntity<?>postLogin(@RequestBody CuentasDto cuentasDto) throws NotFoundEntityException{
        return ResponseEntity.ok(cuentasService.postLogin(cuentasDto));
    }
    
    
    @GetMapping("me")
    public ResponseEntity<?> getMyInformation(){
        MyInfoDto myinfo = cuentasService.getMyInformation();
        return ResponseEntity.ok(myinfo);
    }
    
    @PostMapping("reset/{id}")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetDto) throws NotFoundEntityException{
        return ResponseEntity.ok(cuentasService.resetPassword(resetDto));
    }
    
    @PostMapping("toggle/{id}/{status}")
    public ResponseEntity<?> updateActividadCuenta(@PathVariable("id") int id, @PathVariable("status") int status) throws NotFoundEntityException{
        return ResponseEntity.ok(cuentasService.updateActividadCuenta(id, status == 1));
    }

    
}




