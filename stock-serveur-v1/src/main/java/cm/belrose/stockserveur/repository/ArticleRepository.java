package cm.belrose.stockserveur.repository;

import cm.belrose.stockserveur.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 *@author  Ngnawen Samuel
 */

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long>{
    List<Article> findByNomContaining(String nom);
}
