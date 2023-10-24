package com.example.produitms.repositories;

import com.example.produitms.entities.Produit;
import com.example.produitms.entities.ProduitCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
    @Query(" select count(c) from Produit c where c.produit_category=:ProduitCategory")
    int getProduitByProduitCategory(@Param("ProduitCategory") ProduitCategory ProduitCategory);
    Produit findProduitByproduitId(Long id);
    @Query("SELECT  produit_price FROM Produit order by produit_price ASC ")
    public List<String> AscproduitPrice();

    @Query("SELECT produit_price FROM Produit order by produit_price DESC ")
    public List<String> DesproduitPrice();
}
