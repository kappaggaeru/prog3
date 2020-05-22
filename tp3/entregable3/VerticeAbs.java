package entregable3;
import java.util.ArrayList;
public abstract class VerticeAbs<T> {
	protected T info;
	protected ArrayList<VerticeAbs<T>> adyacentes;
	public VerticeAbs(T info) {
		this.info = info;
		adyacentes = new ArrayList<VerticeAbs<T>>();
	}
	public T getInfo() {
		return this.info;
	}
	public boolean esAdyacente(VerticeAbs<T> v) {
		return adyacentes.contains(v);
	}
	public void addAdyacente(VerticeAbs<T> v) {
		if(!adyacentes.contains(v))
			adyacentes.add(v);
	}
	public int getTotalAdyacentes() {
		return adyacentes.size();
	}
	public ArrayList<VerticeAbs<T>> getAdyacentes(){
		ArrayList<VerticeAbs<T>> res = adyacentes;
		return res;
	}
}
