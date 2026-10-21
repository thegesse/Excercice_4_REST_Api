package com.goose.excercice4.repository;

import com.goose.excercice4.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByNomContainingIgnoreCase(String nom);
    List<Produit> findByPrixLessThanEqual(Double prix);
}

