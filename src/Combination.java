
public class Combination {
	private Teachers teacher;
	private Lessons lesson;
	
	public Combination(Teachers teacher, Lessons lesson) {
		
		this.teacher = teacher;
		this.lesson = lesson;
	}

	public Teachers getTeacher() {
		return teacher;
	}

	public void setTeacher(Teachers teacher) {
		this.teacher = teacher;
	}

	public Lessons getLesson() {
		return lesson;
	}

	public void setLesson(Lessons lesson) {
		this.lesson = lesson;
	}
	
	
	// methodos gia antistoixies mathimatwn kathigitwn
	public String toString() {
		return this.teacher.getName() + " " + this.lesson.getName();
		
	}
	
}
