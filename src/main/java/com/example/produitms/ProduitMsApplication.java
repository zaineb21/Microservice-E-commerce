package com.example.produitms;

import com.example.produitms.entities.Produit;
import com.example.produitms.entities.ProduitCategory;
import com.example.produitms.repositories.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
//@EnableWebFluxSecurity
public class ProduitMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduitMsApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProduitRepository produitRepository) {
        return args -> {
            produitRepository.save(new Produit(null, ProduitCategory.CLOTHING, "test1", 2F, new Date(), true, "pic", 1));
            produitRepository.save(new Produit(null, ProduitCategory.HEALTH_AND_BEAUTY, "test2", 2F, new Date(), true, "pic2", 1));
            produitRepository.findAll().forEach(pr -> {
                System.out.println(pr.getProduit_category());
                System.out.println(pr.getProduit_price());
            });
        };
    }
}
