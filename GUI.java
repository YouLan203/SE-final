package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import finalProject.Account;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.javafx.tk.Toolkit;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Panel;
public class GUI extends JFrame{
	
	private JPanel contentPane;
	public GUI() {
		
		setTitle("高燕大學生成績管理系統");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.repaint();
		
		JLabel title = new JLabel("高燕大學生成績管理系統");
		title.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		title.setBounds(120, 120, 339, 35);
		contentPane.add(title);
		title.repaint();
		
		JLabel account = new JLabel("帳號：");
		account.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		account.setBounds(120, 162, 71, 23);
		contentPane.add(account);
		account.repaint();
		
		JLabel password = new JLabel("密碼：");
		password.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		password.setBounds(120, 200, 71, 23);
		contentPane.add(password);
		password.repaint();
		
		JLabel identity = new JLabel("身分：");
		identity.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		identity.setBounds(120, 236, 60, 23);
		contentPane.add(identity);
		identity.repaint();
		
		JTextField accountIn = new JTextField();
		accountIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		accountIn.setBounds(180, 162, 279, 24);
		contentPane.add(accountIn);
		accountIn.setColumns(10);
		accountIn.repaint();
		
		JPasswordField passwordIn = new JPasswordField();
		passwordIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		passwordIn.setBounds(180, 199, 279, 24);
		contentPane.add(passwordIn);
		passwordIn.repaint();
		
		String[] option = new String[4];
		option[0] = " ";
		option[1] = "管理員";
		option[2] = "教授";
		option[3] = "學生";
		JComboBox identityIn = new JComboBox(option);
		identityIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		identityIn.setBackground(SystemColor.controlHighlight);
		identityIn.setBounds(180, 235, 279, 29);
		contentPane.add(identityIn);
		identityIn.repaint();
		
		JButton login = new JButton("登入");
		login.setBackground(SystemColor.controlHighlight);
		login.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		login.setBounds(482, 160, 124, 63);
		contentPane.add(login);
		login.repaint();
		menu callMenu = new menu(contentPane, accountIn, passwordIn, identityIn);
		login.addActionListener(callMenu);
	}
	
