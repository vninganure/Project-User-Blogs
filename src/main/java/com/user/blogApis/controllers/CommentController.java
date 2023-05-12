package com.user.blogApis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.blogApis.payloads.ApiResponse;
import com.user.blogApis.payloads.CommentDto;
import com.user.blogApis.services.CommentService;
import com.user.blogApis.services.PostsService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/user/{userId}/post/{postId}/create_comment")
	public ResponseEntity<CommentDto> createComment(
			@RequestBody CommentDto commentDto,
			@PathVariable("userId") Integer userId,
			@PathVariable("postId") Integer postId){
		
		CommentDto added_comment = this.commentService.createComment(commentDto, userId, postId);
		
		
		return new ResponseEntity<CommentDto>(added_comment, HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/delete_comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully deleted", true), HttpStatus.OK);
	}

}
