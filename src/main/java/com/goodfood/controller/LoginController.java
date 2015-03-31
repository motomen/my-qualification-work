package com.goodfood.controller;

import com.goodfood.model.Role;
import com.goodfood.model.User;
import com.goodfood.service.RoleService;
import com.goodfood.service.UserService;
import com.goodfood.social.service.SocialMediaService;
import com.goodfood.util.SecurityUtil;
import com.goodfood.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.sql.Timestamp;

/**
 * Created by Yaroslav on 31.01.2015.
 */

@Controller
@SessionAttributes("user")
public class LoginController {

    final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final ProviderSignInUtils providerSignInUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ServletContext servletContext;

    public LoginController() {
        this.providerSignInUtils = new ProviderSignInUtils();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(WebRequest request, ModelMap model) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        User user = createRegistrationDTO(connection);
        model.addAttribute("user", user);
        return "registration";
    }

    private User createRegistrationDTO(Connection<?> connection) {
        User dto = new User();

        if (connection != null) {
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            dto.setMail(socialMediaProfile.getEmail());
            dto.setName(socialMediaProfile.getFirstName());
            dto.setNicname(socialMediaProfile.getUsername());

            ConnectionKey providerKey = connection.getKey();
            dto.setSignInProvider(SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase()));
        }

        return dto;
    }

    // @TODO: re-factor
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addNewUser(@RequestParam("photo") MultipartFile file,
                             @Valid User user,
                             BindingResult bindingResult,
                             WebRequest request) {
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
        /**
         * It is not a good idea to log in a user who has created a normal user account. Typically you want to send
         * a confirmation email which is used to verify his email address.
         * However, the example application works this way because it simplifies the registration process.
         */
        SecurityUtil.logInUser(user);
        ProviderSignInUtils.handlePostSignUp(user.getMail(), request);
        return "redirect:/";
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
}
