package com.marcinha.stylist.repository;

import com.marcinha.stylist.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {



    @Override
    void deleteById(Long aLong);

    Post findPostById(Long id);
}
