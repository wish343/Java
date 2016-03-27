/**
 * FastCompetition.java
 *
 * Version:			1.0
 *     $Id$
 *
 * @author     	 	Vishal Garg(wIsh)
 * @author      	Shobhit Garg
 *
 * Revisions:
 *     $Log$
 */
/**
 * MyNode class is used to create a list of data type E
 *
 * @param data		Stores data of the node
 * @param next 		Stores reference of the next element in the list
 */
class  MyNode<E>{
	E data;
	MyNode<E> next;
	public MyNode(){

	}
	public MyNode(E ele){
		data=ele;
		next=null;
	}
}

/**
 * FastCompetition class implements Interface Competition 
 * 
 * @param MAX_SIZE		Final variable which stores the maximum size of the storage
 * @param size			It stores the current size of the storage
 * @param head			Refers to first element in the storage
 * @param tail			Refers to last element in the storage
 * @param traver		Helps in traversing the storage
 */
public class FastCompetition<E> implements Competition<E>{
	final int MAX_SIZE;
	int size=0;
	MyNode<E> head;
	MyNode<E> tail;  
	MyNode<E> traver=head;

	/**
	 * FastCompetition constructor
	 * @param size1		Takes maximum size of the storage as input
	 */
	public FastCompetition(int size1){
		MAX_SIZE=size1;
		head=null;
		tail=null;
	}

	/**
	 * add function adds the given object to the storage at the end
	 * @param 	flag		Tells if object can be added or not
	 * @param 	aNode		Node object storing the given input element and to be added to the storage
	 * @return 	flag
	 */
	public boolean add(E e){
		boolean flag=false;
		MyNode<E> aNode=new MyNode<>(e);
		if(size>=MAX_SIZE){
			System.out.println("More elements cant be added");
			return flag;
		}
		if(size==0){
			tail=aNode;
			head=aNode;
			traver=head;
			flag=true;		
		}
		if(size<MAX_SIZE&&size>0){
			flag=true;
			tail.next=aNode;
			tail=tail.next;
		} 
		size++;	
		return flag;
	}

	/**
	 * contains function tells if the Object is there in the storage or not
	 * @param 	temp		Used for traversing the storage	
	 * @return 				true or false
	 */
	public boolean contains(Object o) {
		MyNode<E> temp=head;
		if(traver!=null&&o.equals(traver.data)){
			traver=traver.next;
			return true;
		}else{
			while(temp!=null){
				if(o.equals(temp.data)){
					traver=temp.next;
					return true;
				}
				temp=temp.next;
			}
		}
		return false;
	}
	
	/**
	 * remove function removes the first occurrence of an element in the storage
	 * @param	temp			Used to refer to the current element being traversed in the storage
	 * @param 	old				Used to refer the last element traversed
	 * @return 					true or false
	 */
	public boolean remove(Object o) {
		MyNode<E> temp=head,old=head;
		for(int i=0;i<size;i++){
			if(o.equals(temp.data)){
				if(size==1){
					head=tail=head.next=tail.next=null;
				}else if(i==0){
					head=head.next;
				}else if(i==size-1){
					tail=old;
					tail.next=null;
				}else{
					old.next=temp.next;
				}
				size--;
				return true;
			}
			old=temp;
			temp=temp.next;
		}
		return false;
	}
	
	/**
	 * elementAt function checks if there is some Object on the specified index and returns it
	 * @param	item		Stores the object found at the given index
	 * @return	item
	 */
	public E elementAt(int index) {
		E item=null;
		if(index>size||index<0){
			System.out.println("Wrong index");
		}else{
			MyNode<E> temp=head;
			for(int i=0;i<=size;i++){
				if(i==index){
					item=temp.data;
					break;
				}
			}
		}
		return item;
	}


	public Competition<E> sort() {
		return this;
	}
	
	/**
	 * size function returns the current size of the storage
	 * @return	size
	 */
	public int size() {
		return size;
	}
}