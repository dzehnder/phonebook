package ch.gibm.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.gibm.entity.Contact;
import ch.gibm.facade.ContactFacade;
import ch.gibm.util.PermissionUtil;

@ViewScoped
@ManagedBean(name = "contactBean")
public class ContactBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Contact contact;
	private List<Contact> contacts;
	private ContactFacade contactFacade;
	
	public Contact getContact() {
		if (contact == null) {
			contact = new Contact();
		}
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	
	public ContactFacade getContactFacade() {
		if (contactFacade == null) {
			contactFacade = new ContactFacade();
		}
		return contactFacade;
	}
	
	public void loadContacts() {
		contacts = getContactFacade().listAll();
	}
	
	public List<Contact> getAllContacts() {
		if (contacts == null) {
			loadContacts();
		}
		return contacts;
	}
	
	public void resetContact() {
		contact = new Contact();
	}
	
	public void createContact() {
		if (PermissionUtil.isCurrentUserAdmin()) {
			try {
				getContactFacade().createContact(contact);
				closeDialog();
				displayInfoMessageToUser("Successfully created new contact");
				loadContacts();
				resetContact();
			} catch (Exception e) {
				keepDialogOpen();
				displayErrorMessageToUser("A problem has occurred while saving. Please try again later");
				e.printStackTrace();
			}			
			
		} else {
			displayErrorMessageToUser("Permission denied!");
		}
		
		
	}
	
	public void updateContact() {
		if (PermissionUtil.isCurrentUserAdmin()) {
			try {
				getContactFacade().updateContact(contact);
				closeDialog();
				displayInfoMessageToUser("Successfully updated contact");
				loadContacts();
				resetContact();
			} catch (Exception e) {
				keepDialogOpen();
				displayErrorMessageToUser("A problem has occurred while updating. Please try again later");
				e.printStackTrace();
			}			
		} else {
			displayErrorMessageToUser("Permission denied!");
		}
		
	}
	
	public void deleteContact() {
		if (PermissionUtil.isCurrentUserAdmin()) {
			try {
				getContactFacade().deleteContact(contact);
				closeDialog();
				displayInfoMessageToUser("Deleted contact with success");
				loadContacts();
				resetContact();
			} catch (Exception e) {
				keepDialogOpen();
				displayErrorMessageToUser("A problem occurred while removing. Try again later");
				e.printStackTrace();
			}
		} else {
			displayErrorMessageToUser("Permission denied!");
		}
		
	}
}
