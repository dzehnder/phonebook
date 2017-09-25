package ch.gibm.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ch.gibm.entity.Contact;
import ch.gibm.facade.ContactFacade;

@FacesConverter(forClass = ch.gibm.entity.Contact.class)
public class ContactConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		ContactFacade contactFacade = new ContactFacade();
		int contactId;
		
		try {
			contactId = Integer.parseInt(arg2);			
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot convert city object" , "Cannot convert city object"));
		}
		
		return contactFacade.findContact(contactId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		if (arg2 == null) {
			return "";
		}
		
		Contact contact = (Contact) arg2;
		return String.valueOf(contact.getId());
	}

}
