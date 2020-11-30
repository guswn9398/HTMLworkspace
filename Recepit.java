package com.cocktailmall.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Recepit extends JFrame {

	JPanel p_center;
	JPanel p_south;
	JButton bt_back;

	public Recepit() {

		p_center = new JPanel(); // 주문서 받을 테이블 패널
		p_south = new JPanel();// 버튼담을패널
		bt_back = new JButton("메인으로");

		p_center.setBackground(Color.BLACK);

		p_south.add(bt_back);
		add(p_center);
		add(p_south, BorderLayout.SOUTH);

		setSize(700, 900);
		setVisible(true);
		setLocationRelativeTo(null);

		// 메인으로 이벤트 구현
		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AdminMain();
			}
		});
	}

}
