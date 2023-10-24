package com.example.produitms.web;

import com.example.produitms.entities.Produit;
import com.example.produitms.repositories.ProduitRepository;
import com.example.produitms.service.ProduitService;
import com.example.produitms.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/produits") // Updated request mapping
public class ProduitRestController {

    @Autowired
    private ProduitService produitService;
    @Autowired
    private ProduitRepository produitRepository;

    public ProduitRestController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(path = "/produits")
    public List<Produit> listProduit() {
        return produitService.getAllProduit();
    }

    @PostMapping("/add-produit")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        return new ResponseEntity<>(produitService.addProduit(produit), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produit> updateProduit(@PathVariable(value = "id") Long id, @RequestBody Produit produit) {
        return new ResponseEntity<>(produitService.updateProduit(id, produit), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProduit(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(produitService.deleteProduit(id), HttpStatus.OK);
    }
    @PostMapping("/image/{id}/image")
    public ResponseEntity<String> uploadFurnitureImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        produitService.uploadProductImage(id, file);
        return ResponseEntity.ok("Image uploaded successfully");
    }
    @GetMapping(value = "getAscProductPrice")
    @ResponseBody
    public List<String> getAscProductPrice() {
        return produitService.getAscPrice();
    }


    @GetMapping(value = "getDecProductPrice")
    @ResponseBody
    public List<String> getDecProductPrice() {
        return produitService.getDecPrice();
    }
    private final QRCodeService qrCodeService;


    @GetMapping("/qr/{productId}")
    public void generateQRCode(@PathVariable Long productId, HttpServletResponse response) {
        BufferedImage image = qrCodeService.generateQRCodeForProduct(productId);

        if (image != null) {
            response.setContentType("image/png");
            try {
                ImageIO.write(image, "png", response.getOutputStream());
            } catch (IOException e) {
                // Gérer les erreurs d'écriture de l'image
                e.printStackTrace();
            }
        }
    }
}
