package com.example.produitms.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produitId;

    @Enumerated(EnumType.STRING)
    @Column(name = "produitCategory")
    ProduitCategory produit_category;
    String produit_name;
    Float produit_price;
    @Temporal(TemporalType.DATE)
    Date publication_date;
    Boolean availability;
    String produitPicture;
    Integer stock;
}
