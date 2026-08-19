package com.otaviosr.registrocliente.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.otaviosr.registrocliente.dto.CustomError;
import com.otaviosr.registrocliente.dto.ValidationError;
import com.otaviosr.registrocliente.services.exceptions.DataBaseException;
import com.otaviosr.registrocliente.services.exceptions.ResourceNotFundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFundException.class)
	public ResponseEntity<CustomError> resoureNotFound(ResourceNotFundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<CustomError> database(DataBaseException e, HttpServletRequest request) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
			HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;
			ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados inválidos!", request.getRequestURI());
			
			for(FieldError f : e.getBindingResult().getFieldErrors()) {
				err.AddError(f.getField(), f.getDefaultMessage());
			}
			return ResponseEntity.status(status).body(err);
	}
}
