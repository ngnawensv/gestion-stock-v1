package cm.belrose.stockserveur.model;
import cm.belrose.stockserveur.config.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

/**
 *
 *@author  Ngnawen Samuel
 * @since  09/11/2020 20:30:00
 *
 */
@Audited
@Entity
@Table(
        name = "categories",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code_categorie"),
                @UniqueConstraint(columnNames = "nom_categorie")
        }
)
public class Categorie extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Code of category must not be null")
    @Column(name = "code_categorie")
    private String code;

    @NotBlank(message = "Name of category must not be null")
    @Column(name = "nom_categorie")
    private String nom;

    @ManyToMany(mappedBy = "listOfCategories", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @NotAudited
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Article> listOfArticles=new ArrayList<>();

    public Categorie() {
    }

    public Categorie(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Article> getListOfArticles() {
        return listOfArticles;
    }

    public void setListOfArticles(List<Article> listOfArticles) {
        this.listOfArticles = listOfArticles;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
