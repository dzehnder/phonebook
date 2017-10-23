package ch.gibm.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
@ManagedBean(name = "loginBean")
public class LoginBean {

	private String username;
	private String password;

	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			
			
			request.login(this.username, this.password);
		} catch (ServletException e) {
	      context.addMessage(null, new FacesMessage("Username or Password incorrect."));
	      return "error";
	    }
	    return "/pages/public/index.xhtml";
	  }

	  public void logout() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
	    try {
	      request.logout();
	    } catch (ServletException e) {
	      
	      context.addMessage(null, new FacesMessage("Logout failed."));
	    }
	  }
}
