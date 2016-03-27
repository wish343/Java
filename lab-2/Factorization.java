/**
 * primeFactors.java
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
 

 /**
  * This class prints prime factors of a number
  *
  */
public class Factorization {
	/**
	 * This is the main function which takes the input from command line and prints it in the form its prime multiples
	 * @param 	args		Array from which we take input
	 * @param	number		Number input through command line
	 */
	
	public static void main(String args[]){
		int number=Integer.parseInt(args[0]);
		if(number<2){
			System.out.println("Prime factors not possible... You need to study more of mathematics :D");
			System.exit(0);
		}
		System.out.print(number+"= ");
		for(int i=2;i<=number;){
			if(number%i==0){
				System.out.print(i+"*");
				number=number/i;
			}else{
				i++;
			}
		}
		System.out.print("\b ");
	}
}
