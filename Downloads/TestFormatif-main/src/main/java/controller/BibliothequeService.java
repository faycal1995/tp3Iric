/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.DatabaseManager;
import java.time.LocalDate;
import java.util.List;
import model.Emprunt;
import model.Livre;
import model.Membre;

/**
 *
 * @author 2417011
 */
public class BibliothequeService {
    private DatabaseManager dbManager = DatabaseManager.getInstance();

    public void ajouterLivre(String titre, String auteur, String isbn) {
        Livre livre = new Livre( titre, auteur, isbn);
        dbManager.ajouterLivre(livre);
    }

    public List<Livre> listerLivres() {
        return dbManager.listerLivres();
    }

    public Livre trouverLivre(int id) {
        return dbManager.trouverLivreParId(id);
    }

    public void mettreAJourLivre(int id,String titre, String auteur, String isbn) {
        Livre livre = new Livre(id,titre, auteur, isbn);
        dbManager.mettreAJourLivre(livre);
    }

    public void supprimerLivre(int id) {
        dbManager.supprimerLivre(id);
    }

    
    
    public void ajouterMembre(String nom, String email) {
        Membre membre = new Membre(0, nom, email);
        dbManager.ajouterMembre(membre);
    }

    public List<Membre> listerMembres() {
        return dbManager.listerMembres();
    }

    public Membre trouverMembre(int id) {
        return dbManager.trouverMembreParId(id);
    }

    public void supprimerMembre(int id) {
        dbManager.supprimerMembre(id);
    }

    public void emprunterLivre(int livreId, int membreId, LocalDate dateEmprunt, LocalDate dateRetour) {
        Livre livre = dbManager.trouverLivreParId(livreId);
        Membre membre = dbManager.trouverMembreParId(membreId);
        
        if (livre != null && membre != null) {
            Emprunt emprunt = new Emprunt(0, livre, membre, dateEmprunt, dateRetour);
            dbManager.ajouterEmprunt(emprunt);
        } else {
            System.out.println(" Livre ou Membre introuvable !");
        }
    }

    public List<Emprunt> listerEmprunts() {
        return dbManager.listerEmprunts();
    }

    public void supprimerEmprunt(int id) {
        dbManager.supprimerEmprunt(id);
    }
}
