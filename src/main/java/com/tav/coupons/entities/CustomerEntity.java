package com.tav.coupons.entities;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table (name="customers")
public class CustomerEntity implements Serializable {

	@Id
	@OneToOne
	private UserLoginDetailsEntity userLoginDetails;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="customer", fetch=FetchType.LAZY)
	@JsonIgnore
	private List<PurchaseEntity> purchases;
	
	@Column (name="customer_name", nullable=false)
	private String customerName;

//-----------------------------Constructors---------------------------------------

	public CustomerEntity() {
	}
	
	public CustomerEntity(UserLoginDetailsEntity userLoginDetails, List<PurchaseEntity> purchases,
			String customerName) {
		this.userLoginDetails = userLoginDetails;
		this.purchases = purchases;
		this.customerName = customerName;
	}

//-----------------------------Getters and Setters---------------------------------------

	public UserLoginDetailsEntity getUserLoginDetails() {
		return userLoginDetails;
	}

	public void setUserLoginDetails(UserLoginDetailsEntity userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
	}

	public Collection<PurchaseEntity> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseEntity> purchases) {
		this.purchases = purchases;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "CustomerEntity [userLoginDetails=" + userLoginDetails + ", purchases=" + purchases + ", customerName="
				+ customerName + "]";
	}
	
}
