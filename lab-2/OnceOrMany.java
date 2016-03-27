class OnceOrMany {
/**
* singelton function takes two strings as its parameters, compares them and returns the value of comparison.
*
* @param			literal				First parameter
* @param			aNewString			Second parameter
*/

  public static boolean singelton(String literal, String aNewString)	{ 
	return ( literal == aNewString );
  }
  public static void main( String args[] ) {
	String aString = "xyz";
	System.out.println("1.	xyz == aString:	" +     "xyz" == aString   ); // false, since it is concatinating everything before == and comparing it with aString 
	System.out.println("2.	xyz == aString:	" +   ( "xyz" == aString ) ); // true, since it is first comparing aString with "xyz", here aString is a reference pointing to "xyz"

	String newString = new String("xyz");
	System.out.println("xyz == new String(xyz)\n	" + ("xyz" == newString) ); // false, since newString is an Object pointing to a different memory location.

	System.out.println("1: " + singelton("xyz", "xyz")); // true since its the same literal, that is being referred twice.
	System.out.println("2: " + singelton("xyz", new String("xyz") )); // false, since one is a literal while the other one is an object.
	System.out.println("3: " + singelton("xyz", "xy" + "z"));// true, as both are eventually the same literals when "xy" and "z" are concatenated 
	System.out.println("4: " + singelton("x" + "y" + "z", "xyz")); // true, as both are eventually the same literals when "x", "y" and "z" are concatenated
	System.out.println("5: " + singelton("x" + "y" + new String("z"), "xyz"));// false, since one is an object and another is a literal, and they are stored at different memory locations
	System.out.println("6: " + singelton("x" + ( "y" + "z"), "xyz"));// true, as both are eventually the same literals when "y" and "z" are concatenated and then "yz" is in turn concatenated with "x"  
	System.out.println("7: " + singelton('x' + ( "y" + "z"), "xyz"));// true, as both are eventually the same literals when "y" and "z" are concatenated and then "yz" is in turn concatenated with character "x"  
  }
}
