package ru.gosuslugi.geps.ng.rest;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import ru.gosuslugi.geps.ng.dto.MessageDto;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

/**
 * User: Renat Nasyrov
 * Date: 24.11.12
 * Time: 15:37
 */

@Provider
public class MyContextResolver implements ContextResolver<JAXBContext> {

    private JAXBContext context;
    private Class[] types = {MessageDto.class};

    public MyContextResolver() throws Exception {
        this.context = new JSONJAXBContext(JSONConfiguration.natural().build(), types);
    }

    @Override
    public JAXBContext getContext(Class<?> aClass) {
        System.out.println("Context");
        for (Class type : types) {
            if (type == aClass) {
                System.out.println("found");
                return context;
            }
        }
        return null;
    }
}
