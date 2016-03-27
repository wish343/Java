class  MyNode<E>{
	E data;
	MyNode<E> next;
}

public class StorageDynamic<E,V> extends MyNode<E> implements Storage<E,V>{
	static int MAX_SIZE=100;
	static int size=0;
	MyNode<E> head;
	MyNode<E> tail;
	public StorageDynamic(){
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
		}
		if(size>0){
			flag=true;
			tail.next=a;
			tail=tail.next;
		} 
		size++;	
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
		}else if(index==size){
			if(size==0){
				tail=a;
				head=a;
			}else{
				tail.next=a;
				tail=tail.next;
			}
			flag=true;
		}else {
			MyNode<E> temp=head,newTemp;
			flag=true;			
			while(counter<index){
				counter++;
				temp=temp.next;
			}
			newTemp=temp.next;
			a.next=newTemp;
			temp.next=a;
		}
		size++;
		return flag;
	}

	public void	addElement(E obj){
		MyNode<E> a=new MyNode<>();
		a.data=obj;
		a.next=null;
		if(size==0){
			tail=a;
			head=a;		
		}
		if(size>0){
			tail.next=a;
			tail=tail.next;
		} 
		size++;	
	}

	public void	addElement(E obj, V elem){
		MyNode<E> a=new MyNode<>();
		a.data=obj;
		a.next=null;
		if(size==0){
			tail=a;
			head=a;		
		}
		if(size>0){
			tail.next=a;
			tail=tail.next;
		} 
		size++;	
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
		StorageDynamic<E,V> bStorageString = new StorageDynamic<>();
		if(size>0){
			MyNode<E> headTemp=new MyNode<>();
			headTemp.data=this.head.data;
			headTemp.next=null;
			bStorageString.head=headTemp;
			MyNode<E> temp1=this.head,temp2=bStorageString.head;
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
		Storage<String, String> aStorageString = new StorageDynamic<String, String>();
		System.out.println(System.currentTimeMillis());
		for(int i=0;i<10;i++){
			aStorageString.add("0");

		}
		System.out.println(System.currentTimeMillis());
		StorageDynamic<String, String> cStorageString=(StorageDynamic<String,String>)aStorageString.clone();
		aStorageString.add("1");
		System.out.println(aStorageString);
		System.out.println(cStorageString);
		//System.out.println(aStorageString);
	}
}