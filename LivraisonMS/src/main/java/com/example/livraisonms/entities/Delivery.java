package com.example.livraisonms.entities;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;




@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Delivery implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DeliveryMan livreur;



    @JsonIgnore

    private Long commande;



    private float cout;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus etat;

    @Temporal(TemporalType.TIMESTAMP)
    private Date DeliveryDate;


    public Delivery()
    {

    }

    public Delivery(Long id)
    {
        this.id=id;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryMan getLivreur() {
        return livreur;
    }

    public void setLivreur(DeliveryMan livreur) {
        this.livreur = livreur;
    }


    public Long getCommande() {
        return commande;
    }

    public void setCommande(Long commande) {
        this.commande = commande;
    }


    public float getCout() {
        return cout;
    }

    public void setCout(float cout) {
        this.cout = cout;
    }

    public DeliveryStatus getEtat() {
        return etat;
    }

    public void setEtat(DeliveryStatus etat) {
        this.etat = etat;
    }

    public Date getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        DeliveryDate = deliveryDate;
    }



}
