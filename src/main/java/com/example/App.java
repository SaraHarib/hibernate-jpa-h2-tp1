package com.example;

import com.example.model.Produit;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class App {

    public static void main(String[] args) {

        // Démarrage de la console web H2 pour pouvoir consulter la base
        try {
            Server.createWebServer("-web", "-webPort", "8082").start();
            System.out.println("Console H2 disponible sur : http://localhost:8082");
        } catch (Exception e) {
            System.out.println("Erreur lors du démarrage de la console H2");
            e.printStackTrace();
        }

        // Création de l'EntityManagerFactory à partir de persistence.xml
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hibernate-demo");

        // Ajout de quelques produits dans la base
        insererProduits(emf);

        // Lecture et affichage des produits enregistrés
        lireProduits(emf);

        // Fermeture de l'EntityManagerFactory
        emf.close();
    }

    private static void insererProduits(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();

        try {
            // Début de la transaction avant l'insertion
            em.getTransaction().begin();

            // Création de quelques produits pour tester l'application
            Produit p1 = new Produit(
                    "Laptop",
                    "PC-001",
                    "Informatique",
                    new BigDecimal("999.99"),
                    10
            );

            Produit p2 = new Produit(
                    "Smartphone",
                    "TEL-002",
                    "Téléphonie",
                    new BigDecimal("499.99"),
                    20
            );

            Produit p3 = new Produit(
                    "Tablette",
                    "TAB-003",
                    "Informatique",
                    new BigDecimal("299.99"),
                    15
            );

            // Hibernate va enregistrer ces objets dans la table Produit
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);

            // Validation des modifications
            em.getTransaction().commit();

            System.out.println("Produits insérés avec succès !");

        } catch (Exception e) {

            // En cas de problème, on annule la transaction
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Une erreur est survenue pendant l'insertion.");
            e.printStackTrace();

        } finally {
            // L'EntityManager doit être fermé après utilisation
            em.close();
        }
    }

    private static void lireProduits(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();

        try {
            // Requête JPQL pour récupérer tous les produits
            List<Produit> produits = em.createQuery(
                    "SELECT p FROM Produit p",
                    Produit.class
            ).getResultList();

            System.out.println("\nListe des produits :");

            // Affichage des produits trouvés
            for (Produit produit : produits) {
                System.out.println(produit);
            }

            // Recherche d'un produit grâce à sa clé primaire
            System.out.println("\nRecherche du produit avec ID=2 :");

            Produit produit = em.find(Produit.class, 2L);

            if (produit != null) {
                System.out.println(produit);
            } else {
                System.out.println("Produit non trouvé");
            }

        } finally {
            em.close();
        }
    }
}