package com.marcinha.stylist.controller;


import com.marcinha.stylist.entity.Post;
import com.marcinha.stylist.entity.User;
import com.marcinha.stylist.service.PostService;
import com.marcinha.stylist.service.UserService;
import com.marcinha.stylist.validation.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

import java.util.*;
import java.util.logging.Logger;

@Controller
public class ProfileControler {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    private Logger logger = Logger.getLogger(getClass().getName());


    @GetMapping("/profile")
    public String goToProfile(Model model, Principal principal) {

        int count = 0;

        //prinicipal is logged in user
        String username = principal.getName();
        logger.info("Logged user is: " + principal.getName());

        model.addAttribute("user", username);

        //posts
        List<Post> postList = postService.findAll();
        List<Post> userList = new ArrayList();

        for (Post temp : postList) {
            if (temp.getAuthor().equals(username)) {
                userList.add(temp);
                count++;
            }
        }

        model.addAttribute("posts", userList);
        model.addAttribute("count", count);

        return "profile";
    }

    @GetMapping("/contains/{postId}")
    public String allowed(@PathVariable("postId") Long postId, Principal principal, Model model) {

        boolean flag = false;
        Post post = postService.findPostById(postId);

        if (principal.getName().equals(post.getAuthor())) {
            return "redirect:/profile";
        }

        String userName = post.getAuthor();
        logger.info("Username is " + userName);

        List<User> friends = userService.getFriends(userName);
        logger.info("User friends: " + userService.getFriends(userName));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String current = authentication.getName();

        for (User temp : friends) {
            if (temp.getUsername().equals(current)) {
                flag = true;
            }
        }

        if (flag){
            model.addAttribute("list", userService.getUserPost(post));
            model.addAttribute("post",post);
            model.addAttribute("count", userService.getUserPost(post).size());

            return "allowed-seeing-content";
        }
        else{

            model.addAttribute("author", post.getAuthor());
            return "private-profile";
        }
    }

    @GetMapping("/showFormForAdd")
    public String addPost(Model model) {
        Post post = new Post();

        model.addAttribute("post", post);
        return "add_new";
    }

    @PostMapping("/addingPost")
    public String adding(@RequestParam("file") MultipartFile file,
                         @ModelAttribute("post")Post post,Principal principal) {

        Date date = new Date();
        logger.info("Processing registration form for: " + post.getTitle());

        try {
            post.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            System.out.println("tu bląd");
            e.printStackTrace();
        }
        post.setAuthor(principal.getName());
        post.setAddingDate(date);
        post.setCountLikes(0);

        postService.save(post);
        return "redirect:/profile";
    }
}
