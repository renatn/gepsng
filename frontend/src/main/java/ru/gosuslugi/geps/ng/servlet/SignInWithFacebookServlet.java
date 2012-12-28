package ru.gosuslugi.geps.ng.servlet;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;

/**
 * User: renatn
 * Date: 25.12.12
 * Time: 14:03
 */
public class SignInWithFacebookServlet extends HttpServlet {

    private final static String SIGNIN_URL = "http://gepsapp.renatn.com:8080/geps/signin";

    private final static Logger logger = Logger.getLogger(SignInWithFacebookServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("code");
        if (code == null || code.equals("")) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = requestAccessToken(code);
        String graph = requestUserData(token);
        logger.info("Graph: " + graph);

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);

        Authentication authentication = new UsernamePasswordAuthenticationToken("Renat Nasyrov", "qwerty", authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info("CREATE AUTHENTICATION");

        resp.sendRedirect("/geps");
    }

    private String requestUserData(String token) {
        String graph = null;
        try {
            String g = "https://graph.facebook.com/me?" + token;
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String inputLine;
            StringBuilder b = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine).append("\n");
            in.close();
            graph = b.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }

    private String requestAccessToken(String code) {
        String token = null;
        try {
            String g = "https://graph.facebook.com/oauth/access_token?client_id=505845966105387&redirect_uri="
                    + URLEncoder.encode(SIGNIN_URL, "UTF-8")
                    + "&client_secret=e6a4d500347f4238650af16c846e1dba&code=" + code;
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String inputLine;
            StringBuilder b = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine).append("\n");
            in.close();
            token = b.toString();
            if (token.startsWith("{"))
                throw new Exception("error on requesting token: " + token + " with code: " + code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
