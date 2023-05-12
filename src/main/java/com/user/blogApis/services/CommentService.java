package com.user.blogApis.services;

import com.user.blogApis.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);
	
	void deleteComment(int commentId);
}
