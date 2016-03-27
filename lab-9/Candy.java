/**
 * Author	: Vishal Garg
 * 			  Shobhit Garg
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/**
 * This is the main class in which there are sub-classes for different type of producers and consumers
 * @param 		doWeRun		Stores the value true
 */
public class Candy{
    boolean doWeRun = true;
    
    /**
     * This is a Producer of candies
     * @param		justCandy 		this is a List to store the candies produced
     * @param		size			this is the maximum size of the list
     */
    class candyProducer extends Thread{
        List<String> justCandy;
        int size;
        public candyProducer(List<String> queue,int size){
            justCandy=queue;
            this.size=size;
        }
        /**
         * This is the run function of threads
         * It produces candies until the list is full
         */
        public void run(){
            int number=1;
            while(doWeRun){
                synchronized (justCandy){
                    while(justCandy.size()>=this.size){
                        try {
                            justCandy.notify();
                            justCandy.wait();
                        }catch(Exception e){}
                    }
                    justCandy.add(String.valueOf(number));
                    //System.out.println("Candy added, size is " + justCandy.size());
                    justCandy.notify();
                }
                number++;
            }
        }
    }
    
    /**
     * This is a Producer of CandyPaper
     * @param		candyWrapper		List to store produced wrapping paper
     * @param		size				This is the maximum size of the list
     * @param		number				Counter to keep track of the current size of the list
     */
    class candyWrappingPaperProducer extends Thread{
        List<String> candyWrapper;
        int size;
        int number=1;
        public candyWrappingPaperProducer(List<String> queue,int size){
            candyWrapper=queue;
            this.size=size;
        }
        
        /**
         * This is the run function of the thread
         * It produces wrapper till there is space to produce atleast three
         */
        public void run(){
            while(doWeRun){
                synchronized (candyWrapper){
                    while(candyWrapper.size()>=this.size-2){
                        try{
                            candyWrapper.notify();
                            candyWrapper.wait();
                        }catch(Exception e){}
                    }
                    for(int i=0;i<3;i++){
                        candyWrapper.add(String.valueOf(number));
                    }
                    //System.out.println("Wrapper added, size is " + candyWrapper.size());
                    candyWrapper.notify();
                }
                number++;
            }
        }
    }
    
    /**
     * This is a Consumer class which consumes Candies and Wrappers and gives Wrapped Candies
     * @param		candy			List from which it gets candy
     * @param		wrapper			List from which it gets wrapper
     * @param		wrappedCandy	List which stores wrapped Candy
     * @param		size			Tells about the maximum size of the list
     */
    class consumerCandy extends Thread{
        List<String> candy;
        List<String> wrapper;
        List<String> wrappedCandy;
        int size;
        public consumerCandy(List<String> candy,List<String> wrapper,List<String> wrappedCandy,int size){
            this.candy=candy;
            this.wrapper=wrapper;
            this.wrappedCandy=wrappedCandy;
            this.size=size;
            if(this.size<4){
                System.out.println("Wrong size entered for Wrapper");
                System.exit(0);
            }
        }
        
        /**
         * This is the run function of threads
         * It produces wrapped candy by taking candy and wrapper
         */
        public void run(){
            int number=1;
            while(doWeRun){
                if(candy.size()>0 && wrapper.size()>0){
                    synchronized (candy){
                        candy.remove(0);
                        candy.notify();
                    }
                    synchronized (wrapper){
                        wrapper.remove(0);
                        wrapper.notify();
                    }
                    if(wrappedCandy.size()<this.size){
                        synchronized (wrappedCandy){
                            wrappedCandy.add(String.valueOf(number));
                            //System.out.println("Wrapped Candy added!!:	" + wrappedCandy.size());
                            wrappedCandy.notify();
                            number++;
                            if(wrappedCandy.size()>=4){
                                wrappedCandy.notify();
                            }
                        }
                    }else{
                        synchronized(wrappedCandy){
                            try{
                                wrappedCandy.notify();
                                wrappedCandy.wait();
                            }catch(Exception e){}
                        }
                    }
                }else if(candy.size()==0){
                    synchronized (candy){
                        try{
                            candy.wait();
                        }catch(Exception e){}
                    }
                }else if(wrapper.size()==0){
                    synchronized(wrapper){
                        try{
                            wrapper.wait();
                        }catch(Exception e){}
                    }
                }
            }
        }
    }
    
