package ru.gosuslugi.geps.ng.service.impl;

import ru.gosuslugi.geps.ng.model.Message;
import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.service.MessageService;
import ru.gosuslugi.geps.ng.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * import java.util.List;
 * User: rnasyrov
 * Date: 16.12.12
 * Time: 12:59
 */
public class MessageServiceImpl implements MessageService {

    private static List<Message> messages = new ArrayList<Message>();
    private static UserService userService = new UserServiceImpl();

    static {

        Message message = new Message();
        message.setMessageId(123L);
        message.setFromId(4444L);
        message.setToId(1L);
        message.setSubject("Hello World!");
        message.setText("Amazing");
        message.setSendDate(new Date());
        message.setUpdateDate(new Date());

        messages.add(message);

        message = new Message();
        message.setFromId(4444L);
        message.setMessageId(321L);
        message.setSubject("Star Wars");
        message.setText("Episode 7");
        message.setSendDate(new Date());
        message.setToId(2L);
        message.setUpdateDate(new Date());

        messages.add(message);
    }

    @Override
    public Message getMessageById(Long userId, Long messageId) {
        for (Message message : messages) {
            if (message.getMessageId().equals(messageId)) {
                checkPermission(userId, message);
                return message;
            }
        }
        return null;
    }

    @Override
    public List<Message> getMessages(Long userId) {
        return messages;
    }

    @Override
    public Message create(Long userId, Message message) {
        checkUserExist(userId);

        Random random = new Random(System.currentTimeMillis());

        message.setFromId(userId);
        message.setMessageId((long) random.nextInt());
        message.setUpdateDate(new Date());
        message.setSendDate(null);

        messages.add(message);
        return message;
    }

    @Override
    public Message update(Long userId, Message message) {

        Message found = getMessageById(userId, message.getMessageId());
        if (found == null) {
            throw new RuntimeException("error.service.message.not.found");
        }

        checkPermission(userId, message);

        // Sent message is not modifiable
        if (message.getSendDate() != null) {
            throw new RuntimeException("error.service.message.unmodifiable");
        }

        found.setUpdateDate(new Date());
        found.setSubject(message.getSubject());
        found.setText(message.getText());
        found.setToId(message.getToId());

        return found;

    }

    @Override
    public Message send(Long senderId, Long recipientId, Long messageId) {
        Message message = getMessageById(senderId, messageId);
        if (message == null) {
            throw new RuntimeException("error.service.message.not.found");
        }

        checkPermission(senderId, message);

        if (message.getSendDate() != null) {
            throw new RuntimeException("error.message.already.sent");
        }

        if (recipientId == null) {
            throw new RuntimeException("error.service.validation.recipient.is.empty");
        }

        if (message.getSubject() == null || message.getSubject().isEmpty()) {
            throw new RuntimeException("error.service.validation.subject.is.empty");
        }

        if (message.getText() == null || message.getText().isEmpty()) {
            throw new RuntimeException("error.service.validation.text.is.empty");
        }

        checkUserExist(senderId);
        checkUserExist(recipientId);

        message.setFromId(senderId);
        message.setToId(recipientId);
        message.setUpdateDate(new Date());
        message.setSendDate(new Date());
        return message;
    }

    private void checkPermission(Long userId, Message message) {
        boolean hasPermission = (message.getFromId().equals(userId)) || (message.getToId() != null && message.getToId().equals(userId));
        if (!hasPermission) {
            throw new RuntimeException("error.service.permission.denied");
        }
    }

    private void checkUserExist(Long userId) {
        if (userId == 4444L) {
            return;
        }
        User sender = userService.getUserById(userId);
        if (sender == null) {
            throw new RuntimeException("error.service.user.not.found");
        }
    }

}
