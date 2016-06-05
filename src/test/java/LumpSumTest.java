import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;
import pl.zt.mk.cron.LumpSumCron;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Place;
import pl.zt.mk.services.MeterService;
import pl.zt.mk.services.PlaceService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Michal on 11.05.2016.
 */
public class LumpSumTest {

	private PlaceService placeService = Mockito.mock(PlaceService.class);
	private MeterService meterService = Mockito.mock(MeterService.class);
	private LumpSumCron cron;
	private Place place;

	@Before
	public void init() {
		List<Place> places = new ArrayList<>();
		place = new Place();
		List<Meter> meters = new ArrayList<>();
		Meter m = new Meter();
		LocalDate date = new LocalDate(2016, 6, 9);
		m.setDate(date);
		meters.add(m);
		place.setMeters(meters);
		places.add(place);
		Mockito.when(placeService.findAll()).thenReturn(places);
		Mockito.when(meterService.findByPlace(place)).thenReturn(meters);
		cron = new LumpSumCron(placeService, meterService);
	}

	@Test
	public void cronTriggerTest() {
		CronTrigger trigger = new CronTrigger("1 0 0 11 * ?");
		Calendar today = Calendar.getInstance();
		today.set(Calendar.YEAR, 2016);
		today.set(Calendar.MONTH, Calendar.MAY);
		today.set(Calendar.DAY_OF_MONTH, 12);
		today.set(Calendar.HOUR_OF_DAY, 00);
		today.set(Calendar.MINUTE, 00);
		today.set(Calendar.SECOND, 1);

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		final Date yesterday = today.getTime();
		Date nextExecutionTime = trigger.nextExecutionTime(new TriggerContext() {
			@Override
			public Date lastScheduledExecutionTime() {
				return yesterday;
			}

			@Override
			public Date lastActualExecutionTime() {
				return yesterday;
			}

			@Override
			public Date lastCompletionTime() {
				return yesterday;
			}
		});
		Calendar expected = (Calendar) today.clone();
		expected.set(Calendar.MONTH, Calendar.JUNE);
		expected.set(Calendar.DAY_OF_MONTH, 11);

		Assert.assertEquals(df.format(expected.getTime()), df.format(nextExecutionTime));
	}

	@Test
	public void intervalTestTrue() {
		Assert.assertTrue(cron.isLastMeters(place));
	}
}
