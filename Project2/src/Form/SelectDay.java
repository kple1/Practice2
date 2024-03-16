package Form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Utils.MainPlugin;
import Utils.dayCircle;
import Utils.redCircle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;

public class SelectDay {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectDay window = new SelectDay();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelectDay() {
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}
	
	int setMonth = LocalDateTime.now().getMonthValue();
	int getYear = LocalDateTime.now().getYear();
	JLabel monthLabel = new JLabel();

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 578, 558);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("날짜 선택");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(216, 10, 122, 44);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Main main = new Main();
				main.getFrame().setVisible(true);
			}
		});
		ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\메인.png");
		lblNewLabel_1.setIcon(MainPlugin.setSizeImage(icon, 122, 44));
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(428, 10, 122, 44);
		frame.getContentPane().add(lblNewLabel_1);

		JComboBox yearComboBox = new JComboBox();
		for (int i = 1950; i <= getYear; i++) {
			yearComboBox.addItem(String.valueOf(i));
		}
		yearComboBox.setSelectedItem(String.valueOf(getYear));
		yearComboBox.setBounds(216, 64, 98, 23);
		frame.getContentPane().add(yearComboBox);

		JLabel lblNewLabel_2 = new JLabel("년");
		lblNewLabel_2.setBounds(320, 68, 22, 15);
		frame.getContentPane().add(lblNewLabel_2);

		monthLabel.setText(setMonth + "월");
		monthLabel.setBounds(378, 68, 34, 15);
		frame.getContentPane().add(monthLabel);

		JButton beforePage = new JButton("<");
		beforePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (setMonth == 1) {
					setMonth = 12;
					getYear -= 1;
					yearComboBox.setSelectedItem(String.valueOf(getYear));
					monthLabel.setText(setMonth + "월");
				} else {
					setMonth -= 1;
					monthLabel.setText(setMonth + "월");
				}
			}
		});
		beforePage.setBounds(414, 64, 61, 23);
		frame.getContentPane().add(beforePage);

		JButton nextPage = new JButton(">");
		nextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (setMonth == 12) {
						if (getYear != LocalDateTime.now().getYear()) {
							getYear += 1;
							yearComboBox.setSelectedItem(String.valueOf(getYear));
						}
					} else {
						setMonth += 1;
						monthLabel.setText(setMonth + "월");
					}
				
			}
		});
		nextPage.setBounds(478, 64, 61, 23);
		frame.getContentPane().add(nextPage);

		JLabel lblNewLabel_3 = new JLabel("일");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(12, 115, 66, 15);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("월");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(90, 115, 66, 15);
		frame.getContentPane().add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("화");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setBounds(168, 115, 66, 15);
		frame.getContentPane().add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_3 = new JLabel("수");
		lblNewLabel_3_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_3.setBounds(246, 115, 66, 15);
		frame.getContentPane().add(lblNewLabel_3_3);

		JLabel lblNewLabel_3_4 = new JLabel("목");
		lblNewLabel_3_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_4.setBounds(320, 115, 66, 15);
		frame.getContentPane().add(lblNewLabel_3_4);

		JLabel lblNewLabel_3_5 = new JLabel("금");
		lblNewLabel_3_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_5.setBounds(390, 115, 66, 15);
		frame.getContentPane().add(lblNewLabel_3_5);

		JLabel lblNewLabel_3_6 = new JLabel("토");
		lblNewLabel_3_6.setForeground(Color.BLUE);
		lblNewLabel_3_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_6.setBounds(468, 115, 66, 15);
		frame.getContentPane().add(lblNewLabel_3_6);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(22, 140, 528, 369);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new GridLayout(6, 7, 0, 0));
		
		layeredPane.add(new dayCircle());
		layeredPane.add(new redCircle());
		
	}
}
