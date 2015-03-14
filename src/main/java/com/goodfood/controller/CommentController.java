package com.goodfood.controller;

import com.goodfood.auth.IAuthenticationFacade;
import com.goodfood.model.Comments;
import com.goodfood.model.Food;
import com.goodfood.model.User;
import com.goodfood.service.CommentService;
import com.goodfood.service.FoodService;
import com.goodfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Yaroslav on 19.02.2015.
 */

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private IAuthenticationFacade authentication;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addcomment/{id}", method = RequestMethod.POST)
    public String addCommentToFood(@PathVariable("id") String idFood,
                                   @ModelAttribute("comment") Comments comments,
                                   @RequestParam("textComment") String text) {
        String userName = authentication.getUserName();
        User user = userService.getUser(userName);
        comments.setUser(user);
        comments.setTextComment(text);
        comments.setDateComment(new Date());
        Food food = foodService.getFoodById(idFood);
        comments.setFood(food);
        commentService.save(comments);
        return "redirect:/showfood/" + idFood;
    }

    @PreAuthorize("hasRole('Admin') or hasRole('User')")
    @RequestMapping(value = "/comments/{idFood}/delete/{idComment}", method = RequestMethod.POST)
    public String deleteComment(
            @PathVariable("idComment") int id,
            @PathVariable("idFood") String idFood
    ) {
        commentService.delete(commentService.getCommentById(id));
        return "redirect:/showfood/" + idFood;
    }
}
