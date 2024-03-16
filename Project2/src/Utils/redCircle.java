package Utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class redCircle extends JPanel {
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.drawOval(0, 0, 57, 57);
	}
}
