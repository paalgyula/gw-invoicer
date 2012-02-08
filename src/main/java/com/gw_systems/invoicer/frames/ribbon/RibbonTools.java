package com.gw_systems.invoicer.frames.ribbon;

import java.awt.Dimension;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

public class RibbonTools {

	public static ResizableIcon getIcon32(String resourcePath) {
		return ImageWrapperResizableIcon.getIcon( RibbonTools.class.getResource( resourcePath ), new Dimension( 32, 32 ) );
	}
	
	public static ResizableIcon getIcon16(String resourcePath) {
		return ImageWrapperResizableIcon.getIcon( RibbonTools.class.getResource( resourcePath ), new Dimension( 16, 16 ) );
	}
}
