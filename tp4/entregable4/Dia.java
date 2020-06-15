package entregable4;
import java.util.ArrayList;
public class Dia {
	private int id;
	private int capacidad;
	private int maxCapacidad;
	private ArrayList<Familia> familias;
	public Dia(int max, int id) {
		this.id = id;
		this.capacidad = 0;
		this.maxCapacidad = max;
		this.familias = new ArrayList<>();
	}
	public int getCapacidad() {
		return this.capacidad;
	}
	public int getId() {
		return this.id;
	}
	public void agregarFamilia(Familia f) {
		this.familias.add(f);
		this.capacidad += f.miembros();
	}
	public boolean contieneFamilia(Familia f) {
		return this.familias.contains(f);
	}
	public int maxCapacidad() {
		return this.maxCapacidad;
	}
	public boolean estaCompleto() {
		return this.capacidad == this.maxCapacidad;
	}
	public boolean aceptaFamilia(Familia f) {
		return (f.miembros() + this.capacidad <= this.maxCapacidad) && 
				(!this.familias.contains(f));
	}
	public FamiliasIterator familiasIterator() {
		ArrayList<Familia> res = this.familias;
		return new FamiliasIterator(res);
	}
	public int totalFamilias() {
		return this.familias.size();
	}
	public void removeFamilia(Familia f) {
		if(this.familias.contains(f)) {
			this.familias.remove(f);
			this.capacidad -= f.miembros();
		}
	}
}
