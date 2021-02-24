package com.marcinha.stylist.controller;

import com.marcinha.stylist.entity.Comments;
import com.marcinha.stylist.entity.Post;
import com.marcinha.stylist.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {


    private final PostService service;

    @Autowired
    public LoginController(PostService service) {
        this.service = service;
    }


    @GetMapping("/fancy")
    public String fancy(){
        return "fancy-login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){

        Comments comments = new Comments();
        List<Post> postList = service.findAll();

        model.addAttribute("posts", postList);
        model.addAttribute("comment", comments);

        return "dashboard";

    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id){

        System.out.println("Post id = " + id);
        service.deleteById(id);

        return "redirect:/dashboard";
    }




}