package com.marcinha.stylist.controller;

import com.marcinha.stylist.entity.Comments;
import com.marcinha.stylist.entity.Post;
import com.marcinha.stylist.entity.User;
import com.marcinha.stylist.repository.UserRepository;
import com.marcinha.stylist.service.CommentsService;
import com.marcinha.stylist.service.PostService;
import com.marcinha.stylist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.logging.Logger;

@Controller
public class ActionOn {


    @Autowired
    private UserRepository repository;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private PostService postService;

    private Logger logger = Logger.getLogger(getClass().getName());


    @GetMapping("/contains/follow/{author}")
    public String followUser(@PathVariable("author") String author, Principal principal){

        System.out.println("Author that you wanna follow is " + author);

        //user loged in
        User user;
        String username = principal.getName();
        user = repository.findByUsername(username);

        //user to follow
        User user2;
        user2 = repository.findByUsername(author);

        user2.follow(user);

        repository.save(user2);

        return "redirect:/profile";
    }

//    @PostMapping("/like")
//    public void like(@RequestParam(value="checkbox",required = false) boolean checkbox){
//
//        if(checkbox){
//            System.out.println("checkobox is checked");
//        }
//        else{
//            System.out.println("checkobox is not checked");
//        }
//    }

    @PostMapping("/addComment/{id}")
    public String addComment(@PathVariable("id") Long id, @ModelAttribute("comment")Comments comment,
                           Principal principal){

        Post post = postService.findPostById(id);
        User user = repository.findByUsername(principal.getName());

        Date date = new Date();
        comment.setDate(date);

        comment.setUsers(user);
        comment.setPosts(post);
        commentsService.save(comment);

        return "redirect:/dashboard";
    }
}
