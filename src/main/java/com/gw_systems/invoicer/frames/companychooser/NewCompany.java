package com.gw_systems.invoicer.frames.companychooser;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
import com.gw_systems.invoicer.frames.CompanyChooserDialog;

public class NewCompany extends JDialog {

	private static final long serialVersionUID = 247938160436137174L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCegnev;
	private JPasswordField pwdPass;
	private JPasswordField pwdPass_1;
	private JTextField txtPostalcode;
	private JTextField txtCity;
	private JTextField txtAddress;
	private JTextField txtTaxnr;
	private JTextField txtTel;
	private JTextField txtFax;
	private JTextField txtEmail;
	private JTextField txtWebpage;
	private final NewCompany parent = this;

	public NewCompany getParentDialog() {
		return parent;
	}

	public NewCompany(final JFrame parent) {
		super(parent);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewCompany.class.getResource("/icons/folder_add.png")));
		setTitle("Új cég felvitele");
		setBounds(100, 100, 450, 432);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCgNeve = new JLabel("Cég neve:");
			GridBagConstraints gbc_lblCgNeve = new GridBagConstraints();
			gbc_lblCgNeve.insets = new Insets(0, 0, 5, 5);
			gbc_lblCgNeve.anchor = GridBagConstraints.EAST;
			gbc_lblCgNeve.gridx = 0;
			gbc_lblCgNeve.gridy = 0;
			contentPanel.add(lblCgNeve, gbc_lblCgNeve);
		}
		{
			txtCegnev = new JTextField();
			GridBagConstraints gbc_txtCegnev = new GridBagConstraints();
			gbc_txtCegnev.insets = new Insets(0, 0, 5, 0);
			gbc_txtCegnev.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCegnev.gridx = 1;
			gbc_txtCegnev.gridy = 0;
			contentPanel.add(txtCegnev, gbc_txtCegnev);
			txtCegnev.setColumns(10);
		}
		{
			JLabel lblIrnytszm = new JLabel("Irányítószám:");
			GridBagConstraints gbc_lblIrnytszm = new GridBagConstraints();
			gbc_lblIrnytszm.anchor = GridBagConstraints.EAST;
			gbc_lblIrnytszm.insets = new Insets(0, 0, 5, 5);
			gbc_lblIrnytszm.gridx = 0;
			gbc_lblIrnytszm.gridy = 1;
			contentPanel.add(lblIrnytszm, gbc_lblIrnytszm);
		}
		{
			txtPostalcode = new JTextField();
			GridBagConstraints gbc_txtPostalcode = new GridBagConstraints();
			gbc_txtPostalcode.insets = new Insets(0, 0, 5, 0);
			gbc_txtPostalcode.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPostalcode.gridx = 1;
			gbc_txtPostalcode.gridy = 1;
			contentPanel.add(txtPostalcode, gbc_txtPostalcode);
			txtPostalcode.setColumns(10);
		}
		{
			JLabel lblTeleplsvros = new JLabel("Település/Város:");
			GridBagConstraints gbc_lblTeleplsvros = new GridBagConstraints();
			gbc_lblTeleplsvros.anchor = GridBagConstraints.EAST;
			gbc_lblTeleplsvros.insets = new Insets(0, 0, 5, 5);
			gbc_lblTeleplsvros.gridx = 0;
			gbc_lblTeleplsvros.gridy = 2;
			contentPanel.add(lblTeleplsvros, gbc_lblTeleplsvros);
		}
		{
			txtCity = new JTextField();
			GridBagConstraints gbc_txtCity = new GridBagConstraints();
			gbc_txtCity.insets = new Insets(0, 0, 5, 0);
			gbc_txtCity.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCity.gridx = 1;
			gbc_txtCity.gridy = 2;
			contentPanel.add(txtCity, gbc_txtCity);
			txtCity.setColumns(10);
		}
		{
			JLabel lblUtcaHzszmemelet = new JLabel("Utca, házszám (emelet, ajtó):");
			GridBagConstraints gbc_lblUtcaHzszmemelet = new GridBagConstraints();
			gbc_lblUtcaHzszmemelet.anchor = GridBagConstraints.EAST;
			gbc_lblUtcaHzszmemelet.insets = new Insets(0, 0, 5, 5);
			gbc_lblUtcaHzszmemelet.gridx = 0;
			gbc_lblUtcaHzszmemelet.gridy = 3;
			contentPanel.add(lblUtcaHzszmemelet, gbc_lblUtcaHzszmemelet);
		}
		{
			txtAddress = new JTextField();
			GridBagConstraints gbc_txtAddress = new GridBagConstraints();
			gbc_txtAddress.insets = new Insets(0, 0, 5, 0);
			gbc_txtAddress.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtAddress.gridx = 1;
			gbc_txtAddress.gridy = 3;
			contentPanel.add(txtAddress, gbc_txtAddress);
			txtAddress.setColumns(10);
		}
		{
			JLabel lblAdszm = new JLabel("Adószám:");
			GridBagConstraints gbc_lblAdszm = new GridBagConstraints();
			gbc_lblAdszm.anchor = GridBagConstraints.EAST;
			gbc_lblAdszm.insets = new Insets(0, 0, 5, 5);
			gbc_lblAdszm.gridx = 0;
			gbc_lblAdszm.gridy = 4;
			contentPanel.add(lblAdszm, gbc_lblAdszm);
		}
		{
			txtTaxnr = new JTextField();
			GridBagConstraints gbc_txtTaxnr = new GridBagConstraints();
			gbc_txtTaxnr.insets = new Insets(0, 0, 5, 0);
			gbc_txtTaxnr.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTaxnr.gridx = 1;
			gbc_txtTaxnr.gridy = 4;
			contentPanel.add(txtTaxnr, gbc_txtTaxnr);
			txtTaxnr.setColumns(10);
		}
		{
			JLabel lblTelefonszm = new JLabel("Telefonszám:");
			GridBagConstraints gbc_lblTelefonszm = new GridBagConstraints();
			gbc_lblTelefonszm.anchor = GridBagConstraints.EAST;
			gbc_lblTelefonszm.insets = new Insets(0, 0, 5, 5);
			gbc_lblTelefonszm.gridx = 0;
			gbc_lblTelefonszm.gridy = 5;
			contentPanel.add(lblTelefonszm, gbc_lblTelefonszm);
		}
		{
			txtTel = new JTextField();
			GridBagConstraints gbc_txtTel = new GridBagConstraints();
			gbc_txtTel.insets = new Insets(0, 0, 5, 0);
			gbc_txtTel.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTel.gridx = 1;
			gbc_txtTel.gridy = 5;
			contentPanel.add(txtTel, gbc_txtTel);
			txtTel.setColumns(10);
		}
		{
			JLabel lblFax = new JLabel("Fax:");
			GridBagConstraints gbc_lblFax = new GridBagConstraints();
			gbc_lblFax.anchor = GridBagConstraints.EAST;
			gbc_lblFax.insets = new Insets(0, 0, 5, 5);
			gbc_lblFax.gridx = 0;
			gbc_lblFax.gridy = 6;
			contentPanel.add(lblFax, gbc_lblFax);
		}
		{
			txtFax = new JTextField();
			GridBagConstraints gbc_txtFax = new GridBagConstraints();
			gbc_txtFax.insets = new Insets(0, 0, 5, 0);
			gbc_txtFax.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFax.gridx = 1;
			gbc_txtFax.gridy = 6;
			contentPanel.add(txtFax, gbc_txtFax);
			txtFax.setColumns(10);
		}
		{
			JLabel lblEmailCm = new JLabel("E-Mail cím:");
			GridBagConstraints gbc_lblEmailCm = new GridBagConstraints();
			gbc_lblEmailCm.anchor = GridBagConstraints.EAST;
			gbc_lblEmailCm.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmailCm.gridx = 0;
			gbc_lblEmailCm.gridy = 7;
			contentPanel.add(lblEmailCm, gbc_lblEmailCm);
		}
		{
			txtEmail = new JTextField();
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
			gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEmail.gridx = 1;
			gbc_txtEmail.gridy = 7;
			contentPanel.add(txtEmail, gbc_txtEmail);
			txtEmail.setColumns(10);
		}
		{
			JLabel lblWeboldal = new JLabel("Weboldal:");
			GridBagConstraints gbc_lblWeboldal = new GridBagConstraints();
			gbc_lblWeboldal.anchor = GridBagConstraints.EAST;
			gbc_lblWeboldal.insets = new Insets(0, 0, 5, 5);
			gbc_lblWeboldal.gridx = 0;
			gbc_lblWeboldal.gridy = 8;
			contentPanel.add(lblWeboldal, gbc_lblWeboldal);
		}
		{
			txtWebpage = new JTextField();
			GridBagConstraints gbc_txtWebpage = new GridBagConstraints();
			gbc_txtWebpage.insets = new Insets(0, 0, 5, 0);
			gbc_txtWebpage.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWebpage.gridx = 1;
			gbc_txtWebpage.gridy = 8;
			contentPanel.add(txtWebpage, gbc_txtWebpage);
			txtWebpage.setColumns(10);
		}
		{
			JLabel lblAdminisztrtoriJelsz = new JLabel("Adminisztrátori jelszó:");
			GridBagConstraints gbc_lblAdminisztrtoriJelsz = new GridBagConstraints();
			gbc_lblAdminisztrtoriJelsz.anchor = GridBagConstraints.EAST;
			gbc_lblAdminisztrtoriJelsz.insets = new Insets(0, 0, 5, 5);
			gbc_lblAdminisztrtoriJelsz.gridx = 0;
			gbc_lblAdminisztrtoriJelsz.gridy = 9;
			contentPanel.add(lblAdminisztrtoriJelsz, gbc_lblAdminisztrtoriJelsz);
		}
		{
			pwdPass = new JPasswordField();
			GridBagConstraints gbc_pwdPass = new GridBagConstraints();
			gbc_pwdPass.insets = new Insets(0, 0, 5, 0);
			gbc_pwdPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_pwdPass.gridx = 1;
			gbc_pwdPass.gridy = 9;
			contentPanel.add(pwdPass, gbc_pwdPass);
		}
		{
			JLabel lblAdminisztrtoriJelszMegerstse = new JLabel("Adminisztrátori jelszó megerősítése:");
			GridBagConstraints gbc_lblAdminisztrtoriJelszMegerstse = new GridBagConstraints();
			gbc_lblAdminisztrtoriJelszMegerstse.anchor = GridBagConstraints.EAST;
			gbc_lblAdminisztrtoriJelszMegerstse.insets = new Insets(0, 0, 0, 5);
			gbc_lblAdminisztrtoriJelszMegerstse.gridx = 0;
			gbc_lblAdminisztrtoriJelszMegerstse.gridy = 10;
			contentPanel.add(lblAdminisztrtoriJelszMegerstse, gbc_lblAdminisztrtoriJelszMegerstse);
		}
		{
			pwdPass_1 = new JPasswordField();
			GridBagConstraints gbc_pwdPass_1 = new GridBagConstraints();
			gbc_pwdPass_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_pwdPass_1.gridx = 1;
			gbc_pwdPass_1.gridy = 10;
			contentPanel.add(pwdPass_1, gbc_pwdPass_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Mentés");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Company company = new Company();
						company.setPostalCode( Integer.parseInt( txtPostalcode.getText() ) );
						company.setCity( txtCity.getText() );
						company.setAddress( txtAddress.getText() );
						company.setCompanyName( txtCegnev.getText() );
						company.setTaxNumber( txtTaxnr.getText() );
						company.setAdminPassword( String.valueOf( pwdPass.getPassword() ) );
						
						Session session = StaticTools.config.buildSessionFactory().openSession();
						Transaction tx = session.beginTransaction();
						
						try {
							session.save( company );
							tx.commit();
							
							JOptionPane.showMessageDialog( getParentDialog(), "A Cég sikeresen felvéve az adatbázisba!" );
						} catch (Exception e2) {
							tx.rollback();
						} finally {
							session.close();
						}
							
						((CompanyChooserDialog)parent).refreshCompanyList();
						setVisible(false);
						dispose();
					}
				});
				okButton.setIcon(new ImageIcon(NewCompany.class.getResource("/icons/add.png")));
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
				cancelButton.setIcon(new ImageIcon(NewCompany.class.getResource("/icons/delete.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
