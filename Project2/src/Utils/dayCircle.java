package Utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class dayCircle extends JPanel {
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.fillOval(0, 0, 55, 55);
	}
}
