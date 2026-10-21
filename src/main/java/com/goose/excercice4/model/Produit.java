package com.goose.excercice4.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "produits")
public class Produit {
    /**
     * Identifiant unique, généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nom du produit. Ne peut pas être vide.
     */
    @NotBlank(message = "Le nom du produit est obligatoire")
    @Column(nullable = false)
    private String nom;
    /**
     * Description du produit.
     */
    private String description;
    /**
     * Prix du produit. Doit être positif.
     */
    @Positive(message = "Le prix doit être positif")
    @Column(nullable = false)
    private Double prix;
    /**
     * Quantité en stock.
     */
    private Integer stock;
}