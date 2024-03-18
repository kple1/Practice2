package Utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Top2 extends JPanel {
	String listenCount;
	public Top2(String listenCount) {
		this.listenCount = listenCount;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, 57, 257);
		
		JLabel label = new JLabel(listenCount);
		label.removeAll();
		add(label);
	}
}
