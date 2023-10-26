package com.example.reclamationms.entities.Openfeign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produit {
    Long produitId;
    ProduitCategory produit_category;
    String produit_name;
    Float produit_price;
    Date publication_date;
    Boolean availability;
    String produitPicture;
    Integer stock;
    String email_user;
}
