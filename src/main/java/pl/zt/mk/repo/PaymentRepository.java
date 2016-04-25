package pl.zt.mk.repo;

import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zt.mk.entity.Payment;

import java.util.List;

/**
 * Created by zt on 2016-04-05.
 */
@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

	Page<Payment> findAll();
	@Query("select p from Payment p where p.active = true and ((p.endDate is null and p.startDate < :startDate) or ( p.startDate between :startDate and :endDate))")
	List<Payment>  findPaymentsForDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
