package ru.gosuslugi.geps.ng.facebook;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * User: renatn
 * Date: 29.12.12
 * Time: 18:42
 */


public class FacebookClientTest {

    private final static String GRAPH = " {\"id\":\"100002223950429\",\"name\":\"Renat Nasyrov\",\"first_name\":\"Renat\",\"last_name\":\"Nasyrov\",\"link\":\"http:\\/\\/www.facebook.com\\/renatvn\",\"username\":\"renatvn\",\"location\":{\"id\":\"115085015172389\",\"name\":\"Moscow, Russia\"},\"bio\":\"This game has no name\",\"work\":[{\"employer\":{\"id\":\"110734218985546\",\"name\":\"AT Consulting\"},\"start_date\":\"2011-12\"},{\"employer\":{\"id\":\"110734218985546\",\"name\":\"AT Consulting\"},\"position\":{\"id\":\"141816345848094\",\"name\":\"\\u0412\\u0435\\u0434\\u0443\\u0449\\u0438\\u0439 \\u0440\\u0430\\u0437\\u0440\\u0430\\u0431\\u043e\\u0442\\u0447\\u0438\\u043a\"},\"start_date\":\"0000-00\"},{\"employer\":{\"id\":\"166720776676389\",\"name\":\"ITV | Axxon\"},\"position\":{\"id\":\"117912888258298\",\"name\":\"\\u0420\\u0443\\u043a\\u043e\\u0432\\u043e\\u0434\\u0438\\u0442\\u0435\\u043b\\u044c \\u043f\\u0440\\u043e\\u0435\\u043a\\u0442\\u0430\"}},{\"employer\":{\"id\":\"113971645285060\",\"name\":\"kedah\"},\"position\":{\"id\":\"130363183708410\",\"name\":\"Programmer\"},\"start_date\":\"0000-00\",\"end_date\":\"2010-12\"}],\"education\":[{\"school\":{\"id\":\"112137622145618\",\"name\":\"\\u0412\\u043b\\u0413\\u0423\"},\"year\":{\"id\":\"194878617211512\",\"name\":\"2002\"},\"concentration\":[{\"id\":\"193027510729334\",\"name\":\"\\u0418\\u043d\\u0436\\u0435\\u043d\\u0435\\u0440-\\u0440\\u0430\\u0434\\u0438\\u043e\\u0442\\u0435\\u0445\\u043d\\u0438\\u043a\"}],\"type\":\"College\"}],\"gender\":\"male\",\"email\":\"renatn\\u0040gmail.com\",\"timezone\":4,\"locale\":\"ru_RU\",\"verified\":true,\"updated_time\":\"2012-12-27T16:53:58+0000\"}\n";


    @Test
    public void shouldParseFacebookProfile() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        FacebookProfile profile = objectMapper.readValue(GRAPH, FacebookProfile.class);
        Assert.notNull(profile.getId());
        Assert.notNull(profile.getName());
    }

}
