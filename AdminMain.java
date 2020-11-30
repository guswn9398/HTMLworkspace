package com.cocktailmall.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cocktailmall.db.DBControl;

/*
 * BoardApp가 모든 페이지들을 보유하고 있는 최상위 컨테이너 이므로, 
 * 각 페이지들마다 서로 공유할 데이터가 있다면 , BoardApp에 데이터를 두고 처리하면 됨
 * */


public class AdminMain extends JFrame  {
	private static AdminMain adminMain;

	public static final int WIDTH = 700;
	public static final int HEIGHT = 900;

	Font f1, f2;
	JLabel la_north;
	JButton bt_regist, bt_order;
	JPanel p_center;

	// 접속에 필요한 정보들..
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "user1104";
	private String pass = "user1104";

	// 모든 페이지들이 사용할 접속정보 객체를 공통으로 선언
	// 이 커넥션 객체는 프로그램이 가동과 동시에 접속을 얻어다 놓자!!
	private DBControl dbcontrol;
	private Connection con;

	public static AdminMain getAdminMain() {
		return adminMain;
	}

	public AdminMain() {
		this.getConnection();// 프레임을 보여주기 직전에 데이터베이스 접속 성공해놓자!!

		// 생성
		la_north = new JLabel("Cocktail Kiosk");
		bt_regist = new JButton("제품 등록");
		bt_order = new JButton("주문 관리");
		p_center = new JPanel();

		f1 = new Font("고딕", Font.BOLD, 27);
		f2 = new Font("돋움", Font.BOLD, 20);
		la_north.setFont(f1);
		bt_regist.setFont(f2);
		bt_order.setFont(f2);
		la_north.setHorizontalAlignment(JLabel.CENTER);
		la_north.setPreferredSize(new Dimension(WIDTH - 100, HEIGHT - 800));
		bt_regist.setPreferredSize(new Dimension(WIDTH - 100, HEIGHT - 550));
		bt_order.setPreferredSize(new Dimension(WIDTH - 100, HEIGHT - 550));

		p_center.add(bt_regist);
		p_center.add(bt_order);

		add(la_north, BorderLayout.NORTH);
		add(p_center, BorderLayout.CENTER);

		setVisible(true);
		setSize(700, 900);
		setLocationRelativeTo(null);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disConnection(); // 접속해제
				System.exit(0);
			}
		});

		// 제품관리 버튼과 리스너연결
		bt_regist.addActionListener((e) -> {
			setVisible(false);
			new Product();
		});

		// 주문 버튼과 리스너연결
		bt_order.addActionListener((e) -> {
			setVisible(false);
			new Recepit();
		});

	}

	// 접속
	public void getConnection() {
		try {
			Class.forName(driver);// 드라이버 로드
			con = DriverManager.getConnection(url, user, pass); // 접속시도 후, 객체 반환
			if (con == null) { // 접속실패인경우 메시지 출력
				JOptionPane.showMessageDialog(this, "데이터베이스에 접속하지 못했습니다.");
			} else {// 접속 성공의 경우 윈도우 제목창에 현재 접속자 출력
				this.setTitle("관리자모드 접속중");
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new AdminMain();
	}

	// 다른 객체들이 접근할 수 있도록 getter 제공
	public Connection getCon() {
		return con;
	}


	public DBControl getDbControl() {
		return dbcontrol;
	}

}
