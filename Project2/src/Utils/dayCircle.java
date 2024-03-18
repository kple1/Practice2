package Utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Form.Register;
import Form.UserInformation;

public class dayCircle extends JPanel {
	
	private Color currentColor = Color.YELLOW;
	public static String resultSet;
	int day;
	int getYear;
	int getMonth;
	public dayCircle(int day, int getYear, int getMonth, JFrame frame, String formName, String getName, String getId, String getPw) {
		this.day = day;
		this.getYear = getYear;
		this.getMonth = getMonth;
		
		JLabel lblNewLabel = new JLabel(String.valueOf(day));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (currentColor.equals(Color.YELLOW)) {
					currentColor = Color.RED;
				} else {
					currentColor = Color.YELLOW;
				}
				repaint();
				
				frame.dispose();
				resultSet = getYear + "-" + getMonth + "-" + day;
				if (formName.equals("Register")) {
					Register rg = new Register(resultSet, "");
					rg.getFrame().setVisible(true);
				} else if (formName.equals("UserInformation")) {
					UserInformation ui = new UserInformation(getName, resultSet, getId, getPw);
					ui.getFrame().setVisible(true);
				}
			}
		});
	}
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int getYear = LocalDateTime.now().getYear();
		int getMonth = LocalDateTime.now().getMonthValue();
		int getDay = LocalDateTime.now().getDayOfMonth();

		if (this.getYear == getYear && this.getMonth >= getMonth) {
			
		} else {
			g.setColor(Color.YELLOW);
			g.fillOval(0, 0, 55, 55);
			
			g.setColor(currentColor);
			g.drawOval(0, 0, 56, 56);
		}
		
		if (this.getYear == getYear && this.getMonth == getMonth && day < getDay) {
			g.setColor(Color.YELLOW);
			g.fillOval(0, 0, 55, 55);
			
			g.setColor(currentColor);
			g.drawOval(0, 0, 56, 56);
		}
	}
}
