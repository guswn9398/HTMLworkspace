package com.cocktailmall.cos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cocktailmall.db.DBControl;

public class CosMain extends JFrame implements ActionListener {

	// 규격
	public static final int WIDTH = 700;
	public static final int HEIGHT = 900;

	// 페이지들
	public static final int COS_MAIN = 0;
	public static final int COS_SUB = 1;
	public static final int COS_RECEPIT = 2;

	// 페이지 준비시키기
	CosSub cosSub;

	private DBControl dbControl;
	private Connection con;

	Font f1, f2;
	JButton bt_north;
	JPanel p_center;
	TextArea t_area;
	JPanel p_south;
	JButton bt_south;

	public CosMain() {
		super("CockTail Kiosk");

		bt_north = new JButton("메뉴선택");
		p_south = new JPanel();
		bt_south = new JButton("주문하기");

		f1 = new Font("고딕", Font.BOLD, 27);
		f2 = new Font("명조", Font.BOLD, 22);

		// 상단 디자인
		bt_north.setPreferredSize(new Dimension(700, 450));
		bt_north.setFont(f1);

		// 중앙 디자인
		p_center = new JPanel();
		t_area = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		t_area.setText("       상품명          가격         수량         합계 \n\n");
		t_area.setBackground(Color.WHITE);
		t_area.setEditable(false);
		t_area.setFont(f2);
		p_center.add(t_area);

		// 하단 디자인
		p_south.setBackground(new Color(255, 255, 215));
		p_south.add(bt_south);

		bt_north.setLayout(new BorderLayout());
		add(p_south);
		add(bt_north, BorderLayout.NORTH);

		this.add(p_south, BorderLayout.SOUTH);

		setSize(700, 900);
		setVisible(true);
		setLocationRelativeTo(null);

		// 메뉴선택버튼을 누르면
		bt_north.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CosSub();
			}
		});

		// 하단에 주문하기 버튼을 누르면
		bt_south.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, "주문이 완료되었습니다");
		});

		// 윈도우에서 시스템 종료할때
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disConnect();
				System.exit(0);
			}
		});

	}

	// 오라클에 접속끊기
	public void disConnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new CosMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
