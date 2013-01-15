package ru.gosuslugi.geps.ng.dao.impl;

import ru.gosuslugi.geps.ng.dao.KeyValueStore;
import ru.gosuslugi.geps.ng.dao.MessageDao;
import ru.gosuslugi.geps.ng.model.Message;
import ru.gosuslugi.geps.ng.service.ServiceException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: rnasyrov
 * Date: 08.01.13
 * Time: 12:45
 */
public class MessageDaoImpl extends AbstractDao<Message> implements MessageDao {

    @Override
    public Message create(Message message) throws ServiceException {
        long id = getNextId();
        message.setMessageId(id);
        add("message", id, message);
        return getById(message.getMessageId());
    }

    @Override
    public Message getById(Long messageId) throws ServiceException {
        return get("message", messageId, Message.class);
    }

    @Override
    public List<Message> getUserMessages(Long userId) throws ServiceException {
        List<String> messages = KeyValueStore.getByPrefix("message:");
        List<Message> result = new ArrayList<Message>();
        try {
            for (String json : messages) {
                Message message = parse(json, Message.class);
                if (message.getFromId().equals(userId) || (message.getToId() != null && message.getToId().equals(userId))) {
                    result.add(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("Error parse json");
        }
        return result;
    }
}
