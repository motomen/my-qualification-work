package com.goodfood.controller;

import com.goodfood.model.Role;
import com.goodfood.model.User;
import com.goodfood.service.RoleService;
import com.goodfood.service.UserService;
import com.goodfood.service.impl.CustomUserDetailsService;
import com.goodfood.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
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
    private final ProviderSignInUtils providerSignInUtils;

    LoginController() {
        this.providerSignInUtils = new ProviderSignInUtils();
    }
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
        if (bindingResult.getAllErrors().size() == 0) {
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

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signupForm(WebRequest request, ModelMap modelMap) {
        CustomUserDetailsService userDetailsService = new CustomUserDetailsService();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection != null) {
            UserProfile providerUser = connection.fetchUserProfile();
            User userAuth = userService.getUserByEmail(providerUser.getEmail());
         //   SecurityContextHolder.getContext().setAuthentication(Authentication userDetailsService.loadUserByUsername(userAuth.getLogin()));

            User user = new User();
            user.setMail(providerUser.getEmail());
            user.setName(providerUser.getName());
            user.setNicname(providerUser.getUsername());
            modelMap.addAttribute("user", user);
            return "registration";
           // return SignupForm.fromProviderUser(connection.fetchUserProfile());
        } else {
            modelMap.addAttribute("user", new User());
            return "registration";
        }
    }
}
