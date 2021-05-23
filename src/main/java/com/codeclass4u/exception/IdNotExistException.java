package com.codeclass4u.exception;

public class IdNotExistException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public IdNotExistException(String string) {
		super(string);
	}

}
