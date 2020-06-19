package pl.wsb.students.api;

import pl.wsb.students.api.handlers.ErrorHandler;
import pl.wsb.students.api.handlers.SecurityContextHandler;
import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernate.UserAccount;
import pl.wsb.students.model.MovieRatingRequest;
import pl.wsb.students.model.MovieRequest;
import pl.wsb.students.model.User;
import pl.wsb.students.repository.impl.MovieFavoriteRepository;
import pl.wsb.students.repository.impl.MovieRepository;
import pl.wsb.students.repository.impl.UserAccountRepository;
import pl.wsb.students.security.annotation.Authenticate;

import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path(ApiEndpoints.MOVIE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {
    @Context
    SecurityContext securityContext;

    @GET
    public Response getMovie(
            @QueryParam(ApiEndpoints.PARAM_LIMIT) Integer limit,
            @QueryParam(ApiEndpoints.PARAM_OFFSET) Integer offset,
            @QueryParam(ApiEndpoints.PARAM_SEARCH) String search
    ) {
        Persistence.createEntityManagerFactory("manager").createEntityManager();
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }

    @Authenticate
    @POST
    public Response postMovie(MovieRequest body) {
        try {
            MovieRepository movieRepository = new MovieRepository();
            return Response.status(
                Response.Status.OK
            ).entity(
                movieRepository.insertMovie(body)
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

    @Authenticate
    @POST
    @Path(ApiEndpoints.MOVIE_RATE)
    public Response postMovieRate(MovieRatingRequest body) {
        UserAccount userAccount = SecurityContextHandler.getUserAccount(securityContext);
        return Response.status(Response.Status.OK).entity(userAccount.getEmail()).build();
    }
    @Authenticate
    @PUT
    @Path(ApiEndpoints.MOVIE_ID_ACCEPT)
    public Response putMovieIdAccept(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity("mock call ok..." + id).build();
    }
    @Authenticate
    @PUT
    @Path(ApiEndpoints.MOVIE_ID_REJECT)
    public Response putMovieIdReject(@PathParam("id")Integer id) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }

    @Authenticate
    @PUT
    @Path(ApiEndpoints.MOVIE_ID_FAVORITE)
    public Response putMovieIdFavorite(@PathParam("id") Integer id) {
        UserAccount userAccount = SecurityContextHandler.getUserAccount(securityContext);
        try {
            MovieFavoriteRepository movieFavoriteRepository = new MovieFavoriteRepository();
            return Response.status(
                Response.Status.OK
            ).entity(
                movieFavoriteRepository.markAsFavorite(id, userAccount)
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
