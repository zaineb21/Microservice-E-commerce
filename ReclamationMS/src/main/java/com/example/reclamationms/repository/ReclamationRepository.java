package com.example.reclamationms.repository;

import com.example.reclamationms.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository  extends JpaRepository<Reclamation, Long> {
}