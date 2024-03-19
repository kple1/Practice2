package Form;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Data.DB;
import Utils.Top5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Summary {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Summary window = new Summary();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	public Summary() {
		initialize();
	}

	List<String> m_name = new ArrayList<>();
	Top5 top1;
	Top5 top2;
	Top5 top3;
	Top5 top4;
	Top5 top5;
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 528, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("연령별 TOP 5");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(178, 10, 161, 44);
		frame.getContentPane().add(lblNewLabel);
		JComboBox comboBox = new JComboBox();

		for (int i = 10; i <= 60; i += 10) {
			comboBox.addItem(i + "대");
		}
		comboBox.addItem("70대 이상");
		comboBox.setBounds(395, 60, 105, 23);
		frame.getContentPane().add(comboBox);

		JLabel label1 = new JLabel("New label");		
		label1.setBounds(10, 379, 67, 15);
		frame.getContentPane().add(label1);

		JLabel label2 = new JLabel("New label");
		label2.setBounds(107, 379, 67, 15);
		frame.getContentPane().add(label2);

		JLabel label3 = new JLabel("New label");
		label3.setBounds(227, 379, 67, 15);
		frame.getContentPane().add(label3);

		JLabel label4 = new JLabel("New label");
		label4.setBounds(343, 379, 67, 15);
		frame.getContentPane().add(label4);

		JLabel label5 = new JLabel("New label");
		label5.setBounds(435, 379, 67, 15);
		frame.getContentPane().add(label5);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (comboBox.getSelectedIndex()) {
				case 0:
					List<String> array1 = DB.getCountData(20, 10);
					create(array1, label1, label2, label3, label4, label5);
					break;
				case 1:
					List<String> array2 = DB.getCountData(30, 20);
					create(array2, label1, label2, label3, label4, label5);
					break;
				case 2:
					List<String> array3 = DB.getCountData(40, 30);
					create(array3, label1, label2, label3, label4, label5);
					break;
				case 3:
					List<String> array4 = DB.getCountData(50, 40);
					create(array4, label1, label2, label3, label4, label5);
					break;
				case 4:
					List<String> array5 = DB.getCountData(60, 50);
					create(array5, label1, label2, label3, label4, label5);
					break;
				case 5:
					List<String> array6 = DB.getCountData(70, 60);
					create(array6, label1, label2, label3, label4, label5);
					break;
				case 6:
					List<String> array7 = DB.getCountData(80, 70);
					create(array7, label1, label2, label3, label4, label5);
					break;
				case 7:
					List<String> array8 = DB.getCountData70Over(70);
					create(array8, label1, label2, label3, label4, label5);
					break;
				}
			}
		});		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 366, 512, 3);
		frame.getContentPane().add(panel);

		JPanel f = new JPanel();
		f.setLayout(null);
		f.setBounds(435, 93, 65, 276);
		top5.setBounds(0, 0, 65, 276);
		f.add(top5);
		frame.getContentPane().add(f);

		JPanel d = new JPanel();
		d.setBounds(343, 93, 65, 276);
		top4.setBounds(0, 0, 65, 276);
		d.add(top4);
		frame.getContentPane().add(d);
		d.setLayout(null);

		JPanel a = new JPanel();
		a.setLayout(null);
		top1.setBounds(0, 0, 65, 276);
		a.add(top1);
		a.setBounds(12, 93, 65, 276);
		frame.getContentPane().add(a);

		JPanel b = new JPanel();
		b.setBounds(107, 93, 65, 276);
		top2.setBounds(0, 0, 65, 276);
		b.add(top2);
		frame.getContentPane().add(b);
		b.setLayout(null);

		JPanel c = new JPanel();
		c.setBounds(227, 93, 65, 276);
		top3.setBounds(0, 0, 65, 276);
		c.add(top3);
		frame.getContentPane().add(c);
		c.setLayout(null);

		//초기 10대
		List<String> array = DB.getCountData(20, 10);
		create(array, label1, label2, label3, label4, label5);
	}

	public void create(List<String> array, JLabel label1, JLabel label2, JLabel label3, JLabel label4, JLabel label5) {
		for (int i = 0; i < array.size(); i++) {
			JLabel[] labelList = new JLabel[] { label1, label2, label3, label4, label5 };
			String getName = DB.getStringData("m_name", "music", "m_no", array.get(i));
			if (array.get(i) == null || array.get(i).isEmpty()) {
				labelList[i].setText("");
			} else {
				labelList[i].setText(getName);
			}
		}
		top1 = new Top5(array.get(0), Color.BLUE);
		top2 = new Top5(array.get(1), Color.ORANGE);
		top3 = new Top5(array.get(2), Color.GRAY);
		top4 = new Top5(array.get(3), Color.YELLOW);
		top5 = new Top5(array.get(4), Color.CYAN);
	}
}
