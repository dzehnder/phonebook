package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.UserDAO;
import ch.gibm.entity.User;

public class UserFacade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO = new UserDAO();
	
	public void createUser(User user) {
		EntityManagerHelper.beginTransaction();
		userDAO.save(user);
		EntityManagerHelper.commitAndCloseTransaction();
	}
		
	public User findSetting(int userId) {
		EntityManagerHelper.beginTransaction();
		User setting = userDAO.find(userId);
		EntityManagerHelper.commitAndCloseTransaction();
		return setting;
	}
	
	public List<User> listAll() {
		EntityManagerHelper.beginTransaction();
		List<User> result = userDAO.findAll();
		EntityManagerHelper.commitAndCloseTransaction();
		return result;
	}

	public User findSaltWithUser(String username) {
		EntityManagerHelper.beginTransaction();
		User user = userDAO.findSaltWithUser(username);
		EntityManagerHelper.commitAndCloseTransaction();
		return user;
	}
}
