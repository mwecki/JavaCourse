package pl.wsb.students.api;

import org.apache.commons.lang3.StringUtils;
import pl.wsb.students.api.handlers.ErrorHandler;
import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.exceptions.UnauthenticatedException;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernate.UserAccount;
import pl.wsb.students.model.AuthenticationRequest;
import pl.wsb.students.model.AuthenticationResponse;
import pl.wsb.students.repository.impl.ApiTokenRepository;
import pl.wsb.students.repository.impl.UserAccountRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.AUTHENTICATE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticateResource {
    @POST
    public Response postAuthenticate(AuthenticationRequest body) {
        try {
            if (body == null) {
                throw new ValidationException("No request data provided...");
            } //if
            if ((StringUtils.isBlank(body.getEmail())) ||
                    (StringUtils.isBlank(body.getPassword()))) {
                throw new ValidationException("No credentials data provided...");
            } //if
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            UserAccount userAccount = userAccountRepository.findByEmail(body.getEmail());
            if (userAccount == null) {
                throw new UnauthenticatedException();
            } //if
            if (!userAccount.validatePass(body.getPassword())) {
                throw new UnauthenticatedException();
            } //if
            ApiTokenRepository apiTokenRepository = new ApiTokenRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    AuthenticationResponse.createFromApiToken(
                            apiTokenRepository.generateApiToken(
                                    userAccount
                            )
                    )
            ).build();
        } catch (UnauthenticatedException ex) {
            return Response.status(
                    Response.Status.UNAUTHORIZED
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        } catch (ValidationException ex) {
            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }
}