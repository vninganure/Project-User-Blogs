package com.user.blogApis.services;

import java.util.List;

import com.user.blogApis.entities.Posts;
import com.user.blogApis.payloads.PostsDto;


public interface PostsService {
	
	PostsDto createPosts(PostsDto postsDto, Integer userId, Integer categoryId);
	
	//Update
	
	PostsDto updatePosts(PostsDto postsDto, Integer postId);
	
	//Delete
	
	void deletePosts(Integer postId);
	
	//get All
	List<PostsDto> getAllPosts();
	
	//getOne
	PostsDto getPostsById(Integer postId);
	
	//user posts
	List<PostsDto> getPostsByUser(Integer userId);
	
	//post of category
	List<PostsDto> getPostsByCategory(Integer categoryId);
	
	//search by keyWord
	List<PostsDto> searchPosts(String keyWord);
	
	
	

}
