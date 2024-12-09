package com.crime.women.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crime.women.dto.RequestDto;
import com.crime.women.dto.ResponseDto;
import com.crime.women.service.CrimeService;

@ExtendWith(MockitoExtension.class)
class CrimeControllerTest {

	@InjectMocks
	private CrimeController crimeController;
	
	@Mock
	private CrimeService crimeService;
	
	@Test
	void test_getData() {
		int page = 1;
		int size = 50;
		when(crimeService.getDetails(page, size)).thenReturn(new ResponseDto());
		
		ResponseEntity<ResponseDto> response = crimeController.fetchAllData(page, size);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void test_getCrimeDetailsCount() {
		RequestDto request = new RequestDto("Assam");
		
		when(crimeService.getCrimeDetailsCount(request)).thenReturn(new ResponseDto());
		
		ResponseEntity<ResponseDto> response = crimeController.getCrimeDetailsCount(request);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void test_getCrimeDetailsCount_WithEmptyRequest() {
		RequestDto request = new RequestDto();
		
		when(crimeService.getCrimeDetailsCount(request)).thenReturn(new ResponseDto());
		
		ResponseEntity<ResponseDto> response = crimeController.getCrimeDetailsCount(request);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
