package Utils;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class layeredDayCircle extends JPanel {

	public layeredDayCircle(int getDay) {
		
		JLayeredPane layeredPane = new JLayeredPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel day = new JLabel(String.valueOf(getDay));
		day.setHorizontalAlignment(SwingConstants.CENTER);
		day.setBounds(0, 10, 48, 28);
		layeredPane.add(day);
		setLayout(groupLayout);
		
		JPanel dayPanel = new JPanel();
		dayPanel.setBounds(0, 0, 48, 48);;
		layeredPane.add(dayPanel);
	}
}
