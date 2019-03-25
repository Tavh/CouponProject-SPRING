package com.tav.coupons.entities;

import java.util.Date;
import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name="coupons")
public class CouponEntity {
	
	@GeneratedValue
	@Id	
	private long id;
	
	@Column (name="title", nullable=false, unique=true)
	private String title;
	
	@ManyToOne
	private CompanyEntity company;
	
	@Column (name="start_date", nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date startDate;
	
	@Column (name="end_date", nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date endDate;
	
	@Column (name="amount", nullable=false)
	private int amount;
	
	@Column (name="message", nullable=false, columnDefinition="LONGTEXT")
	private String message;
	
	@Column (name="price", nullable=false)
	private float price;

//-----------------------------Constructors---------------------------------------

	public CouponEntity() {
	}

	public CouponEntity(String title, CompanyEntity company, Date startDate, Date endDate, int amount,
			String message, float price) {
		this.title = title;
		this.company = company;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.message = message;
		this.price = price;
	}
	
	public CouponEntity(long id, String title, CompanyEntity company, Date startDate, Date endDate, int amount,
			String message, float price) {
		this.id = id;
		this.title = title;
		this.company = company;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.message = message;
		this.price = price;
	}

//-----------------------------Getters and Setters---------------------------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CouponEntity [id=" + id + ", title=" + title + ", company=" + company + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", amount=" + amount + ", message=" + message + ", price=" + price + "]";
	}

	
}
