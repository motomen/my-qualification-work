package com.goodfood.util;

import com.goodfood.model.User;
import com.goodfood.social.dto.user.SimpleUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Yaroslav on 29.03.2015.
 */
public class SecurityUtil {

    public static void logInUser(User user) {
        final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
        logger.info("Logging in user: {}", user);

        SimpleUserDetails userDetails = SimpleUserDetails.getBuilder()
                .firstName(user.getName())
                .id((long) user.getIdUser())
                .password(user.getPassword())
                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getMail())
                .build();
        logger.debug("Logging in principal: {}", userDetails);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info("User: {} has been logged in.", userDetails);
    }
}