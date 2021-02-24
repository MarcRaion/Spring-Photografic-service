package com.marcinha.stylist.service;

import com.marcinha.stylist.entity.Comments;
import com.marcinha.stylist.entity.Post;
import com.marcinha.stylist.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository repository;

    private Logger logger = Logger.getLogger(getClass().getName());


    public List<Comments> findAll(){

        return repository.findAll();
    }


    public void save(Comments comments){
        repository.save(comments);

        logger.info("Comments saved");
    }
}
