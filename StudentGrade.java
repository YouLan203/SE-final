package finalProject;

public class StudentGrade {
	String name;
	String grade;
	
	public StudentGrade(String name, String grade) {
		this.name = name;
		this.grade = grade;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() == obj.getClass()) {
			StudentGrade o = (StudentGrade) obj;
			return this.name.equals(o.name);
		}
		else {
			return false;
		}
	}
}
