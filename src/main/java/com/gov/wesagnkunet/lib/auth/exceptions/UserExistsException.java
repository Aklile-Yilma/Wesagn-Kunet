package com.gov.wesagnkunet.lib.auth.exceptions;

public class UserExistsException extends RuntimeException{

	public UserExistsException(){
		super();
	}

	public UserExistsException(String msg){
		super(msg);
	}
	
}
