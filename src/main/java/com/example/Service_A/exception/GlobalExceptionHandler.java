package com.example.Service_A.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientResponseException;

import com.example.Service_A.utility.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private ResponseStructure responseStructure;

	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ResponseStructure> NullPointerException(NullPointerException e){
		responseStructure.setMessage("NullPointerException - Occured");
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
	
	@ExceptionHandler({org.springframework.web.client.HttpClientErrorException.class,HttpServerErrorException.class})
	public ResponseEntity<ResponseStructure>HttpClientErrorException(RestClientResponseException e){
		responseStructure.setMessage("URL Incorrect ");
		responseStructure.setStatus(HttpStatus.BAD_GATEWAY.value());
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		
	}
	
	@ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
	public ResponseEntity<ResponseStructure> DataIntegrityViolationException(DataIntegrityViolationException e){
	
		responseStructure.setMessage("--DataIntegrityViolationException--");
		responseStructure.setStatus(HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure> ConstraintViolationException(ConstraintViolationException a){
		responseStructure.setMessage("Invaild Data Passed ");
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		
		
	}


}
