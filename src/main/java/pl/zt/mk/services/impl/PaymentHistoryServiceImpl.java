package pl.zt.mk.services.impl;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.zt.mk.calculations.ChargeCalculation;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Payment;
import pl.zt.mk.entity.PaymentHistory;
import pl.zt.mk.entity.Place;
import pl.zt.mk.lazy.LazyModel;
import pl.zt.mk.lazy.impl.PaymentHistoryPageResolver;
import pl.zt.mk.repo.PaymentHistoryRepository;
import pl.zt.mk.services.MeterService;
import pl.zt.mk.services.PaymentHistoryService;
import pl.zt.mk.services.PaymentService;
import pl.zt.mk.services.PlaceService;

import java.util.List;
import java.util.Objects;

/**
 * Created by zt on 2016-04-25.
 */
@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {
	@Autowired
	PaymentHistoryRepository paymentHistoryRepository;

	@Autowired
	PaymentService paymentService;

	@Autowired
	MeterService meterService;


	@Autowired
	PlaceService placeService;


	@Override
	public LazyModel<PaymentHistory> findByPlace(Place place) {
		return new LazyModel<>(new PaymentHistoryPageResolver(paymentHistoryRepository,place));
	}

	@Override
	public void saveHistory(PaymentHistory paymentHistory) {
		paymentHistoryRepository.save(paymentHistory);
	}

	@Override
	public void pay(PaymentHistory paymentHistory) {
		paymentHistory.setPaymentDate(new LocalDate());
		paymentHistory.setPaid(Boolean.TRUE);

		this.saveHistory(paymentHistory);
	}

	@Override
	@Scheduled(cron = "00 00 02 11 * ?")
	public void calculatePaymentsForAllPlaces() {

		List<Place> places = placeService.findPlacesConnectedWithAnyUser();

		LocalDate currentDate = new LocalDate();
		places.forEach(place -> {
			Meter prevMonth = meterService.findMeterLevelByPlaceAndDate(place, currentDate.minusMonths(1));
			if (Objects.isNull(prevMonth)) {
				prevMonth = new Meter(null, currentDate.minusMonths(1), 0D, 0D, 0D, 0D, null);
			}
			Meter currentMonth = meterService.findMeterLevelByPlaceAndDate(place, currentDate);
			if (Objects.isNull(currentMonth)) {
				throw new IllegalArgumentException("Meters for current month not found !");
			}
			List<Payment> paymentList = paymentService.findActivePaymentForCurrentMonth();
			ChargeCalculation chargeCalculation = new ChargeCalculation(place, prevMonth, currentMonth, paymentList);
			Double sum = chargeCalculation.calculate();
			paymentHistoryRepository.save(new PaymentHistory(place, new LocalDate(), sum));
		});

	}

	@Override
	public List<PaymentHistory> findByPlaceInLastYear(Place place) {
		LocalDate startDate = new LocalDate().minusYears(1);
		LocalDate endDate = new LocalDate();
		return paymentHistoryRepository.findByPlaceInYear(place, startDate,endDate);
	}
}
