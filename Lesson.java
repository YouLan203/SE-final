package finalProject;
import java.io.*;
import java.util.ArrayList;
import finalProject.StudentGrade;
public class Lesson {
	String lessonName;
	String professorName;
	String year;
	String num;
	String word;
	String type;
	String point;
	ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
	
	public Lesson(String lessonName, String professorName, String year, String num, String word, String type, String point, ArrayList<StudentGrade> list) {
		this.lessonName = lessonName;
		this.professorName = professorName;
		this.year = year;
		this.num = num;
		this.word = word;
		this.type = type;
		this.point = point;
		this.list = list;
	}
	
	public boolean buildLessonFile() { //建檔_OK
		try {
			String folderName = this.year;
			File newFolder = new File("C:\\StudentGradeSystem\\LessonFile\\" + folderName);
			newFolder.mkdir();
			String fileName = this.lessonName;
			String path = "C:\\StudentGradeSystem\\LessonFile\\" + folderName + "\\" + fileName + ".txt";
			File newFile = new File(path);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			String word =   this.lessonName + "\n" +
							this.professorName + "\n" +
							this.year + "\n" +
							this.num + "\n" +
							this.word + "\n" +
							this.type + "\n" +
							this.point + "\n";
			String arrayWord = "";
			for (int i = 0; i < this.list.size(); i++) {
				arrayWord += this.list.get(i).name + " ";
				arrayWord += this.list.get(i).grade + "\n";
			}
			FileOutputStream fws = new FileOutputStream(path);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fws, "UTF-8"));
			bw.write(word + "\n" + arrayWord);
			bw.close();
			return true;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean changeLessonFile() { //修改資料_不含課程名稱
		String fileName = this.lessonName;
		String folderName = this.year;
		String path = "C:\\StudentGradeSystem\\LessonFile\\" + folderName + "\\" + fileName + ".txt";
		String word =   this.lessonName + "\n" +
						this.professorName + "\n" +
						this.year + "\n" +
						this.num + "\n" +
						this.word + "\n" +
						this.type + "\n" +
						this.point + "\n";
		String arrayWord = "\n";
		for (int i = 0; i < this.list.size(); i++) {
			arrayWord += this.list.get(i).name + " ";
			arrayWord += this.list.get(i).grade + "\n";
		}
		try {
			FileOutputStream fws = new FileOutputStream(path);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fws, "UTF-8"));
			bw.write(word + arrayWord);
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean changeLessonProName(String newName) { //修改課程教授名稱
		Professor Newpro = new Manager(" "," ", " ").readProFile(newName);
		if (Newpro == null) {
			return false;
		}
		
		Professor Oldpro = new Manager(" "," ", " ").readProFile(this.professorName);
		ArrayList<String> place = new ArrayList<String>();
		for (int i = 0; i < Oldpro.lesson.size(); i++) {
			ArrayList<String> getData = Oldpro.lesson.get(i);
			if (getData.get(0).equals(year)) {
				place = getData;
				break;
			}
		}
		int lessonIndex = Oldpro.lesson.indexOf(place);
		Oldpro.lesson.get(lessonIndex).remove(this.lessonName);
		Oldpro.changeFile();
		
		
		this.professorName = newName;
		this.changeLessonFile();
		
		ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
		Lesson lesson = new Lesson(this.lessonName, newName, this.year, this.num, this.word, this.type, this.point, this.list);
		lesson.buildLessonFile();
		if (Newpro.lesson.isEmpty()) {
			ArrayList<String> newlesson = new ArrayList<String>();
			newlesson.add(lesson.year);
			newlesson.add(lesson.lessonName);
			Newpro.lesson.add(newlesson);
			lesson.changeLessonFile();
			Newpro.changeFile();
			return true;
		}
		ArrayList<String> placeNew = new ArrayList<String>();
		for (int i = 0; i < Newpro.lesson.size(); i++) {
			ArrayList<String> getDataNew = Newpro.lesson.get(i);
			if (getDataNew.get(0).equals(year)) {
				placeNew = getDataNew;
				break;
			}
		}
		int lessonIndexNew = Newpro.lesson.indexOf(place);
		Newpro.lesson.get(lessonIndex).add(lessonName);
		return Newpro.changeFile();
	}
	
