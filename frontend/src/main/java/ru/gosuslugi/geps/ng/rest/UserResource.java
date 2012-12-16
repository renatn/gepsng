package ru.gosuslugi.geps.ng.rest;

import ru.gosuslugi.geps.ng.dto.MessageDto;
import ru.gosuslugi.geps.ng.dto.UserDto;
import ru.gosuslugi.geps.ng.model.Message;
import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.service.UserService;
import ru.gosuslugi.geps.ng.service.impl.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: rnasyrov
 * Date: 05.12.12
 * Time: 0:10
 */

@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {

    private static UserService userService = new UserServiceImpl();

    @GET
    public List<UserDto> getMessages() {

        List<UserDto> organizationListDto = new ArrayList<UserDto>();

        List<User> organizations = userService.getOrganizations();
        for (User user : organizations) {
            UserDto dto = new UserDto(user);
            organizationListDto.add(dto);
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return organizationListDto;
    }


}
