package pl.zt.mk.cron.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.zt.mk.entity.PaymentHistory;

import java.util.Locale;

/**
 * Created by Michal on 30.05.2016.
 */
@Getter
@Setter
@AllArgsConstructor
public class ReportPaymentObject {

	private String month;
	private String charge;
	private String paymentDate;

	public ReportPaymentObject(PaymentHistory payment, Locale locale) {
		charge = String.valueOf(payment.getCharge());
		if (null != payment.getPaymentDate())
			paymentDate = payment.getPaymentDate().toString();
		else
			paymentDate = "-";
		month = payment.getMonth().toString("MMM");
	}


}
