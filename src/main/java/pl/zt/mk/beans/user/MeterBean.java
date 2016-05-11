package pl.zt.mk.beans.user;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.zt.mk.annotations.ViewScoped;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Place;
import pl.zt.mk.jsf.JsfUtils;
import pl.zt.mk.lazy.LazyModel;
import pl.zt.mk.services.InternationalizationService;
import pl.zt.mk.services.MeterService;
import pl.zt.mk.services.PaymentHistoryService;
import pl.zt.mk.services.PlaceService;

import javax.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by zt on 2016-04-11.
 */
@ViewScoped
@Getter
@Setter
public class MeterBean implements Serializable {

	@Autowired
	MeterService meterService;


	@Autowired
	PlaceService placeService;

	@Autowired
	PaymentHistoryService paymentHistoryService;

	@Autowired
	InternationalizationService internationalizationService;

	private LazyModel<Meter> metersLazyModel;
	private Meter selectedMeter;
	private Meter meter = new Meter();
	private String name;
	private Place place;

	public void init() {
		this.name = SecurityContextHolder.getContext().getAuthentication().getName();
		this.place = placeService.findPlaceByUserEmail(name);
		if (Objects.nonNull(place)) {
			this.metersLazyModel = meterService.findByUserEmail(name);
			this.meter = meterService.findCurrentMeterLevelByName(name, new LocalDate());
		}


	}

	public void addMeter() {
		try {
			meterService.addMeter(this.meter);
		} catch (IllegalArgumentException e) {

			JsfUtils.createMessage(internationalizationService.getMessage("user-has-no-place"), FacesMessage.SEVERITY_FATAL);
		} catch (DataAccessException e) {
			JsfUtils.createMessage(internationalizationService.getMessage("bad"), FacesMessage.SEVERITY_FATAL);

		}
		JsfUtils.createDefaultMessage(true, internationalizationService);
	}

	public void preEdit() {
		this.meter = this.selectedMeter;
	}

	public void initCurrentMeter() {
		this.meter = meterService.findCurrentMeterLevelByName(name, new LocalDate());
		if (Objects.isNull(this.meter)) {
			this.meter = new Meter();
			this.meter.setPlace(this.place);
		}
	}

	public void calculateAndSaveHistory() {

	}
}
