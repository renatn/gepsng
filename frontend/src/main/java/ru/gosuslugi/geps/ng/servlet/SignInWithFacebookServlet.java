package ru.gosuslugi.geps.ng.servlet;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import ru.gosuslugi.geps.ng.facebook.FacebookClient;
import ru.gosuslugi.geps.ng.facebook.FacebookProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * User: renatn
 * Date: 25.12.12
 * Time: 14:03
 */
public class SignInWithFacebookServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("code");
        if (code == null || code.equals("")) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        FacebookClient client = new FacebookClient(
                getInitParameter("clientId"),
                getInitParameter("secret"),
                getInitParameter("signinUrl")
        );
        String token = client.requestAccessToken(code);
        if (token.startsWith("{")) {
            throw new IOException("error on requesting token: " + token + " with code: " + code);
        }
        FacebookProfile profile = client.requestUserData(token);

        // Create authentication // TODO: Must be AuthenticationManager
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);
        Authentication authentication = new UsernamePasswordAuthenticationToken(profile.getName(), profile, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        req.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        // Redirect to main page  // TODO: why geps? properties? request path?
        resp.sendRedirect("/geps/#!/");
    }

}
