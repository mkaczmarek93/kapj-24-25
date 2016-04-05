package pl.zt.mk.services;

import org.springframework.dao.DataAccessException;
import pl.zt.mk.entity.Payment;

import java.util.List;

/**
 * Created by zt on 2016-04-05.
 */

public interface PaymentService {

	List<Payment> findAll();

	Long addPayment(Payment payment) throws DataAccessException;
}
