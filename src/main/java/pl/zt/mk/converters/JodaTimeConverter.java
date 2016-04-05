package pl.zt.mk.converters;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import pl.zt.mk.annotations.RequestScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by zt on 2016-04-05.
 */
@RequestScoped
public class JodaTimeConverter implements Converter {


	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String localDateInTextFormat) {
		return DateTimeFormat.forPattern("dd-MM-yyyy").parseLocalDate(localDateInTextFormat);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object localDate) {
		return DateTimeFormat.forPattern("dd-MM-yyyy").print((LocalDate) localDate);
	}
}
