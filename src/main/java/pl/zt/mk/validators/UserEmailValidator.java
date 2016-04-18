package pl.zt.mk.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.zt.mk.repo.UserRepository;
import pl.zt.mk.services.impl.InternationalizationServiceImpl;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Objects;

/**
 * Created by zt on 2016-04-02.
 */
@Component
@Scope(value = "request")
public class UserEmailValidator implements Validator {
	@Autowired
	UserRepository userRepository;
	@Autowired
	InternationalizationServiceImpl i18n;

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

		String mail = (String) o;

		if (org.apache.commons.lang3.StringUtils.isNoneBlank(mail)) {
			if (!org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(mail))
				throw new ValidatorException(new FacesMessage(i18n.getMessage("invalid-mail")));

			if (Objects.nonNull(userRepository.findByEmail(mail)))
				throw new ValidatorException(new FacesMessage(i18n.getMessage("mail-exists")));
		}

	}
}
