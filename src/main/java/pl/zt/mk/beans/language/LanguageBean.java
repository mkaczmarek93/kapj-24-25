package pl.zt.mk.beans.language;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

@Component
@Scope(value = "session")
public class LanguageBean implements Serializable {

	private Locale locale;

	public LanguageBean() {
		this.locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}

	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void setLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));

	}

}
