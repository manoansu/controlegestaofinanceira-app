package pt.amane.controlegestaofinanceiraapp.resources.exceptions;


import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pt.amane.controlegestaofinanceiraapp.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotfound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(Instant.now(), status.value(), "Object not found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError error = new ValidationError();
		error.setTimeStamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Validation exception");
		error.setError(e.getMessage());
		error.setPath(request.getRequestURI());
		
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			error.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(error);
	}

}
