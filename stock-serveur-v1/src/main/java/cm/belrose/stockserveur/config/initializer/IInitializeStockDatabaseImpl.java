package cm.belrose.stockserveur.config.initializer;

import cm.belrose.stockserveur.repository.*;
import cm.belrose.stockserveur.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class IInitializeStockDatabaseImpl implements IInitializeStockDatabase {
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private VenteRepository venteRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ArticleRepository articleRepository;
    ///private ArticleCategorieRepository articleCategorieRepository;
    @Autowired
    private ArticleVenteRepository articleVenteRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private CommandeClientRepository commandeClientRepository;
    @Autowired
    private LivraisonFournisseurRepository livraisonFournisseurRepository;
    @Autowired
    private MouvementStockRepository mouvementStockRepository;
    @Override
    public void initCategorie() {
        Stream.of("Categorie 1","Categorie 2","Categorie 3","Categorie 4","Categorie 5").forEach(nomCategorie->{
            Categorie categorie=new Categorie();
            categorie.setNom("Nom"+nomCategorie);
            categorie.setCode("Code"+nomCategorie);
            categorieRepository.save(categorie);
        });

    }

    @Override
    public void initArticleCategorie() {
        categorieRepository.findAll().forEach(categorie -> {
            articleRepository.findAll().forEach(article -> {
                ArticleCategorie articleCategorie=new ArticleCategorie();
                articleCategorie.setArticle(article);
                articleCategorie.setCategorie(categorie);
                //articleCategorieRepository.save(articleCategorie);
            });
        });
    }

    @Override
    public void initVente() {
        Stream.of("CodeVente1", "CodeVente2","CodeVente3").forEach(codeVente->{
            Vente vente=new Vente();
            vente.setCode(codeVente);
            venteRepository.save(vente);
        });


    }

    @Override
    public void initArticle() {
        /*double[] prix=new double[]{100.0,150.0,200.0,300.0};
        Stream.of("Article 1","article 2","Article 3","Article 4","Article 5").forEach(nomArticle->{
            Article article=new Article();
            article.setNom(nomArticle);
            article.setPrixAchat(prix[new Random().nextInt(prix.length)]);
            article.setPrixVente(prix[new Random().nextInt(prix.length)]);
            articleRepository.save(article);
        });*/

    }

    @Override
    public void initArticleVente() {
        articleRepository.findAll().forEach(article -> {
            venteRepository.findAll().forEach(vente -> {
                ArticleVente articleVente=new ArticleVente();
                articleVente.setArticle(article);
                articleVente.setVente(vente);
                articleVente.setQuantite(10+ (int)Math.random()*7);
                articleVenteRepository.save(articleVente);
            });
        });


    }

   @Override
    public void initRole() {
       for( ERole r: ERole.values()) {
           Role role=new Role();
           role.setName(r);
           roleRepository.save(role);
       }
    }

    /*@Override
    public void initUsers() {
        Stream.of("User 1","User 2","User 3","User 4").forEach(nameUse->{
            User user=new User();
            user.setActive(false);
            user.setLogin("Name"+nameUse);
            user.setPassword("Passeword"+nameUse);
            usersDao.save(user);
        });

    }*/

   /* @Override
    public void initUsersRoles() {
        usersDao.findAll().forEach(user -> {
            rolesDao.findAll().forEach(role -> {
                UsersRoles usersRole = new UsersRoles();
                usersRole.setUser(user);
                usersRole.setRole(role);
                usersRolesDao.save(usersRole);

            });
        });
    }
*/
    @Override
    public void initPersonne() {
        Stream.of("Personne 1","Personne 2","Personne 3","Personne 4","Personne 5").forEach(nomPersonne->{
            Personne personne=new Personne();
            personne.setNom("Nom"+nomPersonne);
            personne.setPrenom("Prenom"+nomPersonne);
            personneRepository.save(personne);
        });
    }

    @Override
    public void initCommandeClient() {
        personneRepository.findAll().forEach(personne -> {
            articleRepository.findAll().forEach(article -> {
                CommandeClient commandeClient=new CommandeClient();
                commandeClient.setQuantite(10+(int)Math.random()*7);
                commandeClient.setArticle(article);
                commandeClient.setPersonne(personne);
                commandeClientRepository.save(commandeClient);
            });
        });

    }

    @Override
    public void initLivraisonFournisseur() {
        personneRepository.findAll().forEach(personne -> {
            articleRepository.findAll().forEach(article -> {
                LivraisonFournisseur livraisonFournisseur=new LivraisonFournisseur();
                livraisonFournisseur.setQuantite(10+(int)Math.random()*7);
                livraisonFournisseur.setArticle(article);
                livraisonFournisseur.setPersonne(personne);
                livraisonFournisseurRepository.save(livraisonFournisseur);
            });
        });
    }



    @Override
    public void initImage() {
        List<String> nomImages= Arrays.asList("image1.jpg","image2.jpg","image3.jpg");
        articleRepository.findAll().forEach(article -> {
                Image image=new Image();
                image.setNom("Nom"+nomImages.get(new Random().nextInt(nomImages.size())));
                image.setNature("Nature"+nomImages.get(new Random().nextInt(nomImages.size())).replace(".jpg",""));
               image.setArticle(article);
                imageRepository.save(image);
        });

    }

    @Override
    public void initMouvementStock() {
        articleRepository.findAll().forEach(article -> {
            Stream.of("Vente","Livraison").forEach(typeMouvement->{
                MouvementStock mouvementStock=new MouvementStock();
                mouvementStock.setType(typeMouvement);
                mouvementStock.setArticle(article);
                mouvementStockRepository.save(mouvementStock);
            });
        });


    }
}
