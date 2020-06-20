package entregable5;
import java.util.ArrayList;
public class Taller {
	private ArrayList<Dia> dias;
	private int capacidad;
	private int diasDisponibles;
	
	public Taller(int cap, int dias) {
		this.capacidad = cap;
		this.diasDisponibles = dias;
		this.dias = new ArrayList<>();
		this.crearDias();
	}
	private void crearDias() {
		for(int i=0; i<this.diasDisponibles; i++) {
			int id = i+1;
			this.dias.add(new Dia(this.capacidad,id));
		}
	}
	public DiasIterator diasIterator(){
		ArrayList<Dia> res = this.dias;
		return new DiasIterator(res); 
	}
	public Dia getDia(int id) {
		return this.dias.get(id-1);
	}
}