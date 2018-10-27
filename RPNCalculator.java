package RPNCalculator;
/*
	Author:Ron Arvanites
 */

import java.util.Scanner;
import java.util.regex.Pattern;


public class RPNCalculator {
	public static void main(String[] args) {
		System.out.println("Welcome to the RPN Calculator. Please enter a string of numbers and math operators.");
		//Initializes variables
		int restartLoop = 0;
		String inString = "";
		String[] tokens = null;
		
		//Accepts user's input
		Scanner in = new Scanner (System.in);
		do {
				//Reset variables
				restartLoop = 0;
				boolean isValid = true;
				inString = in.nextLine();
				int intCount = 0;
				int operatorCount = 0;
				
				//Checks if string is empty
				if (inString.isEmpty() == true) {
					System.out.println("You did not enter anything. Please try again."); //Tells the user they entered an empty string
					restartLoop = 1; //Restarts do while loop
				}
				
				//If string in not empty
				if (inString.isEmpty() == false) {
					tokens = inString.split("\\s"); //Splits the inString up base on spaces and stores it in the token variable
					
					//Loops through elements in tokens
					for (String x : tokens) {
						isValid = validate(x); //Calls the validate function for each item in tokens
						
						//If one of the items in the string is invalid
						if (isValid == false ) { 
							System.out.print("The following character is not a valid number or operator:");
							System.out.println(x); //Prints the current value of x
							System.out.println("Incorrect String. Please enter any string of either integers or +,-,/, or *.");
							restartLoop = 1; //Set to repeat the do while loop
						}
						
						//If element in token is an integer
						if (Pattern.matches("-?[1-9]\\d*|0", x)) {
							intCount++; //Adds 1 to intCount 
						}
						
						//If element in token is an math operator
						if (Pattern.matches("[*/+\\-]", x)) {
							operatorCount++;//Adds 1 to operatorCount
						}
		
					}
					String regex = "[*/+\\-]"; //Accepted math operators
					
					//Checks to see if first and second inputs are math operations
					if (Pattern.matches(regex, tokens[0]) || Pattern.matches(regex, tokens[1])) {
						System.out.println("Incorrect String. Please enter any string of either integers or +,-,/, or *.");
						restartLoop = 1; //Restarts do while loop
					}
					
					//Checks to see if there are more numbers than operations in the user's string
					if (intCount <= operatorCount) {
						System.out.println("Incorrect number of math operators or numbers. Please try agian.");
						restartLoop = 1; //Set to repeat the do while loop
					}
				}
		}while(restartLoop == 1);
		in.close();
		calculate(tokens); //calculates input string
	}
	
	//Checks to see if the characters in the string are valid
	static boolean validate (String s) { 
		String regex = "[*/+\\-]|-?[1-9]\\d*|0"; //The valid characters: all integers and *, /, +, -
		return (Pattern.matches(regex, s));
	}
	
	
	
	//Calculates input
	public static void calculate(String[] args) {
		Stack localStack = new Stack(); //Creates a stack called localStack
		int num1;
		int num2;
		
		//Loops through the string args
		for (String token : args) { 
			 switch (token) {
			 case "+"://Adds the last numbers pushed to the stack
				 num1 = localStack.pop();
				 num2 = localStack.pop();
				 localStack.push(num2 + num1);
				 break;
			 case "-"://Subtract the last numbers pushed to the stack
				 num1 = localStack.pop();
				 num2 = localStack.pop();
				 localStack.push(num2 - num1);
				 break;
			 case "/"://Divides the last numbers pushed to the stack
				 num1 = localStack.pop();
				 num2 = localStack.pop();
				 
				 //Checks division by zero
				 if (num1 == 0) {
					 System.out.println("Division by zero. Please restart the program."); //Tells the user there is divion by zero
					 System.exit(0); //exits the program
				 }
				 localStack.push(num2 / num1);
				 break;
			 case "*"://Multiplies the last numbers pushed to the stack
				 num1 = localStack.pop();
				 num2 = localStack.pop();
				 localStack.push(num2 * num1);
				 break;
				 default://Pushes the integers to the stack
					 localStack.push(Integer.parseInt(token));
			 }
		}
		
		System.out.println(localStack.pop()); //Prints the last item popped to the stack which should be the correct result 
		 
	}
}
