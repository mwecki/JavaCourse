package pl.wsb.students.api;

import pl.wsb.students.consts.ApiEndpoints;
import pl.wsb.students.model.MovieLibraryRequest;
import pl.wsb.students.security.annotation.Authenticate;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.LIBRARY)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class LibraryResource {
    @Authenticate
    @POST
    public Response postLibrary(MovieLibraryRequest body) {
        return Response.status(Response.Status.OK).entity("mock call ok...").build();
    }
}
