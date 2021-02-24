package com.marcinha.stylist.repository;

import com.marcinha.stylist.entity.CrmUser;
import com.marcinha.stylist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);







}
