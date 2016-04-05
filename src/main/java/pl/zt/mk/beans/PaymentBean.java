package pl.zt.mk.beans;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import pl.zt.mk.annotations.ViewScoped;
import pl.zt.mk.entity.Payment;
import pl.zt.mk.entity.meta.PaymentType;
import pl.zt.mk.services.InternationalizationService;
import pl.zt.mk.services.PaymentService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by zt on 2016-04-05.
 */
@ViewScoped
@Getter()
@Setter
public class PaymentBean implements Serializable {

	@Autowired
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	InternationalizationService internationalizationService;
	@Autowired
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private PaymentService paymentService;
	private Payment payment = new Payment();
	private List<Payment> paymentList = null;
	private Payment selectedPayment = null;
	private List<SelectItem> paymentTypes = null;

	public PaymentBean() {
		initTypes();
	}

	public void init() {
		paymentList = paymentService.findAll();
	}

	public void initTypes() {
		paymentTypes = new ArrayList<>();

		for (PaymentType paymentType : PaymentType.values()) {
			paymentTypes.add(new SelectItem(paymentType));
		}
	}


	public void addPayment() {

		String message;
		FacesMessage.Severity severity;
		try {
			paymentService.addPayment(this.payment);
			message = "good";
			severity = FacesMessage.SEVERITY_INFO;
		} catch (DataAccessException e) {
			message = "bad";
			severity = FacesMessage.SEVERITY_FATAL;
		}

		message = internationalizationService.getMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, message));
	}


	public boolean isUnitRequired() {
		return Objects.nonNull(this.payment) && Objects.nonNull(this.payment.getType()) && PaymentType.PER_UNIT.equals(this.payment.getType());
	}

}
