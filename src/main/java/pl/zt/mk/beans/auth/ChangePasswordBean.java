package pl.zt.mk.beans.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.zt.mk.services.InternationalizationService;
import pl.zt.mk.services.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by zt on 2016-04-02.
 */
@Component
@Scope(value = "view")
public class ChangePasswordBean implements Serializable {

	@Getter
	@Setter
	private String oldPassword, newPassword, repeatPassword;

	@Autowired
	InternationalizationService i18n;


	@Autowired
	UserService userService;

	public void change() {

		String message;
		FacesMessage.Severity severity;

		if (newPassword.equals(repeatPassword)) {
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			if (userService.changePassword(name, oldPassword, newPassword)) {
				message = "good";
				severity = FacesMessage.SEVERITY_INFO;
			} else {
				message = "bad";
				severity = FacesMessage.SEVERITY_FATAL;
			}
		} else {
			message = "password-incompatible";
			severity = FacesMessage.SEVERITY_FATAL;
		}
		message = i18n.getMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, message));


	}
}
