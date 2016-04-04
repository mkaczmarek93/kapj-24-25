package pl.zt.mk.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by zt on 2016-04-02.
 */
@Component
@Scope(value = "session")
public class InternationalizationService {
	public InternationalizationService() {
	}

	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();


	public String getMessage(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		if (bundle.containsKey(key)) {
			key = bundle.getString(key);
		}
		return String.format(key);
	}

}
