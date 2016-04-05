package pl.zt.mk.services;

import pl.zt.mk.entity.Payment;

import java.util.List;

/**
 * Created by zt on 2016-04-05.
 */

public interface PaymentService {

	List<Payment> findAll();

	void addPayment(Payment payment);
}
