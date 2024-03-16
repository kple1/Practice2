package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import Data.DB;
import Utils.MainPlugin;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	public static JTextField id_textField;
	public static JTextField pw_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login("");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}
	
	String getPw;
	public Login(String getPw) {
		this.getPw = getPw;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 213);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel loginLabel = new JLabel("로그인");
		loginLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		loginLabel.setBounds(193, 20, 67, 28);
		frame.getContentPane().add(loginLabel);
		
		JLabel mainLogo = new JLabel("");
		mainLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Main main = new Main();
				main.getFrame().setVisible(true);
			}
		});
		ImageIcon iamge = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\메인.png");
		mainLogo.setIcon(MainPlugin.setSizeImage(iamge, 107, 38));
		mainLogo.setBounds(315, 10, 107, 38);
		frame.getContentPane().add(mainLogo);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		idLabel.setBounds(68, 60, 24, 28);
		frame.getContentPane().add(idLabel);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("굴림", Font.PLAIN, 20));
		lblPw.setBounds(68, 98, 38, 28);
		frame.getContentPane().add(lblPw);
		
		id_textField = new JTextField();
		id_textField.setBounds(111, 59, 231, 28);
		frame.getContentPane().add(id_textField);
		id_textField.setColumns(10);
		
		pw_textField = new JTextField(getPw);
		pw_textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				PreferPopSong ppp = new PreferPopSong();
				ppp.getFrame().setVisible(true);
			}
		});
		pw_textField.setEnabled(false);
		pw_textField.setColumns(10);
		pw_textField.setBounds(111, 98, 231, 28);
		frame.getContentPane().add(pw_textField);
		
		JButton loginButton = new JButton("로그인");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getId = DB.getStringData("id", "user", "id", id_textField.getText());
				String getPw = DB.getStringData("pw", "user", "id", id_textField.getText());
				if (id_textField.getText().isEmpty() || pw_textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "빈칸이 있습니다.", "정보", JOptionPane.ERROR_MESSAGE);
				} else if (getId.equals(id_textField.getText()) && getPw.equals(pw_textField.getText())) {
					frame.dispose();
					Main main = new Main();
					main.getFrame().setVisible(true);
					main.registerButton.setEnabled(true);
					main.searchMusic.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "로그인 정보가 일치하지 않습니다.", "정보", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginButton.setBounds(111, 136, 231, 28);
		frame.getContentPane().add(loginButton);
	}

}
