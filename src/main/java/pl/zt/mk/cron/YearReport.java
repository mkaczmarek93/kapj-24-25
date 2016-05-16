package pl.zt.mk.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.zt.mk.entity.PaymentHistory;
import pl.zt.mk.entity.Place;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.services.PaymentHistoryService;
import pl.zt.mk.services.UserService;

import java.util.List;

/**
 * Created by Michal on 16.05.2016.
 */
@Component
public class YearReport {

	@Autowired
	UserService userService;

	@Autowired
	PaymentHistoryService paymentHistoryService;

	@Scheduled(cron = "00 02 00 01 01 *")
	public void prepareAndSendYearReport() {
		List<UserDetail> users = userService.findUsersWithLocal();
		for (UserDetail user : users) {
			Place place = user.getPlace();
			List<PaymentHistory> payments = paymentHistoryService.findByPlaceInLastYear(place);
			for (PaymentHistory p : payments) {

			}
		}
	}
}
