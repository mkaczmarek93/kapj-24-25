package pl.zt.mk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.zt.mk.config.providers.BundleProvider;
import pl.zt.mk.config.providers.LocaleProvider;

import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by zt on 2016-04-07.
 */
@Configuration
public class LocaleConfig {
	private final static String DEF_LOCALE = "pl";
	private final static String DEF_I18N = "messages";

	@Bean
	public LocaleProvider localeProvider() {
		return () -> {
			if (Objects.nonNull(FacesContext.getCurrentInstance())
					&& Objects.nonNull(FacesContext.getCurrentInstance().getViewRoot())
					&& Objects.nonNull(FacesContext.getCurrentInstance().getViewRoot().getLocale())) {

				return FacesContext.getCurrentInstance().getViewRoot().getLocale();
			}
			return new Locale(DEF_LOCALE);
		};
	}

	@Bean()
	@Autowired
	public BundleProvider bundleProvider(LocaleProvider localeProvider) {
		return () -> ResourceBundle.getBundle(DEF_I18N, localeProvider.getLocale());

	}


}
