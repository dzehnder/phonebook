package ch.gibm.util;

import java.security.Principal;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class PermissionUtil {
	private static final String ADMIN_ROLE_NAME = "admin";
	private static final Logger logger = Logger.getLogger(PermissionUtil.class);
	
	public static boolean isCurrentUserAdmin() {
		return getHttpRequest().isUserInRole(ADMIN_ROLE_NAME);
	}
	
	public static String getCurrentUsername() {
		Principal userPrincipal = getHttpRequest().getUserPrincipal();		
		if (userPrincipal != null) {
			return userPrincipal.getName();
		}
		return null;
	}
	
	private static HttpServletRequest getHttpRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context == null) {
			logger.fatal(new IllegalStateException("No current FacesContext available"));
			throw new IllegalStateException("No current FacesContext available");
		}
		
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		return request;
	}
}
