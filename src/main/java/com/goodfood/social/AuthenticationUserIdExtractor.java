package com.goodfood.social;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
/**
 * Created by Yaroslav on 10.04.2015.
 */
public class AuthenticationUserIdExtractor implements UserIdSource {
    @Override
    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
        }
        return authentication.getName();
    }
}
