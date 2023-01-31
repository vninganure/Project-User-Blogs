package com.user.blogApis.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int userId;
	
	@NotEmpty
	@Size(min = 3, message="User name should be at least 4 Characters")
	private String name;
	
	@Email(message="Email is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=10, message="Password length should be minimum 3 and maximum 10")
	private String password;
	
	@NotEmpty(message="please enter some details")
	private String about;
}
