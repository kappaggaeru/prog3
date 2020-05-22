package entregable3;
import java.util.ArrayList;
import java.util.Iterator;
public class ArcoIterator<T> implements Iterator<Arco<T>>{
	private int index;
	private ArrayList<Arco<T>> list;
	public ArcoIterator(ArrayList<Arco<T>> list) {
		this.index = 0;
		this.list = list;
	}
	@Override
	public boolean hasNext() {
		return index < list.size();
	}
	@Override
	public Arco<T> next() {
		index++;
		return list.get(index-1);
	}
}
