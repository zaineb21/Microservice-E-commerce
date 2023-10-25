package com.example.reclamationms.service;

import com.example.reclamationms.entities.Reclamation;
import com.example.reclamationms.entities.ReclamationType;
import com.example.reclamationms.repository.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReclamationService {
    @Autowired
    ReclamationRepository rr;

    public Reclamation addReclamationlivreur(Reclamation reclamation) {
        reclamation.setId_produit(null);
        reclamation.setType(ReclamationType.Livreur);
        return rr.save(reclamation);
    }
    public Reclamation addReclamationProduit(Reclamation reclamation) {
        reclamation.setId_livreur(null);
        reclamation.setType(ReclamationType.Produit);
        return rr.save(reclamation);
    }
    public List<Reclamation> getAllReclamations()
    {
        List<Reclamation> myList = (List<Reclamation>) rr.findAll();
        return myList;
    }
    public List<Reclamation> getReclamationsByProduit(Long idProduit)
    {
        List<Reclamation> first = (List<Reclamation>) rr.findAll();
        List<Reclamation> second = new ArrayList<Reclamation>();
        for(Reclamation rec : first)
        {
            if(rec.getId_produit()==idProduit)
            {

                second.add(rec);

            }
        }

        return second;
    }
    public List<Reclamation> getReclamationsByLivreur(Long idProduit)
    {
        List<Reclamation> first = (List<Reclamation>) rr.findAll();
        List<Reclamation> second = new ArrayList<Reclamation>();
        for(Reclamation rec : first)
        {
            if(rec.getId_produit()==idProduit)
            {

                second.add(rec);

            }
        }

        return second;
    }
}