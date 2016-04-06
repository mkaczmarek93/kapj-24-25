package pl.zt.mk.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.zt.mk.entity.Payment;

/**
 * Created by zt on 2016-04-05.
 */
@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

	Page<Payment> findAll();
}
