package Form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Data.DB;
import Utils.ImageRender;
import Utils.MainPlugin;

public class MyPage {

	private JFrame frame;
	private JTable playList;
	private JTable listenList;
	JPopupMenu pm = new JPopupMenu();
	JMenuItem remove = new JMenuItem("삭제");
	DefaultTableModel listenModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPage window = new MyPage("");
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

	String name;

	public MyPage(String name) {
		this.name = name;
		initialize();
	}

	int i = 0;
	int j = 0;
	List<String> p_no = DB.getAllStringData("p_no", "listenlist");
	int saveNum = -1;
	int listenCount = 0;
	String saveName = "";
	JLabel myMusicCount;

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\마이크.png"));
		frame.setTitle("마이페이지");
		frame.setBounds(100, 100, 775, 583);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("마이페이지");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(303, 10, 150, 47);
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
		ImageIcon image = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\메인.png");
		mainLogo.setIcon(MainPlugin.setSizeImage(image, 105, 31));
		mainLogo.setFont(new Font("굴림", Font.PLAIN, 20));
		mainLogo.setBounds(642, 10, 105, 31);
		frame.getContentPane().add(mainLogo);

		JLabel nameLabel = new JLabel(name + " 님");
		nameLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		nameLabel.setBounds(12, 54, 129, 25);
		frame.getContentPane().add(nameLabel);

		JButton btnNewButton = new JButton("회원 정보수정");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				String getName = DB.getStringData("u_name", "user", "id", Login.id_textField.getText());
				String getBirth = DB.getStringData("birth", "user", "id", Login.id_textField.getText());
				UserInformation ui = new UserInformation(getName, getBirth, Login.id_textField.getText(), Login.pw_textField.getText());
				ui.getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(623, 51, 124, 28);
		frame.getContentPane().add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 80, 735, 419);
		frame.getContentPane().add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("PlayList", null, scrollPane, null);

		playList = new JTable();
		playList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int getCol = playList.getSelectedColumn();
				if (getCol == 2 || getCol == 3) {
					pm.add(remove);
					if (tabbedPane.getTitleAt(0).equals("PlayList"))
						if (e.getButton() == e.BUTTON3) {
							playList.setSelectionBackground(Color.yellow);
							pm.show(playList, e.getX(), e.getY());
						}
				}
			}
		});
		DefaultTableModel model = new DefaultTableModel(new Object[][] {

		}, new String[] { "No", "\uC74C\uC6D0 \uC0AC\uC9C4", "\uC544\uD2F0\uC2A4\uD2B8", "\uC74C\uC6D0 \uC81C\uBAA9" });

		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int getRow = playList.getSelectedRow();
				int getListenListRow = listenList.getSelectedRow();
				int result = JOptionPane.showConfirmDialog(null, "음원을 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					if (tabbedPane.getSelectedIndex() == 0) {
						model.removeRow(getRow);
						myMusicCount.setText("나의 음원 수 : " + --j + "곡");
					} else {
						listenModel.removeRow(getListenListRow);
						myMusicCount.setText("나의 음원 수 : " + --i + "곡");
					}
				}
			}
		});

		String u_no = DB.getStringData("u_no", "user", "id", Login.id_textField.getText());
		List<String> m_no = DB.getMnayData("m_no", "playlist", "u_no", u_no);
		for (String list : m_no) {
			String getMusicName = DB.getStringData("album", "music", "m_no", list);
			String getSinger = DB.getStringData("singer", "music", "m_no", list);
			ImageIcon image2 = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\album\\" + list + ".jpg");
			model.addRow(new Object[] { ++j, MainPlugin.setSizeImage(image2, 50, 50), getSinger, getMusicName });
		}

		myMusicCount = new JLabel("나의 음원 수 : " + j + "곡");
		myMusicCount.setFont(new Font("굴림", Font.PLAIN, 25));
		myMusicCount.setBounds(526, 509, 221, 25);
		frame.getContentPane().add(myMusicCount);

		playList.setModel(model);
		playList.setRowHeight(50);
		playList.getColumnModel().getColumn(0).setPreferredWidth(33);
		playList.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
		playList.getColumnModel().getColumn(2).setPreferredWidth(230);
		playList.getColumnModel().getColumn(3).setPreferredWidth(282);
		scrollPane.setViewportView(playList);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("ListenList", null, scrollPane_1, null);

		listenList = new JTable();
		listenModel = new DefaultTableModel(new Object[][] {}, new String[] { "No", "\uC74C\uC6D0 \uC2DC\uAC04",
				"\uC544\uD2F0\uC2A4\uD2B8", "\uC74C\uC6D0 \uC81C\uBAA9", "\uB4E3\uAE30 \uD69F\uC218" });

		CompletableFuture.runAsync(() -> {
			for (String list : DB.getMusicNo(u_no)) {
				String m_name = DB.getStringData("m_name", "music", "m_no", list);
				String singer = DB.getStringData("singer", "music", "m_no", list);
				ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\album\\" + list + ".jpg");
				listenModel.addRow(new Object[] { ++i, MainPlugin.setSizeImage(icon, 50, 50), singer, m_name, 1 });
			}
		});

		listenList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int getCol = listenList.getSelectedColumn();
				if (tabbedPane.getTitleAt(1).equals("ListenList"))
					if (getCol == 2 || getCol == 3) {
						pm.add(remove);
						if (e.getButton() == e.BUTTON3) {
							listenList.setSelectionBackground(Color.yellow);
							pm.show(listenList, e.getX(), e.getY());
						}
					}
			}
		});

		listenList.setRowHeight(50);
		listenList.setModel(listenModel);
		listenList.getColumnModel().getColumn(0).setPreferredWidth(35);
		listenList.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
		listenList.getColumnModel().getColumn(2).setPreferredWidth(175);
		listenList.getColumnModel().getColumn(3).setPreferredWidth(167);
		listenList.getColumnModel().getColumn(4).setPreferredWidth(170);
		scrollPane_1.setViewportView(listenList);

		//tab 넘길 때마다 음원 곡 수 업데이트
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) {
					myMusicCount.setText("나의 음원 수 : " + j + "곡");
				} else {
					myMusicCount.setText("나의 음원 수 : " + i + "곡");
				}
			}
		});
	}
}
