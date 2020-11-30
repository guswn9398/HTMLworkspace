package com.cocktailmall.cos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CosSub extends JFrame implements ActionListener {
	XCanvas can; // 센터에 크게 나올 이미지를 그릴 켄버스

	ArrayList<CosThumb> list = new ArrayList<CosThumb>();// 썸네일 배열 선언
	String dir = "C:/Users/Im_hyeonju/eclipse-workspace/CocktailMall/res/sample/";
	String[] src = { "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg",
			"11.jpg", "12.jpg", "13.jpg", "14.jpg", "15.jpg" };
	int n = 0;

	JPanel p_container;
	JPanel p_south; // 버튼 4개를 얹힐패널
	JButton bt_prev, bt_next, bt_cart, bt_back; // 이전, 다음, 장바구니, 메인으로 버튼

	public CosSub() {
		// 생성
		p_container = new JPanel();
		p_south = new JPanel();
		bt_prev = new JButton("<-");
		bt_next = new JButton("->");
		bt_cart = new JButton("장바구니");
		bt_back = new JButton("메인으로");

		can = new XCanvas(dir + src[n]);

		p_south.add(bt_prev);// 패널에 이전 버튼 넣기
		p_south.add(bt_next);// 패널에 다음 버튼 넣기
		p_south.add(bt_cart);// 패널에 주문 버튼 넣기
		p_south.add(bt_back);// 패널에 메인 버튼 넣기
		p_container.add(can);// 가운데 패널에 이미지캔버스 부착

		add(p_container, BorderLayout.WEST);
		add(p_south, BorderLayout.SOUTH);// 남쪽에 버튼패널 넣기

		p_container.setPreferredSize(new Dimension(600, 800));
		bt_prev.addActionListener(this);// 이전 버튼과 리스너 연결
		bt_next.addActionListener(this);// 다음 버튼과 리스너 연결
		bt_cart.addActionListener(this);// 주문 버튼과 리스너 연결
		bt_back.addActionListener(this);// 메인 버튼과 리스너 연결

		setSize(700, 900);
		setVisible(true);
		setLocationRelativeTo(null);

		updateData();

		// 장바구니에 이벤트 구현
		bt_cart.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, "장바구니에 담겼습니다");
		});

		// 메인으로 이벤트 구현
		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CosMain();
			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_prev) {
			prev();
		} else if (obj == bt_next) {
			next();
		}
		updateData();
	}

	public void updateData() {
		can.setSrc(dir + src[n]);
		can.repaint();
	}

	public void prev() {
		if (n >= 1) {
			n--;
		} else {
			JOptionPane.showMessageDialog(this, "첫번째 입니다.");
		}
	}

	public void next() {
		if (n < src.length - 1) {
			n++;
		} else {
			JOptionPane.showMessageDialog(this, "마지막 입니다.");
		}

	}
}
