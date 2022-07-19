/*Author Jared Evans
 * ICS 440-50
 * The goal of this program is to create and test a generic queue
 * 
 */


public class Queue<T> {
	private Node head;
	private Node tail;
	private int length;
	
	//constructor
	public Queue(){
		this.head = null;
		this.tail = null;
		this.length = 0;
	}
	
	//singly linked nodes
	class Node{
		T data;
		Node next;
		Node(T data){
			this.data = data;
		}
	}
	
	//queue is empty if length is 0
	public boolean isEmpty() {
		if(length == 0){
			return true;
		}
		else return false;
	}
	
	public int getLength() {
		return length;
	}

	//adds a new node to queue
	public void enqueue(T data){
		Node temp = new Node(data);
		if(isEmpty())
			head=temp;
		else
			tail.next = temp;
		tail = temp;
		length++;
	}
	
	//removes the first node and returns the value of it
	public T dequeue() {
		length--;
		
		if(isEmpty())
			return null;
		T result = head.data;
		head = head.next;
		if(head == null) {
			tail = null;
		}
		
		return result;
	}
	
	

}
