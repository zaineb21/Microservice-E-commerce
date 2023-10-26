package com.example.reclamationms.controller;

import com.example.reclamationms.entities.Openfeign.Produit;
import com.example.reclamationms.entities.Reclamation;
import com.example.reclamationms.service.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import java.util.List;
@RestController
@RequestMapping("/reclamations")
public class ReclamationController {
    @Autowired
    ReclamationService rs;
    @PostMapping("/addReclamationProduit")
    @ResponseBody
    public Reclamation addReclamationProduit(@RequestBody Reclamation reclamation, @RequestParam(name = "token") String token)
    {
        System.out.println(token);
        String tokensend = token;
        String jwtToken = tokensend.substring(7);
        System.out.println( generateToken(jwtToken));
        reclamation.setEmail_user(generateToken(jwtToken));
        Long produitid = reclamation.getId_produit();
        Produit produit = rs.getProduitById(produitid).getBody();
        System.out.println(produit.getProduit_name());
        reclamation.setName_produit(produit.getProduit_name());
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
    @GetMapping("/getReclamationsByLivreur/{idLivreur}")
    @ResponseBody
    public List<Reclamation> getReclamationsByLivreur(@PathVariable("idlivreur") Long idLivreur) {
        return rs.getReclamationsByLivreur(idLivreur);

    }
    //decoder token
    public String generateToken(String token) {


        try {
            Claims claims =  Jwts.parser().setSigningKey("amine".getBytes()).parseClaimsJws(token).getBody();
            System.out.println(claims.toString());
            String id = claims.get("id", String.class);
            boolean isAdmin = claims.get("isAdmin", Boolean.class);
            return id ;
        } catch (SignatureException e) {
            // La signature du jeton n'est pas valide
            return "Signature de jeton non valide";
        } catch (Exception e) {
            // Une autre exception s'est produite, par exemple, si le jeton est mal formé
            return "Jetons JWT invalides";
        }

    }
}
