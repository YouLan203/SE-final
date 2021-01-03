package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartNoteGUI extends JFrame {

	private JPanel contentPane;
	
	public StartNoteGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //點擊關閉時只關閉目前視窗
		setBounds(100, 100, 410, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	public void run(boolean x) {
		try {
			StartNoteGUI frame = new StartNoteGUI();
			frame.setVisible(true);
			if (x == true) {
				JLabel note = new JLabel("已建立檔案路徑：C:\\StudentGradeSystem");
				note.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				note.setBounds(0, 55, 387, 23);
				contentPane.add(note,0);
				note.repaint();
				
				JButton colseButton = new JButton("確認");
				colseButton.setBackground(SystemColor.controlHighlight);
				colseButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				colseButton.setBounds(140, 100, 111, 31);
				contentPane.add(colseButton);
				colseButton.repaint();
				colseButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
				frame.getContentPane().add(note);
				frame.getContentPane().add(colseButton);
			}
			else {
				JLabel note = new JLabel("檔案路徑建立失敗！");
				note.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				note.setBounds(0, 55, 387, 23);
				contentPane.add(note);
				
				JButton colseButton = new JButton("確認");
				colseButton.setBackground(SystemColor.controlHighlight);
				colseButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				colseButton.setBounds(140, 100, 111, 31);
				contentPane.add(colseButton);
				colseButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
				frame.getContentPane().add(note);
				frame.getContentPane().add(colseButton);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
