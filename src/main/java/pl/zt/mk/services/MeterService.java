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

	Meter findCurrentMeterLevelBy(String email, LocalDate date);

	List<Meter> findByPlace(Place place);
}
