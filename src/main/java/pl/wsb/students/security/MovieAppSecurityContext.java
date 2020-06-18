package pl.wsb.students.security;

import pl.wsb.students.hibernate.UserAccount;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;

public class MovieAppSecurityContext implements SecurityContext {
    private final UserAccount user;
    private final UriInfo uriInfo;
    public MovieAppSecurityContext(UserAccount user, UriInfo uriInfo) {
        this.user = user;
        this.uriInfo = uriInfo;
    }
    public UserAccount getUser() {
        return user;
    }
    @Override
    public Principal getUserPrincipal() {
        return new MovieAppPrincipal(this.user);
    }
    @Override
    public boolean isUserInRole(String s) {
        return true;
    }
    @Override
    public boolean isSecure() {
        if (this.uriInfo == null) {
            return false;
        } //if
        return this.uriInfo.getAbsolutePath().toString().startsWith("https");
    }
    @Override
    public String getAuthenticationScheme() {
        return "Movie-App-Auth-Scheme";
    }
}

