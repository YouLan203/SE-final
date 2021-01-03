package finalProject;
import java.io.*;
import java.util.ArrayList;
import finalProject.Manager;
import finalProject.Student;
import finalProject.Professor;
public class Account {
	String account;
	String password;
	String identity;
	
	public Account(String account, String password, String identity) {
		this.account = account;
		this.password = password;
		this.identity = identity;
	}
	
	public boolean logIn() { //驗證帳號_待依GUI修改
		String account;
		String password;
		String identity;
		
		String fileName = this.account;
		String path = "C:\\StudentGradeSystem\\AccountFile\\" + fileName +".txt";
		File newFile = new File(path);
		if (!newFile.exists()) {
			return false;
		}
		else {
			try {
				FileInputStream fis = new FileInputStream(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				account = br.readLine();
				password = br.readLine();
				identity = br.readLine();
				
				if (account.equals(this.account) &&
					password.equals(this.password) &&
					identity.equals(this.identity)) {
					br.close();
					return true;
				}
				else {
					br.close();
					return false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Object changeType(String id) { //變更物件_OK //用於管理員、教授、學生導入資料
		String fileName = this.account;
		String path = "C:\\StudentGradeSystem\\AccountFile\\" + fileName +".txt";
		int x = 0;
		if (id.equals("管理員")) {
			x = 1;
		}
		else if (id.equals("學生")) {
			x = 2;
		}
		if (id.equals("教授")) {
			x = 3;
		}
		switch (x) {
		case 1: //管理員
			try {
				FileInputStream fis = new FileInputStream(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				String account = br.readLine();
				String password = br.readLine();
				String identity = br.readLine();
				Manager changetypeFile = new Manager(account, password, identity);
				br.close();
				return changetypeFile;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2: //學生
			try {
				FileInputStream fis = new FileInputStream(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				String account = br.readLine();
				String password = br.readLine();
				String identity = br.readLine();
				String name = br.readLine();
				String year = br.readLine();
				ArrayList<ArrayList<String>> lesson = new ArrayList<ArrayList<String>>();
				String line;
				while ((line = br.readLine()) != null) {
					ArrayList<String> yearLesson = new ArrayList<String>();
					String[] getLesson = line.split(" ");
					for (int i = 0; i < getLesson.length; i++) {
						yearLesson.add(getLesson[i]);
					}
					lesson.add(yearLesson);
				}
				Student changetypeFile = new Student(account, password, identity, name, year, lesson);
				br.close();
				return changetypeFile;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 3: //教授
			try {
				FileInputStream fis = new FileInputStream(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				String account = br.readLine();
				String password = br.readLine();
				String identity = br.readLine();
				String name = br.readLine();
				ArrayList<ArrayList<String>> lesson = new ArrayList<ArrayList<String>>();
				String line;
				while ((line = br.readLine()) != null) {
					ArrayList<String> yearLesson = new ArrayList<String>();
					String[] getLesson = line.split(" ");
					for (int i = 0; i < getLesson.length; i++) {
						yearLesson.add(getLesson[i]);
					}
					lesson.add(yearLesson);
				}
				Professor changetypeFile = new Professor(account, password, identity, name, lesson);
				br.close();
				return changetypeFile;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		return null;
	}
}
