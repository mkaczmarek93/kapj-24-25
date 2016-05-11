package pl.zt.mk.services;

import pl.zt.mk.entity.Payment;
import pl.zt.mk.lazy.LazyModel;

import java.util.List;

/**
 * Created by zt on 2016-04-05.
 */

public interface PaymentService {

	LazyModel<Payment> findAll();

	Boolean savePayment(Payment payment);

	Boolean deactivePayment(Payment payment);

	List<Payment> findActivePaymentForCurrentMonth();
}
