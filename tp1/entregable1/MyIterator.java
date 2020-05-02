package entregable1;
import java.util.Iterator;

public class MyIterator implements Iterator<Integer>{
	private Node navegador;
	
	public MyIterator(Node node) {
		this.navegador = node;
	}
	@Override
	public boolean hasNext() {
		return this.navegador != null;
	}
	@Override
	public Integer next() {
		Integer info = this.navegador.getInfo();
		this.navegador = this.navegador.getNext(); 
		return info;
	}
	public Integer get() {
		return this.navegador.getInfo();
	}
}