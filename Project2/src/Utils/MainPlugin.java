package Utils;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.LayoutStyle.ComponentPlacement;

import Data.DB;
import Form.Login;
import Form.MusicInformation;

public class MainPlugin extends JPanel {
	
	JLabel ageLimit = new JLabel();
	String m_name;
	JLabel downloadLogo = new JLabel("");
	public MainPlugin(String m_name1, byte[] imageName, int ageLi, JFrame frame, String m_no) {
		JLayeredPane layeredPane = new JLayeredPane();
		
		m_name = m_name1;
		if (m_name.length() > 9) {
			m_name = m_name.substring(0, 9) + "...";
		}
		JLabel musicName = new JLabel(m_name);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(musicName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(musicName)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		
		ImageIcon imagei = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\19금.png");
		if (ageLi == 1) {
			ageLimit.setIcon(setSizeImage(imagei, 28, 23));
		}
		ageLimit.setBounds(12, 10, 28, 23);
		layeredPane.add(ageLimit);
		
		JLabel topPrice = new JLabel("New label");
		topPrice.setBounds(60, 10, 28, 23);
		layeredPane.add(topPrice);
		setLayout(groupLayout);
		
		downloadLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String u_no = DB.getStringData("u_no", "user", "id", Login.id_textField.getText());
				DB.insertPlayList(u_no, m_no);
				JOptionPane.showMessageDialog(null, "음원이 저장되었습니다.");
			}
		});
		ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\담기.png");
		downloadLogo.setIcon(MainPlugin.setSizeImage(icon, 40, 35));
		downloadLogo.setBounds(60, 58, 40, 35);
		downloadLogo.setVisible(false);
		layeredPane.add(downloadLogo);
		
		ImageIcon image = new ImageIcon(imageName);
		JLabel label = new JLabel(setSizeImage(image, 100, 93));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				downloadLogo.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				downloadLogo.setVisible(false);
			}
		});
		
		String getArtist = DB.getStringData("singer", "music", "m_no", m_no);
		label.setToolTipText("<html>아티스트: " + getArtist + "<br>제목: " + m_name1 + "<html/>");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				MusicInformation mi = new MusicInformation(m_name1, imageName);
				mi.getFrame().setVisible(true);
			}
		});
		label.setBounds(0, 0, 100, 93);
		layeredPane.add(label);
	}
	
	public static ImageIcon setSizeImage(ImageIcon image, int x, int y) {
		Image getImage = image.getImage();
		Image returnImage = getImage.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(returnImage);
		return icon;
	}
}
