package com.marcinha.stylist.repository;

import com.marcinha.stylist.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Integer> {
}
