/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package controller;

import java.time.LocalDate;
import java.util.List;

import model.Livre;
import model.Membre;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 2417011
 */

    
    class BibliothequeServiceTest {
    private static BibliothequeService service;

    @BeforeAll
    static void setup() {
        service = new BibliothequeService();
    }

    @Test
    
    void testAjouterLivre() {
        // Ajouter un livre
        service.ajouterLivre("Test Livre", "Auteur Test", "1234567890");

        // Vérifier si le livre a été ajouté en listant tous les livres
        List<Livre> livres = service.listerLivres();
        boolean livreAjoute = livres.stream().anyMatch(l -> l.getTitre().equals("Test Livre"));

        assertTrue(livreAjoute, "Le livre doit être ajouté à la base de données.");
    }

    @Test
    
    void testEmprunterLivre() {
        // Ajouter un livre et un membre avant de tester l'emprunt
        service.ajouterLivre("Test Emprunt Livre", "Auteur Test", "111122223333");
        int livreId = service.listerLivres().stream()
                .filter(l -> l.getTitre().equals("Test Emprunt Livre"))
                .findFirst()
                .map(Livre::getId)
                .orElseThrow(() -> new AssertionError("Le livre n'a pas été trouvé !"));

        Membre membre = new Membre(1, "Membre Test", "test@email.com");
        service.ajouterMembre(membre.getNom(), membre.getEmail());
        int membreId = service.listerMembres().stream()
                .filter(m -> m.getNom().equals("Membre Test"))
                .findFirst()
                .map(Membre::getId)
                .orElseThrow(() -> new AssertionError("Le membre n'a pas été trouvé !"));

        // Emprunter le livre
        service.emprunterLivre(livreId, membreId, LocalDate.now(), LocalDate.now().plusDays(14));

        // Vérifier si l'emprunt est enregistré
        boolean empruntAjoute = service.listerEmprunts().stream()
                .anyMatch(e -> e.getLivre().getId() == livreId && e.getMembre().getId() == membreId);

        assertTrue(empruntAjoute, "L'emprunt doit être enregistré.");
    }
    
}
