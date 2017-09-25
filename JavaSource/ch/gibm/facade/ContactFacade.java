package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import ch.gibm.dao.ContactDAO;
import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.entity.Contact;

public class ContactFacade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ContactDAO cityDAO = new ContactDAO();
	
	
	public List<Contact> listAll() {
		EntityManagerHelper.beginTransaction();
		List<Contact> result = cityDAO.findAll();
		EntityManagerHelper.commitAndCloseTransaction();
		return result;
	}

	public void createCity(Contact city) {
		EntityManagerHelper.beginTransaction();
		cityDAO.save(city);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public Contact findCity(int cityId) {
		EntityManagerHelper.beginTransaction();
		Contact city = cityDAO.find(cityId);
		EntityManagerHelper.commitAndCloseTransaction();
		return city;
	}
	
	public void updateCity(Contact city) {
		EntityManagerHelper.beginTransaction();
		Contact newCity = EntityManagerHelper.getEntityManager().merge(city);
		newCity.setName(city.getName());
		cityDAO.update(newCity);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public void deleteCity(Contact city) {
		EntityManagerHelper.beginTransaction();
		Contact persistedLng = cityDAO.findReferenceOnly(city.getId());
		cityDAO.delete(persistedLng);
		EntityManagerHelper.commitAndCloseTransaction();
	}
}
