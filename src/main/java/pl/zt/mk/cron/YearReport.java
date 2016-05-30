package pl.zt.mk.cron;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
import java.util.List;

/**
 * Created by Michal on 16.05.2016.
 */
@Component
@Slf4j
public class YearReport {

	@Autowired
	private UserService userService;

	@Autowired
	private PaymentHistoryService paymentHistoryService;

	@Autowired
	private JasperRepository jasperRepository;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private InternationalizationService i18n;

	@Autowired
	private LocaleConfig localeConfig;

	@Scheduled(cron = "00 00 00 6 1 *")
	public void prepareAndSendYearReport() throws JRException {
		log.info("Working Directory = " + System.getProperty("user.dir"));
		List<UserDetail> users = userService.findUsersWithLocal();
		for (UserDetail user : users) {
			log.info("Report for: " + user.getName());
			List<ReportPaymentObject> payments = producePaymentObject(user.getPlace());
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(payments, false);
			JasperPrint print = jasperRepository.getReportTemplate("yearReport.jrxml", jrBeanCollectionDataSource);
			JasperExportManager.exportReportToPdfFile(print, user.getName() + ".pdf");
			File file = new File(user.getName() + ".pdf");
			mailSender.sendReport(user.getName(), user.getEmail(), file, i18n.getMessage("report.year.email.title"));
		}
	}

	private List<ReportPaymentObject> producePaymentObject(final Place place) {
		List<ReportPaymentObject> objects = new ArrayList<>();
		List<PaymentHistory> payments = paymentHistoryService.findByPlaceInLastYear(place);
		for (PaymentHistory payment : payments) {
			objects.add(new ReportPaymentObject(payment, localeConfig.localeProvider().getLocale()));
		}
		return objects;
	}
}
