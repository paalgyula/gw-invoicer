package com.gw_systems.invoicer;

import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import com.gw_systems.invoicer.frames.MainFrame;

public class DatabaseConnector extends Thread {
	private MainFrame mf;
	
	public DatabaseConnector(MainFrame mf) {
		this.mf = mf;
	}
	
	@Override
	public void run() {
		this.mf.getLblStatusindicator().setText( "Connecting to the database..." );
		this.mf.getProgressBar().setValue( 30 );
		
		MainFrame.config = new AnnotationConfiguration().configure();
		this.mf.getLblStatusindicator().setText( "Opening database session..." );
		this.mf.getProgressBar().setValue( 50 );
		MainFrame.session = MainFrame.config.buildSessionFactory().openSession();
		
		this.mf.getLblStatusindicator().setText( "Validating settings..." );
		this.mf.getProgressBar().setValue( 70 );
		
		Transaction tx = MainFrame.session.beginTransaction();
		tx.commit();
		
		this.mf.getLblStatusindicator().setText( "Database check completed. Closing session..." );
		this.mf.getProgressBar().setValue( 80 );
		
		MainFrame.session.close();
		
		this.mf.getLblStatusindicator().setText( "Done..." );
		this.mf.getProgressBar().setValue( 100 );
		
	}
}
