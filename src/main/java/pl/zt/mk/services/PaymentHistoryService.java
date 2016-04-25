package pl.zt.mk.services;

import pl.zt.mk.entity.PaymentHistory;
import pl.zt.mk.entity.Place;
import pl.zt.mk.lazy.LazyModel;

/**
 * Created by zt on 2016-04-25.
 */
public interface PaymentHistoryService {
	LazyModel<PaymentHistory> findByPlace(Place place);
}
