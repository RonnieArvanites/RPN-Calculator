package RPNCalculator;
/*
 Author:Ron Arvanites
 */

public class Stack {
	private int[] stack; //Creates private variable stack
	private int pointer; //Creates private variable pointer
	
	public Stack() {
		stack = new int[1000];
		pointer = 0; //Sets pointer
	}
	public void push(int a) {
		stack[pointer++] = a; //adds a to the stack then increments it after
		
	}
	public int pop() {
		return stack[--pointer]; //returns the value in the stack then decrements the pointer variable by 1
	}
}