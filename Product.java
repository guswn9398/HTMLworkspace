package com.cocktailmall.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Product extends JFrame {

	public static final int WIDTH = 700;
	public static final int HEIGHT = 900;

	Font f1;
	JLabel la_north;
	JButton la_back;
	JButton la_bt;

	JPanel p_center;// 아래 내용을 붙힐 패널
	JTextField t_filename;// 이미지
	JTextField t_product_name;// 상품명
	JTextField t_acl;// 도수
	JTextField t_price;// 가격
	JTextArea t_detail;// 상세설명
	JScrollPane s1;

	JPanel p_south;// 버튼을 붙힐 패널
	JButton bt_regist;
	JButton bt_back;

	Product product;
	Recepit recepit;

	public Product() {

		la_north = new JLabel("Cocktail Kiosk");
		f1 = new Font("고딕", Font.BOLD, 30);
		la_north.setFont(f1);
		la_north.setHorizontalAlignment(JLabel.CENTER);
		la_north.setPreferredSize(new Dimension(500, 100));

		add(la_north, BorderLayout.NORTH);

		p_center = new JPanel();// 아래 내용들 담을 패널
		t_filename = new JTextField();// 이미지
		t_product_name = new JTextField();// 상품명
		t_acl = new JTextField();// 도수
		t_price = new JTextField();// 가격
		t_detail = new JTextArea();// 상세설명
		s1 = new JScrollPane();// 상세설명에 스크롤 부착
		p_south = new JPanel();// 하단 버튼붙힐 패널
		bt_regist = new JButton("등록");
		bt_back = new JButton("메인으로");

		Dimension d = new Dimension(420, 50);

		t_filename.setPreferredSize(d);
		t_product_name.setPreferredSize(d);
		t_acl.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_detail.setPreferredSize(new Dimension(450, 300));

		t_detail.add(s1);

		p_center.add(t_filename);
		p_center.add(t_product_name);
		p_center.add(t_acl);
		p_center.add(t_price);
		p_center.add(t_detail);

		p_south.add(bt_regist);
		p_south.add(bt_back);
		add(p_center);
		add(p_south, BorderLayout.SOUTH);// 버튼 붙힌 패널 메인패널에 부착

		setSize(700, 900);
		setVisible(true);
		setLocationRelativeTo(null);

//		// 등록버튼을 누르면
//		bt_regist.addActionListener((e) -> {
//			JOptionPane.showMessageDialog(null, "등록이 완료되었습니다");
//		});
//		
		bt_regist.addActionListener((e) -> {

			if (regist() == 0) {
				JOptionPane.showMessageDialog(this, "등록에 실패했습니다.");
			} else {
				JOptionPane.showMessageDialog(this, "등록되었습니다");
			}
		});

		// 메인으로 이벤트 구현
		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AdminMain();
			}
		});

	}

	public int regist() {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into cock(filename,product_name,acl,price,detail) values(cock_product.nextval,?,?,?,?,?,?)";

		try {
			pstmt = AdminMain.getAdminMain().getCon().prepareStatement(sql);// 쿼리준비
			pstmt.setString(1, t_filename.getText());// 파일URL
			pstmt.setString(2, t_product_name.getText());// 제품명
			pstmt.setInt(3, Integer.parseInt(t_acl.getText()));// 알코올도수
			pstmt.setInt(4, Integer.parseInt(t_price.getText()));// 가격
			pstmt.setString(5, t_detail.getText());// 상세설명
			result = pstmt.executeUpdate();// DML
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AdminMain.getAdminMain().getDbControl().close(pstmt);
		}
		return result;
	}

}
