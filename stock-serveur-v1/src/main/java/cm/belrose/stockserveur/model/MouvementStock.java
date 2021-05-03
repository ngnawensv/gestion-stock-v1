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
public class MouvementStock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @ManyToOne
    private Article article;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
