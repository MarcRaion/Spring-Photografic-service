package com.marcinha.stylist.service;

import com.marcinha.stylist.entity.CrmUser;
import com.marcinha.stylist.entity.Post;
import com.marcinha.stylist.entity.Role;
import com.marcinha.stylist.entity.User;
import com.marcinha.stylist.repository.RoleRepository;
import com.marcinha.stylist.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserServiceIMPL implements UserService {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private BCryptPasswordEncoder bCrypt;


    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String userName) {
        // check the database if the user already exists
        return userRepository.findByUsername(userName);
    }

    @Override
    public void save(CrmUser crmUser) {

        Role role = new Role();
        List<Role> roles = new ArrayList<>();
        User user = new User();

        user.setUsername(crmUser.getUserName());
        user.setFirstName(crmUser.getFirstName());
        user.setLastName(crmUser.getLastName());
        user.setPassword(bCrypt.encode(crmUser.getPassword()));
        user.setEmail(crmUser.getEmail());
        user.setCountryId(1);


        userRepository.save(user);


    }

    @Override
    public List<User> getFriends(String userName) {
        User user = userRepository.findByUsername(userName);

        logger.info("From service: "+user.getUsername()+" and list: " +user.getFriends());
        return user.getFriends();

    }

    public List<Post> getUserPost( Post author){
        User user = new User();
        user.setUsername(author.getAuthor());

        List<Post> temp = postService.findAll();
        List<Post> list = new ArrayList<>();

        for(Post post: temp){
            if(user.getUsername().equals(post.getAuthor())){
                list.add(post);
            }
        }
        return list;
    }


}
