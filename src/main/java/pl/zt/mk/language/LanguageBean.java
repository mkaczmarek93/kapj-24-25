package pl.zt.mk.language;

import java.util.Locale;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class LanguageBean {

	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}
	
	public void setLanguage(String language){
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
	}

}
