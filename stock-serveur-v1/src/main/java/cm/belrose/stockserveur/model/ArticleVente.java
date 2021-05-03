package cm.belrose.stockserveur.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Le 09/11/2020
 *
 *@author  Ngnawen Samuel
 *
 */
@Entity
public class ArticleVente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantite;
    @ManyToOne
    private Article article;
    @ManyToOne
    private Vente vente;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }
}
