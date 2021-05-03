package cm.belrose.stockserveur.repository;

import cm.belrose.stockserveur.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *@author  Ngnawen Samuel
 */

@RepositoryRestResource
public interface VenteRepository extends JpaRepository<Vente, Long> {
}
