package Utils;

import java.awt.Graphics;

import javax.swing.JPanel;

public class mainTopPrice extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawOval(5, 5, 7, 7);
	}
}
