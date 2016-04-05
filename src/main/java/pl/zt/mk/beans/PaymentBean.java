package pl.zt.mk.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.zt.mk.annotations.ViewScoped;
import pl.zt.mk.entity.Payment;
import pl.zt.mk.entity.meta.PaymentType;
import pl.zt.mk.services.PaymentService;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2016-04-05.
 */
@ViewScoped
public class PaymentBean implements Serializable {

	@Autowired
	private PaymentService paymentService;

	@Getter
	@Setter
	private Payment payment = new Payment();

	@Getter
	@Setter
	private List<Payment> paymentList = null;

	@Getter
	@Setter
	private List<SelectItem> paymentTypes = null;

	public void init() {
		paymentList = paymentService.findAll();
		paymentTypes = new ArrayList<>();

		for (PaymentType paymentType : PaymentType.values()) {
			paymentTypes.add(new SelectItem(paymentType));
		}
	}


	public void addPayment() {

		paymentService.addPayment(this.payment);
	}


}
