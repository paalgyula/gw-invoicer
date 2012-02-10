package com.gw_systems.invoicer.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Company {
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, name="COMPANY_SEQ", sequenceName="COMPANY_SEQ")
	@GeneratedValue(generator="COMPANY_SEQ", strategy=GenerationType.SEQUENCE)
	int id;
	
	@Column(nullable=false, unique=true)
	String companyName;
	
	@Column(nullable=false)
	int postalCode;
	
	@Column(nullable=false)
	String city;
	
	@Column(nullable=false)
	String address;
	
	@Column(nullable=false, unique=true)
	String taxNumber;
	
	@Column(nullable=false)
	String adminPassword;

	@OneToMany
	List<Customer> customers;
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return getCompanyName();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
