package cm.belrose.stockserveur.service;

import cm.belrose.stockserveur.dto.CategorieDto;
import cm.belrose.stockserveur.model.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Le 11/11/2020
 *
 * @author Ngnawen Samuel
 */
public interface CategorieService {

    Optional<Categorie> findById(Long id) throws Exception;

    List<Categorie> findAll() throws Exception;

   // Categorie save(CategorieDTO categorieDto) throws Exception;
    Categorie save(Categorie categorie) throws Exception;

    Categorie update(Categorie categorie) throws Exception;

   // void delete(Categorie categorie) throws Exception;

    void deleteById(Long id);

    void deleteAll();

    Boolean existsByNom(String nom);

    //Page<Categorie> cherhcer(String keyword, Pageable pageable);

    Categorie findByCode(String code);

    List<Categorie> findByNomContaining(String nom);
}
