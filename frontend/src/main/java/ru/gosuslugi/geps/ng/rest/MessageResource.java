package ru.gosuslugi.geps.ng.rest;

import ru.gosuslugi.geps.ng.dto.MessageDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * User: renatn
 * Date: 23.11.12
 * Time: 15:29
 */

@Path("/messages")
public class MessageResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<MessageDto> getMessage() {
        List<MessageDto> messages = new ArrayList<MessageDto>();
        MessageDto dto = new MessageDto();
        dto.setMessageId(123L);
        dto.setSubject("Hello World!");
        dto.setText("Amazing");

        messages.add(dto);

        dto = new MessageDto();
        dto.setMessageId(321L);
        dto.setSubject("Star Wars");
        dto.setText("Episode 7");

        messages.add(dto);

        return messages;
    }

}
