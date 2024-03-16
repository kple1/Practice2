package Form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Data.DB;
import Utils.MainPlugin;

public class Main {

	private JFrame frame;
	String getUserYear = "";
	String getUserMonth = "";
	String getUserDay = "";
	JButton registerButton = new JButton("회원가입");
	JButton searchMusic = new JButton("음원검색");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\마이크.png"));
		frame.setTitle("메인");
		frame.setBounds(100, 100, 639, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("SKILL MUSIC (OLD POP SONG)");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(135, 10, 367, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JButton loginButton = new JButton("로그인");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login login = new Login("");
				login.getFrame().setVisible(true);
			}
		});
		loginButton.setBounds(12, 57, 111, 45);
		frame.getContentPane().add(loginButton);
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Register rg = new Register();
				rg.getFrame().setVisible(true);
			}
		});
		registerButton.setEnabled(false);
		registerButton.setBounds(135, 57, 111, 45);
		frame.getContentPane().add(registerButton);
		
		searchMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SearchMusic sm = new SearchMusic();
				sm.getFrame().setVisible(true);
			}
		});
		searchMusic.setEnabled(false);
		searchMusic.setBounds(258, 57, 111, 45);
		frame.getContentPane().add(searchMusic);
		
		JButton myPageButton = new JButton("마이페이지");
		myPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MyPage myPage = new MyPage();
				myPage.getFrame().setVisible(true);
			}
		});
		myPageButton.setBounds(381, 57, 111, 45);
		frame.getContentPane().add(myPageButton);
		
		JButton grape = new JButton("분석");
		grape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		grape.setBounds(504, 57, 111, 45);
		frame.getContentPane().add(grape);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 112, 603, 354);
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		List<String> array = DB.getAllStringData("m_no", "music");
		for (int i = 0; i < array.size(); i++) {
			String getNo = array.get(i);
			String getName = DB.getStringData("m_name", "music", "m_no", getNo);
			int getAgeLimit = DB.getIntData("agelimit", "music", "m_no", getNo);
			
			String getDate = DB.getStringData("birth", "user", "id", Login.id_textField.getText());
			if (Login.id_textField != null) {
				getUserYear = getDate.substring(0, 4);
				getUserMonth= getDate.substring(5, 7);
				getUserDay = getDate.substring(8, 10);
			}
			
			int year = LocalDateTime.now().getYear() - Integer.parseInt(getUserYear);
			int month = LocalDateTime.now().getMonthValue();
			int day = LocalDateTime.now().getDayOfMonth();
			
			if (year < 19 && month < Integer.parseInt(getUserMonth) && day < Integer.parseInt(getUserDay)) {
				panel.add(new MainPlugin(getName, DB.getImage().get(i), getAgeLimit));
			} else {
				panel.add(new MainPlugin(getName, DB.getImage().get(i), 0));
			}
		}
	}
}
