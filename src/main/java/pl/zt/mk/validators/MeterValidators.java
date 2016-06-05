package pl.zt.mk.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.zt.mk.annotations.RequestScoped;
import pl.zt.mk.entity.Meter;
import pl.zt.mk.entity.Place;
import pl.zt.mk.repo.UserRepository;
import pl.zt.mk.services.MeterService;
import pl.zt.mk.services.impl.InternationalizationServiceImpl;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Objects;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Created by zt on 2016-04-02.
 */

@RequestScoped
public  class MeterValidators implements Validator {

	@Autowired
	private InternationalizationServiceImpl i18n;



	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

		Double oldVal = (Double) uiComponent.getAttributes().get("curFieldValue");
		Double newVal = (Double) o;

		if (Objects.nonNull(oldVal)) {

			if (Objects.nonNull(newVal) && oldVal > newVal)
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,i18n.getMessage("smaller-than-expected"),i18n.getMessage("smaller-than-expected")));
		}


	}

}


