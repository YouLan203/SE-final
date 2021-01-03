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

public class GradeTXTNoteGUI extends JFrame {

	private JPanel contentPane;
	public GradeTXTNoteGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //�I�������ɥu�����ثe����
		setBounds(100, 100, 410, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
		
	
	public void run(boolean x) {
		try {
			GradeTXTNoteGUI frame = new GradeTXTNoteGUI();
			frame.setVisible(true);
			frame.setVisible(true);
			if (x == true) {
				
				JLabel notePath = new JLabel("�w�إ��ɮ׸��|�GC:\\StudentGradeSystem\\GradeFile");
				notePath.setFont(new Font("�L�n������", Font.PLAIN, 16));
				notePath.setBounds(5, 65, 387, 23);
				contentPane.add(notePath,0);
				
				JLabel note = new JLabel("�إߦ��\�I");
				note.setFont(new Font("�L�n������", Font.PLAIN, 20));
				note.setBounds(150, 25, 387, 23);
				contentPane.add(note,0);
				
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
				frame.getContentPane().add(notePath);
				frame.getContentPane().add(colseButton);
			}
			else{
				JLabel note = new JLabel("�s�إ��ѡI");
				note.setFont(new Font("�L�n������", Font.PLAIN, 20));
				note.setBounds(150, 55, 387, 23);
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
