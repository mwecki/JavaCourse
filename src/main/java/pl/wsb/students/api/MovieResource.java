package pl.wsb.students.api;

import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.model.MovieRatingRequest;
import pl.wsb.students.model.MovieRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.MOVIE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {
    @GET
    public Response getMovie(
            @QueryParam(ApiEndpoints.PARAM_LIMIT) Integer limit,
            @QueryParam(ApiEndpoints.PARAM_OFFSET) Integer offset,
            @QueryParam(ApiEndpoints.PARAM_SEARCH) String search
    ) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
    @POST
    public Response postMovie(MovieRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
    @POST
    @Path(ApiEndpoints.MOVIE_RATE)
    public Response postMovieRate(MovieRatingRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
    @PUT
    @Path(ApiEndpoints.MOVIE_ID_ACCEPT)
    public Response putMovieIdAccept(Integer id) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
    @PUT
    @Path(ApiEndpoints.MOVIE_ID_REJECT)
    public Response putMovieIdReject(Integer id) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
}
