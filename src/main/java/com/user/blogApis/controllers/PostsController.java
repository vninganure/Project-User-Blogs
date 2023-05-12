package com.user.blogApis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.blogApis.payloads.ApiResponse;
import com.user.blogApis.payloads.PostsDto;
import com.user.blogApis.services.PostsService;

@RestController
@RequestMapping("/api")
public class PostsController {

	
	@Autowired
	private PostsService postsService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts/create/")
	public ResponseEntity<PostsDto> createPosts(@RequestBody PostsDto postsDto, 
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		
		PostsDto createdPost= this.postsService.createPosts(postsDto, userId, categoryId);
		
		return new ResponseEntity<PostsDto>(createdPost, HttpStatus.CREATED);
	}
	
	@PutMapping("/postsupdate/{postId}")
	public ResponseEntity<PostsDto> updatePosts(@RequestBody PostsDto postsDto, @PathVariable Integer postId){
		PostsDto updatedPost = this.postsService.updatePosts(postsDto, postId);
		return new ResponseEntity<PostsDto>(updatedPost, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletepost/{postId}")
	public ApiResponse deletePosts(@PathVariable Integer postId) {
		this.postsService.deletePosts(postId);
		return new ApiResponse("Post has been deleted succesfully", true);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostsDto>> getAllPost(){
		List<PostsDto> postsDtos = postsService.getAllPosts();
		
		return new ResponseEntity<List<PostsDto>>(postsDtos, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostsDto> getById(@PathVariable Integer postId){
		PostsDto postsDto = postsService.getPostsById(postId);
		return new ResponseEntity<PostsDto>(postsDto, HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostsDto>> searchPosts(@PathVariable String keyword){
		List<PostsDto> dtos = this.postsService.searchPosts(keyword);
		return new ResponseEntity<List<PostsDto>>(dtos, HttpStatus.OK);
	}
}
