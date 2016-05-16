package pl.zt.mk.calculations;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

@Getter
@Setter
public class EffectiveMetersDates {
	LocalDate startDate;
	LocalDate endDate;

	public EffectiveMetersDates(LocalDate date) {
		//valid start date is a 11th day of prev month
		int startDay = 11;
		//valid end date is a 10th day of current month
		int endDay = 10;
		//so
		int year = date.getYear();
		int month = date.getMonthOfYear();
		int day = date.getDayOfMonth();

		if (day < 11) {
			this.startDate = new LocalDate(year, month - 1, startDay);
			this.endDate = new LocalDate(year, month, endDay);
//			month--; month;
		} else {
			this.startDate = new LocalDate(year, month, startDay);
			this.endDate = new LocalDate(year, month + 1, endDay);
//			month, month++;
		}
	}
}