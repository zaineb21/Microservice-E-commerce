package com.example.commandems.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
   // private List<Produit> listeProduits; // Vous devez créer l'entité Produit séparément.
    private double sommeTotale;
    private String modePaiement;

}
