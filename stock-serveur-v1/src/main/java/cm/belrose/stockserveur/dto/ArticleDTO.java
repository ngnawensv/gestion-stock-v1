package cm.belrose.stockserveur.dto;

import cm.belrose.stockserveur.model.Categorie;

import java.util.List;
import java.util.Set;

public class ArticleDTO {
    private String nom;
    private double prixAchat;
    private double prixVente;
    private double quantite;
    private Set<Categorie> listOfCategories;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Set<Categorie> getListOfCategories() {
        return listOfCategories;
    }

    public void setListOfCategories(Set<Categorie> listOfCategories) {
        this.listOfCategories = listOfCategories;
    }
}
