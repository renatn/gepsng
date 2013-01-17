package ru.gosuslugi.geps.ng.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import ru.gosuslugi.geps.ng.dao.UserDao;
import ru.gosuslugi.geps.ng.dao.impl.UserDaoImpl;
import ru.gosuslugi.geps.ng.facebook.FacebookClient;
import ru.gosuslugi.geps.ng.facebook.FacebookProfile;
import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: renatn
 * Date: 28.12.12
 * Time: 14:31
 */
public class FbSignInFilter extends AbstractAuthenticationProcessingFilter {

    protected final Log logger = LogFactory.getLog(getClass());

    private String clientId;
    private String secret;
    private String siteUrl;

    private UserDao userDao = new UserDaoImpl();

    protected FbSignInFilter() {
        super("/signin");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException, ServletException {
        String code = req.getParameter("code");
        if (code == null || code.equals("")) {
            throw new AuthenticationServiceException("Facebook code is empty!");
        }

        FacebookClient client = new FacebookClient(clientId, secret, siteUrl);

        String token;
        try {
            token = client.requestAccessToken(code);
        } catch (IOException e) {
            //TODO: log exception
            throw new AuthenticationServiceException("Error on requesting facebook access token");
        }

        FacebookProfile profile;
        try {
             profile = client.requestProfile(token);
        } catch (IOException e) {
            //TODO: log exception
            throw new AuthenticationServiceException("Error while request facebook user profile");
        }

        // Create new user if not exists
        User user;
        try {
            user = userDao.findByFacebookId(profile.getId());
            if (user == null) {
                ru.gosuslugi.geps.ng.model.User newUser = new ru.gosuslugi.geps.ng.model.User();
                newUser.setFacebookId(profile.getId());
                newUser.setName(profile.getName());
                user = userDao.create(newUser);
            }
        } catch (ServiceException e) {
            throw new AuthenticationServiceException("Error on fetching user from database");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Facebook authentication success. User: " + profile.getName());
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUserId().toString(), "N/A");
        authentication.setDetails(authenticationDetailsSource.buildDetails(req));
        return this.getAuthenticationManager().authenticate(authentication);

    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
}
