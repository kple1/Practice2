package Utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Top5 extends JPanel {
	String listenCount;
	Color color;
	public Top5(String listenCount, Color color) {
		this.listenCount = listenCount;
		this.color = color;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.fillRect(0, 0, 57, 257);
		
		JLabel label = new JLabel(listenCount);
		label.removeAll();
		add(label);
	}
}
