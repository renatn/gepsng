package ru.gosuslugi.geps.ng.dao;

import ru.gosuslugi.geps.ng.model.Message;
import ru.gosuslugi.geps.ng.service.ServiceException;

import java.util.List;

/**
 * User: rnasyrov
 * Date: 08.01.13
 * Time: 12:44
 */
public interface MessageDao {

    Message create(Message message) throws ServiceException;

    Message getMessageById(Long messageId) throws ServiceException;

    List<Message> getUserMessages(Long userId) throws ServiceException;
}
