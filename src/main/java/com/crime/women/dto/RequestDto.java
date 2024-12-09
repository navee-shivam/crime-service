package com.crime.women.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * RequestDto class added for purpose of request when user try to search against
 * particular state
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for crime details search")
public class RequestDto {

	@Schema(description = "State for which crime details are to be fetched")
	private String state;
}
