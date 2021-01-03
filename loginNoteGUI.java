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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class loginNoteGUI extends JFrame {

private JPanel contentPane;
	
	public loginNoteGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //�I�������ɥu�����ثe����
		setBounds(100, 100, 410, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
		
	
	public void run(boolean x) {
		try {
			loginNoteGUI frame = new loginNoteGUI();
			frame.setVisible(true);
			if (x == true) {
				JLabel note = new JLabel("�n�J���\�I");
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
			else{
				JLabel note = new JLabel("�n�J���ѡI�нT�{�b���K�X�O�_���~�C");
				note.setFont(new Font("�L�n������", Font.PLAIN, 20));
				note.setBounds(32, 55, 387, 23);
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
