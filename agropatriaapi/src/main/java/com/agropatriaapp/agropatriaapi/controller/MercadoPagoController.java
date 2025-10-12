package com.agropatriaapp.agropatriaapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.agropatriaapp.agropatriaapi.services.MercadoPagoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("mercadopago")
public class MercadoPagoController {
  @Autowired
  MercadoPagoService mercadoPagoService;
  
  @PostMapping("notification/{paymentUuid}")
  public ResponseEntity<?> paymentNotification(@PathVariable("paymentUuid") String paymentUuid, @RequestBody JsonNode payload) throws MPException, MPApiException {
      
      return ResponseEntity.ok().body(
        mercadoPagoService.paymentNotification(paymentUuid, payload)
      );
  }
  
}
