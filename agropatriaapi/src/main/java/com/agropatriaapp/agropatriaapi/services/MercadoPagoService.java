package com.agropatriaapp.agropatriaapi.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.WebHookLogs;
import com.agropatriaapp.agropatriaapi.repositorios.WebhookLogsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

@Service
public class MercadoPagoService {

  @Autowired
  WebhookLogsRepository webhookLogsRepository;

  public Response paymentNotification(JsonNode payload){
    String json = payload.toPrettyString();
    System.out.println("Webhook recibido: " + json);

    WebHookLogs log = new WebHookLogs();
    log.setLog(json);
    webhookLogsRepository.save(log);

    return Response.builder().message("OK").success(true).build();
  }

  public String generatePaymentUrl(String paymentId, String title, int price) throws MPException, MPApiException {
    MercadoPagoConfig.setAccessToken("APP_USR-5981347105150890-092712-6ffe62032547009db02754e3eaf165e2-2712237799");

    PreferenceBackUrlsRequest backUrls =

        PreferenceBackUrlsRequest.builder()
            .build();

    PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
        .title(title)
        .quantity(1)
        .currencyId("ARS")
        .unitPrice(new BigDecimal(price))
        .build();

    List<PreferenceItemRequest> items = new ArrayList<>();

    items.add(itemRequest);
    PreferenceRequest preferenceRequest = PreferenceRequest.builder()
        .items(items)
        .externalReference(paymentId)
        .notificationUrl("http://104.251.222.158:1400/mercadopago/notification")
        .backUrls(backUrls)
        .build();

    PreferenceClient client = new PreferenceClient();

    Preference preference = client.create(preferenceRequest);

    return preference.getSandboxInitPoint();
  }
}
