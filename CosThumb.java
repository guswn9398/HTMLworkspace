package com.cocktailmall.cos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class CosThumb extends JPanel {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;

	Toolkit kit;
	Image img;

	CosSub cosSub;

	public CosThumb(String src, CosSub cosSub) {
		this.cosSub = cosSub; // injection 주입받는다고 한다!!
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(src);
		img = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
}
