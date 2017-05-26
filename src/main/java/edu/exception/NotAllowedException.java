package edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="This action is not allowed")  // 400
public class NotAllowedException extends RuntimeException {
}
