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

import java.util.List;

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
			} else if (!isLastMeters(place)) {
				assignLumpSum(place);
			} else {
				checkIsMeterFilledCorrect(place);
			}
		});
	}

	private void assignLumpSum(final Place place) {
		List<Meter> meters = meterService.findByPlace(place);
		if (meters.isEmpty()) {
			Meter meter = initEmptyMeter();
			meter.setPlace(place);
			meterService.addMeter(meter);
			log.info("Init meter for place: " + place.getId());
		} else {
			Meter meter = Iterables.getLast(meters);
			meter.setId(null);
			meter.setDate(new LocalDate().withDayOfMonth(10));
			meter.setColdWater(meter.getColdWater() + LUMP_SUM);
			meter.setHotWater(meter.getHotWater() + LUMP_SUM);
			meter.setElectricity(meter.getElectricity() + LUMP_SUM);
			meter.setGas(meter.getGas() + LUMP_SUM);
			meterService.addMeter(meter);
			log.info("Ryczalt dla place: " + place.getId());
		}
	}

	private void checkIsMeterFilledCorrect(final Place place) {
		List<Meter> meters = meterService.findByPlace(place);
		Meter last = meters.get(meters.size() - 1);
		Meter previous = meters.get(meters.size() - 2);
		if (last.getColdWater() == null)
			last.setColdWater(previous.getColdWater() + LUMP_SUM);
		if (last.getElectricity() == null)
			last.setElectricity(previous.getElectricity());
		if (last.getGas() == null)
			last.setGas(previous.getGas());
		if (last.getHotWater() == null)
			last.setHotWater(previous.getHotWater());
		meterService.saveMeter(last);
	}

	private void rewriteMeter(final Place place) {
		List<Meter> meters = meterService.findByPlace(place);
		if (meters.isEmpty()) {
			Meter meter = initEmptyMeter();
			meter.setPlace(place);
			meterService.addMeter(meter);
			log.info("Init meter for place: " + place.getId());
		} else if (!isLastMeters(place)) {
			Meter meter = Iterables.getLast(meters);
			meter.setDate(new LocalDate().withDayOfMonth(10));
			meter.setId(null);
			meterService.addMeter(meter);
			log.info("Przepisany licznik dla place: " + place.getId());
		}
	}

	private Meter initEmptyMeter() {
		Meter meter = new Meter();
		meter.setColdWater(0.0);
		meter.setGas(0.0);
		meter.setElectricity(0.0);
		meter.setHotWater(0.0);
		meter.setDate(new LocalDate().withDayOfMonth(10));
		return meter;
	}

	public boolean isLastMeters(final Place place) {
		List<Meter> meters = meterService.findByPlace(place);
		if (meters.isEmpty()) {
			return false;
		}
		Interval interval = getCurrentInterval();
		Meter last = Iterables.getLast(meters);
		return interval.contains(last.getDate().toDate().getTime());
	}

	private Interval getCurrentInterval() {
		DateTime current = new DateTime();

		int startDay = 11;
		int endDat = 10;
		int year = current.getYear();
		int month = current.getMonthOfYear();

		DateTime startDate = new DateTime(year, month - 1, startDay, 0, 0);
		DateTime endDate = new DateTime(year, month, endDat, 23, 59);
		return new Interval(startDate, endDate);
	}
}
