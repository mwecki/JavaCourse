package pl.wsb.students.api.handlers;

import pl.wsb.students.hibernate.UserAccount;
import pl.wsb.students.repository.impl.UserAccountRepository;
import pl.wsb.students.security.MovieAppSecurityContext;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class SecurityContextHandler {
    public static UserAccount getUserAccount(SecurityContext context) {
        try {
            if (context == null) {
                return null;
            }
            if (context instanceof MovieAppSecurityContext) {
                return ((MovieAppSecurityContext) context).getUser();
            }
            Principal principal = context.getUserPrincipal();
            if (principal == null) {
                return null;
            }
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            return userAccountRepository.findByEmail(principal.getName());
        } catch (Throwable ex) {
            return null;
        }
    }
}
