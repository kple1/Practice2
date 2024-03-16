package Utils;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

import Form.PreferPopSong;

import java.awt.Color;

public class PreferPlugin extends JPanel {

	public PreferPlugin(String imageName, byte[] image, JTextField textField, int getNo) {

		JPanel imagePanel = new JPanel();
		imagePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (PreferPopSong.count < 4) {
					PreferPopSong.count += 1;
					if (PreferPopSong.count >= 2) {
						PreferPopSong.showSb.append("," + getNo);
					} else {
						PreferPopSong.showSb.append(getNo);
					}
					PreferPopSong.list.add(getNo);
					setBorder(new LineBorder(new Color(255, 0, 0)));
					textField.setText(String.valueOf(PreferPopSong.showSb));
				} else {
					JOptionPane.showMessageDialog(null, "선호 음원 4개를 선택하세요.", "경고", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});

		if (imageName.length() > 9) {
			imageName = imageName.substring(0, 9) + "...";
		}
		JLabel musicName = new JLabel(imageName);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addComponent(musicName).addContainerGap())
				.addComponent(imagePanel, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(imagePanel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(musicName)
						.addContainerGap(18, Short.MAX_VALUE)));
		setLayout(groupLayout);

		ImageIcon image1 = new ImageIcon(image);
		JLabel label = new JLabel(MainPlugin.setSizeImage(image1, 108, 93));
		imagePanel.add(label);
	}
}
