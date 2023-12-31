package com.example.commandems.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString


public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @CollectionTable(name = "liste_produit")
    private List<Long> listeProduits; // Vous devez créer l'entité Produit séparément.
    @ElementCollection
    @CollectionTable(name = "name_produit")
    private List<String> nameProduits=new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "price_produit")
    private List<Float> priceProduits=new ArrayList<>();
    private double sommeTotale;
    private ModePaiement modePaiement;
    private String emailuser;
}
