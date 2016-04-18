package pl.zt.mk.services.impl;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Place;
import pl.zt.mk.lazy.LazyModel;
import pl.zt.mk.lazy.impl.MetersPageResolver;
import pl.zt.mk.repo.MeterRepository;
import pl.zt.mk.repo.PlaceRepository;
import pl.zt.mk.services.MeterService;

import java.util.Objects;

/**
 * Created by zt on 2016-04-11.
 */
@Service
public class MeterServiceImpl implements MeterService {
	@Autowired
	MeterRepository meterRepository;

	@Autowired
	PlaceRepository placeRepository;

	@Override
	public void addMeter(Meter meter) {
		if (Objects.isNull(meter.getPlace()))
			throw new IllegalArgumentException("Meter whitout place !!");
		meter.setDate(new LocalDate());
		meterRepository.save(meter);
	}

	@Override
	public LazyModel<Meter> findByUserEmail(String mail) {
		Place place = placeRepository.findByUserEmail(mail);
		return new LazyModel<>(new MetersPageResolver(meterRepository, place));

	}

	@Override
	public Meter findCurrentMeterLevelBy(String email, LocalDate date) {
		Place place = placeRepository.findByUserEmail(email);
		//valid start date is a 11th day of prev month
		int startDay = 11;
		//valid end date is a 10th day of current month
		int endDay = 10;
		//so
		int year = date.getYear();
		int month = date.getMonthOfYear();
		LocalDate startDate = new LocalDate(year, month - 1, startDay);
		LocalDate endDate = new LocalDate(year, month, endDay);
		return meterRepository.findByPlacAndDate(place, startDate, endDate);
	}

}
