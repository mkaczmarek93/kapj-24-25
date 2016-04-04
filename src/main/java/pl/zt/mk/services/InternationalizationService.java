package pl.zt.mk.services;

import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by zt on 2016-04-02.
 */
@Service
public class InternationalizationService {


	public String getMessage(String key) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		if (bundle.containsKey(key)) {
			key = bundle.getString(key);
		}
		return String.format(key);
	}

}
