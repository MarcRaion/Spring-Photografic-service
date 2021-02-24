package com.marcinha.stylist.service;


import com.marcinha.stylist.entity.CrmUser;
import com.marcinha.stylist.entity.Post;
import com.marcinha.stylist.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUserName(String userName);

    void save(CrmUser crmUser);

    List<User> getFriends(String userName);

    List<Post> getUserPost(Post author);


}
