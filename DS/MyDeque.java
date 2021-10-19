package dataStructures;


public class MyDeque<E> {
	MyNode<E> head,tail;
	
	public void addStart(E data) {
		MyNode<E> toAdd = new MyNode<E>(data);
		if(head==null) {
			head = tail = toAdd;
		}
		toAdd.next = tail;
		tail.previous = toAdd;
		tail = toAdd;
	}
	public E removeStart(){
		if(head==null) {
			return null;
		}
		MyNode<E> temp = tail;
		tail = tail.next;
		tail.previous = null;
		return temp.data;
	}
	public void addEnd(E data) {
		MyNode<E> toAdd = new MyNode<E>(data);
		if(head==null) {
			head = tail = toAdd;
		}
		toAdd.previous = head;
		head.next = toAdd;
		head = toAdd;
	}
	public E removeEnd() {
		if(head==null) {
			return null;
		}
		MyNode<E> toRemove = head;
		head = head.previous;
		head.next = null;
		return toRemove.data;
	}
	public E peekStart() {
		if(head==null) {
			return null;
		}
		return tail.data;
	}
	public E peekEnd() {
		if(head==null) {
			return null;
		}
		return head.data;
	}
	public void print() {
		MyNode<E> temp = tail;
		if(head==null) {
			System.out.println("null");
			return;
		}
		while(temp!=head) {
			System.out.println(temp.data);
			temp = temp.next;
		}
		System.out.println(temp.data);
	}
	
	
	class MyNode<E>{
		E data;
		MyNode<E> next;
		MyNode<E> previous;
		public MyNode(E data) {
			this.data = data;
			this.next = this.previous = null;
		}
	}
	
}
