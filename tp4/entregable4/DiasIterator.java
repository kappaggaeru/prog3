package entregable4;
import java.util.Iterator;
import java.util.ArrayList;
public class DiasIterator implements Iterator<Dia>{
	private ArrayList<Dia> dias;
	private int index;
	public DiasIterator(ArrayList<Dia> dias) {
		this.dias = dias;
		this.index = 0;
	}
	@Override
	public boolean hasNext() {
		return this.index < this.dias.size();
	}@Override
	public Dia next() {
		this.index ++;
		return this.dias.get(index-1);
	}
}
