package com.example.commandems.Controller;


import com.example.commandems.Entities.Commande;
import com.example.commandems.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add-commande")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        return new ResponseEntity<>(commandeService.addCommande(commande), HttpStatus.OK);
    }

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
}
