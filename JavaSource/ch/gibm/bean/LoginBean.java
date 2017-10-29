package ch.gibm.bean;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ch.gibm.entity.User;
import ch.gibm.facade.UserFacade;


@RequestScoped
@ManagedBean(name = "loginBean")
public class LoginBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(LoginBean.class);
	
	private User user;
	private UserFacade userFacade;
	private List<User> users;
	
	private String username;
	private String password;

	/**
	 * @return username from the login form
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * set the username of current request
	 * @param username 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 
	 * @return the plain password entered in the login form
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * set the password for the current request
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Secure login of a user.
	 * Search for the Salt of the specified user. If no salt is existing, return to the login page and display an error.
	 * If the salt exists, append it to the password and generate a hash value. 
	 * Login the user if the hashed password is equal to the stored password in the database.
	 * 
	 * @return redirect path
	 */
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		
		User saltUser = getSaltWithUser(this.username);
		if (saltUser == null) {
			context.addMessage(null, new FacesMessage("Username or Password incorrect."));
			logger.warn("User " + this.username + " used wrong credentials.");
			this.password = null;
			return "/pages/public/login/login.xhtml";
		}
		
		String pass = generatePassword(this.password, saltUser.getSalt().getBytes());
		this.password = null;
		
		try {
			request.login(this.username, pass);
			logger.info("User " + this.username + " logged in.");
		} catch (ServletException e) {
			logger.error(e);
			context.addMessage(null, new FacesMessage("Username or Password incorrect."));
			return "/pages/public/login/login.xhtml";
	    }
	    return "/pages/public/index.xhtml";
	  }

	/**
	 * securely logout user and invalidate session
	 */
	  public void logout() {
	    FacesContext context = FacesContext.getCurrentInstance();

	    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
	    
	    HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		}
	    
	    try {
	    		request.logout();
	    		logger.info("Log out user" + this.username);
	    } catch (ServletException e) {
	    		logger.error(e);
	    		context.addMessage(null, new FacesMessage("Logout failed."));
	    }
	  }
	  
	  public String register() {
		 
		  SecureRandom random = new SecureRandom();
		  byte[] values = new byte[32];
		  random.nextBytes(values);
		  String salt = new String(values);
		  this.user = new User();
		  this.user.setUser(this.username);
		  this.user.setSalt(salt);
		  createUser();
		  generatePassword(this.password, values);
		  
		  return "/pages/public/tmp.xhtml";
	  }
	  
	  public User getUser() {
			if (user == null) {
				user = new User();
			}
			return user;
	  }
	  
	  public void loadUsers() {
			users = getUserFacade().listAll();
	  }
	  
		public void resetUser() {
			user = new User();
		}
	  
	  public UserFacade getUserFacade() {
			if (userFacade == null) {
				userFacade = new UserFacade();
			}
			return userFacade;
	  }
	  
	  public void createUser() {
			try {
				getUserFacade().createUser(user);
				displayInfoMessageToUser("Successfully created new user");
				loadUsers();
				resetUser();
			} catch (Exception e) {
				logger.error(e);
				keepDialogOpen();
				displayErrorMessageToUser("A problem has occurred while creating new user. Please try again later");
				e.printStackTrace();
			}
		}
	  
	  /**
	   * Securely generate a hashed password with salt
	   * 
	   * @param plain password 
	   * @param random salt for this user
	   * @return hashed password
	   */
	  public String generatePassword(String password, byte[] salt) {
		  String pw = null;
		  try {
			  SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			  PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 5, 256);
			  SecretKey key = secretKeyFactory.generateSecret(spec);
			  pw = new String(key.getEncoded());			  
		  } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			  logger.error(e);
		}
		  
		return pw;
	  }
	  
	  public User getSaltWithUser(String username) {
			
			user = getUserFacade().findSaltWithUser(username);
			
			return user;
		}
}
