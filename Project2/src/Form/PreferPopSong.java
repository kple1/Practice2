package Form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Data.DB;
import Utils.MainPlugin;
import Utils.PreferPlugin;
import Utils.dayCircle;

public class PreferPopSong {

	private JFrame frame;
	private JTextField textField;
	public static int count = 0;
	int count2 = 0;
	public static StringBuilder sb = new StringBuilder();
	public static StringBuilder showSb = new StringBuilder();
	public static List<Integer> list = new ArrayList<>();
	JPanel panel = new JPanel();
	UserInformation ui;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreferPopSong window = new PreferPopSong("");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String formName;

	public PreferPopSong(String formName) {
		this.formName = formName;
		initialize();
	}

	String getName;
	String getId;
	String getBirth;

	public PreferPopSong(String formName, String getName, String getBirth, String getId) {
		this.getName = getName;
		this.getId = getId;
		this.getBirth = getBirth;
		this.formName = formName;
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 704, 704);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel preferPopSongtitle = new JLabel("선호 POP SONG");
		preferPopSongtitle.setFont(new Font("굴림", Font.PLAIN, 25));
		preferPopSongtitle.setBounds(242, 10, 197, 29);
		frame.getContentPane().add(preferPopSongtitle);

		JLabel lblNewLabel = new JLabel("선택");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(26, 71, 36, 18);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(61, 70, 188, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel resetButton = new JLabel("");
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				sb.setLength(0);
				list.removeAll(list);
				showSb.setLength(0);
				panel.removeAll();
				List<String> array = DB.getAllStringData("m_no", "music");
				for (int i = 0; i < array.size(); i++) {
					String getName = DB.getStringData("m_name", "music", "m_no", array.get(i));
					panel.add(new PreferPlugin(getName, DB.getImage().get(i), textField, i + 1));
				}
				panel.repaint();
				panel.revalidate();
			}
		});
		ImageIcon image = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\초기화.png");
		resetButton.setIcon(MainPlugin.setSizeImage(image, 36, 29));
		resetButton.setBounds(261, 66, 36, 29);
		frame.getContentPane().add(resetButton);

		JButton selectButton = new JButton("선택");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (count != 4) {
					JOptionPane.showMessageDialog(null, "선호 음원 4개를 선택하세요.", "경고", JOptionPane.ERROR_MESSAGE);
				} else {
					Collections.sort(list);
					for (int array : list) {
						count2 += 1;
						if (count2 >= 2) {
							sb.append("," + array);
						} else if (count == 4) {
							sb.append(array);
						}
					}

					if (formName.equals("Login")) {
						Login login = new Login(String.valueOf(sb));
						login.getFrame().setVisible(true);
					} else if (formName.equals("Register")) {
						Register rg = new Register(dayCircle.resultSet, String.valueOf(sb));
						rg.getFrame().setVisible(true);
					} else if (formName.equals("UserInformation")) {
						ui = new UserInformation(getName, getBirth, getId, String.valueOf(sb));
						ui.getFrame().setVisible(true);
					}
					showSb.setLength(0);
					sb.setLength(0);
					list.removeAll(list);
					count = 0;
					frame.dispose();
				}
			}
		});
		selectButton.setBounds(309, 69, 97, 23);
		frame.getContentPane().add(selectButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 100, 650, 555);
		frame.getContentPane().add(scrollPane);

		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 5, 5, 0));

		List<String> array = DB.getAllStringData("m_no", "music");
		for (int i = 0; i < array.size(); i++) {
			String getName = DB.getStringData("m_name", "music", "m_no", array.get(i));
			panel.add(new PreferPlugin(getName, DB.getImage().get(i), textField, i + 1));
		}

		JLabel mainLogo = new JLabel("");
		mainLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (count != 4) {
					JOptionPane.showMessageDialog(null, "선호 음원 4개를 선택하세요.", "경고", JOptionPane.ERROR_MESSAGE);
				} else {
					frame.dispose();
					Collections.sort(list);
					for (int array : list) {
						count2 += 1;
						if (count2 >= 2) {
							sb.append("," + array);
						} else if (count == 4) {
							sb.append(array);
						}
					}
					Login login = new Login(String.valueOf(sb));
					// 초기화
					count = 0;
					showSb.setLength(0);
					sb.setLength(0);
					login.getFrame().setVisible(true);
				}
			}
		});
		ImageIcon image1 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\메인.png");
		mainLogo.setIcon(MainPlugin.setSizeImage(image1, 115, 49));
		mainLogo.setBounds(561, 10, 115, 49);
		frame.getContentPane().add(mainLogo);
	}

}
