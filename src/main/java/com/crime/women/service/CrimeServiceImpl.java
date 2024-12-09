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

/**
 * CrimeServiceImpl is an implementation class of crimeService where in related
 * operations takes place
 */
@Service
public class CrimeServiceImpl implements CrimeService {

	private final CrimeRepository crimeRepository;

	public CrimeServiceImpl(CrimeRepository crimeRepository) {
		this.crimeRepository = crimeRepository;
	}

	/**
	 * getDetails method returns all data based on pagination
	 * 
	 * @param page
	 * @param size
	 * @return Page<CrimeData>
	 */
	@Override
	public ResponseDto<Page<CrimeData>> getDetails(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		ResponseDto<Page<CrimeData>> response = new ResponseDto<>(Constants.SUCCESS,
				crimeRepository.findAllByOrderByIdAsc(pageable));
		return response;
	}

	/**
	 * getCrimeDetailsCount used to fetch the data in both scenario, user can enter
	 * state and get response orElse they can see the overall count of crime in all
	 * states.
	 * 
	 * @param request
	 * @return List<CrimeDetailsByState>
	 */
	@Override
	public ResponseDto<List<CrimeDetailsByState>> getCrimeDetailsCount(RequestDto request) {
		if (request != null && request.getState() != null) {
			Optional<List<CrimeDetailsByState>> optionalDetailList = crimeRepository
					.findCrimeCountByStateAndType(request.getState());

			List<CrimeDetailsByState> detailList = optionalDetailList.filter(list -> !list.isEmpty())
					.orElseThrow(() -> new NotFoundException());

			return new ResponseDto<>(Constants.SUCCESS, detailList);
		} else {
			return new ResponseDto<>(Constants.SUCCESS, crimeRepository.getCountByState());
		}
	}

}
