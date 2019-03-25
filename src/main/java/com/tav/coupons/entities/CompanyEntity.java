package com.tav.coupons.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name="companies")
public class CompanyEntity {

	@GeneratedValue
	@Id	
	private long id;
	
	@Column(name="company_name", nullable=false, unique=true)
	private String companyName;
	
	@OneToMany (mappedBy="company", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
	@JsonIgnore
	private Collection<UserLoginDetailsEntity> users;
	
	@OneToMany (mappedBy="company", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
	@JsonIgnore
	private Collection<CouponEntity> companyCoupons;

//-----------------------------Constructors----------------------------------------------
	
	public CompanyEntity () {
	}
	
	public CompanyEntity(String companyName, Collection<UserLoginDetailsEntity> users,
			Collection<CouponEntity> companyCoupons) {
		this.companyName = companyName;
		this.users = users;
		this.companyCoupons = companyCoupons;
	}
	
	public CompanyEntity(long id, String companyName, Collection<UserLoginDetailsEntity> users,
			Collection<CouponEntity> companyCoupons) {
		this.id = id;
		this.companyName = companyName;
		this.users = users;
		this.companyCoupons = companyCoupons;
	}

//-----------------------------Getters and Setters---------------------------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Collection<UserLoginDetailsEntity> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserLoginDetailsEntity> users) {
		this.users = users;
	}

	public Collection<CouponEntity> getCompanyCoupons() {
		return companyCoupons;
	}

	public void setCompanyCoupons(Collection<CouponEntity> companyCoupons) {
		this.companyCoupons = companyCoupons;
	}

	@Override
	public String toString() {
		return "CompanyEntity [id=" + id + ", companyName=" + companyName + ", users=" + users + ", companyCoupons="
				+ companyCoupons + "]";
	}
}
