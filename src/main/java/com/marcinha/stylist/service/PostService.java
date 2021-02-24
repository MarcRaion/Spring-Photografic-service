package com.marcinha.stylist.service;

import com.marcinha.stylist.entity.Post;
import com.marcinha.stylist.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PostService {

    private Logger logger = Logger.getLogger(getClass().getName());
    private final PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }


    public List<Post>findAll(){

        return repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Post findPostById(Long aLong){
       return repository.findPostById(aLong);
    }

    public void save(Post post){


        repository.save(post);
        logger.info("Post is saved");
        System.out.println(post.getImage());
    }



}
