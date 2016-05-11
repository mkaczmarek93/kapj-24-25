package pl.zt.mk.cron;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Place;
import pl.zt.mk.services.MeterService;
import pl.zt.mk.services.PlaceService;

import java.util.Date;

/**
 * Created by Michal on 09.05.2016.
 */
@Component
@Slf4j
public class LumpSumCron {

	private static final double LUMP_SUM = 50;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private MeterService meterService;

	public LumpSumCron() {
	}

	public LumpSumCron(PlaceService placeService, MeterService meterService) {
		this.placeService = placeService;
		this.meterService = meterService;
	}

	@Scheduled(cron = "1 00 00 11 * ?")
	public void checkIsALumpSumRequired() {
		placeService.findAll().stream().forEachOrdered(place -> {
			if (null == place.getUserDetail()) {
				rewriteMeter(place);
			} else if (!isMetersFilled(place)) {
				assignLumpSum(place);
			}
		});
	}

	private void assignLumpSum(Place place) {
		if (place.getMeters().isEmpty()) {
			fillEmptyMeter(place);
		} else {
			Meter meter = Iterables.getLast(place.getMeters());
			meter.setDate(new LocalDate(new Date().getTime()));
			meter.setId(null);
			meterService.addMeter(meter);
		}
	}

	private void rewriteMeter(Place place) {
		if (place.getMeters().isEmpty()) {
			fillEmptyMeter(place);
		} else {
			Meter meter = Iterables.getLast(place.getMeters());
			meter.setId(null);
			meter.setDate(new LocalDate(new Date().getTime()));
			meter.setColdWater(meter.getColdWater() + LUMP_SUM);
			meter.setHotWater(meter.getHotWater() + LUMP_SUM);
			meter.setElectricity(meter.getElectricity() + LUMP_SUM);
			meter.setGas(meter.getGas() + LUMP_SUM);
			meterService.addMeter(meter);
		}
	}

	private void fillEmptyMeter(Place place) {
		Meter meter = new Meter();
		meter.setColdWater(0.0);
		meter.setGas(0.0);
		meter.setElectricity(0.0);
		meter.setHotWater(0.0);
		meter.setPlace(place);
		meter.setDate(new LocalDate(new Date().getTime()));
		place.getMeters().add(meter);
	}

	public boolean isMetersFilled(final Place place) {
		Meter last = Iterables.getLast(place.getMeters());
		DateTime startDate = new DateTime(new Date().getTime()).minusMonths(1);
		DateTime endDate = new DateTime(new Date().getTime());
		Interval interval = new Interval(startDate, endDate);
		return interval.contains(last.getDate().toDate().getTime());
	}
}
