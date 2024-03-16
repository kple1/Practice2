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
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		JLabel searchButton = new JLabel("");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createPanelBro();
			}
		});
		ImageIcon image2 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\검색.png");
		searchButton.setIcon(MainPlugin.setSizeImage(image2, 33, 25));
		searchButton.setBounds(346, 81, 33, 25);
		frame.getContentPane().add(searchButton);
		
		JLabel lblNewLabel_4 = new JLabel("정렬기준");
		lblNewLabel_4.setBounds(545, 133, 57, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		JComboBox soltComboBox = new JComboBox();
		soltComboBox.addItem("오름차순");
		soltComboBox.addItem("내림차순");
		soltComboBox.setBounds(606, 129, 81, 23);
		frame.getContentPane().add(soltComboBox);
		
		createPanelBro();
	}
	
	public void createPanelBro () {
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
		for (int i = 0; i < array.size(); i++) {
			String getNo = array.get(i);
			String getName = DB.getStringData("m_name", "music", "m_no", getNo);
			int getAgeLimit = DB.getIntData("agelimit", "music", "m_no", getNo);
			
			if (searchField.getText().isEmpty()) {
				panel.add(new MainPlugin(getName, DB.getImage().get(i), getAgeLimit));
			} else if (getName.contains(searchField.getText())) {
				panel.add(new MainPlugin(getName, DB.getImage().get(i), getAgeLimit));
			} else {
				continue;
			}
		}
	}
}
