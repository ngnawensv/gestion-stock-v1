package cm.belrose.stockserveur.model;

import cm.belrose.stockserveur.config.audit.Auditable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 *
 * Le 09/11/2020
 *
 *@author  Ngnawen Samuel
 *
 */
@Entity
@Audited
@Table(name = "roles")
public class Role extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() { }

    public Role(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

