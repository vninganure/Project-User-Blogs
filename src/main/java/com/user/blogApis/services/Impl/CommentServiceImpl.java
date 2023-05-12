package com.user.blogApis.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.blogApis.entities.Comment;
import com.user.blogApis.entities.Posts;
import com.user.blogApis.entities.User;
import com.user.blogApis.exceptions.ResourseNotFoundException;
import com.user.blogApis.payloads.CommentDto;
import com.user.blogApis.repositories.CommentRepo;
import com.user.blogApis.repositories.PostsRepo;
import com.user.blogApis.repositories.UserRepo;
import com.user.blogApis.services.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostsRepo postRepo;
	
	@Autowired
	private UserRepo UserRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
		// TODO Auto-generated method stub
		User user = this.UserRepo.findById(userId)
				.orElseThrow(()->new ResourseNotFoundException("User", "userId", userId));
				
		Posts post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourseNotFoundException("Post", "postId", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		comment.setUser(user);
		
				
		return this.modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		// TODO Auto-generated method stub
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(()->new ResourseNotFoundException("Comment", "commentId", commentId));
		
		this.commentRepo.delete(comment);

	}

}
