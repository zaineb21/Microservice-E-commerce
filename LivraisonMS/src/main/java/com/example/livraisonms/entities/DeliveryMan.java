package com.example.livraisonms.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;
import javax.persistence.EnumType;

@Entity
@DiscriminatorValue("DeliveryMan")
public class DeliveryMan  implements Serializable  {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy="livreur")
    private List<Delivery> livraisons;
    private String Name;
    @Enumerated(EnumType.STRING)
    private DeliveryManStatus etat;

    private long PhoneNumber;

    public DeliveryMan()
    {
        super();
    }







}