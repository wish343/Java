/**
 * Numbers.java
 *
 * Version:
 *     $Id$
 *     
 * @author      Vishal Garg
 * @author      Shobhit Garg
 *
 * Revisions:
 *     $Log$
 */

import java.util.Vector;
import java.math.*;
/**
 * This class looks for all numbers m, and n, which meets all of the following properties:
 * 1.  n is the k.st prime number
 * 2. m is mirror of k.st prime number
 * 3. bN is a palindrome
 * where:
 * n: is a integer in the range between 2 and 100000
 * m: is the mirror of n (n=73, m=37)
 * bN: is the binary representation of n
 *
 */

public class Numbers {

	/**
	 * toBinary function converts a number passed to it as an integer argument into a binary form.
	 * It returns the binary value of the number as a String.
	 * 
	 * @param		number					Argument containing the number to be converted to binary
	 * @param		binaryValue				Stores the binary value of the integer argument
	 * @param 		remainder 				Stores remainder after dividing the number by 2, it is used in calculating the binary value
	 *
	 * @return								binary value of the argument(number)
	 */

	public static String toBinary(int number){
		String binaryValue = "";
		int remainder;
		while(number>0){
			remainder = number%2;
			number/=2;
			binaryValue = remainder+ binaryValue;
		}
		return binaryValue;
	}
	/**
	 * mirror function calculates the mirror of a number passed to it as an integer argument.
	 * It returns the mirror of the number as an integer.
	 * 
	 * @param		number					Argument containing the number to be converted to binary
	 * @param		mirrorOfPrime			Stores the mirror of the integer argument
	 * @param 		count 					It helps in calculating the mirror by managing base(10) of each digit of the number
	 * @param		remainder				Stores remainder after dividing the number by 10, it is used in calculating the mirror
	 *
	 * @return								mirror of the argument(number)
	 */

	public static int mirror(int number){
		int mirrorOfPrime = 0, count = String.valueOf(number).length() - 1, remainder;
		while(number>0){
			remainder = number % 10;
			number = number / 10;
			mirrorOfPrime +=  remainder*Math.pow(10, count);
			count --;
		}


		return mirrorOfPrime;
	}

	/**
	 * isPallindrome function checks if the number passed to it as an integer argument is a pallindrome.
	 * It returns the result as a Boolean variable.
	 * 
	 * @param		number					Argument containing the number to be verified to be a pallindrome
	 * @param		isIt					Variable which stores if the argument passed to the function is a pallindrome or not.
	 * @param		length					Stores the length of the number.
	 * @param 		count 					Iterator used to reverse a number.
	 * @param		reverseNumber			Stores the reverse of the number passed as argument.
	 *
	 * @return								True or False based on if the number is pallindrome or not
	 */

	public static boolean isPallindrome(String number){

		boolean isIt = false;
		int length = number.length(), count;
		String reverseNumber = "";
		for( count = length-1; count>=0; count--){
			reverseNumber += number.charAt(count); 		
		}
		if(number.equals(reverseNumber)){
			isIt = true;
		}
		return isIt;
	}

	/**
	 * isPrime function checks if the number passed to it as an integer argument is a prime number.
	 * It returns the result as a Boolean variable.
	 * 
	 * @param		number					Argument containing the number to be verified to be a prime number.
	 * @param		isPrime					Variable which stores if the argument passed to the function is a prime number or not.
	 * @param		var						Iterator used in determining if the number is prime.
	 * @param		middle					Stores the value of number / 2, it is used in verifying if the number is prime.	
	 *
	 * @return								True or False based on if the number is prime or not.
	 */
	public static boolean isPrime(int number){
		int middle = number/2, var;
		boolean isPrime = true;
		for ( var = 2; var<= middle; var ++){
			if(number%var == 0)	{
				isPrime = false;
			}
		}
		return isPrime;
	}

	/**
	 * main function finds the best numbers between the range 2 and 100000.
	 * 
	 * @param		primes							Vector used to store all the prime numbers between 2 and 100000.
	 * @param		number							used to represent individual numbers within the range 2 and 100000.
	 * @param		noOfPrimes						Stores the count of total number of primes between the 2 and 100000.
	 * @param		count							Iterator used to iterate within the vector primes.
	 * @param		mirrorOfPrime					Used to store the mirror value of Prime number.
	 * @param		binaryValue						used to store the binary value of the number received from method toBinary(int); 
	 *
	 * @return										
	 */


	public static void main (String args[]){

		Vector<Integer> primes = new Vector<Integer>();
		int number = 2, noOfPrimes, count, mirrorOfPrime;
		String binaryValue;
		while (number !=100000){

			if(isPrime(number)){
				primes.add(number);	
			}

			number ++;
		}
		noOfPrimes = primes.size();		

		for( count = 0; count<noOfPrimes; count++){
			mirrorOfPrime = mirror(primes.elementAt(count));


			if(isPrime(mirrorOfPrime)){


				binaryValue = toBinary(primes.elementAt(count));

				if(isPallindrome(binaryValue)){
					System.out.println("Best no: "+ primes.elementAt(count));
				}
			}
		}
	}

}

