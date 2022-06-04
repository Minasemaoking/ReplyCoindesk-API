package com.cathay.test.vo;

public class ThrowableResponseVo extends CommonResponseVo {

	private String errorMessage;
	
    public ThrowableResponseVo(String result, String errorMessage) {
        super(result);
        this.errorMessage = errorMessage;
    }

    
    public ThrowableResponseVo(Throwable ex) {
        this(ex.getMessage());
    }
    
    public ThrowableResponseVo(String errorMessage) {
        this("UNEXPECTED_ERROR", errorMessage);
    }
        
    public ThrowableResponseVo(String result, Throwable ex) {
        this(result, ex.getMessage());
    }

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
