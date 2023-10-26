package com.example.livraisonms.entities.Openfeign;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Commande {

    private Long id;
    private List<Long> listeProduits; // Vous devez créer l'entité Produit séparément.
    private List<String> nameProduits=new ArrayList<>();
    private List<Float> priceProduits=new ArrayList<>();
    private double sommeTotale;
    private ModePaiement modePaiement;
    private String emailuser;
}