	public boolean changeLessonName(String newName) { //修改課程名稱_OK
		String pathNew = "C:\\StudentGradeSystem\\LessonFile\\" + this.year + "\\" + newName + ".txt";
		File ready = new File (pathNew);
		if (ready.exists()) {
			return false;
		}
		Professor pro = new Manager(" "," ", " ").readProFile(this.professorName);
		ArrayList<String> place = new ArrayList<String>();
		for (int i = 0; i < pro.lesson.size(); i++) {
			ArrayList<String> getData = pro.lesson.get(i);
			if (getData.get(0).equals(year)) {
				place = getData;
				break;
			}
		}
		int lessonIndex = pro.lesson.indexOf(place);
		pro.lesson.get(lessonIndex).remove(this.lessonName);
		pro.lesson.get(lessonIndex).add(newName);
		pro.changeFile();
		
		for (int j = 0; j < this.list.size(); j++) {
			Student stu = new Manager(" "," ", " ").readStuFile(this.list.get(j).name);
			ArrayList<String> placeStu = new ArrayList<String>();
			for (int i = 0; i < stu.lesson.size(); i++) {
				ArrayList<String> getDataStu = stu.lesson.get(i);
				if (getDataStu.get(0).equals(year)) {
					place = getDataStu;
					break;
				}
			}
			int lessonIndexStu = stu.lesson.indexOf(placeStu);
			stu.lesson.get(lessonIndex).remove(this.lessonName);
			stu.lesson.get(lessonIndex).add(newName);
			stu.changeFile();
		}
		
		String fileName = this.lessonName;
		String folderName = this.year;
		String path = "C:\\StudentGradeSystem\\LessonFile\\" + folderName + "\\" + fileName + ".txt";
		File newFile = new File(path);
		newFile.delete();
		this.lessonName = newName;
		return this.buildLessonFile();
	}
	
	public boolean deleteLessonFile() { //刪除課程_OK
		String fileName = this.lessonName;
		String folderName = this.year;
		String path = "C:\\StudentGradeSystem\\LessonFile\\" + folderName + "\\" + fileName + ".txt";
		File newFile = new File(path);
		return newFile.delete();
	}
	
	public void deleteStudentData(String name) { //移除學生資料
		ArrayList<StudentGrade> list = this.list;
		int place = list.indexOf(new StudentGrade(name, ""));
		list.remove(list.get(place));
		this.list = list;
		this.changeLessonFile();
	}
	
	public void viewFile() { //待依GUI進行更改
		String word =   this.lessonName + "\n" +
						this.professorName + "\n" +
						this.year + "\n" +
						this.num + "\n" +
						this.word + "\n" +
						this.type + "\n" +
						this.point + "\n";
		String arrayWord = "\n";
		for (int i = 0; i < this.list.size(); i++) {
			arrayWord += this.list.get(i).name + " ";
			arrayWord += this.list.get(i).grade + "\n";
		}
		System.out.println(word + arrayWord);
	}
	
	public String findStudentGrade(String name) { //查看成績（依人）_待依GUI修改輸出
		ArrayList<StudentGrade> list = this.list;
		int place = list.indexOf(new StudentGrade(name, ""));
		String grade = list.get(place).name + " " + list.get(place).grade;
		return grade;
	}
	
	public String gradeAverage() { //計算課程總平均_OK
		int sum = 0;
		for (int i = 0; i < this.list.size(); i++) {
			int add = Integer.parseInt(this.list.get(i).grade);
			sum += add;
		}
		float average = sum / this.list.size();
		String grade = "" + average;
		return grade;
	}
	
	public String findGrade() { //查看成績（依課程）_待依GUI修改輸出
		String grade = this.lessonName + "名單成績\n";
		for (int i = 0; i < this.list.size(); i++) {
			String add = this.findStudentGrade(this.list.get(i).name);
			grade += add + "\n";
		}
		if (this.list.size() != 0) {
			grade += "總平均：" + this.gradeAverage();
		}
		else {
			grade += "總平均： －－";
		}
		return grade;
	}
	
	public String printDate() { //查看課程資訊_待依GUI修改
		String word =   "\t課程名稱：" + 
						this.lessonName + "\n\t授課教授：" +
						this.professorName + "\n\t開課學期：" +
						this.year + "\n\t課程代碼：" +
						this.num + "\n\t課程大綱：" +
						this.word + "\n\t選課類型：" +
						this.type + "\n\t   學分數：" +
						this.point + "\n";
		String arrayWord = "\n\t選課名單：\n\t";
		for (int i = 0; i < this.list.size(); i++) {
			Student stu = new Manager(" ", " ", " ").readStuFile(this.list.get(i).name);
			arrayWord += stu.account + " ";
			arrayWord += stu.name + "\n\t";
		}
		String print = word + arrayWord + "\n";
		return print;
	}
	
	public void readFile(String year, String name) { //導入資料_OK
		String fileName = name;
		String folderName = year;
		String path = "C:\\StudentGradeSystem\\LessonFile\\" + folderName + "\\" + fileName + ".txt";
		try {
			FileInputStream fis = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			this.lessonName = br.readLine();
			this.professorName = br.readLine();
			this.year = br.readLine();
			this.num = br.readLine();
			this.word = br.readLine();
			this.type = br.readLine();
			this.point = br.readLine();
			br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] studentGrade= line.split(" ");
				this.list.add(new StudentGrade(studentGrade[0],studentGrade[1]));
			}
			br.close();
			}catch (IOException e) {
			e.printStackTrace();
		}
	}
}