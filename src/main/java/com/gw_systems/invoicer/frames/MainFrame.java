package com.gw_systems.invoicer.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.gw_systems.invoicer.DatabaseConnector;
import com.gw_systems.invoicer.ImagePanel;
import java.awt.Color;
import java.awt.Toolkit;

public class MainFrame {

	private JFrame frmGwinvoicerHomeEdition;
	private JLabel lblStatusindicator;
	private JProgressBar progressBar;

	public static Session session;
	public static Configuration config;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated( true );
					UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
					
					MainFrame window = new MainFrame();
					window.frmGwinvoicerHomeEdition.setVisible(true);
					
					new DatabaseConnector( window ).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGwinvoicerHomeEdition = new JFrame();
		frmGwinvoicerHomeEdition.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/icons/invoice_bigicon.png")));
		frmGwinvoicerHomeEdition.setBackground(Color.WHITE);
		frmGwinvoicerHomeEdition.setTitle("GW-Invoicer Home Edition");
		frmGwinvoicerHomeEdition.setBounds(100, 100, 462, 317);
		frmGwinvoicerHomeEdition.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		progressBar = new JProgressBar();
		frmGwinvoicerHomeEdition.getContentPane().add(progressBar, BorderLayout.SOUTH);
		
		ImagePanel panel = new ImagePanel( new ImageIcon( getClass().getResource("/icons/bg.jpg") ).getImage() );
		frmGwinvoicerHomeEdition.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblStatusindicator = new JLabel(" StatusIndicator");
		panel.add(lblStatusindicator, BorderLayout.SOUTH);
	}

	public synchronized JLabel getLblStatusindicator() {
		return lblStatusindicator;
	}
	public synchronized JProgressBar getProgressBar() {
		return progressBar;
	}
}
