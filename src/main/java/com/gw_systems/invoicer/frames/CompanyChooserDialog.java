package com.gw_systems.invoicer.frames;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

import com.gw_systems.invoicer.frames.companychooser.NewCompany;

public class CompanyChooserDialog extends JDialog {
	
	private static final long serialVersionUID = -6669370700455899884L;
	private JPanel contentPane;

	public CompanyChooserDialog() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CompanyChooserDialog.class.getResource("/icons/invoice_bigicon.png")));
		setTitle("Cég választás");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(3, 3));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JList companyList = new JList();
		scrollPane.setViewportView(companyList);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.EAST);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		JButton btnCgKivlasztsa = new JButton("Cég kiválasztása");
		btnCgKivlasztsa.setIcon(new ImageIcon(CompanyChooserDialog.class.getResource("/icons/tick.png")));
		GridBagConstraints gbc_btnCgKivlasztsa = new GridBagConstraints();
		gbc_btnCgKivlasztsa.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCgKivlasztsa.insets = new Insets(0, 0, 5, 0);
		gbc_btnCgKivlasztsa.gridx = 0;
		gbc_btnCgKivlasztsa.gridy = 0;
		buttonPanel.add(btnCgKivlasztsa, gbc_btnCgKivlasztsa);
		
		JButton btnNewCompany = new JButton("Új cég felvétele");
		
		final CompanyChooserDialog parent = this;
		btnNewCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NewCompany(parent).setVisible( true );
			}
		});
		
		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		buttonPanel.add(label, gbc_label);
		btnNewCompany.setIcon(new ImageIcon(CompanyChooserDialog.class.getResource("/icons/folder_add.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		buttonPanel.add(btnNewCompany, gbc_btnNewButton);
		
		JButton btnCgMdostsa = new JButton("Cég módosítása");
		btnCgMdostsa.setIcon(new ImageIcon(CompanyChooserDialog.class.getResource("/icons/folder_edit.png")));
		GridBagConstraints gbc_btnCgMdostsa = new GridBagConstraints();
		gbc_btnCgMdostsa.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCgMdostsa.insets = new Insets(0, 0, 5, 0);
		gbc_btnCgMdostsa.gridx = 0;
		gbc_btnCgMdostsa.gridy = 3;
		buttonPanel.add(btnCgMdostsa, gbc_btnCgMdostsa);
		
		JButton btnCgTrlse = new JButton("Cég Törlése");
		btnCgTrlse.setIcon(new ImageIcon(CompanyChooserDialog.class.getResource("/icons/folder_delete.png")));
		GridBagConstraints gbc_btnCgTrlse = new GridBagConstraints();
		gbc_btnCgTrlse.insets = new Insets(0, 0, 5, 0);
		gbc_btnCgTrlse.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCgTrlse.gridx = 0;
		gbc_btnCgTrlse.gridy = 4;
		buttonPanel.add(btnCgTrlse, gbc_btnCgTrlse);
		
		JLabel lblPlaceholder = new JLabel("   ");
		GridBagConstraints gbc_lblPlaceholder = new GridBagConstraints();
		gbc_lblPlaceholder.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlaceholder.gridx = 0;
		gbc_lblPlaceholder.gridy = 5;
		buttonPanel.add(lblPlaceholder, gbc_lblPlaceholder);
		
		JButton btnKilps = new JButton("Kilépés");
		btnKilps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Biztosan ki akar lépni?", "GW-Invoicer Számlázó - www.gw-systems.com", JOptionPane.OK_CANCEL_OPTION) == 0)
					System.exit( 0 );
			}
		});
		btnKilps.setIcon(new ImageIcon(CompanyChooserDialog.class.getResource("/icons/door_in.png")));
		GridBagConstraints gbc_btnKilps = new GridBagConstraints();
		gbc_btnKilps.insets = new Insets(0, 0, 5, 0);
		gbc_btnKilps.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnKilps.gridx = 0;
		gbc_btnKilps.gridy = 6;
		buttonPanel.add(btnKilps, gbc_btnKilps);
	}

}
