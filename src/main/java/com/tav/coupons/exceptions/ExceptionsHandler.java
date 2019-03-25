package com.tav.coupons.exceptions;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

	// Handles ApplicationExceptions
	@ExceptionHandler(ApplicationException.class)
	public void handleConflict(HttpServletResponse response, ApplicationException e) {
		e.printStackTrace();
		String errorMessage = e.getMessage();
		response.setStatus(e.getErrorType().getInternalErrorCode());
		response.setHeader("errorMessage", errorMessage);
	}
	
	// Handles all other exceptions
	@ExceptionHandler(Exception.class)
	public void handleConflict(HttpServletResponse response, Exception e) {
		e.printStackTrace();
		String internalMessage = e.getMessage();
		response.setStatus(500);
		response.setHeader("errorMessage", internalMessage);
	}
}


