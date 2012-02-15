package com.gw_systems.invoicer.frames.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class InvoiceTableModel implements TableModel {

	private List<TableModelListener> listener = new ArrayList<TableModelListener>();
	private int maxRows = 0;
	
	public void addTableModelListener(TableModelListener l) {
		listener.add( l );
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0: return "Hellobello!";
		default: return "Unknown";
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
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