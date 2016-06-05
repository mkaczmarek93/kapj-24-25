package pl.zt.mk.repo;

import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Place;

import java.util.List;

import java.util.List;

/**
 * Created by zt on 2016-04-11.
 */
@Repository
public interface MeterRepository extends PagingAndSortingRepository<Meter, Long> {

	@Query(value = "select  m from Meter m where m.place=:place order by m.date asc")
	Page<Meter> findByPlace(@Param("place") Place place, Pageable page);

	@Query(value = "select  count(m.id) from Meter m where m.place=:place")
	long countByPlace(@Param("place") Place place);


	@Query(value = "select  max(m) from Meter m where m.place=:place and m.date between :startDate and :endDate")
	Meter findByPlaceAndDate(@Param("place") Place place, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query(value = "select  m from Meter m where m.place=:place order by m.date asc")
	List<Meter> findByPlace(@Param("place") Place place);



}
