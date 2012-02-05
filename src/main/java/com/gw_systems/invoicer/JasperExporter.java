package com.gw_systems.invoicer;

import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import com.gw_systems.invoicer.datasource.InvoiceItem;
import com.gw_systems.invoicer.datasource.JRDataSourceImpl;

public class JasperExporter {

	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		
		
		try {
			//JasperReport jasperReport = JasperCompileManager.compileReport( getResource( MainShell.class, "/reports/invoice.jrxml") );
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			paramMap.put( "LOGO", getResource( JasperExporter.class, "/reports/poweredby.jpg" ) );
			paramMap.put( "VERSION", "Enterprise 1.21" );
			
			paramMap.put( "INVOICER_IMAGE", 	getResource( JasperExporter.class, "/reports/poweredby.jpg" ) );
			paramMap.put( "INVOICER_COMPANY", 	"GW-Systems Kft." );
			paramMap.put( "INVOICER_ADDRESS", 	"1135 Budapest, Szent László Út 15 5/6" );
			paramMap.put( "INVOICER_TAX", 		"2122486-2-12" );
			paramMap.put( "INVOICER_TEL", 		"30/439-00-50" );
			paramMap.put( "INVOICER_MAIL", 		"info@gw-systems.com" );
			paramMap.put( "INVOICER_WEB", 		"www.gw-systems.com" );
			
			paramMap.put( "INV_SORSZ", 			"2011/1245" );
			paramMap.put( "INV_TELJ_IDO", 		null );
			paramMap.put( "INV_KELT", 			Calendar.getInstance().getTime() );
			paramMap.put( "INV_FIZ_HAT", 		null );
			paramMap.put( "INV_FIZ_MOD", 		"Készpénz" );
			paramMap.put( "INV_PELDANY", 		1 );
			
			paramMap.put( "SUMM_NETTO",			1548245d );
			paramMap.put( "SUMM_AFA_KULCS",		0.25 );
			paramMap.put( "SUMM_AFA_ERTEK",		215468d );
			paramMap.put( "SUMM_BRUTTO",		124345d );
			
			paramMap.put( "CUSTOMER_NAME", 		"SYCONTACT Kft." );
			paramMap.put( "CUSTOMER_ADDR", 		"1235 Budapest, Nemtudom Út 31." );
			
			List<InvoiceItem> itemList = new ArrayList<InvoiceItem>();
			
			for ( int i=0;i<20;i++ )
				itemList.add( new InvoiceItem(0, 0, "Kilós kenyér", 0.25, 5, 150 ) );
			
			JRDataSource dataSource = new JRDataSourceImpl( itemList );			
			//JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, dataSource );
			JasperPrint jasperPrint = JasperFillManager.fillReport( getResource( JasperExporter.class, "/reports/invoice.jasper"), paramMap, dataSource );
			
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, true);
            jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jasperViewer.setTitle("Preview window");
            jasperViewer.setIconImage( null );
            jasperViewer.setVisible(true);
            
			/*if( new File("C:\\").exists() ) {
				String tempdir = System.getProperty( "java.io.tmpdir" );
				JasperExportManager.exportReportToPdfFile(jasperPrint, tempdir + "\\export.pdf");
				//JasperPrintManager.printReport(jasperPrint, false);
				
				//Runtime.getRuntime().exec( "cmd /C start " + tempdir + "export.pdf" );
			} else {
				JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/goofy/Asztal/export.pdf");
			}*/
            
            String home = System.getProperty("user.home");
			JasperExportManager.exportReportToPdfFile( jasperPrint, home + "/invoice.pdf" );
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static InputStream getResource(Class<?> clazz, String path) {
		return clazz.getResourceAsStream(path);
	}
}
