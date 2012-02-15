package com.gw_systems.invoicer.datasource;

import java.util.Iterator;
import java.util.List;

import com.gw_systems.invoicer.beans.InvoiceItem;

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
			return item.getTitle();
		else if ( field.getName().equals( "AFA" ) )
			return item.getTax();
		else if ( field.getName().equals( "MENNYISEG" ) )
			return item.getCount();
		else if ( field.getName().equals( "EGYSEGAR" ) )
			return item.getCostPerCount();
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
