package pl.wsb.students.api;

import pl.wsb.students.api.handlers.ErrorHandler;
import pl.wsb.students.api.handlers.SecurityContextHandler;
import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.exceptions.ApiException;
import pl.wsb.students.exceptions.UnauthenticatedException;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernate.ApiToken;
import pl.wsb.students.hibernate.UserAccount;
import pl.wsb.students.model.ChangePasswordRequest;
import pl.wsb.students.model.RegisterUserRequest;
import pl.wsb.students.model.User;
import pl.wsb.students.repository.impl.ApiTokenRepository;
import pl.wsb.students.repository.impl.UserAccountRepository;
import pl.wsb.students.security.annotation.Authenticate;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Iterator;

@Path(ApiEndpoints.USER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource
{
    @Context
    SecurityContext securityContext;

    @POST
    public Response postUser(RegisterUserRequest body) {
        try {
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    User.createFromUserAccount(userAccountRepository.registerUser(body))
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

    @POST
    @Path(ApiEndpoints.CHANGEPASSWORD)
    public Response postNewPassword(ChangePasswordRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }

    @Authenticate
    @DELETE
    @Path(ApiEndpoints.LOGOUT)
    public Response Logout() {
        UserAccount userAccount = SecurityContextHandler.getUserAccount(securityContext);

        ApiTokenRepository apiTokenRepository = new ApiTokenRepository();
        try {
            return Response.status(
                    Response.Status.OK
            ).entity(
                    apiTokenRepository.dropApiToken(userAccount)
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
