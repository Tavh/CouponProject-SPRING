package com.tav.coupons.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.tav.coupons.enums.UserType;

@Entity
@Table(name="user_login_details")
public class UserLoginDetailsEntity {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	@ManyToOne
	private CompanyEntity company;

	// We expect the user to provide his email as his user name, validation will be 
	// carried out accordingly
	@Column(name="email", nullable=false, unique=true)
	private String email;

	@Column(name="password", nullable=false)
	private String password;

	@Column(name="type", nullable=false)
	@Enumerated(EnumType.STRING)
	private UserType userType;

	//-----------------------------Constructor---------------------------------------

	public UserLoginDetailsEntity() {
	}

	public UserLoginDetailsEntity(String email, String password, UserType userType) {
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	
	public UserLoginDetailsEntity(CompanyEntity company, String email, String password, UserType userType) {
		this.company = company;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	
	public UserLoginDetailsEntity(long id, CompanyEntity company, String email, String password, UserType userType) {
		this.id = id;
		this.company = company;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	//-----------------------------Getters and Setters------------------------------


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserLoginDetailsEntity [id=" + id + ", company=" + company + ", email=" + email + ", password="
				+ password + ", userType=" + userType + "]";
	}

}
