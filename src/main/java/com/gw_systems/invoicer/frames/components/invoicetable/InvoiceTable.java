package com.gw_systems.invoicer.frames.components.invoicetable;

import java.awt.Component;
import java.awt.Font;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class InvoiceTable extends JTable {
	private static final long serialVersionUID = 6350536165003335648L;

	public InvoiceTable() {
		setAutoCreateRowSorter( true );
		super.setModel( new InvoiceTableModel() );
	}
	
	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {
		switch ( column ) {
		case 0: return getImagePropertiesCellRenderer();
		case 1: return getEntryCellRenderer();
		default: return getDefaultCellRenderer();
		}
	}
	
	public void fillColor(JTable t,JLabel l,boolean isSelected ){
        if(isSelected){
            l.setBackground(t.getSelectionBackground());
            l.setForeground(t.getSelectionForeground());
        } else {
       		l.setBackground(t.getBackground());       
        	l.setForeground(t.getForeground());
        }
    }
	
	//////////////////////////////////////// Cell renderes ////////////////////////////////////////////////////////
	
	private TableCellRenderer getEntryCellRenderer() {
		return new TableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				String val = value != null ? value.toString() : "";
				JLabel label = new JLabel( val );
				label.setHorizontalAlignment( JLabel.RIGHT );
				Font newLabelFont = new Font( label.getFont().getName(), Font.BOLD, label.getFont().getSize() - 2 );
				label.setFont( newLabelFont );
				label.setIconTextGap( 6 );
				label.setOpaque( true );
				fillColor(table, label, isSelected );
				return label;
			}
		};
	}
	
	private TableCellRenderer getImagePropertiesCellRenderer() {
		return new TableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				InvoiceImageProperties panel = new InvoiceImageProperties( 
					new Random().nextBoolean(), 
					new Random().nextBoolean(), 
					new Random().nextBoolean(),
					new Random().nextBoolean()
				);
				
				if ( isSelected )
					panel.setBackground( table.getSelectionBackground() );
				else
					panel.setBackground( table.getBackground() );
				
				return panel;
			}
		};
	}
	
	private TableCellRenderer getDefaultCellRenderer() {
		return new TableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				String val = value != null ? value.toString() : "";
				JLabel label = new JLabel( val );
				label.setOpaque( true );
				fillColor( table, label, isSelected );
				return label;
			}
		};
	}
}
