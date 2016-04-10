package pl.zt.mk.beans.admin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.zt.mk.services.InternationalizationService;

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
	private IntegrationAutoConfiguration collaborators;

	@Autowired
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private InternationalizationService i18n;

	public void addAddress() {
		if (Objects.nonNull(city)
				&& Objects.nonNull(street)
				&& Objects.nonNull(flatNumber)
				&& Objects.nonNull(collaborators)) {
			String msg = "";

		}
	}
}
