package pl.zt.mk.beans.language;

import pl.zt.mk.annotations.SessionScoped;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

@SessionScoped
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
