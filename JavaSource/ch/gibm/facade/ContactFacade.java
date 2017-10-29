package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import ch.gibm.dao.ContactDAO;
import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.entity.Contact;

public class ContactFacade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ContactDAO contactDAO = new ContactDAO();
	
	
	public List<Contact> listAll() {
		EntityManagerHelper.beginTransaction();
		List<Contact> result = contactDAO.findAll();
		EntityManagerHelper.commitAndCloseTransaction();
		return result;
	}

	public void createContact(Contact contact) {
		EntityManagerHelper.beginTransaction();
		contactDAO.save(contact);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public Contact findContact(int contactId) {
		EntityManagerHelper.beginTransaction();
		Contact city = contactDAO.find(contactId);
		EntityManagerHelper.commitAndCloseTransaction();
		return city;
	}
	
	public void updateContact(Contact contact) {
		EntityManagerHelper.beginTransaction();
		Contact newContact = EntityManagerHelper.getEntityManager().merge(contact);
		newContact.setName(contact.getName());
		contactDAO.update(newContact);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public void deleteContact(Contact contact) {
		EntityManagerHelper.beginTransaction();
		Contact persistedLng = contactDAO.findReferenceOnly(contact.getId());
		contactDAO.delete(persistedLng);
		EntityManagerHelper.commitAndCloseTransaction();
	}
}
