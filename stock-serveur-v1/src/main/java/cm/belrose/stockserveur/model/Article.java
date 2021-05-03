package cm.belrose.stockserveur.model;

import cm.belrose.stockserveur.config.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Le 09/11/2020
 *
 *@author  Ngnawen Samuel
 *
 */
@Audited
@Entity
@Table(name = "articles")
public class Article extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article")
    private Long id;
    @Column(name = "nom_article")
    private String nom;
    @Column(name = "prix_achat_article")
    private double prixAchat;
    @Column(name = "prix_vente_article")
    private double prixVente;
    @Column(name = "quantite_article")
    private double quantite;
    @ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(
            name = "article_categorie",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    //@NotAudited
    private Set<Categorie> listOfCategories;

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    @NotAudited
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIgnoreProperties("commandeClients")
    private Collection<CommandeClient> commandeClients;

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    @NotAudited
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<ArticleVente> articleVentes;
    @OneToMany (mappedBy = "article",cascade = CascadeType.ALL)
    @NotAudited
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Image> images;
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    @NotAudited
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<LivraisonFournisseur> livraisonFournisseurs;
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    @NotAudited
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<MouvementStock> mouvementStocks;

    public Article() {
    }

    public Article(String nom, double prixAchat, double prixVente, double quantite, Set<Categorie> listOfCategories) {
        this.nom = nom;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.quantite = quantite;
        this.listOfCategories = listOfCategories;
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

    public Collection<CommandeClient> getCommandeClients() {
        return commandeClients;
    }

    public void setCommandeClients(Collection<CommandeClient> commandeClients) {
        this.commandeClients = commandeClients;
    }

    public Set<Categorie> getListOfCategories() {
        return listOfCategories;
    }

    public void setListOfCategories(Set<Categorie> listOfCategories) {
        this.listOfCategories = listOfCategories;
    }

    public Collection<ArticleVente> getArticleVentes() {
        return articleVentes;
    }

    public void setArticleVentes(Collection<ArticleVente> articleVentes) {
        this.articleVentes = articleVentes;
    }

    public Collection<Image> getImages() {
        return images;
    }

    public void setImages(Collection<Image> images) {
        this.images = images;
    }

    public Collection<LivraisonFournisseur> getLivraisonFournisseurs() {
        return livraisonFournisseurs;
    }

    public void setLivraisonFournisseurs(Collection<LivraisonFournisseur> livraisonFournisseurs) {
        this.livraisonFournisseurs = livraisonFournisseurs;
    }

    public Collection<MouvementStock> getMouvementStocks() {
        return mouvementStocks;
    }

    public void setMouvementStocks(Collection<MouvementStock> mouvementStocks) {
        this.mouvementStocks = mouvementStocks;
    }

}
