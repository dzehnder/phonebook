package ch.gibm.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.gibm.entity.Setting;
import ch.gibm.facade.SettingFacade;
import ch.gibm.util.PermissionUtil;

@ViewScoped
@ManagedBean(name = "settingBean")
public class SettingBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Setting setting;
	private SettingFacade settingFacade;
	//UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();

	public void createSetting() {
		try {
			getSettingFacade().createSetting(setting);
			closeDialog();
			displayInfoMessageToUser("Successfully created");
			loadSetting();
			resetSetting();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem has occured while saving. Please try again later");
			e.printStackTrace();
		}
	}
	
	public void updateSetting() {
		try {
			getSettingFacade().updateSetting(setting);
			closeDialog();
			displayInfoMessageToUser("Successfully updated");
			loadSetting();
			resetSetting();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem has occured while updating. Please try again later");
			e.printStackTrace();
		}
	}
	
	public void deletePerson() {
		try {
			getSettingFacade().deleteSetting(setting);
			closeDialog();
			displayInfoMessageToUser("Successfully deleted");
			resetSetting();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem has occured while removing. Please try again later");
			e.printStackTrace();
		}
	}
	
	public Setting getSetting() {
		if (setting == null) {
			setting = createNewSetting();
		}
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	
	public void resetSetting() {
		this.setting = createNewSetting();
	}

	public SettingFacade getSettingFacade() {
		if (settingFacade == null) {
			settingFacade = new SettingFacade();
		}
		return settingFacade;
	}

	public void setSettingFacade(SettingFacade settingFacade) {
		this.settingFacade = settingFacade;
	}
	
	private void loadSetting() {
		setting = getSettingFacade().findSettingWithUser(PermissionUtil.getCurrentUsername());		
	}
	
	public Setting getSettingWithUser(String username) {
		if (setting == null ) {
			setting = createNewSetting();
		}
		setting = getSettingFacade().findSettingWithUser(username);
		
		return setting;
	}
	
	private Setting createNewSetting() {
		Setting setting = new Setting();
		setting.setUser(PermissionUtil.getCurrentUsername());
		return setting;
	}
}
