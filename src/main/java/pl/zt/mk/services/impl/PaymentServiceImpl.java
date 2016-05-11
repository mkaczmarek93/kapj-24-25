package pl.zt.mk.services.impl;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.Payment;
import pl.zt.mk.lazy.LazyModel;
import pl.zt.mk.lazy.impl.PaymentPageResolver;
import pl.zt.mk.repo.PaymentRepository;
import pl.zt.mk.services.PaymentService;

import java.util.List;

/**
 * Created by zt on 2016-04-05.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;


	@Override
	public LazyModel<Payment> findAll() {
		return new LazyModel<>(new PaymentPageResolver(paymentRepository));
	}

	@Override
	public Boolean savePayment(Payment payment) {
		try {
			this.paymentRepository.save(payment);
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public Boolean deactivePayment(Payment payment) {
		if (payment.isActive()) {
			payment.setActive(false);
			return this.savePayment(payment);
		}
		return false;
	}

	@Override
	public List<Payment> findActivePaymentForCurrentMonth() {
		LocalDate date = new LocalDate();
		LocalDate firstDayOfMonth = new LocalDate(date.getYear(), date.getMonthOfYear(), 1);
		LocalDate lastDayofMonth = new LocalDate(date.getYear(), date.getMonthOfYear(), date.dayOfMonth().withMaximumValue().getDayOfMonth());
		return paymentRepository.findPaymentsForDate(firstDayOfMonth, lastDayofMonth);
	}
}
