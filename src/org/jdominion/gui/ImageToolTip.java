package org.jdominion.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.plaf.basic.BasicToolTipUI;

public class ImageToolTip extends JToolTip {

	private static final long serialVersionUID = 1L;

	public ImageToolTip(final BufferedImage image) {
		setUI(new BasicToolTipUI() {

			@Override
			public void paint(Graphics g, JComponent c) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			}

			@Override
			public Dimension getPreferredSize(JComponent c) {
				return new Dimension(image.getWidth(), image.getHeight());
			}

		});
	}

}
