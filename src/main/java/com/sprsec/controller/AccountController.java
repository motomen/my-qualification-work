package com.sprsec.controller;

import com.sprsec.model.User;
import com.sprsec.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yaroslav on 31.01.2015.
 */
@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/showaccount", method = RequestMethod.GET)
    public ModelAndView sowInformation() {
        User user = userService.getUser("vasil");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("myImage", user.getPhoto()); //MyImage (datatype 'byte[]') is the image retrieved from DB
        return new ModelAndView("account", model); //display is the name of jsp on which you want to display image
    }

}
