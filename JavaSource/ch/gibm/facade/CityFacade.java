package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import ch.gibm.dao.CityDAO;
import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.entity.City;

public class CityFacade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private CityDAO cityDAO = new CityDAO();
	
	
	public List<City> listAll() {
		EntityManagerHelper.beginTransaction();
		List<City> result = cityDAO.findAll();
		EntityManagerHelper.commitAndCloseTransaction();
		return result;
	}

	public void createCity(City city) {
		EntityManagerHelper.beginTransaction();
		cityDAO.save(city);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public City findCity(int cityId) {
		EntityManagerHelper.beginTransaction();
		City city = cityDAO.find(cityId);
		EntityManagerHelper.commitAndCloseTransaction();
		return city;
	}
	
	public void updateCity(City city) {
		EntityManagerHelper.beginTransaction();
		City newCity = EntityManagerHelper.getEntityManager().merge(city);
		newCity.setName(city.getName());
		cityDAO.update(newCity);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public void deleteCity(City city) {
		EntityManagerHelper.beginTransaction();
		City persistedLng = cityDAO.findReferenceOnly(city.getId());
		cityDAO.delete(persistedLng);
		EntityManagerHelper.commitAndCloseTransaction();
	}
}
