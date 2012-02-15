package com.gw_systems.invoicer.frames.companychooser;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gw_systems.invoicer.StaticTools;
import com.gw_systems.invoicer.beans.Company;
import com.gw_systems.invoicer.beans.Customer;
import com.gw_systems.invoicer.frames.CompanyChooserDialog;
import com.gw_systems.invoicer.frames.CompanyView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JDialog {

	private static final long serialVersionUID = -186498236958623L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField pwdPass;
	final public CompanyChooserDialog parent;

	public CompanyChooserDialog getParentWindow() {
		return parent;
	}
	
	public LoginWindow(CompanyChooserDialog wparent) {
		this.parent = wparent;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Bejelentkezés");
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/icons/invoice_bigicon.png")));
		setBounds(100, 100, 384, 142);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		
		StaticTools.centerDialog( this );
		
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblFelhasznlnv = new JLabel("Felhasználónév:");
			GridBagConstraints gbc_lblFelhasznlnv = new GridBagConstraints();
			gbc_lblFelhasznlnv.insets = new Insets(0, 0, 5, 5);
			gbc_lblFelhasznlnv.gridx = 0;
			gbc_lblFelhasznlnv.gridy = 0;
			contentPanel.add(lblFelhasznlnv, gbc_lblFelhasznlnv);
		}
		{
			txtUsername = new JTextField();
			txtUsername.setText("admin");
			GridBagConstraints gbc_txtUsername = new GridBagConstraints();
			gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
			gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUsername.gridx = 1;
			gbc_txtUsername.gridy = 0;
			contentPanel.add(txtUsername, gbc_txtUsername);
			txtUsername.setColumns(10);
		}
		{
			JLabel lblJelsz = new JLabel("Jelszó:");
			GridBagConstraints gbc_lblJelsz = new GridBagConstraints();
			gbc_lblJelsz.anchor = GridBagConstraints.EAST;
			gbc_lblJelsz.insets = new Insets(0, 0, 0, 5);
			gbc_lblJelsz.gridx = 0;
			gbc_lblJelsz.gridy = 1;
			contentPanel.add(lblJelsz, gbc_lblJelsz);
		}
		{
			pwdPass = new JPasswordField();
			GridBagConstraints gbc_pwdPass = new GridBagConstraints();
			gbc_pwdPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_pwdPass.gridx = 1;
			gbc_pwdPass.gridy = 1;
			contentPanel.add(pwdPass, gbc_pwdPass);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Bejelentkezés");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String wrongpass = "A megadott felhasználónév/jelszó páros érvénytelen!";
						Company comp = (Company)parent.getCompanyList().getSelectedValue();
						
						if ( txtUsername.getText().toLowerCase().equals( "admin" ) ) {
							if ( !comp.getAdminPassword().equals( String.valueOf( pwdPass.getPassword() ) ) ) {
								JOptionPane.showMessageDialog( null, wrongpass, "Hiba", JOptionPane.ERROR_MESSAGE );
								return;
							}
							
							setVisible(false);
							dispose();
							getParentWindow().setVisible( false );
							new CompanyView( comp ).setVisible( true );
						} else {
							Session session = StaticTools.createSession();
							Transaction tx = session.beginTransaction();
							
							Customer customer = (Customer)session.createQuery( "select cust from Customer cust where cust.company.companyName = :COMP and cust.password = :PASSWORD and cust.name = :NAME" )
									.setString( "COMP", comp.getCompanyName() )
									.setString( "NAME", txtUsername.getText() )
									.setString( "PASSWORD", String.valueOf( pwdPass.getPassword() ) ).uniqueResult();
							if ( customer == null ) {
								JOptionPane.showMessageDialog( null, wrongpass, "Hiba", JOptionPane.ERROR_MESSAGE );
								return;
							}
							
							tx.rollback();
							session.close();
							
							setVisible(false);
							dispose();
							getParentWindow().setVisible( false );
							new CompanyView( comp ).setVisible( true );
						}
					}
				});
				okButton.setIcon(new ImageIcon(LoginWindow.class.getResource("/icons/accept.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Mégsem");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setIcon(new ImageIcon(LoginWindow.class.getResource("/icons/delete.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
