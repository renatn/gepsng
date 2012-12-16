package ru.gosuslugi.geps.ng.rest;

import ru.gosuslugi.geps.ng.dto.MessageDto;
import ru.gosuslugi.geps.ng.dto.UserDto;
import ru.gosuslugi.geps.ng.model.Message;
import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.service.MessageService;
import ru.gosuslugi.geps.ng.service.UserService;
import ru.gosuslugi.geps.ng.service.impl.MessageServiceImpl;
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

    private static UserService userService = new UserServiceImpl();
    private static MessageService messageService = new MessageServiceImpl();

    @GET
    public List<MessageDto> getMessages() {

        List<MessageDto> messageListDto = new ArrayList<MessageDto>();

        List<Message> messages = messageService.getMessages();
        for (Message message : messages) {
            MessageDto dto = new MessageDto(message);

            User recipient = userService.getUserById(message.getToId());
            User sender = userService.getUserById(message.getFromId());
            dto.setRecipient(new UserDto(recipient));
            dto.setSender(new UserDto(sender));

            messageListDto.add(dto);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return messageListDto;
    }

    @GET
    @Path("/{messageId}")
    public MessageDto getMessage(@PathParam("messageId") Long messageId) {
        Message message = messageService.getMessageById(messageId);
        if (message == null) {
            throw new RuntimeException("error.message.not.found");
        }
        return new MessageDto(message);
    }

    @POST
    public MessageDto createMessage(MessageDto dto) {

        Message message = new Message();
        message.setFromId(userService.getCurrentUser().getUserId());
        if (dto.getRecipient() != null) {
            message.setToId(dto.getRecipient().getUserId());
        }
        message.setSubject(dto.getSubject());
        message.setText(dto.getText());

        Message saved = messageService.create(message);
        return new MessageDto(saved);
        //TODO: fill location to new resource
    }

    @POST
    @Path("/sent/{toId}")
    public MessageDto sendMessage(@PathParam("toId") Long toId, MessageDto dto) {

        User user = userService.getCurrentUser();
        Message message = messageService.send(user.getUserId(), toId, dto.getMessageId());

        return new MessageDto(message);
    }

    @PUT
    @Path("/{messageId}")
    public MessageDto updateMessage(@PathParam("messageId") Long messageId, MessageDto dto) {


        User user = userService.getCurrentUser();
        Message message = messageService.update(user.getUserId(), message);

        return new MessageDto(message);

        found.setUpdateDate(new Date());
        found.setSender(dto.getSender());
        found.setRecipient(dto.getRecipient());
        found.setSubject(dto.getSubject());
        found.setText(dto.getText());

        return found;
    }



}
