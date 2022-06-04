package com.cathay.test.exception;

import org.springframework.http.HttpStatus;

public class IdDoesNotExistException extends CommonException {

	public IdDoesNotExistException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
