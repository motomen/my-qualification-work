package com.goodfood.controller;

import com.goodfood.model.Role;
import com.goodfood.model.User;
import com.goodfood.service.RoleService;
import com.goodfood.service.UserService;
import com.goodfood.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Yaroslav on 31.01.2015.
 */

@Controller
public class LoginController {

    final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(HttpServletRequest request, ModelMap model) {
        model.addAttribute("user", new User());
        logger.info("show page registration user");
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addNewUser(@RequestParam("photo") MultipartFile file,
                             @RequestParam("login") String login,
                             @RequestParam("nicname") String nicname,
                             @RequestParam("password") String password,
                             @RequestParam("mail") String mail) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setNicname(nicname);
        user.setMail(mail);
        if (file.getSize() != 0) {
            user.setPhoto(Util.fileToString(file));
        } else {
            File resource = new File(servletContext.getRealPath("/") + "/resources/img/user.jpg");
            if (resource.exists()) {
                try {
                    user.setPhoto(Util.fileToString(resource));
                } catch (Exception e) {
                    logger.info("error convert user photo");
                    e.printStackTrace();
                }
            }
        }
        addUser(user);
        logger.info("add new user with nicname " + user.getNicname());
        return "redirect:/";
    }

    private void addUser(User user) {
        user.setDateReg(new Date());
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
            @RequestParam("password") String password) {
        String username = name;
        String password2 = password;
        return "0k";
    }
}
