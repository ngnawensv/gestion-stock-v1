package cm.belrose.stockserveur.service.impl;

import cm.belrose.stockserveur.dto.CategorieDto;
import cm.belrose.stockserveur.model.Categorie;
import cm.belrose.stockserveur.repository.CategorieRepository;
import cm.belrose.stockserveur.service.CategorieService;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * Le 11/11/2020
 *
 * @author Ngnawen Samuel
 */
@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {

    private static final Logger logger = LoggerFactory.getLogger(CategorieServiceImpl.class);
    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Optional<Categorie> findById(Long id) throws Exception {
        return categorieRepository.findById(id);
    }

    @Override
    public List<Categorie> findAll() throws Exception {
        return categorieRepository.findAll();
    }

    /*@Override
    public Categorie save(CategorieDTO categorieDto) throws Exception {
       Categorie cat= new Categorie(categorieDto.getCode(),categorieDto.getNom());
       return  categorieRepository.save(cat);
    }*/

    @Override
    public Categorie save(Categorie categorie) throws Exception {
        //Categorie _categorie= new Categorie(categorie.getCode(),categorie.getNom());
        try{
            Assert.notNull(categorie,"Category must not be null");
            return  categorieRepository.save(categorie);
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            return  categorieRepository.save(categorie);
        }
    }

    @Override
    public Categorie update(Categorie categorie) throws Exception {
        try{
            Assert.notNull(categorie,"Category must not be null");
            return  categorieRepository.save(categorie);
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            return  categorieRepository.save(categorie);
        }
    }

   /* @Override
    public void delete(Categorie categorie) throws Exception {
        try {
            categorieRepository.delete(categorie);
        } catch (EmptyResultDataAccessException ex) {
            logger.error(String.format("Categpry with Name =" + categorie.getNom() + " don't exist"));
            throw new EmptyResultDataAccessException("DeleteUserError", HttpStatus.NOT_FOUND.value());
        }

    }*/

    @Override
    public void deleteById(Long id) {
        Optional<Categorie> categorie=categorieRepository.findById(id);
        if (categorie.get().getCode()=="0000"){
            logger.error("Impossible to delete default category");
        }
        categorieRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        categorieRepository.deleteAll();
    }

    @Override
    public Boolean existsByNom(String nom) {
        return categorieRepository.existsByNom(nom);
    }

   /* @Override
    public Page<Categorie> cherhcer(String keyword, Pageable pageable) {
        return categorieRepository.chercher(keyword,pageable);
    }*/

    @Override
    public Categorie findByCode(String code) {
        return categorieRepository.findByCode(code);
    }

    @Override
    public List<Categorie> findByNomContaining(String nom) {
        return categorieRepository.findByNomContaining(nom);
    }
}
