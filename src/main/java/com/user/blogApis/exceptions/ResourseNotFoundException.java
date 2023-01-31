package com.user.blogApis.exceptions;

public class ResourseNotFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldValue;
	public ResourseNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s :%s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
