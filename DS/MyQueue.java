package dataStructures;

public class MyQueue<E> {
	MyLinkedList<E> ll = new MyLinkedList<E>();
	void queueAdd(E data) {
		ll.add(data);
	}
	E queueRemove() throws Exception {
		if(ll.isEmpty()) {
			throw new Exception("cannot remove from empty queue");
		}
		return ll.toRemoveQueue();
	}
	E element() throws Exception{
		if(ll.isEmpty()) {
			throw new Exception("cannot view element from empty queue");
		}
		return ll.queueElement();
	}

}
