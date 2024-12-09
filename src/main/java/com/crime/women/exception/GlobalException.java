package com.crime.women.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.crime.women.dto.ResponseDto;

/**
 * Handles the exception globally and it handles the custom Exception
 * NotFoundException
 */
@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ResponseDto> handleResourceNotFound(Exception ex) {
		ResponseDto response = new ResponseDto(ex.getLocalizedMessage(), null);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ResponseDto> handleBadRequest(Exception ex) {
		ResponseDto response = new ResponseDto(ex.getLocalizedMessage(), null);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseDto> handleNotFound(Exception ex) {
		ResponseDto response = new ResponseDto(ex.getMessage(), null);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.NO_CONTENT);
	}

}
