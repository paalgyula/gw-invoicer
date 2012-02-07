package com.gw_systems.invoicer.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Customer {
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, name="CUSTOMER_SEQ", sequenceName="CUSTOMER_SEQ")
	@GeneratedValue(generator="CUSTOMER_SEQ", strategy=GenerationType.SEQUENCE)
	int id;
	
	@Column(name="name", nullable=false, unique=true)
	String name;
	
	@Column(name="address", nullable=false)
	String address;

	@ManyToOne
	Company company;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