    /**
     * This is the Producer class of Boxes
     * @param		boxes			List for storing boxes
     * @param		size			Maximum size for List
     * @param		rand			Random object to generate random sized boxes
     */
    class boxProducer extends Thread{
        List<Integer> boxes;
        int size;
        Random rand=new Random();
        public boxProducer(List<Integer> queue,int size){
            boxes=queue;
            this.size=size;
        }
        public void run(){
            while(doWeRun){
                synchronized(boxes){
                    while(boxes.size()>=size){
                        try{
                            boxes.notify();
                            boxes.wait();
                        }catch(Exception e){}
                    }
                    boxes.add(rand.nextInt(4)+1);
                    boxes.notify();
                    //System.out.println("Boxes added, size is :	" + boxes.size());
                }
            }
        }
    }
    
    /**
     * Consumer class which takes wrappedCandy and fills them in Boxes
     * @param		wCandy			List of wrapped Candies
     * @param		boxes			List of boxes
     * @param		filledBox		List to store filledBoxes
     * @param		size			Maximum size of List
     */
    class consumerBox extends Thread{
        List<String> wCandy;
        List<Integer> boxes;
        List<String> filledBox;
        int size;
        public consumerBox(List<String> wCandy,List<Integer> boxes,List<String> filledBox,int size){
            this.wCandy=wCandy;
            this.boxes=boxes;
            this.filledBox=filledBox;
            this.size=size;
        }
        
        /**
         * Run function for threads
         * It takes wrappedCandies and fills the empty boxes according to their sizes
         */
        public void run(){
            while(doWeRun){
                if(boxes.size()==0){
                    synchronized(boxes){
                        try{
                            boxes.wait();
                        }catch(Exception e){}
                    }
                }
                int boxSize=boxes.get(0);
                while(wCandy.size()<boxSize){
                    synchronized(wCandy){
                        try{
                            wCandy.wait();
                        }catch(Exception e){}
                    }
                }
                synchronized(boxes){
                    boxSize=boxes.remove(0);
                    boxes.notify();
                }
                synchronized(wCandy){
                    for (int i =0;i<boxSize;i++){
                        wCandy.remove(0);
                    }
                    wCandy.notify();
                }
				if(filledBox.size()<size){
					filledBox.add("Box of size"+boxSize);
					System.out.println("BOX OF SIZE :	"+boxSize);
				}else{
					doWeRun=false;
					System.exit(0);
				}
            }
        }
    }
    
    /**
     * main function
     * Here we are creating different kind of lists and threads and starting them
     * @param		candy			List for candies
     * @param		wrapper			List for wrappers
     * @param		wrappedCandy	List for wrapped Candies
     * @param		boxes			List for boxes
     * @param		filledBoxes		List for filled boxes
     */
    public static void main(String args[]){
        Candy cc=new Candy();
        List<String> candy= new LinkedList<>();
        List<String> wrapper= new LinkedList<>();
        List<String> wrappedCandy=new LinkedList<>();
        List<Integer> boxes=new LinkedList<>();
        List<String> filledBoxes= new LinkedList<>();
        cc.new candyProducer(candy,20).start();
        cc.new candyWrappingPaperProducer(wrapper,20).start();
        cc.new consumerCandy(candy,wrapper,wrappedCandy,20).start();
        cc.new boxProducer(boxes,20).start();
        cc.new consumerBox(wrappedCandy,boxes,filledBoxes,20).start();
    }
}