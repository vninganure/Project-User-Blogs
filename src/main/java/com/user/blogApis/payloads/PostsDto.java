package com.user.blogApis.payloads;

import java.util.Date;

import com.user.blogApis.entities.Category;
import com.user.blogApis.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsDto {
	
	private int postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	private UserDto user;
}
