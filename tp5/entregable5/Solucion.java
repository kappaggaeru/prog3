package entregable5;

import java.util.ArrayList;

public class Solucion {
	private int valor;
	private long estados;
	private ArrayList<Dia> dias;
	public Solucion() {
		valor = 9999;
		estados = 0L;
		dias = new ArrayList<>();
	}
	public int valor() {
		return valor;
	}
	public long estados() {
		return estados;
	}
	public ArrayList<Dia> dias(){
		return dias;
	}
	public void setValor(int v) {
		valor = v;
	}
	public void setEstado(long e) {
		estados = e;
	}
	public void addDia(Dia d) {
		dias.add(d);
	}
	public void imprimir() {
		for(Dia d:dias)
			System.out.println("Día "+d.getId()+" Capacidad: "+d.getCapacidad()+"/"+d.maxCapacidad()+" Familias ["+d.familiasToString()+"]");
		System.out.println("Bonos: $"+valor);
		System.out.println("Estados al momento de encontrar la solución: "+estados);
	}
}
