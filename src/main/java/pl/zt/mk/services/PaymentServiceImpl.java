package pl.zt.mk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.Payment;
import pl.zt.mk.repo.PaymentRepository;

import java.util.List;

/**
 * Created by zt on 2016-04-05.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;


	@Override
	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}

	@Override
	public Long addPayment(Payment payment) throws DataAccessException {
		try {
			return this.paymentRepository.save(payment).getId();

		} catch (DataAccessException e) {
			throw e;
		}
	}
}
