package pl.zt.mk.services;

import org.joda.time.LocalDate;
import org.springframework.dao.DataAccessException;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Place;
import pl.zt.mk.lazy.LazyModel;

import java.util.List;

/**
 * Created by zt on 2016-04-11.
 */
public interface MeterService {

	void addMeter(Meter meter) throws DataAccessException, IllegalArgumentException;

	LazyModel<Meter> findByUserEmail(String email);

	Meter findCurrentMeterLevelByName(String email, LocalDate date);

	Meter findMeterLevelByPlaceAndDate(Place place, LocalDate date);

	List<Meter> findByPlace(Place place);

	Meter saveMeter(Meter meter);

	Meter findLastMeterByPlace(Place place);

}
