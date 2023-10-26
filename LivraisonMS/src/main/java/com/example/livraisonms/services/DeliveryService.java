package com.example.livraisonms.services;


import com.example.livraisonms.Openfeign.CommandeOpenfeign;
import com.example.livraisonms.entities.Delivery;
import com.example.livraisonms.entities.DeliveryStatus;
import com.example.livraisonms.entities.Openfeign.Commande;
import com.example.livraisonms.repository.DeliveryRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;


@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository dr;
    private final CommandeOpenfeign co  ;

    public DeliveryService(CommandeOpenfeign co) {
        this.co = co;
    }


    public List<Commande> getcommandeAll() {
        return co.getAllcommande();
    }
    public Delivery addDelivery(Delivery d) {

        d.setEtat(DeliveryStatus.enAttente);
        dr.save(d);
        return d;

    }

    public void deleteDelivery(Long idDelivery) {
        dr.deleteById(idDelivery);


    }

    public void marquerEnAttente(Long idDelivery) {
        Delivery d = (Delivery)dr.findById(idDelivery).orElse(null);
        d.setEtat(DeliveryStatus.enAttente);
        dr.save(d);

    }


   /* public void marquerEnCours(Long idDelivery) {
        Delivery d = (Delivery)dr.findById(idDelivery).orElse(null);
        d.setEtat(DeliveryStatus.enCours);
        dr.save(d);
    }*/




    public void marquerDone(Long idDelivery) {
        Delivery d = (Delivery)dr.findById(idDelivery).orElse(null);
        Date date = new Date();
        d.setDeliveryDate(date);
        d.setEtat(DeliveryStatus.done);
        dr.save(d);
    }

    public String getDeliveryStatus(Long idDelivery ) {
        Delivery d = (Delivery)dr.findById(idDelivery).orElse(null);
        return d.getEtat().toString();


    }


    public List<Delivery> getCurrentDeliveriesForDeliveryMan(Long idDeliveryMan)
    {
        return dr.getCurrentDeliveriesForDeliveryMan(DeliveryStatus.done,idDeliveryMan);
    }

    public List<Delivery> getCurrentDeliveries()
    {
        return dr.getCurrentDeliveries(DeliveryStatus.done);
    }

    public List<Delivery> getHistoryDeliveries()
    {
        return dr.getHistoryDeliveries(DeliveryStatus.done);
    }

    public long getTempsAttenteDelivery(Long idDelivery)
    {
        Delivery d = (Delivery)dr.findById(idDelivery).orElse(null);
        long duree = 3;

        return duree;
    }

    public long getTempsAttenteMoyen()
    {
        long dureeTotale=0;
        for(Delivery d: getHistoryDeliveries() )
        {
            dureeTotale+= 3;
        }
        long moyenne=dureeTotale/getHistoryDeliveries().size();

        return moyenne;
    }
    private EmailService ES;
    public void sendEmail() {
        ES.sendSimpleEmail("Admin@gmail.com", "Reclamation", "Votre commande est en cours de traitement.");
    }
    public void marquerEnCours(Long idDelivery) {
        Delivery d = (Delivery) dr.findById(idDelivery).orElse(null);
        if (d != null) {
            d.setEtat(DeliveryStatus.enCours);
            dr.save(d);

          /*  // Send an SMS using Twilio
            String recipientPhoneNumber = "+21623760098"; // Replace with the recipient's phone number
            String message = "Votre livraison est en cours de traitement"; // Replace with your desired message

            // Initialize Twilio
            Twilio.init("AC257c7b13f2c3fa5ff63dc9f4484d9184", "dd823cb4e56a88f6d88ae905d68387aa");

            // Send the SMS
            Message twilioMessage = Message.creator(
                            new com.twilio.type.PhoneNumber(recipientPhoneNumber),
                            new com.twilio.type.PhoneNumber("+21650403261"), // Replace with your Twilio phone number
                            message)
                    .create();

            System.out.println("Twilio Message SID: " + twilioMessage.getSid());*/

        }
/*

    public List<Object> getTempsAttenteMoyenParLivreur()
    {
        return dr.getAttenteMoyenParLivreur();
    }
*/


    }
}
