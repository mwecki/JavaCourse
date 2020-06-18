package pl.wsb.students.api;

import pl.wsb.students.api.handlers.SecurityContextHandler;
import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.hibernate.UserAccount;
import pl.wsb.students.model.MovieRatingRequest;
import pl.wsb.students.model.MovieRequest;
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
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
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
    public Response putMovieIdAccept(Integer id) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
    @Authenticate
    @PUT
    @Path(ApiEndpoints.MOVIE_ID_REJECT)
    public Response putMovieIdReject(Integer id) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
    @Authenticate
    @PUT
    @Path(ApiEndpoints.MOVIE_ID_FAVORITE)
    public Response putMovieIdFavorite(Integer id) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
}
