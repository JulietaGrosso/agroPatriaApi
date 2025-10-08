package com.agropatriaapp.agropatriaapi.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agropatriaapp.agropatriaapi.model.Pagos;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.WebHookLogs;
import com.agropatriaapp.agropatriaapi.repositorios.PagoRepositorio;
import com.agropatriaapp.agropatriaapi.repositorios.WebhookLogsRepository;
import com.agropatriaapp.agropatriaapi.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.merchantorder.MerchantOrderClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.merchantorder.MerchantOrder;
import com.mercadopago.resources.preference.Preference;

@Service
public class MercadoPagoService {

  @Autowired
  WebhookLogsRepository webhookLogsRepository;

  @Autowired
  PagoRepositorio pagoRepositorio;


  public void checkPayment(String paymentUuid, Long merchantId) throws MPException, MPApiException{
    MerchantOrderClient merchantOrderClient = new MerchantOrderClient();
    MerchantOrder order = merchantOrderClient.get(merchantId);
    Optional<Pagos> pagoOpt = pagoRepositorio.findByUuidPago(paymentUuid);
    if ( pagoOpt.isPresent() && order.getExternalReference().equals(paymentUuid)) {
      Pagos pago = pagoOpt.get();
      String paymentStatus = order.getOrderStatus();
      pago.setStatus(paymentStatus);
      if ( paymentStatus.equals("paid") ) {
        Date fechaPago = new Date();
        Date fechaExpiracion = Utils.agregarDias(fechaPago, pago.getDuracion());
        pago.setFechaPago( fechaPago );
        pago.setFechaExpiracion( fechaExpiracion );
      }

      pagoRepositorio.save(pago);
    }
    System.out.println(order);
  }

  public Response paymentNotification(String paymentUuid, JsonNode payload) throws MPException, MPApiException{
    String json = payload.toPrettyString();
    WebHookLogs log = WebHookLogs.builder().setLog(json).setPaymentUuid(paymentUuid);
    webhookLogsRepository.save(log);

    JsonNode topic = payload.get("topic");

    if ( topic.isTextual() && topic.asText().equals("merchant_order")) {
      JsonNode resource = payload.get("resource");
      String link = resource.asText();
      String[] linkParts = link.split("/");
      long id = Long.parseLong(linkParts[linkParts.length - 1]);
      checkPayment(paymentUuid, id);
    }

    return Response.builder().message("OK").success(true).build();
  }

  public String generatePaymentUrl(String paymentId, String title, int price) throws MPException, MPApiException {

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
        .notificationUrl("http://104.251.222.158:1400/mercadopago/notification/" + paymentId) // No borrar ni descomentar.
        .backUrls(backUrls)
        .build();

    PreferenceClient client = new PreferenceClient();

    Preference preference = client.create(preferenceRequest);

    return preference.getInitPoint();
  }
}
