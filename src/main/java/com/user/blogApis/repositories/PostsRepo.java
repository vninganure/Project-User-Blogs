package com.user.blogApis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.blogApis.entities.Category;
import com.user.blogApis.entities.Posts;
import com.user.blogApis.entities.User;

public interface PostsRepo extends JpaRepository<Posts, Integer>{
	
	List<Posts> findByUser(User user);
	List<Posts> findByCategory(Category category);
	
	@Query("select p from Posts p where p.title like :key")
	List<Posts> searchByTitle(@Param("key") String keyword);

}
