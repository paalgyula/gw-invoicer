package com.gw_systems.invoicer.datasource;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class JRDataSourceImpl implements JRDataSource {

	Iterator<InvoiceItem> itr;
	InvoiceItem item;
	
	public JRDataSourceImpl( List<InvoiceItem> items ) {
		itr = items.iterator();
	}
	
	public Object getFieldValue(JRField field) throws JRException {
		if ( field.getName().equals( "MEGNEVEZES" ) )
			return item.title;
		else if ( field.getName().equals( "AFA" ) )
			return item.tax;
		else if ( field.getName().equals( "MENNYISEG" ) )
			return item.count;
		else if ( field.getName().equals( "EGYSEGAR" ) )
			return item.costPerCount;
		else
			return "hello";
	}

	public boolean next() throws JRException {
		if ( itr.hasNext() ) {
			item = itr.next();
			return true;
		}
		
		return false;
	}

}
