package ch.ffhs.da.uebung4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DeQueue<T> implements Iterable<T>, Iterator<T>{

	private Node<T> first = null;
	private Node<T> last = null;

	public DeQueue(Collection<T> aCollection) {
		for (T item : aCollection) {
			Node<T> tmpNode = new Node<T>(item);
			insertNodeAtLastPosition(tmpNode);
		}
	}

	public void insertNodeAtLastPosition(Node<T> aNode) {
		if (this.first == null && this.last == null) {
			this.first = aNode;
			this.last = aNode;
		} else {
			this.last.setNext(aNode);
			aNode.setPrev(this.last);
			this.last = aNode;
		}
	}

	public void insertNodeAtFirstPosition(Node<T> aNode) {
		if (this.first == null && this.last == null) {
			this.first = aNode;
			this.last = aNode;
		} else {
			this.first.setPrev(aNode);
			aNode.setNext(this.first);
			this.first = aNode;
		}
	}

	public Node<T> getFirstNode() {
		return this.first;
	}

	public Node<T> getLastNode() {
		return this.last;
	}
	
	public Node<T> removeFirstNode() {
		Node<T> ret = this.first;
		this.first = this.first.getNext();
		return ret;
	}
	
	public Node<T> removeLastNode() {
		Node<T> ret = this.last;
		this.last = this.last.getPrev();
		return ret;
	}
	
	@Override
	public Iterator<T> iterator() {
		return this;
	}
	
	@Override
	public boolean hasNext() {
		return this.first != null;
	}

	@Override
	public T next() {
		Node<T> bla = this.removeFirstNode();
		return bla.getItem();
	}

	@Override
	public void remove() {
		// no remove
	}

	public static void main(String[] args) {
		String[] testArray = {"asdf", "qwer", "yxcv"};
		List<String> testList = Arrays.asList(testArray);
		DeQueue<String> queue = new DeQueue<String>(testList);
		
		
		System.out.println("List:");
		for (String aString : testList)
			System.out.println(aString);
		
		System.out.println("DeQueue:");
		for(String aString : queue)
			System.out.println(aString);
		
	}

	@SuppressWarnings("hiding")
	public class Node<T> {
		private T item;
		private Node<T> next;
		private Node<T> prev;


		public Node(T aItem) {
			this.setItem(aItem);
			this.setNext(null);
			this.setPrev(null);
		}

		public Node<T> getNext() {
			return this.next;
		}

		public void setNext(Node<T> aNode) {
			this.next = aNode;
		}

		public Node<T> getPrev() {
			return prev;
		}

		public void setPrev(Node<T> prev) {
			this.prev = prev;
		}

		public T getItem() {
			return item;
		}

		public void setItem(T item) {
			this.item = item;
		}
	}

}
