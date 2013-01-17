package ru.gosuslugi.geps.ng.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User: renatn
 * Date: 15.01.13
 * Time: 13:52
 */
public class AuthUtil {

    public static String getUserPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return null;
        }

        if (!(authentication.getPrincipal() instanceof UserDetails)) {
            return null;
        }

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }


}
