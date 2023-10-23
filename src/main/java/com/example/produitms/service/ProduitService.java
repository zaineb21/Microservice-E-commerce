package com.example.produitms.service;

import com.example.produitms.entities.Produit;
import com.example.produitms.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduit() {
        return produitRepository.findAll();
    }

    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long produitId, Produit newProduit) {
        if (produitRepository.findById(produitId).isPresent()) {
            Produit existingProduit = produitRepository.findById(produitId).get();
            existingProduit.setProduit_name(newProduit.getProduit_name());
            existingProduit.setProduit_price(newProduit.getProduit_price());
            existingProduit.setStock(newProduit.getStock());
            existingProduit.setAvailability(newProduit.getAvailability());

            return produitRepository.save(existingProduit);
        } else
            return null;
    }

    public String deleteProduit(Long produitId) {
        if (produitRepository.findById(produitId).isPresent()) {
            produitRepository.deleteById(produitId);
            return "Deleted";
        } else
            return "Not yet";
    }
}
