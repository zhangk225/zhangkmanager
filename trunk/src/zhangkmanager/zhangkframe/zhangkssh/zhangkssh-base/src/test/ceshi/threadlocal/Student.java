package threadlocal;

public class Student {
	private int age = 0; // 年龄
	private static Student student;

	private Student() {

	}

	public static Student getInstance() {
		if (student == null) {
			student = new Student();
		}
		return student;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}