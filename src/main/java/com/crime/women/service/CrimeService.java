package com.crime.women.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.crime.women.dto.CrimeDetailsByState;
import com.crime.women.dto.RequestDto;
import com.crime.women.dto.ResponseDto;
import com.crime.women.model.CrimeData;

/**
 * CrimeService interface supports and holds the related crime operation in
 * single interface
 */
public interface CrimeService {

	public ResponseDto<Page<CrimeData>> getDetails(int page, int size);

	public ResponseDto<List<CrimeDetailsByState>> getCrimeDetailsCount(RequestDto request);

}
