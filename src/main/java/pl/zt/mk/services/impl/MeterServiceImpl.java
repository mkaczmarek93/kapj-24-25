package pl.zt.mk.services.impl;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;
import pl.zt.mk.calculations.EffectiveMetersDates;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Place;
import pl.zt.mk.lazy.LazyModel;
import pl.zt.mk.lazy.impl.MetersPageResolver;
import pl.zt.mk.repo.MeterRepository;
import pl.zt.mk.repo.PlaceRepository;
import pl.zt.mk.services.MeterService;

import java.util.List;
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
		if (meter.getDate() == null)
			meter.setDate(new LocalDate());
		meterRepository.save(meter);
	}

	@Override
	public LazyModel<Meter> findByUserEmail(String mail) {
		Place place = placeRepository.findByUserEmail(mail);
		return new LazyModel<>(new MetersPageResolver(meterRepository, place));

	}

	@Override
	public Meter findCurrentMeterLevelByName(String email, LocalDate date) {
		Place place = placeRepository.findByUserEmail(email);

		EffectiveMetersDates effectiveDates = new EffectiveMetersDates(date);

		return meterRepository.findByPlaceAndDate(place, effectiveDates.getStartDate(), effectiveDates.getEndDate());
	}

	@Override
	public Meter findMeterLevelByPlaceAndDate(Place place, LocalDate date) {
		EffectiveMetersDates effectiveDates = new EffectiveMetersDates(date);

		return meterRepository.findByPlaceAndDate(place, effectiveDates.getStartDate(), effectiveDates.getEndDate());
	}

	@Override
	public List<Meter> findByPlace(Place place) {
		return meterRepository.findByPlace(place);
	}

	@Override
	public Meter findLastMeterByPlace(Place place) {
		List<Meter> list = meterRepository.findByPlace(place);
		return org.springframework.util.CollectionUtils.isEmpty(list) ? null : list.get(0) ;
	}
	@Override
	public Meter saveMeter(Meter meter) {
		return meterRepository.save(meter);
	}


}
