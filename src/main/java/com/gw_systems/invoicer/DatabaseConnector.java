package com.gw_systems.invoicer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.gw_systems.invoicer.frames.SplashScreenDialog;

public class DatabaseConnector extends Thread {
	private SplashScreenDialog mf;
	
	public static Session session;
	public static Configuration config;
	
	public DatabaseConnector(SplashScreenDialog mf) {
		this.mf = mf;
	}
	
	@Override
	public void run() {
		this.mf.getLblStatusindicator().setText( "Connecting to the database..." );
		this.mf.getProgressBar().setValue( 30 );
		
		config = new AnnotationConfiguration().configure();
		this.mf.getLblStatusindicator().setText( "Opening database session..." );
		this.mf.getProgressBar().setValue( 50 );
		session = config.buildSessionFactory().openSession();
		
		this.mf.getLblStatusindicator().setText( "Validating settings..." );
		this.mf.getProgressBar().setValue( 70 );
		
		Transaction tx = session.beginTransaction();
		tx.commit();
		
		this.mf.getLblStatusindicator().setText( "Database check completed. Closing session..." );
		this.mf.getProgressBar().setValue( 80 );
		
		session.close();
		
		this.mf.getLblStatusindicator().setText( "Done..." );
		this.mf.getProgressBar().setValue( 100 );
		
		try {
			Thread.sleep( 2000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.mf.showLoginDialog();
		}
	}
}
