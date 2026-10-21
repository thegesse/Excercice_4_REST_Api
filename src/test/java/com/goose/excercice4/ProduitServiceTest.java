package com.goose.excercice4;

import com.goose.excercice4.model.Produit;
import com.goose.excercice4.repository.ProduitRepository;
import com.goose.excercice4.service.NotificationService;
import com.goose.excercice4.service.ProduitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ProduitServiceTest {
    @Mock // faux repository, piloté par le test
    private ProduitRepository produitRepository;
    @InjectMocks // vrai service, construit avec le mock
    private ProduitService produitService;

    @Mock
    private NotificationService notificationService;

    @Test
    void recupererParId_retourneLeProduit_quandIlExiste() {
        Produit produit = new Produit();
        produit.setId(1L);
        produit.setNom("Clavier");
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
        Optional<Produit> resultat = produitService.recupererParId(1L);
        assertThat(resultat).isPresent();
        assertThat(resultat.get().getNom()).isEqualTo("Clavier");
    }
    @Test
    void supprimer_retourneFalse_etNeSupprimeRien_siLeProduitNExistePas() {
        when(produitRepository.existsById(99L)).thenReturn(false);
        boolean resultat = produitService.supprimer(99L);
        assertThat(resultat).isFalse();
        verify(produitRepository, never()).deleteById(any());
    }
    @Test
    void creer_DoitMettreIdANullAvantSauvegarde() {
        Produit produit = new Produit();
        produit.setId(1L);
        produit.setNom("Clavier");
        produitService.creer(produit);
        ArgumentCaptor<Produit> argumentCaptor = ArgumentCaptor.forClass(Produit.class);
        verify(produitRepository).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getId()).isNull();
    }

    @Test
    void creer_DoitNotifierUneSeuleFois() {
        Produit produit = new Produit();
        produit.setId(1L);
        produit.setNom("Clavier");
        produitService.creer(produit);

        verify(notificationService, times(1)).notifier("stock", "Nouveau produit : Clavier");
        //times(1) == verifie nombre de fois la methode a ete appeler
        //Ici verifie que la method a bien ete appeller 1 seul fois
    }



}
