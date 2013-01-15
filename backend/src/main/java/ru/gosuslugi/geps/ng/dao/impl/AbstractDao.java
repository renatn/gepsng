package ru.gosuslugi.geps.ng.dao.impl;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import ru.gosuslugi.geps.ng.dao.KeyValueStore;
import ru.gosuslugi.geps.ng.model.Message;
import ru.gosuslugi.geps.ng.service.ServiceException;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

/**
 * User: renatn
 * Date: 15.01.13
 * Time: 14:11
 */
public abstract class AbstractDao<T> {

    private ObjectMapper objectMapper;

    public AbstractDao() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);
        objectMapper.getDeserializationConfig().withDateFormat(df);
    }

    protected T get(String entity, Long id, Class<T> clazz) throws ServiceException {
        String key = String.format("%s:%d", entity, id);
        String json = KeyValueStore.get(key);
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return parse(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("Error parse json");
        }
    }

    protected void add(String entity, Long id, T value) throws ServiceException {
        StringWriter writer = new StringWriter();
        try {
            objectMapper.writeValue(writer, value);
            String key = String.format("%s:%d", entity, id);
            String json = writer.toString();
            KeyValueStore.put(key, json);
        } catch (IOException e) {
            throw new ServiceException("Cannot save message");
        }
    }

    protected long getNextId() {
        return KeyValueStore.getNextId();
    }

    protected T parse(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

}
