package javaBasic;


public class Topic_03_Compare {
	int number = 8;
	
	public static void main(String[] args) {
		Topic_03_Compare fisrtVariable = new Topic_03_Compare();
		Topic_03_Compare secondVariable = fisrtVariable;
		
		System.out.println("Before first: " + fisrtVariable.number);
		System.out.println("Before second: " + secondVariable.number);
		
		secondVariable.number = 15;
		
		System.out.println("After first: " + fisrtVariable.number);
		System.out.println("After second: " + secondVariable.number + "\n");
		
		
		Topic_03_Compare thirdVariable = new Topic_03_Compare();
		Topic_03_Compare fourthVariable = new Topic_03_Compare();
		
		System.out.println("Before third: " + thirdVariable.number);
		System.out.println("Before fourth: " + fourthVariable.number);
		
		fourthVariable.number = thirdVariable.number;
		thirdVariable.number = 16;
		
		System.out.println("After third: " + thirdVariable.number);
		System.out.println("After fourth: " + fourthVariable.number);
	}
}
 