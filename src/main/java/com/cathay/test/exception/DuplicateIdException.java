package com.cathay.test.exception;

import org.springframework.http.HttpStatus;

public class DuplicateIdException extends CommonException {
	
	public DuplicateIdException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
