package com.nk.globaladvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nk.exception.InvalidSSNException;

@RestControllerAdvice
public class CitizenAppRegistrationControllerAdvice {
		
		@ExceptionHandler(InvalidSSNException.class)
		public ResponseEntity<String> handleInvalidSSNException(InvalidSSNException ex){
			System.out.println("handleInvalidSSNException ::");
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<String> handleAllException(Exception ex){
			System.out.println("handleAllException ::");
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
		}
}
