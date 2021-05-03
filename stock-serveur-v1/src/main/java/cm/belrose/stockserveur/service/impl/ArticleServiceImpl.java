package cm.belrose.stockserveur.service.impl;

import cm.belrose.stockserveur.config.constants.Constant;
import cm.belrose.stockserveur.dto.ArticleDTO;
import cm.belrose.stockserveur.model.Article;
import cm.belrose.stockserveur.model.Categorie;
import cm.belrose.stockserveur.repository.ArticleRepository;
import cm.belrose.stockserveur.repository.CategorieRepository;
import cm.belrose.stockserveur.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Le 11/11/2020
 *
 * @author Ngnawen Samuel
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CategorieRepository categorieRepository;

    @Override
    public Optional<Article> findById(Long id) throws Exception {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> findAll() throws Exception {
        return articleRepository.findAll();
    }

    /**
     * This method save a article with all their categories
     * @param articleDTO
     * @return
     * @throws Exception
     */
    @Override
    public Article save(ArticleDTO articleDTO) throws Exception {
        Set<Categorie> categories = articleDTO.getListOfCategories();
        Set<Categorie> _categories = new HashSet<>();
        if (CollectionUtils.isEmpty(categories)|| categories==null) {
            Categorie defaultCategorie = categorieRepository.findByNom(Constant.DEFAULT_CATEGORIE_NAME);
            _categories.add(defaultCategorie);
        }else {
            categories.forEach(categorie -> {
                Categorie _categorie = categorieRepository.findByNom(categorie.getNom());
                _categories.add(_categorie);
            });
        }
        Article article = new Article(articleDTO.getNom(), articleDTO.getPrixAchat(),
                articleDTO.getPrixVente(), articleDTO.getQuantite(), _categories);
        logger.info("Article is successful save .....");
        return articleRepository.save(article);
    }

    @Override
    public Article update(Article article) throws Exception {
        return articleRepository.save(article);
    }

    @Override
    public void delete(Article article) throws Exception {
        articleRepository.delete(article);
    }


    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        articleRepository.deleteAll();
    }


    @Override
    public List<Article> findByNomContaining(String nom) {
        return articleRepository.findByNomContaining(nom);
    }
}
