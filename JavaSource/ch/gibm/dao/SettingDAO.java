package ch.gibm.dao;

import java.util.HashMap;
import java.util.Map;

import ch.gibm.entity.Setting;

public class SettingDAO extends GenericDAO<Setting> {

	private static final long serialVersionUID = 1L;
	
	public SettingDAO() {
		super(Setting.class);
	}
	
	public Setting findSettingWithUser(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		return super.findOneResult(Setting.FIND_SETTING_BY_USER, params);
	}
	
	public void delete(Setting setting) {
		super.delete(setting.getId(), Setting.class);
	}

}