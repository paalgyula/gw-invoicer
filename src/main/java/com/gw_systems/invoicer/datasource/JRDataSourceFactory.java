package com.gw_systems.invoicer.datasource;

import java.util.ArrayList;

public class JRDataSourceFactory {
	public JRDataSourceImpl getDataSource() {
		return new JRDataSourceImpl( new ArrayList<InvoiceItem>() );
	}
}
