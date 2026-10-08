package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Produit {

    // Identifiant unique généré automatiquement par la base de données
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Informations principales du produit
    private String nom;
    private String reference;
    private String categorie;
    private BigDecimal prix;
    private Integer quantiteStock;

    // Constructeur vide obligatoire pour JPA/Hibernate
    public Produit() {
    }

    // Constructeur utilisé pour créer facilement un nouveau produit
    public Produit(String nom, String reference, String categorie,BigDecimal prix, Integer quantiteStock) {
        this.nom = nom;
        this.reference = reference;
        this.categorie = categorie;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(Integer quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", reference='" + reference + '\'' +
                ", categorie='" + categorie + '\'' +
                ", prix=" + prix +
                ", quantiteStock=" + quantiteStock +
                '}';
    }
}