package com.example.livraisonms.Openfeign;

import com.example.livraisonms.entities.Openfeign.Commande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="commande-s",url = "localhost:8090/commande/commande")
public interface CommandeOpenfeign {
    @GetMapping("/commandes")
    List<Commande> getAllcommande();
}
