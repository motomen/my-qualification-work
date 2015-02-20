package com.sprsec.auth;

import org.springframework.security.core.Authentication;

/**
 * Created by Yaroslav on 19.02.2015.
 */
public interface IAuthenticationFacade {
    Authentication getAuthentication();
    String getUserName();
}
