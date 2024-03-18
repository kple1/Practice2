package Form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Data.DB;
import Utils.MainPlugin;

public class UserInformation {

	private JFrame frame;
	private JTextField nameField;
	private JTextField birthField;
	private JTextField idField;
	private JTextField pwField;

	public JFrame getFrame() {
		return frame;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInformation window = new UserInformation("", "", "", "");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getName;
	public String getBirth;
	public String getId;
	public String getPw;

	public UserInformation(String getName, String getBirth, String getId, String getPw) {
		this.getName = getName;
		this.getBirth = getBirth;
		this.getId = getId;
		this.getPw = getPw;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\마이크.png"));
		frame.setTitle("회원정보");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("회원정보");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(168, 10, 104, 37);
		frame.getContentPane().add(lblNewLabel);

		JLabel mainLogo = new JLabel("");
		ImageIcon image1 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\메인.png");
		mainLogo.setIcon(MainPlugin.setSizeImage(image1, 104, 37));
		mainLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Main main = new Main();
				main.getFrame().setVisible(true);
			}
		});
		mainLogo.setFont(new Font("굴림", Font.PLAIN, 25));
		mainLogo.setBounds(318, 10, 104, 37);
		frame.getContentPane().add(mainLogo);

		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(40, 82, 34, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("생년월일");
		lblNewLabel_1_1.setBounds(40, 112, 56, 15);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("ID");
		lblNewLabel_1_2.setBounds(40, 137, 34, 15);
		frame.getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("PW");
		lblNewLabel_1_3.setBounds(40, 162, 34, 15);
		frame.getContentPane().add(lblNewLabel_1_3);

		nameField = new JTextField(getName);
		nameField.setBounds(104, 79, 212, 21);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);

		birthField = new JTextField(getBirth);
		birthField.setEnabled(false);
		birthField.setColumns(10);
		birthField.setBounds(104, 109, 212, 21);
		frame.getContentPane().add(birthField);

		idField = new JTextField(getId);
		idField.setEnabled(false);
		idField.setColumns(10);
		idField.setBounds(104, 134, 212, 21);
		frame.getContentPane().add(idField);

		pwField = new JTextField(getPw);
		pwField.setEnabled(false);
		pwField.setColumns(10);
		pwField.setBounds(104, 159, 212, 21);
		frame.getContentPane().add(pwField);

		JButton btnNewButton = new JButton("정보수정");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().isEmpty() || nameField.getText().isEmpty() || birthField.getText().isEmpty()|| pwField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "빈칸이 있습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else if (!Pattern.matches("[ㄱ-ㅎ가-힣]*", nameField.getText())) {
					JOptionPane.showMessageDialog(null, "이름은 한글만 가능합니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else {
					List<String> list = DB.getAllStringData("id", "user");
					JOptionPane.showMessageDialog(null, "정보수정이 완료되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
					DB.updateUser(idField.getText(), pwField.getText(), nameField.getText(), birthField.getText());
				}
			}
		});
		btnNewButton.setBounds(104, 190, 212, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel birthEditButton = new JLabel("");
		birthEditButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				SelectDay sd = new SelectDay(getName, getId, getPw);
				sd.getFrame().setVisible(true);
			}
		});
		ImageIcon image2 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\달력.png");
		birthEditButton.setIcon(MainPlugin.setSizeImage(image2, 21, 15));
		birthEditButton.setBounds(318, 112, 21, 15);
		frame.getContentPane().add(birthEditButton);

		JLabel pwEditButton = new JLabel("");
		pwEditButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				PreferPopSong ppp = new PreferPopSong("UserInformation", getName, getBirth, getId);
				ppp.getFrame().setVisible(true);
			}
		});
		ImageIcon image3 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\마이크.png");
		pwEditButton.setIcon(MainPlugin.setSizeImage(image3, 21, 15));
		pwEditButton.setBounds(318, 162, 21, 15);
		frame.getContentPane().add(pwEditButton);
	}
}
