package pl.wsb.students.security.annotation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import pl.wsb.students.api.handlers.ErrorHandler;
import pl.wsb.students.exceptions.UnauthenticatedException;
import pl.wsb.students.hibernate.ApiToken;
import pl.wsb.students.hibernate.UserAccount;
import pl.wsb.students.repository.EntityManagerHelper;
import pl.wsb.students.repository.impl.ApiTokenRepository;
import pl.wsb.students.security.MovieAppSecurityContext;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Authenticate
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticateFilter implements ContainerRequestFilter {
    @Context
    UriInfo uriInfo;
    @Override
    public void filter(ContainerRequestContext requestContext) {
        final String AUTH_HEADER_BEARER = "Bearer: ";
        try {
            String authorizationHeader =
                    requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isBlank(authorizationHeader)) {
                throw new UnauthenticatedException("No authorization data provided...");
            }
            if (!authorizationHeader.startsWith(AUTH_HEADER_BEARER)) {
                throw new UnauthenticatedException("Improper format of authorization data...");
            }//if
            requestContext.setSecurityContext(
                new MovieAppSecurityContext(
                    validateToken(authorizationHeader.substring(AUTH_HEADER_BEARER.length()).trim()),
                    uriInfo
                )
            );
        } catch (UnauthenticatedException ex) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).entity(ErrorHandler.getErrorResponse(ex)).build()
            );
        }
    }

    private UserAccount validateToken(String accessToken) throws UnauthenticatedException
    {
        final int TOKEN_VALIDATION_MINUTES = 30;
        if (StringUtils.isBlank(accessToken)) {
            throw new UnauthenticatedException();
        } //if
        ApiToken apiToken = ApiTokenRepository.findByAccessToken(accessToken);
        if (apiToken == null) {
            throw new UnauthenticatedException();
        } //if
        if (apiToken.getUserAccount() == null) {
            throw new UnauthenticatedException();
        } //if
        EntityManagerHelper.startTransaction();
        ApiTokenRepository tokenRepository = new ApiTokenRepository();
        apiToken.setValidTo(
            DateUtils.addMinutes(
                new Date(),
                TOKEN_VALIDATION_MINUTES
            )
        );
        tokenRepository.merge(apiToken);
        EntityManagerHelper.commitTransaction();
        return apiToken.getUserAccount();
    }
}
