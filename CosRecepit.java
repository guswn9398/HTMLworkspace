package com.cocktailmall.cos;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CosRecepit extends JPanel {
	JButton bt_center;

	public CosRecepit(CosMain cosMainApp) {

		bt_center = new JButton();

		JOptionPane.showMessageDialog(null, "주문이 완료되었습니다");
	}
}
