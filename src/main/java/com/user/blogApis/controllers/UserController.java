package com.user.blogApis.controllers;

import java.util.List;

import javax.validation.Valid;

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
import com.user.blogApis.payloads.UserDto;
import com.user.blogApis.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	//Create User
	@PostMapping("/createUser/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser = this.userService.createUser(userDto);
		
		return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
	}
	
	//Update User
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto updatedDto = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedDto);
	}
	
	//Delete User
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}
	
	//Get All Users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> list = this.userService.getAllUsers();
		return ResponseEntity.ok(list);
	}
	
	//Get Single User
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		UserDto userDto = this.userService.getUserById(userId);
		return ResponseEntity.ok(userDto);
	}
}
