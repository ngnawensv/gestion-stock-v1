package cm.belrose.stockserveur.config.audit;

import cm.belrose.stockserveur.Security.services.UserDetailsImpl;
import cm.belrose.stockserveur.Security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
/**
 * @author Ngnawen Samuel
 * @since 17/11/2020 18:20
 *
 * This class is use to pull the authenticate user with spring security context
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {

            return Optional.ofNullable("SYSTEM");
        }
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        //UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return Optional.ofNullable(userPrincipal.getUsername());
    }
}