	public void run() {
		try {
			GUI frame = new GUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class menu implements ActionListener{
		
		private JTextField accountIN;
		private JPasswordField passwordIN;
		private JComboBox identityIN;
		private JPanel login;
		
		public menu(JPanel login, JTextField account, JPasswordField password, JComboBox identity){
			this.login = login;
			this.accountIN = account;
			this.passwordIN = password;
			this.identityIN = identity;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String account = accountIN.getText();
			
			char[] password_loginWord = passwordIN.getPassword();
			String password = "";
			for (int i = 0; i < password_loginWord.length; i++) {
				 password +=  password_loginWord[i];
			}
			
			int identity = identityIN.getSelectedIndex();
			String[] option = new String[4];
			option[0] = " ";
			option[1] = "管理員";
			option[2] = "教授";
			option[3] = "學生";
			
			Account client = new Account(account, password, option[identity]); //建立Account物件
			boolean check = client.logIn();
			
			loginNoteGUI done = new loginNoteGUI();
			done.run(check);
			
			if (check == true) {
				contentPane.setVisible(false);
				
				JPanel menuPane = new JPanel();
				menuPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(menuPane);
				menuPane.setLayout(null);
				menuPane.setVisible(true);
				menuPane.repaint();
				
				JPanel ViewPanel = new JPanel();
				ViewPanel.setBounds(166, 63, 503, 357);
				menuPane.add(ViewPanel,0);
				ViewPanel.removeAll();
				ViewPanel.setLayout(null);
				ViewPanel.setVisible(true);
				ViewPanel.repaint();
				
				JLabel word_login = new JLabel("歡迎！" + client.account + "：");
				word_login.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				word_login.setBounds(15, 15, 639, 43);
				menuPane.add(word_login);
				word_login.repaint();
				
				JLabel menu = new JLabel("功能選單");
				menu.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				menu.setBounds(15, 63, 136, 23);
				menuPane.add(menu);
				menu.repaint();
				
				JButton accountManager = new JButton("帳號管理");
				accountManager.setBackground(SystemColor.controlHighlight);
				accountManager.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				accountManager.setBounds(15, 101, 136, 31);
				menuPane.add(accountManager);
				accountManager.repaint();
				AccountManager callMenu = new AccountManager(ViewPanel, client, identityIN);
				accountManager.addActionListener(callMenu);
				
				JButton lessonManager = new JButton("課程管理");
				lessonManager.setBackground(SystemColor.controlHighlight);
				lessonManager.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				lessonManager.setBounds(15, 163, 136, 31);
				menuPane.add(lessonManager);
				lessonManager.repaint();
				LessonManager lessonmenu = new LessonManager(ViewPanel, client, identityIN);
				lessonManager.addActionListener(lessonmenu);
				
				JButton gradeManager = new JButton("成績管理");
				gradeManager.setBackground(SystemColor.controlHighlight);
				gradeManager.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				gradeManager.setBounds(15, 225, 136, 31);
				menuPane.add(gradeManager);
				gradeManager.repaint();
				GradeManager grademenu = new GradeManager(ViewPanel, client, identityIN);
				gradeManager.addActionListener(grademenu);
				
				JButton logout = new JButton("登出");
				logout.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				logout.setBackground(SystemColor.controlHighlight);
				logout.setBounds(24, 352, 118, 31);
				menuPane.add(logout);
				logout.repaint();
				logout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LogoutGUI OUT = new LogoutGUI();
						OUT.run();
						dispose();
					};
				});
			}
		}
	}
	
	class AccountManager implements ActionListener{
			
			private Account client;
			private JPanel view;
			private JComboBox identityIN;
			
			public AccountManager(JPanel view, Account client, JComboBox identity){
				this.view = view;
				this.client = client;
				this.identityIN = identity;
			}
			
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int identity = identityIN.getSelectedIndex();
				String[] option = new String[4];
				option[0] = " ";
				option[1] = "管理員";
				option[2] = "教授";
				option[3] = "學生";
				
				if (client.identity.equals(option[1])) {
					Manager user = (Manager)client.changeType(option[1]);
					view.removeAll();
					view.repaint();
					
					JButton passwordChange = new JButton("修改密碼");
					passwordChange.setBackground(SystemColor.controlHighlight);
					passwordChange.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					passwordChange.setBounds(176, 62, 153, 31);
					view.add(passwordChange);
					passwordChange.repaint();
					ChangePasswordPanel ToChange = new ChangePasswordPanel(view, user);
					passwordChange.addActionListener(ToChange);
					
					JButton accountManager = new JButton("管理用戶帳號");
					accountManager.setBackground(SystemColor.controlHighlight);
					accountManager.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					accountManager.setBounds(176, 136, 153, 31);
					view.add(accountManager);
					accountManager.repaint();
					ManageAccountPanel ToManage = new ManageAccountPanel(view, user);
					accountManager.addActionListener(ToManage);
					
					JButton deleteAccount = new JButton("刪除帳號");
					deleteAccount.setBackground(SystemColor.controlHighlight);
					deleteAccount.setForeground(Color.RED);
					deleteAccount.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					deleteAccount.setBounds(176, 210, 153, 31);
					view.add(deleteAccount);
					deleteAccount.repaint();
					DeleteAccountPanel delete = new DeleteAccountPanel(view, user);
					deleteAccount.addActionListener(delete);
				}
				
				else if(client.identity.equals(option[2])) {
					Professor user = (Professor)client.changeType(option[2]);
					view.removeAll();
					view.repaint();
					
					JLabel changePasswordTitle = new JLabel("修改密碼");
					changePasswordTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
					changePasswordTitle.setBounds(200, 58, 118, 23);
					view.add(changePasswordTitle);
					changePasswordTitle.repaint();
					
					JLabel oldOne = new JLabel("舊密碼：");
					oldOne.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					oldOne.setBounds(111, 103, 81, 23);
					view.add(oldOne);
					oldOne.repaint();
					
					JLabel newOne = new JLabel("新密碼：");
					newOne.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					newOne.setBounds(111, 141, 81, 23);
					view.add(newOne);
					newOne.repaint();
					
					JLabel lblNewLabel = new JLabel("確認新密碼：");
					lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					lblNewLabel.setBounds(70, 179, 127, 23);
					view.add(lblNewLabel);
					lblNewLabel.repaint();
					
					JTextField oldIn = new JTextField();
					oldIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					oldIn.setBounds(191, 104, 171, 24);
					view.add(oldIn);
					oldIn.setColumns(10);
					oldIn.repaint();
					
					JPasswordField newIn = new JPasswordField();
					newIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					newIn.setBounds(191, 142, 171, 24);
					view.add(newIn);
					newIn.repaint();
					
					JPasswordField checkNew = new JPasswordField();
					checkNew.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					checkNew.setBounds(191, 180, 171, 24);
					view.add(checkNew);
					checkNew.repaint();
					
					JButton changeIt = new JButton("確認修改");
					changeIt.setBackground(SystemColor.controlHighlight);
					changeIt.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					changeIt.setBounds(276, 221, 111, 31);
					view.add(changeIt);
					changeIt.repaint();
					ChangePasswordPro change = new ChangePasswordPro(user, oldIn, newIn, checkNew);
					changeIt.addActionListener(change);
				}
				
				else if(client.identity.equals(option[3])) {
					Student user = (Student)client.changeType(option[3]);
					view.removeAll();
					view.repaint();
					
					JLabel changePasswordTitle = new JLabel("修改密碼");
					changePasswordTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
					changePasswordTitle.setBounds(200, 58, 118, 23);
					view.add(changePasswordTitle);
					changePasswordTitle.repaint();
					
					JLabel oldOne = new JLabel("舊密碼：");
					oldOne.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					oldOne.setBounds(111, 103, 81, 23);
					view.add(oldOne);
					oldOne.repaint();
					
					JLabel newOne = new JLabel("新密碼：");
					newOne.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					newOne.setBounds(111, 141, 81, 23);
					view.add(newOne);
					newOne.repaint();
					
					JLabel lblNewLabel = new JLabel("確認新密碼：");
					lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					lblNewLabel.setBounds(70, 179, 127, 23);
					view.add(lblNewLabel);
					lblNewLabel.repaint();
					
					JTextField oldIn = new JTextField();
					oldIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					oldIn.setBounds(191, 104, 171, 24);
					view.add(oldIn);
					oldIn.setColumns(10);
					oldIn.repaint();
					
					JPasswordField newIn = new JPasswordField();
					newIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					newIn.setBounds(191, 142, 171, 24);
					view.add(newIn);
					newIn.repaint();
					
					JPasswordField checkNew = new JPasswordField();
					checkNew.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					checkNew.setBounds(191, 180, 171, 24);
					view.add(checkNew);
					checkNew.repaint();
					
					JButton changeIt = new JButton("確認修改");
					changeIt.setBackground(SystemColor.controlHighlight);
					changeIt.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					changeIt.setBounds(276, 221, 111, 31);
					view.add(changeIt);
					changeIt.repaint();
					ChangePasswordStu change = new ChangePasswordStu(user, oldIn, newIn, checkNew);
					changeIt.addActionListener(change);
				}
			}
	}
	
	class ChangePasswordPanel implements ActionListener{
		
		private Manager client;
		private JPanel view;
		
		public ChangePasswordPanel(JPanel view, Manager client){
			this.view = view;
			this.client = client;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.removeAll();
			view.repaint();
			
			JLabel changePasswordTitle = new JLabel("修改密碼");
			changePasswordTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			changePasswordTitle.setBounds(200, 58, 118, 23);
			view.add(changePasswordTitle);
			changePasswordTitle.repaint();
			
			JLabel oldOne = new JLabel("舊密碼：");
			oldOne.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			oldOne.setBounds(111, 103, 81, 23);
			view.add(oldOne);
			oldOne.repaint();
			
			JLabel newOne = new JLabel("新密碼：");
			newOne.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			newOne.setBounds(111, 141, 81, 23);
			view.add(newOne);
			newOne.repaint();
			
			JLabel lblNewLabel = new JLabel("確認新密碼：");
			lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			lblNewLabel.setBounds(70, 179, 127, 23);
			view.add(lblNewLabel);
			lblNewLabel.repaint();
			
			JTextField oldIn = new JTextField();
			oldIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			oldIn.setBounds(191, 104, 171, 24);
			view.add(oldIn);
			oldIn.setColumns(10);
			oldIn.repaint();
			
			JPasswordField newIn = new JPasswordField();
			newIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newIn.setBounds(191, 142, 171, 24);
			view.add(newIn);
			newIn.repaint();
			
			JPasswordField checkNew = new JPasswordField();
			checkNew.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			checkNew.setBounds(191, 180, 171, 24);
			view.add(checkNew);
			checkNew.repaint();
			
			JButton changeIt = new JButton("確認修改");
			changeIt.setBackground(SystemColor.controlHighlight);
			changeIt.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			changeIt.setBounds(276, 221, 111, 31);
			view.add(changeIt);
			changeIt.repaint();
			ChangePasswordManager change = new ChangePasswordManager(client, oldIn, newIn, checkNew);
			changeIt.addActionListener(change);
		}
	}
	
	class ChangePasswordManager implements ActionListener{
			
			private Manager user;
			private JTextField passwordOldIN;
			private JPasswordField passwordNewIN;
			private JPasswordField passwordCheckIN;
			
			public ChangePasswordManager(Manager user, JTextField passwordOld, JPasswordField passwordNew, JPasswordField passwordCheck){
				this.user = user;
				this.passwordOldIN = passwordOld;
				this.passwordNewIN = passwordNew;
				this.passwordCheckIN = passwordCheck;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String passwordOld = passwordOldIN.getText();
				
				char[] password_new = passwordNewIN.getPassword();
				String passwordNew = "";
				for (int i = 0; i < password_new.length; i++) {
					 passwordNew +=  password_new[i];
				}
				
				char[] password_checkNew = passwordCheckIN.getPassword();
				String passwordCheck = "";
				for (int i = 0; i < password_checkNew.length; i++) {
					 passwordCheck +=  password_checkNew[i];
				}
				
				if (passwordOld.equals(user.password) && passwordCheck.equals(passwordNew)) {
					user.password = passwordNew;
					boolean gogogo = user.changeFile();
					ChangeNoteGUI okgogo = new ChangeNoteGUI();
					okgogo.run(gogogo);
					dispose();
					
				}
				else {
					ChangeNoteGUI okgogo = new ChangeNoteGUI();
					okgogo.run(false);
					
					passwordOldIN.setText("");
					passwordNewIN.setText("");
					passwordCheckIN.setText("");
				}
			}
	}
	
	class ChangePasswordPro implements ActionListener{
		
		private Professor user;
		private JTextField passwordOldIN;
		private JPasswordField passwordNewIN;
		private JPasswordField passwordCheckIN;
		
		public ChangePasswordPro(Professor user, JTextField passwordOld, JPasswordField passwordNew, JPasswordField passwordCheck){
			this.user = user;
			this.passwordOldIN = passwordOld;
			this.passwordNewIN = passwordNew;
			this.passwordCheckIN = passwordCheck;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String passwordOld = passwordOldIN.getText();
			
			char[] password_new = passwordNewIN.getPassword();
			String passwordNew = "";
			for (int i = 0; i < password_new.length; i++) {
				 passwordNew +=  password_new[i];
			}
			
			char[] password_checkNew = passwordCheckIN.getPassword();
			String passwordCheck = "";
			for (int i = 0; i < password_checkNew.length; i++) {
				 passwordCheck +=  password_checkNew[i];
			}
			
			if (passwordOld.equals(user.password) && passwordCheck.equals(passwordNew)) {
				user.password = passwordNew;
				boolean gogogo = user.changeFile();
				ChangeNoteGUI okgogo = new ChangeNoteGUI();
				okgogo.run(gogogo);
				dispose();
				
			}
			else {
				ChangeNoteGUI okgogo = new ChangeNoteGUI();
				okgogo.run(false);
				
				passwordOldIN.setText("");
				passwordNewIN.setText("");
				passwordCheckIN.setText("");
			}
		}
	}
	
	class ChangePasswordStu implements ActionListener{
		
		private Student user;
		private JTextField passwordOldIN;
		private JPasswordField passwordNewIN;
		private JPasswordField passwordCheckIN;
		
		public ChangePasswordStu(Student user, JTextField passwordOld, JPasswordField passwordNew, JPasswordField passwordCheck){
			this.user = user;
			this.passwordOldIN = passwordOld;
			this.passwordNewIN = passwordNew;
			this.passwordCheckIN = passwordCheck;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String passwordOld = passwordOldIN.getText();
			
			char[] password_new = passwordNewIN.getPassword();
			String passwordNew = "";
			for (int i = 0; i < password_new.length; i++) {
				 passwordNew +=  password_new[i];
			}
			
			char[] password_checkNew = passwordCheckIN.getPassword();
			String passwordCheck = "";
			for (int i = 0; i < password_checkNew.length; i++) {
				 passwordCheck +=  password_checkNew[i];
			}
			
			if (passwordOld.equals(user.password) && passwordCheck.equals(passwordNew)) {
				user.password = passwordNew;
				boolean gogogo = user.changeFile();
				ChangeNoteGUI okgogo = new ChangeNoteGUI();
				okgogo.run(gogogo);
				dispose();
				
			}
			else {
				ChangeNoteGUI okgogo = new ChangeNoteGUI();
				okgogo.run(false);
				
				passwordOldIN.setText("");
				passwordNewIN.setText("");
				passwordCheckIN.setText("");
			}
		}
	}
	
	class ManageAccountPanel implements ActionListener{
			
			private Manager client;
			private JPanel view;
			
			public ManageAccountPanel(JPanel view, Manager client){
				this.view = view;
				this.client = client;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.removeAll();
				view.repaint();
			
				JLabel ManagerAccountTitle = new JLabel("管理用戶帳號");
				ManagerAccountTitle.setForeground(Color.BLACK);
				ManagerAccountTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				ManagerAccountTitle.setBounds(175, 50, 156, 23);
				view.add(ManagerAccountTitle);
				ManagerAccountTitle.repaint();
				
				JButton buildNewOne = new JButton("建立新帳號");
				buildNewOne.setBackground(SystemColor.controlHighlight);
				buildNewOne.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildNewOne.setBounds(53, 229, 134, 31);
				view.add(buildNewOne);
				buildNewOne.repaint();
				BuildAccountPanel ToBuild = new BuildAccountPanel(view, client);
				buildNewOne.addActionListener(ToBuild);
				
				JLabel accountChoose = new JLabel("請輸入欲修改之帳號：");
				accountChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				accountChoose.setBounds(53, 112, 188, 23);
				view.add(accountChoose);
				accountChoose.repaint();
				
				JTextField AccounttextField = new JTextField();
				AccounttextField.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				AccounttextField.setBounds(233, 110, 232, 31);
				AccounttextField.setText("");
				view.add(AccounttextField);
				AccounttextField.setColumns(10);
				AccounttextField.repaint();
				
				JLabel identity = new JLabel("請選擇修改帳號之身分：");
				identity.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				identity.setBounds(53, 170, 203, 23);
				view.add(identity);
				identity.repaint();
				
				String[] option_identity = new String[3];
				option_identity[0] = " ";
				option_identity[1] = "教授";
				option_identity[2] = "學生";
				JComboBox IdentitycomboBox = new JComboBox(option_identity);
				IdentitycomboBox.setBackground(SystemColor.controlHighlight);
				IdentitycomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				IdentitycomboBox.setBounds(253, 167, 212, 29);
				view.add(IdentitycomboBox);
				IdentitycomboBox.repaint();
				
				JButton makeDelete = new JButton("刪除");
				makeDelete.setBackground(SystemColor.controlHighlight);
				makeDelete.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				makeDelete.setForeground(Color.RED);
				makeDelete.setBounds(197, 229, 111, 31);
				view.add(makeDelete);
				makeDelete.repaint();
				DeleteAccount delete = new DeleteAccount(view, client, AccounttextField, IdentitycomboBox);
				makeDelete.addActionListener(delete);
				
				JButton makeChange = new JButton("修改");
				makeChange.setBackground(SystemColor.controlHighlight);
				makeChange.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				makeChange.setBounds(318, 229, 111, 31);
				view.add(makeChange);
				makeChange.repaint();
				ChangeAccount change = new ChangeAccount(view, client, AccounttextField, IdentitycomboBox);
				makeChange.addActionListener(change);
			}
	}
	
	class BuildAccountPanel implements ActionListener{
		
		private Manager client;
		private JPanel view;
		
		public BuildAccountPanel(JPanel view, Manager client){
			this.view = view;
			this.client = client;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.removeAll();
			view.repaint();
		
			JLabel buildAccountPanelTitle = new JLabel("建立新帳號");
			buildAccountPanelTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			buildAccountPanelTitle.setBounds(189, 16, 132, 23);
			view.add(buildAccountPanelTitle);
			buildAccountPanelTitle.repaint();
			
			JButton back = new JButton("回上一頁");
			back.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			back.setBackground(SystemColor.controlHighlight);
			back.setBounds(106, 268, 111, 31);
			view.add(back);
			back.repaint();
			ManageAccountPanel ToManage = new ManageAccountPanel(view, client);
			back.addActionListener(ToManage);
			
			JLabel choose = new JLabel("請選擇帳號種類：");
			choose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			choose.setBounds(49, 55, 177, 23);
			view.add(choose);
			choose.repaint();
			
			String[] option = new String[4];
			option[0] = " ";
			option[1] = "管理員";
			option[2] = "教授";
			option[3] = "學生";
			JComboBox AccountKind = new JComboBox(option);
			AccountKind.setBackground(SystemColor.controlHighlight);
			AccountKind.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			AccountKind.setBounds(210, 54, 230, 29);
			view.add(AccountKind);
			AccountKind.repaint();
			JLabel AccountSet = new JLabel("設定帳號：");
			AccountSet.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			AccountSet.setBounds(106, 117, 111, 23);
			view.add(AccountSet);
			
			JTextField newAccountSet = new JTextField();
			newAccountSet.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newAccountSet.setBounds(210, 116, 230, 24);
			view.add(newAccountSet);
			newAccountSet.setColumns(10);
			newAccountSet.repaint();
			
			JLabel PasswordSet = new JLabel("設定密碼：");
			PasswordSet.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			PasswordSet.setBounds(106, 160, 111, 23);
			view.add(PasswordSet);
			PasswordSet.repaint();
			
			JPasswordField newPasswordSet = new JPasswordField();
			newPasswordSet.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newPasswordSet.setBounds(210, 159, 230, 24);
			view.add(newPasswordSet);
			newPasswordSet.repaint();
			
			JLabel PasswordCheck = new JLabel("確認密碼：");
			PasswordCheck.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			PasswordCheck.setBounds(106, 203, 111, 23);
			view.add(PasswordCheck);
			PasswordCheck.repaint();
			
			JPasswordField newPasswordCheck = new JPasswordField();
			newPasswordCheck.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newPasswordCheck.setBounds(210, 202, 230, 24);
			view.add(newPasswordCheck);
			newPasswordCheck.repaint();
			
			JButton setNewAccount = new JButton("建立");
			setNewAccount.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			setNewAccount.setBackground(SystemColor.controlHighlight);
			setNewAccount.setBounds(280, 268, 111, 31);
			view.add(setNewAccount);
			setNewAccount.repaint();
			BuildAccount ToBuild = new BuildAccount(view, client, newAccountSet, newPasswordSet, newPasswordCheck, AccountKind);
			setNewAccount.addActionListener(ToBuild);
		}
	}
	
	class BuildAccount implements ActionListener{
			
			private Manager client;
			private JPanel view;
			private JTextField newAccountSet;
			private JPasswordField newPasswordSet;
			private JPasswordField newPasswordCheck;
			private JComboBox AccountKind;
			
			public BuildAccount(JPanel view, Manager client, JTextField newAccountSet, JPasswordField newPasswordSet, JPasswordField newPasswordCheck, JComboBox AccountKind){
				this.view = view;
				this.client = client;
				this.newAccountSet = newAccountSet;
				this.newPasswordSet = newPasswordSet;
				this.newPasswordCheck = newPasswordCheck;
				this.AccountKind = AccountKind;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String AccountSetIT = newAccountSet.getText();
				
				char[] password_set = newPasswordSet.getPassword();
				String passwordSet = "";
				for (int i = 0; i < password_set.length; i++) {
					 passwordSet +=  password_set[i];
				}
				
				char[] password_checkSet = newPasswordCheck.getPassword();
				String passwordCheckSet = "";
				for (int i = 0; i < password_checkSet.length; i++) {
					 passwordCheckSet +=  password_checkSet[i];
				}
				
				String[] option = new String[4];
				option[0] = " ";
				option[1] = "管理員";
				option[2] = "教授";
				option[3] = "學生";
				
				File checkFile = new File("C:\\StudentGradeSystem\\AccountFile\\" + AccountSetIT + ".txt");
				if (checkFile.exists()) {
					BuildNoteGUI OKdone = new BuildNoteGUI();
					OKdone.run(false);
					
					newAccountSet.setText("");
					newPasswordSet.setText("");
					newPasswordCheck.setText("");
				}
				if (!checkFile.exists() && !AccountSetIT.equals("") && passwordSet.equals(passwordCheckSet)) {
					if (AccountKind.getSelectedIndex() == 1) {
						Manager newOne = new Manager(AccountSetIT, passwordSet, option[1]);
						boolean haveDone = newOne.buildFile();
						BuildNoteGUI OKdone = new BuildNoteGUI();
						OKdone.run(haveDone);
						
						newAccountSet.setText("");
						newPasswordSet.setText("");
						newPasswordCheck.setText("");
					}
					else if (AccountKind.getSelectedIndex() == 2) {
						view.removeAll();
						view.repaint();
						
						JLabel setAccountPanelTitle = new JLabel("設定帳號資訊");
						setAccountPanelTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
						setAccountPanelTitle.setBounds(185, 57, 157, 23);
						view.add(setAccountPanelTitle);
						setAccountPanelTitle.repaint();
						
						JLabel NameSet = new JLabel("設定姓名：");
						NameSet.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
						NameSet.setBounds(90, 131, 111, 23);
						view.add(NameSet);
						NameSet.repaint();
						
						JTextField newNameSet = new JTextField();
						newNameSet.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						newNameSet.setBounds(194, 130, 230, 24);
						view.add(newNameSet);
						newNameSet.setColumns(10);
						newNameSet.repaint();
						
						JButton back = new JButton("回上一頁");
						back.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						back.setBackground(SystemColor.controlHighlight);
						back.setBounds(120, 206, 111, 31);
						view.add(back);
						back.repaint();
						BuildAccountPanel ToBuild = new BuildAccountPanel(view, client);
						back.addActionListener(ToBuild);
						
						JButton setNewProfessor = new JButton("完成");
						setNewProfessor.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						setNewProfessor.setBackground(SystemColor.controlHighlight);
						setNewProfessor.setBounds(294, 206, 111, 31);
						view.add(setNewProfessor);
						setNewProfessor.repaint();
						BuildPro Build = new BuildPro(view, client, newAccountSet, newPasswordSet, newPasswordCheck, AccountKind, newNameSet);
						setNewProfessor.addActionListener(Build);
					}
					else if (AccountKind.getSelectedIndex() == 3) {
						view.removeAll();
						view.repaint();
						
						JLabel setAccountPanelTitle = new JLabel("設定帳號資訊");
						setAccountPanelTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
						setAccountPanelTitle.setBounds(185, 57, 157, 23);
						view.add(setAccountPanelTitle);
						setAccountPanelTitle.repaint();
						
						JButton back = new JButton("回上一頁");
						back.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						back.setBackground(SystemColor.controlHighlight);
						back.setBounds(120, 206, 111, 31);
						view.add(back);
						back.repaint();
						BuildAccountPanel ToBuild = new BuildAccountPanel(view, client);
						back.addActionListener(ToBuild);
						
						JLabel NameSet = new JLabel("設定姓名：");
						NameSet.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
						NameSet.setBounds(90, 112, 111, 23);
						view.add(NameSet);
						NameSet.repaint();
						
						JTextField newNameSet = new JTextField();
						newNameSet.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						newNameSet.setBounds(194, 111, 230, 24);
						view.add(newNameSet);
						newNameSet.setColumns(10);
						newNameSet.repaint();
						
						JLabel YearSet = new JLabel("設定入學年：");
						YearSet.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
						YearSet.setBounds(70, 149, 131, 23);
						view.add(YearSet);
						YearSet.repaint();
						
						JTextField newYearSet = new JTextField();
						newYearSet.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						newYearSet.setColumns(10);
						newYearSet.setBounds(194, 148, 230, 24);
						view.add(newYearSet);
						newYearSet.repaint();
						
						JButton setNewStudent = new JButton("完成");
						setNewStudent.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						setNewStudent.setBackground(SystemColor.controlHighlight);
						setNewStudent.setBounds(294, 206, 111, 31);
						view.add(setNewStudent);
						setNewStudent.repaint();
						BuildStu Build = new BuildStu(view, client, newAccountSet, newPasswordSet, newPasswordCheck, AccountKind, newNameSet, newYearSet);
						setNewStudent.addActionListener(Build);
					}
				}
			}
	}
	
	class BuildPro implements ActionListener{
		
		private Manager client;
		private JPanel view;
		private JTextField newAccountSet;
		private JPasswordField newPasswordSet;
		private JPasswordField newPasswordCheck;
		private JComboBox AccountKind;
		private JTextField newNameSet;
		
		public BuildPro(JPanel view, Manager client, JTextField newAccountSet, JPasswordField newPasswordSet, JPasswordField newPasswordCheck, JComboBox AccountKind, JTextField newNameSet){
			this.view = view;
			this.client = client;
			this.newAccountSet = newAccountSet;
			this.newPasswordSet = newPasswordSet;
			this.newPasswordCheck = newPasswordCheck;
			this.AccountKind = AccountKind;
			this.newNameSet = newNameSet;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String newName = newNameSet.getText();
			String AccountSetIT = newAccountSet.getText();
			
			char[] password_set = newPasswordSet.getPassword();
			String passwordSet = "";
			for (int i = 0; i < password_set.length; i++) {
				 passwordSet +=  password_set[i];
			}
			
			char[] password_checkSet = newPasswordCheck.getPassword();
			String passwordCheckSet = "";
			for (int i = 0; i < password_checkSet.length; i++) {
				 passwordCheckSet +=  password_checkSet[i];
			}
			ArrayList<ArrayList<String>> lessonProfessor = new ArrayList<ArrayList<String>>();
			
			String[] option = new String[4];
			option[0] = " ";
			option[1] = "管理員";
			option[2] = "教授";
			option[3] = "學生";
			
			Professor newOne = new Professor(AccountSetIT, passwordSet, option[2], newName, lessonProfessor);
			boolean haveDone = newOne.buildFile();
			BuildNoteGUI OKdone = new BuildNoteGUI();
			OKdone.run(haveDone);
			
			newNameSet.setText("");
			newAccountSet.setText("");
			newPasswordSet.setText("");
			newPasswordCheck.setText("");
			
			view.removeAll();
			view.repaint();
		
			JLabel buildAccountPanelTitle = new JLabel("建立新帳號");
			buildAccountPanelTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			buildAccountPanelTitle.setBounds(189, 16, 132, 23);
			view.add(buildAccountPanelTitle);
			buildAccountPanelTitle.repaint();
			
			JButton back = new JButton("回上一頁");
			back.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			back.setBackground(SystemColor.controlHighlight);
			back.setBounds(106, 268, 111, 31);
			view.add(back);
			back.repaint();
			ManageAccountPanel ToManage = new ManageAccountPanel(view, client);
			back.addActionListener(ToManage);
			
			JLabel choose = new JLabel("請選擇帳號種類：");
			choose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			choose.setBounds(49, 55, 177, 23);
			view.add(choose);
			choose.repaint();
			
			JComboBox AccountKind = new JComboBox(option);
			AccountKind.setBackground(SystemColor.controlHighlight);
			AccountKind.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			AccountKind.setBounds(210, 54, 230, 29);
			view.add(AccountKind);
			AccountKind.repaint();
			JLabel AccountSet = new JLabel("設定帳號：");
			AccountSet.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			AccountSet.setBounds(106, 117, 111, 23);
			view.add(AccountSet);
			
			JTextField newAccountSet = new JTextField();
			newAccountSet.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newAccountSet.setBounds(210, 116, 230, 24);
			view.add(newAccountSet);
			newAccountSet.setColumns(10);
			newAccountSet.repaint();
			
			JLabel PasswordSet = new JLabel("設定密碼：");
			PasswordSet.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			PasswordSet.setBounds(106, 160, 111, 23);
			view.add(PasswordSet);
			PasswordSet.repaint();
			
			JPasswordField newPasswordSet = new JPasswordField();
			newPasswordSet.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newPasswordSet.setBounds(210, 159, 230, 24);
			view.add(newPasswordSet);
			newPasswordSet.repaint();
			
			JLabel PasswordCheck = new JLabel("確認密碼：");
			PasswordCheck.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			PasswordCheck.setBounds(106, 203, 111, 23);
			view.add(PasswordCheck);
			PasswordCheck.repaint();
			
			JPasswordField newPasswordCheck = new JPasswordField();
			newPasswordCheck.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newPasswordCheck.setBounds(210, 202, 230, 24);
			view.add(newPasswordCheck);
			newPasswordCheck.repaint();
			
			JButton setNewAccount = new JButton("建立");
			setNewAccount.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			setNewAccount.setBackground(SystemColor.controlHighlight);
			setNewAccount.setBounds(280, 268, 111, 31);
			view.add(setNewAccount);
			setNewAccount.repaint();
			BuildAccount ToBuild = new BuildAccount(view, client, newAccountSet, newPasswordSet, newPasswordCheck, AccountKind);
			setNewAccount.addActionListener(ToBuild);
		}
	}
	
	class BuildStu implements ActionListener{
		
		private Manager client;
		private JPanel view;
		private JTextField newAccountSet;
		private JPasswordField newPasswordSet;
		private JPasswordField newPasswordCheck;
		private JComboBox AccountKind;
		private JTextField newNameSet;
		private JTextField newYearSet;
		
		public BuildStu(JPanel view, Manager client, JTextField newAccountSet, JPasswordField newPasswordSet, JPasswordField newPasswordCheck, JComboBox AccountKind, JTextField newNameSet, JTextField newYearSet){
			this.view = view;
			this.client = client;
			this.newAccountSet = newAccountSet;
			this.newPasswordSet = newPasswordSet;
			this.newPasswordCheck = newPasswordCheck;
			this.AccountKind = AccountKind;
			this.newNameSet = newNameSet;
			this.newYearSet = newYearSet;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String newName = newNameSet.getText();
			String newYear = newYearSet.getText();
			String AccountSetIT = newAccountSet.getText();
			
			char[] password_set = newPasswordSet.getPassword();
			String passwordSet = "";
			for (int i = 0; i < password_set.length; i++) {
				 passwordSet +=  password_set[i];
			}
			
			char[] password_checkSet = newPasswordCheck.getPassword();
			String passwordCheckSet = "";
			for (int i = 0; i < password_checkSet.length; i++) {
				 passwordCheckSet +=  password_checkSet[i];
			}
			ArrayList<ArrayList<String>> lessonProfessor = new ArrayList<ArrayList<String>>();
			
			String[] option = new String[4];
			option[0] = " ";
			option[1] = "管理員";
			option[2] = "教授";
			option[3] = "學生";
			
			Student newOne = new Student(AccountSetIT, passwordSet, option[3], newName, newYear,lessonProfessor);
			boolean haveDone = newOne.buildFile();
			BuildNoteGUI OKdone = new BuildNoteGUI();
			OKdone.run(haveDone);
			
			newNameSet.setText("");
			newYearSet.setText("");
			newAccountSet.setText("");
			newPasswordSet.setText("");
			newPasswordCheck.setText("");
			
			view.removeAll();
			view.repaint();
		
			JLabel buildAccountPanelTitle = new JLabel("建立新帳號");
			buildAccountPanelTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			buildAccountPanelTitle.setBounds(189, 16, 132, 23);
			view.add(buildAccountPanelTitle);
			buildAccountPanelTitle.repaint();
			
			JButton back = new JButton("回上一頁");
			back.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			back.setBackground(SystemColor.controlHighlight);
			back.setBounds(106, 268, 111, 31);
			view.add(back);
			back.repaint();
			ManageAccountPanel ToManage = new ManageAccountPanel(view, client);
			back.addActionListener(ToManage);
			
			JLabel choose = new JLabel("請選擇帳號種類：");
			choose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			choose.setBounds(49, 55, 177, 23);
			view.add(choose);
			choose.repaint();
			
			JComboBox AccountKind = new JComboBox(option);
			AccountKind.setBackground(SystemColor.controlHighlight);
			AccountKind.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			AccountKind.setBounds(210, 54, 230, 29);
			view.add(AccountKind);
			AccountKind.repaint();
			JLabel AccountSet = new JLabel("設定帳號：");
			AccountSet.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			AccountSet.setBounds(106, 117, 111, 23);
			view.add(AccountSet);
			
			JTextField newAccountSet = new JTextField();
			newAccountSet.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newAccountSet.setBounds(210, 116, 230, 24);
			view.add(newAccountSet);
			newAccountSet.setColumns(10);
			newAccountSet.repaint();
			
			JLabel PasswordSet = new JLabel("設定密碼：");
			PasswordSet.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			PasswordSet.setBounds(106, 160, 111, 23);
			view.add(PasswordSet);
			PasswordSet.repaint();
			
			JPasswordField newPasswordSet = new JPasswordField();
			newPasswordSet.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newPasswordSet.setBounds(210, 159, 230, 24);
			view.add(newPasswordSet);
			newPasswordSet.repaint();
			
			JLabel PasswordCheck = new JLabel("確認密碼：");
			PasswordCheck.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			PasswordCheck.setBounds(106, 203, 111, 23);
			view.add(PasswordCheck);
			PasswordCheck.repaint();
			
			JPasswordField newPasswordCheck = new JPasswordField();
			newPasswordCheck.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newPasswordCheck.setBounds(210, 202, 230, 24);
			view.add(newPasswordCheck);
			newPasswordCheck.repaint();
			
			JButton setNewAccount = new JButton("建立");
			setNewAccount.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			setNewAccount.setBackground(SystemColor.controlHighlight);
			setNewAccount.setBounds(280, 268, 111, 31);
			view.add(setNewAccount);
			setNewAccount.repaint();
			BuildAccount ToBuild = new BuildAccount(view, client, newAccountSet, newPasswordSet, newPasswordCheck, AccountKind);
			setNewAccount.addActionListener(ToBuild);
		}
	}
	
	class DeleteAccount implements ActionListener{
		
		private Manager client;
		private JPanel view;
		private JTextField AccounttextField;
		private JComboBox IdentitycomboBox;
		
		public DeleteAccount(JPanel view, Manager client, JTextField AccounttextField, JComboBox IdentitycomboBox){
			this.view = view;
			this.client = client;
			this.AccounttextField = AccounttextField;
			this.IdentitycomboBox = IdentitycomboBox;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String[] option_identity = new String[3];
			option_identity[0] = " ";
			option_identity[1] = "教授";
			option_identity[2] = "學生";
			
			String selectedID = option_identity[IdentitycomboBox.getSelectedIndex()];
			if (selectedID.equals("教授") && !AccounttextField.getText().equals("")) {
				
				Professor clientPro = client.readProFileID(AccounttextField.getText());
				if (clientPro == null) {
					WrongNoteGUI wrongType = new WrongNoteGUI();
					wrongType.run();
				}
				
				else if (!clientPro.identity.equals(selectedID)) {
					WrongNoteGUI wrongType = new WrongNoteGUI();
					wrongType.run();
				}
				else {
					JInternalFrame internalFrameagain = new JInternalFrame("刪除帳號？");
					internalFrameagain.getContentPane().setEnabled(false);
					internalFrameagain.setClosable(true);
					internalFrameagain.setBounds(112, 33, 277, 158);
					view.add(internalFrameagain, 0);
					internalFrameagain.getContentPane().setLayout(null);
					internalFrameagain.repaint();
					
					JLabel checkWord = new JLabel("確定要刪除帳號？");
					checkWord.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					checkWord.setBounds(49, 30, 160, 23);
					internalFrameagain.getContentPane().add(checkWord);
					checkWord.repaint();
					
					JButton Yes = new JButton("是");
					Yes.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					Yes.setBackground(SystemColor.controlHighlight);
					Yes.setBounds(47, 68, 65, 31);
					internalFrameagain.getContentPane().add(Yes);
					Yes.repaint();
					ActionListener chooseYes = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							boolean deleteOkkkk = clientPro.deleteFile();
							DeleteNote_MAGUI okgogogogogo = new DeleteNote_MAGUI();
							okgogogogogo.run(deleteOkkkk);
							internalFrameagain.dispose();
							AccounttextField.setText("");
						};
					};
					Yes.addActionListener(chooseYes);
					
					JButton No = new JButton("否");
					No.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					No.setBackground(SystemColor.controlHighlight);
					No.setBounds(144, 68, 65, 31);
					internalFrameagain.getContentPane().add(No);
					internalFrameagain.setVisible(true);
					No.repaint();
					ActionListener chooseNo = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							internalFrameagain.dispose();
							AccounttextField.setText("");
						};
					};
					No.addActionListener(chooseNo);
				}
			}
			else if (selectedID.equals("學生") && !AccounttextField.getText().equals("")) {
				Student clientStu = client.readStuFileID(AccounttextField.getText());
				if (clientStu == null) {
					WrongNoteGUI wrongType = new WrongNoteGUI();
					wrongType.run();
				}
				
				else if (!clientStu.identity.equals(selectedID)) {
					WrongNoteGUI wrongType = new WrongNoteGUI();
					wrongType.run();
				}
				
				else {
					JInternalFrame internalFrameagain = new JInternalFrame("刪除帳號？");
					internalFrameagain.getContentPane().setEnabled(false);
					internalFrameagain.setClosable(true);
					internalFrameagain.setBounds(112, 33, 277, 158);
					view.add(internalFrameagain, 0);
					internalFrameagain.getContentPane().setLayout(null);
					internalFrameagain.repaint();
					
					JLabel checkWord = new JLabel("確定要刪除帳號？");
					checkWord.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					checkWord.setBounds(49, 30, 160, 23);
					internalFrameagain.getContentPane().add(checkWord);
					checkWord.repaint();
					
					JButton Yes = new JButton("是");
					Yes.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					Yes.setBackground(SystemColor.controlHighlight);
					Yes.setBounds(47, 68, 65, 31);
					internalFrameagain.getContentPane().add(Yes);
					Yes.repaint();
					ActionListener chooseYes = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							boolean deleteOkkkk = clientStu.deleteFile();
							DeleteNote_MAGUI okgogogogogo = new DeleteNote_MAGUI();
							okgogogogogo.run(deleteOkkkk);
							internalFrameagain.dispose();
							AccounttextField.setText("");
						};
					};
					Yes.addActionListener(chooseYes);
					
					JButton No = new JButton("否");
					No.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					No.setBackground(SystemColor.controlHighlight);
					No.setBounds(144, 68, 65, 31);
					internalFrameagain.getContentPane().add(No);
					internalFrameagain.setVisible(true);
					No.repaint();
					ActionListener chooseNo = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							internalFrameagain.dispose();
							AccounttextField.setText("");
						};
					};
					No.addActionListener(chooseNo);
				}
			}
		}
	}
	
	class ChangeAccount implements ActionListener{
		
		private Manager client;
		private JPanel view;
		private JTextField AccounttextField;
		private JComboBox IdentitycomboBox;
		
		public ChangeAccount(JPanel view, Manager client, JTextField AccounttextField, JComboBox IdentitycomboBox){
			this.view = view;
			this.client = client;
			this.AccounttextField = AccounttextField;
			this.IdentitycomboBox = IdentitycomboBox;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String[] option_identity = new String[3];
			option_identity[0] = " ";
			option_identity[1] = "教授";
			option_identity[2] = "學生";
			
			int check_identity_toChange = IdentitycomboBox.getSelectedIndex();
			String toCheck = option_identity[check_identity_toChange];
			
			if (toCheck.equals("教授") && !AccounttextField.getText().equals("")) {
				
				Professor user = client.readProFileID(AccounttextField.getText());
				if (user == null) {
					WrongNoteGUI wrongType = new WrongNoteGUI();
					wrongType.run();
				}
				
				else if (!user.identity.equals(toCheck)) {
					WrongNoteGUI wrongType = new WrongNoteGUI();
					wrongType.run();
				}
				
				else {
					view.removeAll();
					view.repaint();
					
					JLabel changePasswordTitle = new JLabel("修改密碼");
					changePasswordTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
					changePasswordTitle.setBounds(200, 58, 118, 23);
					view.add(changePasswordTitle);
					changePasswordTitle.repaint();
					
					JLabel newOne = new JLabel("新密碼：");
					newOne.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					newOne.setBounds(111, 115, 81, 23);
					view.add(newOne);
					newOne.repaint();
					
					JLabel lblNewLabel = new JLabel("確認新密碼：");
					lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					lblNewLabel.setBounds(70, 165, 127, 23);
					view.add(lblNewLabel);
					lblNewLabel.repaint();
					
					JPasswordField newIn = new JPasswordField();
					newIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					newIn.setBounds(191, 115, 171, 24);
					view.add(newIn);
					newIn.repaint();
					
					JPasswordField checkNew = new JPasswordField();
					checkNew.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					checkNew.setBounds(191, 165, 171, 24);
					view.add(checkNew);
					checkNew.repaint();
					
					JButton changeIt = new JButton("確認修改");
					changeIt.setBackground(SystemColor.controlHighlight);
					changeIt.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					changeIt.setBounds(276, 221, 111, 31);
					view.add(changeIt);
					changeIt.repaint();
					ChangeAccountPro change = new ChangeAccountPro(view, client, user, newIn, checkNew);
					changeIt.addActionListener(change);
					
					JButton back = new JButton("回上一頁");
					back.setBackground(SystemColor.controlHighlight);
					back.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					back.setBounds(121, 221, 111, 31);
					view.add(back);
					back.repaint();
					ManageAccountPanel ToManage = new ManageAccountPanel(view, client);
					back.addActionListener(ToManage);
				}
			}
			
			else if (toCheck.equals("學生") && !AccounttextField.getText().equals("")) {
				Student user = client.readStuFileID(AccounttextField.getText());
				if (user == null) {
					WrongNoteGUI wrongType = new WrongNoteGUI();
					wrongType.run();
				}
				
				else if (!user.identity.equals(toCheck)) {
					WrongNoteGUI wrongType = new WrongNoteGUI();
					wrongType.run();
				}
				
				else {
					view.removeAll();
					view.repaint();
					
					JLabel changePasswordTitle = new JLabel("修改密碼");
					changePasswordTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
					changePasswordTitle.setBounds(200, 58, 118, 23);
					view.add(changePasswordTitle);
					changePasswordTitle.repaint();
					
					JLabel newOne = new JLabel("新密碼：");
					newOne.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					newOne.setBounds(111, 115, 81, 23);
					view.add(newOne);
					newOne.repaint();
					
					JLabel lblNewLabel = new JLabel("確認新密碼：");
					lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					lblNewLabel.setBounds(70, 165, 127, 23);
					view.add(lblNewLabel);
					lblNewLabel.repaint();
					
					JPasswordField newIn = new JPasswordField();
					newIn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					newIn.setBounds(191, 115, 171, 24);
					view.add(newIn);
					newIn.repaint();
					
					JPasswordField checkNew = new JPasswordField();
					checkNew.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					checkNew.setBounds(191, 165, 171, 24);
					view.add(checkNew);
					checkNew.repaint();
					
					JButton changeIt = new JButton("確認修改");
					changeIt.setBackground(SystemColor.controlHighlight);
					changeIt.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					changeIt.setBounds(276, 221, 111, 31);
					view.add(changeIt);
					changeIt.repaint();
					ChangeAccountStu change = new ChangeAccountStu(view, client, user, newIn, checkNew);
					changeIt.addActionListener(change);
					
					JButton back = new JButton("回上一頁");
					back.setBackground(SystemColor.controlHighlight);
					back.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					back.setBounds(121, 221, 111, 31);
					view.add(back);
					back.repaint();
					ManageAccountPanel ToManage = new ManageAccountPanel(view, client);
					back.addActionListener(ToManage);
				}
			}
		}
	}
	
	class ChangeAccountPro implements ActionListener{
			
			private JPanel view;
			private Manager user;
			private Professor client;
			private JPasswordField newIn;
			private JPasswordField checkNew;
			
			public ChangeAccountPro(JPanel view, Manager user, Professor client, JPasswordField newIn, JPasswordField checkNew){
				this.view = view;
				this.client = client;
				this.user = user;
				this.newIn = newIn;
				this.checkNew = checkNew;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				char[] password_new = newIn.getPassword();
				String passwordNew = "";
				for (int i = 0; i < password_new.length; i++) {
					 passwordNew +=  password_new[i];
				}
				
				char[] password_checkNew = checkNew.getPassword();
				String passwordCheck = "";
				for (int i = 0; i < password_checkNew.length; i++) {
					 passwordCheck +=  password_checkNew[i];
				}
				
				if (passwordCheck.equals(passwordNew)) {
					client.password = passwordNew;
					boolean gogogo = client.changeFile();
					ChangeWithoutBooleanNoteGUI okgogo = new ChangeWithoutBooleanNoteGUI();
					okgogo.run(gogogo);
					
					newIn.setText("");
					checkNew.setText("");
					
					view.removeAll();
					view.repaint();
				
					JLabel ManagerAccountTitle = new JLabel("管理用戶帳號");
					ManagerAccountTitle.setForeground(Color.BLACK);
					ManagerAccountTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
					ManagerAccountTitle.setBounds(175, 50, 156, 23);
					view.add(ManagerAccountTitle);
					ManagerAccountTitle.repaint();
					
					JButton buildNewOne = new JButton("建立新帳號");
					buildNewOne.setBackground(SystemColor.controlHighlight);
					buildNewOne.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					buildNewOne.setBounds(53, 229, 134, 31);
					view.add(buildNewOne);
					buildNewOne.repaint();
					BuildAccountPanel ToBuild = new BuildAccountPanel(view, user);
					buildNewOne.addActionListener(ToBuild);
					
					JLabel accountChoose = new JLabel("請輸入欲修改之帳號：");
					accountChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					accountChoose.setBounds(53, 112, 188, 23);
					view.add(accountChoose);
					accountChoose.repaint();
					
					JTextField AccounttextField = new JTextField();
					AccounttextField.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					AccounttextField.setBounds(233, 110, 232, 31);
					AccounttextField.setText("");
					view.add(AccounttextField);
					AccounttextField.setColumns(10);
					AccounttextField.repaint();
					
					JLabel identity = new JLabel("請選擇修改帳號之身分：");
					identity.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					identity.setBounds(53, 170, 203, 23);
					view.add(identity);
					identity.repaint();
					
					String[] option_identity = new String[3];
					option_identity[0] = " ";
					option_identity[1] = "教授";
					option_identity[2] = "學生";
					JComboBox IdentitycomboBox = new JComboBox(option_identity);
					IdentitycomboBox.setBackground(SystemColor.controlHighlight);
					IdentitycomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					IdentitycomboBox.setBounds(253, 167, 212, 29);
					view.add(IdentitycomboBox);
					IdentitycomboBox.repaint();
					
					JButton makeDelete = new JButton("刪除");
					makeDelete.setBackground(SystemColor.controlHighlight);
					makeDelete.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					makeDelete.setForeground(Color.RED);
					makeDelete.setBounds(197, 229, 111, 31);
					view.add(makeDelete);
					makeDelete.repaint();
					DeleteAccount delete = new DeleteAccount(view,user, AccounttextField, IdentitycomboBox);
					makeDelete.addActionListener(delete);
					
					JButton makeChange = new JButton("修改");
					makeChange.setBackground(SystemColor.controlHighlight);
					makeChange.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					makeChange.setBounds(318, 229, 111, 31);
					view.add(makeChange);
					makeChange.repaint();
					ChangeAccount change = new ChangeAccount(view, user, AccounttextField, IdentitycomboBox);
					makeChange.addActionListener(change);
				}
				else {
					ChangeWithoutBooleanNoteGUI okgogo = new ChangeWithoutBooleanNoteGUI();
					okgogo.run(false);
					
					newIn.setText("");
					checkNew.setText("");
				}
			}
	}
	
	class ChangeAccountStu implements ActionListener{
		
		private JPanel view;
		private Manager user;
		private Student client;
		private JPasswordField newIn;
		private JPasswordField checkNew;
		
		public ChangeAccountStu(JPanel view, Manager user, Student client, JPasswordField newIn, JPasswordField checkNew){
			this.view = view;
			this.client = client;
			this.user = user;
			this.newIn = newIn;
			this.checkNew = checkNew;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			char[] password_new = newIn.getPassword();
			String passwordNew = "";
			for (int i = 0; i < password_new.length; i++) {
				 passwordNew +=  password_new[i];
			}
			
			char[] password_checkNew = checkNew.getPassword();
			String passwordCheck = "";
			for (int i = 0; i < password_checkNew.length; i++) {
				 passwordCheck +=  password_checkNew[i];
			}
			
			if (passwordCheck.equals(passwordNew)) {
				client.password = passwordNew;
				boolean gogogo = client.changeFile();
				ChangeWithoutBooleanNoteGUI okgogo = new ChangeWithoutBooleanNoteGUI();
				okgogo.run(gogogo);
				
				newIn.setText("");
				checkNew.setText("");
				
				view.removeAll();
				view.repaint();
			
				JLabel ManagerAccountTitle = new JLabel("管理用戶帳號");
				ManagerAccountTitle.setForeground(Color.BLACK);
				ManagerAccountTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				ManagerAccountTitle.setBounds(175, 50, 156, 23);
				view.add(ManagerAccountTitle);
				ManagerAccountTitle.repaint();
				
				JButton buildNewOne = new JButton("建立新帳號");
				buildNewOne.setBackground(SystemColor.controlHighlight);
				buildNewOne.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildNewOne.setBounds(53, 229, 134, 31);
				view.add(buildNewOne);
				buildNewOne.repaint();
				BuildAccountPanel ToBuild = new BuildAccountPanel(view, user);
				buildNewOne.addActionListener(ToBuild);
				
				JLabel accountChoose = new JLabel("請輸入欲修改之帳號：");
				accountChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				accountChoose.setBounds(53, 112, 188, 23);
				view.add(accountChoose);
				accountChoose.repaint();
				
				JTextField AccounttextField = new JTextField();
				AccounttextField.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				AccounttextField.setBounds(233, 110, 232, 31);
				AccounttextField.setText("");
				view.add(AccounttextField);
				AccounttextField.setColumns(10);
				AccounttextField.repaint();
				
				JLabel identity = new JLabel("請選擇修改帳號之身分：");
				identity.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				identity.setBounds(53, 170, 203, 23);
				view.add(identity);
				identity.repaint();
				
				String[] option_identity = new String[3];
				option_identity[0] = " ";
				option_identity[1] = "教授";
				option_identity[2] = "學生";
				JComboBox IdentitycomboBox = new JComboBox(option_identity);
				IdentitycomboBox.setBackground(SystemColor.controlHighlight);
				IdentitycomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				IdentitycomboBox.setBounds(253, 167, 212, 29);
				view.add(IdentitycomboBox);
				IdentitycomboBox.repaint();
				
				JButton makeDelete = new JButton("刪除");
				makeDelete.setBackground(SystemColor.controlHighlight);
				makeDelete.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				makeDelete.setForeground(Color.RED);
				makeDelete.setBounds(197, 229, 111, 31);
				view.add(makeDelete);
				makeDelete.repaint();
				DeleteAccount delete = new DeleteAccount(view,user, AccounttextField, IdentitycomboBox);
				makeDelete.addActionListener(delete);
				
				JButton makeChange = new JButton("修改");
				makeChange.setBackground(SystemColor.controlHighlight);
				makeChange.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				makeChange.setBounds(318, 229, 111, 31);
				view.add(makeChange);
				makeChange.repaint();
				ChangeAccount change = new ChangeAccount(view, user, AccounttextField, IdentitycomboBox);
				makeChange.addActionListener(change);
			}
			else {
				ChangeWithoutBooleanNoteGUI okgogo = new ChangeWithoutBooleanNoteGUI();
				okgogo.run(false);
				
				newIn.setText("");
				checkNew.setText("");
			}
		}
	}
	
	class DeleteAccountPanel implements ActionListener{
			
			private Manager client;
			private JPanel view;
			
			public DeleteAccountPanel(JPanel view, Manager client){
				this.view = view;
				this.client = client;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.removeAll();
				view.repaint();
				
				JLabel deleteAccountTitle = new JLabel("刪除帳號");
				deleteAccountTitle.setForeground(Color.RED);
				deleteAccountTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				deleteAccountTitle.setBounds(200, 58, 118, 23);
				view.add(deleteAccountTitle);
				deleteAccountTitle.repaint();
				
				JLabel my_password = new JLabel("請輸入密碼：");
				my_password.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				my_password.setBounds(70, 113, 126, 23);
				view.add(my_password);
				my_password.repaint();
				
				JLabel password_again = new JLabel("確認密碼：");
				password_again.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				password_again.setBounds(90, 151, 100, 23);
				view.add(password_again);
				password_again.repaint();
				
				JPasswordField in = new JPasswordField();
				in.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				in.setBounds(199, 113, 171, 24);
				view.add(in);
				in.repaint();
				
				JPasswordField checkin = new JPasswordField();
				checkin.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkin.setBounds(199, 151, 171, 24);
				view.add(checkin);
				checkin.repaint();
				
				JButton deleteIt = new JButton("刪除");
				deleteIt.setBackground(SystemColor.controlHighlight);
				deleteIt.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				deleteIt.setBounds(278, 210, 111, 31);
				view.add(deleteIt);
				deleteIt.repaint();
				DeleteMyAccount delete = new DeleteMyAccount(view, client, in, checkin);
				deleteIt.addActionListener(delete);
			}
	}
	
	class DeleteMyAccount implements ActionListener{
		
		private Manager client;
		private JPanel view;
		private JPasswordField in;
		private JPasswordField checkin;
		
		public DeleteMyAccount(JPanel view, Manager client, JPasswordField in, JPasswordField checkin){
			this.view = view;
			this.client = client;
			this.in = in;
			this.checkin = checkin;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JInternalFrame internalFrame = new JInternalFrame("刪除帳號？");
			internalFrame.getContentPane().setEnabled(false);
			internalFrame.setClosable(true);
			internalFrame.setBounds(112, 53, 277, 158);
			view.add(internalFrame, 0);
			internalFrame.getContentPane().setLayout(null);
			internalFrame.repaint();
			
			JLabel checkWord = new JLabel("確定要刪除帳號？");
			checkWord.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			checkWord.setBounds(49, 30, 160, 23);
			internalFrame.getContentPane().add(checkWord);
			checkWord.repaint();
			
			JButton Yes = new JButton("是");
			Yes.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			Yes.setBackground(SystemColor.controlHighlight);
			Yes.setBounds(47, 68, 65, 31);
			internalFrame.getContentPane().add(Yes);
			Yes.repaint();
			ActionListener chooseYes = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					char[] password_in = in.getPassword();
					String passwordin = "";
					for (int i = 0; i < password_in.length; i++) {
						 passwordin +=  password_in[i];
					}
					
					char[] password_checkin = checkin.getPassword();
					String passwordcheckin = "";
					for (int i = 0; i < password_checkin.length; i++) {
						 passwordcheckin +=  password_checkin[i];
					}
					if (passwordcheckin.equals(passwordin) && passwordin.equals(client.password) && passwordcheckin.equals(client.password)) {
						boolean deleteOrNot = client.deleteFile();
						DeleteNoteGUI okgogogo = new DeleteNoteGUI();
						okgogogo.run(deleteOrNot);
						dispose();
					}
					else {
						DeleteNoteGUI okgogogo = new DeleteNoteGUI();
						okgogogo.run(false);
					}
				};
			};
			Yes.addActionListener(chooseYes);
			
			JButton No = new JButton("否");
			No.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			No.setBackground(SystemColor.controlHighlight);
			No.setBounds(144, 68, 65, 31);
			internalFrame.getContentPane().add(No);
			internalFrame.setVisible(true);
			No.repaint();
			ActionListener chooseNo = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					internalFrame.dispose();
				};
			};
			No.addActionListener(chooseNo);
		}
	}
	
	class LessonManager implements ActionListener{
		
		private Account client;
		private JPanel view;
		private JComboBox identityIN;
		
		public LessonManager(JPanel view, Account client, JComboBox identity){
			this.view = view;
			this.client = client;
			this.identityIN = identity;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int identity = identityIN.getSelectedIndex();
			String[] option = new String[4];
			option[0] = " ";
			option[1] = "管理員";
			option[2] = "教授";
			option[3] = "學生";
			Manager user = (Manager)client.changeType(option[1]);
			
			view.removeAll();
			view.repaint();
			if (client.identity.equals(option[1])) {
				
				JButton lessonEdit = new JButton("編輯課程");
				lessonEdit.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				lessonEdit.setBackground(SystemColor.controlHighlight);
				lessonEdit.setBounds(176, 62, 153, 31);
				view.add(lessonEdit);
				lessonEdit.repaint();
				LessonEdit edit = new LessonEdit(view, user);
				lessonEdit.addActionListener(edit);
				
				JButton lessonRead = new JButton("查看課程");
				lessonRead.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				lessonRead.setBackground(SystemColor.controlHighlight);
				lessonRead.setBounds(176, 136, 153, 31);
				view.add(lessonRead);
				lessonRead.repaint();
				LessonRead read = new LessonRead(view, user);
				lessonRead.addActionListener(read);
				
				JButton chooseLesson = new JButton("選課管理");
				chooseLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				chooseLesson.setBackground(SystemColor.controlHighlight);
				chooseLesson.setBounds(176, 210, 153, 31);
				view.add(chooseLesson);
				chooseLesson.repaint();
				LessonChoose choose = new LessonChoose(view, user);
				chooseLesson.addActionListener(choose);
			}
			
			else if (client.identity.equals(option[2])) {
				
				JLabel ReadLessonTitle = new JLabel("查看課程");
				ReadLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				ReadLessonTitle.setBounds(200, 15, 118, 23);
				view.add(ReadLessonTitle);
				ReadLessonTitle.repaint();
				
				JLabel YearChoose = new JLabel("請選擇學期：");
				YearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				YearChoose.setBounds(55, 50, 127, 23);
				view.add(YearChoose);
				YearChoose.repaint();
				
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
				JComboBox yearChoiceBox = new JComboBox(yearChoice);
				yearChoiceBox.setBackground(SystemColor.controlHighlight);
				yearChoiceBox.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearChoiceBox.setBounds(176, 47, 183, 29);
				view.add(yearChoiceBox);
				yearChoiceBox.repaint();
				
				JButton GoToCheck = new JButton("查詢");
				GoToCheck.setBackground(SystemColor.controlHighlight);
				GoToCheck.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				GoToCheck.setBounds(374, 47, 77, 29);
				view.add(GoToCheck);
				GoToCheck.repaint();
				LessonReadCheck checkIT = new LessonReadCheck(view, user, yearChoiceBox);
				GoToCheck.addActionListener(checkIT);
			}
			
			else if (client.identity.equals(option[3])) {
				JLabel ReadLessonTitle = new JLabel("查看課程");
				ReadLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				ReadLessonTitle.setBounds(200, 15, 118, 23);
				view.add(ReadLessonTitle);
				ReadLessonTitle.repaint();
				
				JLabel YearChoose = new JLabel("請選擇學期：");
				YearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				YearChoose.setBounds(55, 50, 127, 23);
				view.add(YearChoose);
				YearChoose.repaint();
				
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
				JComboBox yearChoiceBox = new JComboBox(yearChoice);
				yearChoiceBox.setBackground(SystemColor.controlHighlight);
				yearChoiceBox.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearChoiceBox.setBounds(176, 47, 183, 29);
				view.add(yearChoiceBox);
				yearChoiceBox.repaint();
				
				JButton GoToCheck = new JButton("查詢");
				GoToCheck.setBackground(SystemColor.controlHighlight);
				GoToCheck.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				GoToCheck.setBounds(374, 47, 77, 29);
				view.add(GoToCheck);
				GoToCheck.repaint();
				LessonReadCheck checkIT = new LessonReadCheck(view, user, yearChoiceBox);
				GoToCheck.addActionListener(checkIT);
			}
		}
	}
	
	class LessonEdit implements ActionListener{
			
			private Manager client;
			private JPanel view;
			
			public LessonEdit(JPanel view, Manager client){
				this.view = view;
				this.client = client;
			}
			
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.removeAll();
				view.repaint();
				
				JLabel editLessonTitle = new JLabel("編輯課程");
				editLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				editLessonTitle.setBounds(200, 58, 118, 23);
				view.add(editLessonTitle);
				editLessonTitle.repaint();
				
				JButton buildLesson = new JButton("建立新課程");
				buildLesson.setBackground(SystemColor.controlHighlight);
				buildLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildLesson.setBounds(65, 200, 134, 31);
				view.add(buildLesson);
				buildLesson.repaint();
				BuildLesson build = new BuildLesson(view, client);
				buildLesson.addActionListener(build);
				
				JLabel yearChoose = new JLabel("請選擇學期：");
				yearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearChoose.setBounds(60, 99, 127, 23);
				view.add(yearChoose);
				yearChoose.repaint();
				
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
	            JComboBox yearFolder = new JComboBox(yearChoice);
				yearFolder.setBackground(SystemColor.controlHighlight);
				yearFolder.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearFolder.setBounds(187, 96, 212, 29);
				view.add(yearFolder);
				yearFolder.repaint();
				
				JButton checkFile = new JButton("查詢");
				checkFile.setBackground(SystemColor.controlHighlight);
				checkFile.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkFile.setBounds(411, 96, 77, 29);
				view.add(checkFile);
				checkFile.repaint();
				LessonEditCheck check = new LessonEditCheck(view, client, yearFolder);
				checkFile.addActionListener(check);
			}
	}
	
	class LessonEditCheck implements ActionListener{
			
			private Manager client;
			private JPanel view;
			private JComboBox lessonFolder;
			
			public LessonEditCheck(JPanel view, Manager client, JComboBox lessonFolder){
				this.view = view;
				this.client = client;
				this.lessonFolder = lessonFolder;
			}
			
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = lessonFolder.getSelectedIndex();
				view.removeAll();
				view.repaint();
				
				if (lessonFolder.getSelectedIndex() == 0) {
					JLabel editLessonTitle = new JLabel("編輯課程");
					editLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
					editLessonTitle.setBounds(200, 58, 118, 23);
					view.add(editLessonTitle);
					editLessonTitle.repaint();
					
					JButton buildLesson = new JButton("建立新課程");
					buildLesson.setBackground(SystemColor.controlHighlight);
					buildLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					buildLesson.setBounds(65, 200, 134, 31);
					view.add(buildLesson);
					buildLesson.repaint();
					BuildLesson build = new BuildLesson(view, client);
					buildLesson.addActionListener(build);
					
					JLabel yearChoose = new JLabel("請選擇學期：");
					yearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					yearChoose.setBounds(60, 99, 127, 23);
					view.add(yearChoose);
					yearChoose.repaint();
					
					String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
					File folder = new File(lessonFolder);
		            File[] Lessonlist = folder.listFiles();
		            ArrayList<String> yearLesson = new ArrayList<String>();
		            for(int i = 0; i < Lessonlist.length; i++){
		            	yearLesson.add(Lessonlist[i].getName());
			        }
		            String[] yearChoice = new String[yearLesson.size()+1];
		            yearChoice[0] = "";
		            for (int j = 1; j < yearLesson.size()+1; j++) {
		            	yearChoice[j] = yearLesson.get(j-1);
		            }
		            JComboBox yearFolder = new JComboBox(yearChoice);
					yearFolder.setBackground(SystemColor.controlHighlight);
					yearFolder.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					yearFolder.setBounds(187, 96, 212, 29);
					view.add(yearFolder);
					yearFolder.repaint();
					
					JButton checkFile = new JButton("查詢");
					checkFile.setBackground(SystemColor.controlHighlight);
					checkFile.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					checkFile.setBounds(411, 96, 77, 29);
					view.add(checkFile);
					checkFile.repaint();
					LessonEditCheck check = new LessonEditCheck(view, client, yearFolder);
					checkFile.addActionListener(check);
				}
				else {
					
					JLabel editLessonTitle = new JLabel("編輯課程");
					editLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
					editLessonTitle.setBounds(200, 58, 118, 23);
					view.add(editLessonTitle);
					editLessonTitle.repaint();
					
					JButton buildLesson = new JButton("建立新課程");
					buildLesson.setBackground(SystemColor.controlHighlight);
					buildLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					buildLesson.setBounds(65, 200, 134, 31);
					view.add(buildLesson);
					buildLesson.repaint();
					BuildLesson build = new BuildLesson(view, client);
					buildLesson.addActionListener(build);
					
					JLabel yearChoose = new JLabel("請選擇學期：");
					yearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					yearChoose.setBounds(60, 99, 127, 23);
					view.add(yearChoose);
					yearChoose.repaint();
					
					String lessons = "C:\\StudentGradeSystem\\LessonFile\\";
					File folder = new File(lessons);
		            File[] Lessonlist = folder.listFiles();
		            ArrayList<String> yearLesson = new ArrayList<String>();
		            for(int i = 0; i < Lessonlist.length; i++){
		            	yearLesson.add(Lessonlist[i].getName());
			        }
		            String[] yearChoice = new String[yearLesson.size()+1];
		            yearChoice[0] = "";
		            for (int j = 1; j < yearLesson.size()+1; j++) {
		            	yearChoice[j] = yearLesson.get(j-1);
		            }
		            JComboBox yearFolder = new JComboBox(yearChoice);
					yearFolder.setBackground(SystemColor.controlHighlight);
					yearFolder.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					yearFolder.setBounds(187, 96, 212, 29);
					yearFolder.setSelectedIndex(index);
					view.add(yearFolder);
					yearFolder.repaint();
					
					JButton checkFile = new JButton("查詢");
					checkFile.setBackground(SystemColor.controlHighlight);
					checkFile.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					checkFile.setBounds(411, 96, 77, 29);
					view.add(checkFile);
					checkFile.repaint();
					LessonEditCheck check = new LessonEditCheck(view, client, yearFolder);
					checkFile.addActionListener(check);
					
					String[] allLesson = client.showAllLesson(yearChoice[lessonFolder.getSelectedIndex()]);
					
					JLabel lessonChoose = new JLabel("請選擇課程：");
					lessonChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					lessonChoose.setBounds(60, 146, 127, 23);
					view.add(lessonChoose);
					lessonChoose.repaint();
					
					JComboBox lessonFile = new JComboBox(allLesson);
					lessonFile.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					lessonFile.setBackground(SystemColor.controlHighlight);
					lessonFile.setBounds(187, 143, 212, 29);
					view.add(lessonFile);
					lessonFile.repaint();
					
					JButton deleteLesson = new JButton("刪除");
					deleteLesson.setForeground(Color.RED);
					deleteLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					deleteLesson.setBackground(SystemColor.controlHighlight);
					deleteLesson.setBounds(215, 200, 111, 31);
					view.add(deleteLesson);
					deleteLesson.repaint();
					DeleteLesson delete = new DeleteLesson(view, client, yearFolder, lessonFile);
					deleteLesson.addActionListener(delete);
					
					JButton changeLesson = new JButton("修改");
					changeLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					changeLesson.setBackground(SystemColor.controlHighlight);
					changeLesson.setBounds(340, 200, 111, 31);
					view.add(changeLesson);
					changeLesson.repaint();
					ChangeLesson change = new ChangeLesson(view, client, yearFolder, lessonFile);
					changeLesson.addActionListener(change);
				}
			}
	}
	
	class BuildLesson implements ActionListener{
		
		private Manager client;
		private JPanel view;
		
		public BuildLesson(JPanel view, Manager client){
			this.view = view;
			this.client = client;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.removeAll();
			view.repaint();
			
			JLabel buildLessonTitle = new JLabel("建立新課程");
			buildLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			buildLessonTitle.setBounds(197, 15, 127, 23);
			view.add(buildLessonTitle);
			buildLessonTitle.repaint();
			
			JLabel buildName = new JLabel("輸入課程名稱：");
			buildName.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			buildName.setBounds(15, 98, 140, 23);
			view.add(buildName);
			buildName.repaint();
			
			JTextField buildNameIN = new JTextField();
			buildNameIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			buildNameIN.setBounds(151, 97, 102, 24);
			view.add(buildNameIN);
			buildNameIN.setColumns(10);
			buildNameIN.repaint();
			
			JLabel buildProName = new JLabel("輸入教授姓名：");
			buildProName.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			buildProName.setBounds(15, 143, 140, 23);
			view.add(buildProName);
			
			JTextField buildProNameIN = new JTextField();
			buildProNameIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			buildProNameIN.setColumns(10);
			buildProNameIN.setBounds(151, 142, 102, 24);
			view.add(buildProNameIN);
			buildProNameIN.repaint();
			
			JLabel buildType = new JLabel("選擇課程類型：");
			buildType.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			buildType.setBounds(265, 98, 140, 23);
			view.add(buildType);
			buildType.repaint();
			
			String[] typeChoose = new String[3];
			typeChoose[0] = " ";
			typeChoose[1] = "必修";
			typeChoose[2] = "選修";
			JComboBox buildTypeIN = new JComboBox(typeChoose);
			buildTypeIN.setBackground(SystemColor.controlHighlight);
			buildTypeIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			buildTypeIN.setBounds(401, 96, 102, 29);
			view.add(buildTypeIN);
			buildTypeIN.repaint();
			
			JLabel buildNum = new JLabel("輸入課程代碼：");
			buildNum.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			buildNum.setBounds(265, 143, 140, 23);
			view.add(buildNum);
			buildNum.repaint();
			
			JTextField buildNumIN = new JTextField();
			buildNumIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			buildNumIN.setColumns(10);
			buildNumIN.setBounds(401, 141, 102, 24);
			view.add(buildNumIN);
			buildNumIN.repaint();
			
			JLabel buildPoint = new JLabel("輸入課程學分：");
			buildPoint.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			buildPoint.setBounds(265, 188, 140, 23);
			view.add(buildPoint);
			buildPoint.repaint();
			
			JTextField buildPointIN = new JTextField();
			buildPointIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			buildPointIN.setColumns(10);
			buildPointIN.setBounds(401, 187, 102, 24);
			view.add(buildPointIN);
			buildPointIN.repaint();
			
			JLabel buildWord = new JLabel("輸入課程大綱：");
			buildWord.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			buildWord.setBounds(79, 232, 140, 23);
			view.add(buildWord);
			buildWord.repaint();
			
			JTextField buildWordIN = new JTextField();
			buildWordIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			buildWordIN.setColumns(10);
			buildWordIN.setBounds(225, 231, 229, 24);
			view.add(buildWordIN);
			buildWordIN.repaint();
			
			JLabel buildYear = new JLabel("輸入開課學期：");
			buildYear.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			buildYear.setBounds(15, 190, 140, 23);
			view.add(buildYear);
			buildYear.repaint();
			
			JTextField buildYearIN = new JTextField();
			buildYearIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			buildYearIN.setColumns(10);
			buildYearIN.setBounds(151, 187, 102, 24);
			view.add(buildYearIN);
			buildYearIN.repaint();
			
			JButton buildLessonOK = new JButton("建立");
			buildLessonOK.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			buildLessonOK.setBackground(SystemColor.controlHighlight);
			buildLessonOK.setBounds(254, 280, 127, 31);
			view.add(buildLessonOK);
			buildLessonOK.repaint();
			BuildLessonGO buildIT = new BuildLessonGO(view, client, buildNameIN, buildProNameIN, buildTypeIN, buildNumIN, buildPointIN, buildWordIN, buildYearIN);
			buildLessonOK.addActionListener(buildIT);
			
			
			JButton back = new JButton("回上一頁");
			back.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			back.setBackground(SystemColor.controlHighlight);
			back.setBounds(112, 280, 127, 31);
			view.add(back);
			back.repaint();
			LessonEdit edit = new LessonEdit(view, client);
			back.addActionListener(edit);
		}
	}
	
	class BuildLessonGO implements ActionListener{
			
			private Manager client;
			private JPanel view;
			private JTextField buildNameIN;
			private JTextField buildProNameIN;
			private JComboBox buildTypeIN;
			private JTextField buildNumIN;
			private JTextField buildPointIN;
			private JTextField buildWordIN;
			private JTextField buildYearIN;
			
			public BuildLessonGO(JPanel view, Manager client, JTextField buildNameIN, JTextField buildProNameIN,JComboBox buildTypeIN, JTextField buildNumIN, JTextField buildPointIN, JTextField buildWordIN, JTextField buildYearIN){
				this.view = view;
				this.client = client;
				this.buildNameIN = buildNameIN;
				this.buildProNameIN = buildProNameIN;
				this.buildTypeIN = buildTypeIN;
				this.buildNumIN = buildNumIN;
				this.buildPointIN = buildPointIN;
				this.buildWordIN = buildWordIN;
				this.buildYearIN = buildYearIN;
			}
			
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] typeChoose = new String[3];
				typeChoose[0] = " ";
				typeChoose[1] = "必修";
				typeChoose[2] = "選修";
				
				String lessonName = buildNameIN.getText();
				String proName = buildProNameIN.getText();
				String year = buildYearIN.getText();
				String num = buildNumIN.getText();
				String word = buildWordIN.getText();
				String type = typeChoose[buildTypeIN.getSelectedIndex()];
				String point = buildPointIN.getText();
				ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
				Lesson newLesson = new Lesson(lessonName, proName, year, num, word, type, point, list);
				Professor pro = client.readProFile(proName);
				boolean buildITdone = client.buildLessonFile(newLesson, year, pro);
				BuildNoteGUI buildLessonOKOK = new BuildNoteGUI();
				buildLessonOKOK.run(buildITdone);
				
				view.removeAll();
				view.repaint();
				
				JLabel buildLessonTitle = new JLabel("建立新課程");
				buildLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				buildLessonTitle.setBounds(197, 15, 127, 23);
				view.add(buildLessonTitle);
				buildLessonTitle.repaint();
				
				JLabel buildName = new JLabel("輸入課程名稱：");
				buildName.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				buildName.setBounds(15, 98, 140, 23);
				view.add(buildName);
				buildName.repaint();
				
				JTextField buildNameIN = new JTextField();
				buildNameIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildNameIN.setBounds(151, 97, 102, 24);
				view.add(buildNameIN);
				buildNameIN.setColumns(10);
				buildNameIN.repaint();
				
				JLabel buildProName = new JLabel("輸入教授姓名：");
				buildProName.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				buildProName.setBounds(15, 143, 140, 23);
				view.add(buildProName);
				
				JTextField buildProNameIN = new JTextField();
				buildProNameIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildProNameIN.setColumns(10);
				buildProNameIN.setBounds(151, 142, 102, 24);
				view.add(buildProNameIN);
				buildProNameIN.repaint();
				
				JLabel buildType = new JLabel("選擇課程類型：");
				buildType.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				buildType.setBounds(265, 98, 140, 23);
				view.add(buildType);
				buildType.repaint();
				
				JComboBox buildTypeIN = new JComboBox(typeChoose);
				buildTypeIN.setBackground(SystemColor.controlHighlight);
				buildTypeIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildTypeIN.setBounds(401, 96, 102, 29);
				view.add(buildTypeIN);
				buildTypeIN.repaint();
				
				JLabel buildNum = new JLabel("輸入課程代碼：");
				buildNum.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				buildNum.setBounds(265, 143, 140, 23);
				view.add(buildNum);
				buildNum.repaint();
				
				JTextField buildNumIN = new JTextField();
				buildNumIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildNumIN.setColumns(10);
				buildNumIN.setBounds(401, 141, 102, 24);
				view.add(buildNumIN);
				buildNumIN.repaint();
				
				JLabel buildPoint = new JLabel("輸入課程學分：");
				buildPoint.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				buildPoint.setBounds(265, 188, 140, 23);
				view.add(buildPoint);
				buildPoint.repaint();
				
				JTextField buildPointIN = new JTextField();
				buildPointIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildPointIN.setColumns(10);
				buildPointIN.setBounds(401, 187, 102, 24);
				view.add(buildPointIN);
				buildPointIN.repaint();
				
				JLabel buildWord = new JLabel("輸入課程大綱：");
				buildWord.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				buildWord.setBounds(79, 232, 140, 23);
				view.add(buildWord);
				buildWord.repaint();
				
				JTextField buildWordIN = new JTextField();
				buildWordIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildWordIN.setColumns(10);
				buildWordIN.setBounds(225, 231, 229, 24);
				view.add(buildWordIN);
				buildWordIN.repaint();
				
				JLabel buildYear = new JLabel("輸入開課學期：");
				buildYear.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				buildYear.setBounds(15, 190, 140, 23);
				view.add(buildYear);
				buildYear.repaint();
				
				JTextField buildYearIN = new JTextField();
				buildYearIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildYearIN.setColumns(10);
				buildYearIN.setBounds(151, 187, 102, 24);
				view.add(buildYearIN);
				buildYearIN.repaint();
				
				JButton buildLessonOK = new JButton("建立");
				buildLessonOK.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				buildLessonOK.setBackground(SystemColor.controlHighlight);
				buildLessonOK.setBounds(254, 280, 127, 31);
				view.add(buildLessonOK);
				buildLessonOK.repaint();
				BuildLessonGO buildIT = new BuildLessonGO(view, client, buildNameIN, buildProNameIN, buildTypeIN, buildNumIN, buildPointIN, buildWordIN, buildYearIN);
				buildLessonOK.addActionListener(buildIT);
				
				JButton back = new JButton("回上一頁");
				back.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				back.setBackground(SystemColor.controlHighlight);
				back.setBounds(112, 280, 127, 31);
				view.add(back);
				back.repaint();
				LessonEdit edit = new LessonEdit(view, client);
				back.addActionListener(edit);
			}
	}
	
	class DeleteLesson implements ActionListener{
			
			private Manager client;
			private JPanel view;
			private JComboBox yearFolder;
			private JComboBox lessonFile;
			
			public DeleteLesson(JPanel view, Manager client, JComboBox yearFolder, JComboBox lessonFile){
				this.view = view;
				this.client = client;
				this.yearFolder = yearFolder;
				this.lessonFile = lessonFile;
			}
			
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String lessons = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessons);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
	            String[] allLesson = client.showAllLesson(yearChoice[yearFolder.getSelectedIndex()]);
	            
				JInternalFrame internalFrame = new JInternalFrame("刪除課程？");
				internalFrame.getContentPane().setEnabled(false);
				internalFrame.setClosable(true);
				internalFrame.setBounds(112, 35, 277, 158);
				view.add(internalFrame, 0);
				internalFrame.getContentPane().setLayout(null);
				internalFrame.repaint();
				
				JLabel checkWord = new JLabel("確定要刪除課程？");
				checkWord.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				checkWord.setBounds(49, 30, 160, 23);
				internalFrame.getContentPane().add(checkWord);
				checkWord.repaint();
				
				JButton Yes = new JButton("是");
				Yes.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				Yes.setBackground(SystemColor.controlHighlight);
				Yes.setBounds(47, 68, 65, 31);
				internalFrame.getContentPane().add(Yes);
				Yes.repaint();
				ActionListener chooseYes = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Lesson DeleteOne = client.findLessonData(yearChoice[yearFolder.getSelectedIndex()], allLesson[lessonFile.getSelectedIndex()]);
						Professor deleteName = client.readProFile(DeleteOne.professorName);
						boolean deleteIT = client.deleteLessonFile(DeleteOne.year, DeleteOne.lessonName, deleteName);
						DeleteNote_MAGUI delete_lesson_go = new DeleteNote_MAGUI();
						delete_lesson_go.run(deleteIT);
						
						internalFrame.dispose();
						
						view.removeAll();
						view.repaint();
						
						JLabel editLessonTitle = new JLabel("編輯課程");
						editLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
						editLessonTitle.setBounds(200, 58, 118, 23);
						view.add(editLessonTitle);
						editLessonTitle.repaint();
						
						JButton buildLesson = new JButton("建立新課程");
						buildLesson.setBackground(SystemColor.controlHighlight);
						buildLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						buildLesson.setBounds(65, 200, 134, 31);
						view.add(buildLesson);
						buildLesson.repaint();
						BuildLesson build = new BuildLesson(view, client);
						buildLesson.addActionListener(build);
						
						JLabel yearChoose = new JLabel("請選擇學期：");
						yearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
						yearChoose.setBounds(60, 99, 127, 23);
						view.add(yearChoose);
						yearChoose.repaint();
						
						String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
						File folder = new File(lessonFolder);
			            File[] Lessonlist = folder.listFiles();
			            ArrayList<String> yearLesson = new ArrayList<String>();
			            for(int i = 0; i < Lessonlist.length; i++){
			            	yearLesson.add(Lessonlist[i].getName());
				        }
			            String[] yearChoice = new String[yearLesson.size()+1];
			            yearChoice[0] = "";
			            for (int j = 1; j < yearLesson.size()+1; j++) {
			            	yearChoice[j] = yearLesson.get(j-1);
			            }
			            JComboBox yearFolder = new JComboBox(yearChoice);
						yearFolder.setBackground(SystemColor.controlHighlight);
						yearFolder.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
						yearFolder.setBounds(187, 96, 212, 29);
						view.add(yearFolder);
						yearFolder.repaint();
						
						JButton checkFile = new JButton("查詢");
						checkFile.setBackground(SystemColor.controlHighlight);
						checkFile.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						checkFile.setBounds(411, 96, 77, 29);
						view.add(checkFile);
						checkFile.repaint();
						LessonEditCheck check = new LessonEditCheck(view, client, yearFolder);
						checkFile.addActionListener(check);
					};
				};
				Yes.addActionListener(chooseYes);
				
				JButton No = new JButton("否");
				No.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				No.setBackground(SystemColor.controlHighlight);
				No.setBounds(144, 68, 65, 31);
				internalFrame.getContentPane().add(No);
				internalFrame.setVisible(true);
				No.repaint();
				ActionListener chooseNo = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						internalFrame.dispose();
					};
				};
				No.addActionListener(chooseNo);
			}
	}
	
	class ChangeLesson implements ActionListener{
		
		private Manager client;
		private JPanel view;
		private JComboBox yearFolder;
		private JComboBox lessonFile;
		
		public ChangeLesson(JPanel view, Manager client, JComboBox yearFolder, JComboBox lessonFile){
			this.view = view;
			this.client = client;
			this.yearFolder = yearFolder;
			this.lessonFile = lessonFile;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String lessons = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessons);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
            String[] allLesson = client.showAllLesson(yearChoice[yearFolder.getSelectedIndex()]);
            
            Lesson editFile = client.findLessonData(yearChoice[yearFolder.getSelectedIndex()], allLesson[lessonFile.getSelectedIndex()]);
            view.removeAll();
			view.repaint();
			
			JLabel changeLessonTitle = new JLabel("修改課程");
			changeLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			changeLessonTitle.setBounds(197, 15, 105, 23);
			view.add(changeLessonTitle);
			changeLessonTitle.repaint();
			
			JLabel newNum = new JLabel("輸入更改代碼：");
			newNum.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			newNum.setBounds(71, 54, 140, 23);
			view.add(newNum);
			newNum.repaint();
			
			JTextField numIN = new JTextField();
			numIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			numIN.setBounds(207, 53, 229, 24);
			view.add(numIN);
			numIN.setColumns(10);
			numIN.repaint();
			
			JLabel newPoint = new JLabel("輸入更改學分：");
			newPoint.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			newPoint.setBounds(71, 99, 140, 23);
			view.add(newPoint);
			newPoint.repaint();
			
			JTextField PointIN = new JTextField();
			PointIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			PointIN.setColumns(10);
			PointIN.setBounds(207, 98, 229, 24);
			view.add(PointIN);
			PointIN.repaint();
			
			JLabel newType = new JLabel("選擇更改類型：");
			newType.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			newType.setBounds(71, 145, 140, 23);
			view.add(newType);
			newType.repaint();
			
			String[] typeWord = new String[3];
			typeWord[0] = " ";
			typeWord[1] = "必修";
			typeWord[2] = "選修";
			JComboBox TypeIN = new JComboBox(typeWord);
			TypeIN.setBackground(SystemColor.controlHighlight);
			TypeIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			TypeIN.setBounds(207, 144, 229, 29);
			view.add(TypeIN);
			TypeIN.repaint();
			
			JLabel newProName = new JLabel("輸入更改教授：");
			newProName.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			newProName.setBounds(71, 190, 140, 23);
			view.add(newProName);
			newProName.repaint();
			
			JTextField newProNameIN = new JTextField();
			newProNameIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newProNameIN.setColumns(10);
			newProNameIN.setBounds(207, 189, 229, 24);
			view.add(newProNameIN);
			newProNameIN.repaint();
			
			JLabel newLesson = new JLabel("輸入更改名稱：");
			newLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			newLesson.setBounds(71, 236, 140, 23);
			view.add(newLesson);
			newLesson.repaint();
			
			JTextField newLessonIN = new JTextField();
			newLessonIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newLessonIN.setColumns(10);
			newLessonIN.setBounds(207, 235, 229, 24);
			view.add(newLessonIN);
			newLessonIN.repaint();
			
			JLabel newWord = new JLabel("輸入更改大綱：");
			newWord.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			newWord.setBounds(71, 281, 140, 23);
			view.add(newWord);
			newWord.repaint();
			
			JTextField newWordIN = new JTextField();
			newWordIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			newWordIN.setColumns(10);
			newWordIN.setBounds(207, 280, 229, 24);
			view.add(newWordIN);
			newWordIN.repaint();
			
			JButton changeLessonOK = new JButton("修改");
			changeLessonOK.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			changeLessonOK.setBackground(SystemColor.controlHighlight);
			changeLessonOK.setBounds(254, 319, 127, 31);
			view.add(changeLessonOK);
			changeLessonOK.repaint();
			ActionListener changelessonGO = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean proNameReset = true;
					boolean lessonNameReset = true;
					if (!newProNameIN.getText().equals("")) {
						proNameReset = editFile.changeLessonProName(newProNameIN.getText());
						newProNameIN.setText("");
					}
					if (!newLessonIN.getText().equals("")) {
						lessonNameReset = editFile.changeLessonName(newLessonIN.getText());
						newLessonIN.setText("");
					}
					if (!numIN.getText().equals("")) {
						editFile.num = numIN.getText();
						numIN.setText("");
					}
					if (!PointIN.getText().equals("")) {
						editFile.point = PointIN.getText();
					}
					if (TypeIN.getSelectedIndex() != 0) {
						editFile.type = typeWord[TypeIN.getSelectedIndex()];
						TypeIN.setSelectedIndex(0);
					}
					if (!newWordIN.getText().equals("")) {
						editFile.word = newWordIN.getText();
						newWordIN.setText("");
					}
					boolean ChangeOK = editFile.changeLessonFile() && proNameReset && lessonNameReset;
					ChangeWithoutBooleanNoteGUI changeOkokok = new ChangeWithoutBooleanNoteGUI();
					changeOkokok.run(ChangeOK);
					
					view.removeAll();
					view.repaint();
					
					JLabel editLessonTitle = new JLabel("編輯課程");
					editLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
					editLessonTitle.setBounds(200, 58, 118, 23);
					view.add(editLessonTitle);
					editLessonTitle.repaint();
					
					JButton buildLesson = new JButton("建立新課程");
					buildLesson.setBackground(SystemColor.controlHighlight);
					buildLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					buildLesson.setBounds(65, 200, 134, 31);
					view.add(buildLesson);
					buildLesson.repaint();
					BuildLesson build = new BuildLesson(view, client);
					buildLesson.addActionListener(build);
					
					JLabel yearChoose = new JLabel("請選擇學期：");
					yearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					yearChoose.setBounds(60, 99, 127, 23);
					view.add(yearChoose);
					yearChoose.repaint();
					
					String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
					File folder = new File(lessonFolder);
		            File[] Lessonlist = folder.listFiles();
		            ArrayList<String> yearLesson = new ArrayList<String>();
		            for(int i = 0; i < Lessonlist.length; i++){
		            	yearLesson.add(Lessonlist[i].getName());
			        }
		            String[] yearChoice = new String[yearLesson.size()+1];
		            yearChoice[0] = "";
		            for (int j = 1; j < yearLesson.size()+1; j++) {
		            	yearChoice[j] = yearLesson.get(j-1);
		            }
		            JComboBox yearFolder = new JComboBox(yearChoice);
					yearFolder.setBackground(SystemColor.controlHighlight);
					yearFolder.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					yearFolder.setBounds(187, 96, 212, 29);
					view.add(yearFolder);
					yearFolder.repaint();
					
					JButton checkFile = new JButton("查詢");
					checkFile.setBackground(SystemColor.controlHighlight);
					checkFile.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					checkFile.setBounds(411, 96, 77, 29);
					view.add(checkFile);
					checkFile.repaint();
					LessonEditCheck check = new LessonEditCheck(view, client, yearFolder);
					checkFile.addActionListener(check);
				};
			};
			changeLessonOK.addActionListener(changelessonGO);
			
			JButton back = new JButton("回上一頁");
			back.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			back.setBackground(SystemColor.controlHighlight);
			back.setBounds(112, 319, 127, 31);
			view.add(back);
			back.repaint();
			LessonEdit edit = new LessonEdit(view, client);
			back.addActionListener(edit);
		}
	}
	
	class LessonRead implements ActionListener{
		
		private Manager client;
		private JPanel view;
		
		public LessonRead(JPanel view, Manager client){
			this.view = view;
			this.client = client;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.removeAll();
			view.repaint();
			
			JLabel ReadLessonTitle = new JLabel("查看課程");
			ReadLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			ReadLessonTitle.setBounds(200, 15, 118, 23);
			view.add(ReadLessonTitle);
			ReadLessonTitle.repaint();
			
			JLabel YearChoose = new JLabel("請選擇學期：");
			YearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			YearChoose.setBounds(55, 50, 127, 23);
			view.add(YearChoose);
			YearChoose.repaint();
			
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
			JComboBox yearChoiceBox = new JComboBox(yearChoice);
			yearChoiceBox.setBackground(SystemColor.controlHighlight);
			yearChoiceBox.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			yearChoiceBox.setBounds(176, 47, 183, 29);
			view.add(yearChoiceBox);
			yearChoiceBox.repaint();
			
			JButton check = new JButton("查詢");
			check.setBackground(SystemColor.controlHighlight);
			check.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			check.setBounds(374, 47, 77, 29);
			view.add(check);
			check.repaint();
			LessonReadCheck checkIT = new LessonReadCheck(view, client, yearChoiceBox);
			check.addActionListener(checkIT);
		}
	}
	
	class LessonReadCheck implements ActionListener{
		
		private Manager client;
		private JPanel view;
		private JComboBox yearChoiceBox;
		
		public LessonReadCheck(JPanel view, Manager client, JComboBox yearChoiceBox){
			this.view = view;
			this.client = client;
			this.yearChoiceBox = yearChoiceBox;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int index = yearChoiceBox.getSelectedIndex();
			
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
			String[] allLesson = client.showAllLesson(yearChoice[yearChoiceBox.getSelectedIndex()]);
			view.removeAll();
			view.repaint();
			
			if (yearChoiceBox.getSelectedIndex() == 0) {
				view.removeAll();
				view.repaint();
				
				JLabel ReadLessonTitle = new JLabel("查看課程");
				ReadLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				ReadLessonTitle.setBounds(200, 15, 118, 23);
				view.add(ReadLessonTitle);
				ReadLessonTitle.repaint();
				
				JLabel YearChoose = new JLabel("請選擇學期：");
				YearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				YearChoose.setBounds(55, 50, 127, 23);
				view.add(YearChoose);
				YearChoose.repaint();
				
				JComboBox yearChoiceBox = new JComboBox(yearChoice);
				yearChoiceBox.setBackground(SystemColor.controlHighlight);
				yearChoiceBox.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearChoiceBox.setBounds(176, 47, 183, 29);
				view.add(yearChoiceBox);
				yearChoiceBox.repaint();
				
				JButton check = new JButton("查詢");
				check.setBackground(SystemColor.controlHighlight);
				check.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				check.setBounds(374, 47, 77, 29);
				view.add(check);
				check.repaint();
				LessonReadCheck checkIT = new LessonReadCheck(view, client, yearChoiceBox);
				check.addActionListener(checkIT);
			}
			else {
				view.removeAll();
				view.repaint();
				
				JLabel ReadLessonTitle = new JLabel("查看課程");
				ReadLessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				ReadLessonTitle.setBounds(200, 15, 118, 23);
				view.add(ReadLessonTitle);
				ReadLessonTitle.repaint();
				
				JLabel YearChoose = new JLabel("請選擇學期：");
				YearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				YearChoose.setBounds(55, 50, 127, 23);
				view.add(YearChoose);
				YearChoose.repaint();
				
				JComboBox yearChoiceBox = new JComboBox(yearChoice);
				yearChoiceBox.setBackground(SystemColor.controlHighlight);
				yearChoiceBox.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearChoiceBox.setBounds(176, 47, 183, 29);
				yearChoiceBox.setSelectedIndex(index);
				view.add(yearChoiceBox);
				yearChoiceBox.repaint();
				
				JButton check = new JButton("查詢");
				check.setBackground(SystemColor.controlHighlight);
				check.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				check.setBounds(374, 47, 77, 29);
				view.add(check);
				check.repaint();
				LessonReadCheck checkIT = new LessonReadCheck(view, client, yearChoiceBox);
				check.addActionListener(checkIT);
				
				JLabel LessonChoose = new JLabel("請選擇課程：");
				LessonChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				LessonChoose.setBounds(55, 82, 127, 23);
				view.add(LessonChoose);
				LessonChoose.repaint();
				
				JComboBox lessonChoiceBox = new JComboBox(allLesson);
				lessonChoiceBox.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				lessonChoiceBox.setBackground(SystemColor.controlHighlight);
				lessonChoiceBox.setBounds(176, 81, 183, 29);
				view.add(lessonChoiceBox);
				lessonChoiceBox.repaint();
				
				JButton GoToFind = new JButton("查看");
				GoToFind.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				GoToFind.setBackground(SystemColor.controlHighlight);
				GoToFind.setBounds(374, 80, 77, 29);
				view.add(GoToFind);
				GoToFind.repaint();
				ActionListener check_lesson_view = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (lessonChoiceBox.getSelectedIndex() != 0) {
							String wordToprint = client.findLessonData(yearChoice[yearChoiceBox.getSelectedIndex()], allLesson[lessonChoiceBox.getSelectedIndex()]).printDate();
							
							JTextArea ViewArea = new JTextArea();
							ViewArea.setText(wordToprint);
							ViewArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
							ViewArea.setBounds(65, 114, 386, 210);
							view.add(ViewArea);
							ViewArea.setEditable(false);
							ViewArea.setVisible(true);
							ViewArea.repaint();
							
							JScrollPane js = new JScrollPane(ViewArea);
							js.setBounds(65, 114, 386, 210);
							view.add(js, 0);
							js.repaint();
							js.setVisible(true);
						}
					};
				};
				GoToFind.addActionListener(check_lesson_view);
			}
		}
	}
	
	class LessonChoose implements ActionListener{
			
			private Manager client;
			private JPanel view;
			
			public LessonChoose(JPanel view, Manager client){
				this.view = view;
				this.client = client;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.removeAll();
				view.repaint();
				
				JButton ChooseLesson = new JButton("選修課程");
				ChooseLesson.setBackground(SystemColor.controlHighlight);
				ChooseLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				ChooseLesson.setBounds(176, 104, 153, 31);
				view.add(ChooseLesson);
				ChooseLesson.repaint();
				ChooseLesson choose = new ChooseLesson(view, client);
				ChooseLesson.addActionListener(choose);
				
				JButton deleteLessonChose = new JButton("課程退選");
				deleteLessonChose.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				deleteLessonChose.setBackground(SystemColor.controlHighlight);
				deleteLessonChose.setBounds(176, 178, 153, 31);
				view.add(deleteLessonChose);
				deleteLessonChose.repaint();
				DeleteChooseLesson delete = new DeleteChooseLesson(view, client);
				deleteLessonChose.addActionListener(delete);
			}
	}
	
	class ChooseLesson implements ActionListener{
			
			private Manager client;
			private JPanel view;
			
			public ChooseLesson(JPanel view, Manager client){
				this.view = view;
				this.client = client;
			}
			
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.removeAll();
				view.repaint();
				
				JLabel chooseNewOneTitle = new JLabel("選修課程");
				chooseNewOneTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				chooseNewOneTitle.setBounds(198, 53, 118, 23);
				view.add(chooseNewOneTitle);
				chooseNewOneTitle.repaint();
				
				JLabel stuAccount = new JLabel("請輸入選修學生帳號：");
				stuAccount.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				stuAccount.setBounds(28, 104, 211, 23);
				view.add(stuAccount);
				stuAccount.repaint();
				
				JTextField stuAccountIN = new JTextField();
				stuAccountIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				stuAccountIN.setBounds(232, 105, 245, 24);
				view.add(stuAccountIN);
				stuAccountIN.setColumns(10);
				stuAccountIN.repaint();
				
				JLabel lessonYear = new JLabel("請選擇課程之學期：");
				lessonYear.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				lessonYear.setBounds(48, 154, 180, 23);
				view.add(lessonYear);
				lessonYear.repaint();
				
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
	            
				JComboBox lessonYearIN = new JComboBox(yearChoice);
				lessonYearIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				lessonYearIN.setBackground(SystemColor.controlHighlight);
				lessonYearIN.setBounds(232, 153, 161, 29);
				view.add(lessonYearIN);
				lessonYearIN.repaint();
				
				JButton checkLesson = new JButton("查詢");
				checkLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkLesson.setBackground(SystemColor.controlHighlight);
				checkLesson.setBounds(396, 152, 81, 29);
				view.add(checkLesson);
				checkLesson.repaint();
				LessonChooseCheck check = new LessonChooseCheck(view, client, lessonYearIN, stuAccountIN);
				checkLesson.addActionListener(check);
				
				JButton goback = new JButton("回上一頁");
				goback.setBackground(SystemColor.controlHighlight);
				goback.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				goback.setBounds(42, 267, 153, 31);
				view.add(goback);
				goback.repaint();
				LessonChoose choose = new LessonChoose(view, client);
				goback.addActionListener(choose);
			}
	}
	
	class LessonChooseCheck implements ActionListener{
			
			private Manager client;
			private JPanel view;
			private JComboBox lessonYearIN;
			private JTextField stuAccountIN;
			
			public LessonChooseCheck(JPanel view, Manager client, JComboBox lessonYearIN, JTextField stuAccountIN){
				this.view = view;
				this.client = client;
				this.lessonYearIN = lessonYearIN;
				this.stuAccountIN = stuAccountIN;
			}
			
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = lessonYearIN.getSelectedIndex();
				String account = stuAccountIN.getText();
				
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
				String[] allLesson = client.showAllLesson(yearChoice[lessonYearIN.getSelectedIndex()]);
				
				view.removeAll();
				view.repaint();
				
				JLabel chooseNewOneTitle = new JLabel("選修課程");
				chooseNewOneTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				chooseNewOneTitle.setBounds(198, 53, 118, 23);
				view.add(chooseNewOneTitle);
				chooseNewOneTitle.repaint();
				
				JLabel stuAccount = new JLabel("請輸入選修學生帳號：");
				stuAccount.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				stuAccount.setBounds(28, 104, 211, 23);
				view.add(stuAccount);
				stuAccount.repaint();
				
				JTextField stuAccountIN = new JTextField();
				stuAccountIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				stuAccountIN.setBounds(232, 105, 245, 24);
				stuAccountIN.setText(account);
				view.add(stuAccountIN);
				stuAccountIN.setColumns(10);
				stuAccountIN.repaint();
				
				JLabel lessonYear = new JLabel("請選擇課程之學期：");
				lessonYear.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				lessonYear.setBounds(48, 154, 180, 23);
				view.add(lessonYear);
				lessonYear.repaint();
				
				JComboBox lessonYearIN = new JComboBox(yearChoice);
				lessonYearIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				lessonYearIN.setBackground(SystemColor.controlHighlight);
				lessonYearIN.setBounds(232, 153, 161, 29);
				lessonYearIN.setSelectedIndex(index);
				view.add(lessonYearIN);
				lessonYearIN.repaint();
				
				JButton checkLesson = new JButton("查詢");
				checkLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkLesson.setBackground(SystemColor.controlHighlight);
				checkLesson.setBounds(396, 152, 81, 29);
				view.add(checkLesson);
				checkLesson.repaint();
				LessonChooseCheck check = new LessonChooseCheck(view, client, lessonYearIN, stuAccountIN);
				checkLesson.addActionListener(check);
				
				JLabel lessonChooseNew = new JLabel("請選擇欲修習之課程：");
				lessonChooseNew.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				lessonChooseNew.setBounds(28, 209, 200, 23);
				view.add(lessonChooseNew);
				lessonChooseNew.repaint();
				
				String[] showallLesson = client.showAllLesson(yearChoice[lessonYearIN.getSelectedIndex()]);
				JComboBox lessonChooseIN = new JComboBox(showallLesson);
				lessonChooseIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				lessonChooseIN.setBackground(SystemColor.controlHighlight);
				lessonChooseIN.setBounds(232, 208, 245, 29);
				view.add(lessonChooseIN);
				lessonChooseIN.repaint();
				
				JButton goback = new JButton("回上一頁");
				goback.setBackground(SystemColor.controlHighlight);
				goback.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				goback.setBounds(42, 267, 153, 31);
				view.add(goback);
				goback.repaint();
				LessonChoose choose = new LessonChoose(view, client);
				goback.addActionListener(choose);
				
				JButton choseIT = new JButton("選課");
				choseIT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				choseIT.setBackground(SystemColor.controlHighlight);
				choseIT.setBounds(284, 267, 153, 31);
				view.add(choseIT);
				choseIT.repaint();
				ActionListener choose_lesson_GO = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Lesson chooseLessonGO = client.findLessonData(yearChoice[lessonYearIN.getSelectedIndex()], showallLesson[lessonChooseIN.getSelectedIndex()]);
						Student stuChoose = client.readStuFileID(stuAccountIN.getText());
						if (stuChoose != null) {
							boolean duckGoOK = client.chooseLesson(chooseLessonGO, stuChoose);
							ChooseNoteGUI duckDOIT = new ChooseNoteGUI();
							duckDOIT.run(duckGoOK);
						}
						else {
							ChooseNoteGUI duckDOIT = new ChooseNoteGUI();
							duckDOIT.run(false);
						}
						
						view.removeAll();
						view.repaint();
						
						JLabel chooseNewOneTitle = new JLabel("選修課程");
						chooseNewOneTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
						chooseNewOneTitle.setBounds(198, 53, 118, 23);
						view.add(chooseNewOneTitle);
						chooseNewOneTitle.repaint();
						
						JLabel stuAccount = new JLabel("請輸入選修學生帳號：");
						stuAccount.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
						stuAccount.setBounds(28, 104, 211, 23);
						view.add(stuAccount);
						stuAccount.repaint();
						
						JTextField stuAccountIN = new JTextField();
						stuAccountIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						stuAccountIN.setBounds(232, 105, 245, 24);
						view.add(stuAccountIN);
						stuAccountIN.setColumns(10);
						stuAccountIN.repaint();
						
						JLabel lessonYear = new JLabel("請選擇課程之學期：");
						lessonYear.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
						lessonYear.setBounds(48, 154, 180, 23);
						view.add(lessonYear);
						lessonYear.repaint();
						
						String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
						File folder = new File(lessonFolder);
			            File[] Lessonlist = folder.listFiles();
			            ArrayList<String> yearLesson = new ArrayList<String>();
			            for(int i = 0; i < Lessonlist.length; i++){
			            	yearLesson.add(Lessonlist[i].getName());
				        }
			            String[] yearChoice = new String[yearLesson.size()+1];
			            yearChoice[0] = "";
			            for (int j = 1; j < yearLesson.size()+1; j++) {
			            	yearChoice[j] = yearLesson.get(j-1);
			            }
			            
						JComboBox lessonYearIN = new JComboBox(yearChoice);
						lessonYearIN.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						lessonYearIN.setBackground(SystemColor.controlHighlight);
						lessonYearIN.setBounds(232, 153, 161, 29);
						view.add(lessonYearIN);
						lessonYearIN.repaint();
						
						JButton checkLesson = new JButton("查詢");
						checkLesson.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						checkLesson.setBackground(SystemColor.controlHighlight);
						checkLesson.setBounds(396, 152, 81, 29);
						view.add(checkLesson);
						checkLesson.repaint();
						LessonChooseCheck check = new LessonChooseCheck(view, client, lessonYearIN, stuAccountIN);
						checkLesson.addActionListener(check);
						
						JButton goback = new JButton("回上一頁");
						goback.setBackground(SystemColor.controlHighlight);
						goback.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						goback.setBounds(42, 267, 153, 31);
						view.add(goback);
						goback.repaint();
						LessonChoose choose = new LessonChoose(view, client);
						goback.addActionListener(choose);
					};
				};
				choseIT.addActionListener(choose_lesson_GO);
			}
	}
	
	class DeleteChooseLesson implements ActionListener{
		
		private Manager client;
		private JPanel view;
		
		public DeleteChooseLesson(JPanel view, Manager client){
			this.view = view;
			this.client = client;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.removeAll();
			view.repaint();
			
			JLabel deleteALessonTitle = new JLabel("課程退選");
			deleteALessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			deleteALessonTitle.setBounds(198, 53, 118, 23);
			view.add(deleteALessonTitle);
			deleteALessonTitle.repaint();
			
			JLabel stuAccount_1 = new JLabel("請輸入選修學生帳號：");
			stuAccount_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			stuAccount_1.setBounds(28, 104, 211, 23);
			view.add(stuAccount_1);
			stuAccount_1.repaint();
			
			JTextField stuAccountIN_1 = new JTextField();
			stuAccountIN_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			stuAccountIN_1.setBounds(232, 105, 245, 24);
			view.add(stuAccountIN_1);
			stuAccountIN_1.setColumns(10);
			stuAccountIN_1.repaint();
			
			JLabel lessonYear_1 = new JLabel("請選擇課程之學期：");
			lessonYear_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			lessonYear_1.setBounds(48, 154, 180, 23);
			view.add(lessonYear_1);
			lessonYear_1.repaint();
			
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
            
			JComboBox lessonYearIN_1 = new JComboBox(yearChoice);
			lessonYearIN_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			lessonYearIN_1.setBackground(SystemColor.controlHighlight);
			lessonYearIN_1.setBounds(232, 153, 161, 29);
			view.add(lessonYearIN_1);
			lessonYearIN_1.repaint();
			
			JButton checkLesson_1 = new JButton("查詢");
			checkLesson_1.setBackground(SystemColor.controlHighlight);
			checkLesson_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			checkLesson_1.setBounds(396, 152, 81, 29);
			view.add(checkLesson_1);
			checkLesson_1.repaint();
			LessonDeleteCheck check = new LessonDeleteCheck(view, client, lessonYearIN_1, stuAccountIN_1);
			checkLesson_1.addActionListener(check);
			
			JButton goback = new JButton("回上一頁");
			goback.setBackground(SystemColor.controlHighlight);
			goback.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			goback.setBounds(42, 267, 153, 31);
			view.add(goback);
			goback.repaint();
			LessonChoose choose = new LessonChoose(view, client);
			goback.addActionListener(choose);
		}
	}
	
	class LessonDeleteCheck implements ActionListener{
		
		private Manager client;
		private JPanel view;
		private JComboBox lessonYearIN_1;
		private JTextField stuAccountIN_1;
		
		public LessonDeleteCheck(JPanel view, Manager client, JComboBox lessonYearIN_1, JTextField stuAccountIN_1){
			this.view = view;
			this.client = client;
			this.lessonYearIN_1 = lessonYearIN_1;
			this.stuAccountIN_1 = stuAccountIN_1;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int index = lessonYearIN_1.getSelectedIndex();
			String account = stuAccountIN_1.getText();
			
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
			String[] allLesson = client.showAllLesson(yearChoice[lessonYearIN_1.getSelectedIndex()]);
			
			view.removeAll();
			view.repaint();
			
			JLabel deleteALessonTitle = new JLabel("課程退選");
			deleteALessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			deleteALessonTitle.setBounds(198, 53, 118, 23);
			view.add(deleteALessonTitle);
			deleteALessonTitle.repaint();
			
			JLabel stuAccount_1 = new JLabel("請輸入選修學生帳號：");
			stuAccount_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			stuAccount_1.setBounds(28, 104, 211, 23);
			view.add(stuAccount_1);
			stuAccount_1.repaint();
			
			JTextField stuAccountIN_1 = new JTextField();
			stuAccountIN_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			stuAccountIN_1.setBounds(232, 105, 245, 24);
			stuAccountIN_1.setText(account);
			view.add(stuAccountIN_1);
			stuAccountIN_1.setColumns(10);
			stuAccountIN_1.repaint();
			
			JLabel lessonYear_1 = new JLabel("請選擇課程之學期：");
			lessonYear_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			lessonYear_1.setBounds(48, 154, 180, 23);
			view.add(lessonYear_1);
			lessonYear_1.repaint();
			
			JComboBox lessonYearIN_1 = new JComboBox(yearChoice);
			lessonYearIN_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			lessonYearIN_1.setBackground(SystemColor.controlHighlight);
			lessonYearIN_1.setBounds(232, 153, 161, 29);
			lessonYearIN_1.setSelectedIndex(index);
			view.add(lessonYearIN_1);
			lessonYearIN_1.repaint();
			
			JButton checkLesson_1 = new JButton("查詢");
			checkLesson_1.setBackground(SystemColor.controlHighlight);
			checkLesson_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			checkLesson_1.setBounds(396, 152, 81, 29);
			view.add(checkLesson_1);
			checkLesson_1.repaint();
			LessonDeleteCheck check = new LessonDeleteCheck(view, client, lessonYearIN_1, stuAccountIN_1);
			checkLesson_1.addActionListener(check);
			
			JButton goback = new JButton("回上一頁");
			goback.setBackground(SystemColor.controlHighlight);
			goback.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			goback.setBounds(42, 267, 153, 31);
			view.add(goback);
			goback.repaint();
			LessonChoose choose = new LessonChoose(view, client);
			goback.addActionListener(choose);
			
			Student stuDeleteLesson = client.readStuFileID(stuAccountIN_1.getText());
			if (stuDeleteLesson != null) {
				ArrayList<String> place = new ArrayList<String>();
				for (int i = 0; i < stuDeleteLesson.lesson.size(); i++) {
					ArrayList<String> getData = stuDeleteLesson.lesson.get(i);
					if (getData.get(0).equals(yearChoice[lessonYearIN_1.getSelectedIndex()])) {
						place = getData;
						break;
					}
				}
				int x = stuDeleteLesson.lesson.indexOf(place);
				String[] showallLesson;
				if (x != -1) {
					ArrayList<String> readLesson = stuDeleteLesson.lesson.get(x);
					showallLesson = new String[readLesson.size()];
					showallLesson[0] = " ";
					for (int p = 1; p < readLesson.size(); p++) {
						showallLesson[p] = readLesson.get(p);
					}
				}
				else {
					showallLesson = new String[1];
					showallLesson[0] = " ";
				}
				
				JLabel lessonChooseNew_1 = new JLabel("請選擇欲退選之課程：");
				lessonChooseNew_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				lessonChooseNew_1.setBounds(28, 209, 200, 23);
				view.add(lessonChooseNew_1);
				lessonChooseNew_1.repaint();
				
				JComboBox lessonChooseIN_1 = new JComboBox(showallLesson);
				lessonChooseIN_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				lessonChooseIN_1.setBackground(SystemColor.controlHighlight);
				lessonChooseIN_1.setBounds(232, 208, 245, 29);
				view.add(lessonChooseIN_1);
				lessonChooseIN_1.repaint();
				
				JButton deleteITTTTTT = new JButton("退選");
				deleteITTTTTT.setForeground(Color.RED);
				deleteITTTTTT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				deleteITTTTTT.setBackground(SystemColor.controlHighlight);
				deleteITTTTTT.setBounds(284, 267, 153, 31);
				view.add(deleteITTTTTT);
				deleteITTTTTT.repaint();
				ActionListener delete_lesson_GO = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Lesson lessonDeleteGO = client.findLessonData(yearChoice[lessonYearIN_1.getSelectedIndex()], showallLesson[lessonChooseIN_1.getSelectedIndex()]);
						boolean duckduckGoOk = client.removeLesson(lessonDeleteGO, stuDeleteLesson);
						DeleteLessonNoteGUI duckduckDOIT = new DeleteLessonNoteGUI();
						duckduckDOIT.run(duckduckGoOk);
						
						view.removeAll();
						view.repaint();
						
						JLabel deleteALessonTitle = new JLabel("課程退選");
						deleteALessonTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
						deleteALessonTitle.setBounds(198, 53, 118, 23);
						view.add(deleteALessonTitle);
						deleteALessonTitle.repaint();
						
						JLabel stuAccount_1 = new JLabel("請輸入選修學生帳號：");
						stuAccount_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
						stuAccount_1.setBounds(28, 104, 211, 23);
						view.add(stuAccount_1);
						stuAccount_1.repaint();
						
						JTextField stuAccountIN_1 = new JTextField();
						stuAccountIN_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						stuAccountIN_1.setBounds(232, 105, 245, 24);
						view.add(stuAccountIN_1);
						stuAccountIN_1.setColumns(10);
						stuAccountIN_1.repaint();
						
						JLabel lessonYear_1 = new JLabel("請選擇課程之學期：");
						lessonYear_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
						lessonYear_1.setBounds(48, 154, 180, 23);
						view.add(lessonYear_1);
						lessonYear_1.repaint();
						
						JComboBox lessonYearIN_1 = new JComboBox(yearChoice);
						lessonYearIN_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						lessonYearIN_1.setBackground(SystemColor.controlHighlight);
						lessonYearIN_1.setBounds(232, 153, 161, 29);
						view.add(lessonYearIN_1);
						lessonYearIN_1.repaint();
						
						JButton checkLesson_1 = new JButton("查詢");
						checkLesson_1.setBackground(SystemColor.controlHighlight);
						checkLesson_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						checkLesson_1.setBounds(396, 152, 81, 29);
						view.add(checkLesson_1);
						checkLesson_1.repaint();
						LessonDeleteCheck check = new LessonDeleteCheck(view, client, lessonYearIN_1, stuAccountIN_1);
						checkLesson_1.addActionListener(check);
						
						JButton goback = new JButton("回上一頁");
						goback.setBackground(SystemColor.controlHighlight);
						goback.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
						goback.setBounds(42, 267, 153, 31);
						view.add(goback);
						goback.repaint();
						LessonChoose choose = new LessonChoose(view, client);
						goback.addActionListener(choose);
					};
				};
				deleteITTTTTT.addActionListener(delete_lesson_GO);
			}
		}
	}
	
	class GradeManager implements ActionListener{
		
		private Account client;
		private JPanel view;
		private JComboBox identityIN;
		
		public GradeManager(JPanel view, Account client, JComboBox identity){
			this.view = view;
			this.client = client;
			this.identityIN = identity;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.removeAll();
			view.repaint();
			
			int identity = identityIN.getSelectedIndex();
			String[] option = new String[4];
			option[0] = " ";
			option[1] = "管理員";
			option[2] = "教授";
			option[3] = "學生";
			Manager user = (Manager)client.changeType(option[1]);
			
			view.removeAll();
			view.repaint();
			if (client.identity.equals(option[1])) {
				JButton logINgrade = new JButton("登錄成績");
				logINgrade.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				logINgrade.setBackground(SystemColor.controlHighlight);
				logINgrade.setBounds(173, 55, 153, 31);
				view.add(logINgrade);
				logINgrade.repaint();
				SetGrade set = new SetGrade(view, user);
				logINgrade.addActionListener(set);
				
				JButton gradeTXT = new JButton("產生成績單");
				gradeTXT.setBackground(SystemColor.controlHighlight);
				gradeTXT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				gradeTXT.setBounds(173, 124, 153, 31);
				view.add(gradeTXT);
				gradeTXT.repaint();
				GradeTXT TXTmake = new GradeTXT(view, user);
				gradeTXT.addActionListener(TXTmake);
				
				JButton readStuGrade = new JButton("查看學生成績");
				readStuGrade.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				readStuGrade.setBackground(SystemColor.controlHighlight);
				readStuGrade.setBounds(173, 193, 153, 31);
				view.add(readStuGrade);
				readStuGrade.repaint();
				StuGradeRead readStu = new StuGradeRead(view, user);
				readStuGrade.addActionListener(readStu);
				
				JButton readLessonGrade = new JButton("查看課程成績");
				readLessonGrade.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				readLessonGrade.setBackground(SystemColor.controlHighlight);
				readLessonGrade.setBounds(173, 262, 153, 31);
				view.add(readLessonGrade);
				readLessonGrade.repaint();
				LessonGradeRead readLesson = new LessonGradeRead(view, user);
				readLessonGrade.addActionListener(readLesson);
			}
			
			else if(client.identity.equals(option[2])) {
				JButton logINgrade = new JButton("登錄成績");
				logINgrade.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				logINgrade.setBackground(SystemColor.controlHighlight);
				logINgrade.setBounds(173, 100, 153, 31);
				view.add(logINgrade);
				logINgrade.repaint();
				SetGradePro set = new SetGradePro(view, user, client);
				logINgrade.addActionListener(set);
				
				JButton readLessonGrade = new JButton("查看課程成績");
				readLessonGrade.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				readLessonGrade.setBackground(SystemColor.controlHighlight);
				readLessonGrade.setBounds(173, 200, 153, 31);
				view.add(readLessonGrade);
				readLessonGrade.repaint();
				LessonGradeReadPro read = new LessonGradeReadPro(view, user, client);
				readLessonGrade.addActionListener(read);
			}
			
			else if(client.identity.equals(option[3])) {
				JButton gradeTXT = new JButton("產生成績單");
				gradeTXT.setBackground(SystemColor.controlHighlight);
				gradeTXT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				gradeTXT.setBounds(173, 124, 153, 31);
				view.add(gradeTXT);
				gradeTXT.repaint();
				GradeTXTStu grade = new GradeTXTStu(view, user, client);
				gradeTXT.addActionListener(grade);
				
				JButton readStuGrade = new JButton("查看成績");
				readStuGrade.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				readStuGrade.setBackground(SystemColor.controlHighlight);
				readStuGrade.setBounds(173, 200, 153, 31);
				view.add(readStuGrade);
				readStuGrade.repaint();
				StuGradeReadSelf read = new StuGradeReadSelf(view, user, client);
				readStuGrade.addActionListener(read);
			}
		}
	}
	
	class SetGrade implements ActionListener{
		
		private Manager client;
		private JPanel view;
		
		public SetGrade(JPanel view, Manager client){
			this.view = view;
			this.client = client;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.removeAll();
			view.repaint();
			
			JLabel putGradeINTitle = new JLabel("登錄成績");
			putGradeINTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			putGradeINTitle.setBounds(210, 37, 106, 23);
			view.add(putGradeINTitle);
			putGradeINTitle.repaint();
			
			JLabel yearChooooose = new JLabel("請選擇學期：");
			yearChooooose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			yearChooooose.setBounds(73, 86, 128, 23);
			view.add(yearChooooose);
			yearChooooose.repaint();
			
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
            
			JComboBox YEARcomboBox = new JComboBox(yearChoice);
			YEARcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			YEARcomboBox.setBackground(SystemColor.controlHighlight);
			YEARcomboBox.setBounds(192, 85, 173, 29);
			view.add(YEARcomboBox);
			YEARcomboBox.repaint();
			
			JButton checkIT = new JButton("查詢");
			checkIT.setBackground(SystemColor.controlHighlight);
			checkIT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			checkIT.setBounds(377, 84, 80, 31);
			view.add(checkIT);
			checkIT.repaint();
			SetGradeCheck check = new SetGradeCheck(view, client, YEARcomboBox);
			checkIT.addActionListener(check);
		}
	}
	
	class SetGradeCheck implements ActionListener{
			
			private Manager client;
			private JPanel view;
			private JComboBox YEARcomboBox;
			
			public SetGradeCheck(JPanel view, Manager client, JComboBox YEARcomboBox){
				this.view = view;
				this.client = client;
				this.YEARcomboBox = YEARcomboBox;
			}
			
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = YEARcomboBox.getSelectedIndex();
				
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
	            
				String[] allLesson = client.showAllLesson(yearChoice[YEARcomboBox.getSelectedIndex()]);
				
				view.removeAll();
				view.repaint();
				
				JLabel putGradeINTitle = new JLabel("登錄成績");
				putGradeINTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				putGradeINTitle.setBounds(210, 37, 106, 23);
				view.add(putGradeINTitle);
				putGradeINTitle.repaint();
				
				JLabel yearChooooose = new JLabel("請選擇學期：");
				yearChooooose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearChooooose.setBounds(73, 86, 128, 23);
				view.add(yearChooooose);
				yearChooooose.repaint();
				
				JComboBox YEARcomboBox = new JComboBox(yearChoice);
				YEARcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				YEARcomboBox.setBackground(SystemColor.controlHighlight);
				YEARcomboBox.setBounds(192, 85, 173, 29);
				YEARcomboBox.setSelectedIndex(index);
				view.add(YEARcomboBox);
				YEARcomboBox.repaint();
				
				JButton checkIT = new JButton("查詢");
				checkIT.setBackground(SystemColor.controlHighlight);
				checkIT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkIT.setBounds(377, 84, 80, 31);
				view.add(checkIT);
				checkIT.repaint();
				SetGradeCheck check = new SetGradeCheck(view, client, YEARcomboBox);
				checkIT.addActionListener(check);
				
				if (YEARcomboBox.getSelectedIndex() != 0) {
					JLabel lessonChoose = new JLabel("請選擇課程：");
					lessonChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					lessonChoose.setBounds(73, 129, 128, 23);
					view.add(lessonChoose);
					lessonChoose.repaint();
					
					JComboBox LESSONcomboBox = new JComboBox(allLesson);
					LESSONcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					LESSONcomboBox.setBackground(SystemColor.controlHighlight);
					LESSONcomboBox.setBounds(192, 128, 173, 29);
					view.add(LESSONcomboBox);
					LESSONcomboBox.repaint();
					
					JButton checkGO = new JButton("查看");
					checkGO.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					checkGO.setBackground(SystemColor.controlHighlight);
					checkGO.setBounds(377, 127, 80, 31);
					view.add(checkGO);
					checkGO.repaint();
					SetGradeStuCheck set = new SetGradeStuCheck(view, client, YEARcomboBox, LESSONcomboBox);
					checkGO.addActionListener(set);
				}
			}
	}
	
	class SetGradeStuCheck implements ActionListener{
		
		private Manager client;
		private JPanel view;
		private JComboBox YEARcomboBox;
		private JComboBox LESSONcomboBox;
		
		public SetGradeStuCheck(JPanel view, Manager client, JComboBox YEARcomboBox, JComboBox LESSONcomboBox){
			this.view = view;
			this.client = client;
			this.YEARcomboBox = YEARcomboBox;
			this.LESSONcomboBox = LESSONcomboBox;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int index_year = YEARcomboBox.getSelectedIndex();
			int index_lesson = LESSONcomboBox.getSelectedIndex();
			
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
            
			String[] allLesson = client.showAllLesson(yearChoice[YEARcomboBox.getSelectedIndex()]);
			
			view.removeAll();
			view.repaint();
			
			JLabel putGradeINTitle = new JLabel("登錄成績");
			putGradeINTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			putGradeINTitle.setBounds(210, 37, 106, 23);
			view.add(putGradeINTitle);
			putGradeINTitle.repaint();
			
			JLabel yearChooooose = new JLabel("請選擇學期：");
			yearChooooose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			yearChooooose.setBounds(73, 86, 128, 23);
			view.add(yearChooooose);
			yearChooooose.repaint();
			
			JComboBox YEARcomboBox = new JComboBox(yearChoice);
			YEARcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			YEARcomboBox.setBackground(SystemColor.controlHighlight);
			YEARcomboBox.setBounds(192, 85, 173, 29);
			YEARcomboBox.setSelectedIndex(index_year);
			view.add(YEARcomboBox);
			YEARcomboBox.repaint();
			
			JButton checkIT = new JButton("查詢");
			checkIT.setBackground(SystemColor.controlHighlight);
			checkIT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			checkIT.setBounds(377, 84, 80, 31);
			view.add(checkIT);
			checkIT.repaint();
			SetGradeCheck check = new SetGradeCheck(view, client, YEARcomboBox);
			checkIT.addActionListener(check);
			
			JLabel lessonChoose = new JLabel("請選擇課程：");
			lessonChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			lessonChoose.setBounds(73, 129, 128, 23);
			view.add(lessonChoose);
			lessonChoose.repaint();
			
			JComboBox LESSONcomboBox = new JComboBox(allLesson);
			LESSONcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			LESSONcomboBox.setBackground(SystemColor.controlHighlight);
			LESSONcomboBox.setBounds(192, 128, 173, 29);
			LESSONcomboBox.setSelectedIndex(index_lesson);
			view.add(LESSONcomboBox);
			LESSONcomboBox.repaint();
			
			JButton checkGO = new JButton("查看");
			checkGO.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			checkGO.setBackground(SystemColor.controlHighlight);
			checkGO.setBounds(377, 127, 80, 31);
			view.add(checkGO);
			checkGO.repaint();
			SetGradeStuCheck set = new SetGradeStuCheck(view, client, YEARcomboBox, LESSONcomboBox);
			checkGO.addActionListener(set);
			
			if (LESSONcomboBox.getSelectedIndex() != 0) {
				JLabel stuChoose = new JLabel("請選擇學生：");
				stuChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				stuChoose.setBounds(73, 172, 128, 23);
				view.add(stuChoose);
				stuChoose.repaint();
				
				Lesson choiceLesson = client.findLessonData(yearChoice[YEARcomboBox.getSelectedIndex()], allLesson[LESSONcomboBox.getSelectedIndex()]);
				String[] stuList = new String[choiceLesson.list.size()+1];
				stuList[0] = " ";
				for (int i = 0; i < choiceLesson.list.size(); i++) {
					stuList[i+1] = choiceLesson.list.get(i).name;
				}
				JComboBox STUcomboBox = new JComboBox(stuList);
				STUcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				STUcomboBox.setBackground(SystemColor.controlHighlight);
				STUcomboBox.setBounds(192, 171, 173, 29);
				view.add(STUcomboBox);
				STUcomboBox.repaint();
				
				JLabel GRADEin = new JLabel("請輸入成績：");
				GRADEin.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				GRADEin.setBounds(73, 215, 128, 23);
				view.add(GRADEin);
				GRADEin.repaint();
				
				JTextField gradeININ = new JTextField();
				gradeININ.setHorizontalAlignment(SwingConstants.CENTER);
				gradeININ.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				gradeININ.setBounds(192, 215, 173, 24);
				view.add(gradeININ);
				gradeININ.setColumns(10);
				gradeININ.repaint();
				
				JButton INgrade = new JButton("登錄");
				INgrade.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				INgrade.setBackground(SystemColor.controlHighlight);
				INgrade.setBounds(279, 262, 153, 31);
				view.add(INgrade);
				INgrade.repaint();
				ActionListener gradeINgogogo = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (STUcomboBox.getSelectedIndex() != 0 && !gradeININ.equals("")) {
							boolean gradeINok = client.setGrade(choiceLesson, stuList[STUcomboBox.getSelectedIndex()], gradeININ.getText());
							GradeInNoteGUI haveGRADE = new GradeInNoteGUI();
							haveGRADE.run(gradeINok);
							
							view.removeAll();
							view.repaint();
							
							JLabel putGradeINTitle = new JLabel("登錄成績");
							putGradeINTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
							putGradeINTitle.setBounds(210, 37, 106, 23);
							view.add(putGradeINTitle);
							putGradeINTitle.repaint();
							
							JLabel yearChooooose = new JLabel("請選擇學期：");
							yearChooooose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
							yearChooooose.setBounds(73, 86, 128, 23);
							view.add(yearChooooose);
							yearChooooose.repaint();
							
							String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
							File folder = new File(lessonFolder);
				            File[] Lessonlist = folder.listFiles();
				            ArrayList<String> yearLesson = new ArrayList<String>();
				            for(int i = 0; i < Lessonlist.length; i++){
				            	yearLesson.add(Lessonlist[i].getName());
					        }
				            String[] yearChoice = new String[yearLesson.size()+1];
				            yearChoice[0] = "";
				            for (int j = 1; j < yearLesson.size()+1; j++) {
				            	yearChoice[j] = yearLesson.get(j-1);
				            }
				            
							JComboBox YEARcomboBox = new JComboBox(yearChoice);
							YEARcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
							YEARcomboBox.setBackground(SystemColor.controlHighlight);
							YEARcomboBox.setBounds(192, 85, 173, 29);
							view.add(YEARcomboBox);
							YEARcomboBox.repaint();
							
							JButton checkIT = new JButton("查詢");
							checkIT.setBackground(SystemColor.controlHighlight);
							checkIT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
							checkIT.setBounds(377, 84, 80, 31);
							view.add(checkIT);
							checkIT.repaint();
							SetGradeCheck check = new SetGradeCheck(view, client, YEARcomboBox);
							checkIT.addActionListener(check);
						}
					};
				};
				INgrade.addActionListener(gradeINgogogo);
			}
		}
	}
	
	class GradeTXT implements ActionListener{
			
			private Manager client;
			private JPanel view;
			
			public GradeTXT(JPanel view, Manager client){
				this.view = view;
				this.client = client;
			}
			
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.removeAll();
				view.repaint();
				
				JLabel GradeTXTTitle = new JLabel("產生成績單");
				GradeTXTTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				GradeTXTTitle.setBounds(192, 71, 137, 23);
				view.add(GradeTXTTitle);
				GradeTXTTitle.repaint();
				
				JLabel yearDuck = new JLabel("請選擇學期：");
				yearDuck.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearDuck.setBounds(109, 130, 128, 23);
				view.add(yearDuck);
				yearDuck.repaint();
				
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
	            
				JComboBox YEARduckBox = new JComboBox(yearChoice);
				YEARduckBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				YEARduckBox.setBackground(SystemColor.controlHighlight);
				YEARduckBox.setBounds(228, 129, 173, 29);
				view.add(YEARduckBox);
				YEARduckBox.repaint();
				
				JLabel STUin = new JLabel("請輸入學生帳號：");
				STUin.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				STUin.setBounds(69, 175, 168, 23);
				view.add(STUin);
				STUin.repaint();
				
				JTextField STUinin = new JTextField();
				STUinin.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				STUinin.setBounds(228, 175, 173, 24);
				view.add(STUinin);
				STUinin.setColumns(10);
				STUinin.repaint();
				
				JButton OutTXT = new JButton("產生成績單");
				OutTXT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				OutTXT.setBackground(SystemColor.controlHighlight);
				OutTXT.setBounds(279, 236, 153, 31);
				view.add(OutTXT);
				OutTXT.repaint();
				ActionListener MAKEgradeTXT = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (YEARduckBox.getSelectedIndex() != 0 && !STUinin.getText().equals("")) {
							Student stu = client.readStuFileID(STUinin.getText());
							if (stu == null) {
								boolean gradeMAKEok = false;
								GradeTXTNoteGUI makeITdone = new GradeTXTNoteGUI();
								makeITdone.run(gradeMAKEok);
								
								YEARduckBox.setSelectedIndex(0);
								STUinin.setText("");
							}
							else {
								boolean gradeMAKEok = client.buildTranscript(yearChoice[YEARduckBox.getSelectedIndex()], stu);
								GradeTXTNoteGUI makeITdone = new GradeTXTNoteGUI();
								makeITdone.run(gradeMAKEok);
								
								YEARduckBox.setSelectedIndex(0);
								STUinin.setText("");
							}
						}
					};
				};
				OutTXT.addActionListener(MAKEgradeTXT);
			}
	}
	
	class StuGradeRead implements ActionListener{
		
		private Manager client;
		private JPanel view;
		
		public StuGradeRead(JPanel view, Manager client){
			this.view = view;
			this.client = client;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.removeAll();
			view.repaint();
			
			JLabel GradeReadStuTitle = new JLabel("查看學生成績");
			GradeReadStuTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			GradeReadStuTitle.setBounds(185, 15, 155, 23);
			view.add(GradeReadStuTitle);
			GradeReadStuTitle.repaint();
			
			JLabel year = new JLabel("請選擇學期：");
			year.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			year.setBounds(99, 54, 127, 23);
			view.add(year);
			year.repaint();
			
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
            
			JComboBox YEARBox = new JComboBox(yearChoice);
			YEARBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			YEARBox.setBackground(SystemColor.controlHighlight);
			YEARBox.setBounds(220, 51, 210, 29);
			view.add(YEARBox);
			YEARBox.repaint();
			
			JLabel STUin = new JLabel("請輸入學生帳號：");
			STUin.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			STUin.setBounds(59, 86, 167, 23);
			view.add(STUin);
			STUin.repaint();
			
			JTextField STUinin = new JTextField();
			STUinin.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			STUinin.setBounds(220, 85, 210, 29);
			view.add(STUinin);
			STUinin.setColumns(10);
			STUinin.repaint();
			
			JButton readITstuGO = new JButton("查看");
			readITstuGO.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			readITstuGO.setBackground(SystemColor.controlHighlight);
			readITstuGO.setBounds(273, 311, 127, 31);
			view.add(readITstuGO);
			readITstuGO.repaint();
			ActionListener read_grade_stu = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (YEARBox.getSelectedIndex() != 0 && !STUinin.getText().equals("")) {
						Student stu = client.readStuFileID(STUinin.getText());
						if (stu != null) {
							String wordToprint = client.findStudentGrade(stu, yearChoice[YEARBox.getSelectedIndex()]);
							
							JTextArea ViewArea = new JTextArea();
							ViewArea.setText(wordToprint);
							ViewArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
							ViewArea.setBounds(65, 114, 386, 193);
							view.add(ViewArea);
							ViewArea.setEditable(false);
							ViewArea.setVisible(true);
							ViewArea.repaint();
							
							JScrollPane js = new JScrollPane(ViewArea);
							js.setBounds(65, 114, 386, 193);
							view.add(js,0);
							js.repaint();
							js.setVisible(true);
						}
					}
				};
			};
			readITstuGO.addActionListener(read_grade_stu);
		}
	}
	
	class LessonGradeRead implements ActionListener{
			
			private Manager client;
			private JPanel view;
			
			public LessonGradeRead(JPanel view, Manager client){
				this.view = view;
				this.client = client;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.removeAll();
				view.repaint();
				
				JLabel GradeReadStuTitle = new JLabel("查看課程成績");
				GradeReadStuTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				GradeReadStuTitle.setBounds(185, 15, 155, 23);
				view.add(GradeReadStuTitle);
				GradeReadStuTitle.repaint();
				
				JLabel year = new JLabel("請選擇學期：");
				year.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				year.setBounds(52, 51, 127, 23);
				view.add(year);
				year.repaint();
				
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
				
				JComboBox YEARBox = new JComboBox(yearChoice);
				YEARBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				YEARBox.setBackground(SystemColor.controlHighlight);
				YEARBox.setBounds(173, 48, 210, 29);
				view.add(YEARBox);
				YEARBox.repaint();
				
				JButton checkLessonGO = new JButton("查詢");
				checkLessonGO.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkLessonGO.setBackground(SystemColor.controlHighlight);
				checkLessonGO.setBounds(401, 47, 77, 29);
				view.add(checkLessonGO);
				checkLessonGO.repaint();
				ReadGradeCheck go = new ReadGradeCheck(view, client, YEARBox);
				checkLessonGO.addActionListener(go);
			}
	}
	
	class ReadGradeCheck implements ActionListener{
		
		private Manager client;
		private JPanel view;
		private JComboBox YEARBox;
		
		public ReadGradeCheck(JPanel view, Manager client, JComboBox YEARBox){
			this.view = view;
			this.client = client;
			this.YEARBox = YEARBox;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int index = YEARBox.getSelectedIndex();
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
			String[] allLesson = client.showAllLesson(yearChoice[YEARBox.getSelectedIndex()]);
			
			view.removeAll();
			view.repaint();
			
			JLabel GradeReadStuTitle = new JLabel("查看課程成績");
			GradeReadStuTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			GradeReadStuTitle.setBounds(185, 15, 155, 23);
			view.add(GradeReadStuTitle);
			GradeReadStuTitle.repaint();
			
			JLabel year = new JLabel("請選擇學期：");
			year.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			year.setBounds(52, 51, 127, 23);
			view.add(year);
			year.repaint();
			
			JComboBox YEARBox = new JComboBox(yearChoice);
			YEARBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			YEARBox.setBackground(SystemColor.controlHighlight);
			YEARBox.setBounds(173, 48, 210, 29);
			YEARBox.setSelectedIndex(index);
			view.add(YEARBox);
			YEARBox.repaint();
			
			JButton checkLessonGO = new JButton("查詢");
			checkLessonGO.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			checkLessonGO.setBackground(SystemColor.controlHighlight);
			checkLessonGO.setBounds(401, 47, 77, 29);
			view.add(checkLessonGO);
			checkLessonGO.repaint();
			ReadGradeCheck go = new ReadGradeCheck(view, client, YEARBox);
			checkLessonGO.addActionListener(go);
			
			if (YEARBox.getSelectedIndex() != 0) {
				JLabel lesson = new JLabel("請選擇課程：");
				lesson.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				lesson.setBounds(52, 88, 127, 23);
				view.add(lesson);
				lesson.repaint();
				
				JComboBox LESSONBox = new JComboBox(allLesson);
				LESSONBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				LESSONBox.setBackground(SystemColor.controlHighlight);
				LESSONBox.setBounds(173, 87, 210, 29);
				view.add(LESSONBox);
				LESSONBox.repaint();
				
				JButton readITlessonGO = new JButton("查看");
				readITlessonGO.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				readITlessonGO.setBackground(SystemColor.controlHighlight);
				readITlessonGO.setBounds(401, 88, 77, 29);
				view.add(readITlessonGO);
				readITlessonGO.repaint();
				ActionListener readITlesson = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (LESSONBox.getSelectedIndex() != 0) {
							String wordToprint = client.findLessonGrade(yearChoice[YEARBox.getSelectedIndex()], allLesson[LESSONBox.getSelectedIndex()]);
							
							JTextArea ViewArea = new JTextArea();
							ViewArea.setText(wordToprint);
							ViewArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
							ViewArea.setBounds(65, 114, 386, 193);
							view.add(ViewArea);
							ViewArea.setEditable(false);
							ViewArea.setVisible(true);
							ViewArea.repaint();
							
							JScrollPane js = new JScrollPane(ViewArea);
							js.setBounds(65, 118, 386, 193);
							view.add(js, 0);
							js.repaint();
							js.setVisible(true);
						}
					};
				};
				readITlessonGO.addActionListener(readITlesson);
			}
		}
	}
	
	class SetGradePro implements ActionListener{
			
			private Account account;
			private Manager client;
			private JPanel view;
			
			public SetGradePro(JPanel view, Manager client, Account account){
				this.account = account;
				this.view = view;
				this.client = client;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.removeAll();
				view.repaint();
			
				JLabel putGradeINTitle = new JLabel("登錄成績");
				putGradeINTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				putGradeINTitle.setBounds(210, 37, 106, 23);
				view.add(putGradeINTitle);
				putGradeINTitle.repaint();
				
				JLabel yearChoose = new JLabel("請選擇學期：");
				yearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearChoose.setBounds(73, 86, 128, 23);
				view.add(yearChoose);
				yearChoose.repaint();
				
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
	            
				JComboBox YEARcomboBox = new JComboBox(yearChoice);
				YEARcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				YEARcomboBox.setBackground(SystemColor.controlHighlight);
				YEARcomboBox.setBounds(192, 85, 173, 29);
				view.add(YEARcomboBox);
				YEARcomboBox.repaint();
				
				JButton checkIT = new JButton("查詢");
				checkIT.setBackground(SystemColor.controlHighlight);
				checkIT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkIT.setBounds(377, 84, 80, 31);
				view.add(checkIT);
				checkIT.repaint();
				SetGradeCheckPro check = new SetGradeCheckPro(view, client, account, YEARcomboBox);
				checkIT.addActionListener(check);
			}
	}
	
	class SetGradeCheckPro implements ActionListener{
		
		private Account account;
		private Manager client;
		private JPanel view;
		private JComboBox YEARcomboBox;
		
		public SetGradeCheckPro(JPanel view, Manager client, Account account, JComboBox YEARcomboBox){
			this.account = account;
			this.view = view;
			this.client = client;
			this.YEARcomboBox = YEARcomboBox;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int index = YEARcomboBox.getSelectedIndex();
			
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
            
            if (YEARcomboBox.getSelectedIndex() != 0) {
				Professor stuDeleteLesson = client.readProFileID(account.account);
				ArrayList<String> place = new ArrayList<String>();
				for (int i = 0; i < stuDeleteLesson.lesson.size(); i++) {
					ArrayList<String> getData = stuDeleteLesson.lesson.get(i);
					if (getData.get(0).equals(yearChoice[YEARcomboBox.getSelectedIndex()])) {
						place = getData;
						break;
					}
				}
				String[] allLesson;
				int x = stuDeleteLesson.lesson.indexOf(place);
				if (x != -1) {
					ArrayList<String> readLesson = stuDeleteLesson.lesson.get(x);
					allLesson = new String[readLesson.size()];
					allLesson[0] = " ";
					for (int p = 1; p < readLesson.size(); p++) {
						allLesson[p] = readLesson.get(p);
					}
				}
				else {
					allLesson = new String[1];
					allLesson[0] = " ";
				}
				
				view.removeAll();
				view.repaint();
				
				JLabel putGradeINTitle = new JLabel("登錄成績");
				putGradeINTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				putGradeINTitle.setBounds(210, 37, 106, 23);
				view.add(putGradeINTitle);
				putGradeINTitle.repaint();
				
				JLabel yearChoose = new JLabel("請選擇學期：");
				yearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearChoose.setBounds(73, 86, 128, 23);
				view.add(yearChoose);
				yearChoose.repaint();
				
				JComboBox YEARcomboBox = new JComboBox(yearChoice);
				YEARcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				YEARcomboBox.setBackground(SystemColor.controlHighlight);
				YEARcomboBox.setBounds(192, 85, 173, 29);
				YEARcomboBox.setSelectedIndex(index);
				view.add(YEARcomboBox);
				YEARcomboBox.repaint();
				
				JButton checkIT = new JButton("查詢");
				checkIT.setBackground(SystemColor.controlHighlight);
				checkIT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkIT.setBounds(377, 84, 80, 31);
				view.add(checkIT);
				checkIT.repaint();
				SetGradeCheckPro check = new SetGradeCheckPro(view, client, account, YEARcomboBox);
				checkIT.addActionListener(check);
				
				JLabel lessonChooooose = new JLabel("請選擇課程：");
				lessonChooooose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				lessonChooooose.setBounds(73, 129, 128, 23);
				view.add(lessonChooooose);
				lessonChooooose.repaint();
				
				JComboBox LESSONcomboBox = new JComboBox(allLesson);
				LESSONcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				LESSONcomboBox.setBackground(SystemColor.controlHighlight);
				LESSONcomboBox.setBounds(192, 128, 173, 29);
				view.add(LESSONcomboBox);
				LESSONcomboBox.repaint();
				
				JButton checkITduck_1 = new JButton("查看");
				checkITduck_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkITduck_1.setBackground(SystemColor.controlHighlight);
				checkITduck_1.setBounds(377, 127, 80, 31);
				view.add(checkITduck_1);
				checkITduck_1.repaint();
				SetGradeStuCheckPro read = new SetGradeStuCheckPro(view, client, account, YEARcomboBox, LESSONcomboBox);
				checkITduck_1.addActionListener(read);
			}
		}
	}
	
	class SetGradeStuCheckPro implements ActionListener{
			
			private Account account;
			private Manager client;
			private JPanel view;
			private JComboBox YEARcomboBox;
			private JComboBox LESSONcomboBox;
			
			public SetGradeStuCheckPro(JPanel view, Manager client, Account account, JComboBox YEARcomboBox, JComboBox LESSONcomboBox){
				this.account = account;
				this.view = view;
				this.client = client;
				this.YEARcomboBox = YEARcomboBox;
				this.LESSONcomboBox = LESSONcomboBox;
			}
			
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index_year = YEARcomboBox.getSelectedIndex();
				int index_lesson = LESSONcomboBox.getSelectedIndex();
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
	            
				Professor stuDeleteLesson = client.readProFileID(account.account);
				ArrayList<String> place = new ArrayList<String>();
				for (int i = 0; i < stuDeleteLesson.lesson.size(); i++) {
					ArrayList<String> getData = stuDeleteLesson.lesson.get(i);
					if (getData.get(0).equals(yearChoice[YEARcomboBox.getSelectedIndex()])) {
						place = getData;
						break;
					}
				}
				String[] allLesson;
				int x = stuDeleteLesson.lesson.indexOf(place);
				if (x != -1) {
					ArrayList<String> readLesson = stuDeleteLesson.lesson.get(x);
					allLesson = new String[readLesson.size()];
					allLesson[0] = " ";
					for (int p = 1; p < readLesson.size(); p++) {
						allLesson[p] = readLesson.get(p);
					}
				}
				else {
					allLesson = new String[1];
					allLesson[0] = " ";
				}
				
				view.removeAll();
				view.repaint();
				
				JLabel putGradeINTitle = new JLabel("登錄成績");
				putGradeINTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				putGradeINTitle.setBounds(210, 37, 106, 23);
				view.add(putGradeINTitle);
				putGradeINTitle.repaint();
				
				JLabel yearChoose = new JLabel("請選擇學期：");
				yearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				yearChoose.setBounds(73, 86, 128, 23);
				view.add(yearChoose);
				yearChoose.repaint();
				
				JComboBox YEARcomboBox = new JComboBox(yearChoice);
				YEARcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				YEARcomboBox.setBackground(SystemColor.controlHighlight);
				YEARcomboBox.setBounds(192, 85, 173, 29);
				YEARcomboBox.setSelectedIndex(index_year);
				view.add(YEARcomboBox);
				YEARcomboBox.repaint();
				
				JButton checkIT = new JButton("查詢");
				checkIT.setBackground(SystemColor.controlHighlight);
				checkIT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkIT.setBounds(377, 84, 80, 31);
				view.add(checkIT);
				checkIT.repaint();
				SetGradeCheckPro check = new SetGradeCheckPro(view, client, account, YEARcomboBox);
				checkIT.addActionListener(check);
				
				JLabel lessonChooooose = new JLabel("請選擇課程：");
				lessonChooooose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				lessonChooooose.setBounds(73, 129, 128, 23);
				view.add(lessonChooooose);
				lessonChooooose.repaint();
				
				JComboBox LESSONcomboBox = new JComboBox(allLesson);
				LESSONcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				LESSONcomboBox.setBackground(SystemColor.controlHighlight);
				LESSONcomboBox.setBounds(192, 128, 173, 29);
				LESSONcomboBox.setSelectedIndex(index_lesson);
				view.add(LESSONcomboBox);
				LESSONcomboBox.repaint();
				
				JButton checkITduck_1 = new JButton("查看");
				checkITduck_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkITduck_1.setBackground(SystemColor.controlHighlight);
				checkITduck_1.setBounds(377, 127, 80, 31);
				view.add(checkITduck_1);
				checkITduck_1.repaint();
				SetGradeStuCheckPro read = new SetGradeStuCheckPro(view, client, account, YEARcomboBox, LESSONcomboBox);
				checkITduck_1.addActionListener(read);
				
				if (LESSONcomboBox.getSelectedIndex() != 0) {
					JLabel stuChooooose = new JLabel("請選擇學生：");
					stuChooooose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					stuChooooose.setBounds(73, 172, 128, 23);
					view.add(stuChooooose);
					stuChooooose.repaint();
					
					Lesson choiceLesson = client.findLessonData(yearChoice[YEARcomboBox.getSelectedIndex()], allLesson[LESSONcomboBox.getSelectedIndex()]);
					String[] stuList = new String[choiceLesson.list.size()+1];
					stuList[0] = " ";
					for (int i = 0; i < choiceLesson.list.size(); i++) {
						stuList[i+1] = choiceLesson.list.get(i).name;
					}
					JComboBox STUcomboBox = new JComboBox(stuList);
					STUcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					STUcomboBox.setBackground(SystemColor.controlHighlight);
					STUcomboBox.setBounds(192, 171, 173, 29);
					view.add(STUcomboBox);
					STUcomboBox.repaint();
					
					JLabel GRADEin = new JLabel("請輸入成績：");
					GRADEin.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					GRADEin.setBounds(73, 215, 128, 23);
					view.add(GRADEin);
					GRADEin.repaint();
					
					JTextField gradeININ = new JTextField();
					gradeININ.setHorizontalAlignment(SwingConstants.CENTER);
					gradeININ.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					gradeININ.setBounds(192, 215, 173, 24);
					view.add(gradeININ);
					gradeININ.setColumns(10);
					gradeININ.repaint();
					
					JButton INgrade = new JButton("登錄");
					INgrade.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					INgrade.setBackground(SystemColor.controlHighlight);
					INgrade.setBounds(279, 262, 153, 31);
					view.add(INgrade);
					INgrade.repaint();
					ActionListener gradeINgogogo = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (STUcomboBox.getSelectedIndex() != 0 && !gradeININ.equals("")) {
								boolean gradeINok = client.setGrade(choiceLesson, stuList[STUcomboBox.getSelectedIndex()], gradeININ.getText());
								GradeInNoteGUI haveGRADE = new GradeInNoteGUI();
								haveGRADE.run(gradeINok);
								
								view.removeAll();
								view.repaint();
							
								JLabel putGradeINTitle = new JLabel("登錄成績");
								putGradeINTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
								putGradeINTitle.setBounds(210, 37, 106, 23);
								view.add(putGradeINTitle);
								putGradeINTitle.repaint();
								
								JLabel yearChoose = new JLabel("請選擇學期：");
								yearChoose.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
								yearChoose.setBounds(73, 86, 128, 23);
								view.add(yearChoose);
								yearChoose.repaint();
								
								String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
								File folder = new File(lessonFolder);
					            File[] Lessonlist = folder.listFiles();
					            ArrayList<String> yearLesson = new ArrayList<String>();
					            for(int i = 0; i < Lessonlist.length; i++){
					            	yearLesson.add(Lessonlist[i].getName());
						        }
					            String[] yearChoice = new String[yearLesson.size()+1];
					            yearChoice[0] = "";
					            for (int j = 1; j < yearLesson.size()+1; j++) {
					            	yearChoice[j] = yearLesson.get(j-1);
					            }
					            
								JComboBox YEARcomboBox = new JComboBox(yearChoice);
								YEARcomboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
								YEARcomboBox.setBackground(SystemColor.controlHighlight);
								YEARcomboBox.setBounds(192, 85, 173, 29);
								view.add(YEARcomboBox);
								YEARcomboBox.repaint();
								
								JButton checkIT = new JButton("查詢");
								checkIT.setBackground(SystemColor.controlHighlight);
								checkIT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
								checkIT.setBounds(377, 84, 80, 31);
								view.add(checkIT);
								checkIT.repaint();
								SetGradeCheckPro check = new SetGradeCheckPro(view, client, account, YEARcomboBox);
								checkIT.addActionListener(check);
							}
						};
					};
					INgrade.addActionListener(gradeINgogogo);
				}
			}
	}
	
	class LessonGradeReadPro implements ActionListener{
		private Account account;
		private Manager client;
		private JPanel view;
		
		public LessonGradeReadPro(JPanel view, Manager client, Account account){
			this.account = account;
			this.view = view;
			this.client = client;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.removeAll();
			view.repaint();
		
			JLabel GradeReadStuTitle = new JLabel("查看課程成績");
			GradeReadStuTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			GradeReadStuTitle.setBounds(185, 15, 155, 23);
			view.add(GradeReadStuTitle);
			GradeReadStuTitle.repaint();
			
			JLabel year = new JLabel("請選擇學期：");
			year.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			year.setBounds(52, 51, 127, 23);
			view.add(year);
			year.repaint();
			
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
			
			JComboBox YEARBox = new JComboBox(yearChoice);
			YEARBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			YEARBox.setBackground(SystemColor.controlHighlight);
			YEARBox.setBounds(173, 48, 210, 29);
			view.add(YEARBox);
			YEARBox.repaint();
			
			JButton checkLessonGO = new JButton("查詢");
			checkLessonGO.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			checkLessonGO.setBackground(SystemColor.controlHighlight);
			checkLessonGO.setBounds(401, 47, 77, 29);
			view.add(checkLessonGO);
			checkLessonGO.repaint();
			ReadGradeCheckPro check = new ReadGradeCheckPro(view, client, YEARBox, account);
			checkLessonGO.addActionListener(check);
		}
	}
	
	class ReadGradeCheckPro implements ActionListener{
			private Account account;
			private Manager client;
			private JPanel view;
			private JComboBox YEARBox;
			
			public ReadGradeCheckPro(JPanel view, Manager client, JComboBox YEARBox, Account account){
				this.account = account;
				this.view = view;
				this.client = client;
				this.YEARBox = YEARBox;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = YEARBox.getSelectedIndex();
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
				
				view.removeAll();
				view.repaint();
			
				JLabel GradeReadStuTitle = new JLabel("查看課程成績");
				GradeReadStuTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				GradeReadStuTitle.setBounds(185, 15, 155, 23);
				view.add(GradeReadStuTitle);
				GradeReadStuTitle.repaint();
				
				JLabel year = new JLabel("請選擇學期：");
				year.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				year.setBounds(52, 51, 127, 23);
				view.add(year);
				year.repaint();
								
				JComboBox YEARBox = new JComboBox(yearChoice);
				YEARBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				YEARBox.setBackground(SystemColor.controlHighlight);
				YEARBox.setBounds(173, 48, 210, 29);
				YEARBox.setSelectedIndex(index);
				view.add(YEARBox);
				YEARBox.repaint();
				
				JButton checkLessonGO = new JButton("查詢");
				checkLessonGO.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				checkLessonGO.setBackground(SystemColor.controlHighlight);
				checkLessonGO.setBounds(401, 47, 77, 29);
				view.add(checkLessonGO);
				checkLessonGO.repaint();
				ReadGradeCheckPro check = new ReadGradeCheckPro(view, client, YEARBox, account);
				checkLessonGO.addActionListener(check);
				
				if (YEARBox.getSelectedIndex() != 0) {
					Professor stuDeleteLesson = client.readProFileID(account.account);
					ArrayList<String> place = new ArrayList<String>();
					for (int i = 0; i < stuDeleteLesson.lesson.size(); i++) {
						ArrayList<String> getData = stuDeleteLesson.lesson.get(i);
						if (getData.get(0).equals(yearChoice[YEARBox.getSelectedIndex()])) {
							place = getData;
							break;
						}
					}
					String[] allLesson;
					int x = stuDeleteLesson.lesson.indexOf(place);
					if (x != -1) {
						ArrayList<String> readLesson = stuDeleteLesson.lesson.get(x);
						allLesson = new String[readLesson.size()];
						allLesson[0] = " ";
						for (int p = 1; p < readLesson.size(); p++) {
							allLesson[p] = readLesson.get(p);
						}
					}
					else {
						allLesson = new String[1];
						allLesson[0] = " ";
					}
					
					JLabel lesson = new JLabel("請選擇課程：");
					lesson.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
					lesson.setBounds(52, 88, 127, 23);
					view.add(lesson);
					lesson.repaint();
					
					JComboBox LESSONBox = new JComboBox(allLesson);
					LESSONBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					LESSONBox.setBackground(SystemColor.controlHighlight);
					LESSONBox.setBounds(173, 87, 210, 29);
					view.add(LESSONBox);
					LESSONBox.repaint();
					
					JButton readITlessonGO = new JButton("查看");
					readITlessonGO.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
					readITlessonGO.setBackground(SystemColor.controlHighlight);
					readITlessonGO.setBounds(401, 88, 77, 29);
					view.add(readITlessonGO);
					readITlessonGO.repaint();
					ActionListener readITlesson = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (LESSONBox.getSelectedIndex() != 0) {
								JTextArea ViewArea = new JTextArea();
								String wordToprint = client.findLessonGrade(yearChoice[YEARBox.getSelectedIndex()], allLesson[LESSONBox.getSelectedIndex()]);
								ViewArea.setText(wordToprint);
								ViewArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
								ViewArea.setBounds(65, 114, 386, 193);
								view.add(ViewArea);
								ViewArea.setEditable(false);
								ViewArea.setVisible(true);
								ViewArea.repaint();
								
								JScrollPane js = new JScrollPane(ViewArea);
								js.setBounds(65, 118, 386, 193);
								view.add(js, 0);
								js.repaint();
								js.setVisible(true);
							}
						};
					};
					readITlessonGO.addActionListener(readITlesson);
				}
			}
	}
	
	class GradeTXTStu implements ActionListener{
		private Account account;
		private Manager client;
		private JPanel view;
		
		public GradeTXTStu(JPanel view, Manager client, Account account){
			this.account = account;
			this.view = view;
			this.client = client;
		}
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.removeAll();
			view.repaint();
			
			JLabel GradeTXTTitle = new JLabel("產生成績單");
			GradeTXTTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
			GradeTXTTitle.setBounds(192, 100, 137, 23);
			view.add(GradeTXTTitle);
			GradeTXTTitle.repaint();
			
			JLabel year = new JLabel("請選擇學期：");
			year.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
			year.setBounds(109, 155, 128, 23);
			view.add(year);
			year.repaint();
			
			String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
			File folder = new File(lessonFolder);
            File[] Lessonlist = folder.listFiles();
            ArrayList<String> yearLesson = new ArrayList<String>();
            for(int i = 0; i < Lessonlist.length; i++){
            	yearLesson.add(Lessonlist[i].getName());
	        }
            String[] yearChoice = new String[yearLesson.size()+1];
            yearChoice[0] = "";
            for (int j = 1; j < yearLesson.size()+1; j++) {
            	yearChoice[j] = yearLesson.get(j-1);
            }
            
			JComboBox YEARBox = new JComboBox(yearChoice);
			YEARBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			YEARBox.setBackground(SystemColor.controlHighlight);
			YEARBox.setBounds(228, 154, 173, 29);
			view.add(YEARBox);
			YEARBox.repaint();
			
			JButton OutTXT = new JButton("產生成績單");
			OutTXT.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			OutTXT.setBackground(SystemColor.controlHighlight);
			OutTXT.setBounds(279, 236, 153, 31);
			view.add(OutTXT);
			OutTXT.repaint();
			ActionListener MAKEgradeTXT = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (YEARBox.getSelectedIndex() != 0) {
						Student stu = client.readStuFileID(account.account);
						if (stu == null) {
							boolean gradeMAKEok = false;
							GradeTXTNoteGUI makeITdone = new GradeTXTNoteGUI();
							makeITdone.run(gradeMAKEok);
						}
						else {
							boolean gradeMAKEok = client.buildTranscript(yearChoice[YEARBox.getSelectedIndex()], stu);
							GradeTXTNoteGUI makeITdone = new GradeTXTNoteGUI();
							makeITdone.run(gradeMAKEok);
						}
					}
				};
			};
			OutTXT.addActionListener(MAKEgradeTXT);
		}
	}
	
	class StuGradeReadSelf implements ActionListener{
			private Account account;
			private Manager client;
			private JPanel view;
			
			public StuGradeReadSelf(JPanel view, Manager client, Account account){
				this.account = account;
				this.view = view;
				this.client = client;
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.removeAll();
				view.repaint();
				
				JLabel GradeReadStuTitle = new JLabel("查看成績");
				GradeReadStuTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
				GradeReadStuTitle.setBounds(185, 15, 155, 23);
				view.add(GradeReadStuTitle);
				GradeReadStuTitle.repaint();
				
				JLabel year = new JLabel("請選擇學期：");
				year.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
				year.setBounds(99, 80, 127, 23);
				view.add(year);
				year.repaint();
				
				String lessonFolder = "C:\\StudentGradeSystem\\LessonFile\\";
				File folder = new File(lessonFolder);
	            File[] Lessonlist = folder.listFiles();
	            ArrayList<String> yearLesson = new ArrayList<String>();
	            for(int i = 0; i < Lessonlist.length; i++){
	            	yearLesson.add(Lessonlist[i].getName());
		        }
	            String[] yearChoice = new String[yearLesson.size()+1];
	            yearChoice[0] = "";
	            for (int j = 1; j < yearLesson.size()+1; j++) {
	            	yearChoice[j] = yearLesson.get(j-1);
	            }
	            
				JComboBox YEARBox = new JComboBox(yearChoice);
				YEARBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				YEARBox.setBackground(SystemColor.controlHighlight);
				YEARBox.setBounds(220, 80, 210, 29);
				view.add(YEARBox);
				YEARBox.repaint();
				
				JButton readITstuGO = new JButton("查看");
				readITstuGO.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
				readITstuGO.setBackground(SystemColor.controlHighlight);
				readITstuGO.setBounds(273, 311, 127, 31);
				view.add(readITstuGO);
				readITstuGO.repaint();
				ActionListener read_grade_stu = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (YEARBox.getSelectedIndex() != 0) {
							Student stu = client.readStuFileID(account.account);
							if (stu != null) {
								String wordToprint = client.findStudentGrade(stu, yearChoice[YEARBox.getSelectedIndex()]);
								
								JTextArea ViewArea = new JTextArea();
								ViewArea.setText(wordToprint);
								ViewArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
								ViewArea.setBounds(65, 114, 386, 193);
								view.add(ViewArea);
								ViewArea.setEditable(false);
								ViewArea.setVisible(true);
								ViewArea.repaint();
								
								JScrollPane js = new JScrollPane(ViewArea);
								js.setBounds(65, 114, 386, 193);
								view.add(js, 0);
								js.repaint();
								js.setVisible(true);
							}
						}
					};
				};
				readITstuGO.addActionListener(read_grade_stu);
			}
	}
}
