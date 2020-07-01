package entregable5;
import java.util.ArrayList;
import java.util.Collections;

public class MainBack {
	static long estados = 0L;
	static Solucion solucion = new Solucion();
	static int asignadas = 0;
	static int bono = 0;
	public static void main(String[] args) {
//		CSVReader reader = new CSVReader("/home/kappaggaeru/eclipse-workspace/Programacion3/data/familias-1.csv");
//		CSVReader reader = new CSVReader("/home/kappaggaeru/eclipse-workspace/Programacion3/data/familias-2.csv");
		CSVReader reader = new CSVReader("./data/familias-1.csv");
//		CSVReader reader = new CSVReader("./data/familias-2.csv");
		ArrayList<Familia> familias = reader.read();
		ordenarPorMiembros(familias);
		Taller taller = new Taller(30,10);
		back(taller,familias,0);
		solucion.imprimir();
		System.out.println("Estados totales: "+estados);
	
	}
	public static void back(Taller taller,ArrayList<Familia> familias,int cant) {
		if(cant == familias.size()) {//estado final, se consideraron todas las familias
			if(asignadas == familias.size()) {//estado solucion, todas las familias se asignaron
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
			Familia f = elegirFamilia(familias,cant);
			if(f != null) {
				for(int i=0; i<f.cantDiasPreferidos();i++) {
					Dia d = taller.getDia(f.preferenciaEn(i));
					if(!d.estaCompleto()) {
						if(d.aceptaFamilia(f)) {
							if(!poda()) {
								estados += 1;
								asignar(d,f);
								bono += f.bono();
								asignadas += 1;
								back(taller,familias,cant+1);
								bono -= f.bono();
								desasignar(d,f);
								asignadas -= 1;
							}
						}
					}
				}
			}
		}
	}
	public static boolean poda() {
		return bono >= solucion.valor();
	}
	public static Familia elegirFamilia(ArrayList<Familia> familias, int i) {
		if(i < familias.size()) {
			return familias.get(i);
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
	public static void ordenarPorMiembros(ArrayList<Familia> familias) {
		Collections.sort(familias, new SortMiembros());
	}
}
