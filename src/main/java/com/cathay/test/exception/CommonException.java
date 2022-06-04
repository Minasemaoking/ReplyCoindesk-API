package com.cathay.test.exception;

import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException {

    private final HttpStatus httpStatus;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public CommonException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
