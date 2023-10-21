package com.example.livraisonms.services;


import com.example.livraisonms.entities.Delivery;
import com.example.livraisonms.entities.DeliveryStatus;
import com.example.livraisonms.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;


@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository dr;



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


    public void marquerEnCours(Long idDelivery) {
        Delivery d = (Delivery)dr.findById(idDelivery).orElse(null);
        d.setEtat(DeliveryStatus.enCours);
        dr.save(d);
    }




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
/*

    public List<Object> getTempsAttenteMoyenParLivreur()
    {
        return dr.getAttenteMoyenParLivreur();
    }
*/



}
