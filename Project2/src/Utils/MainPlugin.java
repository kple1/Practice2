package Utils;

import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainPlugin extends JPanel {
	
	JLabel ageLimit = new JLabel();
	public MainPlugin(String m_name, byte[] imageName, int ageLi) {
		
		JLayeredPane layeredPane = new JLayeredPane();
		
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
		
		ImageIcon image = new ImageIcon(imageName);
		JLabel label = new JLabel(setSizeImage(image, 100, 93));
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
