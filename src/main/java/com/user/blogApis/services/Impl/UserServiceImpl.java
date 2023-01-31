package com.user.blogApis.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.blogApis.entities.User;
import com.user.blogApis.payloads.UserDto;
import com.user.blogApis.repositories.UserRepo;
import com.user.blogApis.services.UserService;
import com.user.blogApis.exceptions.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = dtoToUser(userDto);
		User saveduser = userRepo.save(user);
		
		
		return UserToDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(()->new ResourseNotFoundException("user", "Id", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto updatedDto = UserToDto(updatedUser);
		
		return updatedDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourseNotFoundException("user", " Id " , userId));
		
		return UserToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> allUsers = this.userRepo.findAll();
		List<UserDto> dtos = allUsers.stream().map(user->this.UserToDto(user)).collect(Collectors.toList());
		
		return dtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourseNotFoundException("User", "Id", userId));
		
		this.userRepo.delete(user);

	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user = modelMapper.map(userDto, User.class);
		
//		User user = new User();
//		
//		user.setId(userDto.getUserId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	private UserDto UserToDto(User user) {
		
		UserDto dto = modelMapper.map(user, UserDto.class);
		
//		UserDto dto = new UserDto();
//		
//		dto.setUserId(user.getId());
//		dto.setName(user.getName());
//		dto.setEmail(user.getEmail());
//		dto.setPassword(user.getPassword());
//		dto.setAbout(user.getAbout());
		
		return dto;
	}

}
