package com.goodfood.social.user;

import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yaroslav on 06.04.2015.
 */
final class UserCookieGenerator {

    private final CookieGenerator userCookieGenerator = new CookieGenerator();

    public UserCookieGenerator() {
        userCookieGenerator.setCookieName("quickstart_user");
    }

    public void addCookie(String userId, HttpServletResponse response) {
        userCookieGenerator.addCookie(response, userId);
    }

    public void removeCookie(HttpServletResponse response) {
        userCookieGenerator.addCookie(response, "");
    }

    public String readCookieValue(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(userCookieGenerator.getCookieName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
