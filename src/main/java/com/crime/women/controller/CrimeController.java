package com.crime.women.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crime.women.dto.RequestDto;
import com.crime.women.dto.ResponseDto;
import com.crime.women.service.CrimeService;
import com.crime.women.service.CrimeServiceImpl;
import com.crime.women.utils.Constants;

@RestController
@RequestMapping(Constants.VERSION)
public class CrimeController {

	private final CrimeService crimeService;

	public CrimeController(CrimeServiceImpl crimeServiceImpl) {
		this.crimeService = crimeServiceImpl;
	}

	@GetMapping("fetch")
	public String getWelcome() {
		return crimeService.getWelcome();
	}

	@GetMapping("getList")
	public ResponseEntity<ResponseDto> getData(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "100") int size) {
		return new ResponseEntity<>(crimeService.getDetails(page, size), HttpStatus.OK);
	}

	@GetMapping("getCrimeDetailsCount")
	public ResponseEntity<ResponseDto> getCrimeDetailsCount(@RequestBody(required = false) RequestDto request ) {
		return new ResponseEntity<>(crimeService.getCrimeDetailsCount(request), HttpStatus.OK);
	}

}
