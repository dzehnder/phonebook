package ch.gibm.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.gibm.util.PermissionUtil;

@ViewScoped
@ManagedBean
public class PermissionBean implements Serializable {
	private static final long serialVersionUID = -1449980321582919199L;

	public boolean isCurrentUserAdmin() {
		return PermissionUtil.isCurrentUserAdmin();
	}

	public String getCurrentUser() {
		return PermissionUtil.getCurrentUsername();
	}
}