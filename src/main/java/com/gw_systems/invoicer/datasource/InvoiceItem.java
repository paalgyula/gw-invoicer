package com.gw_systems.invoicer.datasource;

public class InvoiceItem {
	int itemId;
	int invoiceId;
	String title;
	double tax;
	double count;
	double costPerCount;
	
	public InvoiceItem( int itemId, int invoiceId, String title, double tax, double count, double costPerCount ) {
		this.itemId = itemId;
		this.invoiceId = invoiceId;
		this.title = title;
		this.tax = tax;
		this.count = count;
		this.costPerCount = costPerCount;
	}
	
	public InvoiceItem() {
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId( int itemId ) {
		this.itemId = itemId;
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
