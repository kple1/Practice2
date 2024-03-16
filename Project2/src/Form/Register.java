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
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register {

	private JFrame frame;
	private JTextField nameField;
	private JTextField birthField;
	private JTextField idField;
	private JTextField pwField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Register() {
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(174, 10, 92, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel mainLogo = new JLabel("");
		ImageIcon image1 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\메인.png");
		mainLogo.setIcon(MainPlugin.setSizeImage(image1, 103, 39));
		mainLogo.setBounds(319, 10, 103, 39);
		frame.getContentPane().add(mainLogo);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(56, 87, 53, 15);
		frame.getContentPane().add(nameLabel);
		
		JLabel birthDayLabel = new JLabel("생년월일");
		birthDayLabel.setBounds(56, 115, 53, 15);
		frame.getContentPane().add(birthDayLabel);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(56, 145, 53, 15);
		frame.getContentPane().add(idLabel);
		
		JLabel pwLabel = new JLabel("PW");
		pwLabel.setBounds(56, 170, 53, 15);
		frame.getContentPane().add(pwLabel);
		
		nameField = new JTextField();
		nameField.setBounds(118, 84, 224, 21);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		birthField = new JTextField();
		birthField.setEnabled(false);
		birthField.setColumns(10);
		birthField.setBounds(118, 112, 224, 21);
		frame.getContentPane().add(birthField);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(118, 142, 224, 21);
		frame.getContentPane().add(idField);
		
		pwField = new JTextField();
		pwField.setEnabled(false);
		pwField.setColumns(10);
		pwField.setBounds(118, 167, 224, 21);
		frame.getContentPane().add(pwField);
		
		JButton registButton = new JButton("회원가입");
		registButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().isEmpty() || nameField.getText().isEmpty() || birthField.getText().isEmpty() || pwField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "빈칸이 있습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else if (!Pattern.matches("[ㄱ-ㅎ|가-힣]", nameField.getText())) {
					JOptionPane.showMessageDialog(null, "이름은 한글만 가능합니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else {
					List<String> list = DB.getAllStringData("id", "user");
					for (String array: list) {
						if (array.equals(idField.getText())) {
							JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.", "경고", JOptionPane.ERROR_MESSAGE);
							break;
						} else {
							JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
							DB.insertUser(nameField.getText(), birthField.getText(), idField.getText(), pwField.getText());
							break;
						}
					}
				}
			}
		});
		registButton.setBounds(118, 198, 224, 23);
		frame.getContentPane().add(registButton);
		
		JLabel birthdaySelectButton = new JLabel("");
		birthdaySelectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				SelectDay sd = new SelectDay();
				sd.getFrame().setVisible(true);
			}
		});
		ImageIcon image2 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\달력.png");
		birthdaySelectButton.setIcon(MainPlugin.setSizeImage(image2, 23, 15));
		birthdaySelectButton.setBounds(354, 115, 23, 15);
		frame.getContentPane().add(birthdaySelectButton);
		
		JLabel pwSelectButton = new JLabel("");
		pwSelectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				PreferPopSong ppp = new PreferPopSong();
				ppp.getFrame().setVisible(true);
			}
		});
		ImageIcon image3 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\마이크.png");
		pwSelectButton.setIcon(MainPlugin.setSizeImage(image3, 23, 15));
		pwSelectButton.setBounds(354, 170, 23, 15);
		frame.getContentPane().add(pwSelectButton);
	}

}
