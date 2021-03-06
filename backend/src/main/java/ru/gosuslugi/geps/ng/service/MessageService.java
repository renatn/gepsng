package ru.gosuslugi.geps.ng.service;

import ru.gosuslugi.geps.ng.model.Message;

import java.util.List;

/**
 * User: rnasyrov
 * Date: 16.12.12
 * Time: 12:48
 */
public interface MessageService {

    Message getMessageById(Long userId, Long messageId) throws ServiceException;

    List<Message> getMessages(Long userId) throws ServiceException;

    Message create(Long userId, Message message) throws ServiceException;
    Message update(Long userId, Message message) throws ServiceException;

    Message send(Long senderId, Long recipientId, Long messageId) throws ServiceException;

}
