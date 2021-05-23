package com.codeclass4u.exception;

public class NoStudentFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NoStudentFoundException(String string) {
		super(string);
	}

}
