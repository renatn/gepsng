package ru.gosuslugi.geps.ng.facebook;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * User: renatn
 * Date: 29.12.12
 * Time: 18:27
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookProfile {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
