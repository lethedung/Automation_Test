package javaBasic;


public class Topic_01_Variable {
	static int studentNumber;
	String studentName = "Le Van A";
	static final String BROWSER_NAME = "Chrome"; //constant
	
	public static void main(String[] args) {
		Topic_01_Variable topic = new Topic_01_Variable();
		
		System.out.println(studentNumber);
//		System.out.println(topic.studentNumber);
		System.out.println(Topic_01_Variable.studentNumber);
		
		System.out.println(topic.studentName);
		System.out.println(Topic_01_Variable.BROWSER_NAME);
	}
}
