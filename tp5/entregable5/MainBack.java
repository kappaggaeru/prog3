package entregable5;
import java.util.ArrayList;
import java.util.Collections;

public class MainBack {
	static int estados = 0;
	static Solucion solucion = new Solucion();
	static int bono = 0;
	public static void main(String[] args) {
		CSVReader reader = new CSVReader("/home/kappaggaeru/eclipse-workspace/Programacion3/data/familias-1.csv");
//		CSVReader reader = new CSVReader("/home/kappaggaeru/eclipse-workspace/Programacion3/data/familias-2.csv");
		ArrayList<Familia> familias = reader.read();
		ordenarPorMiembros(familias);
		Taller taller = new Taller(30,10);
		probarTodas(taller,familias);
		solucion.imprimir();
	
	}
	public static void probarTodas(Taller taller,ArrayList<Familia> familias) {
		for(Familia f:familias) {
			back(taller,familias,f,0);
		}
	}
	public static void back(Taller taller,ArrayList<Familia> familias,Familia f,int cant) {
		if(cant == familias.size() && f == null) {//estado final
			if(solucion(familias)) {//estado solucion
				int bonosActual = 0;
				for(Familia familia:familias)
					bonosActual += familia.bono();
				if(bonosActual < solucion.valor()) {
					Solucion sol = new Solucion();
					sol.setValor(bonosActual);
					sol.setEstado(estados);
					DiasIterator it = taller.diasIterator();
					while(it.hasNext()) {
						Dia real = it.next();
						Dia copia = new Dia(real.maxCapacidad(),real.getId());
						copia.copiarDia(real);
						sol.addDia(copia);
					}
					solucion = sol;
				}
			}
		}else {
			if(!poda()) {
				for(int i=0; i<f.cantDiasPreferidos();i++) {
					Dia d = taller.getDia(f.preferenciaEn(i));
					if(d.aceptaFamilia(f)) {
						asignar(d,f);
						estados += 1;
						bono += f.bono();
						back(taller,familias,elegirFamilia(familias),cant+1);
						bono -= f.bono();
						desasignar(d,f);
					}
				}
			}
		}
	}
	public static boolean poda() {
		return bono > solucion.valor();
	}
	public static Familia elegirFamilia(ArrayList<Familia> familias) {
		for(Familia f:familias) {
			if(!f.estaAsignada()) {
				return f;
			}
		}
		return null;
	}
	public static void asignar(Dia dia,Familia f) {
		f.asignarDia(dia.getId());
		dia.agregarFamilia(f);
	}
	public static void desasignar(Dia dia,Familia f) {
		dia.removeFamilia(f);
		f.asignarDia(-1);
	}
	public static boolean solucion(ArrayList<Familia> familias) {
		for(Familia f:familias) {
			if(!f.estaAsignada())
				return false;
		}
		return true;
	}
	public static void ordenarPorMiembros(ArrayList<Familia> familias) {
		Collections.sort(familias, new SortMiembros());
	}
}
