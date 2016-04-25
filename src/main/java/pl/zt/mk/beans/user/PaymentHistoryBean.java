package pl.zt.mk.beans.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.zt.mk.annotations.ViewScoped;
import pl.zt.mk.entity.PaymentHistory;
import pl.zt.mk.entity.Place;
import pl.zt.mk.lazy.LazyModel;
import pl.zt.mk.services.PaymentHistoryService;
import pl.zt.mk.services.PlaceService;

import java.util.Objects;

/**
 * Created by zt on 2016-04-25.
 */
@ViewScoped
@Getter
@Setter
public class PaymentHistoryBean {

	Place place;
	LazyModel<PaymentHistory> paymentsLazyModel;

	@Autowired
	PlaceService placeService;

	@Autowired
	PaymentHistoryService paymentHistoryService;

	public void init(){
		this.place = placeService.findPlaceByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (Objects.nonNull(this.place)){
			this.paymentsLazyModel = paymentHistoryService.findByPlace(place);
		}
	}
}
