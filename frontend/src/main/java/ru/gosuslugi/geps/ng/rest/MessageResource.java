package ru.gosuslugi.geps.ng.rest;

import ru.gosuslugi.geps.ng.dto.MessageDto;
import ru.gosuslugi.geps.ng.dto.UserDto;
import ru.gosuslugi.geps.ng.model.Message;
import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.service.MessageService;
import ru.gosuslugi.geps.ng.service.ServiceException;
import ru.gosuslugi.geps.ng.service.UserService;
import ru.gosuslugi.geps.ng.service.impl.MessageServiceImpl;
import ru.gosuslugi.geps.ng.service.impl.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * User: renatn
 * Date: 23.11.12
 * Time: 15:29
 */

@Path("/messages")
@Produces({MediaType.APPLICATION_JSON})
public class MessageResource {

    //TODO: use logger

    private static UserService userService = new UserServiceImpl();
    private static MessageService messageService = new MessageServiceImpl();

    @GET
    public List<MessageDto> getMessages() {

        User user = userService.getCurrentUser();

        List<MessageDto> messageListDto = new ArrayList<MessageDto>();

        List<Message> messages;
        try {
            messages = messageService.getMessages(user.getUserId());
        } catch (ServiceException e) {
            throw new ApiException("Cannot get messages");
        }

        for (Message message : messages) {
            MessageDto dto = messageToDto(message);
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

        User user = userService.getCurrentUser();

        Message message;
        try {
            message = messageService.getMessageById(user.getUserId(), messageId);
        } catch (ServiceException e) {
            throw new ApiException("Cannot get message");
        }
        if (message == null) {
            throw new ApiException("error.message.not.found");
        }

        return messageToDto(message);
    }

    @POST
    // TODO: Return 201 status code
    public MessageDto createMessage(MessageDto dto) {

        User user = userService.getCurrentUser();

        Message message = new Message();
        message.setFromId(userService.getCurrentUser().getUserId());
        if (dto.getRecipient() != null) {
            message.setToId(dto.getRecipient().getUserId());
        }
        message.setSubject(dto.getSubject());
        message.setText(dto.getText());

        Message saved;
        try {
            saved = messageService.create(user.getUserId(), message);
        } catch (ServiceException e) {
            throw new ApiException("Cannot create message");
        }
        return new MessageDto(saved);
        //TODO: fill location to new resource
    }

    @POST
    @Path("/sent/{toId}")
    public MessageDto sendMessage(@PathParam("toId") Long toId, MessageDto dto) {

        User user = userService.getCurrentUser();
        Message message;
        try {
            message = messageService.send(user.getUserId(), toId, dto.getMessageId());
        } catch (ServiceException e) {
            throw new ApiException("Cannot send message");
        }

        return messageToDto(message);
    }

    @PUT
    @Path("/{messageId}")
    public MessageDto updateMessage(@PathParam("messageId") Long messageId, MessageDto dto) {

        User user = userService.getCurrentUser();

        Message message = new Message();
        message.setFromId(user.getUserId());
        message.setMessageId(messageId);
        message.setSubject(dto.getSubject());
        message.setText(dto.getText());
        if (dto.getRecipient() != null) {
            message.setToId(dto.getRecipient().getUserId());
        }

        Message saved;
        try {
            saved = messageService.update(user.getUserId(), message);
        } catch (ServiceException e) {
            throw new ApiException("Cannot update message");
        }
        return messageToDto(saved);

    }

    private MessageDto messageToDto(Message message) {
        MessageDto dto = new MessageDto(message);

        User recipient = userService.getUserById(message.getToId());
        if (recipient != null) {
            dto.setRecipient(new UserDto(recipient));
        }

        User sender = userService.getUserById(message.getFromId());
        dto.setSender(new UserDto(sender));

        return dto;
    }

}
