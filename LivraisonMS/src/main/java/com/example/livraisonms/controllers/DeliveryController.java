package com.example.livraisonms.controllers;
import com.example.livraisonms.entities.Delivery;
import com.example.livraisonms.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class DeliveryController {

    @Autowired
    DeliveryService ds;

    @PostMapping("/addDelivery")
    @ResponseBody
    public void addDelivery(@RequestBody Delivery d ) {

        Delivery addedDelivery = ds.addDelivery(d);

        String msg = "Votre livraison a été ajoutée avec succes, vous pouvez suivre votre livraison sur ce lien : ";


        //return addedDelivery;
    }



    @DeleteMapping("/deleteDelivery/{id}")
    @ResponseBody
    public void deleteDelivery(@PathVariable("id") Long idDelivery) {
        ds.deleteDelivery(idDelivery);
    }


    @PutMapping("/enAttenteDelivery/{id}")
    @ResponseBody
    public void enAttenteDelivery(@PathVariable("id") Long idDelivery) {
        ds.marquerEnAttente(idDelivery);
    }


    @PutMapping("/enCoursDelivery/{id}")
    @ResponseBody
    public void enCoursDelivery(@PathVariable("id") Long idDelivery) {
        ds.marquerEnCours(idDelivery);

    }


    @PutMapping("/doneDelivery/{id}")
    @ResponseBody
    public void doneDelivery(@PathVariable("id") Long idDelivery) {
        ds.marquerDone(idDelivery);
    }

    @GetMapping("/getCurrentDeliveriesForDeliveryMan/{idDeliveryMan}")
    @ResponseBody
    public List<Delivery> getCurrentDeliveriesForDeliveryMan(@PathVariable("idDeliveryMan") Long id){
        return ds.getCurrentDeliveriesForDeliveryMan(id);
    }


    @GetMapping("/getCurrentDeliveries")
    @ResponseBody
    public List<Delivery> getCurrentDeliveries(){
        return ds.getCurrentDeliveries();
    }


    @GetMapping("/getHistoryDeliveries")
    @ResponseBody
    public List<Delivery> getHistoryDeliveries(){
        return ds.getHistoryDeliveries();
    }

    @GetMapping("/countHistoryDeliveries")
    @ResponseBody
    public int countHistoryDeliveries(){
        return ds.getHistoryDeliveries().size();
    }





    @GetMapping("/getTempsAttenteDelivery/{idDelivery}")
    @ResponseBody
    public long getTempsAttenteDelivery(@PathVariable("idDelivery") Long idDelivery){
        return ds.getTempsAttenteDelivery(idDelivery);
    }

    @GetMapping("/getTempsAttenteMoyen")
    @ResponseBody
    public long getTempsAttenteMoyen(){
        return ds.getTempsAttenteMoyen();
    }

    @GetMapping("/getDeliveryStatus/{idDelivery}")
    @ResponseBody
    public String getDeliveryStatus(@PathVariable("idDelivery") Long idDelivery){
        return ds.getDeliveryStatus(idDelivery);
    }

/*

    @GetMapping("/getTempsAttenteMoyenParLivreur")
    @ResponseBody
    public List<Object> getTempsAttenteMoyenParLivreur(){
        return ds.getTempsAttenteMoyenParLivreur();
    }
*/






}