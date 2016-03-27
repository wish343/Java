/**
 * Calculator.java
 *
 * Version:			1.1
 *     $Id$
 *
 * @author     	 	Vishal Garg
 * @author      	Shobhit Garg
 *
 * Revisions:
 *     $Log$
 */
import java.util.*;

/**
 * This class calculates the value of a mathematical expression entered through command line.
 *
 * @param		operators	 			Array that stores all the valid operators for the program
 * @param		count		 			it tell the index of current element being processed in args[]
 * @param		countParenthesis		Counts the no of parenthesis opened or closed
 */

public class Calculator {

	static int counter=0,countParenthesis=0;
	static String operators[]={"+","-","%","*","/","^"};

	/**
	 * Its the main function where we take command line input and display the result
	 * 
	 * @param 		args				Command line input
	 * @param		result				Stores the result of the input mathematical expression
	 * 
	 * @return
	 */
	public static void main(String args[]){

		Double result=Calculator(args);
		if(countParenthesis!=0){
			System.out.println("Unequal no of opening and closing parenthesis.. Terminating!!!");
			System.exit(1);
		}		
		System.out.println(result);
	}

	/**
	 *findPredence function checks the Precedence of an operator passed to it as a String argument.
	 *It returns the precedence of the operator as an integer.
	 * 
	 * @param 		precedenceCounter 		Counter for the loop to check precedence of an operator
	 * @param 		precedence 				Variable which stores the final precedence of the operator which was passed to the function
	 *
	 * @return								precedence of the argument(operator)
	 */


	public static int findPrecedence(String checkOperatorArgument){
		int precedenceCounter=0,precedence=0;										

		for(;precedenceCounter<operators.length;precedenceCounter++){				
			if(operators[precedenceCounter].equals(checkOperatorArgument)){
				precedence=precedenceCounter;
				break;
			}
		}
		return precedence;
	}

	/**
	 *checkDouble function checks if the provided input String is an Double or not.
	 *It returns a Boolean value that is true or false.
	 * 
	 * @param	checkDoubleArgument 		Argument passed to the checkDouble function
	 * @param	isit 						variable which stores if the Argument passed to the function is an Double or not
	 *
	 * @return								true or false based on if the String is an Integer or not
	 */

	public static Boolean checkDouble(String checkDoubleArgument){			

		try{
			Double.parseDouble(checkDoubleArgument);
		}catch( NumberFormatException e){
			return false;
		}
		return true;
	}
	/**
	 *checkOperator function checks if the provided input String is an Operator or not.
	 *It returns a Boolena result.
	 * 
	 * @param	checkOperatorArgument 		Argument passed to the checkOperator function
	 * @param	isit 						variable which stores if the Argument passed to the function is an Operator or not
	 *
	 * @return								true or false based on if the String is an Operator or not
	 */

	public static Boolean checkOperator(String checkOperatorArgument){			
		Boolean isIt=false;															
		for(int counter=0;counter<operators.length;counter++){
			if(operators[counter].equals(checkOperatorArgument)){
				isIt=true;
			}
		}

		return isIt;
	}

	/**
	 *operateIt function takes a 3-variable mathematical expression as the input and returns the result as a Double.
	 *
	 * @param		firstOperand				First operand of the 3-variable mathematical function
	 * @param		operator 					Stores the second variable that will be an operator
	 * @param		secondOperand 				Second operand of the 3-variable mathematical function
	 * @param 		newElement1 				Stores the result of the mathematical expression
	 *
	 * @return									Result of the mathematical expression
	 */

