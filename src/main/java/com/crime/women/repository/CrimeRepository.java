package com.crime.women.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crime.women.dto.CrimeDetailsByState;
import com.crime.women.model.CrimeData;

/**
 * CrimeRepository it extends paging and sorting repository where in it had the
 * functionality related to database operations
 */
@Repository
public interface CrimeRepository extends PagingAndSortingRepository<CrimeData, Integer> {

	Page<CrimeData> findAllByOrderByIdAsc(Pageable pageable);

	/**
	 * findByCrimeType used to fetch the data based on crimeType (currently no
	 * implementation made, future scope)
	 * 
	 * @param crimeType
	 * @return
	 */
	List<CrimeData> findByCrimeType(String crimeType);

	@Query("SELECT new com.crime.women.dto.CrimeDetailsByState(UPPER(c.state) as ignorecase_state, SUM(c.count)) FROM CrimeData c "
			+ "GROUP BY UPPER(c.state) ORDER BY ignorecase_state")
	List<CrimeDetailsByState> getCountByState();

	@Query("SELECT new com.crime.women.dto.CrimeDetailsByState(c.crimeType, SUM(c.count)) FROM CrimeData c "
			+ "WHERE UPPER(c.state) LIKE UPPER(:state) GROUP BY c.crimeType ")
	Optional<List<CrimeDetailsByState>> findCrimeCountByStateAndType(@Param("state") String state);

}
