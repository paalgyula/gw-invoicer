package com.gw_systems.invoicer.frames.components.invoicetable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;

public class InvoiceImageProperties extends JPanel {
	private static final long serialVersionUID = 1L;


	class PropertyLabel extends JLabel {
		private static final long serialVersionUID = 1L;

		public PropertyLabel() {
			setPreferredSize( new Dimension( 20, 16 ) );
			setText("");
		}
		
		public JLabel addPlaceholder() {
			return new PropertyLabel();
		}
		
		public JLabel addPayed() {
			PropertyLabel pl = new PropertyLabel();
			pl.setHorizontalAlignment( JLabel.CENTER );
			pl.setIcon( new ImageIcon( getClass().getResource( "/icons/coins_add.png" ) ) );
			return pl;
		}
		
		public JLabel addStorno() {
			PropertyLabel pl = new PropertyLabel();
			pl.setHorizontalAlignment( JLabel.CENTER );
			pl.setIcon( new ImageIcon( getClass().getResource( "/icons/cross.png" ) ) );
			return pl;
		}
		
		public JLabel addEdit() {
			PropertyLabel pl = new PropertyLabel();
			pl.setHorizontalAlignment( JLabel.CENTER );
			pl.setIcon( new ImageIcon( getClass().getResource( "/icons/page_edit.png" ) ) );
			return pl;
		}
		
		public JLabel addLate() {
			PropertyLabel pl = new PropertyLabel();
			pl.setHorizontalAlignment( JLabel.CENTER );
			pl.setIcon( new ImageIcon( getClass().getResource( "/icons/clock_red.png" ) ) );
			return pl;
		}
		
		public JLabel addIntime() {
			PropertyLabel pl = new PropertyLabel();
			pl.setHorizontalAlignment( JLabel.CENTER );
			pl.setIcon( new ImageIcon( getClass().getResource( "/icons/clock_play.png" ) ) );
			return pl;
		}
	}
	
	
	public InvoiceImageProperties( boolean payed, boolean storno, boolean editable, boolean late ) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0 ));
		setOpaque( true );
		
		PropertyLabel pl = new PropertyLabel();
		
		if ( payed )
			add( pl.addPayed() );
		else 
			add( pl.addPlaceholder() );
		
		if ( storno )
			add( pl.addStorno() );
		else 
			add( pl.addPlaceholder() );
		
		if ( editable )
			add( pl.addEdit() );
		else 
			add( pl.addPlaceholder() );
		
		if ( late )
			add( pl.addLate() );
		else
			add( pl.addIntime() );
	}

}
