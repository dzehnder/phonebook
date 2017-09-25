package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.SettingDAO;
import ch.gibm.entity.Setting;

public class SettingFacade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SettingDAO settingDAO = new SettingDAO();
	
	public void createSetting(Setting setting) {
		EntityManagerHelper.beginTransaction();
		settingDAO.save(setting);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public void updateSetting(Setting setting) {
		EntityManagerHelper.beginTransaction();
		Setting newSetting = EntityManagerHelper.getEntityManager().merge(setting);
		newSetting.setHexcolor(setting.getHexcolor());
		settingDAO.update(newSetting);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public void deleteSetting(Setting setting) {
		EntityManagerHelper.beginTransaction();
		Setting persistedStg = settingDAO.findReferenceOnly(setting.getId());
		settingDAO.delete(persistedStg);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public Setting findSetting(int settingId) {
		EntityManagerHelper.beginTransaction();
		Setting setting = settingDAO.find(settingId);
		EntityManagerHelper.commitAndCloseTransaction();
		return setting;
	}
	
	public List<Setting> listAll() {
		EntityManagerHelper.beginTransaction();
		List<Setting> result = settingDAO.findAll();
		EntityManagerHelper.commitAndCloseTransaction();
		return result;
	}

	public Setting findSettingWithUser(String username) {
		EntityManagerHelper.beginTransaction();
		Setting setting = settingDAO.findSettingWithUser(username);
		EntityManagerHelper.commitAndCloseTransaction();
		return setting;
	}
}
