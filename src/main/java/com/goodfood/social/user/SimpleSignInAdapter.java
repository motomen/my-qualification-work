package com.goodfood.social.user;

import com.goodfood.model.User;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yaroslav on 08.04.2015.
 */
public final class SimpleSignInAdapter implements SignInAdapter {

    private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();

    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        SecurityContext.setCurrentUser(new User(userId));
        userCookieGenerator.addCookie(userId, request.getNativeResponse(HttpServletResponse.class));
        return null;
    }

}
