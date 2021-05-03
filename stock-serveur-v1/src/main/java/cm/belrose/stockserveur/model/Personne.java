package cm.belrose.stockserveur.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * Le 09/11/2020
 *
 *@author  Ngnawen Samuel
 *
 */
@Entity
public class Personne implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nom;
    private String prenom;
    private String sexe;
    private Date dateNaissance;
    private String lieuNaissance;
    private String telephone1;
    private String telephone2;
    private String email;
    private String statut;
    private String residence;
    private String photo;
    @OneToMany(mappedBy = "personne")
    private Collection<CommandeClient> commandeClients;
    @OneToMany(mappedBy = "personne")
    private Collection<LivraisonFournisseur> livraisonFournisseurs;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Collection<CommandeClient> getCommandeClients() {
        return commandeClients;
    }

    public void setCommandeClients(Collection<CommandeClient> commandeClients) {
        this.commandeClients = commandeClients;
    }

    public Collection<LivraisonFournisseur> getLivraisonFournisseurs() {
        return livraisonFournisseurs;
    }

    public void setLivraisonFournisseurs(Collection<LivraisonFournisseur> livraisonFournisseurs) {
        this.livraisonFournisseurs = livraisonFournisseurs;
    }
}
