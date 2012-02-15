package com.gw_systems.invoicer.datasource;

import java.util.ArrayList;

import com.gw_systems.invoicer.beans.InvoiceItem;

public class JRDataSourceFactory {
	public JRDataSourceImpl getDataSource() {
		return new JRDataSourceImpl( new ArrayList<InvoiceItem>() );
	}
}
