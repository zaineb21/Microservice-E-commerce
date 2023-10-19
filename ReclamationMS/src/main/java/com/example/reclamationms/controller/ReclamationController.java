package com.example.reclamationms.controller;

import com.example.reclamationms.entities.Reclamation;
import com.example.reclamationms.service.ReclamationService;
import com.example.reclamationms.entities.Reclamation;
import com.example.reclamationms.service.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ReclamationController {
    @Autowired
    ReclamationService rs;
    @PostMapping("/addReclamationProduit")
    @ResponseBody
    public Reclamation addReclamationProduit(@RequestBody Reclamation reclamation ) {
           return rs.addReclamationProduit(reclamation);

    }
    @PostMapping("/addReclamationLivreur")
    @ResponseBody
    public Reclamation addReclamationLivreur(@RequestBody Reclamation reclamation ) {
        return rs.addReclamationlivreur(reclamation);

    }
    @GetMapping("/getReclamationsByProduit/{idProduit}")
    @ResponseBody
    public List<Reclamation> getReclamationsByProduit(@PathVariable("idProduit") Long idProduit) {
        return rs.getReclamationsByProduit(idProduit);

    }
    @GetMapping("/getReclamationsByProduit/{idProduit}")
    @ResponseBody
    public List<Reclamation> getReclamationsByLivreur(@PathVariable("idlivreur") Long idLivreur) {
        return rs.getReclamationsByLivreur(idLivreur);

    }
}
