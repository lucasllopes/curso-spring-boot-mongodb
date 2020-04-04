package com.lucas.springbootmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucas.springbootmongo.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler  {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException obj,HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError er = new StandardError(System.currentTimeMillis(),status.value(),"Nao Encontrado",obj.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(er);
				
	}

}
