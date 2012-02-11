package com.gw_systems.invoicer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.gw_systems.invoicer.frames.SplashScreen;

public class StaticTools extends Thread {
	private SplashScreen mf;
	
	public static Session session;
	public static Configuration config;
	
	public static Font segoeUIFont;
	public static Font sansRegularFont;
	public static Font sansBoldFont;
	public static Font sansItalicFont;
	public static Font sansBoldItalicFont;
	
	public static void centerFrame(JFrame frame) {
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenSize = tk.getScreenSize();
	    int screenHeight = screenSize.height;
	    int screenWidth = screenSize.width;
	    int windowHeight = frame.getHeight();
	    int windowWidth = frame.getWidth();
	    frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
	}
	
	public static void centerDialog(JDialog frame) {
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenSize = tk.getScreenSize();
	    int screenHeight = screenSize.height;
	    int screenWidth = screenSize.width;
	    int windowHeight = frame.getHeight();
	    int windowWidth = frame.getWidth();
	    frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
	}
	
	public static Session createSession() {
		return config.buildSessionFactory().openSession();
	}
	
	public StaticTools(SplashScreen mf) {
		this.mf = mf;
	}
	
	public static void loadFonts() {
		try {
			segoeUIFont = Font.createFont(Font.TRUETYPE_FONT, StaticTools.class.getResourceAsStream( "/fonts/segoeui.ttf" ) );
			sansRegularFont = Font.createFont( Font.TRUETYPE_FONT, StaticTools.class.getResourceAsStream( "/LiberationSans-Regular.ttf" ) );
			sansBoldFont = Font.createFont( Font.TRUETYPE_FONT, StaticTools.class.getResourceAsStream( "/LiberationSans-Bold.ttf" ) );
			sansItalicFont = Font.createFont( Font.TRUETYPE_FONT, StaticTools.class.getResourceAsStream( "/LiberationSans-Italic.ttf" ) );
			sansBoldItalicFont = Font.createFont( Font.TRUETYPE_FONT, StaticTools.class.getResourceAsStream( "/LiberationSans-BoldItalic.ttf" ) );
			
			//UIManager.put("InternalFrame.titleFont", new Font( sansBoldFont.getFontName(), Font.PLAIN, 12 ) );
			
			UIManager.put("Button.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("ToggleButton.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("RadioButton.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("CheckBox.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("ColorChooser.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("ComboBox.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("Label.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("List.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("MenuBar.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("MenuItem.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("RadioButtonMenuItem.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("CheckBoxMenuItem.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("Menu.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("PopupMenu.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("OptionPane.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("Panel.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("ProgressBar.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("ScrollPane.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("Viewport.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("TabbedPane.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("Table.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("TableHeader.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("TextField.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("PasswordField.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("TextArea.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("TextPane.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("EditorPane.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("TitledBorder.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("ToolBar.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("ToolTip.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			UIManager.put("Tree.font", new Font( sansRegularFont.getFontName(), Font.PLAIN, 12 ));
			
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.mf.getLblStatusindicator().setText( "Connecting to the database..." );
		this.mf.getProgressBar().setValue( 30 );
		
		config = new AnnotationConfiguration().configure();
		this.mf.getLblStatusindicator().setText( "Opening database session..." );
		this.mf.getProgressBar().setValue( 50 );
		session = config.buildSessionFactory().openSession();
		
		this.mf.getLblStatusindicator().setText( "Validating settings..." );
		this.mf.getProgressBar().setValue( 70 );
		
		Transaction tx = session.beginTransaction();
		tx.commit();
		
		this.mf.getLblStatusindicator().setText( "Database check completed. Closing session..." );
		this.mf.getProgressBar().setValue( 80 );
		
		session.close();
		
		this.mf.getLblStatusindicator().setText( "Done..." );
		this.mf.getProgressBar().setValue( 100 );
		
		try {
			Thread.sleep( 2000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.mf.showLoginDialog();
	}
}
