import java.util.Properties;
/**
 * This class uses System.getProperty() function and prints the property associated with the given key
 *
 * @version    $Id$
 *
 * @author    Vishal Garg
 *
 * Revisions:
 *         $Logs$
 */

public class OsName {
	
	/**
	*The main function prints the value of a specific property according to a given property key
	* @param		propertyKey 		stores the key of Property to be searched for
	*
	* @return	
	*/
   
    public static void main(String args[]){
        String propertyKey="os.name";
        System.out.print("OS: \t"+ System.getProperty(propertyKey));
    }   
}