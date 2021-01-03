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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //�I�������ɥu�����ثe����
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
				JLabel note = new JLabel("�R�����\�I�Y�N���s�ҰʡC");
				note.setFont(new Font("�L�n������", Font.PLAIN, 20));
				note.setBounds(93, 55, 387, 23);
				contentPane.add(note);
				
				JButton colseButton = new JButton("�T�{");
				colseButton.setBackground(SystemColor.controlHighlight);
				colseButton.setFont(new Font("�L�n������", Font.PLAIN, 18));
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
				JLabel note = new JLabel("�R�����ѡI�нT�{��J�O�_���~�C");
				note.setFont(new Font("�L�n������", Font.PLAIN, 20));
				note.setBounds(50, 55, 387, 23);
				contentPane.add(note);
				
				JButton colseButton = new JButton("�T�{");
				colseButton.setBackground(SystemColor.controlHighlight);
				colseButton.setFont(new Font("�L�n������", Font.PLAIN, 18));
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
