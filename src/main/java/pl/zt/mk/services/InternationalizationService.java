package pl.zt.mk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zt.mk.config.LocaleConfig;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by zt on 2016-04-02.
 */
@Service
public class InternationalizationService {

	@Autowired
	private LocaleConfig localeConfig;

	public String getMessage(String key) {
		Locale locale = localeConfig.localeProvider().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		if (bundle.containsKey(key)) {
			key = bundle.getString(key);
		}
		return String.format(key);
	}

}
