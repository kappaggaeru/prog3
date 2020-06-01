package entregable4;
import java.util.ArrayList;
public class Taller {
	private ArrayList<Dia> dias;
//	private ArrayList<Familia> familias;
	private int capacidad;
	private int diasDisponibles;
//	public Taller(int cap, int dias, ArrayList<Familia> familias) {
	public Taller(int cap, int dias) {
		this.capacidad = cap;
		this.diasDisponibles = dias;
//		this.familias = familias;
		this.dias = new ArrayList<>();
//		this.totalBonos = 0;
		this.crearDias();
	}
	private void crearDias() {
		for(int i=0; i<this.diasDisponibles; i++) {
			int id = i+1;
			dias.add(new Dia(this.capacidad,id));
		}
	}
	public void asignarDiaParaFamilia(Dia d, Familia f) {
		if(!d.contieneFamilia(f)) {
			int miembros = f.miembros();
			if(d.getCapacidad() + miembros <= d.maxCapacidad()) {
				d.agregarFamilia(f);
				f.asignarDia(d.getId());
//				this.totalBonos += f.calcularBono();
			}else {
//capacidad superada, reasignar
//el taller es quien se encarga de definir que familia asiste en que dia
			}
		}
	}
//	public int totalBonos() {
//		return this.totalBonos;
//	}
	public DiasIterator diasIterator(){
		ArrayList<Dia> res = this.dias;
		return new DiasIterator(res); 
	}
	public Dia getDia(int id) {
		return this.dias.get(id-1);
	}
}
