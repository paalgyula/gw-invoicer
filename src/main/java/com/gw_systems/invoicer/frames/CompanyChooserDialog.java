package com.gw_systems.invoicer.frames;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;
import com.gw_systems.invoicer.StaticTools;
import com.gw_systems.invoicer.JasperExporter;
import com.gw_systems.invoicer.beans.Company;
import com.gw_systems.invoicer.frames.companychooser.LoginWindow;
import com.gw_systems.invoicer.frames.companychooser.NewCompany;
import com.gw_systems.invoicer.frames.ribbon.CompanyChooserAppMenu;
import static com.gw_systems.invoicer.frames.ribbon.RibbonTools.*;
import javax.swing.JToolBar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CompanyChooserDialog extends JRibbonFrame {

	private static final long serialVersionUID = -6669370700455899884L;
	private JPanel contentPane;
	private DefaultListModel listModel;
	private final Action action = new SwingAction();

	public CompanyChooserDialog() {
		final CompanyChooserDialog parent = this;

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				CompanyChooserDialog.class
						.getResource("/icons/invoice_bigicon.png")));
		setTitle("Cég választás");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 511, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane);

		JRibbonBand bandHome = new JRibbonBand("Cégműveletek", null);

		JCommandButton btnSelectCompany = new JCommandButton(
				"Cég kiválasztása", getIcon32("/icons/tick32.png"));

		JCommandButton btnNewCompany = new JCommandButton("Cég felvétele",
				getIcon16("/icons/folder_add.png"));
		JCommandButton btnEditCompany = new JCommandButton("Cég módosítása",
				getIcon16("/icons/folder_edit.png"));
		JCommandButton btnDelCompany = new JCommandButton("Cég Törlése",
				getIcon16("/icons/folder_delete.png"));

		btnSelectCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoginWindow().setVisible(true);
			}
		});

		btnNewCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NewCompany(parent).setVisible(true);
			}
		});

		btnEditCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JasperExporter();
			}
		});

		bandHome.addCommandButton(btnSelectCompany, RibbonElementPriority.TOP);
		bandHome.addCommandButton(btnNewCompany, RibbonElementPriority.MEDIUM);
		bandHome.addCommandButton(btnEditCompany, RibbonElementPriority.MEDIUM);
		bandHome.addCommandButton(btnDelCompany, RibbonElementPriority.MEDIUM);

		List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
		resizePolicies.add(new CoreRibbonResizePolicies.Mirror(bandHome.getControlPanel()));
		resizePolicies.add(new CoreRibbonResizePolicies.Mid2Low(bandHome.getControlPanel()));
		
		bandHome.setResizePolicies(resizePolicies);

		JRibbonBand bandEnterprise = new JRibbonBand( "Enterprise funkciók", null );
		
		JCommandButton btnOfflineWork = new JCommandButton( "Offline mód", getIcon32("/icons/offline32.png") );
		JCommandButton btnManualSync = new JCommandButton( "Szinkronizálás", getIcon32("/icons/sync32.png") );
		
		bandEnterprise.addCommandButton( btnOfflineWork, RibbonElementPriority.TOP );
		bandEnterprise.addCommandButton( btnManualSync, RibbonElementPriority.TOP );
		
		resizePolicies = new ArrayList<RibbonBandResizePolicy>();
		resizePolicies.add(new CoreRibbonResizePolicies.Mirror(bandEnterprise.getControlPanel()));
		resizePolicies.add(new CoreRibbonResizePolicies.Mid2Low(bandEnterprise.getControlPanel()));
		
		bandEnterprise.setResizePolicies(resizePolicies);
		
		RibbonTask taskMainMenu = new RibbonTask("Főmenü", bandHome, bandEnterprise );

		getRibbon().addTask(taskMainMenu);
		
		getRibbon().setApplicationIcon( getIcon32("/icons/invoice_bigicon.png") );
		getRibbon().setApplicationMenu( new CompanyChooserAppMenu() );
		
		getRibbon().addTaskbarComponent( new JCommandButton("", getIcon16("/icons/folder_add.png") ) );
		getRibbon().addTaskbarComponent( new JCommandButton("", getIcon32("/reports/poweredby.jpg") ) );
		
		contentPane.setLayout(new BorderLayout(0, 0));

		// if(JOptionPane.showConfirmDialog(null, "Biztosan ki akar lépni?",
		// "GW-Invoicer Számlázó - www.gw-systems.com",
		// JOptionPane.OK_CANCEL_OPTION) == 0)
		// System.exit( 0 );

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		listModel = new DefaultListModel();
		refreshCompanyList();

		JList companyList = new JList(listModel);
		scrollPane.setViewportView(companyList);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(toolBar, BorderLayout.SOUTH);
		
		JButton btnSync = new JButton("Sync");
		toolBar.add(btnSync);
	}

	public void refreshCompanyList() {
		Session session = StaticTools.config.buildSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();

		listModel.removeAllElements();

		@SuppressWarnings("unchecked")
		Iterator<Company> itr = session
				.createQuery("from Company c order by c.companyName asc")
				.list().iterator();
		while (itr.hasNext())
			listModel.addElement(itr.next());

		tx.rollback();
		session.close();
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
