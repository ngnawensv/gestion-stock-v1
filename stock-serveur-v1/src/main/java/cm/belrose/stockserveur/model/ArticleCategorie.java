package cm.belrose.stockserveur.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Le 09/11/2020
 *
 * @author Ngnawen Samuel
 *
 */
//@Entity
public class ArticleCategorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Article article;
    @ManyToOne
    private Categorie categorie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "ArticleCategorie{" +
                "id=" + id +
                ", article=" + article +
                ", categorie=" + categorie +
                '}';
    }
}
