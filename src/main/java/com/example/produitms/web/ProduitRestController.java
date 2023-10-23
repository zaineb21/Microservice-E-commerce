package com.example.produitms.web;

import com.example.produitms.entities.Produit;
import com.example.produitms.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits") // Updated request mapping
public class ProduitRestController {

    @Autowired
    private ProduitService produitService;

    @GetMapping(path = "/produits")
    public List<Produit> listProduit() {
        return produitService.getAllProduit();
    }

    @PostMapping("/add-produit")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        return new ResponseEntity<>(produitService.addProduit(produit), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produit> updateProduit(@PathVariable(value = "id") Long id, @RequestBody Produit produit) {
        return new ResponseEntity<>(produitService.updateProduit(id, produit), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProduit(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(produitService.deleteProduit(id), HttpStatus.OK);
    }
}
