package ch.gibm.dao;

import java.util.HashMap;
import java.util.Map;

import ch.gibm.entity.User;

public class UserDAO extends GenericDAO<User> {

	private static final long serialVersionUID = 1L;
	
	public UserDAO() {
		super(User.class);
	}
	
	public User findSaltWithUser(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		return super.findOneResult(User.FIND_SALT_BY_USER, params);
	}
	

}
