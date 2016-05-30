package pl.zt.mk.services;

import pl.zt.mk.entity.PaymentHistory;
import pl.zt.mk.entity.Place;
import pl.zt.mk.lazy.LazyModel;

import java.util.List;

/**
 * Created by zt on 2016-04-25.
 */
public interface PaymentHistoryService {
	LazyModel<PaymentHistory> findByPlace(Place place);

	void saveHistory(PaymentHistory paymentHistory);

	void checkAsPayed(PaymentHistory paymentHistory);

	void calculatePaymentsForAllPlaces();

	List<PaymentHistory> findByPlaceInLastYear(Place place);
}
