package ch.gibm.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ch.gibm.entity.City;
import ch.gibm.facade.CityFacade;

@FacesConverter(forClass = ch.gibm.entity.City.class)
public class CityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		CityFacade cityFacade = new CityFacade();
		int cityId;
		
		try {
			cityId = Integer.parseInt(arg2);			
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot convert city object" , "Cannot convert city object"));
		}
		
		return cityFacade.findCity(cityId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		if (arg2 == null) {
			return "";
		}
		
		City city = (City) arg2;
		return String.valueOf(city.getId());
	}

}
