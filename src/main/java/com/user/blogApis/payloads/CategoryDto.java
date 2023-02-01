package com.user.blogApis.payloads;

import javax.persistence.Entity;

//import org.junit.platform.commons.PreconditionViolationException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	private String categoryName;
	private String categoryDescription;
}
