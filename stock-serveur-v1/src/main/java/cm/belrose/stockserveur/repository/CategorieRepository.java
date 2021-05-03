package cm.belrose.stockserveur.repository;

import cm.belrose.stockserveur.model.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Ngnawen Samuel
 *
 * @since  07/11/2020
 *
 * RevisionRepository<Categorie,Long,Long> allows 4 different methods to get versioning information
 * belongs to the Categorie entity class.
 *
 */

@Repository
public interface CategorieRepository  extends RevisionRepository<Categorie, Long, Long>, JpaRepository<Categorie, Long> {

    Boolean existsByNom(String nom);

    void deleteById(Long id);

    void deleteAll();

   // returns all Categories which "nom" contains input "nom"
    List<Categorie> findByNomContaining(String nom);

    Categorie findByNom(String nom);

    Categorie findByCode(String code);


    /*@Query("select cat from Categorie cat where cat.nom like :x")
    Page<Categorie> chercher(@Param("x") String keyword, Pageable pageable);*/
}
