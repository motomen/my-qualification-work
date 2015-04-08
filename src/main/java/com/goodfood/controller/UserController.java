package com.goodfood.controller;

import com.goodfood.model.User;
import com.goodfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Yaroslav on 08.04.2015.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/showuser/{idUser}", method = RequestMethod.GET)
    public String showUser(
            @PathVariable("idUser") int idUser,
            ModelMap modelMap) {
        User user = userService.getUser(idUser);
        modelMap.addAttribute("user", userService.getUser(idUser));
        return "showuser";
    }
}
