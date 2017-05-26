package edu.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No data available")  // 404
public class DataNotFoundException extends RuntimeException {
	// ...
	/*@ResponseStatus(value=HttpStatus.CONFLICT, reason="Data integrity violation")  // 409
	@ExceptionHandler(DataIntegrityViolationException.class)
	public OrderNotFoundException conflict() {
		return null;
	}*/
}