package com.doan.microservices.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {

		//allows for routing to services based on Eureka name

		//routes based on Eureka server
		//Example: http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR <- exhange service
		//Example: http://localhost:8765/currency-conversion/convert-currency/proxy/from/USD/to/INR/quantity/100

		//routes based on custom api gateway config
		//http://localhost:8765/currency-exchange/from/USD/to/AUS
		//http://localhost:8765/convert-currency/proxy/from/USD/to/AUS/quantity/100


		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
