package entregable1;

public class LinkedList implements Iterable<Integer>{
	private Node head;
	private Node tail;
	private int size;
	
	public LinkedList() {	
		head = null;
		tail = null;
		size = 0;
	}
	public void insertFront(int i) {
		Node temp = new Node(i);
		if(head == null) {
			head = temp;
			tail = temp;
		}else {
			tail = head;
			temp.setNext(head);
			head = temp;
		}
		size++;
	}
	public void insertBack(int i) {
		Node temp = new Node(i);
		if(head == null) {
			head = temp;
			tail = temp;
		}else {
			tail.setNext(temp);
			tail = tail.getNext();
		}
		size++;
	}
	public int size() {
		return this.size;
	}
	@Override
	public MyIterator iterator() {
		return new MyIterator(this.head);
	}
}
