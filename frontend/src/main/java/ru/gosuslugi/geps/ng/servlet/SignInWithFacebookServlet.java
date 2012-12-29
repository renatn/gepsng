package ru.gosuslugi.geps.ng.servlet;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

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
    private final static String CLIENT_ID = "505845966105387";
    private final static String SECRET = "e6a4d500347f4238650af16c846e1dba"; // TODO: move to properties

    private final static Logger logger = Logger.getLogger(SignInWithFacebookServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("code");
        if (code == null || code.equals("")) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Request Facebook User Profile
        String token = requestAccessToken(code);
        if (token.startsWith("{")) {
            throw new IOException("error on requesting token: " + token + " with code: " + code);
        }
        String graph = requestUserData(token);
        logger.info("Graph: " + graph);

        // Create authentication
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);
        Authentication authentication = new UsernamePasswordAuthenticationToken("Renat Nasyrov", graph, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        req.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        // Redirect to main page
        resp.sendRedirect("/geps/#!/");
    }

    private String requestUserData(String token) throws IOException {
        String g = "https://graph.facebook.com/me?" + token;
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

    private String requestAccessToken(String code) throws IOException {
        String g = String.format(
                "https://graph.facebook.com/oauth/access_token?client_id=%s&redirect_uri=%s&client_secret=%s&code=%s",
                CLIENT_ID,
                URLEncoder.encode(SIGNIN_URL, "UTF-8"),
                SECRET,
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
}
