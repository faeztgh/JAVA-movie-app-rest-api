package com.faez.movie.utils.functions;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Functions {

    public static boolean isUserHaveAuthority(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getAuthorities() == null) {
            return false;
        }
        return auth.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }
}
