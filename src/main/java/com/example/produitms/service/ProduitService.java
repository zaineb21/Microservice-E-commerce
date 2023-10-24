package com.example.produitms.service;

import com.example.produitms.entities.Produit;
import com.example.produitms.entities.ProduitCategory;
import com.example.produitms.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduit() {
        return produitRepository.findAll();
    }

    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long produitId, Produit newProduit) {
        if (produitRepository.findById(produitId).isPresent()) {
            Produit existingProduit = produitRepository.findById(produitId).get();
            existingProduit.setProduit_name(newProduit.getProduit_name());
            existingProduit.setProduit_price(newProduit.getProduit_price());
            existingProduit.setStock(newProduit.getStock());
            existingProduit.setAvailability(newProduit.getAvailability());

            return produitRepository.save(existingProduit);
        } else
            return null;
    }

    public String deleteProduit(Long produitId) {
        if (produitRepository.findById(produitId).isPresent()) {
            produitRepository.deleteById(produitId);
            return "Deleted";
        } else
            return "Not yet";
    }
    @Scheduled(cron = "*/30 * * * * *")
    void nbreProduitParCat() {
        // CLOTHING,HEALTH_AND_BEAUTY,FOOD_AND_BEVERAGES,HOME_AND_GARDEN,SPORTS_AND_FITNESS
        int nbrCLOTHING = produitRepository.getProduitByProduitCategory(ProduitCategory.CLOTHING);
        int nbrHEALTH_AND_BEAUTY = produitRepository.getProduitByProduitCategory(ProduitCategory.HEALTH_AND_BEAUTY);
        int nbrFOOD_AND_BEVERAGES = produitRepository.getProduitByProduitCategory(ProduitCategory.FOOD_AND_BEVERAGES);
        int nbrHOME_AND_GARDEN =produitRepository.getProduitByProduitCategory(ProduitCategory.HOME_AND_GARDEN);
        int nbrSPORTS_AND_FITNESS = produitRepository.getProduitByProduitCategory(ProduitCategory.SPORTS_AND_FITNESS);
        System.out.println("CLOTHING: " + nbrCLOTHING);
        System.out.println("HEALTH_AND_BEAUTY: " + nbrHEALTH_AND_BEAUTY);
        System.out.println("FOOD_AND_BEVERAGES : " + nbrFOOD_AND_BEVERAGES);
        System.out.println("HOME_AND_GARDEN : " + nbrHOME_AND_GARDEN);
        System.out.println("SPORTS_AND_FITNESS : " + nbrSPORTS_AND_FITNESS);

    }
    @Value("${produit.image.upload.directory}")
    private String uploadDirectory;
    public void uploadProductImage(Long produitId, MultipartFile file) {
        Produit produit = produitRepository.findProduitByproduitId(produitId);
        if (produit != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                Path uploadPath = Paths.get(uploadDirectory);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
                produit.setProduitPicture(fileName);
                produitRepository.save(produit);
            } catch (IOException ex) {
                throw new RuntimeException("Failed to store file " + fileName, ex);
            }
        }
    }

    public List<String> getAscPrice() {
        return produitRepository.AscproduitPrice();
    }


    public List<String> getDecPrice() {
        return produitRepository.DesproduitPrice();
    }

}
