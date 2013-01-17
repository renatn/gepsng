package ru.gosuslugi.geps.ng.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.gosuslugi.geps.ng.dao.UserDao;
import ru.gosuslugi.geps.ng.dao.impl.UserDaoImpl;
import ru.gosuslugi.geps.ng.service.ServiceException;

import java.util.Collection;
import java.util.Collections;

/**
 * User: renatn
 * Date: 28.12.12
 * Time: 14:21
 */

public class FbUserDetailService implements UserDetailsService {

    private UserDao userDao = new UserDaoImpl();

    //   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ru.gosuslugi.geps.ng.model.User found;
        try {
            found = userDao.getById(Long.valueOf(username));
            if (found == null) {
                throw new UsernameNotFoundException("User not found!");
            }
        } catch (ServiceException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);

        return new User(found.getUserId().toString(), "N/A", authorities);
    }


}
