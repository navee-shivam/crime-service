package com.crime.women.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crime.women.dto.RequestDto;
import com.crime.women.dto.ResponseDto;
import com.crime.women.service.CrimeService;
import com.crime.women.utils.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * CrimeController class responsible for handling incoming request related to
 * crime details
 */
@RestController
@RequestMapping(Constants.VERSION)
@CrossOrigin
public class CrimeController {

	private final CrimeService crimeService;

	public CrimeController(CrimeService crimeService) {
		this.crimeService = crimeService;
	}

	/**
	 * fetchAllData method returns all data based on pagination
	 * 
	 * @param page
	 * @param size
	 * @return list<Response>
	 */
	@Operation(summary = "Fetch all crime data", description = "Fetches a paginated list of crime data based on the page number and size.", tags = {
			"Crime Data" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved crime data"),
			@ApiResponse(responseCode = "400", description = "Invalid page or size parameters"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("getList")
	public ResponseEntity<ResponseDto> fetchAllData(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "100") int size) {
		return new ResponseEntity<>(crimeService.getDetails(page, size), HttpStatus.OK);
	}

	/**
	 * getCrimeDetailsCount API used to fetch the data in both scenario, user can
	 * enter state and get response orElse they can see the overall count of crime
	 * in all states.
	 * 
	 * @param request
	 * @return List<CrimeDetailsByState>
	 */
	@Operation(summary = "Get crime details count", description = "Fetches the crime details count by state and type.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved crime details"),
			@ApiResponse(responseCode = "204", description = "No content found for the requested state"),
			@ApiResponse(responseCode = "404", description = "State not found or invalid mapping") })
	@PostMapping("getCrimeDetailsCount")
	public ResponseEntity<ResponseDto> getCrimeDetailsCount(@RequestBody(required = false) RequestDto request) {
		return new ResponseEntity<>(crimeService.getCrimeDetailsCount(request), HttpStatus.OK);
	}

}
