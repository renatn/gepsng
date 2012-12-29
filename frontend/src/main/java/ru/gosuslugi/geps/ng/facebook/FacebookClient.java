package ru.gosuslugi.geps.ng.facebook;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * User: renatn
 * Date: 29.12.12
 * Time: 18:32
 */
public class FacebookClient {

    private String clientId;
    private String secret;
    private String signinUrl;

    public FacebookClient(String clientId, String secret, String signinUrl) {
        this.clientId  = clientId;
        this.secret = secret;
        this.signinUrl = signinUrl;
    }

    public String requestAccessToken(String code) throws IOException {
        String g = String.format(
                "https://graph.facebook.com/oauth/access_token?client_id=%s&redirect_uri=%s&client_secret=%s&code=%s",
                clientId,
                URLEncoder.encode(signinUrl, "UTF-8"),
                secret,
                code
        );
        URL u = new URL(g);
        URLConnection c = u.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
        String inputLine;
        StringBuilder b = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            b.append(inputLine).append("\n");
        in.close();
        return b.toString();
    }

    public FacebookProfile requestUserData(String token) throws IOException {
        String g = "https://graph.facebook.com/me?" + token;
        URL u = new URL(g);
        URLConnection c = u.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(reader, FacebookProfile.class);
    }
}