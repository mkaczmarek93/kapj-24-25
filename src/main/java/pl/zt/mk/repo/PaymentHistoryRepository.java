package pl.zt.mk.repo;

import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zt.mk.entity.PaymentHistory;
import pl.zt.mk.entity.Place;

import java.util.List;

/**
 * Created by zt on 2016-04-25.
 */
@Repository
public interface PaymentHistoryRepository extends PagingAndSortingRepository<PaymentHistory, Long> {

	Page<PaymentHistory> findByPlace(Place place, Pageable pageable);

	int countByPlace(Place place);

	@Query(value = "select p from PaymentHistory p where p.place=:place and p.month between :startDate and :endDate")
	List<PaymentHistory> findByPlaceInYear(@Param("place") Place place, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query(value = "select p from PaymentHistory p where p.place=:place and p.month between :startDate and :endDate order by p.month desc")
	List<PaymentHistory> findByPlaceInMonth(@Param("place") Place place, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
