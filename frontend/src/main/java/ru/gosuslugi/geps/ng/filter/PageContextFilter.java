package ru.gosuslugi.geps.ng.filter;

import ru.gosuslugi.geps.ng.dao.UserDao;
import ru.gosuslugi.geps.ng.dao.impl.UserDaoImpl;
import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.security.AuthUtil;
import ru.gosuslugi.geps.ng.service.ServiceException;

import javax.servlet.*;
import java.io.IOException;

/**
 * User: rnasyrov
 * Date: 17.01.13
 * Time: 23:35
 */
public class PageContextFilter implements Filter {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String principal = AuthUtil.getUserPrincipal();
        if (principal != null) {
            try {
                User user = userDao.getById(Long.valueOf(principal));
                request.setAttribute("username", user.getName());
            } catch (ServiceException e) {
                throw new ServletException("User not found!");
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
