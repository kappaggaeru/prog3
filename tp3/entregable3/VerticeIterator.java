package entregable3;

import java.util.Iterator;
import java.util.ArrayList;

public class VerticeIterator<T> implements Iterator<VerticeAbs<T>>{
	private int index;
	private ArrayList<VerticeAbs<T>> list;
	public VerticeIterator(ArrayList<VerticeAbs<T>> list) {
		index = 0;
		this.list = list;
	}
	@Override
	public boolean hasNext() {
		return index < list.size();
	}

	@Override
	public VerticeAbs<T> next() {
		index++;
		return list.get(index-1);
	}
}
