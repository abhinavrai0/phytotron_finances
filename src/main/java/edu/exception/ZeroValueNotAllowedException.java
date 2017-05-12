package edu.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="value not allowed")  // 404
public class ZeroValueNotAllowedException extends ValueNotAllowedException{
}
