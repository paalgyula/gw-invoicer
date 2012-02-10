package com.gw_systems.invoicer.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.gw_systems.invoicer.StaticTools;
import com.gw_systems.invoicer.ImagePanel;
import java.awt.Font;

public class SplashScreen {

	private JFrame frmGwinvoicerHomeEdition;
	private JLabel lblStatusindicator;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		StaticTools.loadFonts();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated( true );
					
					//UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceCreamLookAndFeel");
					UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
					//UIManager.setLookAndFeel( "com.sun.java.swing.plaf.gtk.GTKLookAndFeel" );
					
					SplashScreen window = new SplashScreen();
					window.frmGwinvoicerHomeEdition.setVisible(true);
					
					Thread thr = new StaticTools( window );
					thr.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SplashScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGwinvoicerHomeEdition = new JFrame();
		frmGwinvoicerHomeEdition.setFont(new Font("Liberation Sans", Font.PLAIN, 11));
		frmGwinvoicerHomeEdition.setForeground(Color.WHITE);
		frmGwinvoicerHomeEdition.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		frmGwinvoicerHomeEdition.getContentPane().setBackground(Color.WHITE);
		frmGwinvoicerHomeEdition.setResizable(false);
		
		BorderLayout borderLayout = (BorderLayout) frmGwinvoicerHomeEdition.getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		frmGwinvoicerHomeEdition.setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/icons/invoice_bigicon.png")));
		frmGwinvoicerHomeEdition.setBackground(Color.WHITE);
		frmGwinvoicerHomeEdition.setTitle("GW-Invoicer Home Edition");
		frmGwinvoicerHomeEdition.setBounds(100, 100, 462, 297);
		frmGwinvoicerHomeEdition.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGwinvoicerHomeEdition.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frmGwinvoicerHomeEdition.setBackground(Color.WHITE);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenSize = tk.getScreenSize();
	    int screenHeight = screenSize.height;
	    int screenWidth = screenSize.width;
	    int windowHeight = frmGwinvoicerHomeEdition.getHeight();
	    int windowWidth = frmGwinvoicerHomeEdition.getWidth();
	    frmGwinvoicerHomeEdition.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
		
		progressBar = new JProgressBar();
		frmGwinvoicerHomeEdition.getContentPane().add(progressBar, BorderLayout.SOUTH);
		
		ImagePanel panel = new ImagePanel( new ImageIcon( getClass().getResource("/icons/bg.jpg") ).getImage() );
		frmGwinvoicerHomeEdition.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(3, 3));
		
		lblStatusindicator = new JLabel(" ");
		panel.add(lblStatusindicator, BorderLayout.SOUTH);
	}

	public synchronized JLabel getLblStatusindicator() {
		return lblStatusindicator;
	}
	public synchronized JProgressBar getProgressBar() {
		return progressBar;
	}
	
	public synchronized void showLoginDialog() {
		frmGwinvoicerHomeEdition.setVisible(false);
		frmGwinvoicerHomeEdition.dispose();
		
		new CompanyChooserDialog().setVisible( true );
	}
}
