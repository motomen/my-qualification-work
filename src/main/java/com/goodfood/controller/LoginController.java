package com.goodfood.controller;

import com.goodfood.model.Role;
import com.goodfood.model.User;
import com.goodfood.service.RoleService;
import com.goodfood.service.UserService;
import com.goodfood.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.sql.Timestamp;

/**
 * Created by Yaroslav on 31.01.2015.
 */

@Controller
public class LoginController {

    final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(HttpServletRequest request, ModelMap model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    // @TODO: re-factor
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addNewUser(@RequestParam("photo") MultipartFile file,
                             @Valid User user,
                             BindingResult bindingResult) {
        // Don't show error's customer about unique login
        Boolean unique = userService.isLoginIdUnique(user.getLogin());
        if (!unique) {
            bindingResult.rejectValue("login", "user must be unique ", new Object[] { user.getLogin() }, null);
        }
        if (bindingResult.hasErrors()) {
            logger.error("return in registation page");
            return "registration";
        }

        if (file.getSize() != 0) {
            user.setPhoto(Util.fileToString(file));
        } else {
            File resource = new File(servletContext.getRealPath("/") + "/resources/img/user.jpg");
            if (resource.exists()){
                try {
                    user.setPhoto(Util.fileToString(resource));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        addUser(user);
        return "redirect:/login";
    }

    private void addUser(User user) {
        java.util.Date date = new java.util.Date();
        user.setDateReg(new Timestamp(date.getTime()));
        //role id 2 = User
        Role role = roleService.getRole(2);
        user.setRole(role);
        userService.addUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.info("Login page");
        return "login";
    }

    @RequestMapping(value = "/apicheck", method = RequestMethod.POST)
    @ResponseBody
    public String apicheck(
            @RequestParam("username") String name,
            @RequestParam("password") String password
    ){
        String username = name;
        String password2 = password;
        return "0k";
    }
}
