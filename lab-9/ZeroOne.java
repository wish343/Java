/**
 * Author	: Vishal Garg
 * 			  Shobhit Garg
 */

/**
 * This class prints numbers from 0 to 98 repeatedly
 * @param		numbers			Maximum number that we need to print
 * @param		ZeroOne			Array of threads
 * @param		info			String storing the number of each thread
 * @param		o				Static object used for synchronization
 * @param		oneIsRunning	Static boolean helping in creating threads initially
 * @param		justExecuted	Parameter which tells if the object was just executed or not
 */
public class ZeroOne extends Thread     {
	static final int numbers=99;
	static ZeroOne[] arr=new ZeroOne[numbers];
    private String info;
	private int checker;
    static Object o = new Object();
    static int counter=0;
    static boolean oneIsRunning = false; 
	boolean justExecuted=false;
	
    public ZeroOne (String info) {
            this.info    = info;
			if(info.equals("98")){
				justExecuted=true;
			}
			
    }
	
    /**
     * The run function for threads
     * 
     */
    public void run () {
            while ( true )  {
                    synchronized ( o ) {
						if(oneIsRunning){
							justExecuted=true;
						}							
                            o.notifyAll();
                            System.out.println(info);
							if(!oneIsRunning){
								counter++;
							}                            
                            if(counter>=numbers){
                            	counter=0;
								oneIsRunning = true;
                            }
                            try {
                                    if ( ! oneIsRunning )   {
                                            arr[counter]= new ZeroOne(String.valueOf(counter));
											//arr[counter].indexT=counter;
											int temp=counter-1;
											arr[counter].checker=temp;
											arr[counter].start();
											
                                    }
									sleep(100);
									do{
										o.wait();
									}while(!oneIsRunning);
									while(!arr[checker].getJustExecuted()){
										o.wait();
									}
                            } catch ( Exception e ) { }
                    }
            }
    }
	
    /**
     * Funtion which returns true if the thread was just executed otherwise False
     */
	public boolean getJustExecuted(){
		if(justExecuted){
			justExecuted=false;
			return true;
		}else{
			return false;
		}
	}
	
    public static void main (String args []) {
            arr[0]=new ZeroOne("0");
			arr[0].checker=numbers-1;
			arr[0].start();
    }
}