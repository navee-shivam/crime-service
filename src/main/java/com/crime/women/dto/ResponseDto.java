package com.crime.women.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseDto global class for formatted response
 * 
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO containing the crime details")
public class ResponseDto<T> {

	@Schema(description = "Response status")
	private String message;

	@Schema(description = "Data containing crime details")
	private T data;
}
