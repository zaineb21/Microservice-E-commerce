package com.example.commandems;

import com.example.commandems.Entities.Commande;
import com.example.commandems.Repository.CommandeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient

public class CommandeMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommandeMsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CommandeRepository commandeRepository){
        return args ->{
            commandeRepository.save(new Commande(null ,2,"cheque"));
            commandeRepository.save(new Commande(null , 10,"virement"));
            commandeRepository.findAll().forEach(fr->{
                System.out.println(fr.getSommeTotale());
                System.out.println(fr.getModePaiement());
            });
        };
    }

}