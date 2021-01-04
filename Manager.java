package finalProject;
import java.io.*;
import java.util.ArrayList;

public class Manager { //管理員
	String account;
	String password;
	String identity;
	
	public Manager(String account, String password, String identity) { //OK
		this.account = account;
		this.password = password;
		this.identity = identity;
	}
	
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
							this.identity + "\n";
			FileOutputStream fws = new FileOutputStream(path);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fws, "UTF-8"));
			bw.write(word);
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
		return newFile.delete();
	}
	
	public boolean changeFile() { //修改密碼_OK
		String fileName = this.account;
		String path = "C:\\StudentGradeSystem\\AccountFile\\" + fileName +".txt";
		String word =   this.account + "\n" +
						this.password + "\n" +
						this.identity + "\n";
		try {
			FileOutputStream fws = new FileOutputStream(path);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fws, "UTF-8"));
			bw.write(word);
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Professor readProFileID(String id) { //導入教授資料_OK
		String fileName = id;
		String path = "C:\\StudentGradeSystem\\AccountFile\\" + fileName +".txt";
		try {
			File newFile = new File(path);
			if (!newFile.exists()) {
				return null;
			}
			else {
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Professor readProFile(String name) { //導入教授資料_OK
		String folderPath = "C:\\StudentGradeSystem\\AccountFile\\";
		File folder = new File(folderPath);
        File[] AccountList = folder.listFiles();
        for (int i = 0; i < AccountList.length; i++) {
        	try {
				FileInputStream fis = new FileInputStream(AccountList[i].getAbsoluteFile());
				BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				String account = br.readLine();
				String password = br.readLine();
				String identity = br.readLine();
				String proname = br.readLine();
				
				if (name.equals(proname)) {
					ArrayList<ArrayList<String>> lesson = new ArrayList<ArrayList<String>>();
					String line;
					while ((line = br.readLine()) != null) {
						ArrayList<String> yearLesson = new ArrayList<String>();
						String[] getLesson = line.split(" ");
						for (int j = 0; j < getLesson.length; j++) {
							yearLesson.add(getLesson[j]);
						}
						lesson.add(yearLesson);
					}
					Professor changetypeFile = new Professor(account, password, identity, proname, lesson);
					br.close();
					return changetypeFile;
				}
				br.close();
        	}catch (Exception e) {
    			e.printStackTrace();
    			return null;
    		}
        }
        return null;
	}
	public Student readStuFileID(String id) { //導入學生資料_OK
		String fileName = id;
		String path = "C:\\StudentGradeSystem\\AccountFile\\" + fileName +".txt";
		try {
			File newFile = new File(path);
			if (!newFile.exists()) {
				return null;
			}
			else {
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Student readStuFile(String name) { //導入學生資料_OK
		String folderPath = "C:\\StudentGradeSystem\\AccountFile\\";
		File folder = new File(folderPath);
        File[] AccountList = folder.listFiles();
        for (int i = 0; i < AccountList.length; i++) {
        	try {
				FileInputStream fis = new FileInputStream(AccountList[i].getAbsoluteFile());
				BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				String account = br.readLine();
				String password = br.readLine();
				String identity = br.readLine();
				String stuname = br.readLine();
				
				if (name.equals(stuname)) {
					String year = br.readLine();
					ArrayList<ArrayList<String>> lesson = new ArrayList<ArrayList<String>>();
					String line;
					while ((line = br.readLine()) != null) {
						ArrayList<String> yearLesson = new ArrayList<String>();
						String[] getLesson = line.split(" ");
						for (int j = 0; j < getLesson.length; j++) {
							yearLesson.add(getLesson[j]);
						}
						lesson.add(yearLesson);
					}
					Student changetypeFile = new Student(account, password, identity, stuname, year, lesson);
					br.close();
					return changetypeFile;
				}
				br.close();
        	}catch (Exception e) {
    			e.printStackTrace();
    			return null;
    		}
        }
        return null;
	}
	
	public boolean buildLessonFile(Lesson lessonName, String year, Professor pro) { //建立課程
		if (pro == null) {
			return false;
		}
		boolean check = lessonName.buildLessonFile();
		if(!check) {
			return false;
		}
		if (pro.lesson.isEmpty()) {
			ArrayList<ArrayList<String>> allLesson = new ArrayList<ArrayList<String>>();
			ArrayList<String> newlesson = new ArrayList<String>();
			newlesson.add(lessonName.year);
			newlesson.add(lessonName.lessonName);
			pro.lesson = allLesson;
			pro.lesson.add(newlesson);
			lessonName.changeLessonFile();
			return pro.changeFile();
		}
		else {
			ArrayList<String> place = new ArrayList<String>();
			for (int i = 0; i < pro.lesson.size(); i++) {
				ArrayList<String> getData = pro.lesson.get(i);
				if (getData.get(0).equals(year)) {
					place = getData;
					break;
				}
			}
			int lessonIndex = pro.lesson.indexOf(place);
			if (lessonIndex == -1) {
				ArrayList<String> newYear = new ArrayList<String>();
				newYear.add(lessonName.year);
				newYear.add(lessonName.lessonName);
				pro.lesson.add(newYear);
				return pro.changeFile();
			}else {
				pro.lesson.get(lessonIndex).add(lessonName.lessonName);
				return pro.changeFile();
			}
		}
	}
	
	public boolean deleteLessonFile(String year, String lessonName, Professor pro) { //刪除課程_OK
		Lesson lesson = this.findLessonData(year, lessonName);
		ArrayList<StudentGrade> stuList= lesson.list;
		for (int j = 0; j < stuList.size(); j++) {
			String stuName = stuList.get(j).name;
			Student stu = this.readStuFile(stuName);
			ArrayList<String> index = new ArrayList<String>();
			for (int i = 0; i < stu.lesson.size(); i++) {
				ArrayList<String> getlesson = stu.lesson.get(i);
				if (getlesson.get(0).equals(year)) {
					index = getlesson;
					break;
				}
			}
			int lessonIndex_stu = stu.lesson.indexOf(index);
			stu.lesson.get(lessonIndex_stu).remove(lessonName);
			stu.changeFile();
		}
		boolean lessondelete = lesson.deleteLessonFile();
		ArrayList<String> place = new ArrayList<String>();
		for (int i = 0; i < pro.lesson.size(); i++) {
			ArrayList<String> getData = pro.lesson.get(i);
			if (getData.get(0).equals(year)) {
				place = getData;
				break;
			}
		}
		int lessonIndex = pro.lesson.indexOf(place);
		pro.lesson.get(lessonIndex).remove(lessonName);
		boolean proChange = pro.changeFile();
		if (lessondelete && proChange) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Lesson findLessonData(String year, String name) { //查詢課程_OK
		String fileName = name;
		String folderName = year;
		String path = "C:\\StudentGradeSystem\\LessonFile\\" + folderName + "\\" + fileName + ".txt";
		File newFile = new File(path);
		if (!newFile.exists()) {
			return null;
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
	
	public String[] showAllLesson(String year) { //依學期列出所有課程_待依GUI修改
		String folderPath = "C:\\StudentGradeSystem\\LessonFile\\" + year + "\\";
		try{
			ArrayList<String> lessonList = new ArrayList<String>();
            File folder = new File(folderPath);
            File[] list = folder.listFiles();          
	        for(int i = 0; i < list.length; i++){
	            lessonList.add(list[i].getName());
	        }
	        
	        String[] allLesson = new String[list.length + 1];
	        allLesson[0] = " ";
	        int j = 0;
	        while (j < list.length) {
	        	String name = lessonList.get(j);
	        	FileInputStream fis = new FileInputStream(list[j].getAbsoluteFile());
				BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				ArrayList<StudentGrade> arraylist = new ArrayList<StudentGrade>();
				Lesson getData = new Lesson(name, "", year, "", "", "", "", arraylist);
				getData.lessonName = br.readLine();
				allLesson[j + 1] = getData.lessonName;
				br.close();
				j++;
	        }
	        return allLesson;
        }catch(Exception e){
        	return null;
        }
	}
	
	public boolean chooseLesson(Lesson lesson, Student name) { //選課_OK
		lesson.list.add(new StudentGrade(name.name, "00"));
		lesson.changeLessonFile();
		if (name.lesson.isEmpty()) {
			ArrayList<String> newlesson = new ArrayList<String>();
			newlesson.add(lesson.year);
			newlesson.add(lesson.lessonName);
			name.lesson.add(newlesson);
			lesson.changeLessonFile();
			name.changeFile();
			return true;
		}
		ArrayList<String> place = new ArrayList<String>();
		for (int i = 0; i < name.lesson.size(); i++) {
			ArrayList<String> getData = name.lesson.get(i);
			if (getData.get(0).equals(lesson.year)) {
				place = getData;
				break;
			}
		}
		int x = name.lesson.indexOf(place);
		if (x != -1) {
			if (name.lesson.get(x).indexOf(lesson.lessonName) != -1) {
				lesson.list.remove(new StudentGrade(name.name, "00"));
				lesson.changeLessonFile();
				return false;
			}
			else {
				name.lesson.get(x).add(lesson.lessonName);
				return lesson.changeLessonFile() && name.changeFile();
			}
		}
		else {
			place.add(lesson.year);
			place.add(lesson.lessonName);
			name.lesson.add(place);
			return name.changeFile();
		}
	}
	
	public boolean removeLesson(Lesson lesson, Student name) { //退選
		int index = lesson.list.indexOf(new StudentGrade(name.name, " "));
		lesson.list.remove(index);
		ArrayList<String> place = new ArrayList<String>();
		for (int i = 0; i < name.lesson.size(); i++) {
			ArrayList<String> getData = name.lesson.get(i);
			if (getData.get(0).equals(lesson.year)) {
				place = getData;
				break;
			}
		}
		int x = name.lesson.indexOf(place);
		return name.lesson.get(x).remove(lesson.lessonName) && lesson.changeLessonFile() && name.changeFile();
	}
	
	public String studentAverage(String year, Student name) { //總平均計算（依人）_OK
		int sum = 0;
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < name.lesson.size(); i++) {
			ArrayList<String> getData = name.lesson.get(i);
			if (getData.get(0).equals(year)) {
				list = getData;
				break;
			}
		}
		String average;
		if (list.size() <= 1) {
			average = "－－";
			return average;
		}
		else {
			for (int j = 1; j < list.size(); j++) {
				Lesson subject = this.findLessonData(year, list.get(j));
				String myGrade = subject.findStudentGrade(name.name);
				String[] grade = myGrade.split(" ");
				sum += Integer.parseInt(grade[1]);
			}
			average = "" + ((float)(sum/(list.size()-1)));
			return average;
		}
	}
	
	public String findStudentGrade(Student name, String year){ //成績整理（依人）_OK
		ArrayList<String> myLessonGrade = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < name.lesson.size(); i++) {
			ArrayList<String> getData = name.lesson.get(i);
			if (getData.get(0).equals(year)) {
				list = getData;
				break;
			}
		}
		if (list.size() == 0) {
			return null;
		}
		String word = "\t" + name.name + year + "的成績\n";
		for (int j = 1; j < list.size(); j++) {
			Lesson lessonName = this.findLessonData(year, list.get(j));
			String mygrade = lessonName.findStudentGrade(name.name);
			String[] grade = mygrade.split(" ");
			word += "\t" + lessonName.lessonName + "\t" + grade[1] + "分\n";
			myLessonGrade.add(word);
			word = "";
		}
		String allWord = "";
		for (int k = 0; k < myLessonGrade.size(); k++) {
			allWord += myLessonGrade.get(k);
		}
		myLessonGrade.add("總平均：" + this.studentAverage("1091", name));
		return allWord;
	}
	
	public String findLessonGrade(String year, String name){ //成績整理（依課程）_OK
		Lesson lesson = this.findLessonData(year, name);
		String lessonGrade = lesson.findGrade();
		return lessonGrade;
	}
	
	public boolean setGrade(Lesson lesson, String name, String grade) { //成績登錄_OK
		ArrayList<StudentGrade> list = lesson.list;
		int place = list.indexOf(new StudentGrade(name, ""));
		list.get(place).grade = grade;
		return lesson.changeLessonFile();
	}
	
	public boolean buildTranscript(String year, Student name) { //成績單產生_OK
		String fileNameCheck = "C:\\StudentGradeSystem\\AccountFile\\" + name.account + ".txt";
		File checkFile = new File(fileNameCheck);
		if (!checkFile.exists()) {
			return false;
		}
		String word ="\t\t" + year + name.name + "的成績單\n";
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < name.lesson.size(); i++) {
			ArrayList<String> getData = name.lesson.get(i);
			if (getData.get(0).equals(year)) {
				list = getData;
				break;
			}
		}
		if (list.size() == 0) {
			return false;
		}
		for (int j = 1; j < list.size(); j++) {
			Lesson lesson = this.findLessonData(year, list.get(j));
			String[] grade = lesson.findStudentGrade(name.name).split(" ");
			word += "\t\t" + lesson.lessonName + "\t"+
					grade[1] + "分\n";
		}
		word += "\t\t總平均：" + this.studentAverage(year, name);
		try {
			String fileName = name.name + year + "的成績單";
			File newFile = new File("C:\\StudentGradeSystem\\GradeFile\\" + fileName +".txt");
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			FileOutputStream fws = new FileOutputStream(newFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fws, "UTF-8"));
			bw.write(word);
			bw.close();
			return true;
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
