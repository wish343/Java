
public class SieveOfEratosthenes {
	
	final static int FIRSTpRIMEuSED = 2;
	static int MAX;
	final boolean[] numbers;
	int noOfProcesses,currentProcesses=0;
	boolean isStart=true;
	
	class multiThread extends Thread{
		int index;
		public multiThread(int index){
			this.index=index;
		}
		public void run() {
			int counter=2;
			while ( index * counter < MAX )	{
				numbers[index * counter] = false;
				counter++;
			}
		}
	}
	public SieveOfEratosthenes(int max) {
		numbers = new boolean[max];
		this.MAX = max;
	}
	public void determinePrimeNumbers()	{
		multiThread[] multiThread=new multiThread[noOfProcesses];
		int counter=2;
		for (int index = 1; index < MAX; index ++ )	{
			numbers[index] = true;
		}
		
		int limit = (MAX > 10 ? (int)Math.sqrt(MAX) + 1 : 3);
		
		for (int index = 2; index < limit; index ++ )	{
			
			if ( numbers[index] )	{				
				//Initializing no of Threads according to the no of Processors
				if(isStart){							
					multiThread[0]=new multiThread(index);
					for(int i=1;i<noOfProcesses;i++){
						multiThread[i]=new multiThread(++index);
					}
					isStart=false;
					for(int i=0;i<noOfProcesses;i++){
						multiThread[i].start();
					}
				}else{
				//Checking if any thread is finished then creating a new one
					boolean ThreadCreated=false;
					while(!ThreadCreated){
						for(int i=0;i<noOfProcesses;i++){
							if(!multiThread[i].isAlive()){
								multiThread[i]=new multiThread(index);
								ThreadCreated=true;
								multiThread[i].start();
								break;
							}
						}						
					}						
				}
				new multiThread(index).start();
			}
		}
	}
	public void testForPrimeNumber()	{
		int[] test = { 2, 3, 4, 7, 13, 17,22,23,24,68, MAX - 1, MAX};
		for (int index = 0; index < test.length; index ++ )	{
			if ( test[index] < MAX )	{
				System.out.println(test[index] + " = " + numbers[test[index]]);
			}
		}
	}
	
	public static void main( String[] args ) {
		
		SieveOfEratosthenes aSieveOfEratosthenes = new SieveOfEratosthenes(200);
		aSieveOfEratosthenes.noOfProcesses=Integer.parseInt(args[0]);
		aSieveOfEratosthenes.determinePrimeNumbers();
		aSieveOfEratosthenes.testForPrimeNumber();
		System.exit(0);
	}
}

