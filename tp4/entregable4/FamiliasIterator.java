package entregable4;
import java.util.Iterator;
import java.util.ArrayList;
public class FamiliasIterator implements Iterator<Familia>{
	private int index;
	private ArrayList<Familia> familias;
	
	public FamiliasIterator(ArrayList<Familia> f) {
		this.index = 0;
		this.familias = f;
	}
	@Override
	public boolean hasNext() {
		return this.index < this.familias.size();
	}

	@Override
	public Familia next() {
		this.index++;
		return this.familias.get(index-1);
	}

}
