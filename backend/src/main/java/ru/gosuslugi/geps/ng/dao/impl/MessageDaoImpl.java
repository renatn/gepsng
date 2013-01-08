package ru.gosuslugi.geps.ng.dao.impl;

import org.codehaus.jackson.map.ObjectMapper;
import ru.gosuslugi.geps.ng.dao.KeyValueStore;
import ru.gosuslugi.geps.ng.dao.MessageDao;
import ru.gosuslugi.geps.ng.model.Message;
import ru.gosuslugi.geps.ng.service.ServiceException;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * User: rnasyrov
 * Date: 08.01.13
 * Time: 12:45
 */
public class MessageDaoImpl implements MessageDao {

    private ObjectMapper objectMapper;

    public MessageDaoImpl() {
        objectMapper = new ObjectMapper();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(df);
        objectMapper.getDeserializationConfig().withDateFormat(df);
    }

    @Override
    public Message create(Message message) throws ServiceException {
        StringWriter writer = new StringWriter();
        try {
            message.setMessageId(KeyValueStore.getNextId());
            objectMapper.writeValue(writer, message);
            String key = String.format("message:%s", message.getMessageId().toString());
            String json = writer.toString();
            System.out.println(json);
            KeyValueStore.put(key, json);
        } catch (IOException e) {
            throw new ServiceException("Cannot save message");
        }
        return getMessageById(message.getMessageId());
    }

    @Override
    public Message getMessageById(Long messageId) throws ServiceException {

        String key = String.format("message:%s", messageId.toString());
        String json = KeyValueStore.get(key);
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.readValue(json, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("Error parse json");
        }
    }

    @Override
    public List<Message> getUserMessages(Long userId) throws ServiceException {
        List<String> messages = KeyValueStore.getByPrefix("message:");
        List<Message> result = new ArrayList<Message>();
        try {
            for (String json : messages) {
                Message message = objectMapper.readValue(json, Message.class);
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
