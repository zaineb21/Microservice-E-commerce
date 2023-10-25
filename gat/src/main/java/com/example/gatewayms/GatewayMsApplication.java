package com.example.gatewayms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayMsApplication.class, args);
    }
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("produit-ms", r->r.path("/produits/**")
                        .uri("http://localhost:8082/"))
                .route("commande-s", r->r.path("/commande/**")
                        .uri("http://localhost:8081/"))
                .route("reclamation-ms", r->r.path("/reclamations/**")
                        .uri("http://localhost:8089/"))
                .route("livraison-ms", r->r.path("/deliveries/**")
                        .uri("http://localhost:9090/"))
                .build();
    }
}


