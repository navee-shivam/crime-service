package com.crime.women.exception;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.crime.women.dto.ResponseDto;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionTest {

	@InjectMocks
	private GlobalException globalException;
	
	@Test
	void test_handleResourceNotFound() {
		NoResourceFoundException ex = new NoResourceFoundException(null, null);
		ResponseEntity<ResponseDto> response = globalException.handleResourceNotFound(ex);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void test_handleBadRequest() {
		BadRequestException ex = new BadRequestException();
		ResponseEntity<ResponseDto> response = globalException.handleBadRequest(ex);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void test_handleNotFound() {
		NotFoundException ex = new NotFoundException();
		ResponseEntity<ResponseDto> response = globalException.handleNotFound(ex);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
}
