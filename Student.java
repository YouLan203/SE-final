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

public class Student{
	String account; //學號兼帳號
	String password;
	String identity;
	String name;
	String year;
	ArrayList<ArrayList<String>> lesson = new ArrayList<ArrayList<String>>();
	
	public Student(String account, String password, String identity, String name, String year, ArrayList<ArrayList<String>> lesson) {
		this.account = account;
		this.password = password;
		this.identity = identity;
		this.name = name;
		this.year = year;
		this.lesson = lesson;
	} //OK
	
	public boolean buildFile() { //建檔_OK
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
							this.name + "\n" +
							this.year + "\n";
			String arrayWord = "";
			for (int i = 0; i < this.lesson.size(); i++) {
				arrayWord += this.lesson.get(i).get(0) + " "; //取學期
				for (int j = 1; j < this.lesson.get(i).size(); j++) { //取課程
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
	
	public boolean deleteFile() { //刪除檔案_OK
		String fileName = this.account;
		String path = "C:\\StudentGradeSystem\\AccountFile\\" + fileName +".txt";
		File newFile = new File(path);
		
		for (int i = 0; i < this.lesson.size(); i++) {
			for (int j = 1; j < this.lesson.get(i).size(); j++) {
				Lesson lessonDelete = this.findLessonData(this.lesson.get(i).get(0), this.lesson.get(i).get(j));
				lessonDelete.deleteStudentData(this.name);
			}
		}
		return newFile.delete();
	}
	
	public boolean changeFile() { //修改密碼_OK
		String fileName = this.account;
		String path = "C:\\StudentGradeSystem\\AccountFile\\" + fileName +".txt";
		String word =   this.account + "\n" +
						this.password + "\n" +
						this.identity + "\n" +
						this.name + "\n" +
						this.year + "\n";
		String arrayWord = "";
		for (int i = 0; i < this.lesson.size(); i++) {
				arrayWord += this.lesson.get(i).get(0) + " "; //取學期
				for (int j = 1; j < this.lesson.get(i).size(); j++) { //取課程
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
		
	public void printLesson(String year) { //列出課程_OK
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
	
	public String[] showAllLesson(String year) {  //依學期列出所有課程_待依GUI修改
		String folderPath = "C:\\StudentGradeSystem\\LessonFile\\" + year + "\\";
		try{
			ArrayList<String> lessonList = new ArrayList<String>();
            File folder = new File(folderPath);
            File[] list = folder.listFiles();          
	        for(int i = 0; i < list.length; i++){
	            lessonList.add(list[i].getName());
	        }
	        String[] allLesson = new String[lessonList.size() + 1];
	        allLesson[0] = " ";
	        for(int j = 0; j < list.length; j++){
	        	String name = lessonList.get(j);
	        	FileInputStream fis = new FileInputStream(list[j].getAbsoluteFile());
				BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				ArrayList<StudentGrade> arraylist = new ArrayList<StudentGrade>();
				Lesson getData = new Lesson(name, "", year, "", "", "", "", arraylist);
				getData.lessonName = br.readLine();
				allLesson[j + 1] = getData.lessonName;
				br.close();
	        }
	        return allLesson;
        }catch(Exception e){
                return null;
        }
	}
	
	public Lesson findLessonData(String year, String name) { //查詢課程_OK
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
	
	public void chooseLesson(Lesson lesson) { //選課
		lesson.list.add(new StudentGrade(this.name, "00"));
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
			if (getData.get(0).equals(lesson.year)) {
				place = getData;
				break;
			}
		}
		int x = this.lesson.indexOf(place);
		if (this.lesson.get(x).indexOf(lesson.lessonName) != -1) {
			System.out.println("You have chose it.");
			return;
		}
		this.lesson.get(x).add(lesson.lessonName);
		lesson.changeLessonFile();
		this.changeFile();

	}
	
	public String studentAverage(String year) { //總平均計算（依人）_OK
		int sum = 0;
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < this.lesson.size(); i++) {
			ArrayList<String> getData = this.lesson.get(i);
			if (getData.get(0).equals(year)) {
				list = getData;
				break;
			}
		}
		for (int j = 1; j < list.size(); j++) {
			Lesson subject = this.findLessonData(year, list.get(j));
			String myGrade = subject.findStudentGrade(this.name);
			String[] grade = myGrade.split(" ");
			sum += Integer.parseInt(grade[1]);
		}
		String average = "" + ((float)(sum/(list.size()-1)));
		return average;
	}
	
	public ArrayList<String> findStudentGrade(String year){ //成績整理（依人）_OK
		ArrayList<String> myLessonGrade = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < this.lesson.size(); i++) {
			ArrayList<String> getData = this.lesson.get(i);
			if (getData.get(0).equals(year)) {
				list = getData;
				break;
			}
		}
		String word = "";
		for (int j = 1; j < list.size(); j++) {
			Lesson lessonName = this.findLessonData(year, list.get(j));
			String mygrade = lessonName.findStudentGrade(this.name);
			String[] grade = mygrade.split(" ");
			word += lessonName.lessonName + " " + grade[1];
			myLessonGrade.add(word);
			word = "";
		}
		myLessonGrade.add("總平均：" + this.studentAverage("1091"));
		return myLessonGrade;
	}
	
	public void buildTranscript() { //成績單全學期產生_OK
		String allWord = "";
		for (int a = 0; a < this.lesson.size(); a++) {
			String year = this.lesson.get(a).get(0);
			String word = "\t\t" + year + this.name + "的成績單\n";
			ArrayList<String> list = this.lesson.get(a);
			for (int j = 1; j < list.size(); j++) {
				Lesson lesson = this.findLessonData(year, list.get(j));
				String[] grade = lesson.findStudentGrade(this.name).split(" ");
				word += "\t" + lesson.lessonName + "\t" + grade[1] + "分\n";
			}
			word += "\t\t總平均：" + this.studentAverage(year);
			allWord += word + "\n\n";
		}
		try {
			String fileName = this.name + "的成績單";
			File newFile = new File("C:\\StudentGradeSystem\\GradeFile\\" + fileName +".txt");
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			FileOutputStream fws = new FileOutputStream(newFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fws, "UTF-8"));
			bw.write(allWord);
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
