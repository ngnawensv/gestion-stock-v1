package cm.belrose.stockserveur.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * Le 09/11/2020
 *
 *@author  Ngnawen Samuel
 *
 */
@Entity
public class Vente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @OneToMany(mappedBy = "vente")
    private Collection<ArticleVente> articleVentes;

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<ArticleVente> getArticleVentes() {
        return articleVentes;
    }

    public void setArticleVentes(Collection<ArticleVente> articleVentes) {
        this.articleVentes = articleVentes;
    }
}
