package finalProject;
import java.io.*;
public class WindowDemo {
	public static void main(String[] args) {
		GUI gui = new GUI();
        gui.run();
        
        File folder = new File("C:\\StudentGradeSystem"); //建立提示視窗
        File accountFolder = new File("C:\\StudentGradeSystem\\AccountFile");
        File lessonFolder = new File("C:\\StudentGradeSystem\\LessonFile");
        File gradeFolder = new File("C:\\StudentGradeSystem\\GradeFile");
        folder.mkdir();
        accountFolder.mkdir();
        lessonFolder.mkdir();
        gradeFolder.mkdir();
        
        File manager = new File("C:\\StudentGradeSystem\\AccountFile\\Manager.txt");
        String word = "Manager\n123\n管理員";
        if (!manager.exists()) {
			try {
				manager.createNewFile();
				
				FileOutputStream fws = new FileOutputStream(manager);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fws, "UTF-8"));
				bw.write(word);
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        boolean done = (folder.exists() && accountFolder.exists() && lessonFolder.exists() && gradeFolder.exists() && manager.exists());
		StartNoteGUI note = new StartNoteGUI();
		note.run(true);
	}
}