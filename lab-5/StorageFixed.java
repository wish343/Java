
class  MyNode<E>{
	E data;
	MyNode<E> next;
}

public class StorageFixed<E,V> extends MyNode<E> implements Storage<E,V>{
	static int MAX_SIZE=100;
	static int size=0;
	MyNode<E> head;
	MyNode<E> tail;
	public StorageFixed(){
		head=null;
		tail=null;

	}

	public boolean	add(E e){
		boolean flag=false;
		MyNode<E> a=new MyNode<>();
		a.data=e;
		a.next=null;
		if(size==0){
			tail=a;
			head=a;
			flag=true;
			size++;			
		}
		if(size<=MAX_SIZE&&size>0){
			flag=true;
			tail.next=a;
			tail=tail.next;
			size++;
		}else if(size>MAX_SIZE){
			System.out.println("More elements cant be added");
		}
		return flag;

	}
	
	public boolean add(int index, E element){
		int counter=1;
		boolean flag=false;
		MyNode<E> a=new MyNode<>();
		a.data=element;
		a.next=null;
		if(index<0||index>size){
			System.out.println("Invalid index");
			return flag;
		}
		if(size==0){
			tail=a;
			head=a;
			flag=true;
			size++;			
		}else if(index==size){
			tail.next=a;
			tail=tail.next;
			flag=true;
			size++;	
		}else if(size<=MAX_SIZE){
			MyNode<E> temp=head,newTemp;
			flag=true;			
			if(index==0){
				a.next=head;
				head=a;
			}
			while(counter<index){
				counter++;
				temp=temp.next;
			}
			newTemp=temp.next;
			a.next=newTemp;
			temp.next=a;
			size++;
		}else{
			System.out.println("Element cant be added");
		}
		return flag;
	}
	
	public void	addElement(E obj){
		MyNode<E> a=new MyNode<>();
		a.data=obj;
		a.next=null;
		if(size==0){
			tail=a;
			head=a;
			size++;			
		}
		if(size<=MAX_SIZE&&size>0){
			tail.next=a;
			tail=tail.next;
			size++;
		}else if(size>MAX_SIZE){
			System.out.println("Element cant be added");
		}
	}
	
	public void	addElement(E obj, V elem){
		MyNode<E> a=new MyNode<>();
		a.data=obj;
		a.next=null;
		if(size==0){
			tail=a;
			head=a;
			size++;			
		}
		if(size<=MAX_SIZE&&size>0){
			tail.next=a;
			tail=tail.next;
			size++;
		}else if (size>MAX_SIZE){
			System.out.println("Element cant be added");
		}
	}
	
	public int	capacity(){
		return MAX_SIZE;
	}
	
	public void	clear(){
		MyNode<E> temp=head;
		while(head!=null){
			temp=head;
			head=head.next;
			temp.next=null;
		}
		tail=null;
	}
	
	public Object clone(){		
		StorageFixed<E,V> bStorageString = new StorageFixed<>();
		if(size>0){
			MyNode<E> temp1=this.head,temp2=bStorageString.head;
			MyNode<E> headTemp=new MyNode<>();
			headTemp.data=this.head.data;
			headTemp.next=null;
			bStorageString.head=headTemp;
			while(temp1.next!=null){
				temp1=temp1.next;
				MyNode<E> NodeTemp=new MyNode<>();
				NodeTemp.data=temp1.data;
				NodeTemp.next=null;
				temp2.next=NodeTemp;
				temp2=temp2.next;			
			}		
		}
		return bStorageString;
	}
	
	public E	firstElement(){
		return head.data;
	}
	
	public E	get(int index){
		int counter=0;
		MyNode<E> temp=head;
		while(counter<index){
			counter++;
			temp=temp.next;
		}
		return temp.data;
	}
	
	public E	lastElement(){
		return tail.data;
	}
	
	public String toString(){
		MyNode<E> temp=head;
		String s="";
		int i=0;
		while(temp.next!=null){
			s+=String.valueOf(temp.data)+"->";
			temp=temp.next;
			i++;
			if(i%10==0){
				s+="\n";
			}
		}
		s+=temp.data;
		return s;
	}
	public static void main(String args[]){
		Storage<String, String> aStorageString = new StorageFixed<String, String>();
		aStorageString.add("0");
		aStorageString.addElement("0");
		aStorageString.add(1, "0");
		aStorageString.add(-1, "0");
		for(int i=0;i<97;i++){
			aStorageString.add("0");
			
		}
		//System.out.println(aStorageString);
	}
}