package com.example.commandems.Repository;


import com.example.commandems.Entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande,Long> {}