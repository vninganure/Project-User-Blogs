package com.user.blogApis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.blogApis.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
