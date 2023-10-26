package com.example.commandems.Openfeign;

import com.example.commandems.Entities.Openfeign.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "produit-ms", url = "http://localhost:8082/produits")
public interface ProduitOpenfeign {

    @GetMapping("/produitsbyId")
    List<Produit> getproduitAllById(@RequestParam("produitId") List<Long> produitId);
}