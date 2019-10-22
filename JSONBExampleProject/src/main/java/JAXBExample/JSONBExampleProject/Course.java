package JAXBExample.JSONBExampleProject;

import javax.json.bind.annotation.JsonbProperty;

public class Course {

	@JsonbProperty
	private int id;
	
	@JsonbProperty
	private String name;
	
	@JsonbProperty
	private int credits;
	
	// change the name of the field in JSON to "course_teacher"
	@JsonbProperty("course_teacher")
	private Teacher teacher;

	public Course() {}
	
	public Course(int id, String name, int credits, Teacher teacher) {
		this.id = id;
		this.name = name;
		this.credits = credits;
		this.teacher = teacher;
	}

	public Course(int id, String name, int credits) {
		this.id = id;
		this.name = name;
		this.credits = credits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
}
