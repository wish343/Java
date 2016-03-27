import java.awt.Color;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Mandelbrot extends JFrame {

	private final int MAX 	= 5000;
	private final int LENGTH   	= 800;
	private final double ZOOM  	= 1000;
	private BufferedImage theImage;
	private int[] colors = new int[MAX];
	public final int noOfCores=Runtime.getRuntime().availableProcessors();
	int currentProcesses=0;
	boolean isStart=true;

	public Mandelbrot() {
		super("Mandelbrot Set");

		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	class pixelThread extends Thread{
		//Setting the variables according to the initial problem provided
		int x,y;
		double zx, zy, cX, cY;
		public pixelThread(int x,int y){
			this.x=x;
			this.y=y;
			zx = zy = 0;
			cX = (x - LENGTH) / ZOOM;
			cY = (y - LENGTH) / ZOOM;
		}
		//Run function of the thread
		public void run(){
			int iter = 0;
			double tmp;
			while ( (zx * zx + zy * zy < 10 ) && ( iter < MAX - 1 ) ) {	
				tmp = zx * zx - zy * zy + cX;
				zy = 2.0 * zx * zy + cY;
				zx = tmp;
				iter++;
			}
			if ( iter > 0 )
				theImage.setRGB(x, y, colors[iter]);
			else
				theImage.setRGB(x, y, iter | (iter << 8));
		}
	}
	public void createSet()	{
		pixelThread[] pixels=new pixelThread[noOfCores];
		theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < getHeight(); y++) {

			for (int x = 0; x < getWidth();x++) {
				//Initializing no of Threads according to the no of Processors
				if(isStart){							
					pixels[0]=new pixelThread(x,y);
					for(int i=1;i<noOfCores;i++){
						pixels[i]=new pixelThread(++x,y);
					}
					isStart=false;
					for(int i=0;i<noOfCores;i++){
						pixels[i].start();
					}
				}else{
				//Checking if any thread is finished then creating a new one
					boolean ThreadCreated=false;
					while(!ThreadCreated){
						for(int i=0;i<noOfCores;i++){
							if(!pixels[i].isAlive()){
								pixels[i]=new pixelThread(x,y);
								ThreadCreated=true;
								pixels[i].start();
								break;
							}
						}						
					}						
				}
			}		
		}	
		repaint();	
	}
	public void initColors() {
		for (int index = 0; index < MAX; index++) {
			colors[index] = Color.HSBtoRGB(index/256f, 1, index/(index+8f));
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(theImage, 0, 0, this);
	}

	public static void main(String[] args) {
		Mandelbrot aMandelbrot = new Mandelbrot();
		aMandelbrot.setVisible(true);
		aMandelbrot.createSet();
	}
}