package com.agropatriaapp.agropatriaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agropatriaapp.agropatriaapi.dto.BusquedaDto;
import com.agropatriaapp.agropatriaapi.dto.VendedorDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Cuentas;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.Vendedor;
import com.agropatriaapp.agropatriaapi.services.VendedorService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("vendedor")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping("{idCuentas}")
    public ResponseEntity<?> getVendedor(@PathVariable("idCuentas") int idCuentas) {
       try{
        return ResponseEntity.ok(vendedorService.getVendedor(idCuentas));
        }catch(NotFoundEntityException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(new Response(false, "El vendedor no se encontró"));
        }
    }
    
    @PostMapping
    public ResponseEntity<?>postVendedor(@RequestBody VendedorDto vendedorDto, @RequestHeader (name="Authorization") String token){
        return ResponseEntity.ok(vendedorService.postVendedor(vendedorDto));
    }

    @DeleteMapping("{idCuentas}")
    public ResponseEntity<?>deleteVendedor(@PathVariable("idCuentas") int idCuentas){
        return ResponseEntity.ok(vendedorService.deleteVendedor(idCuentas));
    }
       
    @PutMapping("{idCuentas}")
    public ResponseEntity<?> putVendedor(@PathVariable("idCuentas")int idCuentas, @RequestBody VendedorDto vendedorDto){
        try{
        return ResponseEntity.ok(vendedorService.putVendedor(idCuentas, vendedorDto));
        }catch(NotFoundEntityException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(new Response(false, "El vendedor no se encontró"));
        }

    }

    @PostMapping("seleccionarPlan/{idPlan}")
    public ResponseEntity<?> postPago(@PathVariable("idPlan") int idPlan) throws MPException, MPApiException, NotFoundEntityException{
        return ResponseEntity.ok(vendedorService.postPago(idPlan));
    }
}





