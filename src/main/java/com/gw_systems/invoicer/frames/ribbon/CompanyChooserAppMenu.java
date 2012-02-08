package com.gw_systems.invoicer.frames.ribbon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;

public class CompanyChooserAppMenu extends RibbonApplicationMenu {
	public CompanyChooserAppMenu() {
		super();
		
		RibbonApplicationMenuEntryPrimary btnMuveletek = new RibbonApplicationMenuEntryPrimary(null, "Cég műveletek", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		}, CommandButtonKind.POPUP_ONLY );
		addMenuEntry( btnMuveletek );
		addMenuSeparator();
	}
}
