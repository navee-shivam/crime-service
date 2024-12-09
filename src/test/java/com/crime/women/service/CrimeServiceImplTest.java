package com.crime.women.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.crime.women.dto.CrimeDetailsByState;
import com.crime.women.dto.RequestDto;
import com.crime.women.dto.ResponseDto;
import com.crime.women.exception.NotFoundException;
import com.crime.women.model.CrimeData;
import com.crime.women.repository.CrimeRepository;
import com.crime.women.utils.Constants;

@ExtendWith(MockitoExtension.class)
class CrimeServiceImplTest {

	@InjectMocks
	private CrimeServiceImpl crimeServiceImpl;

	@Mock
	private CrimeRepository crimeRepository;

	@Test
	void test_getData() {
		int page = 1;
		int size = 10;
		Pageable pageable = PageRequest.of(page, size);
		Page<CrimeData> pageObject = new PageImpl<>(Arrays.asList(new CrimeData(), new CrimeData()));
		when(crimeRepository.findAllByOrderByIdAsc(pageable)).thenReturn(pageObject);
		ResponseDto<Page<CrimeData>> response = crimeServiceImpl.getDetails(page, size);
		assertThat(response.getMessage()).isEqualTo(Constants.SUCCESS);
		assertThat(response.getData()).isEqualTo(pageObject);
		verify(crimeRepository, times(1)).findAllByOrderByIdAsc(pageable);
	}

	@Test
	void test_getCrimeDetailsCount_withState() {
		RequestDto request = new RequestDto("Assam");
		when(crimeRepository.findCrimeCountByStateAndType(request.getState()))
				.thenReturn(Optional.ofNullable(Arrays.asList(new CrimeDetailsByState("Assault against women", 47115L),
						new CrimeDetailsByState("Kidnap And Assault", 71462L))));
		ResponseDto<List<CrimeDetailsByState>> response = crimeServiceImpl.getCrimeDetailsCount(request);
		assertThat(response.getMessage()).isEqualTo(Constants.SUCCESS);
		verify(crimeRepository, times(1)).findCrimeCountByStateAndType(request.getState());
	}

	@Test
	void test_getCrimeDetailsCount_withOutState() {
		when(crimeRepository.getCountByState()).thenReturn(
				Arrays.asList(new CrimeDetailsByState("ASSAM", 291980L), new CrimeDetailsByState("JHARKHAND", 82827L)));
		ResponseDto<List<CrimeDetailsByState>> response = crimeServiceImpl.getCrimeDetailsCount(null);
		assertThat(response.getMessage()).isEqualTo(Constants.SUCCESS);
		verify(crimeRepository, times(1)).getCountByState();
	}

	@Test
	void test_getCrimeDetailsCount_withOutState_NoData() {
		RequestDto request = new RequestDto("NoData");
		when(crimeRepository.findCrimeCountByStateAndType(request.getState())).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> crimeServiceImpl.getCrimeDetailsCount(request));

	}

}
