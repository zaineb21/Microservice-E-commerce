package com.example.commandems.Controller;


import com.example.commandems.Entities.Commande;
import com.example.commandems.Entities.Openfeign.Produit;
import com.example.commandems.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import java.util.List;

@RestController
@RequestMapping("/commande")
public class CommandeRestController {
    @Autowired
    private CommandeService commandeService;

    @GetMapping(path = "/commandes")
    public List<Commande> listCommande() {
        return commandeService.getAllCommande();
    }
    @GetMapping(path = "/commandes/getproduits")
    public List<Produit> getAllProduitbyId(@RequestParam(value="produitId") List<Long> listproduits) {
        return commandeService.getAllProduitbyId(listproduits);
    }
    @PostMapping("/add-commande")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande,@RequestParam(name = "token") String token) {
        //token payload
        System.out.println(token);
        String tokensend = token;
        String jwtToken = tokensend.substring(7);
        //System.out.println( generateToken(jwtToken));
        commande.setEmailuser(generateToken(jwtToken));//ajout emailuser from token
        System.out.println(commande.getNameProduits()+""+commande.getListeProduits());
        List<Produit> list_produit= commandeService.getAllProduitbyId(commande.getListeProduits());
        System.out.println(list_produit);
        double somme = 0.0;
        for (Produit produit : list_produit) {
            commande.getNameProduits().add(produit.getProduit_name());//add produit name
            commande.getPriceProduits().add(produit.getProduit_price());//add price produit
            somme += produit.getProduit_price();//montant totale
        }
        commande.setSommeTotale(somme);//ajouter somme
        return new ResponseEntity<>(commandeService.addCommande(commande), HttpStatus.OK);
    }
  /*  @PostMapping("/add-commande")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        return new ResponseEntity<>(commandeService.addCommande(commande), HttpStatus.OK);
    }*/

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Commande> updateCommande(@PathVariable(value = "id") Long id, @RequestBody Commande commande){
        return new ResponseEntity<>(commandeService.updateCommande(id, commande), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCommande(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(commandeService.deleteCommande(id), HttpStatus.OK);
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
