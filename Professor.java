package finalProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Professor {
	String account;
	String password;
	String identity;
	String name;
	ArrayList<ArrayList<String>> lesson = new ArrayList<ArrayList<String>>();
	
	public Professor(String account, String password, String identity, String name, ArrayList<ArrayList<String>> lesson) {
		this.account = account;
		this.password = password;
		this.identity = identity;
		this.name = name;
		this.lesson = lesson;
	}
	
	public boolean buildFile() { //����_OK
		try {
			String fileName = this.account;
			String path = "C:\\StudentGradeSystem\\AccountFile\\" + fileName +".txt";
			File newFile = new File(path);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			String word =   this.account + "\n" +
							this.password + "\n" +
							this.identity + "\n" +
							this.name + "\n";
			String arrayWord = "";
			for (int i = 0; i < this.lesson.size(); i++) {
				arrayWord += this.lesson.get(i).get(0) + " "; //���Ǵ�
				for (int j = 1; j < this.lesson.get(i).size(); j++) { //���ҵ{
					arrayWord += this.lesson.get(i).get(j) + " ";
				}
				arrayWord += "\n";
			}
			FileOutputStream fws = new FileOutputStream(path);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fws, "UTF-8"));
			bw.write(word + arrayWord);
			bw.close();
			return true;
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteFile() { //�R���ɮ�_OK
		String fileName = this.account;
		String path = "C:\\StudentGradeSystem\\AccountFile\\" + fileName +".txt";
		File newFile = new File(path);
		
		for (int i = 0; i < this.lesson.size(); i++) {
			for (int j = 1; j < this.lesson.get(i).size(); j++) {
				Lesson lessonDelete = this.findLessonData(this.lesson.get(i).get(0), this.lesson.get(i).get(j));
				lessonDelete.deleteLessonFile();
			}
		}
		return newFile.delete();
	}
	
	public boolean changeFile() { //�ק�K�X_OK
		String fileName = this.account;
		String path = "C:\\StudentGradeSystem\\AccountFile\\" + fileName +".txt";
		String word =   this.account + "\n" +
						this.password + "\n" +
						this.identity + "\n" +
						this.name + "\n";
		String arrayWord = "";
		for (int i = 0; i < this.lesson.size(); i++) {
			arrayWord += this.lesson.get(i).get(0) + " "; //���Ǵ�
			for (int j = 1; j < this.lesson.get(i).size(); j++) { //���ҵ{
				arrayWord += this.lesson.get(i).get(j) + " ";
			}
			arrayWord += "\n";
		}
		try {
			FileOutputStream fws = new FileOutputStream(path);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fws, "UTF-8"));
			bw.write(word + arrayWord);
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void buildLessonFile(String lessonName, String year) { //�إ߽ҵ{_OK�]GUI���إߤ��᪽���i�ק�^
		ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
		Lesson lesson = new Lesson(lessonName, this.name, year, "", "", "", "", list);
		lesson.buildLessonFile();
		if (this.lesson.isEmpty()) {
			ArrayList<String> newlesson = new ArrayList<String>();
			newlesson.add(lesson.year);
			newlesson.add(lesson.lessonName);
			this.lesson.add(newlesson);
			lesson.changeLessonFile();
			this.changeFile();
			return;
		}
		ArrayList<String> place = new ArrayList<String>();
		for (int i = 0; i < this.lesson.size(); i++) {
			ArrayList<String> getData = this.lesson.get(i);
			if (getData.get(0).equals(year)) {
				place = getData;
				break;
			}
		}
		int lessonIndex = this.lesson.indexOf(place);
		this.lesson.get(lessonIndex).add(lessonName);
		this.changeFile();
	}
	
	public void printLesson(String year) { //�C�X�ҵ{_OK
		ArrayList<String> place = new ArrayList<String>();
		for (int i = 0; i < this.lesson.size(); i++) {
			ArrayList<String> getData = this.lesson.get(i);
			if (getData.get(0).equals(year)) {
				place = getData;
				break;
			}
		}
		String arrayWord = "";
		for (int i = 1; i < place.size(); i++) {
			arrayWord += place.get(i) + "\n";
		}
		System.out.println(arrayWord);
	}
	
	public Lesson findLessonData(String year, String name) { //�d�߽ҵ{_OK
		String fileName = name;
		String folderName = year;
		String path = "C:\\StudentGradeSystem\\LessonFile\\" + folderName + "\\" + fileName + ".txt";
		File newFile = new File(path);
		if (!newFile.exists()) {
			System.out.println("You need to create a new one.");
		}
		else {
			try {
				FileInputStream fis = new FileInputStream(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
				Lesson getData = new Lesson(name, "", year, "", "", "", "", list);
				getData.lessonName = br.readLine();
				getData.professorName = br.readLine();
				getData.year = br.readLine();
				getData.num = br.readLine();
				getData.word = br.readLine();
				getData.type = br.readLine();
				getData.point = br.readLine();
				br.readLine();
				
				String line;
				while ((line = br.readLine()) != null) {
					String[] stuGra = new String[2];
					stuGra = line.split(" ");
					StudentGrade data = new StudentGrade(stuGra[0], stuGra[1]);
					getData.list.add(data);
				}
				br.close();
				return getData;
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
		
	public String findLessonGrade(String year, String name){ //���Z��z�]�̽ҵ{�^_OK
		Lesson lesson = this.findLessonData(year, name);
		String lessonGrade = lesson.findGrade();
		return lessonGrade;
	}
	
	public void setGrade(Lesson lesson, String name, String grade) { //���Z�n��_OK
		ArrayList<StudentGrade> list = lesson.list;
		int place = list.indexOf(new StudentGrade(name, ""));
		list.get(place).grade = grade;
		lesson.changeLessonFile();
	}
}
