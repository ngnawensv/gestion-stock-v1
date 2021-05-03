package cm.belrose.stockserveur.controller;

import cm.belrose.stockserveur.dto.ArticleDTO;
import cm.belrose.stockserveur.model.Article;
import cm.belrose.stockserveur.payload.response.MessageResponse;
import cm.belrose.stockserveur.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Le 10/11/2020
 *
 * @author Ngnawen Samuel
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ArticleController {

    private static final Logger logger= LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getAll(@RequestParam(required = false) String nom) throws Exception {
        try {
            List<Article> articles = new ArrayList<>();
            //Creating Consumer for article object which will be used in forEach method for adding article on the list
            Consumer<Article> consumer= article->articles.add(article);
            //Consumer<Article> consumer1= articles::add; // same with the above consumer by using reference method
            if (nom == null)
                articleService.findAll().forEach(consumer);
            else
                articleService.findByNomContaining(nom).forEach(consumer);
            if (articles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> findById(@PathVariable("id") Long id) throws Exception {
        Optional<Article> _article = articleService.findById(id);

        if (_article.isPresent()) {
            return new ResponseEntity<>(_article.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save a article in specifique article
     *
     * @param articleDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/articles")
    public ResponseEntity<Article> save(@RequestBody ArticleDTO articleDTO) throws Exception {
        Article article = articleService.save(articleDTO);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @PutMapping("articles/{id}")
    public ResponseEntity<Article> update(@PathVariable("id") long id, @RequestBody Article article) throws Exception {
        Optional<Article> articleData = articleService.findById(id);
        if (articleData.isPresent()) {
            Article _article= articleData.get();
            _article.setId(id);
            _article.setNom(article.getNom());
            _article.setPrixAchat(article.getPrixAchat());
            _article.setPrixVente(article.getPrixVente());
            _article.setQuantite(article.getQuantite());
            return new ResponseEntity<>(articleService.update(_article), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") Long id) {
        try {
            articleService.deleteById(id);
            logger.info("Article is successfully delete........");
            return new ResponseEntity<>(new MessageResponse("Article is successfully delete........"), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Deleting operation failed........");
            return new ResponseEntity<>(new MessageResponse("Deleting operation failed........"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/articles")
    public ResponseEntity<MessageResponse> deleteAll() {
        try {
            articleService.deleteAll();
            logger.info("All Articles are successfully deleted........");
            return new ResponseEntity<>(new MessageResponse("All Articles are successfully deleted........"), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Deleting operation failed........");
            return new ResponseEntity<>(new MessageResponse("Deleting operation failed........"),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
