package com.agropatriaapp.agropatriaapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("pago")
public class MPagoController {

  @GetMapping("mercadop")
  public String getPago() throws MPException, MPApiException{
    MercadoPagoConfig.setAccessToken("APP_USR-5981347105150890-092712-6ffe62032547009db02754e3eaf165e2-2712237799");

            PreferenceBackUrlsRequest backUrls =

        PreferenceBackUrlsRequest.builder()
            .success("https://www.tu-sitio/success")
            .pending("https://www.tu-sitio/pending")
            .failure("https://www.tu-sitio/failure")
            .build();




        PreferenceItemRequest itemRequest =
          PreferenceItemRequest.builder()
              .id("1234")
              .title("Games")
              .description("PS5")
              .pictureUrl("http://picture.com/PS5")
              .categoryId("games")
              .quantity(2)
              .currencyId("ARS")
              .unitPrice(new BigDecimal("4000"))
              .build();

      List<PreferenceItemRequest> items = new ArrayList<>();

      items.add(itemRequest);
      PreferenceRequest preferenceRequest = PreferenceRequest.builder()
      .items(items).backUrls(backUrls).build();

      PreferenceClient client = new PreferenceClient();

      Preference preference = client.create(preferenceRequest);
      
      return preference.getSandboxInitPoint();
  }

}
