package com.jcity;

import java.awt.*;

import org.apache.commons.logging.*;

import com.jcity.gui.*;

@SuppressWarnings("serial")
public class JCityBaseTest extends JCityPanel {

	private Log logger = LogFactory.getLog(this.getClass());

	public static void main(String... args) throws Exception {
		new JCityBaseTest();
	}

	protected JCityBaseTest() throws Exception {
		setupGui(1024, 768);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// draw some shit.
	}

}
