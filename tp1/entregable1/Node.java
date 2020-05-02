package entregable1;

public class Node {
	private int info;
	private Node next;
	
	public Node() {
		this.next = null;
	}
	public Node(int i) {
		this.info = i;
		this.next = null;
	}
	public Node getNext() {
		return next;
	}
	public int getInfo() {
		return info;
	}
	public void setInfo(int i) {
		this.info = i;
	}
	public void setNext(Node node) {
		this.next = node;
	}
}
