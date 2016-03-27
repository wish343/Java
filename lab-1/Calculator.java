/**
 * Calculator.java
 *
 * Version:
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
 * @param		operandStack 			stores all the integers entered through command line
 * @param		operatorStack 			stores all the operators entered through command line
 */

public class Calculator {

	static Stack<Integer> operandStack=new Stack<Integer>();						
	static Stack<String> operatorStack=new Stack<String>();							

	/**
	 *findPredence function checks the Precedence of an operator passed to it as a String argument.
	 *It returns the precedence of the operator as an integer.
	 * 
	 * @param		checkOperatorArgument	Argument  for finding precedence
	 * @param 		operators 				Stores all the valid operators for our function
	 * @param 		precedenceCounter 		Counter for the loop to check precedence of an operator
	 * @param 		precedence 				Variable which stores the final precedence of the operator which was passed to the function
	 *
	 * @return								precedence of the argument(operator)
	 */

	public static int findPrecedence(String checkOperatorArgument){
		String operators[]={"+","-","%","*","/"};									

		int precedenceCounter=0,precedence=0;										


		for(;precedenceCounter<operators.length;precedenceCounter++){				
			if(operators[precedenceCounter].indexOf(checkOperatorArgument)!=-1){
				precedence=precedenceCounter;
				break;
			}
		}
		return precedence;
	}

	/**
	 *checkInteger fuction checks if the provided input String is an Integer or not.
	 *It returns the result as a Boolean variable.
	 * 
	 * @param	checkIntegerArgument 		Argument passed to the checkInteger function
	 * @param	isit 						variable which stores if the Argument passed to the function is an Integer or not
	 *
	 * @return								true or false based on if the String is an Integer or not
	 */

	public static Boolean checkInteger(String checkIntegerArgument){			
		Boolean isIt;															
		isIt=false;
		if("1234567890".indexOf(checkIntegerArgument)!=-1){
			isIt=true;
		}

		return isIt;
	}

	/**
	 *checkOperator function checks if the provided input String is an Operator or not.
	 *It returns the result as a Boolean variable.
	 * 
	 * @param	checkOperatorArgument 		Argument passed to the checkOperator function
	 * @param	isit 						variable which stores if the Argument passed to the function is an Operator or not
	 * @param 	operatorString 				String storing all the valid operators for this function
	 *
	 * @return								true or false based on if the String is an Operator or not
	 */

	public static Boolean checkOperator(String checkOperatorArgument){			
		Boolean isIt;															
		String operatorString;													
		operatorString="%*-=/+";
		isIt=false;
		if(operatorString.indexOf(checkOperatorArgument)!=-1){
			isIt=true;
		}
		return isIt;
	}

	/**
	 *operateIt function takes a 3-variable mathematical expression as the input and returns the result as an Integer.
	 *
	 * @param		firstOperand				First operand of the 3-variable mathematical function
	 * @param		operator 					Stores the second variable that will be an operator
	 * @param		secondOperand 				Second operand of the 3-variable mathematical function
	 * @param 		newElement1 				Stores the result of the mathematical expression
	 *
	 * @return									Result of the mathematical expression
	 */

	public static int operateIt(int firstOperand,String operator,int secondOperand){	

		int newElement1=0;																
		switch(operator){
		case "*":
			newElement1=firstOperand*secondOperand;
			break;
		case "/":
			newElement1=firstOperand/secondOperand;
			break;
		case "%":
			newElement1=firstOperand%secondOperand;
			break;
		case "+":
			newElement1=firstOperand+secondOperand;
			break;
		case "-":
			newElement1=firstOperand-secondOperand;
			break;
		}
		return newElement1;
	}

	/**
	 *main function takes a mathematical expression as an input from command line argument and evaluates it according to a set precedence of operators
	 *
	 *
	 * @param		args					Variable storing command line input
	 * @param		operatorNow				stores the precedence of the current operator,i.e, read through args[i]
	 * @param		operatorOld				stores the precedence of the last operator in the operatorStack
	 * @param 		firstOperand			stores the value of First operand of the 3-variable mathematical function passed to operateIt function
	 * @param		secondOperand			stores the value of Second operand of the 3-variable mathematical function passed to operateIt function
	 * @param		counter					iterator for the for loop
	 * @param		middleOperator			stores the value of Operator of the 3-variable mathematical function passed to operateIt function
	 *
	 * @return								
	 */

	public static void main(String args[]){

		int operatorNow=-1,operatorOld=-1,firstOperand=-1,secondOperand=-1,counter=0;
		String middleOperator=null;

		for(counter=0;counter<args.length;counter++){
			operatorNow=operatorOld=firstOperand=secondOperand=-1;    

			if(checkInteger(args[counter])){
				operandStack.push(Integer.parseInt(args[counter]));
				continue;
			} else if(checkOperator(args[counter])){
				if(checkOperator(args[counter+1])){
					System.out.println("Wrong input..Two Operators simultaneously not allowed.. Terminating!!");
					System.exit(0);
				}

				operatorNow = findPrecedence(args[counter]);
				if(operatorNow==4){
					secondOperand=Integer.parseInt(args[++counter]);
					firstOperand=operandStack.pop();
					middleOperator=args[counter-1];
					operandStack.push(operateIt(firstOperand,middleOperator,secondOperand));


				} else if(!operatorStack.isEmpty()){
					operatorOld=findPrecedence(operatorStack.peek());
					if(operatorNow<=operatorOld){
						secondOperand=operandStack.pop();
						firstOperand=operandStack.pop();
						middleOperator=operatorStack.pop();
						operandStack.push(operateIt(firstOperand,middleOperator,secondOperand));
						operatorStack.push(args[counter]);
					}
					else{

						operatorStack.push(args[counter]);
					}
				} else{
					operatorStack.push(args[counter]);
				}

			} else {
				System.out.println("Wrong input!! HUH!!");
				System.exit(0);
			}

		}
		while(!operatorStack.isEmpty()){
			secondOperand=operandStack.pop();
			firstOperand=operandStack.pop();
			middleOperator=operatorStack.pop();
			operandStack.push(operateIt(firstOperand,middleOperator,secondOperand));
		}
		System.out.println("Result is\t"+operandStack.peek());
	}

}