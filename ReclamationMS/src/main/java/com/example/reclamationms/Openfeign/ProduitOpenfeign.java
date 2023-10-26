package com.example.reclamationms.Openfeign;

import com.example.reclamationms.entities.Openfeign.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "produit-ms", url = "http://localhost:8082/produits/")
public interface ProduitOpenfeign {
    @GetMapping("{id}")
    ResponseEntity<Produit> getproduitById(@PathVariable("id") Long id);
}
