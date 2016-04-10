package pl.zt.mk.beans.admin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.zt.mk.entity.Address;
import pl.zt.mk.services.AddressService;
import pl.zt.mk.services.InternationalizationService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Objects;

/**
 * Created by Michal on 10.04.2016.
 */
@Component
@Scope(value = "view")
@Getter
@Setter
public class AddingAddressBean {

	private String city;
	private String street;
	private Integer flatNumber;
	private Integer apartmentNumber;
	private Integer collaborators;

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private InternationalizationService i18n;

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private AddressService addressService;

	public void addAddress() {
		if (Objects.nonNull(city)
				&& Objects.nonNull(street)
				&& Objects.nonNull(flatNumber)
				&& Objects.nonNull(collaborators)) {
			String msg;
			FacesMessage.Severity severity;
			if (addressService.addAddress(new Address(city, street, flatNumber, apartmentNumber, collaborators))) {
				msg = "good";
				severity = FacesMessage.SEVERITY_INFO;
			} else {
				msg = "bad";
				severity = FacesMessage.SEVERITY_FATAL;
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, i18n.getMessage(msg), i18n.getMessage(msg)));
		}
	}
}
