package com.agropatriaapp.agropatriaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mercadopago.MercadoPagoConfig;

@SpringBootApplication
public class AgropatriaapiApplication {

	public static void main(String[] args) {
		MercadoPagoConfig.setAccessToken("APP_USR-6663264986793431-101216-13479c871aa1d231db8594408d9dbcf3-385481306");

		SpringApplication.run(AgropatriaapiApplication.class, args);
	}

	

}
