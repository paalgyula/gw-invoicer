package com.gw_systems.invoicer.frames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.RichTooltip;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;

import com.gw_systems.invoicer.StaticTools;
import com.gw_systems.invoicer.beans.Company;
import com.gw_systems.invoicer.frames.components.InvoiceTableModel;
import com.gw_systems.invoicer.frames.ribbon.RibbonTools;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

public class CompanyView extends JRibbonFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel taskbarCompanylabel;
	private JCommandButton helpButton;
	private JTable table;
	
	public void createTaskbar() {
		taskbarCompanylabel = new JLabel( "GW-Systems Kft." );
		taskbarCompanylabel.setFont(new Font("Liberation Sans", Font.BOLD, 13));
		helpButton = new JCommandButton( "", RibbonTools.getIcon16( "/icons/information.png" ) );
		helpButton.setActionRichTooltip( new RichTooltip("Segítség","A gomb megnyomásával a \"Cég Számláinak Kezelése\" ablak részletes dokumentációja nyílik meg. A dokumentáció megtekintéséhez internet kapcsolat szükséges!") );
		
		getRibbon().addTaskbarComponent( helpButton );
		getRibbon().addTaskbarComponent( new JSeparator( JSeparator.VERTICAL ) );
		getRibbon().addTaskbarComponent( taskbarCompanylabel );
	}
	
	public JRibbonBand createSzamlaRibbonBand() {
		JRibbonBand jrb = new JRibbonBand( "Alapműveletek", null );
		
		JCommandButton addInvoice = new JCommandButton( "Számla felvétele", RibbonTools.getIcon32( "/icons/invoice32.png" ) );
		JCommandButton stornoInvoice = new JCommandButton( "Számla sztornó", RibbonTools.getIcon32( "/icons/storno32.png" ) );
		JCommandButton editInvoice = new JCommandButton( "Számla szerkesztés", RibbonTools.getIcon32( "/icons/edit32.png" ) );
		
		
		
		jrb.addCommandButton( addInvoice, RibbonElementPriority.TOP );
		jrb.addCommandButton( stornoInvoice, RibbonElementPriority.TOP );
		jrb.addCommandButton( editInvoice, RibbonElementPriority.TOP );
		
		List<RibbonBandResizePolicy> policies = new ArrayList<RibbonBandResizePolicy>();
		policies.add( new CoreRibbonResizePolicies.None( jrb.getControlPanel() ) );
		
		jrb.setResizePolicies( policies );
		return jrb;
	}
	
	public CompanyView( final Company company ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createTaskbar();
		setTitle( "Cég számláinak kezelése" );
		setSize( 677, 426 );
		
		StaticTools.centerFrame( this );
		getRibbon().setApplicationMenu( new RibbonApplicationMenu() );
		getRibbon().addTask( new RibbonTask( "Számla műveletek", createSzamlaRibbonBand() ) );
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		
		table.setModel( new InvoiceTableModel() );
		table.setFont(UIManager.getFont("Label.font"));
		
		scrollPane.setViewportView(table);
		getContentPane().add( taskbarCompanylabel, BorderLayout.SOUTH );	
	}
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new CompanyView( null ).setVisible( true );
			}
		});
	}
}
