package pl.zt.mk.beans.admin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONObject;
import org.primefaces.json.JSONTokener;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.zt.mk.converters.JsonConverter;
import pl.zt.mk.converters.dto.ReadAddress;
import pl.zt.mk.entity.Apartment;
import pl.zt.mk.entity.Block;
import pl.zt.mk.services.AddressService;
import pl.zt.mk.services.InternationalizationService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Michal on 10.04.2016.
 */
@Component
@Scope(value = "view")
@Getter
@Setter
@Slf4j
public class AddingAddressBean implements Serializable {

	@Getter(AccessLevel.NONE)
	private static final String GEOCODING_URL = "https://maps.googleapis.com/maps/api/geocode/json?language=pl";
	@Getter(AccessLevel.NONE)
	private static final String GEOCODING_RESULT_TYPE = "&result_type=street_address|postal_code";
	@Getter(AccessLevel.NONE)
	private static final String GOOGLE_API_KEY = "&key=AIzaSyBdC2NNDL1PYq1O131l7KDRylzYa5dx1D4";

	private String city;
	private String postCode;
	private String street;
	private String flatNumber;
	private Double lat;
	private Double lng;
	private MapModel emptyModel;
	private Block selectedBlock;

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private InternationalizationService i18n;

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private AddressService addressService;

	@Autowired
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private JsonConverter jsonConverter;


	@PostConstruct
	public void init() {
		this.emptyModel = new DefaultMapModel();
	}

	public void geolocationPosition() throws Exception {
		resetAddress();
		ReadAddress read = jsonConverter.getAddress(readUrl());
		if (read.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wskazano niepoprawny ades.", ""));
			return;
		}
		log.info("Block: " + read.toString());
		city = read.getCity();
		postCode = read.getPostCode();
		street = read.getStreet();
		flatNumber = read.getFlatNumber();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Znaleziono adres.", read.getFormattedAddress()));
	}

	public void addAddress() throws IOException {
		if (Objects.nonNull(city)
				&& Objects.nonNull(postCode)
				&& Objects.nonNull(street)) {
			String msg;
			FacesMessage.Severity severity;
			if (addressService.addAddress(new Block(city, postCode, street, flatNumber, null))) {
				msg = "good";
				severity = FacesMessage.SEVERITY_INFO;
			} else {
				msg = "bad";
				severity = FacesMessage.SEVERITY_FATAL;
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, i18n.getMessage(msg), i18n.getMessage(msg)));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, i18n.getMessage("bad"), i18n.getMessage("check-on-map")));
			log.info("Block not added");
		}
	}

	private String readUrl() throws Exception {
		URL url = new URL(GEOCODING_URL + GEOCODING_RESULT_TYPE + "&latlng=" + lat + "," + lng + GOOGLE_API_KEY);
		log.info("Get address from: " + url.toString());
		JSONObject jo = (JSONObject) new JSONTokener(IOUtils.toString(url)).nextValue();
		return jo.toString();
	}

	public void resetAddress() {
		this.city = null;
		this.postCode = null;
		this.street = null;
		this.flatNumber = null;
	}
}
