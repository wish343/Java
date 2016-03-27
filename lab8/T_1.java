public class T_1 extends Thread	{
	static int x = 1;
	String info = "";

	public T_1 (String info) {
		this.info = info;
		x++;
	}

	public void run () {
		x++;
		String output = x + " " + info + ": " + x;
		System.out.println(output);
	}

	public static void main (String args []) {
		new T_1("a").start();
		new T_1("b").start();
	}
}
/**
 * First line that gets printed maybe anything from the following:
 * 2 : 2			Only one object gets created and runs
 * 2 : 3			First Objects gets created and is running(String is being created) 
 					when the second gets created
 * 2 : 4			First Objects gets created and is running(String is being created) 
 					when the second gets created and starts running
 * 3 : 3			Both objects get created while only one is running
 * 3 : 4			Both objects created and string of one is being created while second starts running
 * 4 : 4			Both objects created and running before String is created
 * While the second line will always be 4 : 4
 */
