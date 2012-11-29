package ru.gosuslugi.geps.ng.rest;

import ru.gosuslugi.geps.ng.dto.MessageDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * User: renatn
 * Date: 23.11.12
 * Time: 15:29
 */

@Path("/messages")
@Produces({MediaType.APPLICATION_JSON})
public class MessageResource {

    private static List<MessageDto> messages = new ArrayList<MessageDto>();

    static {
        MessageDto dto = new MessageDto();
        dto.setMessageId(123L);
        dto.setRecipient("ПФР");
        dto.setSubject("Hello World!");
        dto.setText("Amazing");
        dto.setSendDate(new Date());

        messages.add(dto);

        dto = new MessageDto();
        dto.setMessageId(321L);
        dto.setSubject("Star Wars");
        dto.setText("Episode 7");
        dto.setSendDate(new Date());
        dto.setSender("МВД России");
        dto.setRecipient("МВД России");

        messages.add(dto);
    }

    @GET
    public List<MessageDto> getMessages() {
        System.out.println("Get messages: " + messages.size());
        return messages;
    }

    @GET
    @Path("/{messageId}")
    public MessageDto getMessage(@PathParam("messageId") Long messageId) {
        System.out.println("Get messages: " + messages.size());
        return findMessageById(messageId);
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

    @PUT
    @Path("/{messageId}")
    public MessageDto updateMessage(@PathParam("messageId") Long messageId, MessageDto dto) {

        if (dto == null) {
            System.out.println("Null!!!");
            return dto;
        }

        MessageDto found = findMessageById(messageId);
        if (found == null) {
            System.out.println("Not found");
            return dto;
        }

        found.setUpdateDate(new Date());
        found.setSender(dto.getSender());
        found.setRecipient(dto.getRecipient());
        found.setSubject(dto.getSubject());
        found.setText(dto.getText());

        String action = dto.getAction();
        if (action != null && action.equals("SEND")) {
            sendMessage(messageId);
        }
        return found;
    }

    private void sendMessage(Long messageId) {
        MessageDto dto = findMessageById(messageId);
        if (dto != null) {
            dto.setSendDate(new Date());
        }
    }

    private MessageDto findMessageById(Long messageId) {
        for (MessageDto messageDto : messages) {
            if (messageDto.getMessageId().equals(messageId)) {
                return messageDto;
            }
        }
        return null;
    }

}
