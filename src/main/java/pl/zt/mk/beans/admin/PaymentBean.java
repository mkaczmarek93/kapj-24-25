package pl.zt.mk.beans.admin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.zt.mk.annotations.ViewScoped;
import pl.zt.mk.entity.Payment;
import pl.zt.mk.entity.meta.CounterType;
import pl.zt.mk.entity.meta.PaymentType;
import pl.zt.mk.jsf.JsfUtils;
import pl.zt.mk.lazy.LazyModel;
import pl.zt.mk.services.PaymentService;
import pl.zt.mk.services.impl.InternationalizationServiceImpl;

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
	InternationalizationServiceImpl internationalizationServiceImpl;
	@Autowired
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private PaymentService paymentService;
	private Payment payment = new Payment();
	private LazyModel<Payment> paymentLazyModel;
	private Payment selectedPayment = null;
	private List<SelectItem> paymentTypes = null;
	private List<SelectItem> counterTypes;

	public PaymentBean() {
		initTypes();
	}

	public void init() {
		paymentLazyModel = paymentService.findAll();
	}

	public void initTypes() {
		SelectItem empty = new SelectItem();
		paymentTypes = new ArrayList<>();
		counterTypes = new ArrayList<>();

		paymentTypes.add(empty);
		counterTypes.add(empty);


		for (PaymentType paymentType : PaymentType.values()) {
			paymentTypes.add(new SelectItem(paymentType));
		}

		for (CounterType counterType : CounterType.values()) {
			counterTypes.add(new SelectItem(counterType));
		}
	}

	public void deactive() {
		this.selectedPayment.setActive(false);
		this.savePayment(this.selectedPayment);
	}

	public void addPayment() {
		savePayment(this.payment);
	}

	public void savePayment(Payment payment) {
		JsfUtils.createDefaultMessage(paymentService.savePayment(payment), internationalizationServiceImpl);
	}


	public void preEdit() {
		this.payment = this.selectedPayment;
	}

	public boolean isUnitRequired() {
		return Objects.nonNull(this.payment) && Objects.nonNull(this.payment.getType()) && PaymentType.PER_UNIT.equals(this.payment.getType());
	}

}
