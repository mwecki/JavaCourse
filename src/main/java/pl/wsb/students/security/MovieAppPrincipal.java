package pl.wsb.students.security;

import pl.wsb.students.hibernate.UserAccount;

import java.security.Principal;

public class MovieAppPrincipal implements Principal {
    private final UserAccount user;
    public MovieAppPrincipal(UserAccount user) {
        this.user = user;
    }
    @Override
    public String getName() {
        if (this.user != null) {
            return this.user.getEmail();
        }
        return "---";
    }
}
