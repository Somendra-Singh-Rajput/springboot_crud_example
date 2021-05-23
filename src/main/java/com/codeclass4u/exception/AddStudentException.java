package com.codeclass4u.exception;

public class AddStudentException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public AddStudentException(String string) {
		super(string);
	}

}
