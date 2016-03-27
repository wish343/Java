/**
 * Subsets.java
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


import java.util.Vector;
import java.util.Collections;

/**
 * This class calculates all the possible subsets for a number of people attending a party, which is given through the command line 
 */

public class Subsets {

	/**
	 * main function takes an argument that specifies the number of people attending a party and then calculates all
	 * the possible subsets for the people.
	 * 
	 * @param			numberOfElements					variable used to store the number entered through the command line
	 * @param			element								String used to formulate subsets
	 * @param			subsets								Vector used to store the all the subsets
	 * @param			count								Iterator for the vector, used to print all the possible subsets. 
	 */

	public static void main(String args[]){
		int numberOfElements=Integer.parseInt(args[0]), count;
		String element=null;
		Vector<String> subsets=new Vector<String>();
		int vectorLength=0;
		subsets.add("");
		for(int index1=1;index1<=numberOfElements;index1++){
			vectorLength=subsets.size();
			for(int index2=0;index2<vectorLength;index2++){
				element=subsets.elementAt(index2);
				element = element+index1;
				subsets.add(element);
			}
		}


		for(count =0; count<subsets.size(); count++)
		{
			System.out.print("{" + subsets.elementAt(count)+ "} ,");
		}
	}
}
