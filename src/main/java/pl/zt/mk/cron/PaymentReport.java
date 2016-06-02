package pl.zt.mk.cron;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.zt.mk.config.LocaleConfig;
import pl.zt.mk.cron.data.ReportPaymentObject;
import pl.zt.mk.entity.PaymentHistory;
import pl.zt.mk.entity.Place;
import pl.zt.mk.entity.UserDetail;
import pl.zt.mk.repo.JasperRepository;
import pl.zt.mk.services.InternationalizationService;
import pl.zt.mk.services.MailSender;
import pl.zt.mk.services.PaymentHistoryService;
import pl.zt.mk.services.UserService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michal on 16.05.2016.
 */
@Component
@Slf4j
public class PaymentReport {

	@Autowired
	private UserService userService;

	@Autowired
	private PaymentHistoryService paymentHistoryService;

	@Autowired
	private JasperRepository jasperRepository;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private LocaleConfig localeConfig;

	@Scheduled(cron = "00 00 00 6 1 *")
	public void prepareAndSendYearReport() throws JRException {
		List<UserDetail> users = userService.findUsersWithLocal();
		for (UserDetail user : users) {
			log.info("Report for: " + user.getName());
			List<ReportPaymentObject> payments = producePaymentObject(user.getPlace());
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(payments, false);
			Map parameters = getReportParameters(user, payments);
			JasperPrint print = jasperRepository.getReportTemplate("yearReport.jrxml", parameters, jrBeanCollectionDataSource);
			JasperExportManager.exportReportToPdfFile(print, user.getName() + ".pdf");
			File file = new File(user.getName() + ".pdf");
			mailSender.sendReport(user.getName(), user.getEmail(), file, false);
		}
		log.info("All year reports are sent");
	}

	@Scheduled(cron = "00 00 6 11 * ?")
	public void prepareAndSendMonthReport() throws JRException {
		List<UserDetail> users = userService.findUsersWithLocal();
		for (UserDetail user : users) {
			log.info("Report for: " + user.getName());
			List<ReportPaymentObject> payments = producePaymentObjectForLastMonth(user.getPlace());
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(payments, false);
			Map parameters = getReportParameters(user, payments);
			JasperPrint print = jasperRepository.getReportTemplate("monthReport.jrxml", parameters, jrBeanCollectionDataSource);
			JasperExportManager.exportReportToPdfFile(print, user.getName() + ".pdf");
			File file = new File(user.getName() + ".pdf");
			mailSender.sendReport(user.getName(), user.getEmail(), file, true);
		}
		log.info("All month reports are sent");
	}

	private List<ReportPaymentObject> producePaymentObjectForLastMonth(final Place place) {
		List<ReportPaymentObject> objects = new ArrayList<>();
		List<PaymentHistory> payment = paymentHistoryService.findByPlaceInLastMonth(place);
		if (!payment.isEmpty())
			objects.add(new ReportPaymentObject(payment.get(0), localeConfig.localeProvider().getLocale()));
		return objects;
	}

	private List<ReportPaymentObject> producePaymentObject(final Place place) {
		List<ReportPaymentObject> objects = new ArrayList<>();
		List<PaymentHistory> payments = paymentHistoryService.findByPlaceInLastYear(place);
		for (PaymentHistory payment : payments) {
			objects.add(new ReportPaymentObject(payment, localeConfig.localeProvider().getLocale()));
		}
		return objects;
	}

	private Map getReportParameters(UserDetail user, List<ReportPaymentObject> payments) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("Adres", getAdres(user.getPlace()));
		parameters.put("User", user.getName());
		parameters.put("TotalCharge", calculateTotalCharge(payments));
		parameters.put("Date", new LocalDate().toString());
		return parameters;
	}

	private String calculateTotalCharge(List<ReportPaymentObject> payments) {
		double total = 0.0;
		for (ReportPaymentObject payment : payments) {
			total += Double.valueOf(payment.getCharge());
		}
		return String.format("%.2f", total);
	}

	private String getAdres(Place place) {
		StringBuilder sb = new StringBuilder();
		sb.append(place.getBlock().getPostCode())
				.append(" ")
				.append(place.getBlock().getCity())
				.append("\n")
				.append(place.getBlock().getStreet())
				.append(" ")
				.append(place.getBlock().getFlatNumber())
				.append("/")
				.append(place.getApartmentNumber());
		return sb.toString();
	}
}
