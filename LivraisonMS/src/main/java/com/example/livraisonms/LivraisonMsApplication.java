package com.example.livraisonms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LivraisonMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivraisonMsApplication.class, args);
	}

}
