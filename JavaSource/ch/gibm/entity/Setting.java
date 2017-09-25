package ch.gibm.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

@Entity
@NamedQuery(name = "Setting.findSettingByUser", query = "select s from Setting s where s.user = :username")
public class Setting implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_SETTING_BY_USER = "Setting.findSettingByUser";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String user;
	private String hexcolor;
	
	@Version
	private long version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getHexcolor() {
		return hexcolor;
	}
	public void setHexcolor(String hexcolor) {
		this.hexcolor = hexcolor;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	
	

}
