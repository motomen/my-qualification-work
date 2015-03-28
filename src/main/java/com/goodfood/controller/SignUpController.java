package com.goodfood.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Yaroslav on 29.03.2015.
 */
@Controller
public class SignUpController {
    final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage() {
        logger.debug("Redirecting request to registration page.");

        return "redirect:/registration";
    }
}
