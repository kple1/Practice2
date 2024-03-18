package Form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Data.DB;
import Utils.MainPlugin;

public class MusicInformation {

	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusicInformation window = new MusicInformation("", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	String musicName;
	byte[] image;
	int count = 0;
	JProgressBar progressBar;
	Timer timer;
    int counter = 0;
    
	public MusicInformation(String musicName, byte[] image) {
		this.musicName = musicName;
		this.image = image;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 618, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("음원상세정보");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(226, 10, 152, 37);
		frame.getContentPane().add(lblNewLabel);

		JLabel mainLogo = new JLabel("");
		mainLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Main main = new Main();
				main.getFrame().setVisible(true);
			}
		});
		ImageIcon image = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\메인.png");
		mainLogo.setIcon(MainPlugin.setSizeImage(image, 115, 37));
		mainLogo.setBounds(475, 82, 115, 37);
		frame.getContentPane().add(mainLogo);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 130, 602, 1);
		frame.getContentPane().add(panel);

		JLabel musicTitle = new JLabel(musicName);
		musicTitle.setFont(new Font("굴림", Font.PLAIN, 20));
		musicTitle.setBounds(10, 96, 398, 23);
		frame.getContentPane().add(musicTitle);

		JPanel imagePanel = new JPanel();
		ImageIcon imageIcon = new ImageIcon(this.image);
		JLabel label = new JLabel(imageIcon);
		label.setBounds(0, 0, 190, 178);
		imagePanel.add(label);
		imagePanel.setBounds(10, 144, 190, 178);
		frame.getContentPane().add(imagePanel);

		JLabel singerLabel = new JLabel("아티스트");
		singerLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		singerLabel.setBounds(224, 147, 71, 20);
		frame.getContentPane().add(singerLabel);

		JLabel lyricsLabel = new JLabel("작사");
		lyricsLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		lyricsLabel.setBounds(224, 178, 71, 20);
		frame.getContentPane().add(lyricsLabel);

		JLabel compostionLabel = new JLabel("작곡");
		compostionLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		compostionLabel.setBounds(224, 208, 71, 20);
		frame.getContentPane().add(compostionLabel);

		JLabel albumLabel = new JLabel("앨범");
		albumLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		albumLabel.setBounds(224, 238, 71, 20);
		frame.getContentPane().add(albumLabel);

		JLabel playtimeLabel = new JLabel("재생시간");
		playtimeLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		playtimeLabel.setBounds(224, 268, 71, 20);
		frame.getContentPane().add(playtimeLabel);

		JLabel soundqualityLabel = new JLabel("음질");
		soundqualityLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		soundqualityLabel.setBounds(224, 298, 71, 20);
		frame.getContentPane().add(soundqualityLabel);

		String getSinger = DB.getStringData("singer", "music", "m_name", musicName);
		JLabel singer = new JLabel(getSinger);
		singer.setBounds(322, 151, 268, 15);
		frame.getContentPane().add(singer);

		String getlyrics = DB.getStringData("lyricist", "music", "m_name", musicName);
		JLabel lyrics = new JLabel(getlyrics);
		lyrics.setBounds(322, 182, 268, 15);
		frame.getContentPane().add(lyrics);

		String getComposer = DB.getStringData("composer", "music", "m_name", musicName);
		JLabel compostion = new JLabel(getComposer);
		compostion.setBounds(322, 212, 268, 15);
		frame.getContentPane().add(compostion);

		String getAlbum = DB.getStringData("album", "music", "m_name", musicName);
		JLabel album = new JLabel(getAlbum);
		album.setBounds(322, 242, 268, 15);
		frame.getContentPane().add(album);

		String getPlaytime = DB.getStringData("playtime", "music", "m_name", musicName);
		JLabel playtime = new JLabel(getPlaytime);
		playtime.setBounds(322, 272, 268, 15);
		frame.getContentPane().add(playtime);

		String getSoundquality = DB.getStringData("soundquality", "music", "m_name", musicName);
		JLabel soundquality = new JLabel(getSoundquality);
		soundquality.setBounds(322, 302, 268, 15);
		frame.getContentPane().add(soundquality);

		JButton pickupButton = new JButton("담기");
		pickupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				count = 0;
				String u_no = DB.getStringData("u_no", "user", "id", Login.id_textField.getText());
				List<String> getMusic = DB.getMnayData("m_no", "playlist", "u_no", u_no);
				for (String list : getMusic) {
					String m_name = DB.getStringData("m_name", "music", "m_no", list);
					if (m_name.equals(musicName)) {
						count += 1;
					}
				}
				
				if (count != 0) {
					JOptionPane.showMessageDialog(null, "playlist에 담겨진 음원입니다.", "경고", JOptionPane.ERROR_MESSAGE);
				} else {
					String m_no = DB.getStringData("m_no", "music", "m_name", musicName);
					DB.insertPlayList(u_no, m_no);
				}
			}
		});
		pickupButton.setBounds(174, 354, 97, 23);
		frame.getContentPane().add(pickupButton);

		JLabel listenImage = new JLabel("");
		ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\재생중지.png");
		listenImage.setIcon(MainPlugin.setSizeImage(icon, 20, 20));
		listenImage.setBounds(582, 420, 20, 20);
		frame.getContentPane().add(listenImage);
		
		JLabel time = new JLabel(getPlaytime);
		time.setHorizontalAlignment(SwingConstants.RIGHT);
		time.setBounds(522, 404, 57, 15);
		frame.getContentPane().add(time);
		
		JButton listenButton = new JButton("듣기");
		listenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				count = 0;
				String u_no = DB.getStringData("u_no", "user", "id", Login.id_textField.getText());
				List<String> getMusic = DB.getMnayData("m_no", "playlist", "u_no", u_no);
				for (String list : getMusic) {
					String m_name = DB.getStringData("m_name", "music", "m_no", list);
					if (m_name.equals(musicName)) {
						count += 1;
					}
				}
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "음원 담기를 먼저 실행해 주세요.", "경고", JOptionPane.ERROR_MESSAGE);
				} else {
					if (listenButton.getText().equals("듣기")) {
						int result = Integer.parseInt(getPlaytime.substring(0, 2)) * 60 + (Integer.parseInt(getPlaytime.substring(3, 5)));
						progressBar = new JProgressBar();
						progressBar.setMinimum(0);
						progressBar.setMaximum(result); //수정
						
						timer = new Timer(1000, new ActionListener() {
							int getSecond = Integer.parseInt(getPlaytime.substring(3, 5));
							int getMin =  Integer.parseInt(getPlaytime.substring(0, 2));
				            public void actionPerformed(ActionEvent evt) {
				                counter++;
				                progressBar.setValue(counter);
								
								if (getSecond == 0) {
									getMin -= 1;
									getSecond = 60;
									time.setText(getMin + ":" + String.valueOf(getSecond -= 1));
								} else {
									time.setText(getMin + ":" + String.valueOf(getSecond -= 1));
								}
				                if (counter >= result) {
				                	counter = 0;
				                    timer.stop();
				                    JOptionPane.showMessageDialog(null, "음원 재생이 완료되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
				                }
				            }
				        });
						timer.start();
						
						progressBar.setBounds(0, 420, 579, 20);
						frame.getContentPane().add(progressBar);
						listenButton.setText("멈춤");
						ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\재생중.png");
						listenImage.setIcon(MainPlugin.setSizeImage(icon, 20, 20));
					} else {
						timer.stop();
						listenButton.setText("듣기");
						ImageIcon icon = new ImageIcon("C:\\Users\\User\\Desktop\\제2과제\\datafiles\\재생중지.png");
						listenImage.setIcon(MainPlugin.setSizeImage(icon, 20, 20));
						JOptionPane.showMessageDialog(null, "음원 재생이 중지되었습니다.", "경고", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		listenButton.setBounds(322, 354, 97, 23);
		frame.getContentPane().add(listenButton);
	}
}
