package com.user.blogApis.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.h2.mvstore.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.user.blogApis.entities.Category;
import com.user.blogApis.entities.Posts;
import com.user.blogApis.entities.User;
import com.user.blogApis.exceptions.ResourseNotFoundException;
import com.user.blogApis.payloads.CategoryDto;
import com.user.blogApis.payloads.PostsDto;
import com.user.blogApis.repositories.CategoryRepo;
import com.user.blogApis.repositories.PostsRepo;
import com.user.blogApis.repositories.UserRepo;
import com.user.blogApis.services.PostsService;
import com.user.blogApis.services.UserService;

@Service
public class PostsServiceImpl implements PostsService{
	
	@Autowired
	private PostsRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	

	@Override
	public PostsDto createPosts(PostsDto postsDto, Integer userId, Integer categoryId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(()->new ResourseNotFoundException("User", "User Id", userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourseNotFoundException("Category", "categoryId Id", categoryId));
		
		Posts posts = this.modelMapper.map(postsDto, Posts.class);
		posts.setImageName("default.jpg");
		posts.setAddedDate(new Date());
		posts.setCategory(category);
		posts.setUser(user);
		
		Posts savedPost = postRepo.save(posts);
		
		return this.modelMapper.map(savedPost, PostsDto.class);
	}

	@Override
	public PostsDto updatePosts(PostsDto postsDto, Integer postId) {
		Posts currentPost = this.postRepo.findById(postId).orElseThrow(()->new ResourseNotFoundException("post", "PostId", postId));
		currentPost.setTitle(postsDto.getTitle());
		currentPost.setContent(postsDto.getContent());
		currentPost.setImageName(postsDto.getImageName());

		return this.modelMapper.map(currentPost, PostsDto.class);
	}

	@Override
	public void deletePosts(Integer postId) {
		Posts currentPost = this.postRepo.findById(postId).orElseThrow(()->new ResourseNotFoundException("post", "PostId", postId));
		this.postRepo.delete(currentPost);
	}

	@Override
	public List<PostsDto> getAllPosts() {
		
		List<Posts> posts = this.postRepo.findAll();
		
//		
		List<PostsDto> postsDtos = posts.stream().map((post)->this.modelMapper.map(post, PostsDto.class)).collect(Collectors.toList());
		return postsDtos;
	}

	@Override
	public PostsDto getPostsById(Integer postId) {
		Posts currentPost = this.postRepo.findById(postId).orElseThrow(()->new ResourseNotFoundException("post", "PostId", postId));
		return this.modelMapper.map(currentPost, PostsDto.class);
	}

	@Override
	public List<PostsDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostsDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostsDto> searchPosts(String keyWord) {
		List<Posts> posts = postRepo.searchByTitle("%"+keyWord+"%");
		
		return posts.stream().map(post -> this.modelMapper.map(post, PostsDto.class)).collect(Collectors.toList());
	}

}
