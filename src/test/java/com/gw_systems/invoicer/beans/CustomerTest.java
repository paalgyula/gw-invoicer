package com.gw_systems.invoicer.beans;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

import com.gw_systems.invoicer.StaticTools;

public class CustomerTest {
	static {
		StaticTools.config = new AnnotationConfiguration().configure();
	}
	
	@Test
	public void createCustomer() {
		Session session = StaticTools.createSession();
		Transaction tx = session.beginTransaction();
		
		Customer cust = new Customer();
		cust.setName( "goofy" );
		cust.setPassword( "icGp4" );
		session.save( cust );
		
		Company comp = (Company)session.createQuery("from Company c where c.companyName = :NAME").setString( "NAME", "GW-Systems Kft." ).uniqueResult();
		comp.customers.add( cust );
		session.save( comp );
		
		tx.commit();
		session.close();
	}

}
