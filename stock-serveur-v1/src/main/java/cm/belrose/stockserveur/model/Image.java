package cm.belrose.stockserveur.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * Le 09/11/2020
 *
 *@author  Ngnawen Samuel
 *
 */
@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String nature;
    @ManyToOne
    private Article article;

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

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nature='" + nature + '\'' +
                ", article=" + article +
                '}';
    }
}
