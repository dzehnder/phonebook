package ch.gibm.dao;

import ch.gibm.entity.Contact;

public class ContactDAO extends GenericDAO<Contact> {
	
	private static final long serialVersionUID = 1L;

	public ContactDAO() {
		super(Contact.class);
	}
	
	public void delete(Contact contact) {
		super.delete(contact.getId(), Contact.class);
	}

}