	public static Double operateIt(Double firstOperand,String operator,Double secondOperand){	

		Double newElement1=0.0;																
		switch(operator){
		case "*":
			newElement1=firstOperand*secondOperand;
			break;
		case "/":
			if(secondOperand==0){
				System.out.println("Cannot divide by zero");
				System.exit(0);
			}
			newElement1=firstOperand/secondOperand;
			break;
		case "%":
			if(secondOperand==0){
				System.out.println("Cannot mod by zero");
				System.exit(0);
			}
			newElement1=firstOperand%secondOperand;
			break;
		case "+":
			newElement1=firstOperand+secondOperand;
			break;
		case "-":
			newElement1=firstOperand-secondOperand;
			break;
		case "^":
			newElement1=Math.pow(firstOperand,secondOperand);
			break;
		}
		return newElement1;
	}

	/**
	 *main function takes a mathematical expression as an input from command line argument and evaluates it according to a set precedence of operators
	 *
	 *
	 * @param		expression				Variable storing command line input
	 * @param		operatorNow				stores the precedence of the current operator,i.e, read through expression[i]
	 * @param		operatorOld				stores the precedence of the last operator in the operatorStack
	 * @param 		firstOperand			stores the value of First operand of the 3-variable mathematical function passed to operateIt function
	 * @param		secondOperand			stores the value of Second operand of the 3-variable mathematical function passed to operateIt function
	 * @param		middleOperator			stores the value of Operator of the 3-variable mathematical function passed to operateIt function
	 * @param		operandStack 			stores all the Double encountered in the expression String
	 * @param		operatorStack 			stores all the operators encountered in the expression String
	 */

	public static Double Calculator(String expression[]){

		Stack<Double> operandStack=new Stack<Double>();						
		Stack<String> operatorStack=new Stack<String>();							


		int operatorNow=-1,operatorOld=-1;
		Double firstOperand=0.0,secondOperand=0.0;
		String middleOperator=null;

		for(;counter<expression.length;counter++){
			operatorNow=operatorOld=-1;
			firstOperand=secondOperand=0.0;   

			if(expression[counter].equals("(")){
				counter++;
				countParenthesis++;
				Double temp=Calculator(expression);
				operandStack.push(temp);

				continue;
			}else if(expression[counter].equals(")")){
				countParenthesis--;
				if((operandStack.size()-operatorStack.size()!=1)){
					System.out.println("Insufficient Operators or Operands..Exiting!!");
					System.exit(0);
				}
				while(!operatorStack.isEmpty()){
					secondOperand=operandStack.pop();
					firstOperand=operandStack.pop();
					middleOperator=operatorStack.pop();
					operandStack.push(operateIt(firstOperand,middleOperator,secondOperand));
				}
				return operandStack.peek();
			}else if(checkDouble(expression[counter])){
				operandStack.push(Double.parseDouble(expression[counter]));
				continue;
			} else if(checkOperator(expression[counter])){
				if(checkOperator(expression[counter+1])){
					System.out.println("Wrong input..Two Operators simultaneously not allowed.. Terminating!!");
					System.exit(0);
				}

				operatorNow = findPrecedence(expression[counter]);

				if(!operatorStack.isEmpty()){
					operatorOld=findPrecedence(operatorStack.peek());
					while(operatorNow<operatorOld){
						secondOperand=operandStack.pop();
						firstOperand=operandStack.pop();
						middleOperator=operatorStack.pop();
						operandStack.push(operateIt(firstOperand,middleOperator,secondOperand));
						if(!operatorStack.isEmpty()){
							operatorOld=findPrecedence(operatorStack.peek());
						}else{
							break;
						}

					}
					operatorStack.push(expression[counter]);

				} else{
					operatorStack.push(expression[counter]);
				}

			} else {
				System.out.println("Wrong input!! HUH!!");
				System.exit(0);
			}

		}

		if((operandStack.size()-operatorStack.size()!=1)){
			System.out.println("Insufficient Operators or Operands..Exiting!!");
			System.exit(0);
		}		

		while(!operatorStack.isEmpty()){
			secondOperand=operandStack.pop();
			firstOperand=operandStack.pop();
			middleOperator=operatorStack.pop();
			operandStack.push(operateIt(firstOperand,middleOperator,secondOperand));
		}

		return operandStack.peek();
	}

}