package com.gw_systems.invoicer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import com.gw_systems.invoicer.datasource.InvoiceItem;
import com.gw_systems.invoicer.datasource.JRDataSourceImpl;

public class JasperExporter {

	public JasperExporter() {
		
		
		try {
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
			
			JasperPrint jasperPrint;
			/* Ha forditani akarjuk a reportot minden exportkor, akkor ezt erdemes atallitani... */
			boolean recompile = false;
			
			if ( recompile ) {
				JasperReport jasperReport = JasperCompileManager.compileReport( getResource( JasperExporter.class, "/reports/invoice.jrxml") );
				jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, dataSource );
			} else {
				jasperPrint = JasperFillManager.fillReport( getResource( JasperExporter.class, "/reports/invoice.jasper"), paramMap, dataSource );
			}
				
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, true);
            jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jasperViewer.setTitle("Preview window");
            jasperViewer.setIconImage( null );
            jasperViewer.setVisible(true);
            
            //String home = System.getProperty("user.home");
			//JasperExportManager.exportReportToPdfFile( jasperPrint, home + "/invoice.pdf" );
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static InputStream getResource(Class<?> clazz, String path) {
		return clazz.getResourceAsStream(path);
	}
}
