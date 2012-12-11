package ru.gosuslugi.geps.ng.rest;

import ru.gosuslugi.geps.ng.dto.MessageDto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * User: rnasyrov
 * Date: 05.12.12
 * Time: 0:10
 */

@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {

    @POST
    @Path("/{userId}")
    public Response sendMessage(@PathParam("userId") Long userId, MessageDto dto){

        System.out.println("Send message to " + userId);

        URI location;
        try {
            location = new URI("/messages/1024");
        } catch (URISyntaxException e) {
            return Response.serverError().build();
        }
        return Response.created(location).build();
    }

}
