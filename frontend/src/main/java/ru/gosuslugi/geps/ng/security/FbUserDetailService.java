package ru.gosuslugi.geps.ng.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

/**
 * User: renatn
 * Date: 28.12.12
 * Time: 14:21
 */

public class FbUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Load: " + username);

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);

        return new User(username, "N/A", authorities);
    }
}
