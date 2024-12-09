package com.crime.women.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CrimeDetailsByState creates for framing the data against request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Crime details by state")
public class CrimeDetailsByState {

	@Schema(description = "stateName or CrimeType")
	private String stateCrime;

	@Schema(description = "Crime Count against state")
	private Long count;
}
