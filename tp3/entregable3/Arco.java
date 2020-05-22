package entregable3;

public class Arco<T> {
	private VerticeAbs<T> origin;
	private VerticeAbs<T> destiny;
	private T tag;

	public Arco(VerticeAbs<T> ori, VerticeAbs<T> des, T tag) {
		this.origin = ori;
		this.destiny = des;
		this.tag = tag;
	}
	
	public VerticeAbs<T> getOrigen() {
		return origin;
	}
	
	public VerticeAbs<T> getDestino() {
		return destiny;
	}

	public T getInfo() {
		return tag;
	}
}
