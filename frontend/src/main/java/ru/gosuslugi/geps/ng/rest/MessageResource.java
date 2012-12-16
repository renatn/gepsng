package ru.gosuslugi.geps.ng.rest;

import ru.gosuslugi.geps.ng.dto.MessageDto;
import ru.gosuslugi.geps.ng.dto.UserDto;
import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.service.UserService;
import ru.gosuslugi.geps.ng.service.impl.UserServiceImpl;

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

    private static UserService userService = new UserServiceImpl();

    static {

        User pfr = userService.getUserById(1L);
        User mvd = userService.getUserById(2L);
        User renat = userService.getCurrentUser();

        MessageDto dto = new MessageDto();
        dto.setMessageId(123L);
        dto.setSender(new UserDto(renat));
        dto.setRecipient(new UserDto(pfr));
        dto.setSubject("Hello World!");
        dto.setText("Amazing");
        dto.setSendDate(new Date());
        dto.setUpdateDate(new Date());

        messages.add(dto);

        dto = new MessageDto();
        dto.setMessageId(321L);
        dto.setSubject("Star Wars");
        dto.setText("Episode 7");
        dto.setSendDate(new Date());
        dto.setRecipient(new UserDto(mvd));
        dto.setUpdateDate(new Date());

        messages.add(dto);
    }

    @GET
    public List<MessageDto> getMessages() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        messageDto.setUpdateDate(new Date());
        messages.add(messageDto);
        return messageDto;
        //TODO: fill location to new resource
    }

    @POST
    @Path("/to/{toId}")
    public MessageDto sendMessage(@PathParam("toId") Long toId, MessageDto dto) {

        User to = userService.getUserById(toId);
        if (to == null) {
            throw new RuntimeException("error.user.not.found");
        }

        MessageDto draft = findMessageById(dto.getMessageId());
        if (draft == null) {
            throw new RuntimeException("error.draft.not.found");
        }

        if (draft.getSendDate() != null) {
            throw new RuntimeException("error.message.already.sent");
        }

        // TODO: Validate fields

        draft.setRecipient(new UserDto(to));
        draft.setSender(new UserDto(userService.getCurrentUser()));
        draft.setSubject(dto.getSubject());
        draft.setText(dto.getText());
        draft.setSendDate(new Date());

        return draft;
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

        return found;
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
