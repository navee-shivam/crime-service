package com.crime.women.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crime.women.dto.CrimeDetailsByState;
import com.crime.women.dto.RequestDto;
import com.crime.women.dto.ResponseDto;
import com.crime.women.exception.NotFoundException;
import com.crime.women.model.CrimeData;
import com.crime.women.repository.CrimeRepository;
import com.crime.women.utils.Constants;

@Service
public class CrimeServiceImpl implements CrimeService {

	private final CrimeRepository crimeRepository;

	public CrimeServiceImpl(CrimeRepository crimeRepository) {
		this.crimeRepository = crimeRepository;
	}

	@Override
	public String getWelcome() {
		return "welcome";
	}

	@Override
	public ResponseDto<Page<CrimeData>> getDetails(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		ResponseDto<Page<CrimeData>> response = new ResponseDto<>(Constants.SUCCESS,
				crimeRepository.findAllByOrderByIdAsc(pageable));
		return response;
	}

	@Override
	public ResponseDto<List<CrimeDetailsByState>> getCrimeDetailsCount(RequestDto request) {
		ResponseDto<List<CrimeDetailsByState>> response;
		if (request != null && request.getState() != null) {
			Optional<List<CrimeDetailsByState>> optionalDetailList = Optional
					.ofNullable(crimeRepository.findCrimeCountByStateAndType(request.getState()))
					.filter(list -> list.isEmpty())
					.orElseThrow(() -> new NotFoundException("Data not found"));
			response = new ResponseDto<>(Constants.SUCCESS, optionalDetailList.get());
		} else {
			response = new ResponseDto<>(Constants.SUCCESS, crimeRepository.getCountByState());
		}
		return response;
	}

}
