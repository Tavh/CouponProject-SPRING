package com.tav.coupons.exceptions;
import com.tav.coupons.enums.ErrorType;

public class ApplicationException extends Exception {

	private ErrorType errorType;

	// This is used when the exception is intentionally thrown
	public ApplicationException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}

	public ApplicationException(ErrorType errorType, String message, Exception e) {
		super(message, e);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}
	
	
}

