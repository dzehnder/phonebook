package ch.gibm.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.gibm.entity.Contact;
import ch.gibm.facade.ContactFacade;

@ViewScoped
@ManagedBean(name = "cityBean")
public class CityBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Contact city;
	private List<Contact> cities;
	private ContactFacade cityFacade;
	
	public Contact getCity() {
		if (city == null) {
			city = new Contact();
		}
		return city;
	}

	public void setCity(Contact city) {
		this.city = city;
	}
	
	
	public ContactFacade getCityFacade() {
		if (cityFacade == null) {
			cityFacade = new ContactFacade();
		}
		return cityFacade;
	}
	
	public void loadCities() {
		cities = getCityFacade().listAll();
	}
	
	public List<Contact> getAllCities() {
		if (cities == null) {
			loadCities();
		}
		return cities;
	}
	
	public void resetCity() {
		city = new Contact();
	}
	
	public void createCity() {
		try {
			getCityFacade().createCity(city);
			closeDialog();
			displayInfoMessageToUser("Successfully created");
			loadCities();
			resetCity();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem has occurred while saving. Please try again later");
			e.printStackTrace();
		}
	}
	
	public void updateCity() {
		try {
			getCityFacade().updateCity(city);
			closeDialog();
			displayInfoMessageToUser("Successfully updated");
			loadCities();
			resetCity();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem has occurred while updating. Please try again later");
			e.printStackTrace();
		}
	}
	
	public void deleteCity() {
		try {
			getCityFacade().deleteCity(city);
			closeDialog();
			displayInfoMessageToUser("Deleted with success");
			loadCities();
			resetCity();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while removing. Try again later");
			e.printStackTrace();
		}
	}
}
