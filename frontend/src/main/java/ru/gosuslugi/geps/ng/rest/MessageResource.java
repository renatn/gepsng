package ru.gosuslugi.geps.ng.rest;

import ru.gosuslugi.geps.ng.dto.MessageDto;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: renatn
 * Date: 23.11.12
 * Time: 15:29
 */

@Path("/messages")
public class MessageResource {

    private static List<MessageDto> messages = new ArrayList<MessageDto>();

    static {
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
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<MessageDto> getMessages() {
        System.out.println("Get messages: " + messages.size());
        return messages;
    }

    @POST
    public MessageDto createMessage(MessageDto messageDto) {
        Random random = new Random(System.currentTimeMillis());

        System.out.println("Create message");
        System.out.println("- Subject: " + messageDto.getSubject());
        System.out.println("- Text: " + messageDto.getText());
        messageDto.setMessageId((long) random.nextInt());
        messages.add(messageDto);
        return messageDto;
    }


}
