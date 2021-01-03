package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DeleteNoteGUI extends JFrame {

	private JPanel contentPane;
	public DeleteNoteGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //點擊關閉時只關閉目前視窗
		setBounds(100, 100, 410, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
		
	
	public void run(boolean x) {
		try {
			DeleteNoteGUI frame = new DeleteNoteGUI();
			frame.setVisible(true);
			if (x == true) {
				JLabel note = new JLabel("刪除成功！即將重新啟動。");
				note.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				note.setBounds(93, 55, 387, 23);
				contentPane.add(note);
				
				JButton colseButton = new JButton("確認");
				colseButton.setBackground(SystemColor.controlHighlight);
				colseButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				colseButton.setBounds(140, 100, 111, 31);
				contentPane.add(colseButton);
				colseButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						GUI reStart = new GUI();
						reStart.run();
					}
				});
				frame.getContentPane().add(note);
				frame.getContentPane().add(colseButton);
			}
			else{
				JLabel note = new JLabel("刪除失敗！請確認輸入是否有誤。");
				note.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				note.setBounds(50, 55, 387, 23);
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
