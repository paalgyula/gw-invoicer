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
public class InvoiceItem {
	
	@Id
	@SequenceGenerator(name="INVITEM_SEQ", allocationSize=1, sequenceName="INVITEM_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INVITEM_SEQ")
	@Column
	int id;
	
	@Column
	int invoiceId;
	
	@Column
	String title;
	
	@Column
	double tax;
	
	@Column
	double count;
	
	@Column
	double costPerCount;
	
	@ManyToOne
	InvoiceHead head;
	
	public InvoiceItem( int invoiceId, String title, double tax, double count, double costPerCount ) {
		this.invoiceId = invoiceId;
		this.title = title;
		this.tax = tax;
		this.count = count;
		this.costPerCount = costPerCount;
	}
	
	public InvoiceItem() {
	}

	public int getId() {
		return id;
	}
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId( int invoiceId ) {
		this.invoiceId = invoiceId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle( String title ) {
		this.title = title;
	}
	public double getTax() {
		return tax;
	}
	public void setTax( double tax ) {
		this.tax = tax;
	}
	public double getCount() {
		return count;
	}
	public void setCount( double count ) {
		this.count = count;
	}
	public double getCostPerCount() {
		return costPerCount;
	}
	public void setCostPerCount( double costPerCount ) {
		this.costPerCount = costPerCount;
	}
	
	
}
