package pl.zt.mk.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.PaymentHistory;
import pl.zt.mk.entity.Place;
import pl.zt.mk.lazy.LazyModel;
import pl.zt.mk.lazy.impl.PaymentHistoryPageResolver;
import pl.zt.mk.repo.PaymentHistoryRepository;
import pl.zt.mk.services.PaymentHistoryService;

/**
 * Created by zt on 2016-04-25.
 */
@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {
	@Autowired
	PaymentHistoryRepository paymentHistoryRepository;

	@Override
	public LazyModel<PaymentHistory> findByPlace(Place place) {
		return new LazyModel<>(new PaymentHistoryPageResolver(paymentHistoryRepository,place));
	}
}
