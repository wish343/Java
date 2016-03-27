
import java.util.*;

class modelCalc extends Observable{
	
	static int counter=0,countParenthesis=0;
	String operators[]={"+","-","%","*","/","^"};
	int checker=0;
	
	public void execution(String expre[]){
		Double d=Calculator(expre);
		setChanged();
		notifyObservers(d);
		clearChanged();
	}
	
	public Double Calculator(String expression[]){
		
		Stack<Double> operandStack=new Stack<Double>();						
		Stack<String> operatorStack=new Stack<String>();							

		String issue=null;
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
					setChanged();
					issue="Incorrect parenthesis";
					notifyObservers(issue);
					clearChanged();
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
					setChanged();
					issue="Two simultaneous operators not allowed";
					notifyObservers(issue);
					clearChanged();
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
				setChanged();
				issue="Wrong Input";
				notifyObservers(issue);
				clearChanged();
			}

		}

		if((operandStack.size()-operatorStack.size()!=1)){
			setChanged();
			issue="Insufficient Operators or Operands";
			notifyObservers(issue);
			clearChanged();
		}		

		while(!operatorStack.isEmpty()){
			secondOperand=operandStack.pop();
			firstOperand=operandStack.pop();
			middleOperator=operatorStack.pop();
			operandStack.push(operateIt(firstOperand,middleOperator,secondOperand));
		}
		return operandStack.peek();
	}
	
	public Double operateIt(Double firstOperand,String operator,Double secondOperand){	
		String issue=null;
		Double newElement1=0.0;																
		switch(operator){
		case "*":
			newElement1=firstOperand*secondOperand;
			break;
		case "/":
			if(secondOperand==0){
				setChanged();
				issue="Cannot divide by zero";
				notifyObservers(issue);
				clearChanged();
			}
			newElement1=firstOperand/secondOperand;
			break;
		case "%":
			if(secondOperand==0){
				setChanged();
				issue="Cannot mod by zero";
				notifyObservers(issue);
				clearChanged();
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
	
	public Boolean checkOperator(String checkOperatorArgument){			
		Boolean isIt=false;															
		for(int counter=0;counter<operators.length;counter++){
			if(operators[counter].equals(checkOperatorArgument)){
				isIt=true;
			}
		}

		return isIt;
	}
	
	public Boolean checkDouble(String checkDoubleArgument){			

		try{
			Double.parseDouble(checkDoubleArgument);
		}catch( NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public int findPrecedence(String checkOperatorArgument){
		int precedenceCounter=0,precedence=0;										

		for(;precedenceCounter<operators.length;precedenceCounter++){				
			if(operators[precedenceCounter].equals(checkOperatorArgument)){
				precedence=precedenceCounter;
				break;
			}
		}
		return precedence;
	}
	
}

public class controlCalc{
	viewCalc aView;
	modelCalc aModel;
	public controlCalc(viewCalc v,modelCalc m){
		aView=v;
		aModel=m;
	}
	void exec(){
		int viewInCounter=0,countSpace=0,inputCounter=0;
		String temp=aView.getInput();
		System.out.println("testing: "+ temp);
		for(int j=0;j<temp.length();j++){
			
			if(temp.charAt(j)==' '){
				countSpace++;
			}
		}
		String[] inputS=new String[temp.length()-countSpace];
		int count =0; String t2 = "";
				for(int j=0;j<temp.length();j++){
				if(temp.charAt(j)==' '){
				inputS[count] = t2;
				count++;
				t2 = "";
			}
			else{
				t2 = t2+ temp.charAt(j);
			}
				}
				inputS[count] = t2;
		/*while(viewInCounter<input.size()){
			if(temp(inputCounter)!=' '){
				inputS[viewInCounter]=temp(inputCounter);
				
			}			
			viewInCounter++;
		}*/
		aModel.execution(inputS);
	}
	public static void main(String args[]){
		viewCalc aView1=new viewCalc();
		modelCalc aModel1=new modelCalc();
		controlCalc aControl=new controlCalc(aView1,aModel1);
		aControl.aModel.addObserver(aControl.aView);
		aControl.exec();
	}
}

class viewCalc implements Observer{
	static Scanner sc=new Scanner(System.in);
	
	String getInput(){
		System.out.println("Please enter the expression to be calculated");
		String s1=sc.nextLine();		
		return s1;
	}
	
	public void update(Observable aObservable, Object issue){
		try{
			Double result=(Double)issue;
			System.out.println("Result is "+result);
			System.exit(0);
		}catch(Exception e){
			System.out.println(issue+"\nTerminating");
			System.exit(0);
		}
	}

}