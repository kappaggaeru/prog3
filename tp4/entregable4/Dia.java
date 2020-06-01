package entregable4;
import java.util.ArrayList;
public class Dia {
	private int id;
	private int capacidad;
	private int maxCapacidad;
	private ArrayList<Familia> familias;
	public Dia(int max, int id) {
		this.capacidad = id;
		this.capacidad = 0;
		this.familias = new ArrayList<>();
		this.maxCapacidad = max;
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
		return (f.miembros() + this.capacidad < this.maxCapacidad) && 
				(!this.familias.contains(f));
	}
}
