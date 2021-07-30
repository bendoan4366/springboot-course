package com.doan.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange")) // route any request with /currency-exchange/ to load balancer in Eureka
                .route(p -> p.path("/convert-currency/**").uri("lb://currency-conversion")) // route any request with /currency-conversion/ in route to load balancer in Eureka
                .build();
    }

}
