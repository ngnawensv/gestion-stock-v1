package cm.belrose.stockserveur.config.initializer;

import cm.belrose.stockserveur.config.constants.Constant;
import cm.belrose.stockserveur.model.Categorie;
import cm.belrose.stockserveur.model.ERole;
import cm.belrose.stockserveur.model.Role;
import cm.belrose.stockserveur.model.User;
import cm.belrose.stockserveur.repository.CategorieRepository;
import cm.belrose.stockserveur.repository.RoleRepository;
import cm.belrose.stockserveur.repository.UserRepository;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * Le 16/11/2020 à 09heures
 *
 * @author Ngnawen Samuel
 *
 * Cette classe permet de créer le super utilisateur au demarrage de l'application avec ses roles
 */
@Component
public class InitializeDataBase implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitializeDataBase.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
            Optional<Role> role = roleRepository.findByName(ERole.ROLE_ADMIN);
            if (role.isEmpty()) {
                Set<Role> listOfRoles = new HashSet<>(Arrays.asList(
                        new Role(ERole.ROLE_ADMIN),
                        new Role(ERole.ROLE_MODERATOR),
                        new Role(ERole.ROLE_USER))
                );
                if(!CollectionUtils.isEmpty(listOfRoles)){
                    roleRepository.saveAll(listOfRoles);
                    logger.info("All roles successful save !!!!");
                }else{
                    logger.info("List of roles is empty !!!!");
                }
                User user = new User(
                        Constant.DEFAULT_ADMIN_USERNAME,
                        Constant.DEFAULT_ADMIN_EMAIL,
                        passwordEncoder.encode(Constant.DEFAULT_ADMIN_PASSWORD),
                        listOfRoles
                );
                userRepository.save(user);
                logger.info("Super user successful create!!!!");
            } else {
                logger.info("Super user is already exist!!!!");
            }

            // Creation an default category ........
            List<Categorie> categories;
            Categorie default_categorie = categorieRepository.findByNom(Constant.DEFAULT_CATEGORIE_NAME);
            if(default_categorie==null){
                Assert.isNull(default_categorie);
                Categorie defaultCategorie = new Categorie("0000", "Default_cat");
                categories = new ArrayList<>();
                categories.add(defaultCategorie);
                categorieRepository.save(defaultCategorie);
                logger.info("Default category is successful create!!!!");
            }else{
                logger.info("Default category is already exist!!!!");
            }


    }
}
