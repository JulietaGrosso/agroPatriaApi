package com.agropatriaapp.agropatriaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mercadopago.MercadoPagoConfig;

@SpringBootApplication
public class AgropatriaapiApplication {

	public static void main(String[] args) {
		MercadoPagoConfig.setAccessToken("APP_USR-2073197683572192-092712-969d9e0527c2e62f5e1186b108975c1c-570815386");

		SpringApplication.run(AgropatriaapiApplication.class, args);
	}

	

}
