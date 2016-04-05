package pl.zt.mk.repo;

import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zt.mk.entity.Payment;

import java.util.List;

/**
 * Created by zt on 2016-04-05.
 */
@Repository
public interface PaymentRepository extends CrudRepository<Payment, LocalDate> {

	List<Payment> findAll();
}
