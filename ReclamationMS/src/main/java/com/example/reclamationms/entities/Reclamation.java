package com.example.reclamationms.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
public class Reclamation  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = true)
    private Long id_produit ;
    @Column(nullable = false)
    private String email_user ;
    @Column(nullable = true)
    private String name_produit ;
    @Column(nullable = true)
    private Long id_livreur ;
    private ReclamationType type ;
    @Column(nullable = false)
    private String objet ;
    @Column(nullable = false)
    private String description ;
}
