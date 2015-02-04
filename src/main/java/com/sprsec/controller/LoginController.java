package com.sprsec.controller;

import com.sprsec.model.Role;
import com.sprsec.model.User;
import com.sprsec.service.user.RoleService;
import com.sprsec.service.user.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Yaroslav on 31.01.2015.
 */

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(HttpServletRequest request, ModelMap model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addNewUser(@RequestParam("file") MultipartFile file,
                             @RequestParam("login") String login,
                             @RequestParam("nicname") String nicname,
                             @RequestParam("password") String password,
                             @RequestParam("mail") String mail,
                             ModelMap map) {

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setNicname(nicname);
        user.setMail(mail);
        Blob blob;
        if (file.getSize() != 0) {
            try {
                byte[] img = file.getBytes();
                blob = new javax.sql.rowset.serial.SerialBlob(img);
                user.setPhoto(blob);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SerialException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Resource resource = new ClassPathResource("../WEB-INF/user.jpg");
            try {
                byte[] img = IOUtils.toByteArray(resource.getInputStream());
                blob = new javax.sql.rowset.serial.SerialBlob(img);
                user.setPhoto(blob);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SerialException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        addUser(user);
        return "redirect:/";
    }

    private void addUser(User user) {
        //role id 2 = User
        java.util.Date date = new java.util.Date();
        user.setDateReg(new Timestamp(date.getTime()));
        Role role = roleService.getRole(2);
        user.setRole(role);
        userService.addUser(user);
    }
}
