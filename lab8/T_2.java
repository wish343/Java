public class T_2 extends Thread    {
    int id = 1;
    static String  theValue  = "1";
    T_2(int id)       {
        this.id = id;
    }
    public void run () {
        if ( id == 1 )
                theValue = "3";
        else
                theValue = "2";
    }      
        
    public static void main (String args []) {
        new T_2(1).start();;
        new T_2(2).start();;
            
        System.out.println("theValue = " + theValue );
        System.out.println("theValue = " + theValue );
    }       
}  
/**
 * The output can be anything from the following:
 * theValue = 1			None of the threads is executed
 * theValue = 1
 * 
 * theValue = 1			After 1st print statement id:2 thread runs
 * theValue = 2
 * 
 * theValue = 1			After 1st print statement id:1 thread runs
 * theValue = 3
 * 
 * theValue = 2			id:2 the latest executed thread before print statements
 * theValue = 2
 * 
 * theValue = 3			id:1 the latest executed thread before print statements
 * theValue = 3
 * 
 * theValue = 2			id:2 gets executed before first print statement
 * theValue = 3			id:1 gets executed after second print statement
 * 
 * theValue = 3			id:1 gets executed before first print statement
 * theValue = 2			id:2 gets executed after second print statement
 */