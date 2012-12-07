package ru.gosuslugi.geps.ng.rest;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.text.SimpleDateFormat;

/**
 * User: renatn
 * Date: 07.12.12
 * Time: 20:29
 */

@Provider
public class JsonDateTimeSerializer extends JacksonJsonProvider {

    @Override
    public ObjectMapper locateMapper(Class<?> type, MediaType mediaType) {
        ObjectMapper mapper = super.locateMapper(type, mediaType);
        mapper.setDateFormat(new SimpleDateFormat("dd.MM.yyyy HH:mm")); // 1.8 and above
        return mapper;
    }
}
