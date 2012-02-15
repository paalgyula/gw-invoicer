package com.gw_systems.invoicer.frames.components.invoicetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class InvoiceTableModel implements TableModel {

	private List<TableModelListener> listener = new ArrayList<TableModelListener>();
	private int maxRows = 10;
	
	public void addTableModelListener(TableModelListener l) {
		listener.add( l );
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0: return "Számla tulajdonságok";
		case 1: return "Számla sorszám";
		case 2: return "Esedékesség";
		default: return "Unknown";
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1: return new Random().nextInt(1234) + "/" + new Random().nextInt(1234);
		case 0: return new Random().nextBoolean();
		default: return null;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void removeTableModelListener(TableModelListener l) {
		listener.remove( l );
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if ( maxRows < rowIndex )
			maxRows = rowIndex;
	}	
	
	public int getRowCount() {
		return maxRows;
	}
	
	public int getColumnCount() {
		return 10;
	}

}