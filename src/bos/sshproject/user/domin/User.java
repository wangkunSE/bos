package bos.sshproject.user.domin;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String id;
	private String username;
	private String password;
	private Double salary;
	private String gender;
	private Timestamp birthday;
	private String station;
	private Integer telephone;
	private String remark;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	/** full constructor */
	public User(String id, String username, String password, Double salary,
			String gender, Timestamp birthday, String station,
			Integer telephone, String remark) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.salary = salary;
		this.gender = gender;
		this.birthday = birthday;
		this.station = station;
		this.telephone = telephone;
		this.remark = remark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Double getSalary() {
		return this.salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public Integer getTelephone() {
		return this.telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}