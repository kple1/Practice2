package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Data.DB;
import Utils.MainPlugin;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class SearchMusic {

	private JFrame frame;
	private JTextField searchField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchMusic window = new SearchMusic();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SearchMusic() {
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\마이크.png"));
		frame.setTitle("음원검색");
		frame.setBounds(100, 100, 715, 667);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("음원검색");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(306, 10, 101, 37);
		frame.getContentPane().add(lblNewLabel);

		JLabel mainLogo = new JLabel("");
		mainLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Main main = new Main();
				main.getFrame().setVisible(true);
			}
		});
		ImageIcon image1 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\메인.png");
		mainLogo.setIcon(MainPlugin.setSizeImage(image1, 142, 37));
		mainLogo.setFont(new Font("굴림", Font.PLAIN, 25));
		mainLogo.setBounds(545, 10, 142, 37);
		frame.getContentPane().add(mainLogo);

		JLabel lblNewLabel_2 = new JLabel("검색기준");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(12, 81, 62, 25);
		frame.getContentPane().add(lblNewLabel_2);

		JComboBox searchComboBox = new JComboBox();
		searchComboBox.addItem("제목");
		searchComboBox.addItem("아티스트");
		searchComboBox.setBounds(86, 82, 82, 23);
		frame.getContentPane().add(searchComboBox);

		searchField = new JTextField();
		searchField.setBounds(174, 83, 160, 21);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("정렬기준");
		lblNewLabel_4.setBounds(545, 133, 57, 15);
		frame.getContentPane().add(lblNewLabel_4);

		JComboBox soltComboBox = new JComboBox();
		soltComboBox.addItem("오름차순");
		soltComboBox.addItem("내림차순");
		soltComboBox.setBounds(606, 129, 81, 23);
		frame.getContentPane().add(soltComboBox);

		JLabel searchButton = new JLabel("");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createPanelBro(searchComboBox, soltComboBox);
			}
		});
		ImageIcon image2 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\검색.png");
		searchButton.setIcon(MainPlugin.setSizeImage(image2, 33, 25));
		searchButton.setBounds(346, 81, 33, 25);
		frame.getContentPane().add(searchButton);

		createPanelBro(searchComboBox, soltComboBox);
	}

	public void createPanelBro(JComboBox searchComboBox, JComboBox soltComboBox) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 158, 676, 460);
		frame.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		panel.removeAll();
		panel.revalidate();
		panel.repaint();

		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 6, 0, 0));
		List<String> array = DB.getAllStringData("m_no", "music");
		List<String> getName = DB.getAllStringData("m_name", "music");
		List<String> getArtist = DB.getAllStringData("singer", "music");
		List<Integer> getAgeLimit = DB.getAllIntData("agelimit", "music");
		List<byte[]> getImage = DB.getImage();

		// comboBox 정렬
		for (int i = 0; i < array.size(); i++) {
			if (searchField.getText().isEmpty()) {
				panel.add(new MainPlugin(getName.get(i), DB.getImage().get(i), getAgeLimit.get(i), frame, array.get(i)));
			} else if (searchComboBox.getSelectedItem().equals("제목")) {
				if (soltComboBox.getSelectedItem().equals("내림차순")) {
					Collections.sort(getName, Collections.reverseOrder());
					Collections.sort(getArtist, Collections.reverseOrder());
					Collections.sort(getAgeLimit, Collections.reverseOrder());
					if (getName.get(i).contains(searchField.getText())) {
						panel.add(new MainPlugin(getName.get(i), DB.getReverseImage().get(i), getAgeLimit.get(i), frame, array.get(i)));
					} else {
						continue;
					}
				} else {
					Collections.sort(getName);
					Collections.sort(getArtist);
					Collections.sort(getAgeLimit);
					if (getName.get(i).contains(searchField.getText())) {
						panel.add(new MainPlugin(getName.get(i), getImage.get(i), getAgeLimit.get(i), frame, array.get(i)));
					} else {
						continue;
					}
				}
			} else if (searchComboBox.getSelectedItem().equals("아티스트")) {
				if (soltComboBox.getSelectedItem().equals("내림차순")) {
					Collections.sort(getName, Collections.reverseOrder());
					Collections.sort(getArtist, Collections.reverseOrder());
					Collections.sort(getAgeLimit, Collections.reverseOrder());
					if (getArtist.get(i).contains(searchField.getText())) {
						panel.add(new MainPlugin(getName.get(i), DB.getReverseImage().get(i), getAgeLimit.get(i), frame, array.get(i)));
					} else {
						continue;
					}
				} else {
					Collections.sort(getName);
					Collections.sort(getArtist);
					Collections.sort(getAgeLimit);
					if (getArtist.get(i).contains(searchField.getText())) {
						panel.add(new MainPlugin(getName.get(i), getImage.get(i), getAgeLimit.get(i), frame, array.get(i)));
					} else {
						continue;
					}
				}
			}
		}
	}
}
