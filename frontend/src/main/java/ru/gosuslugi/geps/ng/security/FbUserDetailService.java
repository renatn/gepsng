package ru.gosuslugi.geps.ng.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * User: renatn
 * Date: 28.12.12
 * Time: 14:21
 */

public class FbUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("Load: " + s);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
