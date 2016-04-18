package pl.zt.mk.jsf;

import pl.zt.mk.services.InternationalizationService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by zt on 2016-04-12.
 */
public abstract class JsfUtils {

	/**
	 * tworzy domyslną globalna wiadomosć
	 *
	 * @param flag
	 * @param @NotNull internationalizationService
	 */
	public static void createDefaultMessage(boolean flag, InternationalizationService internationalizationService) {

		String message;
		FacesMessage.Severity severity;
		if (flag) {
			message = "good";
			severity = FacesMessage.SEVERITY_INFO;
		} else {
			message = "bad";
			severity = FacesMessage.SEVERITY_FATAL;
		}

		message = internationalizationService.getMessage(message);

		addMessage(null, message, severity);
	}

	public static void createMessage(String message, FacesMessage.Severity severity) {
		addMessage(null, message, severity);

	}

	private static void addMessage(String id, String message, FacesMessage.Severity severity) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(severity, message, message));
	}
}
