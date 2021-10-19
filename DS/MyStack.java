package DS;

public class MyStack<E> {
	MyLinkedList<E> ll = new MyLinkedList<E>();
	public void push(E data) { 
		ll.add(data);
	}
	E pop() throws Exception{
		if(ll.isEmpty()) {
			throw new Exception("Cannot pop element from empty stack");
		}
		return ll.toRemove();
	}
	E peek() throws Exception{
		if(ll.isEmpty()) {
			throw new Exception("Cannot peek element from empty stack");
		}
		return ll.toPeek();
	}
}
